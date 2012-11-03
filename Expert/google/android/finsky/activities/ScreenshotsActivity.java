package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.ScreenshotView;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.utils.BitmapLoader;
import java.util.List;

public class ScreenshotsActivity extends Activity
{
  private Document mDocument;
  private ViewPager mPager;

  public static void show(Context paramContext, Document paramDocument, int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent(paramContext, ScreenshotsActivity.class);
    localIntent.putExtra("document", paramDocument);
    localIntent.putExtra("index", paramInt1);
    localIntent.putExtra("imageType", paramInt2);
    paramContext.startActivity(localIntent);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968834);
    this.mDocument = ((Document)getIntent().getParcelableExtra("document"));
    this.mPager = ((ViewPager)findViewById(2131231251));
    int i = getIntent().getIntExtra("imageType", 1);
    List localList = this.mDocument.getImages(i);
    this.mPager.setAdapter(new ImagePagerAdapter(localList, this, FinskyApp.get().getBitmapLoader()));
    if (paramBundle == null)
      this.mPager.setCurrentItem(getIntent().getIntExtra("index", 0));
  }

  private static final class ImagePagerAdapter extends PagerAdapter
  {
    private final BitmapLoader mBitmapLoader;
    private final List<Doc.Image> mImages;
    private final LayoutInflater mInflater;

    public ImagePagerAdapter(List<Doc.Image> paramList, Context paramContext, BitmapLoader paramBitmapLoader)
    {
      this.mImages = paramList;
      this.mInflater = LayoutInflater.from(paramContext);
      this.mBitmapLoader = paramBitmapLoader;
    }

    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      paramViewGroup.removeView((ScreenshotView)paramObject);
    }

    public int getCount()
    {
      return this.mImages.size();
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      ScreenshotView localScreenshotView = (ScreenshotView)this.mInflater.inflate(2130968833, paramViewGroup, false);
      localScreenshotView.setImage((Doc.Image)this.mImages.get(paramInt), this.mBitmapLoader);
      paramViewGroup.addView(localScreenshotView);
      return localScreenshotView;
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      if (paramView == paramObject);
      for (boolean bool = true; ; bool = false)
        return bool;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ScreenshotsActivity
 * JD-Core Version:    0.6.2
 */