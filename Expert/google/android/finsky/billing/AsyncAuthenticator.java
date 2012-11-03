package com.google.android.finsky.billing;

import android.os.AsyncTask;
import android.util.Pair;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.Authenticator;

public class AsyncAuthenticator
{
  private final Authenticator mAuthenticator;

  public AsyncAuthenticator(Authenticator paramAuthenticator)
  {
    this.mAuthenticator = paramAuthenticator;
  }

  public void getToken(final Listener paramListener, final String paramString)
  {
    new AsyncTask()
    {
      protected Pair<String, AuthFailureError> doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        if (paramString != null)
          AsyncAuthenticator.this.mAuthenticator.invalidateAuthToken(paramString);
        try
        {
          Pair localPair2 = Pair.create(AsyncAuthenticator.this.mAuthenticator.getAuthToken(), null);
          localPair1 = localPair2;
          return localPair1;
        }
        catch (AuthFailureError localAuthFailureError)
        {
          while (true)
            Pair localPair1 = Pair.create(null, localAuthFailureError);
        }
      }

      protected void onPostExecute(Pair<String, AuthFailureError> paramAnonymousPair)
      {
        if (paramAnonymousPair.first != null)
          paramListener.onAuthTokenReceived((String)paramAnonymousPair.first);
        while (true)
        {
          return;
          paramListener.onError((AuthFailureError)paramAnonymousPair.second);
        }
      }
    }
    .execute(new Void[0]);
  }

  public static abstract interface Listener
  {
    public abstract void onAuthTokenReceived(String paramString);

    public abstract void onError(AuthFailureError paramAuthFailureError);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.AsyncAuthenticator
 * JD-Core Version:    0.6.2
 */