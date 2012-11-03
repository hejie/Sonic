package com.google.android.finsky.widget;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WidgetConfigurationActivity extends Activity
{
  private String getCorpusName(int paramInt)
  {
    return getIntent().getStringExtra("name_" + paramInt);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968852);
    setResult(0);
    setTitle(getIntent().getIntExtra("dialog_title", 2131165393));
    GridView localGridView = (GridView)findViewById(2131231276);
    List localList = ((DfeToc)getIntent().getParcelableExtra("dfeToc")).getCorpusList();
    ArrayList localArrayList = Lists.newArrayList(localList.size());
    if (getIntent().getBooleanExtra("enableMultiCorpus", true))
    {
      Toc.CorpusMetadata localCorpusMetadata1 = new Toc.CorpusMetadata();
      localCorpusMetadata1.setBackend(0);
      localCorpusMetadata1.setName(getCorpusName(0));
      localArrayList.add(localCorpusMetadata1);
    }
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Toc.CorpusMetadata localCorpusMetadata2 = (Toc.CorpusMetadata)localIterator.next();
      String str1 = "backend_" + localCorpusMetadata2.getBackend();
      if (getIntent().getBooleanExtra(str1, true))
      {
        String str2 = getCorpusName(localCorpusMetadata2.getBackend());
        if (!TextUtils.isEmpty(str2))
          localCorpusMetadata2.setName(str2);
        localArrayList.add(localCorpusMetadata2);
      }
    }
    localGridView.setNumColumns(Math.min(localArrayList.size(), 3));
    localGridView.setAdapter(new CorpusAdapter(this, localArrayList));
  }

  private static class CorpusAdapter extends BaseAdapter
  {
    private final Activity mActivity;
    private final ActivityManager mActivityManager;
    private final List<Toc.CorpusMetadata> mCorpora;

    public CorpusAdapter(Activity paramActivity, List<Toc.CorpusMetadata> paramList)
    {
      this.mActivity = paramActivity;
      this.mCorpora = paramList;
      this.mActivityManager = ((ActivityManager)this.mActivity.getSystemService("activity"));
    }

    private int getBackend(int paramInt)
    {
      return getItem(paramInt).getBackend();
    }

    private int getBackendIcon(int paramInt)
    {
      int i;
      switch (paramInt)
      {
      case 5:
      default:
        i = 2130903047;
      case 3:
      case 2:
      case 1:
      case 6:
      case 4:
      }
      while (true)
      {
        return i;
        i = 2130903046;
        continue;
        i = 2130903043;
        continue;
        i = 2130903040;
        continue;
        i = 2130903042;
        continue;
        i = 2130903045;
      }
    }

    public int getCount()
    {
      return this.mCorpora.size();
    }

    public Toc.CorpusMetadata getItem(int paramInt)
    {
      return (Toc.CorpusMetadata)this.mCorpora.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return getBackend(paramInt);
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
        paramView = LayoutInflater.from(this.mActivity).inflate(2130968853, paramViewGroup, false);
      WidgetConfigurationActivity.Holder localHolder = (WidgetConfigurationActivity.Holder)paramView.getTag();
      if (localHolder == null)
        localHolder = new WidgetConfigurationActivity.Holder(paramView);
      final int i = getBackend(paramInt);
      Toc.CorpusMetadata localCorpusMetadata = getItem(paramInt);
      localHolder.name.setText(localCorpusMetadata.getName());
      int j = this.mActivityManager.getLauncherLargeIconDensity();
      int k = getBackendIcon(i);
      Drawable localDrawable = this.mActivity.getResources().getDrawableForDensity(k, j);
      localHolder.icon.setImageDrawable(localDrawable);
      localHolder.container.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.putExtra("backend", i);
          WidgetConfigurationActivity.CorpusAdapter.this.mActivity.setResult(-1, localIntent);
          WidgetConfigurationActivity.CorpusAdapter.this.mActivity.finish();
        }
      });
      return paramView;
    }
  }

  private static class Holder
  {
    final ViewGroup container;
    final ImageView icon;
    final TextView name;

    public Holder(View paramView)
    {
      this.container = ((ViewGroup)paramView.findViewById(2131231276));
      this.name = ((TextView)paramView.findViewById(2131231278));
      this.icon = ((ImageView)paramView.findViewById(2131231277));
      paramView.setTag(this);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.WidgetConfigurationActivity
 * JD-Core Version:    0.6.2
 */