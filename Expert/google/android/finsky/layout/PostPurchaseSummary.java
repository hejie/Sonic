package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R.styleable;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;

public class PostPurchaseSummary extends RelativeLayout
{
  private TextView mAddedToLibrary;
  private ImageView mAddedToLibraryImage;
  private final boolean mAlignButtons;
  private View mButtonContainer;
  private DecoratedTextView mCreator;
  private DocImageView mThumbnail;
  private DecoratedTextView mTipperSticker;
  private TextView mTitle;

  public PostPurchaseSummary(Context paramContext)
  {
    this(paramContext, null);
  }

  public PostPurchaseSummary(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PostPurchaseSummary);
    this.mAlignButtons = localTypedArray.getBoolean(0, false);
    localTypedArray.recycle();
  }

  public void bind(Document paramDocument, BitmapLoader paramBitmapLoader)
  {
    this.mTitle.setText(paramDocument.getTitle());
    this.mTitle.setSelected(true);
    int i = paramDocument.getBackend();
    String str;
    if (i == 6)
    {
      DecoratedTextView localDecoratedTextView = this.mCreator;
      if (paramDocument.getDocumentType() == 15)
      {
        str = getResources().getString(2131165811).toUpperCase();
        localDecoratedTextView.setText(str);
      }
    }
    while (true)
    {
      BadgeUtils.configureCreatorBadge(paramDocument, paramBitmapLoader, this.mCreator, -1);
      BadgeUtils.configureTipperSticker(paramDocument, this.mTipperSticker);
      this.mThumbnail.getLayoutParams().width = CorpusResourceUtils.getLargeDetailsIconWidth(getContext(), paramDocument.getDocumentType());
      this.mThumbnail.bind(paramDocument, paramBitmapLoader);
      this.mAddedToLibrary.setTextColor(CorpusResourceUtils.getBackendForegroundColor(getContext(), i));
      this.mAddedToLibraryImage.setImageResource(CorpusResourceUtils.getAddedToLibraryDrawableId(i));
      return;
      str = paramDocument.getSubtitle().toUpperCase();
      break;
      this.mCreator.setText(paramDocument.getCreator().toUpperCase());
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mTitle = ((TextView)findViewById(2131230957));
    this.mCreator = ((DecoratedTextView)findViewById(2131230958));
    this.mTipperSticker = ((DecoratedTextView)findViewById(2131230965));
    this.mThumbnail = ((DocImageView)findViewById(2131230909));
    this.mAddedToLibrary = ((TextView)findViewById(2131230969));
    this.mAddedToLibraryImage = ((ImageView)findViewById(2131230968));
    this.mButtonContainer = findViewById(2131230920);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (!this.mAlignButtons);
    while (true)
    {
      return;
      if (this.mButtonContainer.getBottom() < this.mThumbnail.getBottom())
        this.mButtonContainer.layout(this.mButtonContainer.getLeft(), this.mThumbnail.getBottom() - this.mButtonContainer.getMeasuredHeight(), this.mButtonContainer.getRight(), this.mThumbnail.getBottom());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.PostPurchaseSummary
 * JD-Core Version:    0.6.2
 */