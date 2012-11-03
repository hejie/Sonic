package com.google.android.finsky.billing;

import com.android.i18n.addressinput.ClientCacheManager;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import java.io.UnsupportedEncodingException;

public class AddressMetadataCacheManager
  implements ClientCacheManager
{
  private final Cache mCache;

  public AddressMetadataCacheManager(Cache paramCache)
  {
    this.mCache = paramCache;
  }

  public String get(String paramString)
  {
    Cache.Entry localEntry = this.mCache.get("AddressMetadataCacheManager-" + paramString);
    String str;
    if ((localEntry == null) || (localEntry.isExpired()))
      str = "";
    while (true)
    {
      return str;
      try
      {
        str = new String(localEntry.data, "UTF-8");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
      }
    }
    throw new RuntimeException("UTF-8 not supported.");
  }

  public String getAddressServerUrl()
  {
    return (String)G.vendingAddressServerUrl.get();
  }

  public void put(String paramString1, String paramString2)
  {
    Cache.Entry localEntry = new Cache.Entry();
    try
    {
      localEntry.data = paramString2.getBytes("UTF-8");
      localEntry.serverDate = System.currentTimeMillis();
      localEntry.ttl = (604800000L + localEntry.serverDate);
      this.mCache.put("AddressMetadataCacheManager-" + paramString1, localEntry);
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new RuntimeException("UTF-8 not supported.");
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.AddressMetadataCacheManager
 * JD-Core Version:    0.6.2
 */