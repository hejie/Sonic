package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeNotificationManager;
import com.google.android.finsky.remoting.protos.Notifications.Notification;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Notifier;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import java.util.Set;

public class ServerNotificationReceiver extends BroadcastReceiver
{
  private void tryHandleOldStyleNotification(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("server_notification_message");
    String str2 = paramIntent.getStringExtra("server_dialog_message");
    if ((str1 == null) || (str2 == null))
    {
      FinskyLog.d("Could not handle old style notification.", new Object[0]);
      return;
    }
    String str3;
    if (paramIntent.hasExtra("server_notification_status"))
    {
      str3 = paramIntent.getStringExtra("server_notification_status");
      label51: if (!paramIntent.hasExtra("server_notification_title"))
        break label107;
    }
    label107: for (String str4 = paramIntent.getStringExtra("server_notification_title"); ; str4 = paramContext.getString(2131165685))
    {
      FinskyApp.get().getNotifier().showMessage(str4, str3, str1);
      FinskyLog.d("Handled old style notification.", new Object[0]);
      break;
      str3 = paramContext.getString(2131165684);
      break label51;
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((!paramIntent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) || (!"google.com".equals(paramIntent.getStringExtra("from"))) || (!paramIntent.getCategories().contains("SERVER_NOTIFICATION")));
    while (true)
    {
      return;
      setResultCode(-1);
      Bundle localBundle = paramIntent.getExtras();
      if (localBundle.containsKey("NOTIFICATION_PAYLOAD"))
      {
        byte[] arrayOfByte = Base64.decode(localBundle.getString("NOTIFICATION_PAYLOAD"), 11);
        if (arrayOfByte != null)
        {
          Object localObject = null;
          try
          {
            Notifications.Notification localNotification = Notifications.Notification.parseFrom(arrayOfByte);
            localObject = localNotification;
            if (localObject == null)
              continue;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = localObject.getNotificationId();
            FinskyLog.d("Handling notificationId=[%s]", arrayOfObject);
            FinskyApp.get().getDfeNotificationManager().processNotification(localObject);
          }
          catch (InvalidProtocolBufferMicroException localInvalidProtocolBufferMicroException)
          {
            while (true)
              FinskyLog.e("Received download tickle with malformed notification proto data.", new Object[0]);
          }
        }
      }
      else
      {
        FinskyLog.d("Ignoring server broadcast due to empty notification string.", new Object[0]);
        tryHandleOldStyleNotification(paramContext, paramIntent);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.ServerNotificationReceiver
 * JD-Core Version:    0.6.2
 */