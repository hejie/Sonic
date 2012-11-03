package com.google.android.finsky;

import android.accounts.Account;
import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.provider.SearchRecentSuggestions;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.android.vending.AssetBrowserActivity;
import com.android.vending.VendingBackupAgent;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.ByteArrayPool;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.DfeAnalytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.StubAnalytics;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiContext;
import com.google.android.finsky.api.DfeApiImpl;
import com.google.android.finsky.api.DfeApiProvider;
import com.google.android.finsky.api.DfeNotificationManager;
import com.google.android.finsky.api.DfeRequest;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStatesReplicator;
import com.google.android.finsky.appstate.InMemoryInstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.MigrationAsyncTask;
import com.google.android.finsky.appstate.PackageManagerRepository;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.appstate.SQLiteInstallerDataStore;
import com.google.android.finsky.appstate.WriteThroughInstallerDataStore;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.creditcard.EscrowRequest;
import com.google.android.finsky.billing.iab.PendingNotificationsService;
import com.google.android.finsky.config.ContentLevel;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile;
import com.google.android.finsky.download.DownloadBroadcastReceiver;
import com.google.android.finsky.download.DownloadManagerImpl;
import com.google.android.finsky.download.DownloadQueueImpl;
import com.google.android.finsky.download.obb.ObbFactory;
import com.google.android.finsky.download.obb.ObbPackageTracker;
import com.google.android.finsky.experiments.DfeExperiments;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Accounts;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.library.LibraryReplicatorsImpl;
import com.google.android.finsky.library.SQLiteLibrary;
import com.google.android.finsky.receivers.AccountsChangedReceiver;
import com.google.android.finsky.receivers.BootCompletedReceiver;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.InstallerImpl;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.RegisteredReceiver;
import com.google.android.finsky.receivers.RemoveAssetReceiver;
import com.google.android.finsky.services.ContentSyncService;
import com.google.android.finsky.services.DailyHygiene;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.DenyAllNetwork;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.NotificationManager;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.SelfUpdateScheduler;
import com.google.android.finsky.utils.UninstallRefundTracker;
import com.google.android.finsky.utils.UninstallSubscriptionsTracker;
import com.google.android.finsky.utils.Users;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.widget.WidgetUtils;
import com.google.android.play.analytics.EventLogger;
import com.google.android.play.analytics.EventLogger.LogSource;
import com.google.android.vending.remoting.api.PendingNotificationsHandler;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.api.VendingApiFactory;
import com.google.android.vending.remoting.api.VendingRequest;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.PendingNotificationsProto;
import com.google.android.volley.GoogleHttpClientStack;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class FinskyApp extends Application
  implements DfeApiProvider
{
  private static final Class<?>[] RECEIVERS = { BootCompletedReceiver.class, PackageMonitorReceiver.RegisteredReceiver.class, AccountsChangedReceiver.class };
  private static FinskyApp sInstance;
  private Analytics mAnalytics;
  private VendingApiFactory mApiFactory;
  private AppStates mAppStates;
  private AppStatesReplicator mAppStatesReplicator;
  private Cache mBitmapCache;
  private BitmapLoader mBitmapLoader;
  private RequestQueue mBitmapRequestQueue;
  private Cache mCache;
  private Account mCurrentAccount;
  private final Map<String, DfeApi> mDfeApis = Maps.newHashMap();
  private DfeNotificationManager mDfeNotificationManager;
  private DownloadQueueImpl mDownloadQueue;
  private final Map<String, FinskyEventLog> mEventLoggers = Maps.newHashMap();
  private InstallPolicies mInstallPolicies;
  private Installer mInstaller;
  private InstallerDataStore mInstallerDataStore;
  private Libraries mLibraries;
  private LibraryReplicators mLibraryReplicators;
  private VendingProtos.GetMarketMetadataResponseProto mMarketMetadata;
  private FinskyEventLog mNoAccounteventLog;
  private Notifier mNotificationHelper;
  private PackageMonitorReceiver mPackageMonitorReceiver;
  private PackageStateRepository mPackageStateRepository;
  private final PendingNotificationsHandler mPendingNotificationsHandler = new PendingNotificationsHandler()
  {
    public boolean handlePendingNotifications(Context paramAnonymousContext, String paramAnonymousString, VendingProtos.PendingNotificationsProto paramAnonymousPendingNotificationsProto, boolean paramAnonymousBoolean)
    {
      return PendingNotificationsService.handlePendingNotifications(paramAnonymousContext, paramAnonymousString, paramAnonymousPendingNotificationsProto, paramAnonymousBoolean);
    }
  };
  private SearchRecentSuggestions mRecentSuggestions;
  private RequestQueue mRequestQueue;
  private SelfUpdateScheduler mSelfUpdateScheduler;
  private DfeToc mToc;
  private Users mUsers;

  private boolean checkForCrashOnLastRun(Context paramContext)
  {
    File localFile = new File(paramContext.getCacheDir(), "crashed");
    Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(localFile));
    return localFile.delete();
  }

  private void cleanupOldFinsky()
  {
    PackageStateRepository.PackageState localPackageState = this.mPackageStateRepository.get("com.google.android.finsky");
    if (localPackageState == null);
    while (true)
    {
      return;
      try
      {
        PackageManager localPackageManager = getPackageManager();
        if (localPackageManager.getApplicationEnabledSetting("com.google.android.finsky") != 2)
          localPackageManager.setApplicationEnabledSetting("com.google.android.finsky", 2, 1);
        int i = ((Integer)G.tabskyMinVersion.get()).intValue();
        if (localPackageState.installedVersion >= i)
          continue;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(localPackageState.installedVersion);
        arrayOfObject[1] = Integer.valueOf(i);
        FinskyLog.d("Updating com.google.android.finsky from %d to %d", arrayOfObject);
        downloadSuicidalTabsky("com.google.android.finsky", i);
      }
      catch (SecurityException localSecurityException)
      {
        while (true)
          FinskyLog.w("Unable to disable old finsky package.", new Object[0]);
      }
    }
  }

  public static Network createNetwork()
  {
    if (Utils.isBackgroundDataEnabled(sInstance));
    for (Object localObject = new BasicNetwork(new GoogleHttpClientStack(sInstance, ((Boolean)G.enableSensitiveLogging.get()).booleanValue()), new ByteArrayPool(1024 * ((Integer)G.volleyBufferPoolSizeKb.get()).intValue())); ; localObject = new DenyAllNetwork())
      return localObject;
  }

  private void downloadSuicidalTabsky(String paramString, int paramInt)
  {
    Account localAccount = AccountHandler.getFirstAccount(this);
    if (localAccount == null)
      FinskyLog.w("No current account", new Object[0]);
    while (true)
    {
      return;
      this.mInstaller.setMobileDataAllowed(paramString);
      this.mInstaller.setVisibility(paramString, false, false);
      this.mInstaller.requestInstall(paramString, paramInt, null, localAccount.name, null, null, getString(2131165393), false, "suicidal_tabsky");
    }
  }

  public static void drain(RequestQueue paramRequestQueue)
  {
    drain(paramRequestQueue, paramRequestQueue.getSequenceNumber());
  }

  public static void drain(RequestQueue paramRequestQueue, int paramInt)
  {
    paramRequestQueue.cancelAll(new RequestQueue.RequestFilter()
    {
      public boolean apply(Request<?> paramAnonymousRequest)
      {
        boolean bool = false;
        if ((paramAnonymousRequest instanceof DfeRequest))
          if (!((DfeRequest)paramAnonymousRequest).getAvoidBulkCancel())
            break label38;
        while (true)
        {
          return bool;
          if ((paramAnonymousRequest instanceof VendingRequest))
          {
            if (!((VendingRequest)paramAnonymousRequest).getAvoidBulkCancel())
              label38: if (paramAnonymousRequest.getSequence() < this.val$seq)
                bool = true;
          }
          else
            if (!(paramAnonymousRequest instanceof EscrowRequest))
              break;
        }
      }
    });
  }

  private void enableReceivers()
  {
    PackageManager localPackageManager = getPackageManager();
    Class[] arrayOfClass = RECEIVERS;
    int i = arrayOfClass.length;
    int j = 0;
    while (true)
      if (j < i)
      {
        Class localClass = arrayOfClass[j];
        try
        {
          ComponentName localComponentName = new ComponentName(this, localClass);
          if (localPackageManager.getComponentEnabledSetting(localComponentName) != 1)
            localPackageManager.setComponentEnabledSetting(localComponentName, 1, 1);
          j++;
        }
        catch (SecurityException localSecurityException)
        {
          while (true)
          {
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = localClass.getSimpleName();
            FinskyLog.wtf("Unable to enable %s", arrayOfObject);
          }
        }
      }
  }

  public static FinskyApp get()
  {
    return sInstance;
  }

  public static File getCacheDir(String paramString)
  {
    File localFile = new File(sInstance.getCacheDir(), paramString);
    localFile.mkdirs();
    return localFile;
  }

  private void setupAccountLibrariesAndReplicator(Handler paramHandler1, Handler paramHandler2, Accounts paramAccounts)
  {
    SQLiteLibrary localSQLiteLibrary = new SQLiteLibrary(this);
    this.mLibraries = new Libraries(paramAccounts, localSQLiteLibrary, new Handler(Looper.getMainLooper()), paramHandler2);
    this.mLibraryReplicators = new LibraryReplicatorsImpl(this, localSQLiteLibrary, this.mLibraries, paramHandler1, paramHandler2, ((Boolean)G.debugOptionsEnabled.get()).booleanValue());
    this.mLibraries.addListener(new Libraries.Listener()
    {
      public void onAllLibrariesLoaded()
      {
        FinskyApp.this.mLibraryReplicators.reinitialize();
      }

      public void onLibraryContentsChanged(AccountLibrary paramAnonymousAccountLibrary)
      {
      }
    });
    this.mLibraries.load(null);
  }

  private void whoopsWeBrokeEverything()
  {
    getPackageManager().setComponentEnabledSetting(new ComponentName(this, AssetBrowserActivity.class), 1, 1);
  }

  public void clearCacheAsync(final Runnable paramRunnable)
  {
    this.mRequestQueue.add(new ClearCacheRequest(this.mCache, new Runnable()
    {
      public void run()
      {
        FinskyApp.this.mBitmapRequestQueue.add(new ClearCacheRequest(FinskyApp.this.mBitmapCache, paramRunnable));
      }
    }));
  }

  public void drainAllRequests(int paramInt)
  {
    if (!((Boolean)G.explorerEnabled.get()).booleanValue())
    {
      drain(this.mRequestQueue, paramInt);
      get().getBitmapLoader().drain(paramInt);
    }
  }

  public Analytics getAnalytics()
  {
    return this.mAnalytics;
  }

  public Analytics getAnalytics(String paramString)
  {
    return new DfeAnalytics(new Handler(Looper.getMainLooper()), getDfeApi(paramString));
  }

  public AppStates getAppStates()
  {
    return this.mAppStates;
  }

  public AppStatesReplicator getAppStatesReplicator()
  {
    return this.mAppStatesReplicator;
  }

  public Cache getBitmapCache()
  {
    return this.mBitmapCache;
  }

  public BitmapLoader getBitmapLoader()
  {
    return this.mBitmapLoader;
  }

  public Cache getCache()
  {
    return this.mCache;
  }

  public Account getCurrentAccount()
  {
    Account localAccount2;
    if (this.mCurrentAccount == null)
    {
      localAccount2 = AccountHandler.getAccountFromPreferences(this, FinskyPreferences.currentAccount);
      if (localAccount2 == null)
        FinskyLog.w("No account configured on this device.", new Object[0]);
    }
    for (Account localAccount1 = null; ; localAccount1 = this.mCurrentAccount)
    {
      return localAccount1;
      this.mCurrentAccount = localAccount2;
    }
  }

  public String getCurrentAccountName()
  {
    Account localAccount = getCurrentAccount();
    if (localAccount != null);
    for (String str = localAccount.name; ; str = null)
      return str;
  }

  public DfeApi getDfeApi()
  {
    Account localAccount = getCurrentAccount();
    if (localAccount == null)
      FinskyLog.w("No account configured on this device.", new Object[0]);
    for (DfeApi localDfeApi = null; ; localDfeApi = getDfeApi(localAccount.name))
      return localDfeApi;
  }

  public DfeApi getDfeApi(String paramString)
  {
    synchronized (this.mDfeApis)
    {
      Object localObject2 = (DfeApi)this.mDfeApis.get(paramString);
      if (localObject2 == null)
      {
        int i = ContentLevel.importFromSettings(this).getDfeValue();
        DfeApiContext localDfeApiContext = DfeApiContext.create(this, getCache(), new DfeExperiments(), this.mDfeNotificationManager, paramString, i);
        if (FinskyLog.DEBUG)
          FinskyLog.d("Created new context: %s", new Object[] { localDfeApiContext });
        localObject2 = new DfeApiImpl(this.mRequestQueue, localDfeApiContext);
        this.mDfeApis.put(paramString, localObject2);
      }
      return localObject2;
    }
  }

  public DfeNotificationManager getDfeNotificationManager()
  {
    return this.mDfeNotificationManager;
  }

  public FinskyEventLog getEventLogger()
  {
    return getEventLogger(getCurrentAccount());
  }

  public FinskyEventLog getEventLogger(Account paramAccount)
  {
    Map localMap = this.mEventLoggers;
    if (paramAccount == null);
    FinskyEventLog localFinskyEventLog;
    try
    {
      if (this.mNoAccounteventLog == null)
        this.mNoAccounteventLog = new FinskyEventLog(null);
      localFinskyEventLog = this.mNoAccounteventLog;
      break label115;
      String str = paramAccount.name;
      localFinskyEventLog = (FinskyEventLog)this.mEventLoggers.get(str);
      if (localFinskyEventLog == null)
      {
        localFinskyEventLog = new FinskyEventLog(new EventLogger(this, null, paramAccount, EventLogger.LogSource.MARKET, null));
        this.mEventLoggers.put(str, localFinskyEventLog);
      }
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
    label115: return localFinskyEventLog;
  }

  public InstallPolicies getInstallPolicies()
  {
    return this.mInstallPolicies;
  }

  public Installer getInstaller()
  {
    return this.mInstaller;
  }

  public InstallerDataStore getInstallerDataStore()
  {
    return this.mInstallerDataStore;
  }

  public Libraries getLibraries()
  {
    return this.mLibraries;
  }

  public LibraryReplicators getLibraryReplicators()
  {
    return this.mLibraryReplicators;
  }

  public VendingProtos.GetMarketMetadataResponseProto getMarketMetadata()
  {
    return this.mMarketMetadata;
  }

  public Notifier getNotifier()
  {
    return this.mNotificationHelper;
  }

  public PackageStateRepository getPackageInfoRepository()
  {
    return this.mPackageStateRepository;
  }

  public PackageMonitorReceiver getPackageMonitorReceiver()
  {
    return this.mPackageMonitorReceiver;
  }

  public PendingNotificationsHandler getPendingNotificationsHandler()
  {
    return this.mPendingNotificationsHandler;
  }

  public SearchRecentSuggestions getRecentSuggestions()
  {
    return this.mRecentSuggestions;
  }

  public RequestQueue getRequestQueue()
  {
    return this.mRequestQueue;
  }

  public SelfUpdateScheduler getSelfUpdateScheduler()
  {
    return this.mSelfUpdateScheduler;
  }

  public DfeToc getToc()
  {
    return this.mToc;
  }

  public Users getUsers()
  {
    return this.mUsers;
  }

  public VendingApi getVendingApi()
  {
    Account localAccount = this.mCurrentAccount;
    if (localAccount == null)
    {
      FinskyLog.w("Fall back to primary account.", new Object[0]);
      localAccount = AccountHandler.getFirstAccount(this);
    }
    if (localAccount == null)
      FinskyLog.w("No account configured on this device.", new Object[0]);
    for (VendingApi localVendingApi = null; ; localVendingApi = getVendingApi(localAccount.name))
      return localVendingApi;
  }

  public VendingApi getVendingApi(String paramString)
  {
    return this.mApiFactory.getApi(paramString);
  }

  public int getVersionCode()
  {
    return this.mPackageStateRepository.get(getPackageName()).installedVersion;
  }

  public boolean isTablet()
  {
    try
    {
      Method localMethod = TelephonyManager.class.getMethod("isVoiceCapable", new Class[0]);
      TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
      if (localTelephonyManager != null)
      {
        boolean bool2 = ((Boolean)localMethod.invoke(localTelephonyManager, new Object[0])).booleanValue();
        if (bool2)
          break label58;
      }
      label58: for (bool1 = true; ; bool1 = false)
        return bool1;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public void onCreate()
  {
    sInstance = this;
    GservicesValue.init(this, G.GSERVICES_KEY_PREFIXES);
    PreferenceFile.init(this);
    this.mCache = new DiskBasedCache(getCacheDir("main"), 1024 * (1024 * ((Integer)G.mainCacheSizeMb.get()).intValue()));
    if (checkForCrashOnLastRun(this))
    {
      FinskyLog.d("Clearing cache due to crash on previous run.", new Object[0]);
      this.mCache.clear();
    }
    whoopsWeBrokeEverything();
    this.mRequestQueue = new RequestQueue(this.mCache, createNetwork(), 2);
    this.mRequestQueue.start();
    this.mApiFactory = new VendingApiFactory(getApplicationContext(), this.mRequestQueue);
    this.mPackageMonitorReceiver = new PackageMonitorReceiver();
    this.mBitmapCache = new DiskBasedCache(getCacheDir("images"), 1024 * (1024 * ((Integer)G.imageCacheSizeMb.get()).intValue()));
    this.mBitmapRequestQueue = new RequestQueue(this.mBitmapCache, createNetwork());
    this.mBitmapRequestQueue.start();
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    this.mBitmapLoader = new BitmapLoader(this.mBitmapRequestQueue, localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
    BillingLocator.initSingleton();
    VendingBackupAgent.registerWithBackup(getApplicationContext());
    ObbFactory.initialize();
    this.mNotificationHelper = new NotificationManager(this);
    DownloadManagerImpl localDownloadManagerImpl = new DownloadManagerImpl(this);
    this.mDownloadQueue = new DownloadQueueImpl(this, localDownloadManagerImpl);
    RemoveAssetReceiver.initialize(this.mNotificationHelper);
    DownloadBroadcastReceiver.initialize(this.mDownloadQueue);
    DailyHygiene.goMakeHygieneIfDirty(this);
    this.mPackageStateRepository = new PackageManagerRepository(getPackageManager(), getPackageMonitorReceiver(), (DevicePolicyManager)getSystemService("device_policy"));
    PackageStateRepository.PackageState localPackageState = this.mPackageStateRepository.get(getPackageName());
    this.mSelfUpdateScheduler = new SelfUpdateScheduler(this.mDownloadQueue, localPackageState.installedVersion);
    this.mPackageMonitorReceiver.attach(new ObbPackageTracker());
    this.mRecentSuggestions = new SearchRecentSuggestions(this, "com.google.android.finsky.RecentSuggestionsProvider", 1);
    this.mUsers = new Users(this);
    enableReceivers();
    HandlerThread localHandlerThread = new HandlerThread("libraries-thread", 10);
    localHandlerThread.start();
    Handler localHandler1 = new Handler(Looper.getMainLooper());
    Handler localHandler2 = new Handler(localHandlerThread.getLooper());
    Accounts local2 = new Accounts()
    {
      public Account getAccount(String paramAnonymousString)
      {
        return AccountHandler.findAccount(paramAnonymousString, FinskyApp.this);
      }

      public List<Account> getAccounts()
      {
        return Lists.newArrayList(AccountHandler.getAccounts(FinskyApp.this));
      }
    };
    setupAccountLibrariesAndReplicator(localHandler1, localHandler2, local2);
    WidgetUtils.registerLibraryMutationsListener(sInstance, getLibraryReplicators());
    Handler localHandler3 = new Handler(localHandlerThread.getLooper());
    WriteThroughInstallerDataStore localWriteThroughInstallerDataStore = new WriteThroughInstallerDataStore(new InMemoryInstallerDataStore(), new SQLiteInstallerDataStore(this), localHandler3, localHandler1);
    this.mInstallerDataStore = localWriteThroughInstallerDataStore;
    this.mAppStates = new AppStates(localWriteThroughInstallerDataStore, this.mPackageStateRepository);
    this.mAppStatesReplicator = new AppStatesReplicator(local2, this.mLibraries, this.mAppStates, this.mApiFactory, localHandler1, localHandler3);
    ContentSyncService.setupListeners(this.mLibraryReplicators, this.mPackageMonitorReceiver);
    this.mInstallPolicies = new InstallPolicies(getContentResolver(), getPackageManager(), this.mAppStates, this.mLibraries);
    this.mInstaller = new InstallerImpl(this, this.mAppStates, this.mLibraries, this.mDownloadQueue, this.mNotificationHelper, this.mInstallPolicies, this.mPackageMonitorReceiver, this.mUsers);
    this.mInstaller.start();
    this.mDfeNotificationManager = new DfeNotificationManagerImpl(this, this.mInstaller, this.mNotificationHelper, this.mAppStates, this.mLibraryReplicators, local2);
    new UninstallRefundTracker(this, this.mAppStates, this.mPackageMonitorReceiver);
    new UninstallSubscriptionsTracker(this, this.mLibraries, this.mPackageMonitorReceiver);
    new MigrationAsyncTask(this).execute(new Void[0]);
    cleanupOldFinsky();
    if (((Boolean)G.analyticsEnabled.get()).booleanValue());
    for (this.mAnalytics = new DfeAnalytics(new Handler(getMainLooper()), getDfeApi()); ; this.mAnalytics = new StubAnalytics())
      return;
  }

  public void setMarketMetadata(VendingProtos.GetMarketMetadataResponseProto paramGetMarketMetadataResponseProto)
  {
    this.mMarketMetadata = paramGetMarketMetadataResponseProto;
  }

  public void setToc(DfeToc paramDfeToc)
  {
    this.mToc = paramDfeToc;
  }

  public void switchCurrentAccount(Account paramAccount)
  {
    while (true)
    {
      synchronized (this.mDfeApis)
      {
        this.mDfeApis.clear();
        if ((this.mCurrentAccount == null) || (!this.mCurrentAccount.equals(paramAccount)))
        {
          i = 1;
          this.mCurrentAccount = paramAccount;
          if (i != 0)
          {
            AccountHandler.saveAccountToPreferences(this.mCurrentAccount, FinskyPreferences.currentAccount);
            this.mAnalytics.reset();
            sendBroadcast(new Intent("com.google.android.finsky.action.DFE_API_CONTEXT_CHANGED"));
          }
          return;
        }
      }
      int i = 0;
    }
  }

  private class CrashHandler
    implements Thread.UncaughtExceptionHandler
  {
    private final File mFile;
    private final Thread.UncaughtExceptionHandler mOriginalHandler = Thread.getDefaultUncaughtExceptionHandler();

    public CrashHandler(File arg2)
    {
      Object localObject;
      this.mFile = localObject;
    }

    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      try
      {
        this.mFile.createNewFile();
        label8: this.mOriginalHandler.uncaughtException(paramThread, paramThrowable);
        return;
      }
      catch (Throwable localThrowable)
      {
        break label8;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.FinskyApp
 * JD-Core Version:    0.6.2
 */