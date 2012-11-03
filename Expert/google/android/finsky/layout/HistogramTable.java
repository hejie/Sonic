package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.android.vending.R.styleable;
import java.text.NumberFormat;

public class HistogramTable extends TableLayout
{
  private String[] mLabels;
  private int mMaxWidth;

  public HistogramTable(Context paramContext)
  {
    this(paramContext, null);
  }

  public HistogramTable(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    int[] arrayOfInt = { 2131165526, 2131165527, 2131165528, 2131165529, 2131165530 };
    Resources localResources = paramContext.getResources();
    this.mLabels = new String[5];
    for (int i = 0; i < 5; i++)
      this.mLabels[i] = localResources.getString(arrayOfInt[i]);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.HistogramTable);
    this.mMaxWidth = localTypedArray.getDimensionPixelSize(0, -1);
    localTypedArray.recycle();
  }

  public int getMaxWidth()
  {
    return this.mMaxWidth;
  }

  public void setHistogram(int[] paramArrayOfInt)
  {
    removeAllViews();
    double d = 0.0D;
    for (int i = 0; i < 5; i++)
      if (paramArrayOfInt[i] > d)
        d = paramArrayOfInt[i];
    LayoutInflater localLayoutInflater = LayoutInflater.from(getContext());
    NumberFormat localNumberFormat = NumberFormat.getIntegerInstance();
    for (int j = 0; j < 5; j++)
    {
      TableRow localTableRow = (TableRow)localLayoutInflater.inflate(2130968715, this, false);
      ((TextView)localTableRow.findViewById(2131231064)).setText(this.mLabels[j]);
      ((TextView)localTableRow.findViewById(2131231066)).setText(localNumberFormat.format(paramArrayOfInt[j]));
      ((HistogramBar)localTableRow.findViewById(2131231065)).setWidthPercentage(paramArrayOfInt[j] / d);
      addView(localTableRow);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.HistogramTable
 * JD-Core Version:    0.6.2
 */