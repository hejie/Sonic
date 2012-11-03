package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.LibraryUtils;
import java.util.List;

public class WarningMessageSection extends TextView
{
  private boolean mAdjustedDrawable = false;

  public WarningMessageSection(Context paramContext)
  {
    super(paramContext);
  }

  public WarningMessageSection(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void bind(Document paramDocument, ImageView paramImageView, DfeToc paramDfeToc, Libraries paramLibraries, Account paramAccount)
  {
    AccountLibrary localAccountLibrary = paramLibraries.getAccountLibrary(paramAccount);
    boolean bool1 = LibraryUtils.isAvailable(paramDocument, paramDfeToc, localAccountLibrary);
    boolean bool2 = paramDocument.hasWarningMessage();
    int i;
    if ((paramDocument.getDocumentType() != 1) && (!LibraryUtils.isOwned(paramDocument, localAccountLibrary)) && (LibraryUtils.isOwned(paramDocument, paramLibraries)))
    {
      i = 1;
      if ((!bool1) || (bool2) || (i != 0))
        break label81;
      setVisibility(8);
    }
    label269: 
    while (true)
    {
      return;
      i = 0;
      break;
      label81: setVisibility(0);
      if (!bool1)
        setText(DocUtils.getAvailabilityRestrictionResourceId(paramDocument));
      while (true)
      {
        if ((paramImageView == null) || (paramImageView.getDrawable() == null) || (this.mAdjustedDrawable))
          break label269;
        this.mAdjustedDrawable = true;
        int j = getCompoundDrawables()[0].getIntrinsicWidth();
        int k = (paramImageView.getDrawable().getIntrinsicWidth() - j) / 2;
        ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramImageView.getLayoutParams();
        int m = paramImageView.getPaddingRight() + localMarginLayoutParams.rightMargin;
        setPadding(k + getPaddingLeft(), 0, 0, 0);
        setCompoundDrawablePadding(k + m);
        break;
        if (bool2)
        {
          setText(paramDocument.getWarningMessage());
          setMovementMethod(LinkMovementMethod.getInstance());
        }
        else
        {
          List localList = LibraryUtils.getOwners(paramDocument, paramLibraries);
          Context localContext = getContext();
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = ((Account)localList.get(0)).name;
          setText(localContext.getString(2131165715, arrayOfObject));
        }
      }
    }
  }

  protected void onDetachedFromWindow()
  {
    this.mAdjustedDrawable = false;
    super.onDetachedFromWindow();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.WarningMessageSection
 * JD-Core Version:    0.6.2
 */