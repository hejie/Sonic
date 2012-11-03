package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.android.vending.R.styleable;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.api.AccountHandler;

public class AccountSelectorView extends TextView
{
  protected final Drawable mMultiAccountDrawable;

  public AccountSelectorView(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public AccountSelectorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public AccountSelectorView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AccountSelectorView, paramInt, 0);
    this.mMultiAccountDrawable = localTypedArray.getDrawable(0);
    localTypedArray.recycle();
  }

  public void configure(final AuthenticatedActivity paramAuthenticatedActivity)
  {
    setText(FinskyApp.get().getCurrentAccountName());
    int i;
    if (AccountHandler.getAccounts(getContext()).length > 1)
    {
      i = 1;
      if (i == 0)
        break label63;
      setClickable(true);
      setCompoundDrawablesWithIntrinsicBounds(null, null, this.mMultiAccountDrawable, null);
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAuthenticatedActivity.chooseAccount(true);
        }
      });
    }
    while (true)
    {
      return;
      i = 0;
      break;
      label63: setClickable(false);
      setCompoundDrawables(null, null, null, null);
      setOnClickListener(null);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.AccountSelectorView
 * JD-Core Version:    0.6.2
 */