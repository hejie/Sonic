package com.google.android.finsky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.android.finsky.remoting.protos.Search.RelatedSearch;
import java.util.List;

public class SearchSpinnerAdapter extends ArrayAdapter<Search.RelatedSearch>
{
  public SearchSpinnerAdapter(Context paramContext, List<Search.RelatedSearch> paramList)
  {
    super(paramContext, 2130968835, paramList.toArray(new Search.RelatedSearch[0]));
  }

  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = LayoutInflater.from(getContext()).inflate(2130968835, paramViewGroup, false);
    Search.RelatedSearch localRelatedSearch = (Search.RelatedSearch)getItem(paramInt);
    ((TextView)paramView.findViewById(2131231252)).setText(localRelatedSearch.getHeader().toUpperCase());
    if (localRelatedSearch.getCurrent())
      paramView.setBackgroundResource(2131361840);
    while (true)
    {
      return paramView;
      paramView.setBackgroundResource(2130837573);
    }
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = LayoutInflater.from(getContext()).inflate(2130968836, paramViewGroup, false);
    Search.RelatedSearch localRelatedSearch = (Search.RelatedSearch)getItem(paramInt);
    ((TextView)localView).setText(localRelatedSearch.getHeader().toUpperCase());
    return localView;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.SearchSpinnerAdapter
 * JD-Core Version:    0.6.2
 */