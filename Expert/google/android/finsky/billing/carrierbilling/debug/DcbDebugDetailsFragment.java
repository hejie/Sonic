package com.google.android.finsky.billing.carrierbilling.debug;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DcbDebugDetailsFragment extends DialogFragment
{
  private final DcbDetailExtractor mDetailExtractor;
  private final String mTitle;

  public DcbDebugDetailsFragment(DcbDetailExtractor paramDcbDetailExtractor, String paramString)
  {
    this.mDetailExtractor = paramDcbDetailExtractor;
    this.mTitle = paramString;
  }

  private View buildView(AlertDialog paramAlertDialog)
  {
    ListView localListView = new ListView(paramAlertDialog.getContext());
    Collection localCollection = this.mDetailExtractor.getDetails();
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = localCollection.iterator();
    while (localIterator.hasNext())
      localArrayList.add(new DetailFormatter((DcbDetail)localIterator.next()));
    localListView.setAdapter(new ArrayAdapter(paramAlertDialog.getContext(), 2130968629, localArrayList));
    return localListView;
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
    localBuilder.setPositiveButton(2131165599, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.setCancelable(false);
    localBuilder.setTitle(this.mTitle);
    AlertDialog localAlertDialog = localBuilder.create();
    localAlertDialog.setView(buildView(localAlertDialog));
    return localAlertDialog;
  }

  private static class DetailFormatter
  {
    private final String mString;

    public DetailFormatter(DcbDetail paramDcbDetail)
    {
      this.mString = (paramDcbDetail.getTitle() + ": " + paramDcbDetail.getValue());
    }

    public String toString()
    {
      return this.mString;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.debug.DcbDebugDetailsFragment
 * JD-Core Version:    0.6.2
 */