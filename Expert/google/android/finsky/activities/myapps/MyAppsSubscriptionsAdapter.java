package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.DocDetails.SubscriptionDetails;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.Lists;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MyAppsSubscriptionsAdapter extends BaseAdapter
  implements MyAppsListAdapter
{
  private static final Collator sSubscriptionAbcCollator = Collator.getInstance();
  private static final Comparator<MyAppsSubscriptionEntry> sSubscriptionAbcSorter = new Comparator()
  {
    public int compare(MyAppsSubscriptionsAdapter.MyAppsSubscriptionEntry paramAnonymousMyAppsSubscriptionEntry1, MyAppsSubscriptionsAdapter.MyAppsSubscriptionEntry paramAnonymousMyAppsSubscriptionEntry2)
    {
      String str1 = paramAnonymousMyAppsSubscriptionEntry1.parentDoc.getTitle();
      String str2 = paramAnonymousMyAppsSubscriptionEntry2.parentDoc.getTitle();
      int i = MyAppsSubscriptionsAdapter.sSubscriptionAbcCollator.compare(str1, str2);
      int k;
      if (i != 0)
        k = i;
      while (true)
      {
        return k;
        String str3 = paramAnonymousMyAppsSubscriptionEntry1.subscriptionDoc.getTitle();
        String str4 = paramAnonymousMyAppsSubscriptionEntry2.subscriptionDoc.getTitle();
        int j = MyAppsSubscriptionsAdapter.sSubscriptionAbcCollator.compare(str3, str4);
        if (j != 0)
          k = j;
        else
          k = paramAnonymousMyAppsSubscriptionEntry1.parentDoc.getAppDetails().getPackageName().compareTo(paramAnonymousMyAppsSubscriptionEntry2.parentDoc.getAppDetails().getPackageName());
      }
    }
  };
  private final BitmapLoader mBitmapLoader;
  private final Context mContext;
  private final boolean mHasDocumentView;
  private final LayoutInflater mInflater;
  private final View.OnClickListener mListener;
  private final List<MyAppsSubscriptionEntry> mSubscriptions = Lists.newArrayList();

  public MyAppsSubscriptionsAdapter(Context paramContext, LayoutInflater paramLayoutInflater, boolean paramBoolean, BitmapLoader paramBitmapLoader, View.OnClickListener paramOnClickListener)
  {
    this.mContext = paramContext;
    this.mInflater = paramLayoutInflater;
    this.mBitmapLoader = paramBitmapLoader;
    this.mListener = paramOnClickListener;
    this.mHasDocumentView = paramBoolean;
  }

  public void addEntry(LibrarySubscriptionEntry paramLibrarySubscriptionEntry, Document paramDocument1, Document paramDocument2)
  {
    this.mSubscriptions.add(new MyAppsSubscriptionEntry(paramDocument1, paramDocument2, paramLibrarySubscriptionEntry));
    notifyDataSetChanged();
  }

  public void clear()
  {
    this.mSubscriptions.clear();
    notifyDataSetChanged();
  }

  public int getCount()
  {
    return this.mSubscriptions.size();
  }

  public Document getDocument(int paramInt)
  {
    return ((MyAppsSubscriptionEntry)this.mSubscriptions.get(paramInt)).parentDoc;
  }

  public Object getItem(int paramInt)
  {
    return ((MyAppsSubscriptionEntry)this.mSubscriptions.get(paramInt)).subscriptionDoc;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int j;
    LibrarySubscriptionEntry localLibrarySubscriptionEntry;
    Document localDocument1;
    Document localDocument2;
    Resources localResources;
    TextView localTextView1;
    TextView localTextView2;
    label197: int i;
    if (paramView == null)
    {
      LayoutInflater localLayoutInflater = this.mInflater;
      if (this.mHasDocumentView)
      {
        j = 2130968673;
        paramView = localLayoutInflater.inflate(j, paramViewGroup, false);
      }
    }
    else
    {
      MyAppsSubscriptionEntry localMyAppsSubscriptionEntry = (MyAppsSubscriptionEntry)this.mSubscriptions.get(paramInt);
      localLibrarySubscriptionEntry = localMyAppsSubscriptionEntry.subscriptionOwnership;
      localDocument1 = localMyAppsSubscriptionEntry.subscriptionDoc;
      localDocument2 = localMyAppsSubscriptionEntry.parentDoc;
      localResources = this.mContext.getResources();
      ((DocImageView)paramView.findViewById(2131230751)).bind(localDocument2, this.mBitmapLoader);
      ((TextView)paramView.findViewById(2131230754)).setText(localDocument1.getTitle());
      ((TextView)paramView.findViewById(2131230755)).setText(localDocument2.getTitle());
      View localView = paramView.findViewById(2131230756);
      if (localView != null)
        localView.setVisibility(8);
      localTextView1 = (TextView)paramView.findViewById(2131230752);
      localTextView2 = (TextView)paramView.findViewById(2131231070);
      if (!localLibrarySubscriptionEntry.isAutoRenewing)
        break label327;
      if (System.currentTimeMillis() >= localLibrarySubscriptionEntry.trialUntilTimestampMs)
        break label310;
      localTextView1.setText(2131165511);
      if (localTextView2 != null)
      {
        if (localDocument1.getSubscriptionDetails().getSubscriptionPeriod() != 1)
          break label320;
        i = 2131165502;
        label218: Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = localDocument1.getFormattedPrice(1);
        arrayOfObject[1] = localResources.getString(i);
        localTextView2.setText(localResources.getString(2131165499, arrayOfObject));
        localTextView2.setVisibility(0);
      }
    }
    while (true)
    {
      localTextView1.setTextColor(CorpusResourceUtils.getBackendForegroundColor(localTextView1.getContext(), localDocument1.getBackend()));
      paramView.setTag(localDocument2);
      View.OnClickListener localOnClickListener = this.mListener;
      paramView.setOnClickListener(localOnClickListener);
      return paramView;
      j = 2130968705;
      break;
      label310: localTextView1.setText(2131165510);
      break label197;
      label320: i = 2131165503;
      break label218;
      label327: localTextView1.setText(2131165512);
      if (localTextView2 != null)
      {
        localTextView2.setText(Html.fromHtml(localResources.getString(2131165506, new Object[] { DateUtils.formatShortDisplayDate(new Date(localLibrarySubscriptionEntry.validUntilTimestampMs)) })));
        localTextView2.setVisibility(0);
      }
    }
  }

  void sortDocs()
  {
    Collections.sort(this.mSubscriptions, sSubscriptionAbcSorter);
  }

  private class MyAppsSubscriptionEntry
  {
    public final Document parentDoc;
    public final Document subscriptionDoc;
    public final LibrarySubscriptionEntry subscriptionOwnership;

    public MyAppsSubscriptionEntry(Document paramDocument1, Document paramLibrarySubscriptionEntry, LibrarySubscriptionEntry arg4)
    {
      this.subscriptionDoc = paramDocument1;
      this.parentDoc = paramLibrarySubscriptionEntry;
      Object localObject;
      this.subscriptionOwnership = localObject;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsSubscriptionsAdapter
 * JD-Core Version:    0.6.2
 */