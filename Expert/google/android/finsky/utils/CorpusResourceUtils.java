package com.google.android.finsky.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.List;

public class CorpusResourceUtils
{
  private static final SparseArray<SoftReference<Bitmap>> sPromoPlaceholders = new SparseArray();
  private static final SparseArray<SoftReference<Bitmap>> sThumbnailIcons = new SparseArray();

  public static int getAddedToLibraryDrawableId(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      i = 2130837604;
    case 4:
    case 2:
    case 1:
    case 6:
    }
    while (true)
    {
      return i;
      i = 2130837657;
      continue;
      i = 2130837658;
      continue;
      i = 2130837605;
      continue;
      i = 2130837623;
    }
  }

  public static int getBackendDarkColor(Context paramContext, int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      i = 2131361820;
    case 3:
    case 1:
    case 6:
    case 4:
    case 2:
    }
    while (true)
    {
      return paramContext.getResources().getColor(i);
      i = 2131361820;
      continue;
      i = 2131361821;
      continue;
      i = 2131361824;
      continue;
      i = 2131361822;
      continue;
      i = 2131361823;
    }
  }

  public static int getBackendForegroundColor(Context paramContext, int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      i = 2131361794;
    case 3:
    case 1:
    case 6:
    case 4:
    case 2:
    }
    while (true)
    {
      return paramContext.getResources().getColor(i);
      i = 2131361815;
      continue;
      i = 2131361816;
      continue;
      i = 2131361819;
      continue;
      i = 2131361817;
      continue;
      i = 2131361818;
    }
  }

  public static int getBackendHintColor(Context paramContext, int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      i = 2131361794;
    case 3:
    case 1:
    case 6:
    case 4:
    case 2:
    }
    while (true)
    {
      return paramContext.getResources().getColor(i);
      i = 2131361810;
      continue;
      i = 2131361811;
      continue;
      i = 2131361814;
      continue;
      i = 2131361812;
      continue;
      i = 2131361813;
    }
  }

  public static int getCorpusCellContentDescriptionResource(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Unsupported backend ID (" + paramInt + ")");
    case 4:
      i = 2131165662;
    case 6:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return i;
      i = 2131165663;
      continue;
      i = 2131165661;
    }
  }

  public static int getCorpusCellContentLongDescriptionResource(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Unsupported backend ID (" + paramInt + ")");
    case 4:
      i = 2131165665;
    case 6:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return i;
      i = 2131165666;
      continue;
      i = 2131165664;
    }
  }

  public static int getCorpusDetailsLayoutResource(int paramInt)
  {
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Unsupported backend ID (" + paramInt + ")");
    case 3:
    case 0:
    case 1:
    case 2:
    case 4:
    case 6:
    }
    for (int i = 2130968660; ; i = 2130968667)
      return i;
  }

  public static String getCorpusMyCollectionDescription(int paramInt)
  {
    String str = null;
    if (paramInt == 0)
      paramInt = 3;
    DfeToc localDfeToc = FinskyApp.get().getToc();
    if (localDfeToc == null)
      break label38;
    while (true)
    {
      return str;
      List localList = localDfeToc.getCorpusList();
      if (localList != null)
      {
        Iterator localIterator = localList.iterator();
        label38: if (localIterator.hasNext())
        {
          Toc.CorpusMetadata localCorpusMetadata = (Toc.CorpusMetadata)localIterator.next();
          if ((!localCorpusMetadata.hasBackend()) || (localCorpusMetadata.getBackend() != paramInt) || (!localCorpusMetadata.hasLibraryName()))
            break;
          str = localCorpusMetadata.getLibraryName();
        }
      }
    }
  }

  public static int getCorpusMyCollectionIcon(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      i = 2130837632;
    case 1:
    case 6:
    case 4:
    case 2:
    }
    while (true)
    {
      return i;
      i = 2130837633;
      continue;
      i = 2130837634;
      continue;
      i = 2130837635;
      continue;
      i = 2130837636;
    }
  }

  public static int getCorpusSpinnerDrawable(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      i = 2130837745;
    case 1:
    case 6:
    case 4:
    case 2:
    }
    while (true)
    {
      return i;
      i = 2130837746;
      continue;
      i = 2130837748;
      continue;
      i = 2130837749;
    }
  }

  public static int getCorpusWatermarkIconBig(int paramInt)
  {
    int i = 2130837624;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
    case 1:
    case 6:
    case 4:
    case 2:
    }
    while (true)
    {
      return i;
      i = 2130837625;
      continue;
      i = 2130837626;
      continue;
      i = 2130837627;
      continue;
      i = 2130837628;
    }
  }

  public static int getDescriptionHeaderStringId(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 4:
    }
    for (int i = 2131165494; ; i = 2131165492)
      return i;
  }

  public static int getHoloDarkOpenButtonDrawableId(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      throw new IllegalArgumentException("Unsupported backend ID (" + paramInt + ")");
    case 4:
      i = 2130837669;
    case 2:
    case 1:
    case 6:
    }
    while (true)
    {
      return i;
      i = 2130837670;
      continue;
      i = 2130837666;
      continue;
      i = 2130837668;
    }
  }

  public static int getLargeDetailsIconWidth(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    case 4:
    case 18:
    case 19:
    case 20:
    }
    for (int i = 2131427429; ; i = 2131427431)
      return paramContext.getResources().getDimensionPixelSize(i);
  }

  public static int getOpenButtonDrawableId(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      throw new IllegalArgumentException("Unsupported backend ID (" + paramInt + ")");
    case 4:
      i = 2130837609;
    case 2:
    case 1:
    case 6:
    }
    while (true)
    {
      return i;
      i = 2130837610;
      continue;
      i = 2130837607;
      continue;
      i = 2130837608;
    }
  }

  public static int getOpenButtonStringId(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      i = 2131165487;
    case 4:
    case 2:
    case 1:
    case 6:
    }
    while (true)
    {
      return i;
      i = 2131165444;
      continue;
      i = 2131165489;
      continue;
      i = 2131165445;
    }
  }

  public static Bitmap getPlaceholderIcon(int paramInt, Resources paramResources)
  {
    SoftReference localSoftReference = (SoftReference)sThumbnailIcons.get(paramInt);
    if ((localSoftReference == null) || (localSoftReference.get() == null))
    {
      localSoftReference = new SoftReference(BitmapFactory.decodeResource(paramResources, getPlaceholderIconResource(paramInt)));
      sThumbnailIcons.put(paramInt, localSoftReference);
    }
    return (Bitmap)localSoftReference.get();
  }

  private static int getPlaceholderIconResource(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Unsupported backend ID (" + paramInt + ")");
    case 1:
      i = 2130837688;
    case 6:
    case 2:
    case 4:
    case 0:
    case 3:
    }
    while (true)
    {
      return i;
      i = 2130837689;
      continue;
      i = 2130837691;
      continue;
      i = 2130837690;
      continue;
      i = 2130837687;
    }
  }

  public static Bitmap getPlaceholderPromo(int paramInt, Resources paramResources)
  {
    SoftReference localSoftReference = (SoftReference)sPromoPlaceholders.get(paramInt);
    if ((localSoftReference == null) || (localSoftReference.get() == null))
    {
      localSoftReference = new SoftReference(BitmapFactory.decodeResource(paramResources, getPlaceholderPromoResource(paramInt)));
      sPromoPlaceholders.put(paramInt, localSoftReference);
    }
    return (Bitmap)localSoftReference.get();
  }

  private static int getPlaceholderPromoResource(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Unsupported backend ID (" + paramInt + ")");
    case 1:
      i = 2130837717;
    case 6:
    case 2:
    case 4:
    case 3:
    }
    while (true)
    {
      return i;
      i = 2130837718;
      continue;
      i = 2130837720;
      continue;
      i = 2130837719;
      continue;
      i = 2130837716;
    }
  }

  public static int getRegularDetailsIconHeight(Context paramContext, int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Unsupported backend ID (" + paramInt + ")");
    case 3:
      i = 2131427352;
    case 1:
    case 4:
    case 6:
    case 0:
    case 2:
    }
    while (true)
    {
      return paramContext.getResources().getDimensionPixelSize(i);
      i = 2131427354;
      continue;
      i = 2131427353;
    }
  }

  public static int getShareHeaderId(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 2:
    }
    for (int i = 2131165560; ; i = 2131165561)
      return i;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.CorpusResourceUtils
 * JD-Core Version:    0.6.2
 */