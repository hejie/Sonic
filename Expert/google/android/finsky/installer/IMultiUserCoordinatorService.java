package com.google.android.finsky.installer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IMultiUserCoordinatorService extends IInterface
{
  public abstract boolean acquirePackage(String paramString)
    throws RemoteException;

  public abstract void registerListener(IMultiUserCoordinatorServiceListener paramIMultiUserCoordinatorServiceListener)
    throws RemoteException;

  public abstract void releaseAllPackages()
    throws RemoteException;

  public abstract void releasePackage(String paramString)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IMultiUserCoordinatorService
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.finsky.installer.IMultiUserCoordinatorService");
    }

    public static IMultiUserCoordinatorService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
        if ((localIInterface != null) && ((localIInterface instanceof IMultiUserCoordinatorService)))
          localObject = (IMultiUserCoordinatorService)localIInterface;
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
      int i = 1;
      switch (paramInt1)
      {
      default:
        i = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      case 2:
      case 3:
      case 4:
      }
      while (true)
      {
        return i;
        paramParcel2.writeString("com.google.android.finsky.installer.IMultiUserCoordinatorService");
        continue;
        paramParcel1.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
        registerListener(IMultiUserCoordinatorServiceListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
        boolean bool = acquirePackage(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (bool);
        int k;
        for (int j = i; ; k = 0)
        {
          paramParcel2.writeInt(j);
          break;
        }
        paramParcel1.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
        releasePackage(paramParcel1.readString());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
        releaseAllPackages();
        paramParcel2.writeNoException();
      }
    }

    private static class Proxy
      implements IMultiUserCoordinatorService
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public boolean acquirePackage(String paramString)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorService");
          localParcel1.writeString(paramString);
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public void registerListener(IMultiUserCoordinatorServiceListener paramIMultiUserCoordinatorServiceListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorService");
          if (paramIMultiUserCoordinatorServiceListener != null)
          {
            localIBinder = paramIMultiUserCoordinatorServiceListener.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void releaseAllPackages()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorService");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void releasePackage(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorService");
          localParcel1.writeString(paramString);
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.installer.IMultiUserCoordinatorService
 * JD-Core Version:    0.6.2
 */