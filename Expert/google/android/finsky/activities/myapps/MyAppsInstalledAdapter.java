package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.AggregatedAdapter;
import com.google.android.finsky.adapters.DownloadProgressHelper;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.layout.OverviewBucketEntry;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerProgressReport;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MyAppsInstalledAdapter extends BaseAdapter
  implements MyAppsListAdapter
{
  private static final Collator sDocumentAbcCollator = Collator.getInstance();
  private static final Comparator<Document> sDocumentAbcSorter = new Comparator()
  {
    public int compare(Document paramAnonymousDocument1, Document paramAnonymousDocument2)
    {
      String str1 = paramAnonymousDocument1.getTitle();
      String str2 = paramAnonymousDocument2.getTitle();
      int i = MyAppsInstalledAdapter.sDocumentAbcCollator.compare(str1, str2);
      if (i != 0);
      while (true)
      {
        return i;
        i = paramAnonymousDocument1.getAppDetails().getPackageName().compareTo(paramAnonymousDocument2.getAppDetails().getPackageName());
      }
    }
  };
  private final AggregatedAdapter<SectionAdapter> mAggregatedAdapter;
  private final AppStates mAppStates;
  private final BitmapLoader mBitmapLoader;
  private final BucketsChangedListener mBucketsChangedListener;
  protected Context mContext;
  private final SectionAdapter mDownloadingSectionAdapter;
  private final boolean mHasDocumentView;
  private final InstallPolicies mInstallPolicies;
  private final SectionAdapter mInstalledSectionAdapter;
  private final Installer mInstaller;
  protected final LayoutInflater mLayoutInflater;
  protected ListView mListView;
  private final SectionAdapter mManualUpdatesSectionAdapter;
  private final View.OnClickListener mOnClickListener;
  private final List<Document> mUnsortedDocuments = Lists.newArrayList();
  private final SectionAdapter mUpdatesSectionAdapter;

  public MyAppsInstalledAdapter(Context paramContext, Installer paramInstaller, InstallPolicies paramInstallPolicies, AppStates paramAppStates, BitmapLoader paramBitmapLoader, View.OnClickListener paramOnClickListener, boolean paramBoolean, BucketsChangedListener paramBucketsChangedListener)
  {
    this.mContext = paramContext;
    this.mInstaller = paramInstaller;
    this.mInstallPolicies = paramInstallPolicies;
    this.mAppStates = paramAppStates;
    this.mOnClickListener = paramOnClickListener;
    this.mBitmapLoader = paramBitmapLoader;
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    this.mHasDocumentView = paramBoolean;
    this.mBucketsChangedListener = paramBucketsChangedListener;
    this.mDownloadingSectionAdapter = new SectionAdapter(DocumentState.DOWNLOADING, 2131165418, DocumentBulkAction.STOP_ALL_DOWNLOADS);
    this.mUpdatesSectionAdapter = new SectionAdapter(DocumentState.UPDATE_AVAILABLE, 2131165419, DocumentBulkAction.UPDATE_ALL);
    this.mManualUpdatesSectionAdapter = new SectionAdapter(DocumentState.UPDATE_AVAILABLE, 2131165420, null);
    this.mInstalledSectionAdapter = new SectionAdapter(DocumentState.INSTALLED, 2131165422, null);
    SectionAdapter[] arrayOfSectionAdapter = new SectionAdapter[4];
    arrayOfSectionAdapter[0] = this.mDownloadingSectionAdapter;
    arrayOfSectionAdapter[1] = this.mUpdatesSectionAdapter;
    arrayOfSectionAdapter[2] = this.mManualUpdatesSectionAdapter;
    arrayOfSectionAdapter[3] = this.mInstalledSectionAdapter;
    this.mAggregatedAdapter = new AggregatedAdapter(arrayOfSectionAdapter);
  }

  private View getDocView(Document paramDocument, View paramView, ViewGroup paramViewGroup, DocumentState paramDocumentState, boolean paramBoolean)
  {
    LayoutInflater localLayoutInflater;
    if (paramView == null)
    {
      localLayoutInflater = this.mLayoutInflater;
      if (!this.mHasDocumentView)
        break label99;
    }
    label99: for (int i = 2130968673; ; i = 2130968705)
    {
      paramView = localLayoutInflater.inflate(i, paramViewGroup, false);
      OverviewBucketEntry localOverviewBucketEntry = (OverviewBucketEntry)paramView;
      localOverviewBucketEntry.setRightSeparatorVisibility(false);
      localOverviewBucketEntry.bind(null, paramDocument, this.mBitmapLoader, paramBoolean, this.mOnClickListener);
      BaseViewHolder localBaseViewHolder = (BaseViewHolder)paramView.getTag();
      if (localBaseViewHolder == null)
        localBaseViewHolder = new BaseViewHolder(paramView);
      localBaseViewHolder.doc = paramDocument;
      localOverviewBucketEntry.setTag(localBaseViewHolder);
      return paramView;
    }
  }

  private View getDownloadingDocView(Document paramDocument, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = this.mLayoutInflater.inflate(2130968676, paramViewGroup, false);
    DownloadingViewHolder localDownloadingViewHolder = (DownloadingViewHolder)paramView.getTag();
    if (localDownloadingViewHolder == null)
      localDownloadingViewHolder = new DownloadingViewHolder(paramView);
    localDownloadingViewHolder.doc = paramDocument;
    localDownloadingViewHolder.title.setText(paramDocument.getTitle());
    localDownloadingViewHolder.author.setText(paramDocument.getCreator());
    localDownloadingViewHolder.thumbnail.bind(paramDocument, this.mBitmapLoader);
    Installer.InstallerProgressReport localInstallerProgressReport = this.mInstaller.getProgress(paramDocument.getAppDetails().getPackageName());
    DownloadProgressHelper.configureDownloadProgressUi(this.mContext, localInstallerProgressReport, localDownloadingViewHolder.downloadingBytes, localDownloadingViewHolder.downloadingPercentage, localDownloadingViewHolder.progressBar);
    paramView.setOnClickListener(this.mOnClickListener);
    int i = CorpusResourceUtils.getCorpusCellContentLongDescriptionResource(3);
    Context localContext = this.mContext;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramDocument.getTitle();
    arrayOfObject[1] = paramDocument.getCreator();
    arrayOfObject[2] = this.mContext.getString(2131165604);
    paramView.setContentDescription(localContext.getString(i, arrayOfObject));
    return paramView;
  }

  private View getHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup, SectionAdapter paramSectionAdapter)
  {
    if (paramView == null)
      paramView = this.mLayoutInflater.inflate(2130968738, paramViewGroup, false);
    SectionHeaderHolder localSectionHeaderHolder = (SectionHeaderHolder)paramView.getTag();
    if (localSectionHeaderHolder == null)
      localSectionHeaderHolder = new SectionHeaderHolder(paramView);
    localSectionHeaderHolder.titleView.setText(this.mContext.getString(paramSectionAdapter.mHeaderTextId));
    localSectionHeaderHolder.bulkActionButton.setVisibility(8);
    localSectionHeaderHolder.loadingProgress.setVisibility(8);
    localSectionHeaderHolder.divider.setVisibility(8);
    final DocumentBulkAction localDocumentBulkAction = paramSectionAdapter.mHeaderAction;
    int i;
    int j;
    label132: label146: int m;
    label159: TextView localTextView;
    if ((localDocumentBulkAction != null) && (localDocumentBulkAction.isVisible(this)))
    {
      i = 1;
      if ((localDocumentBulkAction == null) || (!localDocumentBulkAction.isWaiting(this)))
        break label212;
      j = 1;
      if (j == 0)
        break label218;
      localSectionHeaderHolder.loadingProgress.setVisibility(0);
      if ((j == 0) && (i != 0))
        break label340;
      m = 1;
      localTextView = localSectionHeaderHolder.countView;
      if (m == 0)
        break label346;
    }
    label212: label346: for (int n = 0; ; n = 8)
    {
      localTextView.setVisibility(n);
      if (m != 0)
        localSectionHeaderHolder.countView.setText(Integer.toString(-1 + paramSectionAdapter.getCount()));
      return paramView;
      i = 0;
      break;
      j = 0;
      break label132;
      label218: if (i == 0)
        break label146;
      localSectionHeaderHolder.divider.setVisibility(0);
      localSectionHeaderHolder.bulkActionButton.setVisibility(0);
      Button localButton = localSectionHeaderHolder.bulkActionButton;
      Context localContext = this.mContext;
      int k = localDocumentBulkAction.getLabelId();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(-1 + paramSectionAdapter.getCount());
      localButton.setText(localContext.getString(k, arrayOfObject));
      localSectionHeaderHolder.bulkActionButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localDocumentBulkAction.run(MyAppsInstalledAdapter.this.mContext, MyAppsInstalledAdapter.this);
        }
      });
      Drawable localDrawable = localDocumentBulkAction.getIcon(this.mContext);
      localSectionHeaderHolder.bulkActionButton.setCompoundDrawablesWithIntrinsicBounds(localDrawable, null, null, null);
      break label146;
      m = 0;
      break label159;
    }
  }

  public static Document getViewDoc(View paramView)
  {
    Object localObject = paramView.getTag();
    if ((localObject instanceof BaseViewHolder));
    for (Document localDocument = ((BaseViewHolder)localObject).doc; ; localDocument = null)
      return localDocument;
  }

  private void putDocsInBuckets()
  {
    SectionAdapter[] arrayOfSectionAdapter = (SectionAdapter[])this.mAggregatedAdapter.getAdapters();
    int i = arrayOfSectionAdapter.length;
    for (int j = 0; j < i; j++)
      arrayOfSectionAdapter[j].clearDocs();
    ArrayList localArrayList1 = Lists.newArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator1 = this.mUnsortedDocuments.iterator();
    while (localIterator1.hasNext())
    {
      Document localDocument2 = (Document)localIterator1.next();
      String str = localDocument2.getAppDetails().getPackageName();
      PackageStateRepository.PackageState localPackageState = this.mAppStates.getPackageStateRepository().get(str);
      if (this.mInstaller.getState(str).isDownloadingOrInstalling())
        this.mDownloadingSectionAdapter.addDoc(localDocument2);
      else if ((localPackageState == null) || (localPackageState.isDisabled))
        localArrayList1.add(localDocument2);
      else if (this.mInstallPolicies.canUpdateApp(localPackageState, localDocument2))
        localArrayList2.add(localDocument2);
      else
        this.mInstalledSectionAdapter.addDoc(localDocument2);
    }
    List localList = this.mInstallPolicies.getApplicationsEligibleForAutoUpdate(localArrayList2, false);
    Iterator localIterator2 = localArrayList2.iterator();
    while (localIterator2.hasNext())
    {
      Document localDocument1 = (Document)localIterator2.next();
      if (localList.contains(localDocument1))
        this.mUpdatesSectionAdapter.addDoc(localDocument1);
      else
        this.mManualUpdatesSectionAdapter.addDoc(localDocument1);
    }
    this.mUnsortedDocuments.removeAll(localArrayList1);
    int k = arrayOfSectionAdapter.length;
    for (int m = 0; m < k; m++)
    {
      SectionAdapter localSectionAdapter = arrayOfSectionAdapter[m];
      localSectionAdapter.sortDocs();
      localSectionAdapter.notifyDataSetChanged();
    }
  }

  public void addDocs(Collection<Document> paramCollection)
  {
    this.mUnsortedDocuments.clear();
    this.mUnsortedDocuments.addAll(paramCollection);
    notifyDataSetChanged();
  }

  public boolean areAllItemsEnabled()
  {
    return this.mAggregatedAdapter.areAllItemsEnabled();
  }

  public int getCount()
  {
    return this.mAggregatedAdapter.getCount();
  }

  public Document getDocument(int paramInt)
  {
    return (Document)getItem(paramInt);
  }

  public Object getItem(int paramInt)
  {
    return this.mAggregatedAdapter.getItem(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    return this.mAggregatedAdapter.getItemViewType(paramInt);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    this.mListView = ((ListView)paramViewGroup);
    return this.mAggregatedAdapter.getView(paramInt, paramView, paramViewGroup);
  }

  public int getViewTypeCount()
  {
    return 3;
  }

  public boolean isEnabled(int paramInt)
  {
    return this.mAggregatedAdapter.isEnabled(paramInt);
  }

  public void notifyDataSetChanged()
  {
    putDocsInBuckets();
    super.notifyDataSetChanged();
    if (this.mBucketsChangedListener != null)
      this.mBucketsChangedListener.bucketsChanged();
  }

  private static class BaseViewHolder
  {
    public final TextView author;
    public Document doc;
    public final DocImageView thumbnail;
    public final TextView title;

    public BaseViewHolder(View paramView)
    {
      this.title = ((TextView)paramView.findViewById(2131230754));
      this.thumbnail = ((DocImageView)paramView.findViewById(2131230751));
      this.author = ((TextView)paramView.findViewById(2131230755));
    }
  }

  static abstract interface BucketsChangedListener
  {
    public abstract void bucketsChanged();
  }

  public static abstract enum DocumentBulkAction
  {
    static
    {
      // Byte code:
      //   0: new 14	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction$1
      //   3: dup
      //   4: ldc 15
      //   6: iconst_0
      //   7: invokespecial 19	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction$1:<init>	(Ljava/lang/String;I)V
      //   10: putstatic 21	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction:UPDATE_ALL	Lcom/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction;
      //   13: new 23	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction$2
      //   16: dup
      //   17: ldc 24
      //   19: iconst_1
      //   20: invokespecial 25	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction$2:<init>	(Ljava/lang/String;I)V
      //   23: putstatic 27	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction:STOP_ALL_DOWNLOADS	Lcom/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction;
      //   26: iconst_2
      //   27: anewarray 2	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction
      //   30: astore_0
      //   31: aload_0
      //   32: iconst_0
      //   33: getstatic 21	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction:UPDATE_ALL	Lcom/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction;
      //   36: aastore
      //   37: aload_0
      //   38: iconst_1
      //   39: getstatic 27	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction:STOP_ALL_DOWNLOADS	Lcom/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction;
      //   42: aastore
      //   43: aload_0
      //   44: putstatic 29	com/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction:$VALUES	[Lcom/google/android/finsky/activities/myapps/MyAppsInstalledAdapter$DocumentBulkAction;
      //   47: return
    }

    public abstract Drawable getIcon(Context paramContext);

    public abstract int getLabelId();

    public abstract boolean isVisible(MyAppsInstalledAdapter paramMyAppsInstalledAdapter);

    public abstract boolean isWaiting(MyAppsInstalledAdapter paramMyAppsInstalledAdapter);

    public abstract void run(Context paramContext, MyAppsInstalledAdapter paramMyAppsInstalledAdapter);
  }

  private static enum DocumentState
  {
    static
    {
      DocumentState[] arrayOfDocumentState = new DocumentState[3];
      arrayOfDocumentState[0] = DOWNLOADING;
      arrayOfDocumentState[1] = INSTALLED;
      arrayOfDocumentState[2] = UPDATE_AVAILABLE;
    }
  }

  private static final class DownloadingViewHolder extends MyAppsInstalledAdapter.BaseViewHolder
  {
    public final TextView downloadingBytes;
    public final TextView downloadingPercentage;
    public final ProgressBar progressBar;

    public DownloadingViewHolder(View paramView)
    {
      super();
      this.downloadingBytes = ((TextView)paramView.findViewById(2131230929));
      this.downloadingPercentage = ((TextView)paramView.findViewById(2131230928));
      this.progressBar = ((ProgressBar)paramView.findViewById(2131230930));
      paramView.setTag(this);
    }
  }

  private class SectionAdapter extends BaseAdapter
  {
    private final List<Document> mDocs;
    private final MyAppsInstalledAdapter.DocumentState mDocumentState;
    private final MyAppsInstalledAdapter.DocumentBulkAction mHeaderAction;
    private final int mHeaderTextId;

    public SectionAdapter(MyAppsInstalledAdapter.DocumentState paramInt, int paramDocumentBulkAction, MyAppsInstalledAdapter.DocumentBulkAction arg4)
    {
      this.mDocumentState = paramInt;
      this.mHeaderTextId = paramDocumentBulkAction;
      Object localObject;
      this.mHeaderAction = localObject;
      this.mDocs = new ArrayList();
    }

    void addDoc(Document paramDocument)
    {
      this.mDocs.add(paramDocument);
    }

    void clearDocs()
    {
      this.mDocs.clear();
    }

    public int getCount()
    {
      if (isVisible());
      for (int i = 1 + this.mDocs.size(); ; i = 0)
        return i;
    }

    public Object getItem(int paramInt)
    {
      if (paramInt == 0);
      for (Object localObject = null; ; localObject = this.mDocs.get(paramInt - 1))
        return localObject;
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public int getItemViewType(int paramInt)
    {
      int i;
      if (paramInt == 0)
        i = 0;
      while (true)
      {
        return i;
        if (this.mDocumentState == MyAppsInstalledAdapter.DocumentState.DOWNLOADING)
          i = 2;
        else
          i = 1;
      }
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      int i = getItemViewType(paramInt);
      boolean bool;
      View localView;
      if (paramInt == -1 + getCount())
      {
        bool = true;
        localView = null;
        switch (i)
        {
        default:
        case 2:
        case 1:
        case 0:
        }
      }
      while (true)
      {
        if (localView != null)
          break label166;
        throw new IllegalStateException("Null row view for position " + paramInt + " and type " + i);
        bool = false;
        break;
        localView = MyAppsInstalledAdapter.this.getDownloadingDocView((Document)getItem(paramInt), paramView, paramViewGroup);
        continue;
        localView = MyAppsInstalledAdapter.this.getDocView((Document)getItem(paramInt), paramView, paramViewGroup, this.mDocumentState, bool);
        continue;
        localView = MyAppsInstalledAdapter.this.getHeaderView(paramInt, paramView, paramViewGroup, this);
      }
      label166: return localView;
    }

    public int getViewTypeCount()
    {
      return 3;
    }

    public boolean isVisible()
    {
      if (this.mDocs.size() > 0);
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    void sortDocs()
    {
      Collections.sort(this.mDocs, MyAppsInstalledAdapter.sDocumentAbcSorter);
    }
  }

  private static final class SectionHeaderHolder
  {
    public final Button bulkActionButton;
    public final TextView countView;
    public final View divider;
    public final View loadingProgress;
    public final TextView titleView;

    public SectionHeaderHolder(View paramView)
    {
      this.titleView = ((TextView)paramView.findViewById(2131231105));
      this.bulkActionButton = ((Button)paramView.findViewById(2131231104));
      this.loadingProgress = paramView.findViewById(2131230943);
      this.divider = paramView.findViewById(2131231107);
      this.countView = ((TextView)paramView.findViewById(2131231106));
      paramView.setTag(this);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsInstalledAdapter
 * JD-Core Version:    0.6.2
 */