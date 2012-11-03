package com.google.android.finsky.layout;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.activities.PurchaseContentLayout;
import com.google.android.finsky.utils.ExpandableUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AppSecurityPermissions extends LinearLayout
{
  private static final Comparator<DetailsEntry> mNewComparator = new Comparator()
  {
    public int compare(AppSecurityPermissions.DetailsEntry paramAnonymousDetailsEntry1, AppSecurityPermissions.DetailsEntry paramAnonymousDetailsEntry2)
    {
      int i = 1;
      int j;
      if (paramAnonymousDetailsEntry2.hasNewPermission)
      {
        j = i;
        if (!paramAnonymousDetailsEntry1.hasNewPermission)
          break label30;
      }
      while (true)
      {
        return j - i;
        j = 0;
        break;
        label30: i = 0;
      }
    }
  };
  private Context mContext;
  private View mExpander;
  private int mExpansionState = 0;
  private FragmentManager mFragmentManager;
  private Handler mHandler = new Handler(Looper.getMainLooper());
  private PackageManager mPackageManager;
  private String mPackageName;
  private AppPermissionAdapter mPermissionAdapter;
  private Runnable mScrollerRunnable;

  public AppSecurityPermissions(Context paramContext)
  {
    this(paramContext, null);
  }

  public AppSecurityPermissions(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private boolean isCollapsed()
  {
    if (this.mExpansionState != 2);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private void setNormalPermissionsVisibility()
  {
    boolean bool = isCollapsed();
    if (bool);
    for (int i = 8; ; i = 0)
      for (int j = 0; j < getChildCount(); j++)
      {
        View localView2 = getChildAt(j);
        if (this.mPermissionAdapter.getViewType(j) == 1)
          localView2.setVisibility(i);
      }
    if (bool)
      return;
    int k = this.mExpander.getTop();
    Object localObject = (View)this.mExpander.getParent();
    label84: View localView1 = (View)((View)localObject).getParent();
    final ObservableScrollView localObservableScrollView;
    if ((localView1 instanceof ObservableScrollView))
    {
      localObservableScrollView = (ObservableScrollView)((View)localObject).getParent();
      if (!(localObject instanceof PurchaseContentLayout))
        break label187;
    }
    label187: for (final int m = k - ((PurchaseContentLayout)localObject).getAcquireRowFooterTop() + localObservableScrollView.getScrollY(); ; m = k)
    {
      this.mScrollerRunnable = new Runnable()
      {
        public void run()
        {
          localObservableScrollView.smoothScrollTo(0, m);
        }
      };
      this.mHandler.post(this.mScrollerRunnable);
      break;
      k += ((View)localObject).getTop();
      localObject = localView1;
      break label84;
    }
  }

  private void showPermissions()
  {
    removeAllViews();
    if (this.mPermissionAdapter.getCount() == 0)
    {
      addView((TextView)LayoutInflater.from(this.mContext).inflate(2130968742, null));
      return;
    }
    if (this.mPermissionAdapter.containsDangerousNewPermissions())
      addView((TextView)LayoutInflater.from(this.mContext).inflate(2130968741, null));
    int i = 0;
    label67: int j;
    View localView;
    if (i < this.mPermissionAdapter.getCount())
    {
      j = this.mPermissionAdapter.getViewType(i);
      localView = this.mPermissionAdapter.getView(this, i);
      if (j == 1)
        if (!isCollapsed())
          break label140;
    }
    label140: for (int k = 8; ; k = 0)
    {
      localView.setVisibility(k);
      if (j == 2)
        this.mExpander = localView;
      addView(localView);
      i++;
      break label67;
      break;
    }
  }

  public void bindInfo(FragmentManager paramFragmentManager, String paramString, List<PermissionInfo> paramList, Bundle paramBundle)
  {
    this.mContext = getContext();
    this.mPackageManager = this.mContext.getPackageManager();
    this.mFragmentManager = paramFragmentManager;
    this.mPackageName = paramString;
    this.mPermissionAdapter = new AppPermissionAdapter(paramList);
    if (this.mExpansionState == 0)
      this.mExpansionState = ExpandableUtils.getSavedExpansionState(paramBundle, this.mPackageName + ":" + getId(), 1);
    showPermissions();
  }

  protected void onDetachedFromWindow()
  {
    this.mHandler.removeCallbacks(this.mScrollerRunnable);
    super.onDetachedFromWindow();
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    ExpandableUtils.saveExpansionState(paramBundle, this.mPackageName + ":" + getId(), this.mExpansionState);
  }

  private class AppPermissionAdapter
  {
    private final List<AppSecurityPermissions.DetailsEntry> mDangerousList = Lists.newArrayList();
    private final Map<String, Set<AppSecurityPermissions.PermissionUiInfo>> mDangerousMap = Maps.newHashMap();
    private LayoutInflater mLayoutInflater = LayoutInflater.from(AppSecurityPermissions.this.mContext);
    private final Set<AppSecurityPermissions.PermissionUiInfo> mNewDangerousPermissions = Sets.newHashSet();
    private final Set<AppSecurityPermissions.PermissionUiInfo> mNewNormalPermissions = Sets.newHashSet();
    private final Map<String, Set<AppSecurityPermissions.PermissionUiInfo>> mNormalMap = Maps.newHashMap();
    private final List<AppSecurityPermissions.DetailsEntry> mTotalList = Lists.newArrayList();

    AppPermissionAdapter()
    {
      Set localSet = loadLocalAssetPermissions(AppSecurityPermissions.this.mContext);
      Object localObject1;
      Iterator localIterator = localObject1.iterator();
      while (localIterator.hasNext())
      {
        PermissionInfo localPermissionInfo = (PermissionInfo)localIterator.next();
        if (!isDisplayablePermission(localPermissionInfo))
        {
          if (FinskyLog.DEBUG)
            FinskyLog.v("Permission:" + localPermissionInfo.name + " is not displayable", new Object[0]);
        }
        else
        {
          Object localObject2 = null;
          AppSecurityPermissions.PermissionUiInfo localPermissionUiInfo;
          try
          {
            PermissionGroupInfo localPermissionGroupInfo = AppSecurityPermissions.this.mPackageManager.getPermissionGroupInfo(localPermissionInfo.group, 0);
            localObject2 = localPermissionGroupInfo;
            if (localObject2 == null)
            {
              str1 = AppSecurityPermissions.this.mContext.getString(2131165589);
              String str2 = localPermissionInfo.loadLabel(AppSecurityPermissions.this.mPackageManager).toString();
              CharSequence localCharSequence = localPermissionInfo.loadDescription(AppSecurityPermissions.this.mPackageManager);
              if (localCharSequence == null)
                break label392;
              str3 = localCharSequence.toString();
              localPermissionUiInfo = new AppSecurityPermissions.PermissionUiInfo(AppSecurityPermissions.this, str2, str3);
              if (localPermissionInfo.protectionLevel != 1)
                break label398;
              if (!this.mDangerousMap.containsKey(str1))
                this.mDangerousMap.put(str1, new TreeSet());
              ((Set)this.mDangerousMap.get(str1)).add(localPermissionUiInfo);
              if ((localSet == null) || (localSet.contains(localPermissionInfo.name)))
                continue;
              this.mNewDangerousPermissions.add(localPermissionUiInfo);
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            String str1;
            while (true)
            {
              FinskyLog.e("Invalid group name:" + localPermissionInfo.group, new Object[0]);
              continue;
              str1 = localObject2.loadLabel(AppSecurityPermissions.this.mPackageManager).toString();
              continue;
              label392: String str3 = null;
            }
            label398: if (!this.mNormalMap.containsKey(str1))
              this.mNormalMap.put(str1, new TreeSet());
            ((Set)this.mNormalMap.get(str1)).add(localPermissionUiInfo);
          }
          if ((localSet != null) && (!localSet.contains(localPermissionInfo.name)))
            this.mNewNormalPermissions.add(localPermissionUiInfo);
        }
      }
      aggregatePermissions(this.mDangerousMap, this.mNewDangerousPermissions, this.mDangerousList);
      Collections.sort(this.mDangerousList, AppSecurityPermissions.mNewComparator);
      ArrayList localArrayList = Lists.newArrayList();
      aggregatePermissions(this.mNormalMap, this.mNewNormalPermissions, localArrayList);
      Collections.sort(localArrayList, AppSecurityPermissions.mNewComparator);
      this.mTotalList.addAll(this.mDangerousList);
      this.mTotalList.addAll(localArrayList);
    }

    private void aggregatePermissions(Map<String, Set<AppSecurityPermissions.PermissionUiInfo>> paramMap, Set<AppSecurityPermissions.PermissionUiInfo> paramSet, List<AppSecurityPermissions.DetailsEntry> paramList)
    {
      Iterator localIterator1 = paramMap.keySet().iterator();
      while (localIterator1.hasNext())
      {
        String str1 = (String)localIterator1.next();
        SpannableStringBuilder localSpannableStringBuilder1 = new SpannableStringBuilder();
        SpannableStringBuilder localSpannableStringBuilder2 = new SpannableStringBuilder();
        StringBuilder localStringBuilder = new StringBuilder();
        boolean bool1 = false;
        Iterator localIterator2 = ((Set)paramMap.get(str1)).iterator();
        while (localIterator2.hasNext())
        {
          AppSecurityPermissions.PermissionUiInfo localPermissionUiInfo = (AppSecurityPermissions.PermissionUiInfo)localIterator2.next();
          boolean bool2 = paramSet.contains(localPermissionUiInfo);
          SpannableStringBuilder localSpannableStringBuilder3;
          label124: String str2;
          String str3;
          String str4;
          if (bool2)
          {
            localSpannableStringBuilder3 = localSpannableStringBuilder2;
            bool1 |= bool2;
            str2 = localPermissionUiInfo.label;
            if (!TextUtils.isEmpty(str2))
            {
              str3 = localPermissionUiInfo.description;
              if (localSpannableStringBuilder3.length() > 0)
                localSpannableStringBuilder3.append(", ");
              str4 = str2.substring(0, 1).toUpperCase() + str2.substring(1);
              if (localSpannableStringBuilder3.length() == 0)
              {
                localSpannableStringBuilder3.append(str4);
                label220: if (localStringBuilder.length() > 0)
                  localStringBuilder.append("<br><br>");
                localStringBuilder.append("<b>");
                if (!paramSet.contains(localPermissionUiInfo))
                  break label326;
                localStringBuilder.append(AppSecurityPermissions.this.getContext().getResources().getString(2131165676, new Object[] { str4 }));
              }
            }
          }
          else
          {
            while (true)
            {
              localStringBuilder.append("</b><br><br>");
              localStringBuilder.append(str3);
              break;
              localSpannableStringBuilder3 = localSpannableStringBuilder1;
              break label124;
              localSpannableStringBuilder3.append(str2);
              break label220;
              label326: localStringBuilder.append(str4);
            }
          }
        }
        AppSecurityPermissions.DetailsEntry localDetailsEntry = new AppSecurityPermissions.DetailsEntry();
        localDetailsEntry.headerText = str1;
        localDetailsEntry.contentText = localSpannableStringBuilder1;
        localDetailsEntry.newContentText = localSpannableStringBuilder2;
        localDetailsEntry.detailedDescription = localStringBuilder;
        localDetailsEntry.hasNewPermission = bool1;
        paramList.add(localDetailsEntry);
      }
    }

    private boolean containsDangerousNewPermissions()
    {
      if (!this.mNewDangerousPermissions.isEmpty());
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    private PackageInfo getPackageInfo(Context paramContext)
    {
      try
      {
        PackageInfo localPackageInfo2 = paramContext.getPackageManager().getPackageInfo(AppSecurityPermissions.this.mPackageName, 4096);
        localPackageInfo1 = localPackageInfo2;
        return localPackageInfo1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
          PackageInfo localPackageInfo1 = null;
      }
    }

    private View getPermissionView(ViewGroup paramViewGroup, final AppSecurityPermissions.DetailsEntry paramDetailsEntry)
    {
      View localView = this.mLayoutInflater.inflate(2130968707, paramViewGroup, false);
      TextView localTextView1 = (TextView)localView.findViewById(2131230895);
      TextView localTextView2 = (TextView)localView.findViewById(2131231056);
      TextView localTextView3 = (TextView)localView.findViewById(2131230739);
      localTextView1.setText(paramDetailsEntry.headerText);
      if (TextUtils.isEmpty(paramDetailsEntry.contentText))
      {
        localTextView3.setVisibility(8);
        if (!TextUtils.isEmpty(paramDetailsEntry.newContentText))
          break label142;
        localTextView2.setVisibility(8);
      }
      while (true)
      {
        localView.findViewById(2131231055).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            AppSecurityPermissions.PermissionInfoDialog.showInfo(AppSecurityPermissions.this.mFragmentManager, paramDetailsEntry.headerText, paramDetailsEntry.detailedDescription.toString());
          }
        });
        localView.findViewById(2131231057).setVisibility(0);
        return localView;
        localTextView3.setVisibility(0);
        localTextView3.setText(paramDetailsEntry.contentText);
        break;
        label142: localTextView2.setVisibility(0);
        Resources localResources = AppSecurityPermissions.this.mContext.getResources();
        localTextView2.setTextColor(localResources.getColor(2131361810));
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramDetailsEntry.newContentText;
        String str = localResources.getString(2131165676, arrayOfObject);
        int i = str.indexOf(paramDetailsEntry.newContentText.toString());
        SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(str);
        localSpannableStringBuilder.setSpan(new TextAppearanceSpan(AppSecurityPermissions.this.mContext, 2131623976), i, i + paramDetailsEntry.newContentText.length(), 0);
        localTextView2.setText(localSpannableStringBuilder);
      }
    }

    private View getTogglerView(ViewGroup paramViewGroup)
    {
      final TextView localTextView = (TextView)this.mLayoutInflater.inflate(2130968779, paramViewGroup, false);
      localTextView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AppSecurityPermissions localAppSecurityPermissions = AppSecurityPermissions.this;
          if (AppSecurityPermissions.this.isCollapsed());
          for (int i = 2; ; i = 1)
          {
            AppSecurityPermissions.access$802(localAppSecurityPermissions, i);
            AppSecurityPermissions.AppPermissionAdapter.this.configureExpander(localTextView);
            AppSecurityPermissions.this.setNormalPermissionsVisibility();
            return;
          }
        }
      });
      configureExpander(localTextView);
      return localTextView;
    }

    private View getView(ViewGroup paramViewGroup, int paramInt)
    {
      View localView;
      switch (getViewType(paramInt))
      {
      default:
        localView = null;
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        return localView;
        localView = getPermissionView(paramViewGroup, (AppSecurityPermissions.DetailsEntry)this.mDangerousList.get(paramInt));
        continue;
        int i = paramInt;
        if (this.mNormalMap.size() > 0)
          i--;
        localView = getPermissionView(paramViewGroup, (AppSecurityPermissions.DetailsEntry)this.mTotalList.get(i));
        continue;
        localView = getTogglerView(paramViewGroup);
      }
    }

    private int getViewType(int paramInt)
    {
      int i;
      if (paramInt < this.mDangerousList.size())
        i = 0;
      while (true)
      {
        return i;
        if ((paramInt == this.mDangerousList.size()) && (this.mNormalMap.size() > 0))
          i = 2;
        else
          i = 1;
      }
    }

    private boolean isDisplayablePermission(PermissionInfo paramPermissionInfo)
    {
      int i = 1;
      if ((paramPermissionInfo.protectionLevel == i) || (paramPermissionInfo.protectionLevel == 0));
      while (true)
      {
        return i;
        int j = 0;
      }
    }

    private Set<String> loadLocalAssetPermissions(Context paramContext)
    {
      PackageInfo localPackageInfo = getPackageInfo(paramContext);
      Object localObject;
      if (localPackageInfo == null)
        localObject = null;
      while (true)
      {
        return localObject;
        localObject = new HashSet();
        if ((localPackageInfo != null) && (localPackageInfo.requestedPermissions != null))
        {
          String[] arrayOfString = localPackageInfo.requestedPermissions;
          int i = arrayOfString.length;
          for (int j = 0; j < i; j++)
            ((HashSet)localObject).add(arrayOfString[j]);
        }
      }
    }

    protected void configureExpander(TextView paramTextView)
    {
      int i;
      if (AppSecurityPermissions.this.isCollapsed())
      {
        i = 2130837648;
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
        if (!AppSecurityPermissions.this.isCollapsed())
          break label49;
      }
      label49: for (int j = 2131165467; ; j = 2131165468)
      {
        paramTextView.setText(j);
        return;
        i = 2130837652;
        break;
      }
    }

    public int getCount()
    {
      int i;
      if (this.mDangerousList.size() == 0)
        i = 0;
      while (true)
      {
        return i;
        i = this.mTotalList.size();
        if (!this.mNormalMap.isEmpty())
          i++;
      }
    }
  }

  public static class DetailsEntry
  {
    public CharSequence contentText;
    public CharSequence detailedDescription;
    public boolean hasNewPermission;
    public String headerText;
    public CharSequence newContentText;
  }

  public static class PermissionInfoDialog extends DialogFragment
  {
    static void showInfo(FragmentManager paramFragmentManager, String paramString1, String paramString2)
    {
      if (paramFragmentManager.findFragmentByTag("permission_info_dialog") != null);
      while (true)
      {
        return;
        Bundle localBundle = new Bundle();
        localBundle.putString("label", paramString1);
        localBundle.putString("description", paramString2);
        PermissionInfoDialog localPermissionInfoDialog = new PermissionInfoDialog();
        localPermissionInfoDialog.setArguments(localBundle);
        localPermissionInfoDialog.show(paramFragmentManager, "permission_info_dialog");
      }
    }

    public Dialog onCreateDialog(Bundle paramBundle)
    {
      return new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), 2131623999)).setTitle(getArguments().getString("label")).setMessage(Html.fromHtml(getArguments().getString("description"))).setPositiveButton(2131165599, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          AppSecurityPermissions.PermissionInfoDialog.this.dismiss();
        }
      }).create();
    }
  }

  private class PermissionUiInfo
    implements Comparable<PermissionUiInfo>
  {
    String description;
    String label;

    public PermissionUiInfo(String paramString1, String arg3)
    {
      this.label = paramString1;
      Object localObject;
      this.description = localObject;
    }

    public int compareTo(PermissionUiInfo paramPermissionUiInfo)
    {
      return this.label.compareTo(paramPermissionUiInfo.label);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.AppSecurityPermissions
 * JD-Core Version:    0.6.2
 */