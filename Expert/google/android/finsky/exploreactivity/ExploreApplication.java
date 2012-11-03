package com.google.android.finsky.exploreactivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Lists;
import com.jme3.app.Application;
import com.jme3.app.ResetStatsState;
import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.controls.TouchListener;
import com.jme3.input.controls.TouchTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.input.event.TouchEvent;
import com.jme3.input.event.TouchEvent.Type;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import com.jme3.system.Timer;
import java.util.List;

public class ExploreApplication extends Application
  implements NodeController.SongListener, TouchListener
{
  private final CollisionResults collisionResults;
  private final Vector3f delta;
  private ExploreActivity mActivity;
  private Geometry mBackground;
  private final Vector3f mCameraLocation = new Vector3f(0.0F, 0.0F, 1000.0F);
  private DrawingUtils mDrawingUtils;
  private boolean mInRotation;
  private boolean mInScroll;
  private float mLastMoveX;
  private float mLastMoveY;
  long mLastUpdate;
  private final NodeController mNodeController;
  private DocumentNode mOriginalCenterNode;
  private final RelativeLayout mParentLayout;
  private TextView mPlaybackControls;
  private List<DocumentNode> mPreviousNodes;
  private Node mRootNode;
  private final Vector2f[] mScreenBounds;
  float mScreenHeight;
  private float mScreenScaleFactor;
  float mScreenWidth;
  private final float mScrollThreshold;
  private Document mSeedDocument;
  private Geometry mSwipe;
  private FadeAdapter mSwipeFader;
  private Vector2f mTouch;
  private Vector2f mTouchWorld;
  private boolean mWasInRotation;
  private final Vector2f[] mWorldBounds;
  private final Ray ray;
  private final Vector2f screenCenter;
  private final Vector2f screenSize;

  public ExploreApplication(ExploreActivity paramExploreActivity, NodeController paramNodeController, Document paramDocument, RelativeLayout paramRelativeLayout)
  {
    Vector2f[] arrayOfVector2f1 = new Vector2f[4];
    arrayOfVector2f1[0] = new Vector2f();
    arrayOfVector2f1[1] = new Vector2f();
    arrayOfVector2f1[2] = new Vector2f();
    arrayOfVector2f1[3] = new Vector2f();
    this.mScreenBounds = arrayOfVector2f1;
    Vector2f[] arrayOfVector2f2 = new Vector2f[4];
    arrayOfVector2f2[0] = new Vector2f();
    arrayOfVector2f2[1] = new Vector2f();
    arrayOfVector2f2[2] = new Vector2f();
    arrayOfVector2f2[3] = new Vector2f();
    this.mWorldBounds = arrayOfVector2f2;
    this.mPreviousNodes = Lists.newArrayList();
    this.mRootNode = new Node("Root Node");
    this.mOriginalCenterNode = null;
    this.mLastUpdate = 0L;
    this.mInScroll = false;
    this.mInRotation = false;
    this.mWasInRotation = false;
    this.mTouch = new Vector2f();
    this.mTouchWorld = new Vector2f();
    this.delta = new Vector3f();
    this.screenSize = new Vector2f();
    this.screenCenter = new Vector2f();
    this.ray = new Ray();
    this.collisionResults = new CollisionResults();
    this.mSeedDocument = paramDocument;
    this.mParentLayout = paramRelativeLayout;
    this.mActivity = paramExploreActivity;
    this.mNodeController = paramNodeController;
    this.mNodeController.setSongListener(this);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(this.mActivity);
    this.mScrollThreshold = (localViewConfiguration.getScaledTouchSlop() * localViewConfiguration.getScaledTouchSlop());
    this.stateManager.detach(this.stateManager.getState(ResetStatsState.class));
  }

  private void doNodeTap(DocumentNode paramDocumentNode, CollisionResult paramCollisionResult)
  {
    if (paramDocumentNode == this.mNodeController.getCenterNode())
      if ((this.mNodeController.isWishlistEnabled()) && (paramDocumentNode.isWishlistTap(paramCollisionResult)))
        this.mNodeController.toggleWishlist(paramDocumentNode);
    while (true)
    {
      return;
      this.mActivity.startActivity(IntentUtils.createViewDocumentIntent(this.mActivity, paramDocumentNode.getDoc().getDoc()));
      continue;
      this.mNodeController.setCenterNode(paramDocumentNode);
    }
  }

  // ERROR //
  private int expandHeap(int paramInt)
  {
    // Byte code:
    //   0: iload_1
    //   1: anewarray 228	[B
    //   4: astore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: iload_3
    //   8: aload_2
    //   9: arraylength
    //   10: if_icmpge +16 -> 26
    //   13: aload_2
    //   14: iload_3
    //   15: ldc 229
    //   17: newarray byte
    //   19: aastore
    //   20: iinc 3 1
    //   23: goto -16 -> 7
    //   26: iconst_1
    //   27: anewarray 231	java/lang/Object
    //   30: astore 8
    //   32: aload 8
    //   34: iconst_0
    //   35: iload_3
    //   36: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   39: aastore
    //   40: ldc 239
    //   42: aload 8
    //   44: invokestatic 245	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   47: iload_3
    //   48: ireturn
    //   49: astore 6
    //   51: iconst_1
    //   52: anewarray 231	java/lang/Object
    //   55: astore 7
    //   57: aload 7
    //   59: iconst_0
    //   60: iload_3
    //   61: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   64: aastore
    //   65: ldc 239
    //   67: aload 7
    //   69: invokestatic 245	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   72: goto -25 -> 47
    //   75: astore 4
    //   77: iconst_1
    //   78: anewarray 231	java/lang/Object
    //   81: astore 5
    //   83: aload 5
    //   85: iconst_0
    //   86: iload_3
    //   87: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   90: aastore
    //   91: ldc 239
    //   93: aload 5
    //   95: invokestatic 245	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   98: aload 4
    //   100: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   7	20	49	java/lang/OutOfMemoryError
    //   7	20	75	finally
  }

  private void updateCamera(Vector3f paramVector3f)
  {
    this.mCameraLocation.addLocal(paramVector3f);
    this.cam.setLocation(this.mCameraLocation);
    this.cam.setParallelProjection(true);
    this.cam.setFrustum(900.0F, 1100.0F, this.mCameraLocation.x - 0.33F * this.mScreenWidth, this.mCameraLocation.x + 0.67F * this.mScreenWidth, this.mCameraLocation.y + 0.9F * this.mScreenHeight, this.mCameraLocation.y - 0.09999999F * this.mScreenHeight);
    for (int i = 0; i < this.mScreenBounds.length; i++)
      this.mDrawingUtils.getBasePlaneCoords(this.cam, this.mScreenBounds[i], this.mWorldBounds[i]);
    DrawingUtils.getPolySize(this.mWorldBounds, this.screenSize);
    this.mBackground.setLocalScale(this.screenSize.x, this.screenSize.y, 1.0F);
    this.mBackground.setLocalTranslation(this.mWorldBounds[0].x, this.mWorldBounds[3].y, -1.0F);
    this.mSwipe.setLocalTranslation(this.mWorldBounds[0].x, this.mWorldBounds[3].y + 0.1F * this.mScreenHeight, 0.5F);
  }

  public void createViews()
  {
    this.mPlaybackControls = ((TextView)((LayoutInflater)this.mActivity.getApplicationContext().getSystemService("layout_inflater")).inflate(2130968693, this.mParentLayout).findViewById(2131231036));
  }

  public void initialize()
  {
    int i = Math.max(-2 + expandHeap(2 + this.mActivity.getResources().getInteger(2131492891)), 0);
    BitmapLoader localBitmapLoader = FinskyApp.get().getBitmapLoader();
    localBitmapLoader.drain(FinskyApp.get().getRequestQueue().getSequenceNumber());
    localBitmapLoader.evictCache();
    super.initialize();
    this.viewPort.attachScene(this.mRootNode);
    this.mDrawingUtils = new DrawingUtils(this.assetManager, this.mActivity.getResources(), 1024 * (i * 1024));
    this.mNodeController.createRoot(new DocWrapper(this.mSeedDocument), this.mDrawingUtils);
    InputManager localInputManager = this.inputManager;
    Trigger[] arrayOfTrigger = new Trigger[1];
    arrayOfTrigger[0] = new TouchTrigger(0);
    localInputManager.addMapping("Touch", arrayOfTrigger);
    this.inputManager.addListener(this, new String[] { "Touch" });
    this.mScreenBounds[0].set(0.0F, this.settings.getHeight());
    this.mScreenBounds[1].set(this.settings.getWidth(), this.settings.getHeight());
    this.mScreenBounds[2].set(this.settings.getWidth(), 0.0F);
    this.mScreenBounds[3].set(0.0F, 0.0F);
    this.mScreenWidth = 10.0F;
    this.mScreenScaleFactor = (this.settings.getWidth() / this.mScreenWidth);
    this.mScreenHeight = (this.settings.getHeight() / this.mScreenScaleFactor);
    this.mBackground = new Geometry("background", DrawingUtils.UNIT_QUAD);
    View localView1 = this.mNodeController.mUiComponents.findViewById(2131231035);
    this.mBackground.setMaterial(this.mDrawingUtils.convertBitmapToMaterial(this.mDrawingUtils.createViewBitmap(localView1), true));
    this.mBackground.getMaterial().getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Off);
    this.mBackground.setQueueBucket(RenderQueue.Bucket.Opaque);
    this.mRootNode.attachChild(this.mBackground);
    this.mSwipe = new Geometry("swipe", DrawingUtils.UNIT_QUAD);
    View localView2 = this.mNodeController.mUiComponents.findViewById(2131231034);
    Bitmap localBitmap = this.mDrawingUtils.createViewBitmap(localView2);
    this.mSwipe.setMaterial(this.mDrawingUtils.convertBitmapToMaterial(localBitmap, true));
    this.mSwipe.getMaterial().getAdditionalRenderState().setBlendMode(RenderState.BlendMode.AlphaAdditive);
    float f = 1.125F * (localView2.getWidth() / localView2.getHeight());
    this.mSwipe.setLocalScale(f, 1.125F, 1.0F);
    this.mRootNode.attachChild(this.mSwipe);
    Geometry[] arrayOfGeometry = new Geometry[1];
    arrayOfGeometry[0] = this.mSwipe;
    this.mSwipeFader = new FadeAdapter(2.0F, 0.25F, 5.0F, arrayOfGeometry);
    updateCamera(Vector3f.ZERO);
    this.mRootNode.setCullHint(Spatial.CullHint.Never);
    this.mRootNode.setQueueBucket(RenderQueue.Bucket.Transparent);
    showPlaybackControls();
    this.audioRenderer = null;
  }

  public void onPause()
  {
    if (this.mNodeController != null)
      this.mNodeController.resetPlayback(NodeController.PlayState.STOPPED);
  }

  public void onPlayStateChanged(final NodeController.PlayState paramPlayState, final String paramString)
  {
    this.mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        switch (ExploreApplication.4.$SwitchMap$com$google$android$finsky$exploreactivity$NodeController$PlayState[paramPlayState.ordinal()])
        {
        default:
        case 1:
          while (true)
          {
            return;
            ExploreApplication.this.mPlaybackControls.setText(ExploreApplication.this.mActivity.getResources().getString(2131165432));
          }
        case 2:
          ExploreApplication.this.mPlaybackControls.setCompoundDrawablesWithIntrinsicBounds(2130837660, 0, 0, 0);
          TextView localTextView2 = ExploreApplication.this.mPlaybackControls;
          if (paramString != null);
          for (String str2 = paramString; ; str2 = "")
          {
            localTextView2.setText(str2);
            break;
          }
        case 3:
        }
        ExploreApplication.this.mPlaybackControls.setCompoundDrawablesWithIntrinsicBounds(2130837661, 0, 0, 0);
        TextView localTextView1 = ExploreApplication.this.mPlaybackControls;
        if (paramString != null);
        for (String str1 = paramString; ; str1 = "")
        {
          localTextView1.setText(str1);
          break;
        }
      }
    });
  }

  public void onStop()
  {
    this.mNodeController.disposeObjects();
    if (this.mDrawingUtils != null)
      this.mDrawingUtils.unloadCache();
  }

  public void onTouch(String paramString, TouchEvent paramTouchEvent, float paramFloat)
  {
    if (paramTouchEvent.getPointerId() != 0);
    while (true)
    {
      return;
      this.mTouch.set(paramTouchEvent.getX(), paramTouchEvent.getY());
      this.mDrawingUtils.getBasePlaneCoords(this.cam, this.mTouch, this.mTouchWorld);
      switch (4.$SwitchMap$com$jme3$input$event$TouchEvent$Type[paramTouchEvent.getType().ordinal()])
      {
      default:
        break;
      case 1:
        if ((this.mTouchWorld.x <= this.mWorldBounds[0].x + 0.2F * this.mScreenWidth) && (this.mTouchWorld.y <= this.mWorldBounds[3].y + 0.4F * this.mScreenHeight));
        for (boolean bool3 = true; ; bool3 = false)
        {
          this.mInRotation = bool3;
          this.mLastMoveX = this.mTouch.x;
          this.mLastMoveY = this.mTouch.y;
          this.mOriginalCenterNode = this.mNodeController.getCenterNode();
          this.mWasInRotation = this.mInRotation;
          break;
        }
      case 2:
        this.mInScroll = false;
        this.mInRotation = false;
        break;
      case 3:
        this.ray.getOrigin().set(this.mTouchWorld.x, this.mTouchWorld.y, 10.0F);
        this.ray.getDirection().set(0.0F, 0.0F, -1.0F);
        this.collisionResults.clear();
        this.mRootNode.collideWith(this.ray, this.collisionResults);
        CollisionResult localCollisionResult = this.collisionResults.getClosestCollision();
        Node localNode = localCollisionResult.getGeometry().getParent();
        do
        {
          if ((localNode instanceof DocumentNode))
          {
            doNodeTap((DocumentNode)localNode, localCollisionResult);
            this.mSwipeFader.fade(false);
            break;
          }
          localNode = localNode.getParent();
        }
        while (localNode != null);
        break;
      case 4:
        float f1 = (this.mTouch.x - this.mLastMoveX) / this.mScreenScaleFactor;
        float f2 = (this.mTouch.y - this.mLastMoveY) / this.mScreenScaleFactor;
        if (this.mInRotation)
        {
          this.mNodeController.rotate(1.0F * (3.141593F * f2 / 4.0F));
          this.mLastMoveX = this.mTouch.x;
          this.mLastMoveY = this.mTouch.y;
        }
        else
        {
          this.mSwipeFader.fade(false);
          boolean bool1 = this.mInScroll;
          if ((this.mTouch.x - this.mLastMoveX) * (this.mTouch.x - this.mLastMoveX) + (this.mTouch.y - this.mLastMoveY) * (this.mTouch.y - this.mLastMoveY) > this.mScrollThreshold);
          for (boolean bool2 = true; ; bool2 = false)
          {
            this.mInScroll = (bool2 | bool1);
            float f3 = this.mNodeController.getNodeCenter(this.mOriginalCenterNode).x;
            float f4 = Math.min(Math.max(f1, this.mWorldBounds[0].x - f3), this.mWorldBounds[1].x - f3);
            this.delta.set(-f4 / 2.0F, -f2 / 2.0F, 0.0F);
            updateCamera(this.delta);
            break;
          }
        }
        break;
      case 5:
        if ((paramTouchEvent.getDeltaY() < 0.0F) && (!this.mWasInRotation) && (this.mOriginalCenterNode.hasParentNode()))
          this.mNodeController.setCenterNode(this.mOriginalCenterNode.getParentNode());
        break;
      }
    }
  }

  public void setSeedDocument(Document paramDocument)
  {
    this.mSeedDocument = paramDocument;
    if (this.mDrawingUtils != null)
      this.mNodeController.createRoot(new DocWrapper(this.mSeedDocument), this.mDrawingUtils);
  }

  public void setSettings(AppSettings paramAppSettings)
  {
    paramAppSettings.setSamples(2);
    paramAppSettings.setFrameRate(30);
    paramAppSettings.setAudioRenderer(null);
    super.setSettings(paramAppSettings);
  }

  public void showPlaybackControls()
  {
    this.mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        ExploreApplication.this.mPlaybackControls.setVisibility(0);
        ExploreApplication.this.mPlaybackControls.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            ExploreApplication.this.mNodeController.togglePlayback();
          }
        });
      }
    });
  }

  public void update()
  {
    long l1 = System.currentTimeMillis();
    long l2 = 33L - (l1 - this.mLastUpdate);
    this.mLastUpdate = l1;
    if (l2 > 0L)
      SystemClock.sleep(l2);
    super.update();
    if ((this.speed == 0.0F) || (this.paused))
      return;
    float f1 = Math.min(this.timer.getTimePerFrame() * this.speed, 0.1F);
    this.stateManager.update(f1);
    DocumentNode localDocumentNode1 = this.mNodeController.getCenterNode();
    List localList = this.mNodeController.processOnscreenNodes(this.mWorldBounds, f1);
    if (this.mInScroll)
      this.mNodeController.chooseCenterNode(this.mOriginalCenterNode);
    int i = 0;
    if (i < localList.size())
    {
      DocumentNode localDocumentNode3 = (DocumentNode)localList.get(i);
      if (localDocumentNode3.isOnscreen())
        if (localDocumentNode3 == localDocumentNode1)
        {
          bool4 = true;
          localDocumentNode3.update(f1, bool4);
        }
      while (!localDocumentNode3.hasThumbnail())
        while (true)
        {
          i++;
          break;
          boolean bool4 = false;
        }
      this.mRootNode.attachChild(localDocumentNode3);
      if (localDocumentNode3 == localDocumentNode1);
      for (boolean bool3 = true; ; bool3 = false)
      {
        localDocumentNode3.attach(bool3);
        break;
      }
    }
    for (int j = 0; j < this.mPreviousNodes.size(); j++)
    {
      DocumentNode localDocumentNode2 = (DocumentNode)this.mPreviousNodes.get(j);
      if ((!localList.contains(localDocumentNode2)) && (localDocumentNode2.isOnscreen()))
      {
        localDocumentNode2.removeFromParent();
        localDocumentNode2.detach();
      }
    }
    this.mPreviousNodes.clear();
    for (int k = 0; k < localList.size(); k++)
      this.mPreviousNodes.add(localList.get(k));
    boolean bool1;
    label363: int m;
    label525: FadeAdapter localFadeAdapter;
    if (!this.mInScroll)
    {
      bool1 = true;
      if (bool1 != localDocumentNode1.isDescriptionOn())
        localDocumentNode1.setDescriptionState(bool1);
      m = 0;
      if (!this.mInScroll)
      {
        Vector2f localVector2f1 = this.mNodeController.getNodeCenter(localDocumentNode1);
        DrawingUtils.getPolyCenter(this.mWorldBounds, this.screenCenter);
        Vector2f localVector2f2 = this.screenCenter;
        localVector2f2.x += -0.17F * this.mScreenWidth;
        Vector2f localVector2f3 = this.screenCenter;
        localVector2f3.y += -0.4F * this.mScreenHeight;
        this.delta.x = (localVector2f1.x - this.screenCenter.x);
        this.delta.y = (localVector2f1.y - this.screenCenter.y);
        this.delta.z = 0.0F;
        if (this.delta.length() <= 1.0F)
          break label699;
        m = 1;
        this.delta.multLocal(f1);
        updateCamera(this.delta);
      }
      if (!this.mInRotation)
      {
        float f2 = this.mNodeController.getRotation();
        if (f2 > 3.141593F)
          f2 -= 6.283186F;
        this.mNodeController.rotate(f1 * -f2 / 1.0F);
      }
      localFadeAdapter = this.mSwipeFader;
      if ((this.mInScroll) || (m != 0) || (!this.mNodeController.isSwipable()))
        break label705;
    }
    label699: label705: for (boolean bool2 = true; ; bool2 = false)
    {
      localFadeAdapter.fade(bool2);
      this.mSwipeFader.update(f1);
      this.mRootNode.updateLogicalState(f1);
      this.mRootNode.updateGeometricState();
      this.stateManager.render(this.renderManager);
      this.renderManager.render(f1, this.context.isRenderable());
      this.stateManager.postRender();
      break;
      bool1 = false;
      break label363;
      m = 0;
      break label525;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.exploreactivity.ExploreApplication
 * JD-Core Version:    0.6.2
 */