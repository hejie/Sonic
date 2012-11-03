package com.google.android.finsky.exploreactivity;

import android.graphics.Bitmap;
import android.util.FloatMath;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request.Priority;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.WishlistHelper;
import com.jme3.collision.CollisionResult;
import com.jme3.math.Ray;
import com.jme3.math.Transform;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import java.util.List;

public abstract class DocumentNode extends Node
  implements DocWrapper.DocWrapperListener
{
  private static final Mesh EDGE_LINE = new Quad(1.0F, 0.1F);
  private static final Ray direction = new Ray();
  private static final Vector3f end;
  private static final Vector3f point = new Vector3f();
  private static final Vector2f position = new Vector2f();
  private static final Vector3f start = new Vector3f();
  private final NodeRepresentation m3dNode;
  private float mAngle = 2.827433F;
  private final FadeAdapter mAnimationAdapter;
  private final Vector2f mCenter = new Vector2f((0.0F / 0.0F), (0.0F / 0.0F));
  int mChildOffset = 0;
  private final List<DocumentNode> mChildren = Lists.newArrayList(2);
  private boolean mDescriptionOn;
  private float mDistToCenter = 3.4028235E+38F;
  protected final DocWrapper mDocWrapper;
  private final DrawingUtils mDrawingUtils;
  private Geometry mEdge;
  private Geometry mEdgeLabel;
  private final float mGlowSize;
  private boolean mInWishlist;
  protected final NodeController mNodeController;
  private DocumentNode mParent;
  protected final ViewGroup mUiComponents;
  private float mZoomProgress = 0.0F;

  static
  {
    end = new Vector3f();
  }

  public DocumentNode(DocumentNode paramDocumentNode, DocWrapper paramDocWrapper, NodeController paramNodeController, DrawingUtils paramDrawingUtils, ViewGroup paramViewGroup)
  {
    super(paramDocWrapper.getDocId());
    this.mParent = paramDocumentNode;
    this.mDocWrapper = paramDocWrapper;
    this.mNodeController = paramNodeController;
    this.mDrawingUtils = paramDrawingUtils;
    this.mUiComponents = paramViewGroup;
    this.mGlowSize = (1.5F * (2.0F * paramDrawingUtils.mNodeGlowWidth + paramDrawingUtils.mNodeBitmapSize) / paramDrawingUtils.mNodeBitmapSize);
    this.mInWishlist = WishlistHelper.isInWishlist(paramDocWrapper.getDoc(), FinskyApp.get().getDfeApi().getAccount());
    View localView = paramViewGroup.findViewById(2131231032);
    float f = 1.5F * (localView.getHeight() / localView.getWidth());
    String str = this.mDocWrapper.getDocId();
    this.m3dNode = new NodeRepresentation();
    this.m3dNode.setLocalTranslation(0.0F, 0.0F, 0.5F);
    attachChild(this.m3dNode);
    Geometry[] arrayOfGeometry2;
    if (this.mParent != null)
    {
      this.mEdge = new Geometry("edge-" + str, EDGE_LINE);
      this.mEdgeLabel = new Geometry("edge-label-" + str, DrawingUtils.UNIT_QUAD);
      this.mEdgeLabel.setLocalScale(1.5F, f, 1.0F);
      arrayOfGeometry2 = new Geometry[4];
      arrayOfGeometry2[0] = this.m3dNode.mGlow;
      arrayOfGeometry2[1] = this.m3dNode.mThumbnail;
      arrayOfGeometry2[2] = this.mEdge;
      arrayOfGeometry2[3] = this.mEdgeLabel;
    }
    Geometry[] arrayOfGeometry1;
    for (this.mAnimationAdapter = new FadeAdapter(0.5F, 1.0F, 0.0F, arrayOfGeometry2); ; this.mAnimationAdapter = new FadeAdapter(0.5F, 1.0F, 0.0F, arrayOfGeometry1))
    {
      update(0.0F, false);
      return;
      this.mEdge = null;
      this.mEdgeLabel = null;
      arrayOfGeometry1 = new Geometry[2];
      arrayOfGeometry1[0] = this.m3dNode.mGlow;
      arrayOfGeometry1[1] = this.m3dNode.mThumbnail;
    }
  }

  private Bitmap createTextBitmap(int paramInt, String paramString)
  {
    TextView localTextView = (TextView)this.mUiComponents.findViewById(paramInt);
    localTextView.setText(paramString);
    return this.mDrawingUtils.createViewBitmap(localTextView);
  }

  private boolean isDocInUse(DocWrapper paramDocWrapper)
  {
    String str = paramDocWrapper.getDocId();
    DocumentNode localDocumentNode = this;
    boolean bool;
    if (localDocumentNode.mDocWrapper.getDocId().equals(str))
      bool = true;
    while (true)
    {
      return bool;
      int i = Math.min(NodeController.FIXED_CHILD_ANGLES.length, localDocumentNode.getChildNodes().size());
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label86;
        if (localDocumentNode.getChildNode(j).mDocWrapper.getDocId().equals(str))
        {
          bool = true;
          break;
        }
      }
      label86: localDocumentNode = localDocumentNode.getParentNode();
      if (localDocumentNode != null)
        break;
      for (int k = 0; ; k++)
      {
        if (k >= this.mChildren.size())
          break label151;
        if (str.equals(((DocumentNode)this.mChildren.get(k)).mDocWrapper.getDocId()))
        {
          bool = true;
          break;
        }
      }
      label151: bool = false;
    }
  }

  public void attach(boolean paramBoolean)
  {
    this.mAnimationAdapter.fade(true);
    this.mAnimationAdapter.reset();
    this.m3dNode.mNodeLabelFadeAdapter.reset();
    if ((this.mEdge != null) && (this.parent != null))
    {
      this.mEdge.setMaterial(this.mDrawingUtils.getGlowingLineMaterial());
      this.mEdgeLabel.setMaterial(this.mDrawingUtils.convertBitmapToMaterial(createTextBitmap(2131231032, this.mDocWrapper.getDoc().getRecommendationReason()), true));
      this.parent.attachChild(this.mEdge);
      this.parent.attachChild(this.mEdgeLabel);
    }
    update(0.0F, paramBoolean);
  }

  public void clearParentNode()
  {
    this.mParent = null;
    if (this.mEdge != null)
    {
      this.mEdge.removeFromParent();
      this.mEdgeLabel.removeFromParent();
      this.mEdge = null;
      this.mEdgeLabel = null;
    }
  }

  public void createChildren(int paramInt)
  {
    if ((this.mChildren.size() >= paramInt) || ((0x8 & this.mDocWrapper.getLoadedState()) == 0))
      return;
    List localList = this.mDocWrapper.getRelations();
    int i = localList.size();
    int j = Math.min(i, paramInt - this.mChildren.size());
    int k = 0;
    label62: DocWrapper localDocWrapper;
    if (k < j)
    {
      localDocWrapper = (DocWrapper)localList.get((k + i) % i);
      if (!isDocInUse(localDocWrapper))
        break label111;
      j = Math.min(i, j + 1);
    }
    while (true)
    {
      k++;
      break label62;
      break;
      label111: if ((0x1 & localDocWrapper.getLoadedState()) == 0)
      {
        if (k + this.mChildren.size() < NodeController.FIXED_CHILD_ANGLES.length);
        for (Request.Priority localPriority = Request.Priority.HIGH; ; localPriority = Request.Priority.LOW)
        {
          this.mNodeController.loadData(localDocWrapper, 3, localPriority);
          break;
        }
      }
      DocumentNode localDocumentNode = this.mNodeController.createDocumentNode(this, localDocWrapper, this.mDrawingUtils);
      this.mChildren.add(localDocumentNode);
    }
  }

  public void detach()
  {
    if (this.mEdge != null)
    {
      this.mEdge.removeFromParent();
      this.mDrawingUtils.recycleMaterial(this.mEdgeLabel);
      this.mEdgeLabel.removeFromParent();
    }
  }

  public void disposeObjects()
  {
    this.mDrawingUtils.recycleBitmap(this.mDocWrapper.disposeObjects(13));
    this.mDrawingUtils.recycleMaterial(this.m3dNode.mDescription);
    this.mDrawingUtils.recycleMaterial(this.m3dNode.mThumbnail);
    this.mDrawingUtils.recycleMaterial(this.m3dNode.mNodeLabel);
    if (this.mEdgeLabel != null)
      this.mDrawingUtils.recycleMaterial(this.mEdgeLabel);
    this.mAnimationAdapter.makeInvisible();
    this.mDescriptionOn = false;
    detach();
  }

  public void docChanged(DocWrapper paramDocWrapper)
  {
    this.mNodeController.runOnGlThread(new Runnable()
    {
      public void run()
      {
        DocumentNode.this.setNodeThumbnail();
      }
    });
  }

  public void fade(boolean paramBoolean)
  {
    this.mAnimationAdapter.fade(paramBoolean);
    this.m3dNode.update(0.0F);
  }

  public float getAngle()
  {
    return this.mAngle;
  }

  public Vector2f getCenter()
  {
    return this.mCenter;
  }

  public DocumentNode getChildNode(int paramInt)
  {
    int i = this.mChildren.size();
    if (i == 0);
    for (DocumentNode localDocumentNode = null; ; localDocumentNode = (DocumentNode)this.mChildren.get((i + (paramInt + this.mChildOffset)) % i))
      return localDocumentNode;
  }

  public int getChildNodePos(DocumentNode paramDocumentNode)
  {
    int i = 0;
    if (i < this.mChildren.size())
      if (getChildNode(i) != paramDocumentNode);
    while (true)
    {
      return i;
      i++;
      break;
      i = -1;
    }
  }

  public List<DocumentNode> getChildNodes()
  {
    return this.mChildren;
  }

  public float getDistanceToCenter()
  {
    return this.mDistToCenter;
  }

  public DocWrapper getDoc()
  {
    return this.mDocWrapper;
  }

  public DocumentNode getParentNode()
  {
    return this.mParent;
  }

  public Vector2f getRelativePosition()
  {
    position.set(DrawingUtils.UNIT_X);
    position.multLocal(4.0F);
    position.rotateAroundOrigin(this.mAngle, true);
    return position;
  }

  public boolean hasParentNode()
  {
    if (this.mParent != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasThumbnail()
  {
    return this.mDrawingUtils.hasBitmapTexture(this.m3dNode.mThumbnail);
  }

  public boolean isDescriptionOn()
  {
    return this.mDescriptionOn;
  }

  public boolean isOnscreen()
  {
    if (getParent() != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isVisible()
  {
    return this.mAnimationAdapter.isVisible();
  }

  public boolean isWishlistTap(CollisionResult paramCollisionResult)
  {
    boolean bool = false;
    if (paramCollisionResult.getGeometry() != this.m3dNode.mDescription);
    while (true)
    {
      return bool;
      this.m3dNode.mDescription.getWorldTransform().transformInverseVector(paramCollisionResult.getContactPoint(), point);
      if ((point.x >= 0.8F) && (point.y >= 0.5F))
        bool = true;
    }
  }

  public void recycleThumbnail()
  {
    this.mDrawingUtils.recycleBitmap(this.mDocWrapper.disposeObjects(1));
  }

  public void rotateChildren(int paramInt)
  {
    int i = this.mChildren.size();
    if (i <= NodeController.FIXED_CHILD_ANGLES.length);
    while (true)
    {
      return;
      this.mChildOffset = ((i + (paramInt + this.mChildOffset)) % i);
    }
  }

  public void setAngle(float paramFloat)
  {
    this.mAngle = paramFloat;
  }

  public void setDescriptionState(boolean paramBoolean)
  {
    this.mDescriptionOn = paramBoolean;
    if (paramBoolean)
      this.m3dNode.loadDescriptionMaterial();
  }

  public void setDistanceToCenter(float paramFloat)
  {
    this.mDistToCenter = paramFloat;
  }

  public boolean setNodeThumbnail()
  {
    int i = 1;
    this.m3dNode.mNodeLabel.setMaterial(this.mDrawingUtils.convertBitmapToMaterial(createTextBitmap(2131231033, this.mDocWrapper.getTitle()), i));
    Bitmap localBitmap = this.mDocWrapper.getThumbnail();
    if ((localBitmap != null) && ((0x1 & this.mDocWrapper.getLoadedState()) != 0) && (!this.mDrawingUtils.hasBitmapTexture(this.m3dNode.mThumbnail)))
    {
      this.mDrawingUtils.recycleMaterial(this.m3dNode.mThumbnail);
      this.m3dNode.mThumbnail.setMaterial(this.mDrawingUtils.convertBitmapToMaterial(this.mDrawingUtils.createNodeBitmap(localBitmap), false));
      this.mDrawingUtils.recycleBitmap(this.mDocWrapper.disposeObjects(i));
    }
    while (true)
    {
      return i;
      int j = 0;
    }
  }

  public void setWishlist(boolean paramBoolean)
  {
    if (paramBoolean != this.mInWishlist)
    {
      this.mInWishlist = paramBoolean;
      this.mDrawingUtils.recycleMaterial(this.m3dNode.mDescription);
      this.m3dNode.loadDescriptionMaterial();
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(256);
    localStringBuilder.append(this.mDocWrapper.toString());
    String str = this.mDocWrapper.getDoc().getRecommendationReason();
    if (str != null)
    {
      localStringBuilder.append(",relation=");
      localStringBuilder.append(str);
    }
    localStringBuilder.append(",center=");
    localStringBuilder.append(this.mCenter.toString());
    localStringBuilder.append(",fade=");
    localStringBuilder.append(this.mAnimationAdapter.getFadeProgress());
    localStringBuilder.append(",GPUThumb=");
    localStringBuilder.append(hasThumbnail());
    return localStringBuilder.toString();
  }

  public void update(float paramFloat, boolean paramBoolean)
  {
    this.mAnimationAdapter.update(paramFloat);
    setLocalTranslation(this.mCenter.x, this.mCenter.y, 0.0F);
    setLocalScale(1.0F + 1.22F * this.mZoomProgress);
    if ((this.mEdge != null) && (this.mEdge.getParent() != null))
    {
      start.x = this.mCenter.x;
      start.y = this.mCenter.y;
      start.z = 0.0F;
      end.x = this.mParent.getCenter().x;
      end.y = this.mParent.getCenter().y;
      end.z = 0.0F;
      this.mDrawingUtils.setupLine(this.mEdge, start, end);
      this.mEdgeLabel.setLocalTranslation((2.0F * start.x + end.x) / 3.0F - 0.75F, (2.0F * start.y + end.y) / 3.0F, 1.0F);
    }
    if (!paramBoolean)
      this.mDescriptionOn = false;
    this.m3dNode.update(paramFloat);
  }

  class NodeRepresentation extends Node
  {
    private final float DESCRIPTION_HEIGHT = 0.95445F;
    private final float DESCRIPTION_WIDTH = 1.9089F;
    private final float DESCRIPTION_X = 0.9F;
    private final float DESCRIPTION_Y = -0.255F;
    private final float DESCRIPTION_Z = 1.0F;
    private final float GLOW_Z = 2.0F;
    private final float NODE_LABEL_Z = 4.0F;
    private final float THUMBNAIL_Z = 3.0F;
    private final Geometry mDescription;
    private final Geometry mGlow;
    private final Geometry mNodeLabel;
    private final FadeAdapter mNodeLabelFadeAdapter;
    private final Geometry mThumbnail = new Geometry(DocumentNode.this.mDocWrapper.getDocId() + "-thumbnail", DrawingUtils.UNIT_QUAD);

    public NodeRepresentation()
    {
      this.mThumbnail.setLocalScale(1.5F, 1.5F, 1.0F);
      this.mThumbnail.setLocalTranslation(-0.75F, -0.75F, 3.0F);
      this.mThumbnail.setMaterial(DocumentNode.this.mDrawingUtils.getInvisibleMaterial());
      attachChild(this.mThumbnail);
      this.mGlow = new Geometry(DocumentNode.this.mDocWrapper.getDocId() + "-glow", DrawingUtils.UNIT_QUAD);
      this.mGlow.setLocalScale(DocumentNode.this.mGlowSize, DocumentNode.this.mGlowSize, 1.0F);
      this.mGlow.setLocalTranslation(-DocumentNode.this.mGlowSize / 2.0F, -DocumentNode.this.mGlowSize / 2.0F, 2.0F);
      this.mGlow.setMaterial(DocumentNode.this.mDrawingUtils.getNodeGlowMaterial());
      attachChild(this.mGlow);
      this.mDescription = new Geometry(DocumentNode.this.mDocWrapper.getDocId() + "-description", DrawingUtils.UNIT_QUAD);
      this.mDescription.setMaterial(DocumentNode.this.mDrawingUtils.getInvisibleMaterial());
      attachChild(this.mDescription);
      this.mNodeLabel = new Geometry(DocumentNode.this.mDocWrapper.getDocId() + "-node-label", DrawingUtils.UNIT_QUAD);
      View localView = DocumentNode.this.mUiComponents.findViewById(2131231033);
      float f = 1.5F * (localView.getHeight() / localView.getWidth());
      this.mNodeLabel.setLocalScale(1.5F, f, 1.0F);
      this.mNodeLabel.setLocalTranslation(-0.75F, -f / 2.0F, 4.0F);
      attachChild(this.mNodeLabel);
      Geometry[] arrayOfGeometry = new Geometry[1];
      arrayOfGeometry[0] = this.mNodeLabel;
      this.mNodeLabelFadeAdapter = new FadeAdapter(0.5F, 0.5F, 0.0F, arrayOfGeometry);
    }

    private void loadDescriptionMaterial()
    {
      if ((!DocumentNode.this.mDescriptionOn) || (DocumentNode.this.mDrawingUtils.hasBitmapTexture(this.mDescription)))
        return;
      String str1 = DocumentNode.this.mDocWrapper.getDoc().getTitle();
      String str2 = DocumentNode.this.mDocWrapper.getDoc().getCreator();
      TextView localTextView1 = (TextView)DocumentNode.this.mUiComponents.findViewById(2131230957);
      localTextView1.setText(str1);
      int k;
      TextView localTextView2;
      label150: View localView;
      float f2;
      int i;
      int j;
      label329: ImageView localImageView;
      if (DocumentNode.this.mNodeController.isWishlistEnabled())
      {
        if (DocumentNode.this.mInWishlist)
        {
          k = 2130837647;
          localTextView1.setCompoundDrawablesWithIntrinsicBounds(0, 0, k, 0);
        }
      }
      else
      {
        localTextView2 = (TextView)DocumentNode.this.mUiComponents.findViewById(2131230958);
        if (str1.equals(str2))
          break label385;
        localTextView2.setText(str2);
        localTextView2.setVisibility(0);
        CharSequence localCharSequence = DocumentNode.this.mDocWrapper.getDoc().getDescription();
        ((TextView)DocumentNode.this.mUiComponents.findViewById(2131231023)).setText(localCharSequence);
        localView = DocumentNode.this.mUiComponents.findViewById(2131231024);
        if (!DocumentNode.this.mDocWrapper.getDoc().hasRating())
          break label494;
        localView.setVisibility(0);
        TextView localTextView4 = (TextView)DocumentNode.this.mUiComponents.findViewById(2131231030);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Long.valueOf(DocumentNode.this.mDocWrapper.getDoc().getRatingCount());
        localTextView4.setText(String.format("(%,d)", arrayOfObject));
        float f1 = DocumentNode.this.mDocWrapper.getDoc().getStarRating();
        f2 = f1 - FloatMath.floor(f1);
        i = (int)FloatMath.floor(f1);
        ViewGroup localViewGroup = (ViewGroup)DocumentNode.this.mUiComponents.findViewById(2131231024);
        j = 1;
        if (j > 5)
          break label501;
        localImageView = (ImageView)localViewGroup.getChildAt(j - 1);
        if (j > i)
          break label395;
        localImageView.setImageBitmap(DocumentNode.this.mNodeController.mStarOn);
      }
      while (true)
      {
        j++;
        break label329;
        k = 2130837646;
        break;
        label385: localTextView2.setVisibility(8);
        break label150;
        label395: if (j > i + 1)
          localImageView.setImageBitmap(DocumentNode.this.mNodeController.mStarOff);
        else if (f2 < 0.25F)
          localImageView.setImageBitmap(DocumentNode.this.mNodeController.mStarOff);
        else if (f2 > 0.75F)
          localImageView.setImageBitmap(DocumentNode.this.mNodeController.mStarOn);
        else
          localImageView.setImageBitmap(DocumentNode.this.mNodeController.mStarHalf);
      }
      label494: localView.setVisibility(8);
      label501: String str3 = DocumentNode.this.mDocWrapper.getDoc().getFormattedPrice(1);
      TextView localTextView3 = (TextView)DocumentNode.this.mUiComponents.findViewById(2131231031);
      if (str3 != null)
      {
        localTextView3.setText(str3);
        localTextView3.setVisibility(0);
      }
      while (true)
      {
        DocumentNode.this.mUiComponents.measure(0, 0);
        DocumentNode.this.mUiComponents.layout(0, 0, 0, 0);
        DocumentNode.this.mDrawingUtils.recycleMaterial(this.mDescription);
        this.mDescription.setMaterial(DocumentNode.this.mDrawingUtils.convertBitmapToMaterial(DocumentNode.this.mDrawingUtils.createViewBitmap(DocumentNode.this.mUiComponents.findViewById(2131231022)), true));
        break;
        localTextView3.setVisibility(8);
      }
    }

    public void update(float paramFloat)
    {
      DocumentNode localDocumentNode = DocumentNode.this;
      float f1 = paramFloat / 0.35F;
      float f2;
      FadeAdapter localFadeAdapter;
      if (DocumentNode.this.mDescriptionOn)
      {
        f2 = 1.0F;
        DocumentNode.access$1016(localDocumentNode, f2 * f1);
        DocumentNode.access$1002(DocumentNode.this, Math.max(0.0F, DocumentNode.this.mZoomProgress));
        DocumentNode.access$1002(DocumentNode.this, Math.min(1.0F, DocumentNode.this.mZoomProgress));
        localFadeAdapter = this.mNodeLabelFadeAdapter;
        if ((DocumentNode.this.mDescriptionOn) || (!DocumentNode.this.mAnimationAdapter.isFadingIn()))
          break label183;
      }
      label183: for (boolean bool = true; ; bool = false)
      {
        localFadeAdapter.fade(bool);
        this.mNodeLabelFadeAdapter.update(paramFloat);
        this.mDescription.setLocalScale(1.9089F * DocumentNode.this.mZoomProgress, 0.95445F * DocumentNode.this.mZoomProgress, 1.0F);
        this.mDescription.setLocalTranslation(0.9F * DocumentNode.this.mZoomProgress, -0.255F * DocumentNode.this.mZoomProgress, 1.0F);
        return;
        f2 = -1.0F;
        break;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.exploreactivity.DocumentNode
 * JD-Core Version:    0.6.2
 */