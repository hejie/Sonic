package com.google.android.finsky.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.UserManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Users
{
  private final UserManagerFacade mUserManagerFacade;

  public Users(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 17)
      this.mUserManagerFacade = new UserManagerFacade(null);
    while (true)
    {
      return;
      try
      {
        localObject = new UserManagerSystemFacade(paramContext);
        this.mUserManagerFacade = ((UserManagerFacade)localObject);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        while (true)
        {
          FinskyLog.wtf("Unable to reflect into UserManager: %s", new Object[] { localNoSuchMethodException });
          Object localObject = new UserManagerFacade(null);
        }
      }
    }
  }

  public boolean hasMultipleUsers()
  {
    if ((supportsMultiUser()) && (this.mUserManagerFacade.hasMultipleUsers()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean supportsMultiUser()
  {
    return this.mUserManagerFacade.supportsMultipleUsers();
  }

  private static class UserManagerFacade
  {
    public boolean hasMultipleUsers()
    {
      return false;
    }

    public boolean supportsMultipleUsers()
    {
      return false;
    }
  }

  private static class UserManagerSystemFacade extends Users.UserManagerFacade
  {
    private Method mGetUsers;
    private Method mSupportsMultipleUsers;
    private UserManager mUserManager;

    public UserManagerSystemFacade(Context paramContext)
      throws NoSuchMethodException
    {
      super();
      this.mUserManager = ((UserManager)paramContext.getSystemService("user"));
      Class localClass1 = this.mUserManager.getClass();
      Class[] arrayOfClass = new Class[0];
      Method localMethod1 = localClass1.getMethod("supportsMultipleUsers", arrayOfClass);
      Class localClass2 = localMethod1.getReturnType();
      if (!localClass2.equals(Boolean.TYPE))
        throw new NoSuchMethodException("Return type " + localClass2);
      this.mSupportsMultipleUsers = localMethod1;
      Method localMethod2 = localClass1.getMethod("getUsers", arrayOfClass);
      Class localClass3 = localMethod2.getReturnType();
      if (!localClass3.equals(List.class))
        throw new NoSuchMethodException("Return type " + localClass3);
      this.mGetUsers = localMethod2;
    }

    public boolean hasMultipleUsers()
    {
      try
      {
        int i = ((List)this.mGetUsers.invoke(this.mUserManager, (Object[])null)).size();
        if (i > 1);
        for (bool = true; ; bool = false)
          return bool;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        while (true)
          boolean bool = false;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        break label42;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        label42: break label42;
      }
    }

    public boolean supportsMultipleUsers()
    {
      try
      {
        boolean bool2 = ((Boolean)this.mSupportsMultipleUsers.invoke(this.mUserManager, (Object[])null)).booleanValue();
        bool1 = bool2;
        return bool1;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        while (true)
          boolean bool1 = false;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        break label30;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        label30: break label30;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.Users
 * JD-Core Version:    0.6.2
 */