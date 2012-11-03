package com.google.android.finsky.exploreactivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiContext;
import com.google.android.finsky.api.DfeRequest;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.previews.MediaPlayerWrapper;
import com.google.android.finsky.previews.StatusListener;
import com.google.android.finsky.remoting.protos.Details.DetailsResponse;
import com.google.android.finsky.remoting.protos.DocList.ListResponse;
import com.google.android.finsky.remoting.protos.DocumentV2.DocV2;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.WishlistHelper;
import com.google.android.finsky.utils.WishlistHelper.WishlistStatusListener;
import com.jme3.math.Vector2f;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NodeController
  implements MusicPreviewManager.MusicPreviewListener
{
  private static final Response.ErrorListener ERROR_LISTENER = new Response.ErrorListener()
  {
    public void onErrorResponse(VolleyError paramAnonymousVolleyError)
    {
      FinskyLog.d(paramAnonymousVolleyError.toString(), new Object[0]);
    }
  };
  public static final float[] FIXED_CHILD_ANGLES = { -0.7391983F, -1.396264F, -2.026834F };
  private static final float[] FIXED_GRANDCHILD_ANGLES = { -1.415132F, -1.72615F };
  private ExploreActivity mActivity;
  private final Vector2f mCenter = new Vector2f(0.0F, 0.0F);
  private DocumentNode mCenterNode;
  private final Vector2f mCenterNodeCenter = new Vector2f(0.0F, 0.0F);
  private List<DocumentNode> mCurrentNodes = Lists.newArrayList();
  private final DfeApi mDfeApi;
  private DrawingUtils mDrawingUtils;
  private final List<DocumentNode> mFadeOutNodes = Lists.newArrayList();
  private boolean mFadingIn = true;
  private int mFrameNum = 0;
  private final MusicPreviewManager mMusicPreviewManager;
  private final Vector2f mNodeVector = new Vector2f(0.0F, 0.0F);
  private PlayState mPlayState = PlayState.STOPPED;
  private long mPlaybackShouldStartTime = System.currentTimeMillis();
  private MediaPlayerWrapper mPlayer = new MediaPlayerWrapper(new StatusListener()
  {
    public void completed()
    {
      NodeController.this.updatePlayState(NodeController.PlayState.STOPPED);
    }

    public void paused()
    {
      NodeController.this.updatePlayState(NodeController.PlayState.STOPPED);
    }

    public void playing()
    {
      NodeController.this.updatePlayState(NodeController.PlayState.PLAYING);
    }

    public void prepared()
    {
      NodeController.this.mPlayer.setVolume(NodeController.this.mVolume, NodeController.this.mVolume);
      NodeController.this.mPlayer.start();
      NodeController.access$302(NodeController.this, true);
    }

    public void queueChanged(int paramAnonymousInt)
    {
      NodeController localNodeController = NodeController.this;
      if (paramAnonymousInt > 0);
      for (NodeController.PlayState localPlayState = NodeController.PlayState.PLAYING; ; localPlayState = NodeController.PlayState.STOPPED)
      {
        localNodeController.updatePlayState(localPlayState);
        return;
      }
    }

    public void reset()
    {
      NodeController.this.updatePlayState(NodeController.PlayState.STOPPED);
    }
  });
  private List<DocumentNode> mPreviousNodes = Lists.newArrayList();
  private final RequestQueue mRequestQueue;
  private final List<Object> mRequestsToCancel = Lists.newArrayList();
  private final List<DocumentNode> mReturnNodes = Lists.newArrayList();
  private float mRotation = 0.0F;
  private DocumentNode mSeedNode;
  private final Vector2f mSize = new Vector2f();
  private SongListener mSongListener;
  private String mSongPlayingNow = null;
  Bitmap mStarHalf;
  Bitmap mStarOff;
  Bitmap mStarOn;
  final ViewGroup mUiComponents;
  private float mVolume = 0.0F;
  private final boolean mWishlistEnabled;

  public NodeController(ExploreActivity paramExploreActivity, DfeApi paramDfeApi, MusicPreviewManager paramMusicPreviewManager, RequestQueue paramRequestQueue)
  {
    this.mDfeApi = paramDfeApi;
    this.mMusicPreviewManager = paramMusicPreviewManager;
    this.mActivity = paramExploreActivity;
    this.mRequestQueue = paramRequestQueue;
    this.mWishlistEnabled = ((Boolean)G.wishlistEnabled.get()).booleanValue();
    Resources localResources = this.mActivity.getResources();
    this.mStarOn = BitmapFactory.decodeResource(localResources, 2130837680);
    this.mStarOff = BitmapFactory.decodeResource(localResources, 2130837679);
    this.mStarHalf = BitmapFactory.decodeResource(localResources, 2130837678);
    this.mUiComponents = ((ViewGroup)((LayoutInflater)this.mActivity.getApplicationContext().getSystemService("layout_inflater")).inflate(2130968692, null));
    this.mUiComponents.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
    this.mUiComponents.measure(0, 0);
    this.mUiComponents.layout(0, 0, 0, 0);
  }

  private void animateNode(DocumentNode paramDocumentNode, float[] paramArrayOfFloat, float paramFloat)
  {
    int i = paramDocumentNode.getChildNodes().size();
    int j = 0;
    if (j < i)
    {
      DocumentNode localDocumentNode = paramDocumentNode.getChildNode(j);
      float f1;
      label47: float f3;
      float f4;
      if (j < paramArrayOfFloat.length)
      {
        f1 = paramArrayOfFloat[j] + this.mRotation;
        float f2 = normalizeAngle(f1);
        f3 = localDocumentNode.getAngle();
        f4 = f2 - f3;
        if (f4 <= 3.141593F)
          break label172;
        f4 = 3.141593F - f4;
        label86: if (f4 != 0.0F)
          break label192;
      }
      label172: label192: for (float f5 = 0.0F; ; f5 = f4 / Math.abs(f4))
      {
        localDocumentNode.setAngle(normalizeAngle(f3 + Math.min(4.0F * (float)Math.pow(f4 * f5, 1.200000047683716D), 12.566371F) * (paramFloat * f5)));
        j++;
        break;
        if (j < FIXED_CHILD_ANGLES.length)
        {
          f1 = 2.827433F;
          break label47;
        }
        f1 = 1.570796F + this.mRotation;
        break label47;
        if (f4 >= -3.141593F)
          break label86;
        f4 = -3.141593F - f4;
        break label86;
      }
    }
  }

  private void animateNodes(float paramFloat)
  {
    animateNode(this.mCenterNode, FIXED_CHILD_ANGLES, paramFloat);
    int i = this.mCenterNode.getChildNodes().size();
    for (int j = 0; j < i; j++)
      if (j < FIXED_CHILD_ANGLES.length)
        animateNode(this.mCenterNode.getChildNode(j), FIXED_GRANDCHILD_ANGLES, paramFloat);
    float f;
    if (this.mPlayer.isPlaying())
    {
      f = this.mVolume;
      if (!this.mFadingIn)
        break label220;
    }
    label220: for (this.mVolume = Math.min(1.0F, this.mVolume + paramFloat / 0.5F); ; this.mVolume = Math.max(0.0F, this.mVolume - paramFloat / 0.1F))
    {
      if (f != this.mVolume)
      {
        this.mPlayer.setVolume(this.mVolume, this.mVolume);
        if ((this.mVolume == 0.0F) && (!this.mFadingIn))
          this.mPlayer.pause();
      }
      if ((this.mPlayState == PlayState.PLAYING) && (this.mCenterNode.getDoc().getSong() != null) && (!this.mCenterNode.getDoc().getSong().getDocId().equals(this.mSongPlayingNow)) && (System.currentTimeMillis() - this.mPlaybackShouldStartTime > 500L))
        resetPlayback(PlayState.BUFFERING);
      return;
    }
  }

  private void calculateCenter(DocumentNode paramDocumentNode, float paramFloat)
  {
    paramDocumentNode.setDistanceToCenter(getNodeCenter(paramDocumentNode).distance(this.mCenter) / paramFloat);
  }

  private void clearNodePositions(DocumentNode paramDocumentNode)
  {
    paramDocumentNode.getCenter().set((0.0F / 0.0F), (0.0F / 0.0F));
    List localList = paramDocumentNode.getChildNodes();
    for (int i = 0; i < localList.size(); i++)
      clearNodePositions((DocumentNode)localList.get(i));
  }

  private List<Document> convertDocList(DocList.ListResponse paramListResponse)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramListResponse.getDocCount() > 0) && (paramListResponse.getDoc(0).getChildCount() > 0))
    {
      List localList = paramListResponse.getDoc(0).getChildList();
      for (int i = 0; i < localList.size(); i++)
        localArrayList.add(new Document((DocumentV2.DocV2)localList.get(i), null));
    }
    localArrayList.trimToSize();
    return localArrayList;
  }

  private void disposeObjects(DocumentNode paramDocumentNode)
  {
    if (paramDocumentNode == null);
    while (true)
    {
      return;
      paramDocumentNode.disposeObjects();
      List localList = paramDocumentNode.getChildNodes();
      for (int i = 0; i < localList.size(); i++)
        disposeObjects((DocumentNode)localList.get(i));
      paramDocumentNode.removeFromParent();
    }
  }

  private void dumpNodeLeaf(DocumentNode paramDocumentNode, int paramInt)
  {
    int i = 0;
    StringBuilder localStringBuilder1 = new StringBuilder(256);
    for (int j = 0; j < paramInt; j++)
      localStringBuilder1.append("  ");
    StringBuilder localStringBuilder2 = new StringBuilder(localStringBuilder1);
    localStringBuilder1.append(paramDocumentNode.toString());
    FinskyLog.d(localStringBuilder1.toString(), new Object[0]);
    List localList1 = paramDocumentNode.mDocWrapper.getRelations();
    if (localList1 != null)
      for (int k = 0; k < localList1.size(); k++)
      {
        StringBuilder localStringBuilder3 = new StringBuilder(localStringBuilder2);
        localStringBuilder3.append("  - ");
        localStringBuilder3.append(((DocWrapper)localList1.get(k)).toString());
        FinskyLog.d(localStringBuilder3.toString(), new Object[0]);
      }
    List localList2 = paramDocumentNode.getChildNodes();
    while (i < localList2.size())
    {
      dumpNodeLeaf((DocumentNode)localList2.get(i), paramInt + 1);
      i++;
    }
  }

  private float normalizeAngle(float paramFloat)
  {
    while (paramFloat > 6.283186F)
      paramFloat -= 6.283186F;
    while (paramFloat < 0.0F)
      paramFloat += 6.283186F;
    return paramFloat;
  }

  private void prepareOnscreenNodes(DocumentNode paramDocumentNode, float paramFloat)
  {
    int i = 0;
    int j = 0;
    if (paramDocumentNode == this.mCenterNode)
    {
      loadData(paramDocumentNode, 15, Request.Priority.HIGH);
      this.mCenterNode.createChildren(8);
      i = 1;
    }
    while (true)
    {
      if (i != 0)
      {
        this.mCurrentNodes.add(paramDocumentNode);
        calculateCenter(paramDocumentNode, paramFloat);
      }
      if ((i != 0) || (j != 0))
        break label358;
      if (paramDocumentNode.mDocWrapper.getInProgressState() != 0)
        this.mRequestsToCancel.add(paramDocumentNode.mDocWrapper);
      List localList2 = paramDocumentNode.mDocWrapper.getRelations();
      if (localList2 == null)
        break;
      this.mRequestsToCancel.addAll(localList2);
      List localList3 = paramDocumentNode.getChildNodes();
      for (int m = 0; m < localList3.size(); m++)
        this.mRequestsToCancel.remove(((DocumentNode)localList3.get(m)).mDocWrapper);
      if ((this.mCenterNode.hasParentNode()) && (this.mCenterNode.getParentNode() == paramDocumentNode))
      {
        loadData(paramDocumentNode, 15, Request.Priority.LOW);
        j = 1;
      }
      else if ((paramDocumentNode.hasParentNode()) && (paramDocumentNode.getParentNode() == this.mCenterNode))
      {
        loadData(paramDocumentNode, 11, Request.Priority.NORMAL);
        if (this.mCenterNode.getChildNodePos(paramDocumentNode) < FIXED_CHILD_ANGLES.length)
        {
          loadData(paramDocumentNode, 4, Request.Priority.NORMAL);
          paramDocumentNode.createChildren(FIXED_CHILD_ANGLES.length);
          i = 1;
        }
        else
        {
          j = 1;
        }
      }
      else if ((paramDocumentNode.hasParentNode()) && (paramDocumentNode.getParentNode().hasParentNode()) && (paramDocumentNode.getParentNode().getParentNode() == this.mCenterNode))
      {
        DocumentNode localDocumentNode = paramDocumentNode.getParentNode();
        if ((this.mCenterNode.getChildNodePos(localDocumentNode) < FIXED_CHILD_ANGLES.length) && (localDocumentNode.getChildNodePos(paramDocumentNode) < FIXED_GRANDCHILD_ANGLES.length))
        {
          loadData(paramDocumentNode, 3, Request.Priority.LOW);
          i = 1;
        }
      }
    }
    paramDocumentNode.disposeObjects();
    label358: List localList1 = paramDocumentNode.getChildNodes();
    for (int k = 0; k < localList1.size(); k++)
      prepareOnscreenNodes((DocumentNode)localList1.get(k), paramFloat);
  }

  private List<DocumentNode> processFadeOutNodes()
  {
    for (int i = 0; i < this.mFadeOutNodes.size(); i++)
      if (!((DocumentNode)this.mFadeOutNodes.get(i)).isVisible())
      {
        this.mFadeOutNodes.remove(i);
        i--;
      }
    return this.mFadeOutNodes;
  }

  private void queueRequest(String paramString, DocWrapper paramDocWrapper, final Request.Priority paramPriority, Class<?> paramClass, Response.Listener<?> paramListener)
  {
    DfeRequest local9 = new DfeRequest(paramString, this.mDfeApi.getApiContext(), paramClass, paramListener, ERROR_LISTENER)
    {
      public Request.Priority getPriority()
      {
        return paramPriority;
      }
    };
    local9.setTag(paramDocWrapper);
    this.mRequestQueue.add(local9);
  }

  private Request.Priority raise(Request.Priority paramPriority)
  {
    Request.Priority[] arrayOfPriority = Request.Priority.values();
    return arrayOfPriority[Math.min(-1 + arrayOfPriority.length, 1 + paramPriority.ordinal())];
  }

  private void updatePlayState(PlayState paramPlayState)
  {
    this.mPlayState = paramPlayState;
    if (this.mSongListener != null)
      if (this.mCenterNode.getDoc().getSong() == null)
        break label54;
    label54: for (String str = this.mCenterNode.getDoc().getSong().getTitle(); ; str = null)
    {
      this.mSongListener.onPlayStateChanged(this.mPlayState, str);
      return;
    }
  }

  public void chooseCenterNode(DocumentNode paramDocumentNode)
  {
    DocumentNode localDocumentNode1 = paramDocumentNode;
    if (this.mCenter.y > getNodeCenter(paramDocumentNode).y);
    for (int i = 0; ; i++)
      if (i < paramDocumentNode.getChildNodes().size())
      {
        DocumentNode localDocumentNode2 = (DocumentNode)paramDocumentNode.getChildNodes().get(i);
        if ((localDocumentNode2.isVisible()) && (localDocumentNode2.getDistanceToCenter() < localDocumentNode1.getDistanceToCenter()))
          localDocumentNode1 = (DocumentNode)paramDocumentNode.getChildNodes().get(i);
      }
      else
      {
        if ((paramDocumentNode.hasParentNode()) && (Math.abs(this.mCenter.y - getNodeCenter(paramDocumentNode.getParentNode()).y) < Math.abs(this.mCenter.y - getNodeCenter(localDocumentNode1).y)))
          localDocumentNode1 = paramDocumentNode.getParentNode();
        if (this.mCenterNode != localDocumentNode1)
          setCenterNode(localDocumentNode1);
        return;
      }
  }

  public DocumentNode createDocumentNode(DocumentNode paramDocumentNode, DocWrapper paramDocWrapper, DrawingUtils paramDrawingUtils)
  {
    Object localObject;
    switch (paramDocWrapper.getDoc().getBackend())
    {
    default:
      throw new IllegalArgumentException("Unknown backend type " + paramDocWrapper.getDoc().getBackend());
    case 3:
      localObject = new AppNode(paramDocumentNode, paramDocWrapper, this, paramDrawingUtils, this.mUiComponents);
    case 2:
    case 1:
    case 4:
    }
    while (true)
    {
      return localObject;
      switch (paramDocWrapper.getDoc().getDocumentType())
      {
      default:
        localObject = new MusicArtistNode(paramDocumentNode, paramDocWrapper, this, paramDrawingUtils, this.mUiComponents);
        break;
      case 3:
        localObject = new MusicArtistNode(paramDocumentNode, paramDocWrapper, this, paramDrawingUtils, this.mUiComponents);
        break;
      case 2:
        localObject = new MusicAlbumNode(paramDocumentNode, paramDocWrapper, this, paramDrawingUtils, this.mUiComponents);
        continue;
        localObject = new BookNode(paramDocumentNode, paramDocWrapper, this, paramDrawingUtils, this.mUiComponents);
        continue;
        localObject = new VideoNode(paramDocumentNode, paramDocWrapper, this, paramDrawingUtils, this.mUiComponents);
      }
    }
  }

  public void createRoot(DocWrapper paramDocWrapper, DrawingUtils paramDrawingUtils)
  {
    try
    {
      runOnGlThread(new Runnable()
      {
        public void run()
        {
          Thread.currentThread().setPriority(10);
        }
      });
      this.mCenterNode = createDocumentNode(null, paramDocWrapper, paramDrawingUtils);
      this.mSeedNode = this.mCenterNode;
      this.mDrawingUtils = paramDrawingUtils;
      if (shouldShowPlaybackControls())
        this.mPlayState = PlayState.BUFFERING;
      loadData(this.mCenterNode.mDocWrapper, 15, Request.Priority.IMMEDIATE);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void disposeObjects()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        NodeController.this.mRequestQueue.cancelAll(new RequestQueue.RequestFilter()
        {
          public boolean apply(Request<?> paramAnonymous2Request)
          {
            Object localObject = paramAnonymous2Request.getTag();
            if ((localObject != null) && ((localObject instanceof DocWrapper)));
            for (boolean bool = true; ; bool = false)
              return bool;
          }
        });
        NodeController.this.disposeObjects(NodeController.this.mSeedNode);
        NodeController.this.mPreviousNodes.clear();
      }
    });
  }

  public void dumpControllerState()
  {
    FinskyLog.d("NodeController state:", new Object[0]);
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = this.mCenterNode.toString();
    FinskyLog.d("Center node: %s", arrayOfObject1);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = this.mCenter.toString();
    FinskyLog.d("Center %s", arrayOfObject2);
    FinskyLog.d("Node tree:", new Object[0]);
    dumpNodeLeaf(this.mSeedNode, 0);
  }

  public void fileReady(DocWrapper paramDocWrapper, File paramFile)
  {
    Document localDocument = paramDocWrapper.getSong();
    if ((this.mCenterNode.mDocWrapper == paramDocWrapper) && (!localDocument.getDocId().equals(this.mSongPlayingNow)));
    try
    {
      if ((this.mPlayState == PlayState.BUFFERING) || (this.mPlayState == PlayState.PLAYING))
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = localDocument.getTitle();
        FinskyLog.d("Starting playback of song %s", arrayOfObject2);
        this.mPlayer.reset();
        FileInputStream localFileInputStream = new FileInputStream(paramFile);
        this.mPlayer.setDataSource(localFileInputStream.getFD());
        localFileInputStream.close();
        this.mPlayer.prepareAsync();
        this.mSongPlayingNow = localDocument.getDocId();
      }
      updatePlayState(this.mPlayState);
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localException.toString();
        FinskyLog.e("Exception trying to get sample: %s", arrayOfObject1);
      }
    }
  }

  DocumentNode getCenterNode()
  {
    return this.mCenterNode;
  }

  public Vector2f getNodeCenter(DocumentNode paramDocumentNode)
  {
    Vector2f localVector2f = paramDocumentNode.getCenter();
    if ((!Float.isNaN(localVector2f.x)) && (!Float.isNaN(localVector2f.y)));
    while (true)
    {
      return localVector2f;
      if ((Float.isNaN(this.mSeedNode.getCenter().x)) || (Float.isNaN(this.mSeedNode.getCenter().y)))
      {
        this.mCenterNode.getCenter().set(this.mCenterNodeCenter);
        DocumentNode localDocumentNode;
        for (Object localObject = this.mCenterNode; ; localObject = localDocumentNode)
        {
          localDocumentNode = ((DocumentNode)localObject).getParentNode();
          if (localDocumentNode == null)
            break;
          localDocumentNode.getCenter().set(((DocumentNode)localObject).getCenter()).subtractLocal(((DocumentNode)localObject).getRelativePosition());
        }
      }
      if (paramDocumentNode.getParentNode() != null)
      {
        this.mNodeVector.set(paramDocumentNode.getRelativePosition());
        localVector2f.set(getNodeCenter(paramDocumentNode.getParentNode())).addLocal(this.mNodeVector);
      }
    }
  }

  public float getRotation()
  {
    return this.mRotation;
  }

  public boolean isSwipable()
  {
    if (this.mCenterNode.getChildNodes().size() > FIXED_CHILD_ANGLES.length);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isWishlistEnabled()
  {
    return this.mWishlistEnabled;
  }

  public void loadData(final DocWrapper paramDocWrapper, final int paramInt, final Request.Priority paramPriority)
  {
    int i = paramDocWrapper.getLoadedState();
    int j = paramInt & (i ^ 0xFFFFFFFF) & (0xFFFFFFFF ^ paramDocWrapper.getInProgressState());
    if ((i & 0x2) == 0);
    for (int k = j & 0xFFFFFFF3; ; k = j)
    {
      paramDocWrapper.markInProgress(k);
      if ((k & 0x1) != 0)
      {
        Request localRequest = this.mDrawingUtils.getThumbnailRequest(paramDocWrapper, paramPriority, ERROR_LISTENER);
        localRequest.setTag(paramDocWrapper);
        this.mRequestQueue.add(localRequest);
      }
      if ((k & 0x2) != 0)
        queueRequest(paramDocWrapper.getDetailsUrl(), paramDocWrapper, paramPriority, Details.DetailsResponse.class, new Response.Listener()
        {
          public void onResponse(Details.DetailsResponse paramAnonymousDetailsResponse)
          {
            if (paramAnonymousDetailsResponse.hasDocV2())
            {
              paramDocWrapper.setDoc(new Document(paramAnonymousDetailsResponse.getDocV2(), null));
              paramDocWrapper.markLoaded(2);
              NodeController.this.loadData(paramDocWrapper, paramInt, paramPriority);
            }
          }
        });
      String str;
      if ((k & 0x4) != 0)
      {
        if (paramDocWrapper.getDoc().getBackend() != 2)
          return;
        str = paramDocWrapper.getDoc().getCoreContentListUrl();
        if (TextUtils.isEmpty(str))
          break label210;
      }
      while (true)
      {
        if (!TextUtils.isEmpty(str))
          queueRequest(str, paramDocWrapper, paramPriority, DocList.ListResponse.class, new Response.Listener()
          {
            public void onResponse(DocList.ListResponse paramAnonymousListResponse)
            {
              boolean bool = false;
              List localList = NodeController.this.convertDocList(paramAnonymousListResponse);
              paramDocWrapper.setSongList(localList);
              if (localList.size() > 0)
              {
                Document localDocument = (Document)localList.get(0);
                paramDocWrapper.setSong(localDocument);
                MusicPreviewManager localMusicPreviewManager = NodeController.this.mMusicPreviewManager;
                DocWrapper localDocWrapper = paramDocWrapper;
                NodeController localNodeController = NodeController.this;
                if (paramDocWrapper == NodeController.this.mCenterNode.mDocWrapper)
                  bool = true;
                localMusicPreviewManager.fetchPreview(localDocWrapper, localNodeController, bool);
              }
            }
          });
        if ((k & 0x8) == 0)
          break;
        queueRequest(paramDocWrapper.getRelatedItemUrl(), paramDocWrapper, raise(paramPriority), DocList.ListResponse.class, new Response.Listener()
        {
          public void onResponse(DocList.ListResponse paramAnonymousListResponse)
          {
            ArrayList localArrayList = Lists.newArrayList();
            if ((paramAnonymousListResponse.getDocCount() > 0) && (paramAnonymousListResponse.getDoc(0).getChildCount() > 0))
            {
              Iterator localIterator = paramAnonymousListResponse.getDoc(0).getChildList().iterator();
              while (localIterator.hasNext())
                localArrayList.add(new DocWrapper(new Document((DocumentV2.DocV2)localIterator.next(), null)));
            }
            localArrayList.trimToSize();
            paramDocWrapper.setRelations(localArrayList);
          }
        });
        break;
        label210: str = paramDocWrapper.getDoc().getRelatedDocTypeListUrl();
      }
    }
  }

  public void loadData(DocumentNode paramDocumentNode, int paramInt, Request.Priority paramPriority)
  {
    if (paramDocumentNode.hasThumbnail())
    {
      paramInt &= -2;
      if ((0x1 & paramDocumentNode.mDocWrapper.getLoadedState()) > 0)
        paramDocumentNode.recycleThumbnail();
    }
    while (true)
    {
      loadData(paramDocumentNode.mDocWrapper, paramInt, paramPriority);
      return;
      if (((0x1 & paramDocumentNode.mDocWrapper.getLoadedState()) > 0) && (!paramDocumentNode.hasThumbnail()))
      {
        paramInt &= -2;
        paramDocumentNode.setNodeThumbnail();
      }
    }
  }

  public List<DocumentNode> processOnscreenNodes(Vector2f[] paramArrayOfVector2f, float paramFloat)
  {
    clearNodePositions(this.mSeedNode);
    List localList1 = this.mCurrentNodes;
    this.mCurrentNodes = this.mPreviousNodes;
    this.mPreviousNodes = localList1;
    this.mCurrentNodes.clear();
    DrawingUtils.getPolyCenter(paramArrayOfVector2f, this.mCenter);
    DrawingUtils.getPolySize(paramArrayOfVector2f, this.mSize);
    Vector2f localVector2f1 = this.mCenter;
    localVector2f1.y -= 0.4F * this.mSize.y;
    Vector2f localVector2f2 = this.mCenter;
    localVector2f2.x -= 0.17F * this.mSize.x;
    float f = FloatMath.sqrt(this.mSize.x * this.mSize.x + this.mSize.y * this.mSize.y) / 2.0F;
    synchronized (this.mRequestsToCancel)
    {
      prepareOnscreenNodes(this.mSeedNode, f);
      if (this.mRequestsToCancel.size() > 0)
        runOnUiThread(new Runnable()
        {
          public void run()
          {
            List localList = NodeController.this.mRequestsToCancel;
            int i = 0;
            try
            {
              while (i < NodeController.this.mRequestsToCancel.size())
              {
                DocWrapper localDocWrapper = (DocWrapper)NodeController.this.mRequestsToCancel.get(i);
                NodeController.this.mRequestQueue.cancelAll(localDocWrapper);
                NodeController.this.mMusicPreviewManager.cancel(localDocWrapper);
                localDocWrapper.clearInProgress();
                i++;
              }
              NodeController.this.mRequestsToCancel.clear();
              return;
            }
            finally
            {
              localObject = finally;
              throw localObject;
            }
          }
        });
      animateNodes(paramFloat);
      int i = 0;
      if (i < this.mCurrentNodes.size())
      {
        DocumentNode localDocumentNode = (DocumentNode)this.mCurrentNodes.get(i);
        if (!this.mPreviousNodes.contains(localDocumentNode))
        {
          if (this.mFadeOutNodes.contains(localDocumentNode))
            this.mFadeOutNodes.remove(localDocumentNode);
          ((DocumentNode)this.mCurrentNodes.get(i)).fade(true);
        }
        i++;
      }
    }
    for (int j = 0; j < this.mPreviousNodes.size(); j++)
      if (!this.mCurrentNodes.contains(this.mPreviousNodes.get(j)))
      {
        this.mFadeOutNodes.add(this.mPreviousNodes.get(j));
        ((DocumentNode)this.mPreviousNodes.get(j)).fade(false);
      }
    this.mReturnNodes.clear();
    List localList3 = processFadeOutNodes();
    for (int k = 0; k < localList3.size(); k++)
      this.mReturnNodes.add(localList3.get(k));
    for (int m = 0; m < this.mCurrentNodes.size(); m++)
      this.mReturnNodes.add(this.mCurrentNodes.get(m));
    for (int n = 0; n < this.mReturnNodes.size(); n++)
      getNodeCenter((DocumentNode)this.mReturnNodes.get(n));
    if (FinskyLog.DEBUG)
    {
      int i1 = 1 + this.mFrameNum;
      this.mFrameNum = i1;
      if (i1 % 500 == 0)
        dumpControllerState();
    }
    return this.mReturnNodes;
  }

  public void resetPlayback(PlayState paramPlayState)
  {
    this.mSongPlayingNow = null;
    this.mPlayer.pause();
    updatePlayState(paramPlayState);
  }

  public void rotate(float paramFloat)
  {
    this.mRotation = normalizeAngle(paramFloat + this.mRotation);
    if (this.mCenterNode.getChildNodes().size() == 0);
    while (true)
    {
      return;
      for (int i = 0; i < FIXED_CHILD_ANGLES.length; i++)
      {
        DocumentNode localDocumentNode = this.mCenterNode.getChildNode(i);
        localDocumentNode.setAngle(normalizeAngle(paramFloat + localDocumentNode.getAngle()));
      }
      if ((this.mRotation > 0.5235988F) && (6.283186F - this.mRotation > 0.5235988F))
        if (this.mRotation < 3.141593F)
        {
          this.mRotation = normalizeAngle(this.mRotation - 0.5235988F);
          this.mCenterNode.rotateChildren(1);
          FinskyLog.d("Applied child rotation of +1", new Object[0]);
        }
        else
        {
          this.mRotation = normalizeAngle(0.5235988F + this.mRotation);
          this.mCenterNode.rotateChildren(-1);
          FinskyLog.d("Applied child rotation of -1", new Object[0]);
        }
    }
  }

  public void runOnGlThread(Runnable paramRunnable)
  {
    this.mActivity.runOnGlThread(paramRunnable);
  }

  public void runOnUiThread(Runnable paramRunnable)
  {
    this.mActivity.runOnUiThread(paramRunnable);
  }

  public void setCenterNode(DocumentNode paramDocumentNode)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramDocumentNode.toString();
    FinskyLog.d("New center: %s", arrayOfObject1);
    this.mCenterNodeCenter.set(getNodeCenter(paramDocumentNode));
    this.mCenterNode = paramDocumentNode;
    this.mRotation = 0.0F;
    FinskyApp.get().getAnalytics().logPageView(null, paramDocumentNode.getDoc().getDoc().getCookie(), "exploreNavigateTo?doc=" + paramDocumentNode.getDoc().getDocId());
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = "cidi";
    arrayOfObject2[1] = paramDocumentNode.getDoc().getDocId();
    localFinskyEventLog.logTag("exploreNavigateTo", arrayOfObject2);
    if ((this.mPlayState == PlayState.BUFFERING) || (this.mPlayState == PlayState.PLAYING))
      startPlayback();
    while (true)
    {
      int i = 1;
      DocumentNode localDocumentNode = paramDocumentNode;
      while (localDocumentNode.hasParentNode())
      {
        localDocumentNode = localDocumentNode.getParentNode();
        i++;
        if (i > 10)
        {
          this.mSeedNode = localDocumentNode;
          localDocumentNode.clearParentNode();
        }
      }
      return;
      resetPlayback(PlayState.STOPPED);
    }
  }

  public void setSongListener(SongListener paramSongListener)
  {
    this.mSongListener = paramSongListener;
  }

  public boolean shouldShowPlaybackControls()
  {
    if (this.mCenterNode.getDoc().getDoc().getBackend() == 2);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void startPlayback()
  {
    Document localDocument = this.mCenterNode.mDocWrapper.getSong();
    if (localDocument != null)
    {
      if (this.mPlayState == PlayState.STOPPED)
      {
        this.mPlayState = PlayState.PLAYING;
        this.mPlaybackShouldStartTime = System.currentTimeMillis();
      }
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localDocument.getTitle();
      FinskyLog.d("Fetching preview of %s", arrayOfObject);
      this.mMusicPreviewManager.fetchPreview(this.mCenterNode.mDocWrapper, this, true);
    }
  }

  public void togglePlayback()
  {
    if (this.mPlayState == PlayState.BUFFERING)
      updatePlayState(PlayState.STOPPED);
    while (true)
    {
      return;
      if (this.mPlayer.isPlaying())
      {
        this.mFadingIn = false;
      }
      else if (this.mSongPlayingNow != null)
      {
        this.mPlayer.setVolume(this.mVolume, this.mVolume);
        this.mPlayer.start();
        this.mFadingIn = true;
      }
      else
      {
        startPlayback();
      }
    }
  }

  public void toggleWishlist(final DocumentNode paramDocumentNode)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        WishlistHelper.processWishlistClick(paramDocumentNode.getDoc().getDoc(), NodeController.this.mDfeApi, this.val$listener);
      }
    });
  }

  public static enum PlayState
  {
    static
    {
      PLAYING = new PlayState("PLAYING", 1);
      BUFFERING = new PlayState("BUFFERING", 2);
      PlayState[] arrayOfPlayState = new PlayState[3];
      arrayOfPlayState[0] = STOPPED;
      arrayOfPlayState[1] = PLAYING;
      arrayOfPlayState[2] = BUFFERING;
    }
  }

  public static abstract interface SongListener
  {
    public abstract void onPlayStateChanged(NodeController.PlayState paramPlayState, String paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.exploreactivity.NodeController
 * JD-Core Version:    0.6.2
 */