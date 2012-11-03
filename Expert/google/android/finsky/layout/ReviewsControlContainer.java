package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.finsky.adapters.ReviewsAdapter.ChooseFilterOptionsHandler;
import com.google.android.finsky.api.model.DfeReviews;

public class ReviewsControlContainer extends LinearLayout
{
  private TextView mFilterBox;
  private Spinner mSortSpinner;

  public ReviewsControlContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setSortAdapter()
  {
    ArrayAdapter localArrayAdapter = new ArrayAdapter(getContext(), 2130968826);
    localArrayAdapter.setDropDownViewResource(17367049);
    localArrayAdapter.add(getContext().getString(2131165532));
    localArrayAdapter.add(getContext().getString(2131165533));
    localArrayAdapter.add(getContext().getString(2131165534));
    this.mSortSpinner.setAdapter(localArrayAdapter);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mSortSpinner = ((Spinner)findViewById(2131231223));
    this.mFilterBox = ((TextView)findViewById(2131231224));
    setSortAdapter();
  }

  public void setData(final DfeReviews paramDfeReviews)
  {
    this.mSortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramDfeReviews != null)
          switch (paramAnonymousInt)
          {
          default:
          case 0:
          case 1:
          case 2:
          }
        while (true)
        {
          return;
          paramDfeReviews.setSortType(2);
          continue;
          paramDfeReviews.setSortType(0);
          continue;
          paramDfeReviews.setSortType(1);
        }
      }

      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
      {
      }
    });
    switch (paramDfeReviews.getSortType())
    {
    default:
    case 2:
    case 0:
    case 1:
    }
    while (true)
    {
      return;
      this.mSortSpinner.setSelection(0);
      continue;
      this.mSortSpinner.setSelection(1);
      continue;
      this.mSortSpinner.setSelection(2);
    }
  }

  public void setFilterHandler(final ReviewsAdapter.ChooseFilterOptionsHandler paramChooseFilterOptionsHandler)
  {
    this.mFilterBox.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramChooseFilterOptionsHandler.onChooseFilterOptions();
      }
    });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ReviewsControlContainer
 * JD-Core Version:    0.6.2
 */