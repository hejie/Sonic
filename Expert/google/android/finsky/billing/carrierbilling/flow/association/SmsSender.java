package com.google.android.finsky.billing.carrierbilling.flow.association;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.text.TextUtils;
import com.google.android.finsky.utils.FinskyLog;

public class SmsSender
{
  private static SmsSendListener mListener;

  private static void dispatch(SmsSender.SmsSendListener.SmsSenderResult paramSmsSenderResult)
  {
    if (mListener == null);
    while (true)
    {
      return;
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          SmsSender.mListener.onResult(this.val$result);
        }
      });
    }
  }

  public static void send(Context paramContext, String paramString1, String paramString2, String paramString3, SmsSendListener paramSmsSendListener)
  {
    mListener = paramSmsSendListener;
    if (TextUtils.isEmpty(paramString1))
    {
      FinskyLog.w("Send sms failed invalid destination address", new Object[0]);
      dispatch(SmsSender.SmsSendListener.SmsSenderResult.RESULT_ERROR);
    }
    while (true)
    {
      return;
      PendingIntent localPendingIntent = null;
      if (paramSmsSendListener != null)
      {
        localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent("android.finsky.SMS_SENT"), 0);
        paramContext.registerReceiver(new BroadcastReceiver()
        {
          public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
          {
            paramAnonymousContext.unregisterReceiver(this);
            if (getResultCode() == -1)
              SmsSender.dispatch(SmsSender.SmsSendListener.SmsSenderResult.RESULT_OK);
            while (true)
            {
              return;
              FinskyLog.w("Error while sending sms " + getResultCode(), new Object[0]);
              SmsSender.dispatch(SmsSender.SmsSendListener.SmsSenderResult.RESULT_ERROR);
            }
          }
        }
        , new IntentFilter("android.finsky.SMS_SENT"));
      }
      SmsManager.getDefault().sendTextMessage(paramString1, paramString2, paramString3, localPendingIntent, null);
    }
  }

  public static abstract interface SmsSendListener
  {
    public abstract void onResult(SmsSenderResult paramSmsSenderResult);

    public static enum SmsSenderResult
    {
      static
      {
        RESULT_ERROR = new SmsSenderResult("RESULT_ERROR", 1);
        SmsSenderResult[] arrayOfSmsSenderResult = new SmsSenderResult[2];
        arrayOfSmsSenderResult[0] = RESULT_OK;
        arrayOfSmsSenderResult[1] = RESULT_ERROR;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.flow.association.SmsSender
 * JD-Core Version:    0.6.2
 */