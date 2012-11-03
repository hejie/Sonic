package com.google.android.finsky.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.Html;
import android.text.TextUtils;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import java.util.List;

public class NotificationManager
  implements Notifier
{
  private static final boolean SUPPORTS_RICH_NOTIFICATIONS;
  private static final int UNKNOWN_PACKAGE_ID = "unknown package".hashCode();
  private final Context mContext;
  private NotificationListener mListener;

  static
  {
    if (Build.VERSION.SDK_INT >= 11);
    for (boolean bool = true; ; bool = false)
    {
      SUPPORTS_RICH_NOTIFICATIONS = bool;
      return;
    }
  }

  public NotificationManager(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static Intent createDefaultClickIntent(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, MainActivity.class);
    if (!TextUtils.isEmpty(paramString1))
    {
      localIntent = IntentUtils.createViewDocumentUrlIntent(paramContext, paramString4);
      localIntent.putExtra("error_doc_id", paramString1);
    }
    if (!TextUtils.isEmpty(paramString2))
      localIntent.putExtra("error_title", paramString2);
    if (!TextUtils.isEmpty(paramString3))
      localIntent.putExtra("error_html_message", paramString3);
    return localIntent;
  }

  private Bitmap drawableToBitmap(Drawable paramDrawable, String paramString)
  {
    try
    {
      Resources localResources = this.mContext.getResources();
      int i = localResources.getDimensionPixelSize(17104901);
      int j = localResources.getDimensionPixelSize(17104902);
      int k = paramDrawable.getIntrinsicWidth();
      int m = paramDrawable.getIntrinsicHeight();
      Bitmap localBitmap1;
      if ((k <= i) && (m <= j) && ((paramDrawable instanceof BitmapDrawable)))
      {
        Bitmap localBitmap2 = ((BitmapDrawable)paramDrawable).getBitmap();
        localBitmap1 = localBitmap2;
      }
      while (true)
      {
        return localBitmap1;
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = paramDrawable.getClass().getName();
        arrayOfObject[2] = Integer.valueOf(k);
        arrayOfObject[3] = Integer.valueOf(m);
        FinskyLog.w("Resource for %s is %s of size %d*%d", arrayOfObject);
        float f = Math.min(1.0F, Math.min(i / k, j / m));
        int n = (int)(f * k);
        int i1 = (int)(f * m);
        localBitmap1 = Bitmap.createBitmap(n, i1, Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap1);
        paramDrawable.setBounds(0, 0, n, i1);
        paramDrawable.draw(localCanvas);
      }
    }
    finally
    {
    }
  }

  private void showAppMessage(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    showAppNotificationOrAlert(paramString1, paramString2, paramString3, paramString4, paramInt, -1);
  }

  private void showAppNotificationOnly(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2)
  {
    if ((this.mListener == null) || (!this.mListener.showAppNotification(paramString1, paramString3, paramString4)))
    {
      Intent localIntent = createDefaultClickIntent(this.mContext, paramString1, null, null, DfeUtils.createDetailsUrlFromId(paramString1));
      localIntent.putExtra("error_return_code", paramInt2);
      showNotification(paramString1, paramString2, paramString3, paramString4, paramInt1, localIntent);
    }
  }

  private void showAppNotificationOrAlert(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2)
  {
    if ((this.mListener == null) || (!this.mListener.showAppAlert(paramString1, paramString3, paramString4, paramInt2)))
    {
      Intent localIntent = createDefaultClickIntent(this.mContext, paramString1, paramString3, paramString4, DfeUtils.createDetailsUrlFromId(paramString1));
      localIntent.putExtra("error_return_code", paramInt2);
      showNotification(paramString1, paramString2, paramString3, paramString4, paramInt1, localIntent);
    }
  }

  private void showDocNotification(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5)
  {
    if ((this.mListener == null) || (!this.mListener.showDocAlert(paramString1, paramString3, paramString4, paramString5)))
      showNotification(paramString1, paramString2, paramString3, paramString4, paramInt, createDefaultClickIntent(this.mContext, paramString1, paramString3, paramString4, paramString5));
  }

  private void showNewNotification(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, Bitmap paramBitmap, int paramInt2, Intent paramIntent)
  {
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this.mContext);
    localBuilder.setTicker(paramString2);
    localBuilder.setContentTitle(paramString3);
    localBuilder.setContentText(paramString4);
    if (!TextUtils.isEmpty(paramString5))
      localBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(paramString5));
    localBuilder.setSmallIcon(paramInt1);
    if (paramBitmap != null)
      localBuilder.setLargeIcon(paramBitmap);
    if (paramInt2 > 0)
      localBuilder.setNumber(paramInt2);
    localBuilder.setPriority(-1);
    if (paramString1 == null);
    for (int i = UNKNOWN_PACKAGE_ID; ; i = paramString1.hashCode())
    {
      localBuilder.setContentIntent(PendingIntent.getActivity(this.mContext, i, paramIntent, 1342177280));
      localBuilder.setAutoCancel(true);
      ((android.app.NotificationManager)this.mContext.getSystemService("notification")).notify(i, localBuilder.build());
      if (((Boolean)G.debugOptionsEnabled.get()).booleanValue())
      {
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = paramString1;
        arrayOfObject[1] = paramString3;
        arrayOfObject[2] = paramString4;
        arrayOfObject[3] = Integer.valueOf(paramIntent.getIntExtra("error_return_code", -1));
        FinskyLog.d("Showing notification: [ID=%s, Title=%s, Message=%s, returnCode=%d]", arrayOfObject);
      }
      return;
    }
  }

  public void hideAllMessagesForAccount(String paramString)
  {
    ((android.app.NotificationManager)this.mContext.getSystemService("notification")).cancel(("account:" + paramString).hashCode());
  }

  public void hideAllMessagesForPackage(String paramString)
  {
    android.app.NotificationManager localNotificationManager = (android.app.NotificationManager)this.mContext.getSystemService("notification");
    if (paramString == null);
    for (int i = UNKNOWN_PACKAGE_ID; ; i = paramString.hashCode())
    {
      localNotificationManager.cancel(i);
      return;
    }
  }

  public void setNotificationListener(NotificationListener paramNotificationListener)
  {
    this.mListener = paramNotificationListener;
  }

  public void showDownloadErrorMessage(String paramString1, String paramString2, int paramInt, String paramString3, boolean paramBoolean)
  {
    String str1;
    String str2;
    String str3;
    if (!paramBoolean)
    {
      str1 = this.mContext.getString(2131165345, new Object[] { paramString1 });
      str2 = this.mContext.getString(2131165346, new Object[] { paramString1 });
      if (paramString3 != null)
        str3 = this.mContext.getString(2131165348, new Object[] { paramString1, paramString3 });
    }
    while (true)
    {
      showAppMessage(paramString2, str1, str2, str3, 17301642);
      return;
      Context localContext2 = this.mContext;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramString1;
      arrayOfObject2[1] = Integer.valueOf(paramInt);
      str3 = localContext2.getString(2131165347, arrayOfObject2);
      continue;
      str1 = this.mContext.getString(2131165349, new Object[] { paramString1 });
      str2 = this.mContext.getString(2131165350, new Object[] { paramString1 });
      if (paramString3 != null)
      {
        str3 = this.mContext.getString(2131165352, new Object[] { paramString1, paramString3 });
      }
      else
      {
        Context localContext1 = this.mContext;
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = paramString1;
        arrayOfObject1[1] = Integer.valueOf(paramInt);
        str3 = localContext1.getString(2131165351, arrayOfObject1);
      }
    }
  }

  public void showExternalStorageMissing(String paramString1, String paramString2)
  {
    showAppMessage(paramString2, this.mContext.getString(2131165336, new Object[] { paramString1 }), this.mContext.getString(2131165337, new Object[] { paramString1 }), this.mContext.getString(2131165338, new Object[] { paramString1 }), 17301642);
  }

  public void showInstallationFailureMessage(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    showAppNotificationOrAlert(paramString2, paramString3, paramString1, paramString3, 17301642, paramInt);
  }

  public void showInstallingMessage(String paramString1, String paramString2, boolean paramBoolean)
  {
    Context localContext1 = this.mContext;
    int i;
    String str;
    Context localContext2;
    if (paramBoolean)
    {
      i = 2131165355;
      str = String.format(localContext1.getString(i), new Object[] { paramString1 });
      localContext2 = this.mContext;
      if (!paramBoolean)
        break label105;
    }
    label105: for (int j = 2131165356; ; j = 2131165354)
    {
      showNewNotification(paramString2, str, paramString1, String.format(localContext2.getString(j), new Object[] { paramString1 }), null, 17301633, null, 0, createDefaultClickIntent(this.mContext, paramString2, null, null, DfeUtils.createDetailsUrlFromId(paramString2)));
      return;
      i = 2131165353;
      break;
    }
  }

  public void showInternalStorageFull(String paramString1, String paramString2)
  {
    showAppMessage(paramString2, this.mContext.getString(2131165330, new Object[] { paramString1 }), this.mContext.getString(2131165331, new Object[] { paramString1 }), this.mContext.getString(2131165332, new Object[] { paramString1 }), 17301642);
  }

  public void showMaliciousAssetRemovedMessage(String paramString1, String paramString2)
  {
    showMessage(this.mContext.getString(2131165339, new Object[] { paramString1 }), this.mContext.getString(2131165340, new Object[] { paramString1 }), this.mContext.getString(2131165341, new Object[] { paramString1 }));
  }

  public void showMessage(String paramString1, String paramString2, String paramString3)
  {
    showNotification(null, paramString1, paramString1, paramString3, 17301642, createDefaultClickIntent(this.mContext, null, paramString1, paramString3, null));
  }

  public void showNormalAssetRemovedMessage(String paramString1, String paramString2)
  {
    showAppMessage(paramString2, this.mContext.getString(2131165342, new Object[] { paramString1 }), this.mContext.getString(2131165343, new Object[] { paramString1 }), this.mContext.getString(2131165344, new Object[] { paramString1 }), 17301642);
  }

  public void showNotification(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, Intent paramIntent)
  {
    String str = Html.fromHtml(paramString4).toString();
    if (paramString1 == null);
    for (int i = UNKNOWN_PACKAGE_ID; ; i = paramString1.hashCode())
    {
      PendingIntent localPendingIntent = PendingIntent.getActivity(this.mContext, i, paramIntent, 1342177280);
      Notification localNotification = new Notification(paramInt, paramString2, System.currentTimeMillis());
      localNotification.flags = (0x10 | localNotification.flags);
      localNotification.setLatestEventInfo(this.mContext, paramString3, str, localPendingIntent);
      android.app.NotificationManager localNotificationManager = (android.app.NotificationManager)this.mContext.getSystemService("notification");
      localNotification.setLatestEventInfo(this.mContext, paramString3, str, localPendingIntent);
      if ((Build.VERSION.SDK_INT >= 16) && ("updates".equals(paramString1)))
        localNotification.priority = -1;
      localNotificationManager.notify(i, localNotification);
      if (((Boolean)G.debugOptionsEnabled.get()).booleanValue())
      {
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = paramString1;
        arrayOfObject[1] = paramString3;
        arrayOfObject[2] = str;
        arrayOfObject[3] = Integer.valueOf(paramIntent.getIntExtra("error_return_code", -1));
        FinskyLog.d("Showing notification: [ID=%s, Title=%s, Message=%s, returnCode=%d]", arrayOfObject);
      }
      return;
    }
  }

  public void showPurchaseErrorMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    showDocNotification(paramString4, paramString2, paramString1, paramString3, 17301642, paramString5);
  }

  public void showSingleUpdateAvailableMessage(Document paramDocument)
  {
    Resources localResources = this.mContext.getResources();
    String str1 = localResources.getString(2131165591);
    String str2 = paramDocument.getTitle();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(1);
    showNewNotification("updates", str1, str2, localResources.getQuantityString(2131558401, 1, arrayOfObject), null, 2130837764, null, 0, IntentUtils.createViewDocumentUrlIntent(this.mContext, paramDocument.getDetailsUrl()));
  }

  public void showSubscriptionsWarningMessage(String paramString1, String paramString2, String paramString3)
  {
    showAppNotificationOnly(paramString2, paramString3, paramString1, paramString3, 17301642, 2);
  }

  public void showSuccessfulInstallMessage(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    Context localContext1 = this.mContext;
    int i;
    if (paramBoolean)
      i = 2131165360;
    while (true)
    {
      String str1 = String.format(localContext1.getString(i), new Object[] { paramString1 });
      Context localContext2 = this.mContext;
      int j;
      String str2;
      Intent localIntent;
      Object localObject;
      if (paramBoolean)
      {
        j = 2131165361;
        str2 = localContext2.getString(j);
        localIntent = this.mContext.getPackageManager().getLaunchIntentForPackage(paramString2);
        if (!TextUtils.isEmpty(paramString3))
        {
          str2 = this.mContext.getString(2131165359);
          localIntent = IntentUtils.createContinueUrlIntent(paramString2, paramString3);
        }
        if (localIntent == null)
          localIntent = createDefaultClickIntent(this.mContext, paramString2, null, null, DfeUtils.createDetailsUrlFromId(paramString2));
        localObject = null;
        if ((!SUPPORTS_RICH_NOTIFICATIONS) || (paramBoolean));
      }
      try
      {
        Drawable localDrawable = this.mContext.getPackageManager().getApplicationIcon(paramString2);
        if (localDrawable != null)
        {
          Bitmap localBitmap = drawableToBitmap(localDrawable, paramString2);
          localObject = localBitmap;
        }
        label166: showNewNotification(paramString2, str1, paramString1, str2, null, 2130837764, localObject, 0, localIntent);
        return;
        i = 2131165357;
        continue;
        j = 2131165358;
      }
      catch (Exception localException)
      {
        break label166;
      }
    }
  }

  public void showUpdatesAvailableMessage(List<Document> paramList, int paramInt)
  {
    Resources localResources = this.mContext.getResources();
    String str1 = localResources.getString(2131165591);
    String str2 = localResources.getString(2131165592);
    int i = paramList.size();
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(paramList.size());
    String str3 = localResources.getQuantityString(2131558401, i, arrayOfObject1);
    Intent localIntent = MainActivity.getMyDownloadsIntent(this.mContext);
    String str4;
    switch (paramList.size())
    {
    default:
      Object[] arrayOfObject6 = new Object[5];
      arrayOfObject6[0] = ((Document)paramList.get(0)).getTitle();
      arrayOfObject6[1] = ((Document)paramList.get(1)).getTitle();
      arrayOfObject6[2] = ((Document)paramList.get(2)).getTitle();
      arrayOfObject6[3] = ((Document)paramList.get(3)).getTitle();
      arrayOfObject6[4] = Integer.valueOf(-4 + paramList.size());
      str4 = localResources.getString(2131165832, arrayOfObject6);
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      Bitmap localBitmap = null;
      if (SUPPORTS_RICH_NOTIFICATIONS)
        localBitmap = drawableToBitmap(this.mContext.getResources().getDrawable(2130837765), Integer.toString(2130837765));
      showNewNotification("updates", str1, str2, str3, str4, 2130837764, localBitmap, paramInt, localIntent);
      return;
      Object[] arrayOfObject5 = new Object[2];
      arrayOfObject5[0] = ((Document)paramList.get(0)).getTitle();
      arrayOfObject5[1] = ((Document)paramList.get(1)).getTitle();
      str4 = localResources.getString(2131165828, arrayOfObject5);
      continue;
      Object[] arrayOfObject4 = new Object[3];
      arrayOfObject4[0] = ((Document)paramList.get(0)).getTitle();
      arrayOfObject4[1] = ((Document)paramList.get(1)).getTitle();
      arrayOfObject4[2] = ((Document)paramList.get(2)).getTitle();
      str4 = localResources.getString(2131165829, arrayOfObject4);
      continue;
      Object[] arrayOfObject3 = new Object[4];
      arrayOfObject3[0] = ((Document)paramList.get(0)).getTitle();
      arrayOfObject3[1] = ((Document)paramList.get(1)).getTitle();
      arrayOfObject3[2] = ((Document)paramList.get(2)).getTitle();
      arrayOfObject3[3] = ((Document)paramList.get(3)).getTitle();
      str4 = localResources.getString(2131165830, arrayOfObject3);
      continue;
      Object[] arrayOfObject2 = new Object[5];
      arrayOfObject2[0] = ((Document)paramList.get(0)).getTitle();
      arrayOfObject2[1] = ((Document)paramList.get(1)).getTitle();
      arrayOfObject2[2] = ((Document)paramList.get(2)).getTitle();
      arrayOfObject2[3] = ((Document)paramList.get(3)).getTitle();
      arrayOfObject2[4] = ((Document)paramList.get(4)).getTitle();
      str4 = localResources.getString(2131165831, arrayOfObject2);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.NotificationManager
 * JD-Core Version:    0.6.2
 */