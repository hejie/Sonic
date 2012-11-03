package com.google.android.finsky.fragments;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;

public abstract class DetailsViewBinder
  implements Response.ErrorListener
{
  protected Context mContext;
  protected DfeApi mDfeApi;
  protected Document mDoc;
  protected int mHeaderLayoutId;
  protected LayoutInflater mInflater;
  protected View mLayout;
  protected LayoutSwitcher mLayoutSwitcher;
  protected NavigationManager mNavigationManager;

  public void bind(View paramView, Document paramDocument, int paramInt)
  {
    if (paramInt >= 0);
    for (String str = this.mContext.getString(paramInt); ; str = "")
    {
      bind(paramView, paramDocument, str);
      return;
    }
  }

  public void bind(View paramView, Document paramDocument, String paramString)
  {
    this.mLayout = paramView;
    this.mDoc = paramDocument;
    bind(paramView, paramString, paramDocument.getBackend());
  }

  public void bind(View paramView, String paramString, int paramInt)
  {
    this.mLayout = paramView;
    setupHeader(paramString, paramInt);
  }

  public void hideHeader()
  {
    TextView localTextView = (TextView)this.mLayout.findViewById(this.mHeaderLayoutId);
    if (localTextView != null)
      localTextView.setVisibility(8);
  }

  public void init(Context paramContext, DfeApi paramDfeApi, NavigationManager paramNavigationManager)
  {
    this.mContext = paramContext;
    this.mDfeApi = paramDfeApi;
    this.mNavigationManager = paramNavigationManager;
    this.mInflater = LayoutInflater.from(this.mContext);
    this.mHeaderLayoutId = 2131230895;
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    if (this.mLayoutSwitcher != null)
      this.mLayoutSwitcher.switchToErrorMode(ErrorStrings.get(this.mContext, paramVolleyError));
  }

  public void setHeaderNavigable(boolean paramBoolean)
  {
    TextView localTextView = (TextView)this.mLayout.findViewById(this.mHeaderLayoutId);
    if (paramBoolean);
    for (int i = 2130837650; ; i = 0)
    {
      localTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
      return;
    }
  }

  protected void setupHeader(String paramString, int paramInt)
  {
    TextView localTextView = (TextView)this.mLayout.findViewById(this.mHeaderLayoutId);
    if (localTextView != null)
    {
      if (!TextUtils.isEmpty(paramString))
        localTextView.setText(paramString.toUpperCase());
      localTextView.setTextColor(CorpusResourceUtils.getBackendHintColor(this.mContext, paramInt));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.DetailsViewBinder
 * JD-Core Version:    0.6.2
 */