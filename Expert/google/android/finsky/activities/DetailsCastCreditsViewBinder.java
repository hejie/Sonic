package com.google.android.finsky.activities;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.remoting.protos.DocDetails.VideoCredit;
import java.util.Iterator;
import java.util.List;

public class DetailsCastCreditsViewBinder extends DetailsViewBinder
{
  private List<DocDetails.VideoCredit> mCreditsList;

  private void populateContent()
  {
    ViewGroup localViewGroup1 = (ViewGroup)this.mLayout.findViewById(2131230932);
    localViewGroup1.removeAllViews();
    Iterator localIterator = this.mCreditsList.iterator();
    while (localIterator.hasNext())
    {
      DocDetails.VideoCredit localVideoCredit = (DocDetails.VideoCredit)localIterator.next();
      ViewGroup localViewGroup2 = (ViewGroup)this.mInflater.inflate(2130968615, localViewGroup1, false);
      ((TextView)localViewGroup2.findViewById(2131230843)).setText(localVideoCredit.getCredit().toUpperCase());
      ((TextView)localViewGroup2.findViewById(2131230844)).setText(TextUtils.join(", ", localVideoCredit.getNameList()));
      localViewGroup1.addView(localViewGroup2);
    }
  }

  public void bind(View paramView, Document paramDocument, boolean paramBoolean)
  {
    super.bind(paramView, paramDocument, 2131165497);
    this.mCreditsList = paramDocument.getCreditsList();
    if ((!paramBoolean) || (this.mCreditsList == null) || (this.mCreditsList.size() == 0))
      this.mLayout.setVisibility(8);
    while (true)
    {
      return;
      this.mLayout.setVisibility(0);
      populateContent();
    }
  }

  public void onDestroyView()
  {
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsCastCreditsViewBinder
 * JD-Core Version:    0.6.2
 */