package com.google.android.finsky.api;

import com.google.android.finsky.remoting.protos.Notifications.Notification;

public abstract interface DfeNotificationManager
{
  public abstract void processNotification(Notifications.Notification paramNotification);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.DfeNotificationManager
 * JD-Core Version:    0.6.2
 */