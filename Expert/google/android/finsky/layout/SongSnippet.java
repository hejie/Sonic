package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.previews.SongSnippetStatusListener;
import com.google.android.finsky.previews.StatusListener;
import com.google.android.finsky.remoting.protos.DocDetails.MusicDetails;
import com.google.android.finsky.remoting.protos.DocDetails.SongDetails;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.LibraryUtils;

public class SongSnippet extends CheckedRelativeLayout
{
  private ImageView mAddedDrawable;
  private TextView mAddedState;
  private Document mAlbumDocument;
  private BitmapLoader mBitmapLoader;
  private Button mBuyButton;
  private final PreviewController mConnection = new PreviewController(this.mStatusListener);
  private boolean mInitialized;
  private boolean mIsNewPurchase;
  private NavigationManager mNavigationManager;
  private boolean mShouldShowArtistName;
  private DocDetails.SongDetails mSongDetails;
  private Document mSongDocument;
  private TextView mSongDuration;
  private SongIndex mSongIndex;
  private DecoratedTextView mSongSubTitle;
  private TextView mSongTitle;
  private final StatusListener mStatusListener = new SongSnippetStatusListener()
  {
    private boolean isSameAsSnippetSong()
    {
      boolean bool = false;
      if (SongSnippet.this.mSongDetails == null);
      while (true)
      {
        return bool;
        DocDetails.SongDetails localSongDetails = SongSnippet.this.mConnection.getCurrentTrack();
        String str = SongSnippet.this.mSongDetails.getPreviewUrl();
        if ((localSongDetails != null) && (str != null) && (str.equals(localSongDetails.getPreviewUrl())))
          bool = true;
      }
    }

    protected void update(int paramAnonymousInt, boolean paramAnonymousBoolean)
    {
      SongSnippet.access$402(SongSnippet.this, true);
      if (!isSameAsSnippetSong())
        SongSnippet.this.resetUI();
      while (true)
      {
        return;
        SongSnippet.this.mSongIndex.setState(paramAnonymousInt);
        SongSnippet.this.setHighlighted(paramAnonymousBoolean);
      }
    }
  };
  private int mTrackNumber;

  public SongSnippet(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void clearBuyButtonStyle()
  {
    Resources localResources = getResources();
    this.mBuyButton.setBackgroundResource(0);
    this.mBuyButton.setPadding(0, 0, localResources.getDimensionPixelSize(2131427366), 0);
    this.mBuyButton.setTextColor(localResources.getColor(2131361798));
  }

  private void resetUI()
  {
    setHighlighted(false);
    this.mSongIndex.setState(0);
  }

  private void setBuyButtonStyle(int paramInt1, int paramInt2)
  {
    this.mBuyButton.setBackgroundResource(paramInt1);
    this.mBuyButton.setPadding(this.mBuyButton.getPaddingLeft(), 0, this.mBuyButton.getPaddingRight(), 0);
    this.mBuyButton.setTextColor(getResources().getColor(paramInt2));
  }

  private void setHighlighted(boolean paramBoolean)
  {
    Resources localResources = getContext().getResources();
    if (paramBoolean)
    {
      setBackgroundResource(2131361843);
      this.mSongTitle.setTextColor(localResources.getColor(17170443));
      this.mSongSubTitle.setTextColor(localResources.getColor(2131361794));
      this.mSongDuration.setTextColor(localResources.getColor(17170443));
    }
    while (true)
    {
      setSelected(paramBoolean);
      return;
      int i = getPaddingLeft();
      int j = getPaddingRight();
      int k = getPaddingTop();
      int m = getPaddingBottom();
      setBackgroundResource(2130837602);
      setPadding(i, k, j, m);
      this.mSongTitle.setTextColor(localResources.getColor(2131361797));
      this.mSongSubTitle.setTextColor(localResources.getColor(2131361798));
      this.mSongDuration.setTextColor(localResources.getColor(2131361798));
    }
  }

  private void updateAddedState()
  {
    if (this.mIsNewPurchase)
    {
      this.mSongDuration.setVisibility(8);
      this.mAddedState.setVisibility(0);
      this.mAddedDrawable.setVisibility(0);
    }
    while (true)
    {
      return;
      this.mSongDuration.setVisibility(0);
      this.mAddedState.setVisibility(8);
      this.mAddedDrawable.setVisibility(8);
    }
  }

  private void updateBuyButtonState()
  {
    this.mBuyButton.setVisibility(0);
    AccountLibrary localAccountLibrary = FinskyApp.get().getLibraries().getAccountLibrary(FinskyApp.get().getCurrentAccount());
    Account localAccount = FinskyApp.get().getCurrentAccount();
    Libraries localLibraries = FinskyApp.get().getLibraries();
    if (LibraryUtils.getOwnerWithCurrentAccount(this.mSongDocument, localLibraries, localAccount) != null)
    {
      setBuyButtonStyle(2130837575, 2131361867);
      this.mBuyButton.setText(2131165489);
      this.mBuyButton.setEnabled(true);
      Resources localResources = getContext().getResources();
      this.mBuyButton.setTextColor(localResources.getColorStateList(2131361868));
    }
    while (true)
    {
      updateAddedState();
      return;
      if (this.mSongDocument.getOffer(1) != null)
      {
        setBuyButtonStyle(2130837587, 2131361867);
        this.mBuyButton.setText(this.mSongDocument.getFormattedPrice(1));
        this.mBuyButton.setEnabled(true);
      }
      else if (!LibraryUtils.isAvailable(this.mSongDocument, FinskyApp.get().getToc(), localAccountLibrary))
      {
        clearBuyButtonStyle();
        this.mBuyButton.setEnabled(false);
        switch (this.mSongDocument.getAvailabilityRestriction())
        {
        default:
          this.mBuyButton.setVisibility(4);
          break;
        case 13:
          this.mBuyButton.setText(2131165758);
          break;
        }
      }
      else if ((this.mAlbumDocument != null) && (this.mAlbumDocument.getOffer(1) != null))
      {
        clearBuyButtonStyle();
        this.mBuyButton.setText(2131165758);
        this.mBuyButton.setEnabled(false);
      }
      else
      {
        this.mBuyButton.setVisibility(4);
      }
    }
  }

  public void initialize()
  {
    if (!this.mInitialized)
    {
      setState(1);
      this.mInitialized = true;
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mSongDetails = this.mSongDocument.getSongDetails();
    DocDetails.MusicDetails localMusicDetails = this.mSongDetails.getDetails();
    if (localMusicDetails == null)
    {
      setVisibility(8);
      return;
    }
    this.mSongIndex.setTrackNumber(this.mTrackNumber);
    this.mSongDuration.setText(DateUtils.formatElapsedTime(localMusicDetails.getDurationSec()));
    this.mSongTitle.setText(this.mSongDocument.getTitle());
    if (this.mShouldShowArtistName)
    {
      this.mSongSubTitle.setText(this.mSongDocument.getCreator());
      BadgeUtils.configureCreatorBadge(this.mSongDocument, this.mBitmapLoader, this.mSongSubTitle, -1);
    }
    while (true)
    {
      updateBuyButtonState();
      this.mBuyButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Account localAccount1 = FinskyApp.get().getCurrentAccount();
          Libraries localLibraries = FinskyApp.get().getLibraries();
          Account localAccount2 = LibraryUtils.getOwnerWithCurrentAccount(SongSnippet.this.mSongDocument, localLibraries, localAccount1);
          if (localAccount2 != null)
            SongSnippet.this.mNavigationManager.openItem(localAccount2, SongSnippet.this.mSongDocument);
          while (true)
          {
            return;
            SongSnippet.this.mNavigationManager.buy(localAccount1, SongSnippet.this.mSongDocument, 1, null, null, null);
          }
        }
      });
      this.mSongIndex.setClickable(false);
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SongSnippet.this.mConnection.togglePlayback(SongSnippet.this.mSongDetails);
        }
      });
      if (((Boolean)G.prePurchaseSharingEnabled.get()).booleanValue())
        setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            Intent localIntent = IntentUtils.buildShareIntent(SongSnippet.this.getContext(), SongSnippet.this.mSongDocument);
            Context localContext = SongSnippet.this.getContext();
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = SongSnippet.this.mSongDocument.getTitle();
            String str = localContext.getString(2131165582, arrayOfObject1);
            SongSnippet.this.getContext().startActivity(Intent.createChooser(localIntent, str));
            FinskyApp.get().getAnalytics().logPageView(null, null, "share?doc=" + SongSnippet.this.mSongDocument.getDocId());
            FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
            Object[] arrayOfObject2 = new Object[2];
            arrayOfObject2[0] = "cidi";
            arrayOfObject2[1] = SongSnippet.this.mSongDocument.getDocId();
            localFinskyEventLog.logTag("songShareLongPress", arrayOfObject2);
            return true;
          }
        });
      setContentDescription(this.mSongDocument.getTitle());
      this.mConnection.getStatusUpdate(this.mStatusListener);
      break;
      this.mSongSubTitle.setVisibility(8);
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
    this.mSongIndex = ((SongIndex)findViewById(2131231050));
    this.mBuyButton = ((Button)findViewById(2131230897));
    this.mSongDuration = ((TextView)findViewById(2131231093));
    this.mSongTitle = ((TextView)findViewById(2131231094));
    this.mSongSubTitle = ((DecoratedTextView)findViewById(2131231095));
    this.mAddedState = ((TextView)findViewById(2131231009));
    this.mAddedDrawable = ((ImageView)findViewById(2131230968));
  }

  public void setSongDetails(BitmapLoader paramBitmapLoader, Document paramDocument1, Document paramDocument2, int paramInt, boolean paramBoolean1, NavigationManager paramNavigationManager, boolean paramBoolean2)
  {
    this.mBitmapLoader = paramBitmapLoader;
    this.mShouldShowArtistName = paramBoolean1;
    this.mAlbumDocument = paramDocument1;
    this.mSongDocument = paramDocument2;
    this.mNavigationManager = paramNavigationManager;
    this.mTrackNumber = paramInt;
    this.mIsNewPurchase = paramBoolean2;
  }

  public void setState(int paramInt)
  {
    switch (paramInt)
    {
    default:
      this.mSongIndex.setState(0);
    case 2:
    case 1:
    }
    while (true)
    {
      return;
      setHighlighted(true);
      this.mSongIndex.setState(3);
      continue;
      this.mSongIndex.setState(5);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.SongSnippet
 * JD-Core Version:    0.6.2
 */