package com.google.android.finsky.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.Toc.TocResponse;
import com.google.android.finsky.utils.ApplicationDismissedDeferrer;
import com.google.android.finsky.utils.DeviceConfigurationHelper;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.SelfUpdateScheduler;
import com.google.android.finsky.utils.Users;
import com.google.android.finsky.utils.Utils;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;
import java.io.IOException;

public abstract class AuthenticatedActivity extends FragmentActivity
  implements BackgroundDataDialog.BackgroundDataSettingListener
{
  private static final boolean ADD_ACCOUNT_SUPPORTS_CUSTOM_MESSAGE;
  protected static boolean sSwitchToMyApps;
  private final DialogInterface.OnClickListener mDeclineCreateAccountListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      AuthenticatedActivity.this.finish();
    }
  };
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private boolean mJustReturnedFromDialog = false;
  private final DialogInterface.OnClickListener mOnClickCreateAccountListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      AuthenticatedActivity.this.addAccount();
      paramAnonymousDialogInterface.cancel();
    }
  };
  private boolean mRestartRequired;
  boolean mStateSaved;
  private Request<?> mUploadDeviceConfigRequest;
  private boolean mWaitingForUserInput;

  static
  {
    if (Build.VERSION.SDK_INT >= 14);
    for (boolean bool = true; ; bool = false)
    {
      ADD_ACCOUNT_SUPPORTS_CUSTOM_MESSAGE = bool;
      return;
    }
  }

  private void addAccount()
  {
    AccountManager.get(this).addAccount("com.google", "androidmarket", null, createAddAccountOptions(this), null, new AccountManagerCallback()
    {
      public void run(AccountManagerFuture<Bundle> paramAnonymousAccountManagerFuture)
      {
        try
        {
          Intent localIntent = (Intent)((Bundle)paramAnonymousAccountManagerFuture.getResult()).getParcelable("intent");
          AuthenticatedActivity.this.startActivityForResult(localIntent, 21);
          AuthenticatedActivity.access$502(AuthenticatedActivity.this, true);
          return;
        }
        catch (OperationCanceledException localOperationCanceledException)
        {
          while (true)
          {
            FinskyLog.d("Account add canceled. Finishing.", new Object[0]);
            AuthenticatedActivity.this.finish();
          }
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            FinskyLog.d("IOException while adding account: %s. Finishing.", new Object[] { localIOException });
            AuthenticatedActivity.this.finish();
          }
        }
        catch (AuthenticatorException localAuthenticatorException)
        {
          while (true)
          {
            FinskyLog.d("AuthenticatorException while adding account: %s. Finishing.", new Object[] { localAuthenticatorException });
            AuthenticatedActivity.this.finish();
          }
        }
      }
    }
    , null);
  }

  private void checkTosAcceptanceAndContinue(boolean paramBoolean)
  {
    if (!displayTos())
      fireOnReadyRunnable(paramBoolean);
  }

  private static String[] convertToStringArray(Account[] paramArrayOfAccount)
  {
    String[] arrayOfString = new String[paramArrayOfAccount.length];
    for (int i = 0; i < paramArrayOfAccount.length; i++)
      arrayOfString[i] = paramArrayOfAccount[i].name;
    return arrayOfString;
  }

  public static Bundle createAddAccountOptions(Context paramContext)
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("pendingIntent", PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0));
    localBundle.putString("introMessage", paramContext.getString(2131165417));
    localBundle.putBoolean("allowSkip", false);
    return localBundle;
  }

  private String determineAccount()
  {
    String str1 = getIntent().getStringExtra("authAccount");
    Account localAccount2;
    String str2;
    if (str1 != null)
    {
      localAccount2 = AccountHandler.findAccount(str1, this);
      if (localAccount2 == null)
      {
        FinskyLog.wtf("This app was called with an intent that specified the account %s, which is not a valid account on this device", new Object[] { str1 });
        finish();
        str2 = null;
      }
    }
    while (true)
    {
      return str2;
      str2 = localAccount2.name;
      continue;
      str2 = FinskyApp.get().getCurrentAccountName();
      if (!AccountHandler.hasAccount(str2, this))
      {
        Account localAccount1 = AccountHandler.getFirstAccount(this);
        if (localAccount1 != null)
          str2 = localAccount1.name;
        else
          str2 = null;
      }
    }
  }

  private boolean displayTos()
  {
    boolean bool = true;
    String str = determineAccount();
    if (TosActivity.requiresAcceptance(str, FinskyApp.get().getToc()))
    {
      this.mWaitingForUserInput = bool;
      Intent localIntent = TosActivity.getIntent(this, str, FinskyApp.get().getToc());
      localIntent.setFlags(67108864);
      startActivityForResult(localIntent, 20);
    }
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  private void fireOnReadyRunnable(final boolean paramBoolean)
  {
    FinskyApp.get().getInstaller().startDeferredInstalls();
    hideLoadingIndicator();
    Runnable local3 = new Runnable()
    {
      public void run()
      {
        if (AuthenticatedActivity.this.mStateSaved)
          FinskyLog.d("onSaveInstanceState() called, not firing onReady().", new Object[0]);
        while (true)
        {
          return;
          AuthenticatedActivity.this.onReady(paramBoolean);
        }
      }
    };
    this.mHandler.post(local3);
  }

  private static int getIndexOfAccount(String[] paramArrayOfString, String paramString)
  {
    int i = 0;
    if (i < paramArrayOfString.length)
      if (!paramArrayOfString[i].equals(paramString));
    while (true)
    {
      return i;
      i++;
      break;
      i = -1;
    }
  }

  private void getMarketMetadataAndLoadLibraries(final boolean paramBoolean)
  {
    final Runnable local9 = new Runnable()
    {
      private int mFinished;

      public void run()
      {
        this.mFinished = (1 + this.mFinished);
        if (this.mFinished == 3)
          AuthenticatedActivity.this.loadTocAndContinue(paramBoolean);
      }
    };
    new GetMarketMetadataAction().run(this, FinskyApp.get().getCurrentAccountName(), new Response.Listener()
    {
      public void onResponse(VendingProtos.GetMarketMetadataResponseProto paramAnonymousGetMarketMetadataResponseProto)
      {
        FinskyApp.get().setMarketMetadata(paramAnonymousGetMarketMetadataResponseProto);
        FinskyApp.get().getSelfUpdateScheduler().checkForSelfUpdate(paramAnonymousGetMarketMetadataResponseProto);
        local9.run();
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        FinskyLog.d("Metadata failed: %s", new Object[] { paramAnonymousVolleyError });
        AuthenticatedActivity.this.handleAuthenticationError(paramAnonymousVolleyError);
      }
    });
    FinskyApp.get().getAppStates().load(local9);
    FinskyApp.get().getLibraries().load(local9);
  }

  private boolean isAccountSwitchNeeded(String paramString)
  {
    if (!paramString.equals(FinskyApp.get().getCurrentAccountName()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private void loadTocAndContinue(final boolean paramBoolean)
  {
    final boolean[] arrayOfBoolean = new boolean[1];
    FinskyApp.get().getDfeApi().getToc(new Response.Listener()
    {
      public void onResponse(Toc.TocResponse paramAnonymousTocResponse)
      {
        if (arrayOfBoolean[0] == 1)
          new ApplicationDismissedDeferrer(AuthenticatedActivity.this.getApplicationContext()).runOnApplicationClose(new Runnable()
          {
            public void run()
            {
              FinskyLog.d("Exiting process due to toc change", new Object[0]);
              System.exit(0);
            }
          }
          , 10000);
        while (true)
        {
          return;
          arrayOfBoolean[0] = true;
          DfeToc localDfeToc = new DfeToc(paramAnonymousTocResponse);
          FinskyApp.get().setToc(localDfeToc);
          AuthenticatedActivity.this.checkTosAcceptanceAndContinue(paramBoolean);
        }
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        AuthenticatedActivity.this.hideLoadingIndicator();
        AuthenticatedActivity.this.handleAuthenticationError(paramAnonymousVolleyError);
      }
    }
    , true);
  }

  private void performFirstRunIfNecessary(boolean paramBoolean)
  {
    if (FirstRunActivity.requiresFirstRun())
    {
      startActivity(FirstRunActivity.getIntent(this, getIntent()));
      finish();
    }
    while (true)
    {
      return;
      getMarketMetadataAndLoadLibraries(paramBoolean);
    }
  }

  private void registerGcmIfNecessary()
  {
    if (!FinskyApp.get().getUsers().hasMultipleUsers());
    while (true)
    {
      return;
      GCMRegistrar.checkDevice(this);
      String str = GCMRegistrar.getRegistrationId(this);
      if (str.equals(""))
        GCMRegistrar.register(this, new String[] { "932144863878" });
      else if (!GCMRegistrar.isRegisteredOnServer(this))
        this.mUploadDeviceConfigRequest = DeviceConfigurationHelper.uploadDeviceConfig(FinskyApp.get().getDfeApi(), DeviceConfigurationHelper.getDeviceConfiguration(), str, new Runnable()
        {
          public void run()
          {
            AuthenticatedActivity.access$002(AuthenticatedActivity.this, null);
          }
        });
    }
  }

  private void setupAccountAndContinue(boolean paramBoolean)
  {
    String str = determineAccount();
    if (str == null)
      if (ADD_ACCOUNT_SUPPORTS_CUSTOM_MESSAGE)
        addAccount();
    while (true)
    {
      return;
      showDialog(2);
      continue;
      if (isAccountSwitchNeeded(str))
        switchAccount(str);
      else
        performFirstRunIfNecessary(paramBoolean);
    }
  }

  private Dialog setupAccountCreationDialog()
  {
    Resources localResources = getResources();
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(localResources.getString(2131165416)).setCancelable(false).setPositiveButton(localResources.getString(2131165402), this.mOnClickCreateAccountListener).setNegativeButton(localResources.getString(2131165403), this.mDeclineCreateAccountListener).setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 84);
        for (boolean bool = true; ; bool = false)
          return bool;
      }
    });
    return localBuilder.create();
  }

  private AlertDialog setupAccountDialog()
  {
    final Account[] arrayOfAccount = AccountHandler.getAccounts(getApplicationContext());
    String[] arrayOfString = convertToStringArray(arrayOfAccount);
    int i = getIndexOfAccount(arrayOfString, FinskyApp.get().getCurrentAccountName());
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(getString(2131165414));
    localBuilder.setSingleChoiceItems(arrayOfString, i, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        String str = arrayOfAccount[paramAnonymousInt].name;
        if (AuthenticatedActivity.this.isAccountSwitchNeeded(str))
        {
          FinskyApp.get().getAnalytics().logPageView(null, null, "switchAccount");
          FinskyApp.get().getEventLogger().logTag("switchAccount", new Object[0]);
          AuthenticatedActivity.this.setIntent(new Intent());
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = FinskyLog.scrubPii(str);
          FinskyLog.d("b/5160617: Switching account to %s on user action", arrayOfObject);
          AuthenticatedActivity.this.switchAccount(str);
        }
        while (true)
        {
          AuthenticatedActivity.this.removeDialog(0);
          return;
          AuthenticatedActivity.sSwitchToMyApps = false;
        }
      }
    });
    localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        AuthenticatedActivity.sSwitchToMyApps = false;
        AuthenticatedActivity.this.removeDialog(0);
      }
    });
    return localBuilder.create();
  }

  protected void authenticateOnNewIntent(boolean paramBoolean)
  {
    startInitializationActions(paramBoolean);
  }

  public void chooseAccount(boolean paramBoolean)
  {
    sSwitchToMyApps = paramBoolean;
    if (Build.VERSION.SDK_INT >= 14)
      startActivityForResult(AccountManager.newChooseAccountIntent(FinskyApp.get().getCurrentAccount(), null, new String[] { "com.google" }, true, null, "androidmarket", null, createAddAccountOptions(this)), 32);
    while (true)
    {
      return;
      showDialog(0);
    }
  }

  protected boolean getJustReturnedFromDialog()
  {
    return this.mJustReturnedFromDialog;
  }

  protected void handleAuthenticationError(VolleyError paramVolleyError)
  {
  }

  protected void handleUserAuthentication(Intent paramIntent)
  {
    this.mWaitingForUserInput = true;
    startActivityForResult(paramIntent, 22);
  }

  public boolean hasDiffVersionCode()
  {
    int i = FinskyApp.get().getVersionCode();
    if (((Integer)FinskyPreferences.versionCode.get()).intValue() != i)
      FinskyPreferences.versionCode.put(Integer.valueOf(i));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected void hideLoadingIndicator()
  {
    ViewGroup localViewGroup = (ViewGroup)getWindow().findViewById(2131230775);
    View localView = localViewGroup.findViewById(2131230808);
    if (localView != null)
      localViewGroup.removeView(localView);
  }

  public boolean isStateSaved()
  {
    return this.mStateSaved;
  }

  public boolean isTosAccepted()
  {
    boolean bool = false;
    String str = FinskyApp.get().getCurrentAccountName();
    if (str == null);
    while (true)
    {
      return bool;
      DfeToc localDfeToc = FinskyApp.get().getToc();
      if ((localDfeToc != null) && (!TosActivity.requiresAcceptance(str, localDfeToc)))
        bool = true;
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default:
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    case 32:
    case 20:
    case 22:
    case 21:
    }
    while (true)
    {
      return;
      if (paramInt2 == -1)
      {
        String str = paramIntent.getStringExtra("authAccount");
        if (str != null)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = FinskyLog.scrubPii(str);
          FinskyLog.d("b/5160617: Switch account to %s due to request code", arrayOfObject);
          switchAccount(str);
        }
      }
      else
      {
        sSwitchToMyApps = false;
        continue;
        this.mWaitingForUserInput = false;
        if (paramInt2 == 0)
        {
          finish();
        }
        else
        {
          setJustReturnedFromDialog(true);
          continue;
          if (paramInt2 == 0)
          {
            chooseAccount(false);
          }
          else
          {
            this.mWaitingForUserInput = false;
            setJustReturnedFromDialog(true);
            continue;
            this.mWaitingForUserInput = false;
            if (AccountHandler.getAccounts(this).length == 0)
            {
              FinskyLog.d("No new account added: Assume the user canceled and finish.", new Object[0]);
              finish();
            }
            else
            {
              setJustReturnedFromDialog(true);
            }
          }
        }
      }
    }
  }

  public void onBackgroundDataNotEnabled()
  {
    finish();
  }

  protected void onCleanup()
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle != null)
      this.mWaitingForUserInput = paramBundle.getBoolean("waiting_for_user_input");
    showLoadingIndicator();
    registerGcmIfNecessary();
    if (hasDiffVersionCode())
    {
      if (FinskyLog.DEBUG)
        FinskyLog.v("Diff version code, clear cache", new Object[0]);
      FinskyApp.get().clearCacheAsync(new Runnable()
      {
        public void run()
        {
          AuthenticatedActivity.this.startInitializationActions(true);
        }
      });
    }
    while (true)
    {
      return;
      if (FinskyLog.DEBUG)
        FinskyLog.v("Same version code as before", new Object[0]);
      if (!this.mWaitingForUserInput)
        startInitializationActions(true);
      else
        FinskyLog.d("Waiting for user to return from auth screen.", new Object[0]);
    }
  }

  @Deprecated
  protected Dialog onCreateDialog(int paramInt, Bundle paramBundle)
  {
    switch (paramInt)
    {
    case 1:
    default:
      throw new IllegalStateException("Invalid dialog type id " + paramInt);
    case 2:
    case 0:
    }
    for (Object localObject = setupAccountCreationDialog(); ; localObject = setupAccountDialog())
      return localObject;
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.mUploadDeviceConfigRequest != null)
      this.mUploadDeviceConfigRequest.cancel();
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    boolean bool = true;
    if (("android.intent.action.MAIN".equals(paramIntent.getAction())) && (paramIntent.hasCategory("android.intent.category.LAUNCHER")))
      bool = false;
    authenticateOnNewIntent(bool);
  }

  protected abstract void onReady(boolean paramBoolean);

  protected void onResume()
  {
    super.onResume();
    this.mStateSaved = false;
    if (this.mRestartRequired)
      restart();
    while (true)
    {
      return;
      if (getJustReturnedFromDialog())
      {
        setJustReturnedFromDialog(false);
        startInitializationActions(true);
      }
      if (!Utils.isBackgroundDataEnabled(this))
        showBackgroundDataDialog();
      else
        BackgroundDataDialog.dismissExisting(getSupportFragmentManager());
    }
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    this.mStateSaved = true;
    paramBundle.putBoolean("waiting_for_user_input", this.mWaitingForUserInput);
    super.onSaveInstanceState(paramBundle);
  }

  public boolean onSearchRequested()
  {
    if ((isTosAccepted()) && (super.onSearchRequested()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected void onStart()
  {
    super.onStart();
    this.mStateSaved = false;
  }

  protected void onStop()
  {
    super.onStop();
    this.mStateSaved = true;
  }

  protected void reinitialize(Account paramAccount, boolean paramBoolean)
  {
    if (paramAccount == null)
    {
      paramAccount = FinskyApp.get().getCurrentAccount();
      if (paramAccount == null)
        restart();
    }
    while (true)
    {
      return;
      FinskyApp localFinskyApp = FinskyApp.get();
      onCleanup();
      if (paramBoolean)
        localFinskyApp.clearCacheAsync(null);
      localFinskyApp.setToc(null);
      localFinskyApp.switchCurrentAccount(paramAccount);
      startInitializationActions(true);
    }
  }

  protected void restart()
  {
    this.mRestartRequired = false;
    if (Build.VERSION.SDK_INT >= 11)
      recreate();
    while (true)
    {
      return;
      finish();
      final Intent localIntent = getIntent();
      this.mHandler.postDelayed(new Runnable()
      {
        public void run()
        {
          try
          {
            AuthenticatedActivity.this.startActivity(localIntent);
            return;
          }
          catch (ActivityNotFoundException localActivityNotFoundException)
          {
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = localIntent.toString();
            FinskyLog.wtf(localActivityNotFoundException, "Intent: %s", arrayOfObject);
            throw localActivityNotFoundException;
          }
        }
      }
      , 250L);
    }
  }

  public void restartOnResume()
  {
    this.mRestartRequired = true;
  }

  protected void setJustReturnedFromDialog(boolean paramBoolean)
  {
    this.mJustReturnedFromDialog = paramBoolean;
  }

  protected void showBackgroundDataDialog()
  {
    BackgroundDataDialog.show(getSupportFragmentManager(), this);
  }

  protected void showLoadingIndicator()
  {
    ViewGroup localViewGroup = (ViewGroup)getWindow().findViewById(2131230775);
    if (localViewGroup.findViewById(2131230808) != null);
    while (true)
    {
      return;
      LayoutInflater.from(localViewGroup.getContext()).inflate(2130968777, localViewGroup, true);
    }
  }

  protected void startInitializationActions(boolean paramBoolean)
  {
    showLoadingIndicator();
    setupAccountAndContinue(paramBoolean);
  }

  protected void switchAccount(String paramString)
  {
    Account localAccount = AccountHandler.findAccount(paramString, this);
    if (localAccount == null)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = FinskyLog.scrubPii(paramString);
      throw new IllegalArgumentException(String.format("Error, could not switch to %s because the account could not be found on the device", arrayOfObject));
    }
    reinitialize(localAccount, false);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.AuthenticatedActivity
 * JD-Core Version:    0.6.2
 */