package com.google.android.finsky.billing.creditcard;

import android.net.Uri;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.util.HashMap;
import java.util.Map;

public class EscrowRequest extends StringRequest
{
  private final String mCreditCardNumber;
  private final String mCvc;
  private final int mUserId;

  public EscrowRequest(int paramInt, String paramString1, String paramString2, Response.Listener<String> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(getAndCheckUrl(), paramListener, paramErrorListener);
    setRetryPolicy(new DefaultRetryPolicy(10000, 0, 0.0F));
    this.mUserId = paramInt;
    this.mCreditCardNumber = paramString1;
    this.mCvc = paramString2;
  }

  private static String getAndCheckUrl()
  {
    String str = (String)G.checkoutEscrowUrl.get() + "?s7e=cardNumber%3Bcvv";
    Uri localUri = Uri.parse(str);
    if ((!localUri.getScheme().equals("https")) && (!localUri.getHost().endsWith("corp.google.com")))
      throw new SecurityException("Unsafe escrow URL.");
    return str;
  }

  public Map<String, String> getPostParams()
  {
    HashMap localHashMap = Maps.newHashMap();
    localHashMap.put("gid", Integer.toString(this.mUserId));
    localHashMap.put("cardNumber", this.mCreditCardNumber);
    localHashMap.put("cvv", this.mCvc);
    return localHashMap;
  }

  protected Response<String> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    if (paramNetworkResponse.data.length == 0)
    {
      if (((Boolean)G.enableSensitiveLogging.get()).booleanValue())
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = this.mCreditCardNumber;
        arrayOfObject2[1] = this.mCvc;
        FinskyLog.w("Empty escrow handle for card number %s cvc %s", arrayOfObject2);
      }
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(this.mUserId);
      FinskyLog.wtf("Null response for Escrow string with id %d", arrayOfObject1);
    }
    for (Response localResponse = Response.error(new ServerError(paramNetworkResponse)); ; localResponse = super.parseNetworkResponse(paramNetworkResponse))
      return localResponse;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.creditcard.EscrowRequest
 * JD-Core Version:    0.6.2
 */