package com.google.android.finsky.services;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IMarketCatalogService extends IInterface
{
  public abstract boolean isBackendEnabled(String paramString, int paramInt)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IMarketCatalogService
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.finsky.services.IMarketCatalogService");
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int i = 1;
      switch (paramInt1)
      {
      default:
        i = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        while (true)
        {
          return i;
          paramParcel2.writeString("com.google.android.finsky.services.IMarketCatalogService");
        }
      case 1:
      }
      paramParcel1.enforceInterface("com.google.android.finsky.services.IMarketCatalogService");
      boolean bool = isBackendEnabled(paramParcel1.readString(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      if (bool);
      int k;
      for (int j = i; ; k = 0)
      {
        paramParcel2.writeInt(j);
        break;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.IMarketCatalogService
 * JD-Core Version:    0.6.2
 */