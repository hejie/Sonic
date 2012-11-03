package com.google.android.finsky.installer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IMultiUserCoordinatorServiceListener extends IInterface
{
  public abstract void packageAcquired(String paramString)
    throws RemoteException;

  public abstract void packageReleased(String paramString)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IMultiUserCoordinatorServiceListener
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
    }

    public static IMultiUserCoordinatorServiceListener asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
        if ((localIInterface != null) && ((localIInterface instanceof IMultiUserCoordinatorServiceListener)))
          localObject = (IMultiUserCoordinatorServiceListener)localIInterface;
        else
          localObject = new Proxy(paramIBinder);
      }
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool = true;
      switch (paramInt1)
      {
      default:
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      case 2:
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
        continue;
        paramParcel1.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
        packageAcquired(paramParcel1.readString());
        continue;
        paramParcel1.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
        packageReleased(paramParcel1.readString());
      }
    }

    private static class Proxy
      implements IMultiUserCoordinatorServiceListener
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public void packageAcquired(String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
          localParcel.writeString(paramString);
          this.mRemote.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }

      public void packageReleased(String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
          localParcel.writeString(paramString);
          this.mRemote.transact(2, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener
 * JD-Core Version:    0.6.2
 */