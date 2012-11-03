package com.google.android.finsky.layout;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.finsky.adapters.CorpusGridItem;
import com.google.android.finsky.adapters.DocumentGridItem;
import com.google.android.finsky.adapters.Graphic2x1TitleTopLeft;
import com.google.android.finsky.adapters.GraphicColoredTitle;
import com.google.android.finsky.adapters.ListGridItem;
import com.google.android.finsky.adapters.UnevenGridAdapter;
import com.google.android.finsky.adapters.UnevenGridAdapter.UnevenGridItem;
import com.google.android.finsky.adapters.UnevenGridItemType;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocAnnotations.Template;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GridSequencer
{
  private final UnevenGridAdapter mAdapter;
  private BitmapLoader mBitmapLoader;
  private final Context mContext;
  private String mCurrentPageUrl;
  private final int[] mLayoutSequence;
  private final NavigationManager mNavigationManager;
  private final DfeToc mToc;

  public GridSequencer(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, int[] paramArrayOfInt, DfeToc paramDfeToc, String paramString)
  {
    this.mContext = paramContext;
    this.mNavigationManager = paramNavigationManager;
    this.mBitmapLoader = paramBitmapLoader;
    this.mLayoutSequence = paramArrayOfInt;
    this.mCurrentPageUrl = paramString;
    this.mAdapter = new UnevenGridAdapter(this.mContext);
    this.mToc = paramDfeToc;
  }

  private SparseArray<List<Document>> bucketItemsBySize(List<Document> paramList)
  {
    SparseArray localSparseArray = new SparseArray();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Document localDocument = (Document)localIterator.next();
      int i = getGridSlotSize(localDocument);
      Object localObject = (List)localSparseArray.get(i);
      if (localObject == null)
      {
        localObject = Lists.newLinkedList();
        localSparseArray.put(i, localObject);
      }
      ((List)localObject).add(localDocument);
    }
    return localSparseArray;
  }

  private UnevenGridAdapter.UnevenGridItem getGridItem(Document paramDocument)
  {
    UnevenGridItemType localUnevenGridItemType = getTypeForItem(paramDocument);
    Object localObject;
    switch (1.$SwitchMap$com$google$android$finsky$adapters$UnevenGridItemType[localUnevenGridItemType.ordinal()])
    {
    default:
      throw new IllegalStateException("Unknown type: " + localUnevenGridItemType);
    case 1:
      localObject = DocumentGridItem.createSquarePromo(this.mContext, this.mNavigationManager, this.mBitmapLoader, paramDocument, this.mCurrentPageUrl);
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    while (true)
    {
      return localObject;
      localObject = DocumentGridItem.createSmallPromo(this.mContext, this.mNavigationManager, this.mBitmapLoader, paramDocument, this.mCurrentPageUrl);
      continue;
      localObject = new Graphic2x1TitleTopLeft(this.mNavigationManager, this.mBitmapLoader, paramDocument, this.mCurrentPageUrl);
      continue;
      localObject = DocumentGridItem.create4x2PromoGraphic(this.mContext, this.mNavigationManager, this.mBitmapLoader, paramDocument, this.mCurrentPageUrl);
      continue;
      localObject = new ListGridItem(this.mContext, paramDocument, this.mBitmapLoader, this.mNavigationManager, this.mCurrentPageUrl, this.mToc);
      continue;
      localObject = DocumentGridItem.create2x1PromoGraphic(this.mContext, this.mNavigationManager, this.mBitmapLoader, paramDocument, this.mCurrentPageUrl);
      continue;
      localObject = GraphicColoredTitle.create2x1(this.mContext, this.mNavigationManager, this.mBitmapLoader, paramDocument, this.mCurrentPageUrl);
      continue;
      localObject = GraphicColoredTitle.create4x2(this.mContext, this.mNavigationManager, this.mBitmapLoader, paramDocument, this.mCurrentPageUrl);
    }
  }

  private int getGridSlotSize(Document paramDocument)
  {
    UnevenGridItemType localUnevenGridItemType = getTypeForItem(paramDocument);
    int i;
    switch (1.$SwitchMap$com$google$android$finsky$adapters$UnevenGridItemType[localUnevenGridItemType.ordinal()])
    {
    default:
      throw new IllegalStateException("Unknown type: " + localUnevenGridItemType);
    case 2:
    case 3:
    case 6:
    case 7:
      i = 4;
    case 1:
    case 4:
    case 5:
    case 8:
    }
    while (true)
    {
      return i;
      i = 3;
      continue;
      i = 1;
    }
  }

  private UnevenGridItemType getTypeForItem(Document paramDocument)
  {
    DocAnnotations.Template localTemplate = paramDocument.getTemplate();
    UnevenGridItemType localUnevenGridItemType;
    if (localTemplate != null)
      if (localTemplate.hasTileDetailsReflectedGraphic2X2())
        localUnevenGridItemType = UnevenGridItemType.DOC_DETAILS_WITH_REFLECTED_PROMO_2x2;
    while (true)
    {
      return localUnevenGridItemType;
      if (localTemplate.hasTileFourBlock4X2())
        localUnevenGridItemType = UnevenGridItemType.LIST_FOUR_BLOCK_4x2;
      else if (localTemplate.hasTileGraphic2X1())
        localUnevenGridItemType = UnevenGridItemType.GRAPHIC_2x1;
      else if (localTemplate.hasTileGraphic4X2())
        localUnevenGridItemType = UnevenGridItemType.GRAPHIC_4x2;
      else if (localTemplate.hasTileGraphicColoredTitle2X1())
        localUnevenGridItemType = UnevenGridItemType.GRAPHIC_COLORED_TITLE_2x1;
      else if (localTemplate.hasTileGraphicColoredTitle4X2())
        localUnevenGridItemType = UnevenGridItemType.GRAPHIC_COLORED_TITLE_4x2;
      else if (localTemplate.hasTileGraphicUpperLeftTitle2X1())
        localUnevenGridItemType = UnevenGridItemType.GRAPHIC_2x1_TITLE_TOP_LEFT;
      else if (paramDocument.getContainerAnnotation() == null)
        localUnevenGridItemType = UnevenGridItemType.DOCUMENT_2x1;
      else
        localUnevenGridItemType = UnevenGridItemType.GRAPHIC_2x1_TITLE_TOP_LEFT;
    }
  }

  private List<UnevenGridAdapter.UnevenGridItem> updateGridItems(List<Document> paramList)
  {
    ArrayList localArrayList = Lists.newArrayList();
    SparseArray localSparseArray = bucketItemsBySize(paramList);
    int[] arrayOfInt = this.mLayoutSequence;
    int i = arrayOfInt.length;
    int j = 0;
    if (j < i)
    {
      int i2 = arrayOfInt[j];
      if (i2 == 2)
        localArrayList.add(new CorpusGridItem(this.mContext, this.mNavigationManager, this.mToc, this.mCurrentPageUrl));
      while (true)
      {
        j++;
        break;
        List localList2 = (List)localSparseArray.get(i2);
        if ((localList2 != null) && (!localList2.isEmpty()))
          localArrayList.add(getGridItem((Document)localList2.remove(0)));
      }
    }
    int k = 0;
    for (int m = 0; m < localSparseArray.size(); m++)
      k = Math.max(k, ((List)localSparseArray.get(localSparseArray.keyAt(m))).size());
    for (int n = 0; n < k; n++)
      for (int i1 = 0; i1 < localSparseArray.size(); i1++)
      {
        List localList1 = (List)localSparseArray.get(localSparseArray.keyAt(i1));
        if (!localList1.isEmpty())
          localArrayList.add(getGridItem((Document)localList1.remove(0)));
      }
    return localArrayList;
  }

  public UnevenGridAdapter getAdapter()
  {
    return this.mAdapter;
  }

  public void onDestroy()
  {
    this.mAdapter.onDestroy();
  }

  public void setPromoData(List<Document> paramList)
  {
    this.mAdapter.setData(updateGridItems(paramList));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.GridSequencer
 * JD-Core Version:    0.6.2
 */