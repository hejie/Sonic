package com.google.android.vending.remoting.api;

import android.accounts.Account;
import android.util.Base64;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.Maps;
import com.google.android.vending.remoting.protos.VendingProtos.PendingNotificationsProto;
import com.google.android.vending.remoting.protos.VendingProtos.RequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.RequestProto.Request;
import com.google.android.vending.remoting.protos.VendingProtos.ResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.ResponseProto.Response;
import com.google.android.volley.MicroProtoHelper;
import com.google.protobuf.micro.MessageMicro;
import java.util.HashMap;
import java.util.Map;

public class VendingRequest<T extends MessageMicro, U extends MessageMicro> extends Request<VendingProtos.ResponseProto>
{
  protected final VendingApiContext mApiContext;
  private boolean mAvoidBulkCancel = false;
  private Map<String, String> mExtraHeaders;
  private final Response.Listener<U> mListener;
  private final T mRequest;
  private final Class<T> mRequestClass;
  private final Class<U> mResponseClass;
  private final boolean mUseSecureAuthToken;

  protected VendingRequest(String paramString, Class<T> paramClass, T paramT, Class<U> paramClass1, Response.Listener<U> paramListener, VendingApiContext paramVendingApiContext, Response.ErrorListener paramErrorListener)
  {
    super(paramString, paramErrorListener);
    this.mUseSecureAuthToken = paramString.startsWith("https");
    this.mRequest = paramT;
    this.mRequestClass = paramClass;
    this.mResponseClass = paramClass1;
    this.mListener = paramListener;
    this.mApiContext = paramVendingApiContext;
    setRetryPolicy(new VendingRetryPolicy(this.mApiContext, this.mUseSecureAuthToken));
  }

  public static <T extends MessageMicro, U extends MessageMicro> VendingRequest<T, U> make(String paramString, Class<T> paramClass, T paramT, Class<U> paramClass1, Response.Listener<U> paramListener, VendingApiContext paramVendingApiContext, Response.ErrorListener paramErrorListener)
  {
    return new VendingRequest(paramString, paramClass, paramT, paramClass1, paramListener, paramVendingApiContext, paramErrorListener);
  }

  public void addExtraHeader(String paramString1, String paramString2)
  {
    if (this.mExtraHeaders == null)
      this.mExtraHeaders = Maps.newHashMap();
    this.mExtraHeaders.put(paramString1, paramString2);
  }

  public void deliverError(VolleyError paramVolleyError)
  {
    if ((paramVolleyError instanceof AuthFailureError))
      this.mApiContext.invalidateAuthToken(this.mUseSecureAuthToken);
    super.deliverError(paramVolleyError);
  }

  protected void deliverResponse(VendingProtos.ResponseProto paramResponseProto)
  {
    MessageMicro localMessageMicro = MicroProtoHelper.getParsedResponseFromWrapper(paramResponseProto.getResponse(0), VendingProtos.ResponseProto.Response.class, this.mResponseClass);
    this.mListener.onResponse(localMessageMicro);
  }

  public boolean getAvoidBulkCancel()
  {
    return this.mAvoidBulkCancel;
  }

  public Map<String, String> getHeaders()
  {
    Object localObject = this.mApiContext.getHeaders();
    if ((this.mExtraHeaders != null) && (!this.mExtraHeaders.isEmpty()))
    {
      HashMap localHashMap = Maps.newHashMap();
      localHashMap.putAll((Map)localObject);
      localHashMap.putAll(this.mExtraHeaders);
      localObject = localHashMap;
    }
    return localObject;
  }

  public Map<String, String> getPostParams()
    throws AuthFailureError
  {
    HashMap localHashMap = Maps.newHashMap();
    localHashMap.put("request", serializeRequestProto(this.mRequest));
    localHashMap.put("version", String.valueOf(2));
    return localHashMap;
  }

  protected boolean handlePendingNotifications(VendingProtos.ResponseProto paramResponseProto, boolean paramBoolean)
  {
    boolean bool = false;
    if (paramResponseProto.hasPendingNotifications())
    {
      VendingProtos.PendingNotificationsProto localPendingNotificationsProto = paramResponseProto.getPendingNotifications();
      PendingNotificationsHandler localPendingNotificationsHandler = FinskyApp.get().getPendingNotificationsHandler();
      if ((localPendingNotificationsHandler != null) && (localPendingNotificationsHandler.handlePendingNotifications(FinskyApp.get().getApplicationContext(), this.mApiContext.getAccount().name, localPendingNotificationsProto, paramBoolean)))
        bool = true;
    }
    return bool;
  }

  // ERROR //
  protected com.android.volley.Response<VendingProtos.ResponseProto> parseNetworkResponse(com.android.volley.NetworkResponse paramNetworkResponse)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 186	java/util/zip/GZIPInputStream
    //   5: dup
    //   6: new 188	java/io/ByteArrayInputStream
    //   9: dup
    //   10: aload_1
    //   11: getfield 194	com/android/volley/NetworkResponse:data	[B
    //   14: invokespecial 197	java/io/ByteArrayInputStream:<init>	([B)V
    //   17: aload_1
    //   18: getfield 194	com/android/volley/NetworkResponse:data	[B
    //   21: arraylength
    //   22: invokespecial 200	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;I)V
    //   25: astore_3
    //   26: aload_3
    //   27: invokestatic 206	com/google/protobuf/micro/CodedInputStreamMicro:newInstance	(Ljava/io/InputStream;)Lcom/google/protobuf/micro/CodedInputStreamMicro;
    //   30: invokestatic 210	com/google/android/vending/remoting/protos/VendingProtos$ResponseProto:parseFrom	(Lcom/google/protobuf/micro/CodedInputStreamMicro;)Lcom/google/android/vending/remoting/protos/VendingProtos$ResponseProto;
    //   33: astore 10
    //   35: aload 10
    //   37: invokevirtual 214	com/google/android/vending/remoting/protos/VendingProtos$ResponseProto:getResponseCount	()I
    //   40: iconst_1
    //   41: if_icmpeq +30 -> 71
    //   44: new 216	com/android/volley/ServerError
    //   47: dup
    //   48: invokespecial 219	com/android/volley/ServerError:<init>	()V
    //   51: invokestatic 225	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   54: astore 16
    //   56: aload 16
    //   58: astore 8
    //   60: aload_3
    //   61: ifnull +7 -> 68
    //   64: aload_3
    //   65: invokevirtual 230	java/io/InputStream:close	()V
    //   68: aload 8
    //   70: areturn
    //   71: aload 10
    //   73: iconst_0
    //   74: invokevirtual 100	com/google/android/vending/remoting/protos/VendingProtos$ResponseProto:getResponse	(I)Lcom/google/android/vending/remoting/protos/VendingProtos$ResponseProto$Response;
    //   77: invokevirtual 234	com/google/android/vending/remoting/protos/VendingProtos$ResponseProto$Response:getResponseProperties	()Lcom/google/android/vending/remoting/protos/VendingProtos$ResponsePropertiesProto;
    //   80: invokevirtual 239	com/google/android/vending/remoting/protos/VendingProtos$ResponsePropertiesProto:getResult	()I
    //   83: ifeq +30 -> 113
    //   86: new 216	com/android/volley/ServerError
    //   89: dup
    //   90: invokespecial 219	com/android/volley/ServerError:<init>	()V
    //   93: invokestatic 225	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   96: astore 14
    //   98: aload 14
    //   100: astore 8
    //   102: aload_3
    //   103: ifnull +7 -> 110
    //   106: aload_3
    //   107: invokevirtual 230	java/io/InputStream:close	()V
    //   110: goto -42 -> 68
    //   113: aload_0
    //   114: aload 10
    //   116: iconst_1
    //   117: invokevirtual 241	com/google/android/vending/remoting/api/VendingRequest:handlePendingNotifications	(Lcom/google/android/vending/remoting/protos/VendingProtos$ResponseProto;Z)Z
    //   120: pop
    //   121: aload 10
    //   123: aconst_null
    //   124: invokestatic 245	com/android/volley/Response:success	(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
    //   127: astore 12
    //   129: aload 12
    //   131: astore 8
    //   133: aload_3
    //   134: ifnull +7 -> 141
    //   137: aload_3
    //   138: invokevirtual 230	java/io/InputStream:close	()V
    //   141: goto -73 -> 68
    //   144: astore 4
    //   146: new 247	java/lang/StringBuilder
    //   149: dup
    //   150: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   153: ldc 250
    //   155: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload 4
    //   160: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   163: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   166: iconst_0
    //   167: anewarray 263	java/lang/Object
    //   170: invokestatic 269	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   173: new 271	com/android/volley/VolleyError
    //   176: dup
    //   177: invokespecial 272	com/android/volley/VolleyError:<init>	()V
    //   180: invokestatic 225	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   183: astore 7
    //   185: aload 7
    //   187: astore 8
    //   189: aload_2
    //   190: ifnull -122 -> 68
    //   193: aload_2
    //   194: invokevirtual 230	java/io/InputStream:close	()V
    //   197: goto -129 -> 68
    //   200: astore 9
    //   202: goto -134 -> 68
    //   205: astore 5
    //   207: aload_2
    //   208: ifnull +7 -> 215
    //   211: aload_2
    //   212: invokevirtual 230	java/io/InputStream:close	()V
    //   215: aload 5
    //   217: athrow
    //   218: astore 17
    //   220: goto -152 -> 68
    //   223: astore 15
    //   225: goto -115 -> 110
    //   228: astore 13
    //   230: goto -89 -> 141
    //   233: astore 6
    //   235: goto -20 -> 215
    //   238: astore 5
    //   240: aload_3
    //   241: astore_2
    //   242: goto -35 -> 207
    //   245: astore 4
    //   247: aload_3
    //   248: astore_2
    //   249: goto -103 -> 146
    //
    // Exception table:
    //   from	to	target	type
    //   2	26	144	java/io/IOException
    //   193	197	200	java/io/IOException
    //   2	26	205	finally
    //   146	185	205	finally
    //   64	68	218	java/io/IOException
    //   106	110	223	java/io/IOException
    //   137	141	228	java/io/IOException
    //   211	215	233	java/io/IOException
    //   26	56	238	finally
    //   71	98	238	finally
    //   113	129	238	finally
    //   26	56	245	java/io/IOException
    //   71	98	245	java/io/IOException
    //   113	129	245	java/io/IOException
  }

  String serializeRequestProto(T paramT)
    throws AuthFailureError
  {
    VendingProtos.RequestProto.Request localRequest = new VendingProtos.RequestProto.Request();
    MicroProtoHelper.setRequestInWrapper(localRequest, VendingProtos.RequestProto.Request.class, paramT, this.mRequestClass);
    VendingProtos.RequestProto localRequestProto = new VendingProtos.RequestProto();
    localRequestProto.setRequestProperties(this.mApiContext.getRequestProperties(this.mUseSecureAuthToken));
    localRequestProto.addRequest(localRequest);
    return Base64.encodeToString(localRequestProto.toByteArray(), 11);
  }

  public void setAvoidBulkCancel()
  {
    this.mAvoidBulkCancel = true;
  }

  public String toString()
  {
    return super.toString() + " " + this.mRequestClass.getSimpleName();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.remoting.api.VendingRequest
 * JD-Core Version:    0.6.2
 */