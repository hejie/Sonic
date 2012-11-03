package com.google.android.finsky.adapters;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.finsky.receivers.Installer.InstallerProgressReport;
import com.google.android.finsky.receivers.Installer.InstallerState;

public class DownloadProgressHelper
{
  private static CharSequence sDownloadStatusFormatBytes;
  private static CharSequence sDownloadStatusFormatPercents;

  public static void configureDownloadProgressUi(Context paramContext, Installer.InstallerProgressReport paramInstallerProgressReport, TextView paramTextView1, TextView paramTextView2, ProgressBar paramProgressBar)
  {
    initializeStrings(paramContext);
    paramTextView2.setText(" ");
    paramTextView1.setText(" ");
    if (paramInstallerProgressReport.installerState != Installer.InstallerState.DOWNLOADING)
    {
      paramProgressBar.setIndeterminate(true);
      if (paramInstallerProgressReport.installerState == Installer.InstallerState.INSTALLING)
        paramTextView1.setText(2131165597);
    }
    while (true)
    {
      return;
      int i;
      label82: int j;
      if ((paramInstallerProgressReport.bytesCompleted > 0L) && (paramInstallerProgressReport.bytesTotal > 0L) && (paramInstallerProgressReport.bytesCompleted <= paramInstallerProgressReport.bytesTotal))
      {
        i = 1;
        j = 0;
        if (i == 0)
          break label148;
        j = (int)(100L * paramInstallerProgressReport.bytesCompleted / paramInstallerProgressReport.bytesTotal);
        paramProgressBar.setIndeterminate(false);
        paramProgressBar.setProgress(j);
      }
      int k;
      while (true)
      {
        k = paramInstallerProgressReport.downloadStatus;
        if (k != 195)
          break label157;
        paramTextView1.setText(2131165605);
        break;
        i = 0;
        break label82;
        label148: paramProgressBar.setIndeterminate(true);
      }
      label157: if (k == 196)
      {
        paramTextView1.setText(2131165606);
      }
      else if (i != 0)
      {
        CharSequence localCharSequence1 = sDownloadStatusFormatPercents;
        CharSequence[] arrayOfCharSequence1 = new CharSequence[1];
        arrayOfCharSequence1[0] = Integer.toString(j);
        paramTextView2.setText(TextUtils.expandTemplate(localCharSequence1, arrayOfCharSequence1));
        CharSequence localCharSequence2 = sDownloadStatusFormatBytes;
        CharSequence[] arrayOfCharSequence2 = new CharSequence[2];
        arrayOfCharSequence2[0] = Formatter.formatFileSize(paramContext, paramInstallerProgressReport.bytesCompleted);
        arrayOfCharSequence2[1] = Formatter.formatFileSize(paramContext, paramInstallerProgressReport.bytesTotal);
        paramTextView1.setText(TextUtils.expandTemplate(localCharSequence2, arrayOfCharSequence2));
      }
      else
      {
        paramTextView1.setText(2131165604);
      }
    }
  }

  private static void initializeStrings(Context paramContext)
  {
    if (sDownloadStatusFormatPercents == null)
      sDownloadStatusFormatPercents = Html.fromHtml(paramContext.getString(2131165594));
    if (sDownloadStatusFormatBytes == null)
      sDownloadStatusFormatBytes = Html.fromHtml(paramContext.getString(2131165595));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.DownloadProgressHelper
 * JD-Core Version:    0.6.2
 */