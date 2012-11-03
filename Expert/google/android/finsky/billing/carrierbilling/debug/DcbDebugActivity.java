package com.google.android.finsky.billing.carrierbilling.debug;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.GetMarketMetadataAction;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.action.CarrierCredentialsAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierParamsAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;
import java.util.Collection;
import java.util.Collections;

public class DcbDebugActivity extends FragmentActivity
{
  private static final Collection<DcbDetail> GSERVICES_DETAILS = Collections.unmodifiableCollection(Lists.newArrayList(arrayOfDcbDetail));
  private RatingBar mCredStatus;
  private RatingBar mDcbParamStatus;
  private CarrierBillingStorage mDcbStorage;
  private RatingBar mProvStatus;
  private final Runnable updateStatusRunnable = new Runnable()
  {
    public void run()
    {
      Utils.ensureOnMainThread();
      DcbDebugActivity.this.updateStatus();
    }
  };

  static
  {
    DcbDetail[] arrayOfDcbDetail = new DcbDetail[6];
    arrayOfDcbDetail[0] = new GServicesDetail(G.vendingDcbConnectionType);
    arrayOfDcbDetail[1] = new GServicesDetail(G.vendingDcbProxyHost);
    arrayOfDcbDetail[2] = new GServicesDetail(G.vendingDcbProxyPort);
    arrayOfDcbDetail[3] = new GServicesDetail(G.vendingCarrierCredentialsBufferMs);
    arrayOfDcbDetail[4] = new GServicesDetail(G.vendingCarrierProvisioningRefreshFrequencyMs);
    arrayOfDcbDetail[5] = new GServicesDetail(G.vendingCarrierProvisioningRetryMs);
  }

  private void displayCredentials()
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    new DcbDebugDetailsFragment(new ReflectionDcbDetailExtractor(this.mDcbStorage.getCredentials(), "cred"), "Credentials").show(localFragmentTransaction, "showCredentials");
  }

  private void displayDcbParams()
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    new DcbDebugDetailsFragment(new ReflectionDcbDetailExtractor(this.mDcbStorage.getParams(), "dcb"), "DCB Params").show(localFragmentTransaction, "showDcbParams");
  }

  private void displayProvisioning()
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    new DcbDebugDetailsFragment(new ReflectionDcbDetailExtractor(this.mDcbStorage.getProvisioning(), "prov"), "Provisioning").show(localFragmentTransaction, "showProvisioning");
  }

  private void handleMenuClearItem(int paramInt)
  {
    switch (paramInt)
    {
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.wtf("Got unexpected whichField %s", arrayOfObject);
    case 16:
    case 32:
    case 48:
    }
    while (true)
    {
      updateStatus();
      return;
      this.mDcbStorage.clearParams();
      continue;
      this.mDcbStorage.clearProvisioning();
      continue;
      this.mDcbStorage.clearCredentials();
    }
  }

  private void handleMenuRefreshItem(int paramInt)
  {
    switch (paramInt)
    {
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.wtf("Got unexpected whichField %s", arrayOfObject);
    case 16:
    case 32:
    case 48:
    }
    while (true)
    {
      return;
      refreshDcbParams(this.updateStatusRunnable, this.updateStatusRunnable);
      continue;
      refreshProvisioning(this.updateStatusRunnable, this.updateStatusRunnable);
      continue;
      refreshCredentials(this.updateStatusRunnable, this.updateStatusRunnable);
    }
  }

  private void handleMenuViewDetails(int paramInt)
  {
    switch (paramInt)
    {
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.wtf("Got unexpected whichField %s", arrayOfObject);
    case 16:
    case 32:
    case 48:
    }
    while (true)
    {
      return;
      displayDcbParams();
      continue;
      displayProvisioning();
      continue;
      displayCredentials();
    }
  }

  private void refreshAllInfo()
  {
    BillingLocator.initCarrierBillingStorage(new Runnable()
    {
      public void run()
      {
        DcbDebugActivity.this.updateStatus();
        DcbDebugActivity.this.refreshDcbParams(this.val$postRefreshDcbParamsRunnable, DcbDebugActivity.this.updateStatusRunnable);
      }
    });
  }

  private void refreshCredentials(Runnable paramRunnable1, Runnable paramRunnable2)
  {
    ErrorRunnable localErrorRunnable = new ErrorRunnable("Error refreshing credentials", paramRunnable2);
    new CarrierCredentialsAction().run(this.mDcbStorage.getProvisioning().getProvisioningId(), null, paramRunnable1, localErrorRunnable);
  }

  private void refreshDcbParams(final Runnable paramRunnable1, final Runnable paramRunnable2)
  {
    new GetMarketMetadataAction().run(this, FinskyApp.get().getCurrentAccountName(), new Response.Listener()
    {
      public void onResponse(VendingProtos.GetMarketMetadataResponseProto paramAnonymousGetMarketMetadataResponseProto)
      {
        new CarrierParamsAction(paramAnonymousGetMarketMetadataResponseProto).run(paramRunnable1);
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        String str = String.format("Error getting dcb params: %s", new Object[] { paramAnonymousVolleyError });
        Toast.makeText(DcbDebugActivity.this, str, 1).show();
        paramRunnable2.run();
      }
    });
  }

  private void refreshProvisioning(Runnable paramRunnable1, Runnable paramRunnable2)
  {
    ErrorRunnable localErrorRunnable = new ErrorRunnable("Error refreshing provisioning", paramRunnable2);
    new CarrierProvisioningAction().forceRun(paramRunnable1, localErrorRunnable);
  }

  private void updateStatus()
  {
    float f1 = 1.0F;
    boolean bool1 = CarrierBillingUtils.isProvisioned(this.mDcbStorage);
    boolean bool2 = CarrierBillingUtils.areCredentialsValid(this.mDcbStorage);
    int i;
    float f2;
    label44: float f3;
    label64: RatingBar localRatingBar3;
    if (this.mDcbStorage.getParams() != null)
    {
      i = 1;
      RatingBar localRatingBar1 = this.mProvStatus;
      if (!bool1)
        break label95;
      f2 = f1;
      localRatingBar1.setRating(f2);
      RatingBar localRatingBar2 = this.mCredStatus;
      if (!bool2)
        break label101;
      f3 = f1;
      localRatingBar2.setRating(f3);
      localRatingBar3 = this.mDcbParamStatus;
      if (i == 0)
        break label107;
    }
    while (true)
    {
      localRatingBar3.setRating(f1);
      return;
      i = 0;
      break;
      label95: f2 = 0.0F;
      break label44;
      label101: f3 = 0.0F;
      break label64;
      label107: f1 = 0.0F;
    }
  }

  private static int whichField(View paramView)
  {
    int i;
    switch (paramView.getId())
    {
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramView.getId());
      FinskyLog.wtf("Unknown view id %d", arrayOfObject);
      i = -1;
    case 2131230888:
    case 2131230889:
    case 2131230890:
    case 2131230891:
    case 2131230886:
    case 2131230887:
    }
    while (true)
    {
      return i;
      i = 32;
      continue;
      i = 48;
      continue;
      i = 16;
    }
  }

  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = true;
    int i = 0xF & paramMenuItem.getItemId();
    int j = 0xF0 & paramMenuItem.getItemId();
    switch (i)
    {
    default:
      bool = super.onContextItemSelected(paramMenuItem);
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return bool;
      handleMenuViewDetails(j);
      continue;
      handleMenuRefreshItem(j);
      continue;
      handleMenuClearItem(j);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    setContentView(2130968628);
    super.onCreate(paramBundle);
    this.mProvStatus = ((RatingBar)findViewById(2131230888));
    this.mProvStatus.setEnabled(false);
    this.mCredStatus = ((RatingBar)findViewById(2131230890));
    this.mCredStatus.setEnabled(false);
    this.mDcbParamStatus = ((RatingBar)findViewById(2131230886));
    this.mDcbParamStatus.setEnabled(false);
    this.mDcbStorage = BillingLocator.getCarrierBillingStorage();
    registerForContextMenu(findViewById(2131230888));
    registerForContextMenu(findViewById(2131230889));
    registerForContextMenu(findViewById(2131230890));
    registerForContextMenu(findViewById(2131230891));
    registerForContextMenu(findViewById(2131230886));
    registerForContextMenu(findViewById(2131230887));
    findViewById(2131230889).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DcbDebugActivity.this.displayProvisioning();
      }
    });
    findViewById(2131230891).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DcbDebugActivity.this.displayCredentials();
      }
    });
    findViewById(2131230887).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DcbDebugActivity.this.displayDcbParams();
      }
    });
    findViewById(2131230892).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FragmentTransaction localFragmentTransaction = DcbDebugActivity.this.getSupportFragmentManager().beginTransaction();
        new DcbDebugDetailsFragment(new DcbDetailExtractor()
        {
          public Collection<DcbDetail> getDetails()
          {
            return DcbDebugActivity.GSERVICES_DETAILS;
          }
        }
        , "DCB GServices Values").show(localFragmentTransaction, "dcbGservices");
      }
    });
  }

  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    int i = whichField(paramView);
    boolean bool1 = false;
    boolean bool2 = CarrierBillingUtils.isProvisioned(this.mDcbStorage);
    if (this.mDcbStorage.getParams() != null);
    for (int j = 1; ; j = 0)
    {
      if ((i == 32) && (j != 0))
        bool1 = true;
      if ((i == 48) && (j != 0) && (bool2))
        bool1 = true;
      if (i == 16)
        bool1 = true;
      paramContextMenu.add(0, i | 0x1, 0, 2131165325);
      paramContextMenu.add(0, i | 0x2, 1, 2131165327).setEnabled(bool1);
      paramContextMenu.add(0, i | 0x3, 2, 2131165328);
      return;
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131689475, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 2131231293:
    }
    for (boolean bool = super.onOptionsItemSelected(paramMenuItem); ; bool = true)
    {
      return bool;
      refreshAllInfo();
    }
  }

  private class ErrorRunnable
    implements Runnable
  {
    private final Runnable mChainedRunnable;
    private final String mErrorMessage;

    public ErrorRunnable(String paramRunnable, Runnable arg3)
    {
      this.mErrorMessage = paramRunnable;
      Object localObject;
      this.mChainedRunnable = localObject;
    }

    public void run()
    {
      Toast.makeText(DcbDebugActivity.this, this.mErrorMessage, 1).show();
      this.mChainedRunnable.run();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.debug.DcbDebugActivity
 * JD-Core Version:    0.6.2
 */