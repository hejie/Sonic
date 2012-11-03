package com.google.android.finsky.layout;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LayoutSwitcher
{
  private final View mContentLayout;
  private int mDataLayoutId;
  private final int mErrorLayoutId;
  private final Handler mHandler = new Handler();
  private final int mLoadingLayoutId;
  private int mMode;
  private volatile boolean mPendingLoad = false;
  private final RetryButtonListener mRetryListener;
  private final View.OnClickListener retryClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      LayoutSwitcher.this.switchToLoadingMode();
      LayoutSwitcher.this.mRetryListener.onRetry();
    }
  };

  public LayoutSwitcher(View paramView, int paramInt1, int paramInt2, int paramInt3, RetryButtonListener paramRetryButtonListener)
  {
    this.mDataLayoutId = paramInt1;
    this.mErrorLayoutId = paramInt2;
    this.mLoadingLayoutId = paramInt3;
    this.mContentLayout = paramView;
    this.mRetryListener = paramRetryButtonListener;
    resetMode();
  }

  public LayoutSwitcher(View paramView, int paramInt1, int paramInt2, int paramInt3, RetryButtonListener paramRetryButtonListener, int paramInt4)
  {
    this.mDataLayoutId = paramInt1;
    this.mErrorLayoutId = paramInt2;
    this.mLoadingLayoutId = paramInt3;
    this.mContentLayout = paramView;
    this.mRetryListener = paramRetryButtonListener;
    this.mMode = paramInt4;
  }

  public LayoutSwitcher(View paramView, int paramInt, RetryButtonListener paramRetryButtonListener)
  {
    this(paramView, paramInt, 2131231015, 2131230808, paramRetryButtonListener);
  }

  private void performSwitch(int paramInt, String paramString)
  {
    this.mPendingLoad = false;
    if (this.mMode == paramInt)
      return;
    switch (this.mMode)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
      switch (paramInt)
      {
      default:
        throw new IllegalStateException("Invalid mode " + paramInt + "should be LOADING_MODE, ERROR_MODE, DATA_MODE, or BLANK_MODE");
        setLoadingVisible(false);
        continue;
        setErrorVisible(false, null);
        continue;
        setDataVisible(false);
      case 0:
      case 3:
      case 1:
      case 2:
      }
    setLoadingVisible(true);
    while (true)
    {
      this.mMode = paramInt;
      break;
      setErrorVisible(true, paramString);
      continue;
      setDataVisible(true);
    }
  }

  private void resetMode()
  {
    this.mMode = 3;
    setLoadingVisible(false);
    setErrorVisible(false, null);
    setDataVisible(false);
  }

  private void setDataVisible(boolean paramBoolean)
  {
    ViewGroup localViewGroup = (ViewGroup)this.mContentLayout.findViewById(this.mDataLayoutId);
    if (localViewGroup != null)
      if (!paramBoolean)
        break label31;
    label31: for (int i = 0; ; i = 8)
    {
      localViewGroup.setVisibility(i);
      return;
    }
  }

  private void setErrorVisible(boolean paramBoolean, String paramString)
  {
    View localView = this.mContentLayout.findViewById(this.mErrorLayoutId);
    int i;
    Button localButton;
    if (paramBoolean)
    {
      i = 0;
      localView.setVisibility(i);
      if (paramBoolean)
        ((TextView)localView.findViewById(2131230788)).setText(paramString);
      localButton = (Button)localView.findViewById(2131231014);
      if (!paramBoolean)
        break label78;
    }
    label78: for (View.OnClickListener localOnClickListener = this.retryClickListener; ; localOnClickListener = null)
    {
      localButton.setOnClickListener(localOnClickListener);
      return;
      i = 8;
      break;
    }
  }

  private void setLoadingVisible(boolean paramBoolean)
  {
    View localView = this.mContentLayout.findViewById(this.mLoadingLayoutId);
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      localView.setVisibility(i);
      return;
    }
  }

  public boolean isDataMode()
  {
    if (this.mMode == 2);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void switchToBlankMode()
  {
    performSwitch(3, null);
  }

  public void switchToDataMode()
  {
    performSwitch(2, null);
  }

  public void switchToErrorMode(String paramString)
  {
    performSwitch(1, paramString);
  }

  public void switchToLoadingDelayed(int paramInt)
  {
    this.mPendingLoad = true;
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        if (LayoutSwitcher.this.mPendingLoad)
          LayoutSwitcher.this.switchToLoadingMode();
      }
    }
    , paramInt);
  }

  public void switchToLoadingMode()
  {
    performSwitch(0, null);
  }

  public static abstract interface RetryButtonListener
  {
    public abstract void onRetry();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.LayoutSwitcher
 * JD-Core Version:    0.6.2
 */