package com.google.android.finsky.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;

public class SidecarFragment extends Fragment
{
  private int mErrorInstance = 0;
  private Listener mListener;
  private int mState = 0;
  private int mSubstate;

  private void notifyListener()
  {
    if (this.mListener != null)
      this.mListener.onStateChange(this);
  }

  public int getErrorInstance()
  {
    return this.mErrorInstance;
  }

  public int getState()
  {
    return this.mState;
  }

  public int getSubstate()
  {
    return this.mSubstate;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setRetainInstance(true);
    if (paramBundle != null)
      restoreFromSavedInstanceState(paramBundle);
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("SidecarFragment.state", this.mState);
    paramBundle.putInt("SidecarFragment.substate", this.mSubstate);
    paramBundle.putInt("SidecarFragment.errorInstance", this.mErrorInstance);
  }

  protected void restoreFromSavedInstanceState(Bundle paramBundle)
  {
    this.mState = paramBundle.getInt("SidecarFragment.state");
    this.mSubstate = paramBundle.getInt("SidecarFragment.substate");
    this.mErrorInstance = paramBundle.getInt("SidecarFragment.errorInstance");
    if (this.mState == 1)
    {
      FinskyLog.d("Restoring after serialization in RUNNING, resetting to INIT.", new Object[0]);
      setState(0, 0);
    }
  }

  public void setListener(Listener paramListener)
  {
    this.mListener = paramListener;
    if (this.mListener != null)
      notifyListener();
  }

  protected void setState(int paramInt1, int paramInt2)
  {
    Utils.ensureOnMainThread();
    this.mState = paramInt1;
    this.mSubstate = paramInt2;
    if (paramInt1 == 3)
      this.mErrorInstance = (1 + this.mErrorInstance);
    notifyListener();
  }

  public static abstract interface Listener
  {
    public abstract void onStateChange(SidecarFragment paramSidecarFragment);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.SidecarFragment
 * JD-Core Version:    0.6.2
 */