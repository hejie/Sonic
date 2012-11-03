package com.google.android.finsky.activities;

import android.accounts.Account;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DetailsButtonColumnLayout;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocDetails.AlbumDetails;
import com.google.android.finsky.remoting.protos.DocDetails.ArtistDetails;
import com.google.android.finsky.utils.BadgeUtils;

public class DetailsSummaryMusicViewBinder extends DetailsSummaryViewBinder
{
  public DetailsSummaryMusicViewBinder(DfeToc paramDfeToc, Account paramAccount)
  {
    super(paramDfeToc, paramAccount);
  }

  private void goToArtistPage()
  {
    String str = this.mDoc.getAlbumDetails().getDisplayArtist().getDetailsUrl();
    this.mNavigationManager.goToDocPage(str, null, this.mDoc.getDetailsUrl(), null, null);
  }

  private boolean hasArtistLink()
  {
    if ((this.mDoc.getAlbumDetails() != null) && (this.mDoc.getAlbumDetails().getDisplayArtist() != null) && (!TextUtils.isEmpty(this.mDoc.getAlbumDetails().getDisplayArtist().getDetailsUrl())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected void setupItemDetails()
  {
    super.setupItemDetails();
    View localView1 = findViewById(2131230963);
    if (localView1 != null)
    {
      if ((!hasArtistLink()) || (this.mNavigationManager == null))
        break label134;
      findViewById(2131230964).setVisibility(0);
      localView1.setFocusable(true);
      localView1.setBackgroundResource(2130837602);
      localView1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DetailsSummaryMusicViewBinder.this.goToArtistPage();
        }
      });
    }
    while (true)
    {
      DecoratedTextView localDecoratedTextView = (DecoratedTextView)findViewById(2131230965);
      BadgeUtils.configureTipperSticker(this.mDoc, localDecoratedTextView);
      if (localDecoratedTextView.getVisibility() == 0)
      {
        TextView localTextView = (TextView)findViewById(2131230957);
        localTextView.setSingleLine();
        localTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
      }
      View localView2 = findViewById(2131230920);
      if ((localView2 instanceof DetailsButtonColumnLayout))
        ((DetailsButtonColumnLayout)localView2).setMinimumRowCount(1);
      return;
      label134: localView1.setFocusable(false);
      localView1.setBackgroundResource(0);
      findViewById(2131230964).setVisibility(8);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsSummaryMusicViewBinder
 * JD-Core Version:    0.6.2
 */