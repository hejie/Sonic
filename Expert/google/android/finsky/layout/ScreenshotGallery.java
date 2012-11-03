package com.google.android.finsky.layout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.FrameLayout;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.ImageStripAdapter;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ThumbnailUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ScreenshotGallery extends FrameLayout
  implements LayoutSwitcher.RetryButtonListener
{
  private BitmapLoader mBitmapLoader;
  private List<Doc.Image> mCombinedImagesToLoad;
  private Document mDocument;
  private final Handler mHandler = new Handler(Looper.myLooper());
  private boolean mHasDetailsLoaded;
  private HorizontalStrip mImageStrip;
  private ImageStripAdapter mImageStripAdapter;
  private SparseArray<List<Doc.Image>> mImageUrls = new SparseArray();
  private final List<BitmapLoader.BitmapContainer> mInFlightRequests = Lists.newArrayList();
  private final Runnable mInvalidateRunnable = new Runnable()
  {
    public void run()
    {
      ScreenshotGallery.this.mImageStripAdapter.notifyDataSetInvalidated();
    }
  };
  private int mLastRequestedHeight;
  private LayoutSwitcher mLayoutSwitcher;
  private NavigationManager mNavigationManager;
  private int mNumImagesFailed;
  private int mScreenshotsSpacing;
  private List<Doc.Image> mVideoPreviews;

  public ScreenshotGallery(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public ScreenshotGallery(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mImageUrls.put(1, Collections.emptyList());
    this.mImageUrls.put(3, Collections.emptyList());
    this.mImageUrls.put(13, Collections.emptyList());
    this.mScreenshotsSpacing = paramContext.getResources().getDimensionPixelOffset(2131427372);
  }

  private void clearPendingRequests()
  {
    Iterator localIterator = this.mInFlightRequests.iterator();
    while (localIterator.hasNext())
    {
      BitmapLoader.BitmapContainer localBitmapContainer = (BitmapLoader.BitmapContainer)localIterator.next();
      if (localBitmapContainer != null)
        localBitmapContainer.cancelRequest();
    }
    this.mInFlightRequests.clear();
  }

  private boolean isSame(List<Doc.Image> paramList1, List<Doc.Image> paramList2)
  {
    boolean bool;
    if (paramList1.size() != paramList2.size())
      bool = false;
    while (true)
    {
      return bool;
      for (int i = 0; ; i++)
      {
        if (i >= paramList1.size())
          break label77;
        if (!((Doc.Image)paramList1.get(i)).getImageUrl().equals(((Doc.Image)paramList2.get(i)).getImageUrl()))
        {
          bool = false;
          break;
        }
      }
      label77: bool = true;
    }
  }

  private void loadImages()
  {
    int i = 0;
    final int j = this.mCombinedImagesToLoad.size();
    this.mNumImagesFailed = 0;
    clearPendingRequests();
    int k = 0;
    Iterator localIterator = this.mCombinedImagesToLoad.iterator();
    while (localIterator.hasNext())
    {
      Doc.Image localImage = (Doc.Image)localIterator.next();
      if (localImage == null)
      {
        this.mInFlightRequests.add(null);
        i++;
      }
      else
      {
        this.mImageStripAdapter.setImageDimensionAt(i, localImage.getDimension());
        final int m = i;
        final boolean bool = this.mImageStripAdapter.hasImageDimensionAt(m);
        String str = localImage.getImageUrl();
        if (localImage.getSupportsFifeUrlOptions())
          str = ThumbnailUtils.buildFifeUrl(str, 0, getHeight());
        BitmapLoader.BitmapContainer localBitmapContainer = this.mBitmapLoader.get(str, null, new BitmapLoader.BitmapLoadedHandler()
        {
          public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
          {
            ScreenshotGallery.this.mInFlightRequests.set(m, null);
            if (ScreenshotGallery.this.mImageStripAdapter == null);
            while (true)
            {
              return;
              Bitmap localBitmap = paramAnonymousBitmapContainer.getBitmap();
              if (localBitmap == null)
              {
                if (ScreenshotGallery.access$504(ScreenshotGallery.this) == j)
                  ScreenshotGallery.this.mLayoutSwitcher.switchToErrorMode(ScreenshotGallery.this.getContext().getString(2131165660));
              }
              else
              {
                if (!ScreenshotGallery.this.mLayoutSwitcher.isDataMode())
                  ScreenshotGallery.this.mLayoutSwitcher.switchToDataMode();
                ScreenshotGallery.this.mImageStripAdapter.setImageAt(m, new BitmapDrawable(localBitmap));
                if (!bool)
                  ScreenshotGallery.this.mImageStrip.requestLayout();
              }
            }
          }
        }
        , 0, getHeight());
        this.mInFlightRequests.add(localBitmapContainer);
        if (localBitmapContainer.getBitmap() != null)
        {
          k = 1;
          this.mImageStripAdapter.setImageAt(m, new BitmapDrawable(localBitmapContainer.getBitmap()));
          if (!bool)
            this.mImageStrip.requestLayout();
          this.mInFlightRequests.set(m, null);
        }
        i++;
      }
    }
    if (k != 0)
    {
      this.mLayoutSwitcher.switchToDataMode();
      this.mHandler.post(this.mInvalidateRunnable);
    }
  }

  private void loadImagesIfNecessary()
  {
    boolean bool = false;
    int i;
    int j;
    label37: int k;
    if (this.mHasDetailsLoaded)
      if ((this.mDocument.hasScreenshots()) || (this.mDocument.hasVideos()))
      {
        i = 1;
        if (i == 0)
          break label96;
        j = 0;
        setVisibility(j);
        k = getHeight();
        if ((k != 0) && (k != this.mLastRequestedHeight) && (this.mBitmapLoader != null))
          break label102;
      }
    label96: label102: List localList1;
    label122: List localList3;
    label142: label340: label348: label354: 
    while (true)
    {
      return;
      i = 0;
      break;
      if (this.mDocument.getBackend() == 3);
      for (i = 1; ; i = 0)
        break;
      j = 8;
      break label37;
      List localList2;
      if (this.mDocument.hasScreenshots())
      {
        localList1 = this.mDocument.getImages(1);
        if (!this.mDocument.hasVideos())
          break label340;
        localList2 = this.mDocument.getImages(3);
        this.mVideoPreviews = localList2;
        if (!this.mDocument.hasVideoThumbnails())
          break label348;
      }
      for (localList3 = this.mDocument.getImages(13); ; localList3 = Collections.emptyList())
      {
        if ((this.mDocument != null) && (this.mDocument.getDocId().equals(this.mDocument.getDocId())) && (getVisibility() == 0) && (isSame(localList1, (List)this.mImageUrls.get(1))) && (isSame(this.mVideoPreviews, (List)this.mImageUrls.get(3))) && (isSame(localList3, (List)this.mImageUrls.get(13))))
          break label354;
        this.mImageUrls.clear();
        this.mImageUrls.put(1, localList1);
        this.mImageUrls.put(3, this.mVideoPreviews);
        this.mImageUrls.put(13, localList3);
        if (!localList1.isEmpty())
          break label356;
        if (!this.mHasDetailsLoaded)
          break;
        setVisibility(8);
        break;
        localList1 = Collections.emptyList();
        break label122;
        localList2 = Collections.emptyList();
        break label142;
      }
    }
    label356: int m;
    if ((this.mVideoPreviews.size() == localList3.size()) && (!this.mVideoPreviews.isEmpty()))
    {
      m = 1;
      label390: this.mCombinedImagesToLoad = Lists.newArrayList();
      if (m == 0)
        break label582;
      this.mCombinedImagesToLoad.addAll(localList3);
    }
    while (true)
    {
      this.mCombinedImagesToLoad.addAll(localList1);
      this.mImageStrip.setLayoutMargin(this.mScreenshotsSpacing);
      if (this.mImageStripAdapter != null)
        this.mImageStripAdapter.unregisterAll();
      int i1 = (int)(0.62F * k);
      Document localDocument = this.mDocument;
      BitmapLoader localBitmapLoader = this.mBitmapLoader;
      int i2 = localList1.size();
      int i3 = this.mVideoPreviews.size();
      if (m == 0)
        bool = true;
      this.mImageStripAdapter = new ImageStripAdapter(localDocument, localBitmapLoader, i2, i3, bool, i1);
      this.mImageStrip.setAdapter(this.mImageStripAdapter);
      this.mLastRequestedHeight = k;
      this.mImageStrip.setImageChildTappedListener(new HorizontalStrip.OnImageChildViewTapListener()
      {
        public void onImageChildViewTap(int paramAnonymousInt)
        {
          ScreenshotGallery.this.mNavigationManager.goToScreenshots(ScreenshotGallery.this.mDocument, paramAnonymousInt);
        }
      });
      this.mImageStrip.setVideoChildTappedListener(new HorizontalStrip.OnVideoChildViewTapListener()
      {
        public void onVideoChildViewTap(int paramAnonymousInt)
        {
          Intent localIntent = IntentUtils.createYouTubeIntentForUrl(ScreenshotGallery.this.getContext().getPackageManager(), Uri.parse(((Doc.Image)ScreenshotGallery.this.mVideoPreviews.get(paramAnonymousInt)).getImageUrl()), FinskyApp.get().getCurrentAccountName());
          ScreenshotGallery.this.getContext().startActivity(localIntent);
        }
      });
      loadImages();
      break;
      m = 0;
      break label390;
      label582: for (int n = 0; n < this.mVideoPreviews.size(); n++)
        this.mCombinedImagesToLoad.add(null);
    }
  }

  public void bind(Document paramDocument, BitmapLoader paramBitmapLoader, NavigationManager paramNavigationManager, boolean paramBoolean)
  {
    this.mBitmapLoader = paramBitmapLoader;
    this.mNavigationManager = paramNavigationManager;
    this.mDocument = paramDocument;
    this.mHasDetailsLoaded = paramBoolean;
    loadImagesIfNecessary();
  }

  protected void onDetachedFromWindow()
  {
    if (this.mImageStripAdapter != null)
      this.mImageStripAdapter.unregisterAll();
    this.mHandler.removeCallbacks(this.mInvalidateRunnable);
    this.mImageStrip.onDestroyView();
    this.mImageStripAdapter = null;
    super.onDetachedFromWindow();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mImageStrip = ((HorizontalStrip)findViewById(2131230948));
    this.mLayoutSwitcher = new LayoutSwitcher(this, 2131230948, this);
    this.mLayoutSwitcher.switchToLoadingDelayed(500);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    loadImagesIfNecessary();
  }

  public void onRetry()
  {
    loadImages();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ScreenshotGallery
 * JD-Core Version:    0.6.2
 */