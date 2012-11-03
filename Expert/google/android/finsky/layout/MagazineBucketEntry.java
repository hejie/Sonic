package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.DocAnnotations.Badge;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;

public class MagazineBucketEntry extends RelativeLayout
  implements DocumentCell
{
  private View mAccessibilityOverlay;
  private DecoratedTextView mBadge;
  private Document mBoundDocument;
  private final int mCoverMaxWidth;
  private final int mCoverMinMargin;
  protected DocImageView mImageView;
  protected final float mRatio = 1.4F;
  private TextView mSubtitle;
  private TextView mTitle;

  public MagazineBucketEntry(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public MagazineBucketEntry(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public MagazineBucketEntry(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = paramContext.getResources();
    this.mCoverMaxWidth = localResources.getDimensionPixelSize(2131427347);
    this.mCoverMinMargin = localResources.getDimensionPixelSize(2131427348);
  }

  public void bind(Document paramDocument1, Document paramDocument2, BitmapLoader paramBitmapLoader, boolean paramBoolean, View.OnClickListener paramOnClickListener)
  {
    if (this.mBoundDocument == paramDocument2)
      return;
    this.mBoundDocument = paramDocument2;
    this.mImageView.delayLoadingUntilLayout();
    this.mImageView.bind(paramDocument2, paramBitmapLoader);
    this.mImageView.requestLayout();
    String str1 = paramDocument2.getTitle();
    this.mTitle.setText(str1);
    TextView localTextView;
    if (paramDocument1 != null)
    {
      String str3 = paramDocument1.getTitle();
      localTextView = this.mTitle;
      if (!str3.equals(str1))
        break label273;
    }
    label273: for (int m = 8; ; m = 0)
    {
      localTextView.setVisibility(m);
      this.mSubtitle.setText(paramDocument2.getSubtitle());
      this.mSubtitle.setVisibility(0);
      if (!paramDocument2.hasItemBadges())
        break label279;
      DocAnnotations.Badge localBadge = paramDocument2.getFirstItemBadge();
      String str2 = BadgeUtils.getImageUrl(localBadge, 6);
      if (str2 != null)
      {
        int k = this.mSubtitle.getResources().getDimensionPixelSize(2131427397);
        this.mBadge.loadDecoration(paramBitmapLoader, str2, k);
      }
      this.mBadge.setText(localBadge.getTitle().toUpperCase());
      if (this.mTitle.getVisibility() == 0)
        this.mSubtitle.setVisibility(8);
      this.mBadge.setVisibility(0);
      int j = CorpusResourceUtils.getCorpusCellContentDescriptionResource(paramDocument2.getBackend());
      View localView = this.mAccessibilityOverlay;
      Context localContext = getContext();
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = str1;
      arrayOfObject[1] = this.mSubtitle.getText();
      localView.setContentDescription(localContext.getString(j, arrayOfObject));
      this.mAccessibilityOverlay.setOnClickListener(paramOnClickListener);
      break;
    }
    label279: DecoratedTextView localDecoratedTextView = this.mBadge;
    if (this.mTitle.getVisibility() == 8);
    for (int i = 4; ; i = 8)
    {
      localDecoratedTextView.setVisibility(i);
      break;
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mImageView = ((DocImageView)findViewById(2131231083));
    this.mTitle = ((TextView)findViewById(2131231084));
    this.mSubtitle = ((TextView)findViewById(2131231085));
    this.mBadge = ((DecoratedTextView)findViewById(2131231086));
    this.mAccessibilityOverlay = findViewById(2131230744);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getWidth();
    int j = getHeight();
    int k = j - getPaddingBottom();
    int m = (i - this.mImageView.getMeasuredWidth()) / 2;
    if (this.mBadge.getVisibility() != 8)
    {
      this.mBadge.layout(m, k - this.mBadge.getMeasuredHeight(), m + this.mBadge.getMeasuredWidth(), k);
      k -= this.mBadge.getMeasuredHeight();
    }
    if (this.mSubtitle.getVisibility() != 8)
    {
      this.mSubtitle.layout(m, k - this.mSubtitle.getMeasuredHeight(), m + this.mSubtitle.getMeasuredWidth(), k);
      k -= this.mSubtitle.getMeasuredHeight();
    }
    if (this.mTitle.getVisibility() != 8)
    {
      this.mTitle.layout(m, k - this.mTitle.getMeasuredHeight(), m + this.mTitle.getMeasuredWidth(), k);
      k -= this.mTitle.getMeasuredHeight();
    }
    this.mImageView.layout(m, k - this.mImageView.getMeasuredHeight(), m + this.mImageView.getMeasuredWidth(), k);
    this.mAccessibilityOverlay.layout(0, 0, i, j);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = Math.min(this.mCoverMaxWidth, i - 2 * this.mCoverMinMargin);
    int k = getPaddingTop();
    int m = (int)(1.4F * j);
    int n = View.MeasureSpec.makeMeasureSpec(j, 1073741824);
    if (!this.mImageView.isLoaded())
    {
      this.mImageView.measure(n, View.MeasureSpec.makeMeasureSpec(m, 1073741824));
      int i3 = k + m;
      int i4 = View.MeasureSpec.makeMeasureSpec(j, -2147483648);
      if (this.mTitle.getVisibility() != 8)
      {
        this.mTitle.measure(i4, 0);
        i3 += this.mTitle.getMeasuredHeight();
      }
      if (this.mSubtitle.getVisibility() != 8)
      {
        this.mSubtitle.measure(i4, 0);
        i3 += this.mSubtitle.getMeasuredHeight();
      }
      if (this.mBadge.getVisibility() != 8)
      {
        this.mBadge.measure(i4, 0);
        i3 += this.mBadge.getMeasuredHeight();
      }
      int i5 = i3 + getPaddingBottom();
      this.mAccessibilityOverlay.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(i5, 1073741824));
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), i5);
      return;
    }
    Drawable localDrawable = this.mImageView.getDrawable();
    float f = localDrawable.getIntrinsicHeight() / localDrawable.getIntrinsicWidth();
    int i2;
    int i1;
    if (f > 1.4F)
    {
      i2 = Math.min(m, localDrawable.getIntrinsicHeight());
      i1 = (int)(i2 / f);
    }
    while (true)
    {
      this.mImageView.measure(View.MeasureSpec.makeMeasureSpec(i1, 1073741824), View.MeasureSpec.makeMeasureSpec(i2, 1073741824));
      break;
      i1 = Math.min(j, localDrawable.getIntrinsicWidth());
      i2 = (int)(f * i1);
    }
  }

  public void setTitle(String paramString)
  {
    this.mTitle.setText(paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.MagazineBucketEntry
 * JD-Core Version:    0.6.2
 */