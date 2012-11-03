package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.DocAnnotations.PlusOneData;
import com.google.android.finsky.remoting.protos.DocAnnotations.PlusProfile;
import com.google.android.finsky.remoting.protos.PlusOne.PlusOneResponse;
import java.text.DecimalFormat;
import java.util.List;

public class DetailsPlusOne extends AccessibleLinearLayout
  implements Response.Listener<PlusOne.PlusOneResponse>, Response.ErrorListener
{
  private static final DecimalFormat sAllIntegerDigitFormatter = new DecimalFormat("#");
  private static final DecimalFormat sSingleFractionDigitFormatter = new DecimalFormat("@#");
  private int mCirclesPeopleCount;
  private String mCookie;
  private DfeApi mDfeApi;
  private Document mDoc;
  private String mFirstFriendName;
  private boolean mHasPlusOneData;
  private boolean mIsDetached;
  private boolean mIsWaitingServerResponse;
  private boolean mSetByUser;
  private long mTotal;
  private String mUrl;
  private PlusOneButtonState mUserState;

  public DetailsPlusOne(Context paramContext)
  {
    this(paramContext, null);
  }

  public DetailsPlusOne(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void bindPlusOneButton()
  {
    if (this.mSetByUser);
    for (PlusOneButtonState localPlusOneButtonState = PlusOneButtonState.On; ; localPlusOneButtonState = PlusOneButtonState.Off)
    {
      this.mUserState = localPlusOneButtonState;
      syncButtonState();
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DetailsPlusOne.this.handleClick();
        }
      });
      return;
    }
  }

  private void bindPlusOneData(DocAnnotations.PlusOneData paramPlusOneData, Bundle paramBundle)
  {
    if ((paramBundle != null) && (paramBundle.containsKey("PlusOneViewBinder.hasPlusOneData")))
    {
      this.mHasPlusOneData = paramBundle.getBoolean("PlusOneViewBinder.hasPlusOneData");
      if (this.mHasPlusOneData)
      {
        this.mSetByUser = paramBundle.getBoolean("PlusOneViewBinder.setByUser");
        this.mTotal = paramBundle.getLong("PlusOneViewBinder.total");
        this.mCirclesPeopleCount = paramBundle.getInt("PlusOneViewBinder.circlesPeopleCount");
        this.mFirstFriendName = paramBundle.getString("PlusOneViewBinder.firstFriendName");
      }
    }
    while (true)
    {
      return;
      if (paramPlusOneData == null)
      {
        this.mHasPlusOneData = false;
      }
      else
      {
        this.mHasPlusOneData = true;
        this.mSetByUser = paramPlusOneData.getSetByUser();
        this.mTotal = paramPlusOneData.getTotal();
        this.mCirclesPeopleCount = paramPlusOneData.getCirclesProfilesCount();
        if (this.mCirclesPeopleCount <= 0)
          this.mFirstFriendName = null;
        else
          this.mFirstFriendName = ((DocAnnotations.PlusProfile)paramPlusOneData.getCirclesProfilesList().get(0)).getDisplayName();
      }
    }
  }

  private void bindPlusOneLegend()
  {
    TextView localTextView = (TextView)findViewById(2131230944);
    if (this.mUserState == PlusOneButtonState.Error)
      localTextView.setText(2131165743);
    while (true)
    {
      return;
      if (this.mUserState == PlusOneButtonState.On);
      for (int i = 1; ; i = 0)
      {
        if ((this.mTotal != 0L) || (i != 0))
          break label66;
        localTextView.setText(2131165744);
        break;
      }
      label66: if ((this.mTotal == 1L) && (i != 0))
      {
        localTextView.setText(2131165745);
      }
      else
      {
        Context localContext = getContext();
        int j = this.mCirclesPeopleCount;
        if (j == 0)
        {
          String str2 = formatPlusOneCount(localContext, this.mTotal, 2131165752);
          localTextView.setText(getResources().getQuantityString(2131558402, (int)this.mTotal, new Object[] { str2 }));
        }
        else if ((j == 1) && (i == 0))
        {
          Resources localResources3 = getResources();
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = this.mFirstFriendName;
          localTextView.setText(localResources3.getString(2131165746, arrayOfObject3));
        }
        else if ((j == 1) && (i != 0))
        {
          Resources localResources2 = localContext.getResources();
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = this.mFirstFriendName;
          localTextView.setText(localResources2.getString(2131165747, arrayOfObject2));
        }
        else
        {
          int k = j - 1;
          String str1 = formatPlusOneCount(localContext, k, 2131165752);
          Resources localResources1 = getResources();
          Object[] arrayOfObject1 = new Object[2];
          arrayOfObject1[0] = this.mFirstFriendName;
          arrayOfObject1[1] = str1;
          localTextView.setText(localResources1.getQuantityString(2131558403, k, arrayOfObject1));
        }
      }
    }
  }

  public static String formatPlusOneCount(Context paramContext, long paramLong, int paramInt)
  {
    String str2;
    if (paramLong < 1000L)
    {
      Resources localResources2 = paramContext.getResources();
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Long.valueOf(paramLong);
      arrayOfObject2[1] = "";
      str2 = localResources2.getString(paramInt, arrayOfObject2);
      return str2;
    }
    float f1 = (float)paramLong;
    int i = 2131165750;
    float f2 = f1 / 1000.0F;
    if (f2 > 1000.0F)
    {
      i = 2131165751;
      f2 /= 1000.0F;
    }
    if (f2 < 10.0F);
    for (String str1 = sSingleFractionDigitFormatter.format(f2); ; str1 = sAllIntegerDigitFormatter.format(f2))
    {
      Resources localResources1 = paramContext.getResources();
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = str1;
      arrayOfObject1[1] = paramContext.getString(i);
      str2 = localResources1.getString(paramInt, arrayOfObject1);
      break;
    }
  }

  private String getAnalyticsString()
  {
    return "plusOne?doc=" + this.mDoc.getDocId();
  }

  private void handleClick()
  {
    FinskyApp.get().getAnalytics().logPageView(this.mUrl, this.mCookie, getAnalyticsString());
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = "cidi";
    arrayOfObject[1] = this.mDoc.getDocId();
    arrayOfObject[2] = "c";
    arrayOfObject[3] = this.mUrl;
    localFinskyEventLog.logTag("+1", arrayOfObject);
    if (this.mSetByUser)
    {
      this.mTotal -= 1L;
      this.mSetByUser = false;
      setLoading(true);
      if (!this.mSetByUser)
        break label161;
    }
    label161: for (PlusOneButtonState localPlusOneButtonState = PlusOneButtonState.On; ; localPlusOneButtonState = PlusOneButtonState.Off)
    {
      this.mUserState = localPlusOneButtonState;
      syncButtonState();
      this.mDfeApi.setPlusOne(this.mDoc.getDocId(), this.mSetByUser, this, this);
      return;
      this.mTotal = (1L + this.mTotal);
      this.mSetByUser = true;
      break;
    }
  }

  private void rebindViews()
  {
    if (this.mHasPlusOneData)
    {
      bindPlusOneButton();
      bindPlusOneLegend();
      syncContentDescription();
    }
  }

  private void setLoading(boolean paramBoolean)
  {
    this.mIsWaitingServerResponse = paramBoolean;
    View localView = findViewById(2131230943);
    if (this.mIsWaitingServerResponse);
    for (int i = 0; ; i = 8)
    {
      localView.setVisibility(i);
      return;
    }
  }

  private void syncButtonState()
  {
    int i = 2130837674;
    ImageView localImageView = (ImageView)findViewById(2131230942);
    switch (2.$SwitchMap$com$google$android$finsky$layout$DetailsPlusOne$PlusOneButtonState[this.mUserState.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      if (this.mIsWaitingServerResponse);
      while (true)
      {
        localImageView.setImageResource(i);
        break;
        i = 2130837677;
      }
      if (this.mIsWaitingServerResponse);
      while (true)
      {
        localImageView.setImageResource(i);
        break;
        i = 2130837676;
      }
      localImageView.setImageResource(2130837675);
    }
  }

  private void syncContentDescription()
  {
    setContentDescription(((TextView)findViewById(2131230944)).getText());
  }

  public void bind(DfeApi paramDfeApi, String paramString1, String paramString2, Document paramDocument, boolean paramBoolean, Bundle paramBundle)
  {
    this.mDfeApi = paramDfeApi;
    this.mUrl = paramString1;
    this.mCookie = paramString2;
    this.mDoc = paramDocument;
    if ((paramBoolean) && (!this.mDoc.hasPlusOneData()))
      setVisibility(8);
    while (true)
    {
      return;
      setVisibility(0);
      bindPlusOneData(this.mDoc.getPlusOneData(), paramBundle);
      rebindViews();
    }
  }

  protected void onDetachedFromWindow()
  {
    this.mIsDetached = true;
    super.onDetachedFromWindow();
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    if (!this.mIsDetached)
    {
      setLoading(false);
      this.mUserState = PlusOneButtonState.Error;
      syncButtonState();
      bindPlusOneLegend();
      syncContentDescription();
    }
    this.mDfeApi.invalidateDetailsCache(this.mUrl, true);
  }

  public void onResponse(PlusOne.PlusOneResponse paramPlusOneResponse)
  {
    if (!this.mIsDetached)
    {
      setLoading(false);
      rebindViews();
    }
    this.mDfeApi.invalidateDetailsCache(this.mUrl, true);
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("PlusOneViewBinder.hasPlusOneData", this.mHasPlusOneData);
    if (this.mHasPlusOneData)
    {
      paramBundle.putBoolean("PlusOneViewBinder.setByUser", this.mSetByUser);
      paramBundle.putLong("PlusOneViewBinder.total", this.mTotal);
      paramBundle.putInt("PlusOneViewBinder.circlesPeopleCount", this.mCirclesPeopleCount);
      paramBundle.putString("PlusOneViewBinder.firstFriendName", this.mFirstFriendName);
    }
  }

  private static enum PlusOneButtonState
  {
    static
    {
      Off = new PlusOneButtonState("Off", 1);
      Error = new PlusOneButtonState("Error", 2);
      PlusOneButtonState[] arrayOfPlusOneButtonState = new PlusOneButtonState[3];
      arrayOfPlusOneButtonState[0] = On;
      arrayOfPlusOneButtonState[1] = Off;
      arrayOfPlusOneButtonState[2] = Error;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DetailsPlusOne
 * JD-Core Version:    0.6.2
 */