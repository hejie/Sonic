package com.google.android.finsky.fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.AppSecurityPermissions;
import com.google.android.finsky.layout.CustomRadioGroup;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.DocDetails.VideoRentalTerm;
import com.google.android.finsky.remoting.protos.DocDetails.VideoRentalTerm.Term;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PurchaseDocumentDetailsViewBinder
{
  private AppSecurityPermissions mAppSecurityPermissions;
  private CustomRadioGroup mButtonStrip;
  private Context mContext;
  private int mDefaultTab = -1;
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private View mLayout;
  private PackageManager mPackageManager;
  private final Runnable mRequestLayoutRunnable = new Runnable()
  {
    public void run()
    {
      if (PurchaseDocumentDetailsViewBinder.this.mLayout != null)
      {
        PurchaseDocumentDetailsViewBinder.this.mLayout.requestLayout();
        PurchaseDocumentDetailsViewBinder.this.mButtonStrip.invalidate();
      }
    }
  };
  private Bundle mSavedState;
  private FrameLayout mTabContent;

  private void initTab(LayoutInflater paramLayoutInflater, int paramInt1, String paramString, final View paramView, int paramInt2)
  {
    final RadioButton localRadioButton = (RadioButton)paramLayoutInflater.inflate(2130968627, this.mButtonStrip, false);
    localRadioButton.setId(paramInt1);
    localRadioButton.setText(paramString.toUpperCase());
    int i;
    if (paramInt2 != 0)
    {
      i = CorpusResourceUtils.getBackendForegroundColor(this.mContext, paramInt2);
      localRadioButton.setTextColor(i);
      this.mButtonStrip.addView(localRadioButton);
      if (!this.mContext.getResources().getBoolean(2131296256))
        break label124;
    }
    label124: for (final int j = 4; ; j = 8)
    {
      paramView.setVisibility(j);
      this.mTabContent.addView(paramView);
      localRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          View localView = paramView;
          if (paramAnonymousBoolean);
          for (int i = 0; ; i = j)
          {
            localView.setVisibility(i);
            if (paramAnonymousBoolean)
              ((ViewGroup)PurchaseDocumentDetailsViewBinder.this.mButtonStrip.getParent()).scrollTo(localRadioButton.getLeft(), 0);
            PurchaseDocumentDetailsViewBinder.this.requestContentLayout();
            return;
          }
        }
      });
      return;
      i = 2131361798;
      break;
    }
  }

  private void requestContentLayout()
  {
    this.mHandler.post(this.mRequestLayoutRunnable);
  }

  public void bind(FragmentManager paramFragmentManager, ViewGroup paramViewGroup, Document paramDocument, int paramInt, List<String> paramList, boolean paramBoolean)
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(this.mContext);
    int i = paramDocument.getBackend();
    this.mLayout = paramViewGroup;
    this.mButtonStrip = ((CustomRadioGroup)paramViewGroup.findViewById(2131231162));
    this.mTabContent = ((FrameLayout)paramViewGroup.findViewById(2131231163));
    ((HorizontalScrollView)paramViewGroup.findViewById(2131231161)).setHorizontalScrollBarEnabled(false);
    int j = this.mDefaultTab;
    if (this.mButtonStrip.getChildCount() > 0)
      j = this.mButtonStrip.getCheckedRadioButtonId();
    this.mButtonStrip.clearCheck();
    this.mButtonStrip.removeAllViews();
    this.mButtonStrip.setBackendId(paramDocument.getBackend());
    this.mTabContent.removeAllViews();
    List localList = paramDocument.getAppPermissionsList();
    if ((i == 3) && (paramBoolean))
    {
      CharSequence localCharSequence = paramDocument.getDescription();
      TextView localTextView4 = (TextView)localLayoutInflater.inflate(2130968797, null);
      localTextView4.setText(localCharSequence);
      initTab(localLayoutInflater, 2, FinskyApp.get().getString(2131165587), localTextView4, i);
      if (j < 0)
      {
        j = 2;
        break label506;
      }
    }
    while (true)
      label186: if ((paramList != null) && (paramList.size() > 0))
      {
        ViewGroup localViewGroup2 = (ViewGroup)localLayoutInflater.inflate(2130968795, null);
        Iterator localIterator3 = paramList.iterator();
        while (true)
          if (localIterator3.hasNext())
          {
            String str2 = (String)localIterator3.next();
            ViewGroup localViewGroup3 = (ViewGroup)localLayoutInflater.inflate(2130968707, localViewGroup2, false);
            TextView localTextView1 = (TextView)localViewGroup3.findViewById(2131230895);
            localTextView1.setText(Html.fromHtml(str2));
            localTextView1.setMovementMethod(LinkMovementMethod.getInstance());
            localViewGroup3.findViewById(2131230739).setVisibility(8);
            localViewGroup2.addView(localViewGroup3);
            continue;
            if (localList != null)
            {
              ArrayList localArrayList = Lists.newArrayList();
              Iterator localIterator4 = localList.iterator();
              while (localIterator4.hasNext())
              {
                String str3 = (String)localIterator4.next();
                try
                {
                  localArrayList.add(this.mPackageManager.getPermissionInfo(str3, 0));
                }
                catch (PackageManager.NameNotFoundException localNameNotFoundException)
                {
                }
              }
              this.mAppSecurityPermissions = ((AppSecurityPermissions)localLayoutInflater.inflate(2130968780, null));
              this.mAppSecurityPermissions.bindInfo(paramFragmentManager, paramDocument.getAppDetails().getPackageName(), localArrayList, this.mSavedState);
              initTab(localLayoutInflater, 0, FinskyApp.get().getString(2131165585), this.mAppSecurityPermissions, i);
              if (j >= 0)
                break label186;
              j = 0;
              break label186;
            }
            if (i != 4)
              break label186;
            ViewGroup localViewGroup1 = (ViewGroup)localLayoutInflater.inflate(2130968795, null);
            if ((paramDocument.getDocumentType() == 19) || (paramDocument.getDocumentType() == 20) || (paramDocument.getDocumentType() != 6))
              break label186;
            Iterator localIterator1 = paramDocument.getMovieRentalTerms().iterator();
            label506: if (!localIterator1.hasNext())
              break label186;
            DocDetails.VideoRentalTerm localVideoRentalTerm = (DocDetails.VideoRentalTerm)localIterator1.next();
            if (localVideoRentalTerm.getOfferType() != paramInt)
              break;
            String str1 = localVideoRentalTerm.getRentalHeader();
            Iterator localIterator2 = localVideoRentalTerm.getTermList().iterator();
            while (localIterator2.hasNext())
            {
              DocDetails.VideoRentalTerm.Term localTerm = (DocDetails.VideoRentalTerm.Term)localIterator2.next();
              ViewGroup localViewGroup4 = (ViewGroup)localLayoutInflater.inflate(2130968707, localViewGroup1, false);
              TextView localTextView2 = (TextView)localViewGroup4.findViewById(2131230895);
              localTextView2.setText(Html.fromHtml(localTerm.getHeader()));
              localTextView2.setMovementMethod(LinkMovementMethod.getInstance());
              TextView localTextView3 = (TextView)localViewGroup4.findViewById(2131230739);
              localTextView3.setText(Html.fromHtml(localTerm.getBody()));
              localTextView3.setMovementMethod(LinkMovementMethod.getInstance());
              localViewGroup1.addView(localViewGroup4);
            }
            initTab(localLayoutInflater, 3, str1, localViewGroup1, i);
            if (j >= 0)
              break label186;
            j = 3;
            break label186;
          }
        initTab(localLayoutInflater, 1, FinskyApp.get().getString(2131165586), localViewGroup2, i);
        if (j < 0)
          j = 1;
      }
    if (this.mButtonStrip.getChildCount() == 0)
      this.mButtonStrip.setVisibility(8);
    while (true)
    {
      return;
      this.mButtonStrip.setVisibility(0);
      this.mButtonStrip.check(j);
    }
  }

  public void init(Context paramContext, PackageManager paramPackageManager, Bundle paramBundle)
  {
    this.mContext = paramContext;
    this.mPackageManager = paramPackageManager;
    this.mSavedState = paramBundle;
    if ((paramBundle != null) && (paramBundle.containsKey("default_tab")))
      this.mDefaultTab = paramBundle.getInt("default_tab", -1);
  }

  public void onDestroyView()
  {
    this.mLayout = null;
    this.mContext = null;
    this.mAppSecurityPermissions = null;
    this.mButtonStrip = null;
    this.mTabContent = null;
    this.mHandler.removeCallbacks(this.mRequestLayoutRunnable);
  }

  public void saveState(Bundle paramBundle)
  {
    if (this.mButtonStrip != null)
      paramBundle.putInt("default_tab", this.mButtonStrip.getCheckedRadioButtonId());
    if (this.mAppSecurityPermissions != null)
      this.mAppSecurityPermissions.saveInstanceState(paramBundle);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.PurchaseDocumentDetailsViewBinder
 * JD-Core Version:    0.6.2
 */