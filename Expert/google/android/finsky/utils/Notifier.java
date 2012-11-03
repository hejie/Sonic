package com.google.android.finsky.utils;

import android.content.Intent;
import com.google.android.finsky.api.model.Document;
import java.util.List;

public abstract interface Notifier
{
  public abstract void hideAllMessagesForAccount(String paramString);

  public abstract void hideAllMessagesForPackage(String paramString);

  public abstract void setNotificationListener(NotificationListener paramNotificationListener);

  public abstract void showDownloadErrorMessage(String paramString1, String paramString2, int paramInt, String paramString3, boolean paramBoolean);

  public abstract void showExternalStorageMissing(String paramString1, String paramString2);

  public abstract void showInstallationFailureMessage(String paramString1, String paramString2, String paramString3, int paramInt);

  public abstract void showInstallingMessage(String paramString1, String paramString2, boolean paramBoolean);

  public abstract void showInternalStorageFull(String paramString1, String paramString2);

  public abstract void showMaliciousAssetRemovedMessage(String paramString1, String paramString2);

  public abstract void showMessage(String paramString1, String paramString2, String paramString3);

  public abstract void showNormalAssetRemovedMessage(String paramString1, String paramString2);

  public abstract void showNotification(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, Intent paramIntent);

  public abstract void showPurchaseErrorMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract void showSingleUpdateAvailableMessage(Document paramDocument);

  public abstract void showSubscriptionsWarningMessage(String paramString1, String paramString2, String paramString3);

  public abstract void showSuccessfulInstallMessage(String paramString1, String paramString2, String paramString3, boolean paramBoolean);

  public abstract void showUpdatesAvailableMessage(List<Document> paramList, int paramInt);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.Notifier
 * JD-Core Version:    0.6.2
 */