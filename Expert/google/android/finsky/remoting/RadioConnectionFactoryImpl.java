package com.google.android.finsky.remoting;

import android.net.ConnectivityManager;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;

public class RadioConnectionFactoryImpl
  implements RadioConnectionFactory
{
  private static final RadioConnection NULL_RADIO_CONNECTION = new RadioConnection()
  {
    public void ensureRouteToHost(String paramAnonymousString)
    {
    }

    public void start()
    {
    }

    public void stop()
    {
    }
  };
  private static final GservicesValue<Boolean> USE_RADIO = GservicesValue.value("vending.use_radio", Boolean.valueOf(true));
  private final ConnectivityManager mConnMgr;

  public RadioConnectionFactoryImpl(ConnectivityManager paramConnectivityManager)
  {
    this.mConnMgr = paramConnectivityManager;
  }

  private RadioConnection createNewConnectionByType(ConnectionType paramConnectionType)
  {
    Object localObject;
    switch (2.$SwitchMap$com$google$android$finsky$remoting$RadioConnectionFactoryImpl$ConnectionType[paramConnectionType.ordinal()])
    {
    default:
      localObject = new RadioConnectionImpl(this.mConnMgr, 2, PhoneFeature.ENABLE_MMS);
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return localObject;
      localObject = new RadioConnectionWithFallback(new RadioConnectionImpl(this.mConnMgr, 5, PhoneFeature.ENABLE_HIPRI), new RadioConnectionImpl(this.mConnMgr, 2, PhoneFeature.ENABLE_MMS));
      continue;
      localObject = new RadioConnectionImpl(this.mConnMgr, 4, PhoneFeature.ENABLE_DUN);
      continue;
      localObject = new RadioConnectionImpl(this.mConnMgr, 3, PhoneFeature.ENABLE_SUPL);
    }
  }

  public RadioConnection createNewConnection()
  {
    if (!((Boolean)USE_RADIO.get()).booleanValue());
    ConnectionType localConnectionType;
    for (Object localObject = NULL_RADIO_CONNECTION; ; localObject = new StateHandleRadioConnection(createNewConnectionByType(localConnectionType)))
    {
      return localObject;
      localConnectionType = ConnectionType.parse((String)G.vendingDcbConnectionType.get());
      FinskyLog.d("Creating new RadioConnection of type " + localConnectionType.name(), new Object[0]);
    }
  }

  private static enum ConnectionType
  {
    static
    {
      DUN = new ConnectionType("DUN", 2);
      DEFAULT = new ConnectionType("DEFAULT", 3);
      ConnectionType[] arrayOfConnectionType = new ConnectionType[4];
      arrayOfConnectionType[0] = HIPRI;
      arrayOfConnectionType[1] = SUPL;
      arrayOfConnectionType[2] = DUN;
      arrayOfConnectionType[3] = DEFAULT;
    }

    public static ConnectionType parse(String paramString)
    {
      ConnectionType[] arrayOfConnectionType = values();
      int i = arrayOfConnectionType.length;
      int j = 0;
      ConnectionType localConnectionType;
      if (j < i)
      {
        localConnectionType = arrayOfConnectionType[j];
        if (!localConnectionType.name().equals(paramString));
      }
      while (true)
      {
        return localConnectionType;
        j++;
        break;
        localConnectionType = DEFAULT;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.RadioConnectionFactoryImpl
 * JD-Core Version:    0.6.2
 */