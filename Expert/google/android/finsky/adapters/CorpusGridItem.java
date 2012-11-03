package com.google.android.finsky.adapters;

import android.content.Context;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.CorpusResourceUtils;
import java.util.List;

public class CorpusGridItem
  implements UnevenGridAdapter.UnevenGridItem
{
  private final int mCellHeight;
  private final Context mContext;
  private final String mCurrentPageUrl;
  private final NavigationManager mNavigationManager;
  private final DfeToc mToc;

  public CorpusGridItem(Context paramContext, NavigationManager paramNavigationManager, DfeToc paramDfeToc, String paramString)
  {
    this.mContext = paramContext;
    this.mNavigationManager = paramNavigationManager;
    this.mToc = paramDfeToc;
    this.mCurrentPageUrl = paramString;
    this.mCellHeight = approximateCellHeight();
  }

  private int approximateCellHeight()
  {
    return (int)FloatMath.ceil(0.6F * this.mToc.getCorpusList().size());
  }

  private void bindCorpusTile(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, final String paramString1, final String paramString2, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, final int paramInt3)
  {
    ViewGroup localViewGroup = (ViewGroup)paramLayoutInflater.inflate(2130968622, paramViewGroup, false);
    localViewGroup.findViewById(2131230858).setBackgroundColor(0xC0FFFFFF & paramInt1);
    ((TextView)localViewGroup.findViewById(2131230861)).setText(paramString1);
    ((ImageView)localViewGroup.findViewById(2131230862)).setImageResource(paramInt2);
    ViewGroup.LayoutParams localLayoutParams1 = localViewGroup.findViewById(2131230859).getLayoutParams();
    int i;
    ViewGroup.LayoutParams localLayoutParams2;
    if (paramBoolean1)
    {
      i = 2;
      localLayoutParams1.height = i;
      localLayoutParams2 = localViewGroup.findViewById(2131230860).getLayoutParams();
      if (!paramBoolean2)
        break label157;
    }
    label157: for (int j = 2; ; j = 1)
    {
      localLayoutParams2.height = j;
      View localView = localViewGroup.findViewById(2131230744);
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          FinskyApp.get().getEventLogger().logClick(FinskyEventLog.getCorpusButtonDocId(paramInt3), CorpusGridItem.this.mCurrentPageUrl, paramString2, null, null);
          CorpusGridItem.this.mNavigationManager.goToCorpusHome(paramString2, paramString1, paramInt3, CorpusGridItem.this.mToc, CorpusGridItem.this.mCurrentPageUrl);
        }
      });
      localView.setContentDescription(paramString1);
      paramViewGroup.addView(localViewGroup);
      return;
      i = 1;
      break;
    }
  }

  public void bind(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(this.mContext);
    int i = this.mToc.getCorpusList().size();
    int j = 0;
    if (j < i)
    {
      Toc.CorpusMetadata localCorpusMetadata = (Toc.CorpusMetadata)this.mToc.getCorpusList().get(j);
      boolean bool1;
      label63: int k;
      int m;
      int n;
      String str1;
      String str2;
      if (j == i - 1)
      {
        bool1 = true;
        k = localCorpusMetadata.getBackend();
        m = CorpusResourceUtils.getBackendHintColor(this.mContext, k);
        n = CorpusResourceUtils.getCorpusWatermarkIconBig(k);
        str1 = localCorpusMetadata.getName();
        str2 = localCorpusMetadata.getLandingUrl();
        if (j != 0)
          break label142;
      }
      label142: for (boolean bool2 = true; ; bool2 = false)
      {
        bindCorpusTile(localLayoutInflater, paramViewGroup, str1, str2, bool2, bool1, m, n, k);
        j++;
        break;
        bool1 = false;
        break label63;
      }
    }
  }

  public int getCellHeight()
  {
    return this.mCellHeight;
  }

  public int getCellWidth()
  {
    return 2;
  }

  public UnevenGridItemType getGridItemType()
  {
    return UnevenGridItemType.CORPUS_2xN;
  }

  public int getLayoutId()
  {
    return 2130968623;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.CorpusGridItem
 * JD-Core Version:    0.6.2
 */