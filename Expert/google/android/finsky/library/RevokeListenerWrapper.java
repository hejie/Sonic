package com.google.android.finsky.library;

import android.accounts.Account;
import com.android.volley.Response.Listener;
import com.google.android.finsky.remoting.protos.RevokeResponse;

public class RevokeListenerWrapper
  implements Response.Listener<RevokeResponse>
{
  private final Account mCurrentAccount;
  private final Response.Listener<RevokeResponse> mListener;
  private final LibraryReplicators mReplicators;

  public RevokeListenerWrapper(LibraryReplicators paramLibraryReplicators, Account paramAccount, Response.Listener<RevokeResponse> paramListener)
  {
    this.mReplicators = paramLibraryReplicators;
    this.mCurrentAccount = paramAccount;
    this.mListener = paramListener;
  }

  public void onResponse(RevokeResponse paramRevokeResponse)
  {
    if (paramRevokeResponse.hasLibraryUpdate())
      this.mReplicators.applyLibraryUpdate(this.mCurrentAccount, paramRevokeResponse.getLibraryUpdate(), "revoke");
    this.mListener.onResponse(paramRevokeResponse);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.RevokeListenerWrapper
 * JD-Core Version:    0.6.2
 */