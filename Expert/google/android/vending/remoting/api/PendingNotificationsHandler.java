package com.google.android.vending.remoting.api;

import android.content.Context;
import com.google.android.vending.remoting.protos.VendingProtos.PendingNotificationsProto;

public abstract interface PendingNotificationsHandler
{
  public abstract boolean handlePendingNotifications(Context paramContext, String paramString, VendingProtos.PendingNotificationsProto paramPendingNotificationsProto, boolean paramBoolean);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.remoting.api.PendingNotificationsHandler
 * JD-Core Version:    0.6.2
 */