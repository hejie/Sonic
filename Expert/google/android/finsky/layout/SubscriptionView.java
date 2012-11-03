package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.remoting.protos.Common.SubscriptionTerms;
import com.google.android.finsky.remoting.protos.Common.TimePeriod;
import com.google.android.finsky.remoting.protos.DocDetails.SubscriptionDetails;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.ExpandableUtils;
import com.google.android.finsky.utils.FinskyLog;
import java.util.Date;
import java.util.List;

public class SubscriptionView extends RelativeLayout
{
  private Button mCancelButton;
  private CancelListener mCancelListener;
  private SubscriptionDateInfo mDateInfo;
  private View mDescriptionCollapser;
  private View mDescriptionExpander;
  private Document mDocument;
  private int mExpansionState = -1;
  private final int mHalfSeparatorThickness;
  private final Paint mSeparatorPaint;
  private final int mSeparatorThickness;
  private boolean mShowsBottomSeparator;
  private boolean mShowsTopSeparator;
  private TextView mSubscriptionDescription;
  private TextView mSubscriptionPrice;
  private TextView mSubscriptionRenewal;
  private TextView mSubscriptionStatus;
  private TextView mSubscriptionTitle;

  public SubscriptionView(Context paramContext)
  {
    this(paramContext, null);
  }

  public SubscriptionView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    Resources localResources = paramContext.getResources();
    this.mSeparatorThickness = localResources.getDimensionPixelSize(2131427368);
    this.mHalfSeparatorThickness = ((int)FloatMath.ceil(this.mSeparatorThickness / 2.0F));
    this.mSeparatorPaint = new Paint();
    this.mSeparatorPaint.setColor(localResources.getColor(2131361799));
    this.mSeparatorPaint.setStrokeWidth(this.mSeparatorThickness);
    this.mShowsTopSeparator = false;
    this.mShowsBottomSeparator = true;
    this.mDateInfo = new SubscriptionDateInfo();
  }

  private void collapseDescription()
  {
    this.mDescriptionExpander.setVisibility(0);
    this.mSubscriptionDescription.setVisibility(8);
    this.mDescriptionCollapser.setVisibility(8);
  }

  private void expandDescription()
  {
    this.mDescriptionExpander.setVisibility(8);
    this.mSubscriptionDescription.setVisibility(0);
    this.mDescriptionCollapser.setVisibility(0);
  }

  public static void fillSubscriptionDateInfo(SubscriptionDateInfo paramSubscriptionDateInfo, LibrarySubscriptionEntry paramLibrarySubscriptionEntry, Resources paramResources)
  {
    long l = System.currentTimeMillis();
    String str = DateUtils.formatShortDisplayDate(new Date(paramLibrarySubscriptionEntry.validUntilTimestampMs));
    if (paramLibrarySubscriptionEntry.isAutoRenewing)
      if (l < paramLibrarySubscriptionEntry.trialUntilTimestampMs)
      {
        paramSubscriptionDateInfo.renewalDescription = Html.fromHtml(paramResources.getString(2131165504, new Object[] { DateUtils.formatShortDisplayDate(new Date(paramLibrarySubscriptionEntry.trialUntilTimestampMs)) }));
        paramSubscriptionDateInfo.statusResourceId = 2131165511;
      }
    for (paramSubscriptionDateInfo.canCancel = true; ; paramSubscriptionDateInfo.canCancel = false)
    {
      return;
      if (l < paramLibrarySubscriptionEntry.validUntilTimestampMs);
      for (paramSubscriptionDateInfo.renewalDescription = Html.fromHtml(paramResources.getString(2131165505, new Object[] { str })); ; paramSubscriptionDateInfo.renewalDescription = null)
      {
        paramSubscriptionDateInfo.statusResourceId = 2131165510;
        break;
      }
      paramSubscriptionDateInfo.renewalDescription = Html.fromHtml(paramResources.getString(2131165506, new Object[] { str }));
      paramSubscriptionDateInfo.statusResourceId = 2131165512;
    }
  }

  public static String getSubscriptionPriceDescription(Document paramDocument, Common.Offer paramOffer, Resources paramResources)
  {
    String str;
    if (!paramOffer.hasSubscriptionTerms())
    {
      str = null;
      return str;
    }
    Common.TimePeriod localTimePeriod = paramOffer.getSubscriptionTerms().getRecurringPeriod();
    int i = -1;
    switch (localTimePeriod.getUnit())
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramDocument.getFormattedPrice(1);
      arrayOfObject[1] = paramResources.getString(i);
      str = paramResources.getString(2131165499, arrayOfObject);
      break;
      i = 2131165500;
      continue;
      i = 2131165501;
      continue;
      i = 2131165502;
      continue;
      i = 2131165503;
    }
  }

  private void hideDescription()
  {
    this.mSubscriptionDescription.setVisibility(8);
    this.mDescriptionCollapser.setVisibility(8);
    this.mDescriptionExpander.setVisibility(8);
  }

  public void configure(final Document paramDocument, final LibrarySubscriptionEntry paramLibrarySubscriptionEntry, CancelListener paramCancelListener, Bundle paramBundle)
  {
    this.mDocument = paramDocument;
    this.mCancelListener = paramCancelListener;
    this.mSubscriptionTitle.setText(this.mDocument.getTitle());
    Resources localResources = getContext().getResources();
    int i;
    if (paramDocument.getSubscriptionDetails() != null)
      if (this.mDocument.getSubscriptionDetails().getSubscriptionPeriod() == 1)
      {
        i = 2131165502;
        TextView localTextView = this.mSubscriptionPrice;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = this.mDocument.getFormattedPrice(1);
        arrayOfObject2[1] = localResources.getString(i);
        localTextView.setText(localResources.getString(2131165499, arrayOfObject2));
        label107: this.mSubscriptionStatus.setTextColor(CorpusResourceUtils.getBackendForegroundColor(getContext(), this.mDocument.getBackend()));
        fillSubscriptionDateInfo(this.mDateInfo, paramLibrarySubscriptionEntry, localResources);
        if (!TextUtils.isEmpty(this.mDateInfo.renewalDescription))
          break label335;
        this.mSubscriptionRenewal.setVisibility(8);
        label160: this.mSubscriptionStatus.setText(this.mDateInfo.statusResourceId);
        if (!this.mDateInfo.canCancel)
          break label360;
        this.mCancelButton.setVisibility(0);
        this.mCancelButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            SubscriptionView.this.mCancelListener.onCancel(paramDocument, paramLibrarySubscriptionEntry);
          }
        });
        setNextFocusRightId(this.mCancelButton.getId());
        this.mCancelButton.setNextFocusLeftId(getId());
      }
    while (true)
    {
      if (!TextUtils.isEmpty(paramDocument.getDescription()))
        break label377;
      hideDescription();
      return;
      i = 2131165503;
      break;
      Common.Offer localOffer = (Common.Offer)paramDocument.getAvailableOffers().get(0);
      String str = getSubscriptionPriceDescription(this.mDocument, localOffer, localResources);
      if (!TextUtils.isEmpty(str))
      {
        this.mSubscriptionPrice.setText(str);
        break label107;
      }
      this.mSubscriptionPrice.setVisibility(8);
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = paramDocument.getDocId();
      FinskyLog.wtf("Document for %s does not contain subscription details or terms", arrayOfObject1);
      break label107;
      label335: this.mSubscriptionRenewal.setVisibility(0);
      this.mSubscriptionRenewal.setText(this.mDateInfo.renewalDescription);
      break label160;
      label360: this.mCancelButton.setVisibility(8);
      setNextFocusRightId(-1);
    }
    label377: this.mSubscriptionDescription.setText(paramDocument.getDescription());
    if (this.mExpansionState < 0)
      this.mExpansionState = ExpandableUtils.getSavedExpansionState(paramBundle, paramDocument.getDocId(), 1);
    if (this.mExpansionState == 2)
      expandDescription();
    while (true)
    {
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (SubscriptionView.this.mExpansionState == 2)
          {
            SubscriptionView.this.collapseDescription();
            SubscriptionView.access$102(SubscriptionView.this, 1);
          }
          while (true)
          {
            return;
            SubscriptionView.this.expandDescription();
            SubscriptionView.access$102(SubscriptionView.this, 2);
          }
        }
      });
      break;
      collapseDescription();
    }
  }

  public void hideBottomSeparator()
  {
    this.mShowsBottomSeparator = false;
    invalidate();
  }

  public void onDraw(Canvas paramCanvas)
  {
    int i = getWidth();
    int j = getHeight();
    if (this.mShowsBottomSeparator)
    {
      int m = j - this.mHalfSeparatorThickness;
      paramCanvas.drawLine(0.0F, m, i, m, this.mSeparatorPaint);
    }
    if (this.mShowsTopSeparator)
    {
      int k = this.mHalfSeparatorThickness;
      paramCanvas.drawLine(0.0F, k, i, k, this.mSeparatorPaint);
    }
    super.onDraw(paramCanvas);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mSubscriptionTitle = ((TextView)findViewById(2131231253));
    this.mSubscriptionPrice = ((TextView)findViewById(2131231255));
    this.mSubscriptionRenewal = ((TextView)findViewById(2131231256));
    this.mSubscriptionStatus = ((TextView)findViewById(2131231254));
    this.mCancelButton = ((Button)findViewById(2131231257));
    this.mSubscriptionDescription = ((TextView)findViewById(2131231259));
    this.mDescriptionExpander = findViewById(2131231258);
    this.mDescriptionCollapser = findViewById(2131231260);
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    String str = this.mDocument.getDocId();
    if (!TextUtils.isEmpty(str))
      ExpandableUtils.saveExpansionState(paramBundle, str, this.mExpansionState);
  }

  public void showTopSeparator()
  {
    this.mShowsTopSeparator = true;
    invalidate();
  }

  public static abstract interface CancelListener
  {
    public abstract void onCancel(Document paramDocument, LibrarySubscriptionEntry paramLibrarySubscriptionEntry);
  }

  public static class SubscriptionDateInfo
  {
    boolean canCancel;
    CharSequence renewalDescription;
    int statusResourceId;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.SubscriptionView
 * JD-Core Version:    0.6.2
 */