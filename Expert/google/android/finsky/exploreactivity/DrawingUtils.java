package com.google.android.finsky.exploreactivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.FloatMath;
import android.util.TypedValue;
import android.view.View;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ThumbnailUtils;
import com.jme3.asset.AndroidImageInfo;
import com.jme3.asset.AssetManager;
import com.jme3.material.MatParam;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Plane;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Image;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import java.util.List;

public class DrawingUtils
{
  private static final Plane BASE_PLANE = new Plane(Vector3f.UNIT_Z, 0.0F);
  public static final Mesh UNIT_QUAD = new Quad(1.0F, 1.0F);
  public static final Vector2f UNIT_X = new Vector2f(1.0F, 0.0F);
  private static final Object sDecodeLock = new Object();
  private final Vector3f click3d = new Vector3f();
  private final Vector3f dir = new Vector3f();
  private final Vector3f intersectionPoint = new Vector3f();
  private final Matrix4f inverseMatrix = new Matrix4f();
  private List<Bitmap> mBitmapPool = Lists.newArrayList();
  private int mBitmapPoolBytes = 0;
  private final float mEdgeLineGlowWidth;
  private final float mEdgeLineWidth;
  private final int mGlowBackgroundColor;
  private final int mGlowForegroundColor;
  private final Material mGlowingLineMaterial;
  private final Material mInvisibleTexture;
  private final int mLineForegroundColor;
  private final int mMaxBitmapPoolBytes;
  public final float mNodeBitmapSize;
  private final Material mNodeGlowMaterial;
  private final float mNodeGlowOutlineWidth;
  public final float mNodeGlowWidth;
  private final Material mUnshadedMaterial;
  private final Quaternion quat = new Quaternion();
  private final Ray ray = new Ray();

  public DrawingUtils(AssetManager paramAssetManager, Resources paramResources, int paramInt)
  {
    float f = TypedValue.applyDimension(1, 1.0F, paramResources.getDisplayMetrics());
    if (f < 1.5F)
      f = 1.0F;
    this.mNodeBitmapSize = (f * paramResources.getInteger(2131492890));
    this.mNodeGlowWidth = paramResources.getInteger(2131492892);
    this.mGlowBackgroundColor = paramResources.getColor(2131361858);
    this.mGlowForegroundColor = paramResources.getColor(2131361859);
    this.mLineForegroundColor = paramResources.getColor(2131361860);
    this.mNodeGlowOutlineWidth = paramResources.getInteger(2131492895);
    this.mEdgeLineGlowWidth = paramResources.getInteger(2131492893);
    this.mEdgeLineWidth = paramResources.getInteger(2131492894);
    this.mUnshadedMaterial = new Material(paramAssetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    this.mGlowingLineMaterial = convertBitmapToMaterial(createLineBitmap(), false);
    this.mGlowingLineMaterial.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.AlphaAdditive);
    this.mInvisibleTexture = getUnshadedMaterial();
    this.mInvisibleTexture.setColor("Color", ColorRGBA.BlackNoAlpha);
    this.mInvisibleTexture.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
    this.mNodeGlowMaterial = convertBitmapToMaterial(createGlowBitmap(), false);
    this.mNodeGlowMaterial.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.AlphaAdditive);
    this.mMaxBitmapPoolBytes = paramInt;
  }

  private void convertBlueToAlpha(Bitmap paramBitmap)
  {
    for (int i = 0; i < paramBitmap.getWidth(); i++)
      for (int j = 0; j < paramBitmap.getHeight(); j++)
        paramBitmap.setPixel(i, j, 0xFFFFFF | (0xFF & paramBitmap.getPixel(i, j)) << 24);
  }

  private Bitmap createGlowBitmap()
  {
    float f1 = this.mNodeBitmapSize / 2.0F;
    float f2 = f1 + this.mNodeGlowWidth;
    int i = (int)(f2 * 2.0F);
    Bitmap localBitmap = getBitmap(i, i);
    localBitmap.eraseColor(this.mGlowBackgroundColor);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(true);
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setStrokeWidth(this.mNodeGlowOutlineWidth);
    localPaint.setMaskFilter(new BlurMaskFilter(f2 - f1, BlurMaskFilter.Blur.OUTER));
    localPaint.setColor(this.mGlowForegroundColor);
    localCanvas.drawCircle(f2, f2, f1, localPaint);
    localPaint.setMaskFilter(null);
    localCanvas.drawCircle(f2, f2, f1, localPaint);
    convertBlueToAlpha(localBitmap);
    return localBitmap;
  }

  private float getAngle(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    this.dir.x = (paramVector3f2.x - paramVector3f1.x);
    this.dir.y = (paramVector3f2.y - paramVector3f1.y);
    this.dir.z = (paramVector3f2.z - paramVector3f1.z);
    this.dir.normalizeLocal();
    float f = this.dir.angleBetween(Vector3f.UNIT_X);
    if (this.dir.y < 0.0F)
      f = -f;
    return f;
  }

  private Bitmap getBitmap(int paramInt1, int paramInt2)
  {
    Bitmap localBitmap1 = null;
    List localList = this.mBitmapPool;
    for (int i = 0; ; i++)
      while (true)
      {
        try
        {
          if (i < this.mBitmapPool.size())
          {
            localBitmap1 = (Bitmap)this.mBitmapPool.get(i);
            if (localBitmap1.isRecycled())
            {
              removeBitmapFromPool(i);
              poolLog(localBitmap1, "recycled");
              i--;
              break;
            }
            if ((localBitmap1.getWidth() == paramInt1) && (localBitmap1.getHeight() == paramInt2))
            {
              removeBitmapFromPool(i);
              poolLog(localBitmap1, "hit");
            }
          }
          else
          {
            if (localBitmap1 == null)
              break label133;
            localBitmap1.eraseColor(0);
            localObject2 = localBitmap1;
            return localObject2;
          }
          localBitmap1 = null;
        }
        finally
        {
        }
        label133: Bitmap localBitmap2 = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
        poolLog(localBitmap2, "miss");
        Object localObject2 = localBitmap2;
      }
  }

  public static void getPolyCenter(Vector2f[] paramArrayOfVector2f, Vector2f paramVector2f)
  {
    float f1 = (1.0F / 1.0F);
    float f2 = (1.0F / -1.0F);
    float f3 = (1.0F / 1.0F);
    float f4 = (1.0F / -1.0F);
    for (int i = 0; i < paramArrayOfVector2f.length; i++)
    {
      f1 = Math.min(f1, paramArrayOfVector2f[i].x);
      f2 = Math.max(f2, paramArrayOfVector2f[i].x);
      f3 = Math.min(f3, paramArrayOfVector2f[i].y);
      f4 = Math.max(f4, paramArrayOfVector2f[i].y);
    }
    float f5 = f2 - f1;
    float f6 = f4 - f3;
    paramVector2f.x = (f1 + f5 / 2.0F);
    paramVector2f.y = (f3 + f6 / 2.0F);
  }

  public static void getPolySize(Vector2f[] paramArrayOfVector2f, Vector2f paramVector2f)
  {
    float f1 = 3.4028235E+38F;
    float f2 = 1.4E-45F;
    float f3 = 3.4028235E+38F;
    float f4 = 1.4E-45F;
    for (int i = 0; i < paramArrayOfVector2f.length; i++)
    {
      f1 = Math.min(f1, paramArrayOfVector2f[i].x);
      f2 = Math.max(f2, paramArrayOfVector2f[i].x);
      f3 = Math.min(f3, paramArrayOfVector2f[i].y);
      f4 = Math.max(f4, paramArrayOfVector2f[i].y);
    }
    paramVector2f.x = (f2 - f1);
    paramVector2f.y = (f4 - f3);
  }

  private Material getUnshadedMaterial()
  {
    return this.mUnshadedMaterial.clone();
  }

  private void getWorldCoordinates(Camera paramCamera, Vector2f paramVector2f, float paramFloat, Vector3f paramVector3f)
  {
    this.inverseMatrix.set(paramCamera.getViewProjectionMatrix());
    this.inverseMatrix.invertLocal();
    paramVector3f.set(2.0F * ((paramVector2f.x / paramCamera.getWidth() - paramCamera.getViewPortLeft()) / (paramCamera.getViewPortRight() - paramCamera.getViewPortLeft())) - 1.0F, 2.0F * ((paramVector2f.y / paramCamera.getHeight() - paramCamera.getViewPortBottom()) / (paramCamera.getViewPortTop() - paramCamera.getViewPortBottom())) - 1.0F, paramFloat * 2.0F - 1.0F);
    paramVector3f.multLocal(1.0F / this.inverseMatrix.multProj(paramVector3f, paramVector3f));
  }

  // ERROR //
  private void poolLog(Bitmap paramBitmap, String paramString)
  {
    // Byte code:
    //   0: getstatic 418	com/google/android/finsky/utils/FinskyLog:DEBUG	Z
    //   3: ifne +4 -> 7
    //   6: return
    //   7: aload_0
    //   8: getfield 82	com/google/android/finsky/exploreactivity/DrawingUtils:mBitmapPool	Ljava/util/List;
    //   11: astore_3
    //   12: aload_3
    //   13: monitorenter
    //   14: new 420	java/util/HashMap
    //   17: dup
    //   18: invokespecial 421	java/util/HashMap:<init>	()V
    //   21: astore 4
    //   23: aload_0
    //   24: getfield 82	com/google/android/finsky/exploreactivity/DrawingUtils:mBitmapPool	Ljava/util/List;
    //   27: invokeinterface 425 1 0
    //   32: astore 6
    //   34: aload 6
    //   36: invokeinterface 430 1 0
    //   41: ifeq +124 -> 165
    //   44: aload 6
    //   46: invokeinterface 433 1 0
    //   51: checkcast 225	android/graphics/Bitmap
    //   54: astore 15
    //   56: iconst_2
    //   57: anewarray 4	java/lang/Object
    //   60: astore 16
    //   62: aload 16
    //   64: iconst_0
    //   65: aload 15
    //   67: invokevirtual 229	android/graphics/Bitmap:getWidth	()I
    //   70: invokestatic 439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   73: aastore
    //   74: aload 16
    //   76: iconst_1
    //   77: aload 15
    //   79: invokevirtual 232	android/graphics/Bitmap:getHeight	()I
    //   82: invokestatic 439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   85: aastore
    //   86: ldc_w 441
    //   89: aload 16
    //   91: invokestatic 447	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   94: astore 17
    //   96: aload 4
    //   98: aload 17
    //   100: invokeinterface 453 2 0
    //   105: ifeq +43 -> 148
    //   108: aload 4
    //   110: aload 17
    //   112: iconst_1
    //   113: aload 4
    //   115: aload 17
    //   117: invokeinterface 456 2 0
    //   122: checkcast 435	java/lang/Integer
    //   125: invokevirtual 459	java/lang/Integer:intValue	()I
    //   128: iadd
    //   129: invokestatic 439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   132: invokeinterface 463 3 0
    //   137: pop
    //   138: goto -104 -> 34
    //   141: astore 5
    //   143: aload_3
    //   144: monitorexit
    //   145: aload 5
    //   147: athrow
    //   148: aload 4
    //   150: aload 17
    //   152: iconst_1
    //   153: invokestatic 439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   156: invokeinterface 463 3 0
    //   161: pop
    //   162: goto -128 -> 34
    //   165: new 465	java/lang/StringBuilder
    //   168: dup
    //   169: invokespecial 466	java/lang/StringBuilder:<init>	()V
    //   172: astore 7
    //   174: aload 4
    //   176: invokeinterface 470 1 0
    //   181: invokeinterface 473 1 0
    //   186: astore 8
    //   188: aload 8
    //   190: invokeinterface 430 1 0
    //   195: ifeq +71 -> 266
    //   198: aload 8
    //   200: invokeinterface 433 1 0
    //   205: checkcast 475	java/util/Map$Entry
    //   208: astore 10
    //   210: aload 7
    //   212: aload 10
    //   214: invokeinterface 478 1 0
    //   219: checkcast 443	java/lang/String
    //   222: invokevirtual 482	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: aload 7
    //   228: ldc_w 484
    //   231: invokevirtual 482	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload 7
    //   237: aload 10
    //   239: invokeinterface 487 1 0
    //   244: checkcast 435	java/lang/Integer
    //   247: invokevirtual 459	java/lang/Integer:intValue	()I
    //   250: invokevirtual 490	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   253: pop
    //   254: aload 7
    //   256: ldc_w 492
    //   259: invokevirtual 482	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: pop
    //   263: goto -75 -> 188
    //   266: bipush 6
    //   268: anewarray 4	java/lang/Object
    //   271: astore 9
    //   273: aload 9
    //   275: iconst_0
    //   276: aload_2
    //   277: aastore
    //   278: aload 9
    //   280: iconst_1
    //   281: aload_1
    //   282: invokevirtual 229	android/graphics/Bitmap:getWidth	()I
    //   285: invokestatic 439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   288: aastore
    //   289: aload 9
    //   291: iconst_2
    //   292: aload_1
    //   293: invokevirtual 232	android/graphics/Bitmap:getHeight	()I
    //   296: invokestatic 439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   299: aastore
    //   300: aload 9
    //   302: iconst_3
    //   303: aload_0
    //   304: getfield 82	com/google/android/finsky/exploreactivity/DrawingUtils:mBitmapPool	Ljava/util/List;
    //   307: invokeinterface 319 1 0
    //   312: invokestatic 439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   315: aastore
    //   316: aload 9
    //   318: iconst_4
    //   319: aload_0
    //   320: getfield 84	com/google/android/finsky/exploreactivity/DrawingUtils:mBitmapPoolBytes	I
    //   323: invokestatic 439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   326: aastore
    //   327: aload 9
    //   329: iconst_5
    //   330: aload 7
    //   332: invokevirtual 496	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   335: aastore
    //   336: ldc_w 498
    //   339: aload 9
    //   341: invokestatic 502	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   344: aload_3
    //   345: monitorexit
    //   346: goto -340 -> 6
    //
    // Exception table:
    //   from	to	target	type
    //   14	145	141	finally
    //   148	346	141	finally
  }

  private Bitmap removeBitmapFromPool(int paramInt)
  {
    Bitmap localBitmap = (Bitmap)this.mBitmapPool.remove(paramInt);
    this.mBitmapPoolBytes -= localBitmap.getHeight() * localBitmap.getRowBytes();
    return localBitmap;
  }

  Material convertBitmapToMaterial(Bitmap paramBitmap, boolean paramBoolean)
  {
    Material localMaterial = null;
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Image.Format localFormat;
    switch (2.$SwitchMap$android$graphics$Bitmap$Config[paramBitmap.getConfig().ordinal()])
    {
    default:
      return localMaterial;
    case 1:
      localFormat = Image.Format.Alpha8;
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      Image localImage = new Image(localFormat, i, j, null);
      localImage.setEfficentData(new TransformingImageInfo(paramBitmap, paramBoolean, null));
      localMaterial = getUnshadedMaterial();
      localMaterial.setTexture("ColorMap", new Texture2D(localImage));
      localMaterial.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
      localMaterial.getAdditionalRenderState().setAlphaTest(true);
      localMaterial.getAdditionalRenderState().setAlphaFallOff(0.1F);
      break;
      localFormat = Image.Format.ARGB4444;
      continue;
      localFormat = Image.Format.RGBA8;
      continue;
      localFormat = Image.Format.RGB565;
    }
  }

  Bitmap createLineBitmap()
  {
    int i = (int)(2.0F * this.mEdgeLineGlowWidth + this.mEdgeLineWidth);
    Bitmap localBitmap = getBitmap(i, i);
    localBitmap.eraseColor(this.mGlowBackgroundColor);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(true);
    localPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    localPaint.setStrokeWidth(this.mEdgeLineWidth);
    localPaint.setMaskFilter(new BlurMaskFilter(this.mEdgeLineGlowWidth, BlurMaskFilter.Blur.OUTER));
    localPaint.setColor(this.mGlowForegroundColor);
    localCanvas.drawRect(0.0F, this.mEdgeLineGlowWidth - 0.5F, i, 0.5F + this.mEdgeLineGlowWidth, localPaint);
    localPaint.setMaskFilter(null);
    localPaint.setColor(this.mLineForegroundColor);
    localCanvas.drawRect(0.0F, this.mEdgeLineGlowWidth - 0.5F, i, 0.5F + this.mEdgeLineGlowWidth, localPaint);
    convertBlueToAlpha(localBitmap);
    return localBitmap;
  }

  public Bitmap createNodeBitmap(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    int k = Math.min(i, j);
    Matrix localMatrix = new Matrix();
    localMatrix.preScale(1.0F, -1.0F);
    float f1 = this.mNodeBitmapSize / k;
    localMatrix.postScale(f1, f1);
    Bitmap localBitmap = transformBitmap(paramBitmap, (i - k) / 2, (j - k) / 2, k, k, localMatrix);
    Paint localPaint = new Paint();
    Canvas localCanvas = new Canvas(localBitmap);
    int m = Math.min(localBitmap.getWidth(), localBitmap.getHeight());
    localPaint.setStyle(Paint.Style.STROKE);
    float f2 = m * FloatMath.sqrt(2.0F) / 2.0F;
    float f3 = m / 2.0F;
    localPaint.setStrokeWidth(f2 - f3);
    localPaint.setAntiAlias(true);
    localPaint.setColor(0);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
    localCanvas.drawCircle(m / 2.0F, m / 2.0F, (f2 + f3) / 2.0F, localPaint);
    return localBitmap;
  }

  public Bitmap createViewBitmap(View paramView)
  {
    Bitmap localBitmap = getBitmap(paramView.getWidth(), paramView.getHeight());
    Canvas localCanvas = new Canvas();
    localCanvas.setBitmap(localBitmap);
    paramView.draw(localCanvas);
    return localBitmap;
  }

  public void getBasePlaneCoords(Camera paramCamera, Vector2f paramVector2f1, Vector2f paramVector2f2)
  {
    getWorldCoordinates(paramCamera, paramVector2f1, 0.0F, this.click3d);
    getWorldCoordinates(paramCamera, paramVector2f1, 1.0F, this.dir);
    this.dir.x -= this.click3d.x;
    this.dir.y -= this.click3d.y;
    this.dir.z -= this.click3d.z;
    this.ray.setOrigin(this.click3d);
    this.ray.setDirection(this.dir);
    this.ray.intersectsWherePlane(BASE_PLANE, this.intersectionPoint);
    paramVector2f2.x = this.intersectionPoint.x;
    paramVector2f2.y = this.intersectionPoint.y;
  }

  public Material getGlowingLineMaterial()
  {
    return this.mGlowingLineMaterial.clone();
  }

  public Material getInvisibleMaterial()
  {
    return this.mInvisibleTexture;
  }

  public Material getNodeGlowMaterial()
  {
    return this.mNodeGlowMaterial.clone();
  }

  public Request<Bitmap> getThumbnailRequest(final DocWrapper paramDocWrapper, Request.Priority paramPriority, Response.ErrorListener paramErrorListener)
  {
    return new PoolingImageRequest(ThumbnailUtils.getCroppedIconUrlFromDocument(paramDocWrapper.getDoc(), (int)this.mNodeBitmapSize), paramPriority, new Response.Listener()
    {
      public void onResponse(Bitmap paramAnonymousBitmap)
      {
        paramDocWrapper.setThumbnail(paramAnonymousBitmap);
      }
    }
    , paramErrorListener);
  }

  public boolean hasBitmapTexture(Geometry paramGeometry)
  {
    if ((paramGeometry.getMaterial() != null) && (paramGeometry.getMaterial().getParam("ColorMap") != null));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  // ERROR //
  public void recycleBitmap(Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +94 -> 95
    //   4: aload_1
    //   5: invokevirtual 327	android/graphics/Bitmap:isRecycled	()Z
    //   8: ifne +87 -> 95
    //   11: aload_1
    //   12: invokevirtual 687	android/graphics/Bitmap:isMutable	()Z
    //   15: ifeq +80 -> 95
    //   18: aload_0
    //   19: getfield 82	com/google/android/finsky/exploreactivity/DrawingUtils:mBitmapPool	Ljava/util/List;
    //   22: astore_2
    //   23: aload_2
    //   24: monitorenter
    //   25: aload_0
    //   26: getfield 82	com/google/android/finsky/exploreactivity/DrawingUtils:mBitmapPool	Ljava/util/List;
    //   29: aload_1
    //   30: invokeinterface 690 2 0
    //   35: pop
    //   36: aload_0
    //   37: aload_0
    //   38: getfield 84	com/google/android/finsky/exploreactivity/DrawingUtils:mBitmapPoolBytes	I
    //   41: aload_1
    //   42: invokevirtual 232	android/graphics/Bitmap:getHeight	()I
    //   45: aload_1
    //   46: invokevirtual 508	android/graphics/Bitmap:getRowBytes	()I
    //   49: imul
    //   50: iadd
    //   51: putfield 84	com/google/android/finsky/exploreactivity/DrawingUtils:mBitmapPoolBytes	I
    //   54: aload_0
    //   55: aload_1
    //   56: ldc_w 692
    //   59: invokespecial 337	com/google/android/finsky/exploreactivity/DrawingUtils:poolLog	(Landroid/graphics/Bitmap;Ljava/lang/String;)V
    //   62: aload_0
    //   63: getfield 84	com/google/android/finsky/exploreactivity/DrawingUtils:mBitmapPoolBytes	I
    //   66: aload_0
    //   67: getfield 213	com/google/android/finsky/exploreactivity/DrawingUtils:mMaxBitmapPoolBytes	I
    //   70: if_icmple +23 -> 93
    //   73: aload_0
    //   74: aload_0
    //   75: iconst_0
    //   76: invokespecial 331	com/google/android/finsky/exploreactivity/DrawingUtils:removeBitmapFromPool	(I)Landroid/graphics/Bitmap;
    //   79: ldc_w 694
    //   82: invokespecial 337	com/google/android/finsky/exploreactivity/DrawingUtils:poolLog	(Landroid/graphics/Bitmap;Ljava/lang/String;)V
    //   85: goto -23 -> 62
    //   88: astore_3
    //   89: aload_2
    //   90: monitorexit
    //   91: aload_3
    //   92: athrow
    //   93: aload_2
    //   94: monitorexit
    //   95: return
    //
    // Exception table:
    //   from	to	target	type
    //   25	91	88	finally
    //   93	95	88	finally
  }

  public void recycleMaterial(Geometry paramGeometry)
  {
    if (paramGeometry == null);
    while (true)
    {
      return;
      Material localMaterial = paramGeometry.getMaterial();
      paramGeometry.setMaterial(this.mInvisibleTexture);
      if (localMaterial != null)
      {
        MatParam localMatParam = localMaterial.getParam("ColorMap");
        if (localMatParam != null)
        {
          Object localObject1 = localMatParam.getValue();
          if ((localObject1 instanceof Texture))
          {
            Image localImage = ((Texture)localObject1).getImage();
            if (localImage != null)
            {
              Object localObject2 = localImage.getEfficentData();
              if ((localObject2 instanceof AndroidImageInfo))
                recycleBitmap(((AndroidImageInfo)localObject2).getBitmap());
            }
          }
        }
      }
    }
  }

  public void setupLine(Spatial paramSpatial, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    this.quat.fromAngles(0.0F, 0.0F, getAngle(paramVector3f1, paramVector3f2));
    paramSpatial.setLocalRotation(this.quat);
    paramSpatial.setLocalTranslation(paramVector3f1.x, paramVector3f1.y, paramVector3f1.z);
    paramSpatial.setLocalScale(paramVector3f1.distance(paramVector3f2), 1.0F, 1.0F);
  }

  Bitmap transformBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Matrix paramMatrix)
  {
    Canvas localCanvas = new Canvas();
    Rect localRect = new Rect(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4);
    RectF localRectF1 = new RectF(0.0F, 0.0F, paramInt3, paramInt4);
    RectF localRectF2 = new RectF();
    paramMatrix.mapRect(localRectF2, localRectF1);
    Bitmap localBitmap = getBitmap(Math.round(localRectF2.width()), Math.round(localRectF2.height()));
    localCanvas.translate(-localRectF2.left, -localRectF2.top);
    localCanvas.concat(paramMatrix);
    Paint localPaint = new Paint();
    localBitmap.setDensity(paramBitmap.getDensity());
    localCanvas.setBitmap(localBitmap);
    localCanvas.drawBitmap(paramBitmap, localRect, localRectF1, localPaint);
    return localBitmap;
  }

  public void unloadCache()
  {
    synchronized (this.mBitmapPool)
    {
      this.mBitmapPool.clear();
      this.mBitmapPoolBytes = 0;
      return;
    }
  }

  private class PoolingImageRequest extends ImageRequest
  {
    private final Request.Priority mPriority;

    public PoolingImageRequest(Request.Priority paramListener, Response.Listener<Bitmap> paramErrorListener, Response.ErrorListener arg4)
    {
      super(localListener, 0, 0, Bitmap.Config.RGB_565, localErrorListener);
      this.mPriority = paramErrorListener;
    }

    public Request.Priority getPriority()
    {
      return this.mPriority;
    }

    protected Response<Bitmap> parseNetworkResponse(NetworkResponse paramNetworkResponse)
    {
      try
      {
        synchronized (DrawingUtils.sDecodeLock)
        {
          byte[] arrayOfByte = paramNetworkResponse.data;
          BitmapFactory.Options localOptions = new BitmapFactory.Options();
          localOptions.inJustDecodeBounds = true;
          localOptions.inSampleSize = 1;
          localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
          BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length, localOptions);
          localOptions.inJustDecodeBounds = false;
          if (Build.VERSION.SDK_INT >= 11)
            localOptions.inBitmap = DrawingUtils.this.getBitmap(localOptions.outWidth, localOptions.outHeight);
          Bitmap localBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length, localOptions);
          if (localBitmap == null)
          {
            Response localResponse2 = Response.error(new ParseError());
            localObject2 = localResponse2;
            return localObject2;
          }
          Response localResponse1 = Response.success(localBitmap, HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));
          localObject2 = localResponse1;
        }
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        while (true)
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = Integer.valueOf(paramNetworkResponse.data.length);
          arrayOfObject[1] = getUrl();
          FinskyLog.e("OOM for %d byte image, url=%s", arrayOfObject);
          Object localObject2 = Response.error(new ParseError(localOutOfMemoryError));
        }
      }
    }
  }

  private class TransformingImageInfo extends AndroidImageInfo
  {
    Bitmap mBitmap;
    final boolean mFlipVertical;

    private TransformingImageInfo(Bitmap paramBoolean, boolean arg3)
    {
      super();
      this.mBitmap = paramBoolean;
      boolean bool;
      this.mFlipVertical = bool;
    }

    protected void loadBitmap()
    {
      if (this.mFlipVertical)
      {
        Matrix localMatrix = new Matrix();
        localMatrix.preScale(1.0F, -1.0F);
        this.bitmap = DrawingUtils.this.transformBitmap(this.mBitmap, 0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight(), localMatrix);
        DrawingUtils.this.recycleBitmap(this.mBitmap);
      }
      while (true)
      {
        this.mBitmap = null;
        return;
        this.bitmap = this.mBitmap;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.exploreactivity.DrawingUtils
 * JD-Core Version:    0.6.2
 */