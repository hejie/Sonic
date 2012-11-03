package com.google.android.finsky.widget.consumption;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.widget.RemoteViews;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.services.ConsumptionAppDataCache;
import com.google.android.finsky.services.ConsumptionAppDoc;
import com.google.android.finsky.services.LoadConsumptionDataService;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.TrampolineActivity;
import com.google.android.finsky.widget.WidgetTypeMap;
import com.google.android.finsky.widget.WidgetUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class NowPlayingWidgetProvider extends BaseWidgetProvider
{
  private static final int[] ACCESSIBILITY_OVERLAY_IDS;
  private static final int[] BACKENDS;
  private static final int[] CONTAINERS;
  private static final int[] IMAGE_IDS = { 2131231112, 2131231116, 2131231114, 2131231118 };
  private static final Block[] PORTRAIT_BLOCKS;
  private static final Block PORTRAIT_LARGE_REPLACEMENT;
  private static final Block[] SQUARE_BLOCKS = arrayOfBlock2;
  public static BatchedImageLoader mImageLoader;
  private final SparseIntArray mRowStartCounts = new SparseIntArray();

  static
  {
    ACCESSIBILITY_OVERLAY_IDS = new int[] { 2131231113, 2131231117, 2131231115, 2131231119 };
    BACKENDS = new int[] { 1, 4, 2, 6 };
    CONTAINERS = new int[] { 2131231126, 2131231128, 2131231127, 2131231129 };
    PORTRAIT_LARGE_REPLACEMENT = new Block(2130968748).sized(2131427474, 2131427475).hosting(4, 2131427476, 2131427477);
    Block[] arrayOfBlock1 = new Block[5];
    arrayOfBlock1[0] = new Block(2130968745).sized(2131427474, 2131427475).replaceLastOccurenceInRowWith(PORTRAIT_LARGE_REPLACEMENT).markToSupportMetadata();
    arrayOfBlock1[1] = new Block(2130968753).sized(2131427476, 2131427475).hosting(2, 2131427476, 2131427477);
    arrayOfBlock1[2] = new Block(2130968754).sized(2131427478, 2131427475).hosting(3, 2131427478, 2131427479);
    arrayOfBlock1[3] = new Block(2130968746).sized(2131427476, 2131427477);
    arrayOfBlock1[4] = new Block(2130968747).sized(2131427478, 2131427479);
    PORTRAIT_BLOCKS = arrayOfBlock1;
    Block[] arrayOfBlock2 = new Block[10];
    arrayOfBlock2[0] = new Block(2130968751).sized(2131427480, 2131427480).limitRowStartTo(1).markToSupportMetadata();
    arrayOfBlock2[1] = new Block(2130968749).sized(2131427481, 2131427481).markToSupportMetadata();
    arrayOfBlock2[2] = new Block(2130968755).sized(2131427482, 2131427480).hosting(2, 2131427482, 2131427482);
    arrayOfBlock2[3] = new Block(2130968750).sized(2131427482, 2131427482).limitRowStartTo(1).markToSupportMetadata();
    arrayOfBlock2[4] = new Block(2130968757).sized(2131427483, 2131427480).hosting(4, 2131427483, 2131427483);
    arrayOfBlock2[5] = new Block(2130968758).sized(2131427483, 2131427481).hosting(3, 2131427483, 2131427483);
    arrayOfBlock2[6] = new Block(2130968756).sized(2131427483, 2131427482).hosting(2, 2131427483, 2131427483);
    arrayOfBlock2[7] = new Block(2130968759).sized(2131427481, 2131427480).hosting(4).withChild(0, 2131427481, 2131427480).withChild(1, 2131427483, 2131427483).withChild(2, 2131427483, 2131427483).withChild(3, 2131427483, 2131427483).limitRowStartTo(0);
    arrayOfBlock2[8] = new Block(2130968760).sized(2131427482, 2131427481).hosting(3).withChild(0, 2131427482, 2131427482).withChild(1, 2131427483, 2131427483).withChild(2, 2131427483, 2131427483).limitRowStartTo(0);
    arrayOfBlock2[9] = new Block(2130968752).sized(2131427483, 2131427483).markToSupportMetadata();
  }

  protected static void fetchConsumptionDataIfNecessary(Context paramContext, int[] paramArrayOfInt)
  {
    ConsumptionAppDataCache localConsumptionAppDataCache = ConsumptionAppDataCache.get();
    int i = paramArrayOfInt.length;
    int[] arrayOfInt = new int[i];
    int j = 0;
    int k = 0;
    int m;
    int n;
    if (j < i)
    {
      m = paramArrayOfInt[j];
      if ((localConsumptionAppDataCache.hasConsumptionAppData(m)) || (localConsumptionAppDataCache.isLoadingData(m)))
      {
        if (!FinskyLog.DEBUG)
          break label129;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(m);
        FinskyLog.v("Data for [%s] is available or loading, skipping", arrayOfObject);
        n = k;
      }
    }
    while (true)
    {
      j++;
      k = n;
      break;
      localConsumptionAppDataCache.startLoading(m);
      n = k + 1;
      arrayOfInt[k] = m;
      continue;
      if (k > 0)
        LoadConsumptionDataService.scheduleAlarmForUpdate(paramContext, Arrays.copyOf(arrayOfInt, k));
      return;
      label129: n = k;
    }
  }

  private RemoteViews generateAccountNeededState(Context paramContext, int paramInt)
  {
    RemoteViews localRemoteViews1 = generateBaseTree(paramContext, true);
    RemoteViews localRemoteViews2 = new RemoteViews(paramContext.getPackageName(), 2130968744);
    localRemoteViews1.removeAllViews(2131231122);
    localRemoteViews1.addView(2131231122, localRemoteViews2);
    localRemoteViews1.setOnClickPendingIntent(2131231121, getAddAccountIntent(paramContext));
    localRemoteViews1.setViewVisibility(2131231121, 0);
    return localRemoteViews1;
  }

  private RemoteViews generateBaseTree(Context paramContext, boolean paramBoolean)
  {
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130968761);
    localRemoteViews.setTextViewText(2131231125, "");
    localRemoteViews.setImageViewResource(2131231124, getHeaderIconRes(0));
    int i = getEmptyBackgroundRes(0);
    if ((paramBoolean) && (i != 0))
      localRemoteViews.setImageViewResource(2131231120, i);
    localRemoteViews.removeAllViews(2131231122);
    localRemoteViews.setViewVisibility(2131231121, 8);
    localRemoteViews.setViewVisibility(2131230744, 8);
    return localRemoteViews;
  }

  private RemoteViews generateConfigurationState(Context paramContext, int paramInt)
  {
    RemoteViews localRemoteViews = generateBaseTree(paramContext, true);
    localRemoteViews.addView(2131231122, new RemoteViews(paramContext.getPackageName(), 2130968762));
    localRemoteViews.setOnClickPendingIntent(2131231121, TrampolineActivity.getLaunchIntent(paramContext, NowPlayingTrampoline.class, paramInt));
    localRemoteViews.setViewVisibility(2131231121, 0);
    return localRemoteViews;
  }

  private RemoteViews generateDisabledState(Context paramContext, int paramInt1, int paramInt2)
  {
    RemoteViews localRemoteViews1 = generateBaseTree(paramContext, true);
    RemoteViews localRemoteViews2 = new RemoteViews(paramContext.getPackageName(), 2130968762);
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      String str2 = paramContext.getString(2131165773, new Object[] { localPackageManager.getApplicationInfo(IntentUtils.getPackageName(paramInt2), 0).loadLabel(localPackageManager).toString() });
      str1 = str2;
      localRemoteViews2.setTextViewText(2131230788, str1);
      localRemoteViews1.addView(2131231122, localRemoteViews2);
      localRemoteViews1.setOnClickPendingIntent(2131231121, EnableAppReceiver.getEnableIntent(paramContext, paramInt2));
      localRemoteViews1.setViewVisibility(2131231121, 0);
      return localRemoteViews1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        String str1 = "";
    }
  }

  private RemoteViews generateUnavailableState(Context paramContext, int paramInt1, int paramInt2)
  {
    RemoteViews localRemoteViews1 = generateBaseTree(paramContext, true);
    RemoteViews localRemoteViews2 = new RemoteViews(paramContext.getPackageName(), 2130968762);
    localRemoteViews2.setTextViewText(2131230788, paramContext.getString(2131165774));
    localRemoteViews1.addView(2131231122, localRemoteViews2);
    String str = IntentUtils.getPackageName(paramInt2);
    Uri localUri = new Uri.Builder().scheme("https").authority("play.google.com").path("store/apps/details").appendQueryParameter("id", str).build();
    Intent localIntent = new Intent(paramContext, MainActivity.class);
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setData(localUri);
    localIntent.addFlags(268435456);
    localRemoteViews1.setOnClickPendingIntent(2131231121, PendingIntent.getActivity(paramContext, 0, localIntent, 0));
    localRemoteViews1.setViewVisibility(2131231121, 0);
    return localRemoteViews1;
  }

  private ViewTreeWrapper generateViewTree(int paramInt1, int paramInt2, DfeToc paramDfeToc, Context paramContext, Map<ImageBatch.ImageSpec, Bitmap> paramMap, int paramInt3, int paramInt4)
  {
    fetchConsumptionDataIfNecessary(paramContext, getBackends(paramInt1));
    RemoteViews localRemoteViews1 = generateBaseTree(paramContext, false);
    ViewTreeWrapper localViewTreeWrapper1 = new ViewTreeWrapper();
    ViewTreeWrapper.access$002(localViewTreeWrapper1, localRemoteViews1);
    localRemoteViews1.setTextViewText(2131231125, getTitleRes(paramDfeToc, paramContext, paramInt1).toUpperCase());
    localRemoteViews1.setImageViewResource(2131231124, getHeaderIconRes(paramInt1));
    Intent localIntent1 = getHeaderIntent(paramInt1);
    if (localIntent1 != null)
    {
      localRemoteViews1.setOnClickPendingIntent(2131230744, PendingIntent.getActivity(paramContext, paramInt2, localIntent1, 0));
      localRemoteViews1.setViewVisibility(2131230744, 0);
    }
    int i = paramContext.getResources().getInteger(2131492897);
    NowPlayingArranger.Arrangement localArrangement = NowPlayingArranger.arrange(NowPlayingScorer.score(getConsumptionDocLists(paramInt1), i, System.currentTimeMillis()), 0);
    int j = localArrangement.quadrantToData.length;
    int k;
    int i1;
    label225: int i3;
    int i8;
    int i12;
    label343: ViewTreeWrapper localViewTreeWrapper2;
    int i9;
    label474: int i10;
    label490: int i11;
    if (localArrangement.layoutVariant == 0)
    {
      k = 0;
      Object localObject = localRemoteViews1;
      if (j > 1)
      {
        int i13 = getLayout(localArrangement);
        localRemoteViews1.removeAllViews(2131231122);
        RemoteViews localRemoteViews2 = new RemoteViews(paramContext.getPackageName(), i13);
        localRemoteViews1.addView(2131231122, localRemoteViews2);
        localObject = localRemoteViews2;
      }
      ViewTreeWrapper.access$302(localViewTreeWrapper1, false);
      ViewTreeWrapper.access$402(localViewTreeWrapper1, true);
      int m = 0;
      n = -1;
      i1 = 0;
      if (i1 >= j)
        break label618;
      i3 = paramInt3;
      int i4 = paramInt4;
      int i5 = 2131231122;
      int i6 = localArrangement.quadrantToData.length;
      int i7 = localArrangement.layoutVariant;
      i8 = NowPlayingArranger.Arrangement.getLocation(i6, i1, i7);
      if (j > 1)
      {
        i5 = CONTAINERS[i1];
        i3 /= 2;
        if ((j == 4) || ((j == 3) && (i1 != k)))
        {
          if (paramInt4 / i3 <= 2.2F)
            break label572;
          if ((i8 & 0x1) == 0)
            break label545;
          if ((i8 & 0x4) == 0)
            break label534;
          i12 = paramInt4 / 3;
          i4 = i12 + m;
        }
      }
      localViewTreeWrapper2 = populateWidget((RemoteViews)localObject, paramInt2, i5, i8, paramContext, localArrangement.quadrantToData[i1], i3, i4, paramMap);
      if ((j == 4) || ((j == 3) && (i1 != k)))
        m = localViewTreeWrapper2.heightRemaining;
      ViewTreeWrapper.access$376(localViewTreeWrapper1, localViewTreeWrapper2.showBackground);
      ViewTreeWrapper.access$472(localViewTreeWrapper1, localViewTreeWrapper2.showEmptyBackground);
      if (!localViewTreeWrapper2.mLoadedSuccessfully)
        ViewTreeWrapper.access$102(localViewTreeWrapper1, false);
      localViewTreeWrapper1.mUris.addAll(localViewTreeWrapper2.mUris);
      if ((j != 1) && ((i8 & 0x1) == 0))
        break label581;
      i9 = 1;
      if ((j != 1) && ((i8 & 0x2) == 0))
        break label587;
      i10 = 1;
      if (i9 != 0)
      {
        if (i10 == 0)
          break label593;
        i11 = (paramInt3 - localViewTreeWrapper2.widestRowWidth) / 2;
        label512: if (n != -1)
          break label606;
      }
    }
    label534: label545: label572: label581: label587: label593: label606: for (int n = i11; ; n = Math.min(n, i11))
    {
      i1++;
      break label225;
      k = 1;
      break;
      i12 = paramInt4 * 2 / 3;
      break label343;
      if ((i8 & 0x4) != 0);
      for (i12 = paramInt4 * 2 / 3; ; i12 = paramInt4 / 3)
        break;
      i12 = paramInt4 / 2;
      break label343;
      i9 = 0;
      break label474;
      i10 = 0;
      break label490;
      i11 = i3 - localViewTreeWrapper2.widestRowWidth;
      break label512;
    }
    label618: localRemoteViews1.setViewPadding(2131231124, Math.max(0, n), 0, 0, paramContext.getResources().getDimensionPixelSize(2131427465));
    int i2 = 0;
    if (localViewTreeWrapper1.showBackground)
      i2 = getBackgroundRes(paramInt1);
    while (true)
    {
      if (i2 != 0)
        localRemoteViews1.setImageViewResource(2131231120, i2);
      return localViewTreeWrapper1;
      if (localViewTreeWrapper1.showEmptyBackground)
      {
        i2 = getEmptyBackgroundRes(paramInt1);
        if (paramInt1 != 0)
        {
          Intent localIntent2 = IntentUtils.buildConsumptionAppLaunchIntent(paramContext.getPackageManager(), paramInt1, FinskyApp.get().getCurrentAccountName());
          if (localIntent2 != null)
            localRemoteViews1.setOnClickPendingIntent(2131231121, PendingIntent.getActivity(paramContext, 0, localIntent2, 0));
          localRemoteViews1.setViewVisibility(2131231121, 0);
        }
      }
    }
  }

  private WidgetLayout generateWidgetLayout(final Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ArrayList localArrayList1 = Lists.newArrayList();
    int i = 0;
    int j;
    ArrayList localArrayList2;
    int k;
    int m;
    boolean bool2;
    label37: int i4;
    label57: Block localBlock4;
    if ((paramInt4 > 0) && (paramInt1 > i))
    {
      j = paramInt3;
      localArrayList2 = Lists.newArrayList();
      k = 0;
      m = 0;
      bool2 = true;
      if ((j > 0) && (paramInt1 > i))
      {
        if (k == 0)
        {
          i4 = paramInt4;
          localBlock4 = findLargestBlock(paramContext, j, i4, paramInt2, paramInt1 - i, bool2);
          if (localBlock4 != null)
            break label130;
        }
      }
      else
        if (m != 0)
          break label187;
    }
    else
    {
      Collections.sort(localArrayList1, new Comparator()
      {
        public int compare(List<Block> paramAnonymousList1, List<Block> paramAnonymousList2)
        {
          int i = 1;
          if (paramAnonymousList1 == paramAnonymousList2)
            i = 0;
          while (true)
          {
            return i;
            int j = getRowAverageHeight(paramAnonymousList1, paramContext);
            int k = getRowAverageHeight(paramAnonymousList2, paramContext);
            if (j != k)
            {
              if (j >= k)
                i = -1;
            }
            else
            {
              int m = getRowCellCount(paramAnonymousList1);
              int n = getRowCellCount(paramAnonymousList2);
              if (m != n)
              {
                if (m >= n)
                  i = -1;
              }
              else if (paramAnonymousList1.hashCode() <= paramAnonymousList2.hashCode())
                i = -1;
            }
          }
        }

        int getRowAverageHeight(List<Block> paramAnonymousList, Context paramAnonymousContext)
        {
          int i = 0;
          int j = 0;
          Resources localResources = paramAnonymousContext.getResources();
          Iterator localIterator = paramAnonymousList.iterator();
          while (localIterator.hasNext())
          {
            Block localBlock = (Block)localIterator.next();
            int k = localBlock.getNumImages();
            for (int m = 0; m < k; m++)
              j += localResources.getDimensionPixelSize(localBlock.getImageHeightRes(m));
            i += k;
          }
          return j / i;
        }

        int getRowCellCount(List<Block> paramAnonymousList)
        {
          int i = 0;
          Iterator localIterator = paramAnonymousList.iterator();
          while (localIterator.hasNext())
            i += ((Block)localIterator.next()).getNumImages();
          return i;
        }
      });
      if (i < paramInt1)
        break label479;
    }
    label130: label187: label479: for (boolean bool1 = true; ; bool1 = false)
    {
      return new WidgetLayout(localArrayList1, bool1, paramInt4);
      i4 = k;
      break label57;
      m++;
      i += localBlock4.getNumImages();
      localArrayList2.add(localBlock4);
      j -= localBlock4.getWidth(paramContext);
      int i5 = localBlock4.getHeight(paramContext);
      k = Math.max(k, i5);
      bool2 = false;
      break label37;
      HashMap localHashMap = Maps.newHashMap();
      Iterator localIterator1 = localArrayList2.iterator();
      while (localIterator1.hasNext())
      {
        Block localBlock3 = (Block)localIterator1.next();
        if (localBlock3.hasLastOccurenceInRowReplacement())
        {
          if (localHashMap.containsKey(localBlock3));
          for (int i3 = ((Integer)localHashMap.get(localBlock3)).intValue(); ; i3 = 0)
          {
            localHashMap.put(localBlock3, Integer.valueOf(i3 + 1));
            break;
          }
        }
      }
      Iterator localIterator2 = localHashMap.entrySet().iterator();
      while (localIterator2.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator2.next();
        if (((Integer)localEntry.getValue()).intValue() != 1)
        {
          Block localBlock1 = (Block)localEntry.getKey();
          int n = -1;
          for (int i1 = -1 + localArrayList2.size(); ; i1--)
            if (i1 >= 0)
            {
              if (localArrayList2.get(i1) == localBlock1)
                n = i1;
            }
            else
            {
              Block localBlock2 = localBlock1.getLastOccurenceInRowReplacement();
              int i2 = localBlock2.getNumImages() - localBlock1.getNumImages();
              if ((i2 > 0) && (paramInt1 - i < i2))
                break;
              localArrayList2.remove(n);
              localArrayList2.add(n, localBlock2);
              i += i2;
              break;
            }
        }
      }
      localArrayList1.add(localArrayList2);
      paramInt4 -= k;
      break;
    }
  }

  private static int[] getBackends(int paramInt)
  {
    if (paramInt == 0);
    for (int[] arrayOfInt = BACKENDS; ; arrayOfInt = new int[] { paramInt })
      return arrayOfInt;
  }

  private static int getBackgroundRes(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      i = 0;
    case 1:
    case 4:
    case 2:
    case 6:
    case 0:
    }
    while (true)
    {
      return i;
      i = 2130837513;
      continue;
      i = 2130837515;
      continue;
      i = 2130837516;
      continue;
      i = 2130837514;
      continue;
      i = 2130837517;
    }
  }

  private static Block[] getBlocks(int paramInt)
  {
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Invalid backend");
    case 1:
    case 4:
    case 6:
    case 2:
    case 3:
    }
    for (Block[] arrayOfBlock = PORTRAIT_BLOCKS; ; arrayOfBlock = SQUARE_BLOCKS)
      return arrayOfBlock;
  }

  private static Bitmap getCachedImage(Context paramContext, Map<ImageBatch.ImageSpec, Bitmap> paramMap, Uri paramUri, int paramInt1, int paramInt2)
  {
    int i = WidgetUtils.getDips(paramContext, paramInt1);
    int j = WidgetUtils.getDips(paramContext, paramInt2);
    Iterator localIterator = paramMap.keySet().iterator();
    ImageBatch.ImageSpec localImageSpec;
    do
    {
      if (!localIterator.hasNext())
        break;
      localImageSpec = (ImageBatch.ImageSpec)localIterator.next();
    }
    while (!localImageSpec.satisfies(paramUri, i, j));
    for (Bitmap localBitmap = (Bitmap)paramMap.get(localImageSpec); ; localBitmap = null)
      return localBitmap;
  }

  private static List<ConsumptionAppDoc> getConsumptionData(int paramInt)
  {
    return getDocsWithImages(ConsumptionAppDataCache.get().getConsumptionAppData(paramInt));
  }

  private static List<ConsumptionAppDocList> getConsumptionDocLists(int paramInt)
  {
    ArrayList localArrayList = Lists.newArrayList();
    for (int k : getBackends(paramInt))
    {
      List localList = getConsumptionData(k);
      if (!localList.isEmpty())
        localArrayList.add(new ConsumptionAppDocList(k, localList));
    }
    Collections.sort(localArrayList, ConsumptionAppDocList.NEWEST_FIRST);
    return localArrayList;
  }

  private static List<ConsumptionAppDoc> getDocsWithImages(List<ConsumptionAppDoc> paramList)
  {
    ArrayList localArrayList = Lists.newArrayList();
    if (paramList != null)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        ConsumptionAppDoc localConsumptionAppDoc = (ConsumptionAppDoc)localIterator.next();
        if (localConsumptionAppDoc.getImageUri() != null)
        {
          localArrayList.add(localConsumptionAppDoc);
        }
        else
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localConsumptionAppDoc.getDocId();
          FinskyLog.d("filtering out docId=[%s] because uri was null", arrayOfObject);
        }
      }
    }
    return localArrayList;
  }

  private static int getEmptyBackgroundRes(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      i = 0;
    case 1:
    case 4:
    case 2:
    case 6:
    case 0:
    }
    while (true)
    {
      return i;
      i = 2130837506;
      continue;
      i = 2130837511;
      continue;
      i = 2130837508;
      continue;
      i = 2130837507;
      continue;
      i = 2130837509;
    }
  }

  private static int getFallbackTitleRes(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      i = 2131165766;
    case 1:
    case 4:
    case 2:
    case 6:
    }
    while (true)
    {
      return i;
      i = 2131165767;
      continue;
      i = 2131165768;
      continue;
      i = 2131165769;
      continue;
      i = 2131165770;
    }
  }

  private static int getHeaderIconRes(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Invalid backend");
    case 1:
      i = 2130837666;
    case 4:
    case 2:
    case 6:
    case 0:
    case 3:
    }
    while (true)
    {
      return i;
      i = 2130837669;
      continue;
      i = 2130837670;
      continue;
      i = 2130837668;
      continue;
      i = 2130837671;
    }
  }

  private static Intent getHeaderIntent(int paramInt)
  {
    switch (paramInt)
    {
    case 5:
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 6:
    }
    for (Intent localIntent = null; ; localIntent = IntentUtils.buildConsumptionAppLaunchIntent(FinskyApp.get().getPackageManager(), paramInt, FinskyApp.get().getCurrentAccountName()))
      return localIntent;
  }

  private static BatchedImageLoader getImageLoader(Context paramContext)
  {
    if (mImageLoader == null)
      mImageLoader = new BatchedImageLoader(paramContext, FinskyApp.get().getBitmapCache());
    return mImageLoader;
  }

  private static int getLayout(NowPlayingArranger.Arrangement paramArrangement)
  {
    int i;
    switch (paramArrangement.quadrantToData.length)
    {
    default:
      throw new IllegalArgumentException("Invalid count: " + paramArrangement.quadrantToData.length);
    case 0:
    case 1:
      i = 0;
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return i;
      i = 2130968769;
      continue;
      if (paramArrangement.layoutVariant == 0)
      {
        i = 2130968767;
      }
      else
      {
        i = 2130968768;
        continue;
        i = 2130968765;
      }
    }
  }

  private static String getTitleRes(DfeToc paramDfeToc, Context paramContext, int paramInt)
  {
    String str;
    switch (paramInt)
    {
    case 1:
    case 2:
    default:
      if ((paramDfeToc != null) && (paramDfeToc.getCorpus(paramInt) != null))
        str = paramDfeToc.getCorpus(paramInt).getLibraryName();
      break;
    case 0:
    case 3:
    }
    while (true)
    {
      return str;
      str = paramContext.getString(2131165766);
      continue;
      str = paramContext.getString(2131165771);
      continue;
      str = paramContext.getString(getFallbackTitleRes(paramInt));
    }
  }

  private boolean isConsumptionAppDisabled(Context paramContext, int paramInt)
  {
    boolean bool = false;
    String str = IntentUtils.getPackageName(paramInt);
    if (str == null);
    while (true)
    {
      return bool;
      if (paramContext.getPackageManager().getApplicationEnabledSetting(str) == 3)
        bool = true;
    }
  }

  private List<ImageBatch.ImageSpec> mergePortAndLandRequests(List<ImageBatch.ImageSpec> paramList1, List<ImageBatch.ImageSpec> paramList2)
  {
    ArrayList localArrayList = Lists.newArrayList();
    if ((paramList2 != null) && (paramList1 != null))
    {
      List<ImageBatch.ImageSpec> localList1 = paramList2;
      List<ImageBatch.ImageSpec> localList2 = paramList1;
      if (paramList2.size() < paramList1.size())
      {
        localList1 = paramList1;
        localList2 = paramList2;
      }
      Iterator localIterator1 = localList1.iterator();
      while (localIterator1.hasNext())
      {
        ImageBatch.ImageSpec localImageSpec2 = (ImageBatch.ImageSpec)localIterator1.next();
        int j = 0;
        Iterator localIterator4 = localList2.iterator();
        while (localIterator4.hasNext())
        {
          ImageBatch.ImageSpec localImageSpec3 = (ImageBatch.ImageSpec)localIterator4.next();
          if (localImageSpec2.uri.equals(localImageSpec3.uri))
          {
            localArrayList.add(ImageBatch.ImageSpec.merge(localImageSpec3, localImageSpec2));
            j = 1;
          }
        }
        if (j == 0)
          localArrayList.add(localImageSpec2);
      }
      Iterator localIterator2 = localList2.iterator();
      while (localIterator2.hasNext())
      {
        ImageBatch.ImageSpec localImageSpec1 = (ImageBatch.ImageSpec)localIterator2.next();
        int i = 0;
        Iterator localIterator3 = localArrayList.iterator();
        while (localIterator3.hasNext())
          if (((ImageBatch.ImageSpec)localIterator3.next()).uri.equals(localImageSpec1.uri))
            i = 1;
        if (i == 0)
          localArrayList.add(localImageSpec1);
      }
    }
    return localArrayList;
  }

  private ViewTreeWrapper populateWidget(Context paramContext, RemoteViews paramRemoteViews, int paramInt1, int paramInt2, int paramInt3, List<List<Block>> paramList, List<ConsumptionAppDoc> paramList1, Map<ImageBatch.ImageSpec, Bitmap> paramMap)
  {
    Resources localResources = paramContext.getResources();
    ArrayList localArrayList1 = Lists.newArrayList();
    int i = 0;
    int j = 0;
    String str = paramContext.getPackageName();
    paramRemoteViews.removeAllViews(paramInt2);
    NowPlayingCellSorter localNowPlayingCellSorter = new NowPlayingCellSorter();
    localNowPlayingCellSorter.sort(paramList, localResources);
    for (int k = 0; ; k++)
    {
      int m = paramList.size();
      if (k >= m)
        break;
      List localList = (List)paramList.get(k);
      RemoteViews localRemoteViews1 = new RemoteViews(str, 2130968763);
      ArrayList localArrayList2 = Lists.newArrayList();
      for (int n = 0; n < localList.size(); n++)
      {
        Block localBlock = (Block)localList.get(n);
        RemoteViews localRemoteViews2 = new RemoteViews(str, localBlock.getLayoutId());
        int i5 = Math.min(IMAGE_IDS.length, localBlock.getNumImages());
        int i6 = 0;
        while (i6 < i5)
        {
          int i7 = localNowPlayingCellSorter.getSortedIndex(k, n, i6);
          if ((i7 < 0) || (i7 >= paramList1.size()))
          {
            i6++;
          }
          else
          {
            ConsumptionAppDoc localConsumptionAppDoc = (ConsumptionAppDoc)paramList1.get(i7);
            if (paramMap != null)
            {
              Bitmap localBitmap = getCachedImage(paramContext, paramMap, localConsumptionAppDoc.getImageUri(), localBlock.getImageWidthRes(i6), localBlock.getImageHeightRes(i6));
              localRemoteViews2.setImageViewBitmap(IMAGE_IDS[i6], localBitmap);
              if (localBitmap == null)
                i = 1;
              label271: int i8 = localResources.getDimensionPixelSize(localBlock.getImageWidthRes(i6));
              int i9 = localResources.getDimensionPixelSize(localBlock.getImageHeightRes(i6));
              ImageBatch.ImageSpec localImageSpec = new ImageBatch.ImageSpec(localConsumptionAppDoc.getImageUri(), i8, i9);
              localArrayList1.add(localImageSpec);
              if (localBlock.supportsMetadata())
              {
                if ((TextUtils.isEmpty(localConsumptionAppDoc.getReason())) || (j != 0))
                  break label415;
                localRemoteViews2.setTextViewText(2131231130, localConsumptionAppDoc.getReason().toUpperCase());
                localRemoteViews2.setViewVisibility(2131231130, 0);
                j = 1;
              }
            }
            while (true)
            {
              PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, i6, localConsumptionAppDoc.getViewIntent(), 0);
              localRemoteViews2.setOnClickPendingIntent(ACCESSIBILITY_OVERLAY_IDS[i6], localPendingIntent);
              break;
              i = 1;
              break label271;
              label415: localRemoteViews2.setViewVisibility(2131231130, 8);
            }
          }
        }
        localArrayList2.add(localRemoteViews2);
      }
      int i1;
      int i2;
      label464: int i3;
      if ((paramInt3 & 0x1) != 0)
      {
        i1 = 1;
        if ((paramInt3 & 0x2) == 0)
          break label558;
        i2 = 1;
        i3 = 1;
        if (i1 == 0)
          break label564;
        i3 = 5;
        label475: if (k % 2 != 0)
          break label575;
      }
      label558: label564: label575: for (int i4 = i3 | 0x50; ; i4 = i3 | 0x30)
      {
        localRemoteViews1.setInt(2131231123, "setGravity", i4);
        if (i1 != 0)
          Collections.reverse(localArrayList2);
        Iterator localIterator = localArrayList2.iterator();
        while (localIterator.hasNext())
          localRemoteViews1.addView(2131231123, (RemoteViews)localIterator.next());
        i1 = 0;
        break;
        i2 = 0;
        break label464;
        if (i2 == 0)
          break label475;
        i3 = 3;
        break label475;
      }
      paramRemoteViews.addView(paramInt2, localRemoteViews1);
    }
    if (i == 0);
    for (boolean bool = true; ; bool = false)
    {
      ViewTreeWrapper localViewTreeWrapper = new ViewTreeWrapper(null, bool, localArrayList1);
      return localViewTreeWrapper;
    }
  }

  protected Block findLargestBlock(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    Object localObject = null;
    Block[] arrayOfBlock = getBlocks(paramInt3);
    int i = arrayOfBlock.length;
    int j = 0;
    if (j < i)
    {
      Block localBlock = arrayOfBlock[j];
      int m = this.mRowStartCounts.get(localBlock.hashCode(), 0);
      if ((paramBoolean) && (localBlock.hasMaxRowStartCount()) && (m >= localBlock.getMaxRowStartCount()));
      label170: label176: label180: 
      while (true)
      {
        j++;
        break;
        int n = 1;
        int i1 = 1;
        if (localObject != null)
        {
          if (localBlock.getHeight(paramContext) <= localObject.getHeight(paramContext))
            break label170;
          n = 1;
          label105: if (localBlock.getWidth(paramContext) < localObject.getWidth(paramContext))
            break label176;
        }
        for (i1 = 1; ; i1 = 0)
        {
          if ((n == 0) || (localBlock.getHeight(paramContext) > paramInt2) || (i1 == 0) || (localBlock.getWidth(paramContext) > paramInt1) || (paramInt4 < localBlock.getNumImages()))
            break label180;
          localObject = localBlock;
          break;
          n = 0;
          break label105;
        }
      }
    }
    if ((localObject != null) && (paramBoolean))
    {
      int k = this.mRowStartCounts.get(localObject.hashCode(), 0);
      this.mRowStartCounts.put(localObject.hashCode(), k + 1);
    }
    return localObject;
  }

  public void onAppWidgetOptionsChanged(Context paramContext, AppWidgetManager paramAppWidgetManager, int paramInt, Bundle paramBundle)
  {
    updateWidgets(paramContext, paramAppWidgetManager, new int[] { paramInt });
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    super.onReceive(paramContext, paramIntent);
    if ((!"android.intent.action.PACKAGE_CHANGED".equals(paramIntent.getAction())) || (paramIntent.getIntExtra("android.intent.extra.UID", -1) == -1));
    while (true)
    {
      return;
      String str = paramIntent.getData().getSchemeSpecificPart();
      WidgetTypeMap localWidgetTypeMap = WidgetTypeMap.get(paramContext);
      int i = IntentUtils.getBackendId(str);
      if (i != -1)
      {
        int[] arrayOfInt = localWidgetTypeMap.getWidgets(NowPlayingWidgetProvider.class, WidgetUtils.translate(i));
        updateWidgets(paramContext, AppWidgetManager.getInstance(paramContext), arrayOfInt);
      }
    }
  }

  protected ViewTreeWrapper populateWidget(RemoteViews paramRemoteViews, int paramInt1, int paramInt2, int paramInt3, Context paramContext, ConsumptionAppDocList paramConsumptionAppDocList, int paramInt4, int paramInt5, Map<ImageBatch.ImageSpec, Bitmap> paramMap)
  {
    this.mRowStartCounts.clear();
    new WidgetLayout(null, true, paramInt5);
    WidgetLayout localWidgetLayout = generateWidgetLayout(paramContext, paramConsumptionAppDocList.size(), paramConsumptionAppDocList.getBackend(), paramInt4, paramInt5);
    ViewTreeWrapper localViewTreeWrapper = populateWidget(paramContext, paramRemoteViews, paramInt1, paramInt2, paramInt3, localWidgetLayout, paramConsumptionAppDocList, paramMap);
    ViewTreeWrapper.access$302(localViewTreeWrapper, localWidgetLayout.showBackground);
    ViewTreeWrapper.access$402(localViewTreeWrapper, paramConsumptionAppDocList.isEmpty());
    ViewTreeWrapper.access$502(localViewTreeWrapper, localWidgetLayout.heightRemaining);
    int i = 0;
    Iterator localIterator1 = localWidgetLayout.iterator();
    while (localIterator1.hasNext())
    {
      List localList = (List)localIterator1.next();
      int j = 0;
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
        j += ((Block)localIterator2.next()).getWidth(paramContext);
      i = Math.max(i, j);
    }
    ViewTreeWrapper.access$602(localViewTreeWrapper, i);
    return localViewTreeWrapper;
  }

  protected void updateWidgets(final Context paramContext, AppWidgetManager paramAppWidgetManager, Map<ImageBatch.ImageSpec, Bitmap> paramMap, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt == null)
      return;
    WidgetTypeMap localWidgetTypeMap = WidgetTypeMap.get(paramContext);
    int i = WidgetUtils.getDips(paramContext, 2131427466);
    int j = paramArrayOfInt.length;
    int k = 0;
    label29: final int m;
    if (k < j)
    {
      m = paramArrayOfInt[k];
      if (FinskyApp.get().getDfeApi() != null)
        break label71;
      paramAppWidgetManager.updateAppWidget(m, generateAccountNeededState(paramContext, m));
    }
    while (true)
    {
      k++;
      break label29;
      break;
      label71: String str = localWidgetTypeMap.get(getClass(), m);
      if (str == null)
      {
        paramAppWidgetManager.updateAppWidget(m, generateConfigurationState(paramContext, m));
      }
      else
      {
        int n = WidgetUtils.translate(str);
        if ((n != 0) && (!IntentUtils.isConsumptionAppInstalled(paramContext.getPackageManager(), n)))
        {
          paramAppWidgetManager.updateAppWidget(m, generateUnavailableState(paramContext, m, n));
        }
        else if (isConsumptionAppDisabled(paramContext, n))
        {
          paramAppWidgetManager.updateAppWidget(m, generateDisabledState(paramContext, m, n));
        }
        else
        {
          int[] arrayOfInt = getBoundingBoxes(paramContext, m);
          if ((arrayOfInt[0] == 0) && (arrayOfInt[1] == 0) && (arrayOfInt[2] == 0) && (arrayOfInt[3] == 0))
          {
            if (n == 0)
              warmImageCache(paramContext, n);
            paramAppWidgetManager.updateAppWidget(m, generateBaseTree(paramContext, true));
          }
          else
          {
            int i1 = arrayOfInt[0];
            int i2 = arrayOfInt[1] - i;
            int i3 = arrayOfInt[2];
            int i4 = arrayOfInt[3] - i;
            DfeToc localDfeToc = FinskyApp.get().getToc();
            ViewTreeWrapper localViewTreeWrapper1 = generateViewTree(n, m, localDfeToc, paramContext, paramMap, i3, i2);
            ViewTreeWrapper localViewTreeWrapper2 = generateViewTree(n, m, localDfeToc, paramContext, paramMap, i1, i4);
            RemoteViews localRemoteViews = new RemoteViews(localViewTreeWrapper1.mRemoteViews, localViewTreeWrapper2.mRemoteViews);
            if ((!localViewTreeWrapper1.mLoadedSuccessfully) || (!localViewTreeWrapper2.mLoadedSuccessfully))
            {
              List localList = mergePortAndLandRequests(localViewTreeWrapper2.mUris, localViewTreeWrapper1.mUris);
              getImageLoader(paramContext).enqueue(new ImageBatch(n, localList, new BatchedImageLoader.BatchedImageCallback()
              {
                public void onLoaded(Map<ImageBatch.ImageSpec, Bitmap> paramAnonymousMap)
                {
                  NowPlayingWidgetProvider localNowPlayingWidgetProvider = NowPlayingWidgetProvider.this;
                  Context localContext = paramContext;
                  AppWidgetManager localAppWidgetManager = AppWidgetManager.getInstance(paramContext);
                  int[] arrayOfInt = new int[1];
                  arrayOfInt[0] = m;
                  localNowPlayingWidgetProvider.updateWidgets(localContext, localAppWidgetManager, paramAnonymousMap, arrayOfInt);
                }
              }));
            }
            else
            {
              paramAppWidgetManager.updateAppWidget(m, localRemoteViews);
            }
          }
        }
      }
    }
  }

  protected void updateWidgets(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    updateWidgets(paramContext, paramAppWidgetManager, getImageLoader(paramContext).getCachedBitmaps(), paramArrayOfInt);
  }

  public void warmImageCache(Context paramContext, int paramInt)
  {
    List localList = getConsumptionDocLists(paramInt);
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ConsumptionAppDocList localConsumptionAppDocList = (ConsumptionAppDocList)localIterator.next();
      for (int i = 0; i < Math.min(4, localConsumptionAppDocList.size()); i++)
        localArrayList.add(new ImageBatch.ImageSpec(((ConsumptionAppDoc)localConsumptionAppDocList.get(i)).getImageUri(), 0, 0));
    }
    if (localArrayList.size() > 0)
    {
      if (FinskyLog.DEBUG)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        arrayOfObject[1] = Integer.valueOf(localArrayList.size());
        FinskyLog.v("Warming cache for %s with %d images", arrayOfObject);
      }
      getImageLoader(paramContext).enqueue(new ImageBatch(paramInt, localArrayList, null));
    }
  }

  private class ViewTreeWrapper
  {
    private int heightRemaining;
    private boolean mLoadedSuccessfully = true;
    private RemoteViews mRemoteViews;
    private List<ImageBatch.ImageSpec> mUris = Lists.newArrayList();
    private boolean showBackground = false;
    private boolean showEmptyBackground = false;
    private int widestRowWidth;

    public ViewTreeWrapper()
    {
    }

    public ViewTreeWrapper(boolean paramList, List<ImageBatch.ImageSpec> arg3)
    {
      this.mRemoteViews = paramList;
      boolean bool;
      this.mLoadedSuccessfully = bool;
      Object localObject;
      this.mUris = localObject;
    }
  }

  private class WidgetLayout extends ArrayList<List<Block>>
  {
    final int heightRemaining;
    final boolean showBackground;

    public WidgetLayout(boolean paramInt, int arg3)
    {
      boolean bool;
      this.showBackground = bool;
      int i;
      this.heightRemaining = i;
      if (paramInt != null)
        addAll(paramInt);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.consumption.NowPlayingWidgetProvider
 * JD-Core Version:    0.6.2
 */