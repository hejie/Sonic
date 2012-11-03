package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.PurchaseButtonHelper;

public class OverviewBucketEntry extends CheckedRelativeLayout
  implements DocumentCell
{
  protected View mActionPack;
  protected ViewStub mActionPackStub;
  protected DecoratedTextView mBadge;
  protected DecoratedTextView mCreator;
  private Document mDocument;
  private final int mHalfSeparatorThickness;
  private boolean mIsLastRow = false;
  protected TextView mPrice;
  protected RatingBar mRating;
  private final Paint mSeparatorPaint;
  private final float mSeparatorThickness;
  private boolean mShowRight = false;
  protected DocImageView mThumbnail;
  protected TextView mTitle;

  public OverviewBucketEntry(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public OverviewBucketEntry(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public OverviewBucketEntry(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = paramContext.getResources();
    this.mSeparatorThickness = localResources.getDimensionPixelSize(2131427368);
    this.mHalfSeparatorThickness = ((int)FloatMath.ceil(this.mSeparatorThickness / 2.0F));
    this.mSeparatorPaint = new Paint();
    this.mSeparatorPaint.setColor(localResources.getColor(2131361807));
    this.mSeparatorPaint.setStrokeWidth(this.mSeparatorThickness);
  }

  public void bind(Document paramDocument1, Document paramDocument2, BitmapLoader paramBitmapLoader, boolean paramBoolean, View.OnClickListener paramOnClickListener)
  {
    this.mDocument = paramDocument2;
    this.mIsLastRow = paramBoolean;
    this.mTitle.setVisibility(0);
    setTitle(paramDocument2.getTitle());
    this.mCreator.setVisibility(0);
    int i = paramDocument2.getDocumentType();
    int j;
    String str;
    if ((i == 16) || (i == 17))
    {
      j = 1;
      DecoratedTextView localDecoratedTextView = this.mCreator;
      if (j == 0)
        break label250;
      str = paramDocument2.getSubtitle();
      label75: localDecoratedTextView.setText(str);
      if (this.mRating != null)
      {
        if ((!paramDocument2.hasRating()) || (paramDocument2.getRatingCount() <= 0L))
          break label259;
        this.mRating.setRating(paramDocument2.getStarRating());
        this.mRating.setVisibility(0);
      }
    }
    while (true)
    {
      BadgeUtils.configureCreatorBadge(paramDocument2, paramBitmapLoader, this.mCreator, -1);
      BadgeUtils.configureRatingItemSection(paramDocument2, paramBitmapLoader, this.mRating, this.mBadge);
      PurchaseButtonHelper.stylePurchaseButton(paramDocument2, true, this.mPrice);
      this.mThumbnail.setVisibility(0);
      this.mThumbnail.bind(paramDocument2, paramBitmapLoader);
      int k = CorpusResourceUtils.getCorpusCellContentLongDescriptionResource(paramDocument2.getBackend());
      Context localContext = getContext();
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = paramDocument2.getTitle();
      arrayOfObject[1] = this.mCreator.getText();
      arrayOfObject[2] = this.mPrice.getText();
      setContentDescription(localContext.getString(k, arrayOfObject));
      setOnClickListener(paramOnClickListener);
      return;
      j = 0;
      break;
      label250: str = paramDocument2.getCreator();
      break label75;
      label259: this.mRating.setVisibility(4);
    }
  }

  public void configureActionPack(int paramInt1, final OnActionListener paramOnActionListener, int paramInt2)
  {
    if (this.mActionPack == null)
      this.mActionPack = this.mActionPackStub.inflate();
    this.mActionPack.setVisibility(0);
    ((ImageView)this.mActionPack.findViewById(2131230814)).setImageResource(paramInt1);
    this.mActionPack.setContentDescription(getResources().getString(paramInt2));
    this.mActionPack.setNextFocusLeftId(2131230750);
    setNextFocusRightId(this.mActionPack.getId());
    this.mActionPack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramOnActionListener.onAction(OverviewBucketEntry.this.mDocument);
      }
    });
  }

  public void hideActionPack()
  {
    if (this.mActionPack != null)
    {
      this.mActionPack.setVisibility(8);
      setNextFocusRightId(-1);
    }
  }

  public void onDraw(Canvas paramCanvas)
  {
    int i = getWidth();
    int j = getHeight();
    if (this.mShowRight)
    {
      int m = i - this.mHalfSeparatorThickness;
      paramCanvas.drawLine(m, 0.0F, m, j, this.mSeparatorPaint);
    }
    if (!this.mIsLastRow)
    {
      int k = j - this.mHalfSeparatorThickness;
      paramCanvas.drawLine(0.0F, k, i, k, this.mSeparatorPaint);
    }
    super.onDraw(paramCanvas);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mThumbnail = ((DocImageView)findViewById(2131230751));
    this.mTitle = ((TextView)findViewById(2131230754));
    this.mCreator = ((DecoratedTextView)findViewById(2131230755));
    this.mPrice = ((TextView)findViewById(2131230752));
    this.mBadge = ((DecoratedTextView)findViewById(2131231070));
    this.mRating = ((RatingBar)findViewById(2131231069));
    this.mActionPackStub = ((ViewStub)findViewById(2131230977));
  }

  public void setMockDocument(int paramInt)
  {
    if (this.mRating != null)
      this.mRating.setVisibility(8);
    this.mTitle.setText(2131165433);
    this.mCreator.setVisibility(8);
    this.mPrice.setVisibility(8);
    if (this.mBadge != null)
      this.mBadge.setVisibility(8);
    this.mThumbnail.setImageBitmap(CorpusResourceUtils.getPlaceholderIcon(paramInt, getContext().getResources()));
    BitmapLoader.BitmapContainer localBitmapContainer = (BitmapLoader.BitmapContainer)this.mThumbnail.getTag();
    if (localBitmapContainer != null)
      localBitmapContainer.cancelRequest();
    this.mThumbnail.setTag(null);
    this.mThumbnail.setVisibility(0);
  }

  public void setNoDocument()
  {
    if (this.mRating != null)
      this.mRating.setVisibility(8);
    this.mTitle.setVisibility(8);
    this.mCreator.setVisibility(8);
    this.mPrice.setVisibility(8);
    if (this.mBadge != null)
      this.mBadge.setVisibility(8);
    BitmapLoader.BitmapContainer localBitmapContainer = (BitmapLoader.BitmapContainer)this.mThumbnail.getTag();
    if (localBitmapContainer != null)
      localBitmapContainer.cancelRequest();
    this.mThumbnail.setTag(null);
    this.mThumbnail.setVisibility(8);
    this.mIsLastRow = true;
    this.mShowRight = false;
  }

  public void setRightSeparatorVisibility(boolean paramBoolean)
  {
    this.mShowRight = paramBoolean;
    invalidate();
  }

  public void setTitle(String paramString)
  {
    this.mTitle.setText(paramString);
  }

  public void setTitleColor(int paramInt)
  {
    this.mTitle.setTextColor(paramInt);
  }

  public static abstract interface OnActionListener
  {
    public abstract void onAction(Document paramDocument);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.OverviewBucketEntry
 * JD-Core Version:    0.6.2
 */