package com.google.android.finsky.layout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.DocAnnotations.PlusProfile;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.Rev.Review;

public class ReviewItemLayout extends LinearLayout
{
  private TextView mBody;
  private ReviewItemHeaderLayout mHeader;
  private TextView mMetadata;
  private FifeImageView mProfilePicture;
  private View mRatingImage;
  private View mRatingSeparator;
  private TextView mTitle;

  public ReviewItemLayout(Context paramContext)
  {
    super(paramContext);
  }

  public ReviewItemLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private String getReviewExtraInfo(Document paramDocument, Rev.Review paramReview)
  {
    if (paramDocument.getBackend() != 3)
    {
      localObject = null;
      return localObject;
    }
    String str1 = paramReview.getDocumentVersion();
    Object localObject = paramReview.getDeviceName();
    int i;
    if (!TextUtils.isEmpty(str1))
    {
      i = 1;
      label35: if (TextUtils.isEmpty((CharSequence)localObject))
        break label68;
    }
    label68: for (int j = 1; ; j = 0)
    {
      if (i != 0)
        break label74;
      if (j != 0)
        break;
      localObject = null;
      break;
      i = 0;
      break label35;
    }
    label74: DocDetails.AppDetails localAppDetails = paramDocument.getAppDetails();
    if ((localAppDetails.hasVersionString()) && (str1.equals(localAppDetails.getVersionString())));
    for (int k = 1; ; k = 0)
    {
      if (k == 0)
        break label125;
      if (j != 0)
        break;
      localObject = null;
      break;
    }
    label125: Context localContext = getContext();
    if (j != 0);
    for (String str2 = localContext.getString(2131165543, new Object[] { localObject }); ; str2 = localContext.getString(2131165544))
    {
      localObject = str2;
      break;
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mTitle = ((TextView)findViewById(2131231231));
    this.mHeader = ((ReviewItemHeaderLayout)findViewById(2131231232));
    this.mBody = ((TextView)findViewById(2131231240));
    this.mMetadata = ((TextView)findViewById(2131231239));
    this.mRatingImage = findViewById(2131231228);
    this.mRatingSeparator = findViewById(2131231229);
    this.mProfilePicture = ((FifeImageView)findViewById(2131231216));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    int i = (this.mRatingImage.getTop() + this.mRatingImage.getBottom()) / 2 - this.mRatingSeparator.getHeight() / 2;
    this.mRatingSeparator.layout(this.mRatingSeparator.getLeft(), i, this.mRatingSeparator.getRight(), i + this.mRatingSeparator.getHeight());
  }

  public void setRateReviewClickListener(View.OnClickListener paramOnClickListener)
  {
    this.mRatingImage.setOnClickListener(paramOnClickListener);
  }

  public void setReviewInfo(Document paramDocument, Rev.Review paramReview)
  {
    String str1 = paramReview.getTitle();
    int i;
    label60: int j;
    label68: String str2;
    if (!TextUtils.isEmpty(str1))
    {
      this.mTitle.setText(str1);
      this.mTitle.setVisibility(0);
      this.mHeader.setReviewInfo(paramReview);
      this.mBody.setText(paramReview.getComment());
      if (TextUtils.isEmpty(paramReview.getCommentId()))
        break label158;
      i = 1;
      if (i == 0)
        break label164;
      j = 0;
      this.mRatingImage.setVisibility(j);
      this.mRatingSeparator.setVisibility(j);
      str2 = getReviewExtraInfo(paramDocument, paramReview);
      if (str2 != null)
        break label171;
      this.mMetadata.setVisibility(8);
      label108: DocAnnotations.PlusProfile localPlusProfile = (DocAnnotations.PlusProfile)null;
      if (localPlusProfile == null)
        break label191;
      this.mProfilePicture.setImage(localPlusProfile.getProfileImage(), FinskyApp.get().getBitmapLoader());
      this.mProfilePicture.setVisibility(0);
    }
    while (true)
    {
      return;
      this.mTitle.setVisibility(8);
      break;
      label158: i = 0;
      break label60;
      label164: j = 8;
      break label68;
      label171: this.mMetadata.setVisibility(0);
      this.mMetadata.setText(str2);
      break label108;
      label191: this.mProfilePicture.setVisibility(8);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ReviewItemLayout
 * JD-Core Version:    0.6.2
 */