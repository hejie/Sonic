package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.previews.SongSnippetStatusListener;
import com.google.android.finsky.previews.StatusListener;
import com.google.android.finsky.remoting.protos.DocDetails.SongDetails;
import com.google.android.finsky.utils.LibraryUtils;

public class FreeSongOfTheDaySummary extends LinearLayout
{
  private Button mBuyButton;
  private final PreviewController mConnection = new PreviewController(this.mStatusListener);
  private TextView mCreator;
  private SongIndex mSongIndex;
  private final StatusListener mStatusListener = new SongSnippetStatusListener()
  {
    protected void update(int paramAnonymousInt, boolean paramAnonymousBoolean)
    {
      FreeSongOfTheDaySummary.this.mSongIndex.setState(paramAnonymousInt);
      FreeSongOfTheDaySummary.this.setHighlighted(paramAnonymousBoolean);
    }
  };
  private TextView mTitle;

  public FreeSongOfTheDaySummary(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setHighlighted(boolean paramBoolean)
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = getPaddingLeft();
    arrayOfInt[1] = getPaddingTop();
    arrayOfInt[2] = getPaddingRight();
    arrayOfInt[3] = getPaddingBottom();
    Resources localResources = getContext().getResources();
    if (paramBoolean)
    {
      setBackgroundColor(localResources.getColor(2131361843));
      this.mCreator.setTextColor(localResources.getColor(17170443));
    }
    while (true)
    {
      setPadding(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
      return;
      setBackgroundResource(2130837768);
      this.mCreator.setTextColor(localResources.getColor(2131361798));
    }
  }

  protected void onDetachedFromWindow()
  {
    this.mConnection.unbind();
    super.onDetachedFromWindow();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mTitle = ((TextView)findViewById(2131230957));
    this.mCreator = ((TextView)findViewById(2131230958));
    this.mBuyButton = ((Button)findViewById(2131230897));
    this.mSongIndex = ((SongIndex)findViewById(2131231050));
  }

  public void showSummary(final Document paramDocument, final NavigationManager paramNavigationManager, String paramString1, String paramString2)
  {
    this.mTitle.setText(paramDocument.getTitle());
    this.mCreator.setText(paramDocument.getCreator());
    Account localAccount1 = FinskyApp.get().getCurrentAccount();
    final Account localAccount2 = LibraryUtils.getOwnerWithCurrentAccount(paramDocument, FinskyApp.get().getLibraries(), localAccount1);
    if (localAccount2 != null)
    {
      this.mBuyButton.setText(2131165489);
      this.mBuyButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramNavigationManager.openItem(localAccount2, paramDocument);
        }
      });
    }
    while (true)
    {
      this.mSongIndex.setState(3);
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          FreeSongOfTheDaySummary.this.mConnection.togglePlayback(this.val$song);
        }
      });
      PreviewController.setupOnBackStackChangedListener(paramNavigationManager);
      return;
      this.mBuyButton.setText(paramDocument.getFormattedPrice(1));
      this.mBuyButton.setOnClickListener(paramNavigationManager.getBuyImmediateClickListener(localAccount1, paramDocument, 1, paramString1, paramString2, null));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.FreeSongOfTheDaySummary
 * JD-Core Version:    0.6.2
 */