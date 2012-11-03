package com.google.android.finsky.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.utils.FinskyLog;

public class DownloadBroadcastReceiver extends BroadcastReceiver
{
  private static DownloadQueueImpl sDownloadQueueImpl;

  private int getHttpStatusForUri(Uri paramUri)
  {
    Cursor localCursor = sDownloadQueueImpl.getDownloadManager().queryStatus(paramUri);
    int i;
    if ((localCursor == null) || (localCursor.getCount() != 1))
      i = -1;
    while (true)
    {
      return i;
      localCursor.moveToFirst();
      i = localCursor.getInt(0);
      localCursor.close();
    }
  }

  public static void initialize(DownloadQueueImpl paramDownloadQueueImpl)
  {
    sDownloadQueueImpl = paramDownloadQueueImpl;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    FinskyLog.d("Intent received at DownloadBroadcastReceiver", new Object[0]);
    Uri localUri1 = paramIntent.getData();
    String str1 = paramIntent.getAction();
    if (localUri1 == null)
    {
      long l = paramIntent.getLongExtra("extra_download_id", -1L);
      if (l == -1L)
      {
        long[] arrayOfLong = paramIntent.getLongArrayExtra("extra_click_download_ids");
        if ((arrayOfLong != null) && (arrayOfLong.length == 1))
          l = arrayOfLong[0];
      }
      if (l != -1L)
        localUri1 = Uri.parse(DownloadManagerConstants.getContentUriString(String.valueOf(l)));
    }
    label213: 
    while (true)
    {
      final Uri localUri2 = localUri1;
      final String str2 = str1;
      if (sDownloadQueueImpl.getDownloadByContentUri(localUri2) == null)
      {
        FinskyLog.d("DownloadBroadcastReceiver could not find %s in queue.", new Object[] { localUri2 });
        if (str2.equals("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED"))
        {
          FinskyApp localFinskyApp = FinskyApp.get();
          if (FinskyApp.get().getCurrentAccount() != null)
          {
            Intent localIntent = MainActivity.getMyDownloadsIntent(localFinskyApp);
            localIntent.setFlags(268435456);
            localFinskyApp.startActivity(localIntent);
          }
        }
      }
      while (true)
      {
        return;
        if (!"android.intent.action.DOWNLOAD_COMPLETED".equals(str1))
          break label213;
        str1 = "android.intent.action.DOWNLOAD_COMPLETE";
        break;
        new AsyncTask()
        {
          protected Integer doInBackground(Uri[] paramAnonymousArrayOfUri)
          {
            if (paramAnonymousArrayOfUri.length < 1);
            for (int i = -1; ; i = DownloadBroadcastReceiver.this.getHttpStatusForUri(paramAnonymousArrayOfUri[0]))
              return Integer.valueOf(i);
          }

          protected void onPostExecute(Integer paramAnonymousInteger)
          {
            InternalDownload localInternalDownload = (InternalDownload)DownloadBroadcastReceiver.sDownloadQueueImpl.getDownloadByContentUri(localUri2);
            if (localInternalDownload == null)
              FinskyLog.d("DownloadBroadcastReceiver could not find download in queue.", new Object[0]);
            while (true)
            {
              return;
              if (paramAnonymousInteger.intValue() != -1)
                localInternalDownload.setHttpStatus(paramAnonymousInteger.intValue());
              while (true)
              {
                if (!str2.equals("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED"))
                  break label80;
                DownloadBroadcastReceiver.sDownloadQueueImpl.notifyClicked(localInternalDownload);
                break;
                FinskyLog.e("DownloadBroadcastReceiver received invalid HTTP status of -1", new Object[0]);
              }
              label80: if (str2.equals("android.intent.action.DOWNLOAD_COMPLETE"))
              {
                if (localInternalDownload.isCompleted())
                {
                  Object[] arrayOfObject = new Object[3];
                  arrayOfObject[0] = paramAnonymousInteger;
                  arrayOfObject[1] = localInternalDownload;
                  arrayOfObject[2] = localInternalDownload.getState();
                  FinskyLog.d("Received ACTION_DOWNLOAD_COMPLETE %d for %s - dropping because already in state %s.", arrayOfObject);
                }
                else if (DownloadManagerConstants.isStatusSuccess(paramAnonymousInteger.intValue()))
                {
                  DownloadBroadcastReceiver.sDownloadQueueImpl.setDownloadState(localInternalDownload, Download.DownloadState.SUCCESS);
                }
                else
                {
                  DownloadBroadcastReceiver.sDownloadQueueImpl.setDownloadState(localInternalDownload, Download.DownloadState.ERROR);
                }
              }
              else
                FinskyLog.wtf("Invalid DownloadBroadcastReceiver intent", new Object[0]);
            }
          }
        }
        .execute(new Uri[] { localUri2 });
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadBroadcastReceiver
 * JD-Core Version:    0.6.2
 */