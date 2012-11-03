package com.google.android.finsky.widget.consumption;

import android.util.SparseIntArray;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.widget.WidgetUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NowPlayingArranger
{
  private static final int[][][] PERMUTATIONS = { new int[0][], { { 0 } }, { { 0, 1 }, { 1, 0 } }, { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 2, 0, 1 } }, { { 0, 1, 2, 3 }, { 0, 1, 3, 2 }, { 0, 2, 1, 3 }, { 0, 2, 3, 1 }, { 0, 3, 1, 2 }, { 0, 3, 2, 1 }, { 1, 0, 3, 2 }, { 1, 0, 2, 3 }, { 1, 2, 3, 0 }, { 1, 2, 0, 3 }, { 1, 3, 2, 0 }, { 1, 3, 0, 2 }, { 2, 0, 1, 3 }, { 2, 0, 3, 1 }, { 2, 1, 0, 3 }, { 2, 1, 3, 0 }, { 2, 3, 0, 1 }, { 2, 3, 1, 0 }, { 3, 0, 2, 1 }, { 3, 0, 1, 2 }, { 3, 1, 2, 0 }, { 3, 1, 0, 2 }, { 3, 2, 1, 0 }, { 3, 2, 0, 1 } } };
  private static SparseIntArray sBottomAffinity;
  private static Map<String, int[]> sCachedArrangements = Maps.newHashMap();
  private static boolean sInitialized = false;
  private static SparseIntArray sLeftAffinity;
  private static SparseIntArray sRightAffinity;
  private static SparseIntArray sTopAffinity;

  public static Arrangement arrange(List<ConsumptionAppDocList> paramList, int paramInt)
  {
    initialize();
    int i = paramList.size();
    int[] arrayOfInt1 = new int[i];
    for (int j = 0; j < arrayOfInt1.length; j++)
      arrayOfInt1[j] = ((ConsumptionAppDocList)paramList.get(j)).getBackend();
    Arrangement localArrangement;
    if (i == 0)
      localArrangement = new Arrangement(new ConsumptionAppDocList[0], paramInt);
    while (true)
    {
      return localArrangement;
      if (i == 1)
      {
        localArrangement = new Arrangement((ConsumptionAppDocList[])paramList.toArray(new ConsumptionAppDocList[1]), paramInt);
      }
      else
      {
        int[] arrayOfInt2 = getCachedCandidate(arrayOfInt1, paramInt);
        int k;
        if (arrayOfInt2 != null)
        {
          k = 1;
          if (k == 0)
          {
            if (FinskyLog.DEBUG)
              FinskyLog.v("Arrangement cache miss, computing from scratch.", new Object[0]);
            arrayOfInt2 = computeBestCandidate(arrayOfInt1, paramInt);
          }
          if (i != 3)
            break label252;
        }
        ConsumptionAppDocList[] arrayOfConsumptionAppDocList;
        label252: for (int m = determineLayoutVariant3(arrayOfInt1, arrayOfInt2); ; m = paramInt)
        {
          if (k == 0)
            putCachedCandidate(arrayOfInt1, paramInt, arrayOfInt2, m);
          arrayOfConsumptionAppDocList = new ConsumptionAppDocList[i];
          for (int n = 0; n < arrayOfInt2.length; n++)
          {
            int i1 = arrayOfInt2[n];
            Iterator localIterator = paramList.iterator();
            while (localIterator.hasNext())
            {
              ConsumptionAppDocList localConsumptionAppDocList = (ConsumptionAppDocList)localIterator.next();
              if (localConsumptionAppDocList.getBackend() == i1)
                arrayOfConsumptionAppDocList[n] = localConsumptionAppDocList;
            }
          }
          k = 0;
          break;
        }
        if (FinskyLog.DEBUG)
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = Arrays.toString(arrayOfInt2);
          arrayOfObject[1] = Integer.valueOf(m);
          FinskyLog.v("Widget arrangement: quadrants=%s, layoutVariant=%d", arrayOfObject);
        }
        localArrangement = new Arrangement(arrayOfConsumptionAppDocList, m);
      }
    }
  }

  private static int[] computeBestCandidate(int[] paramArrayOfInt, int paramInt)
  {
    int i = -1;
    int j = paramArrayOfInt.length;
    int[] arrayOfInt1 = new int[j];
    int[] arrayOfInt2 = new int[j];
    int[][] arrayOfInt = PERMUTATIONS[j];
    int k = arrayOfInt.length;
    for (int m = 0; m < k; m++)
    {
      permute(paramArrayOfInt, arrayOfInt[m], arrayOfInt1);
      int n = paramInt;
      if (j == 3)
        n = determineLayoutVariant3(paramArrayOfInt, arrayOfInt1);
      int i1 = getScore(arrayOfInt1, n);
      if (FinskyLog.DEBUG)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Arrays.toString(arrayOfInt1);
        arrayOfObject[1] = Integer.valueOf(i1);
        FinskyLog.v("Score for candidate %s: %d", arrayOfObject);
      }
      if (i1 > i)
      {
        System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, arrayOfInt2.length);
        i = i1;
      }
    }
    return arrayOfInt2;
  }

  private static int determineLayoutVariant3(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = 0;
    if (paramArrayOfInt1[0] == paramArrayOfInt2[0]);
    while (true)
    {
      return i;
      i = 1;
    }
  }

  private static String getCacheKey(int[] paramArrayOfInt, int paramInt)
  {
    return Arrays.toString(paramArrayOfInt) + "/" + paramInt;
  }

  private static int[] getCachedCandidate(int[] paramArrayOfInt, int paramInt)
  {
    try
    {
      int[] arrayOfInt = (int[])sCachedArrangements.get(getCacheKey(paramArrayOfInt, paramInt));
      return arrayOfInt;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static int getScore(int paramInt1, int paramInt2)
  {
    int i = 0;
    if ((paramInt2 & 0x4) != 0);
    try
    {
      i = 0 + sTopAffinity.get(paramInt1, 0);
      if ((paramInt2 & 0x8) != 0)
        i += sBottomAffinity.get(paramInt1, 0);
      if ((paramInt2 & 0x1) != 0)
        i += sLeftAffinity.get(paramInt1, 0);
      if ((paramInt2 & 0x2) != 0)
      {
        int j = sRightAffinity.get(paramInt1, 0);
        i += j;
      }
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static int getScore(int[] paramArrayOfInt, int paramInt)
  {
    int i = 0;
    int j = paramArrayOfInt.length;
    for (int k = 0; k < j; k++)
      i += getScore(paramArrayOfInt[k], Arrangement.getLocation(j, k, paramInt));
    return i;
  }

  private static void increaseAndDecrease(SparseIntArray paramSparseIntArray1, SparseIntArray paramSparseIntArray2, int paramInt)
  {
    paramSparseIntArray1.put(paramInt, Math.min(1 + paramSparseIntArray1.get(paramInt, 0), 10));
    paramSparseIntArray2.put(paramInt, Math.max(-1 + paramSparseIntArray2.get(paramInt, 0), 0));
  }

  private static void initialize()
  {
    try
    {
      if (!sInitialized)
      {
        sTopAffinity = WidgetUtils.parseSparseIntArray((String)FinskyPreferences.myLibraryWidgetTopAffinity.get());
        sBottomAffinity = WidgetUtils.parseSparseIntArray((String)FinskyPreferences.myLibraryWidgetBottomAffinity.get());
        sLeftAffinity = WidgetUtils.parseSparseIntArray((String)FinskyPreferences.myLibraryWidgetLeftAffinity.get());
        sRightAffinity = WidgetUtils.parseSparseIntArray((String)FinskyPreferences.myLibraryWidgetRightAffinity.get());
        sInitialized = true;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static void permute(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    for (int i = 0; i < paramArrayOfInt2.length; i++)
      paramArrayOfInt3[i] = paramArrayOfInt1[paramArrayOfInt2[i]];
  }

  private static void putCachedCandidate(int[] paramArrayOfInt1, int paramInt1, int[] paramArrayOfInt2, int paramInt2)
  {
    try
    {
      sCachedArrangements.put(getCacheKey(paramArrayOfInt1, paramInt1), paramArrayOfInt2);
      for (int i = 0; i < paramArrayOfInt2.length; i++)
        updateAffinity(paramArrayOfInt2[i], Arrangement.getLocation(paramArrayOfInt1.length, i, paramInt2));
      writeAffinities();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static void updateAffinity(int paramInt1, int paramInt2)
  {
    if ((paramInt2 & 0x4) != 0);
    try
    {
      increaseAndDecrease(sTopAffinity, sBottomAffinity, paramInt1);
      if ((paramInt2 & 0x8) != 0)
        increaseAndDecrease(sBottomAffinity, sTopAffinity, paramInt1);
      if ((paramInt2 & 0x1) != 0)
        increaseAndDecrease(sLeftAffinity, sRightAffinity, paramInt1);
      if ((paramInt2 & 0x2) != 0)
        increaseAndDecrease(sRightAffinity, sLeftAffinity, paramInt1);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static void writeAffinities()
  {
    FinskyPreferences.myLibraryWidgetTopAffinity.put(WidgetUtils.serializeSparseIntArray(sTopAffinity));
    FinskyPreferences.myLibraryWidgetBottomAffinity.put(WidgetUtils.serializeSparseIntArray(sBottomAffinity));
    FinskyPreferences.myLibraryWidgetLeftAffinity.put(WidgetUtils.serializeSparseIntArray(sLeftAffinity));
    FinskyPreferences.myLibraryWidgetRightAffinity.put(WidgetUtils.serializeSparseIntArray(sRightAffinity));
  }

  public static class Arrangement
  {
    private static final byte[] LOCATION_FLAGS_2_HORIZONTAL = { 1, 2 };
    private static final byte[] LOCATION_FLAGS_2_VERTICAL = { 4, 8 };
    private static final byte[] LOCATION_FLAGS_3_STRETCH_FIRST = { 1, 6, 10 };
    private static final byte[] LOCATION_FLAGS_3_STRETCH_SECOND = { 5, 2, 9 };
    private static final byte[] LOCATION_FLAGS_4 = { 5, 6, 9, 10 };
    public int layoutVariant;
    public final ConsumptionAppDocList[] quadrantToData;

    public Arrangement(ConsumptionAppDocList[] paramArrayOfConsumptionAppDocList, int paramInt)
    {
      this.quadrantToData = paramArrayOfConsumptionAppDocList;
      this.layoutVariant = paramInt;
    }

    public static byte getLocation(int paramInt1, int paramInt2, int paramInt3)
    {
      byte b = 0;
      if (paramInt1 == 1);
      while (true)
      {
        return b;
        if ((paramInt1 == 2) && (paramInt3 == 0))
          b = LOCATION_FLAGS_2_HORIZONTAL[paramInt2];
        else if ((paramInt1 == 2) && (paramInt3 == 1))
          b = LOCATION_FLAGS_2_VERTICAL[paramInt2];
        else if ((paramInt1 == 3) && (paramInt3 == 0))
          b = LOCATION_FLAGS_3_STRETCH_FIRST[paramInt2];
        else if ((paramInt1 == 3) && (paramInt3 == 1))
          b = LOCATION_FLAGS_3_STRETCH_SECOND[paramInt2];
        else if (paramInt1 == 4)
          b = LOCATION_FLAGS_4[paramInt2];
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.consumption.NowPlayingArranger
 * JD-Core Version:    0.6.2
 */