package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.PurchaseButtonHelper;

public abstract class EditorialBucketContent extends RelativeLayout
  implements InstallerListener
{
  protected TextView mBuyButton;
  protected DecoratedTextView mCreator;
  protected TextView mDescription;
  private Document mDocument;
  private NavigationManager mNavigationManager;
  protected DocImageView mThumbnail;
  protected TextView mTitle;

  public EditorialBucketContent(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public EditorialBucketContent(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public EditorialBucketContent(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void updatePurchaseButton(String paramString)
  {
    if (!this.mDocument.hasContainerAnnotation())
    {
      Account localAccount = FinskyApp.get().getCurrentAccount();
      PurchaseButtonHelper.stylePurchaseButton(this.mDocument, false, this.mBuyButton);
      PurchaseButtonHelper.setPurchaseOrOpenClickListener(this.mDocument, this.mBuyButton, localAccount, this.mNavigationManager, paramString);
      this.mBuyButton.setVisibility(0);
    }
    while (true)
    {
      return;
      this.mBuyButton.setVisibility(8);
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    FinskyApp.get().getInstaller().addListener(this);
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    FinskyApp.get().getInstaller().removeListener(this);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mThumbnail = ((DocImageView)findViewById(2131230751));
    this.mTitle = ((TextView)findViewById(2131230754));
    this.mCreator = ((DecoratedTextView)findViewById(2131230755));
    this.mBuyButton = ((TextView)findViewById(2131230897));
    this.mDescription = ((TextView)findViewById(2131230986));
  }

  public void onInstallPackageEvent(String paramString, InstallerListener.InstallerPackageEvent paramInstallerPackageEvent, int paramInt)
  {
    updatePurchaseButton(null);
  }

  public void setDocument(BitmapLoader paramBitmapLoader, NavigationManager paramNavigationManager, Document paramDocument, String paramString)
  {
    this.mDocument = paramDocument;
    this.mNavigationManager = paramNavigationManager;
    this.mTitle.setVisibility(0);
    this.mCreator.setVisibility(0);
    this.mBuyButton.setVisibility(0);
    this.mDescription.setVisibility(0);
    this.mThumbnail.setVisibility(0);
    setTitle(paramDocument.getTitle());
    this.mCreator.setText(paramDocument.getCreator());
    BadgeUtils.configureCreatorBadge(paramDocument, paramBitmapLoader, this.mCreator, -1);
    updatePurchaseButton(paramString);
    this.mDescription.setText(paramDocument.getDescription());
    this.mThumbnail.bind(paramDocument, paramBitmapLoader, new int[] { 2, 4 });
    int i = CorpusResourceUtils.getCorpusCellContentLongDescriptionResource(paramDocument.getBackend());
    Context localContext = getContext();
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramDocument.getTitle();
    arrayOfObject[1] = paramDocument.getCreator();
    arrayOfObject[2] = this.mBuyButton.getText();
    setContentDescription(localContext.getString(i, arrayOfObject));
  }

  public void setMockDocument(int paramInt)
  {
    this.mTitle.setText(2131165433);
    this.mCreator.setVisibility(8);
    this.mDescription.setVisibility(8);
    this.mBuyButton.setVisibility(8);
    this.mThumbnail.setImageBitmap(CorpusResourceUtils.getPlaceholderPromo(paramInt, getContext().getResources()));
    BitmapLoader.BitmapContainer localBitmapContainer = (BitmapLoader.BitmapContainer)this.mThumbnail.getTag();
    if (localBitmapContainer != null)
      localBitmapContainer.cancelRequest();
    this.mThumbnail.setTag(null);
    this.mThumbnail.setVisibility(0);
  }

  public void setNoDocument()
  {
    this.mTitle.setVisibility(8);
    this.mCreator.setVisibility(8);
    this.mBuyButton.setVisibility(8);
    this.mDescription.setVisibility(8);
    this.mThumbnail.setVisibility(8);
  }

  public void setTitle(String paramString)
  {
    this.mTitle.setText(paramString);
  }

  public void setTitleColor(int paramInt)
  {
    this.mTitle.setTextColor(paramInt);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.EditorialBucketContent
 * JD-Core Version:    0.6.2
 */