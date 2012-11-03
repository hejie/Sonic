package com.google.android.finsky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.navigationmanager.NavigationManager;

public abstract class PaginatedListAdapter extends BaseAdapter
  implements OnDataChangedListener
{
  protected final Context mContext;
  private FooterMode mFooterMode;
  private final LayoutInflater mLayoutInflater;
  protected final NavigationManager mNavigationManager;
  protected View.OnClickListener mRetryClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (PaginatedListAdapter.this.mFooterMode == PaginatedListAdapter.FooterMode.ERROR)
        PaginatedListAdapter.this.retryLoadingItems();
      PaginatedListAdapter.this.setFooterMode(PaginatedListAdapter.FooterMode.LOADING);
    }
  };

  public PaginatedListAdapter(Context paramContext, NavigationManager paramNavigationManager, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mContext = paramContext;
    this.mNavigationManager = paramNavigationManager;
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    if (paramBoolean1)
      this.mFooterMode = FooterMode.ERROR;
    while (true)
    {
      return;
      if (paramBoolean2)
        this.mFooterMode = FooterMode.LOADING;
      else
        this.mFooterMode = FooterMode.NONE;
    }
  }

  private void setFooterMode(FooterMode paramFooterMode)
  {
    this.mFooterMode = paramFooterMode;
    notifyDataSetChanged();
  }

  protected View getErrorFooterView(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = inflate(2130968688, paramViewGroup, false);
      ((Button)paramView.findViewById(2131231014)).setOnClickListener(this.mRetryClickListener);
    }
    ((TextView)paramView.findViewById(2131230788)).setText(getLastRequestError());
    return paramView;
  }

  protected FooterMode getFooterMode()
  {
    return this.mFooterMode;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  protected abstract String getLastRequestError();

  protected View getLoadingFooterView(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null);
    while (true)
    {
      return paramView;
      paramView = inflate(2130968726, paramViewGroup, false);
    }
  }

  protected View inflate(int paramInt, ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return this.mLayoutInflater.inflate(paramInt, paramViewGroup, paramBoolean);
  }

  public boolean isEnabled(int paramInt)
  {
    return false;
  }

  protected abstract boolean isMoreDataAvailable();

  public void onDataChanged()
  {
    if (isMoreDataAvailable())
      setFooterMode(FooterMode.LOADING);
    while (true)
    {
      return;
      setFooterMode(FooterMode.NONE);
    }
  }

  protected abstract void retryLoadingItems();

  public void triggerFooterErrorMode()
  {
    setFooterMode(FooterMode.ERROR);
  }

  protected static enum FooterMode
  {
    static
    {
      LOADING = new FooterMode("LOADING", 1);
      ERROR = new FooterMode("ERROR", 2);
      FooterMode[] arrayOfFooterMode = new FooterMode[3];
      arrayOfFooterMode[0] = NONE;
      arrayOfFooterMode[1] = LOADING;
      arrayOfFooterMode[2] = ERROR;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.PaginatedListAdapter
 * JD-Core Version:    0.6.2
 */