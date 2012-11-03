package com.google.android.finsky.billing.carrierbilling.action;

import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters.Builder;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.vending.remoting.protos.VendingProtos.BillingParameterProto;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;
import java.util.Iterator;
import java.util.List;

public class CarrierParamsAction
{
  private final VendingProtos.GetMarketMetadataResponseProto mResponse;

  public CarrierParamsAction(VendingProtos.GetMarketMetadataResponseProto paramGetMarketMetadataResponseProto)
  {
    this.mResponse = paramGetMarketMetadataResponseProto;
  }

  CarrierBillingParameters createCarrierBillingParameters(VendingProtos.GetMarketMetadataResponseProto paramGetMarketMetadataResponseProto)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    if (paramGetMarketMetadataResponseProto == null);
    while (true)
    {
      return localObject1;
      Iterator localIterator = paramGetMarketMetadataResponseProto.getBillingParameterList().iterator();
      while (localIterator.hasNext())
      {
        VendingProtos.BillingParameterProto localBillingParameterProto = (VendingProtos.BillingParameterProto)localIterator.next();
        if (localBillingParameterProto.getBillingInstrumentType() == 1)
          localObject2 = localBillingParameterProto;
      }
      if (localObject2 != null)
        try
        {
          CarrierBillingParameters localCarrierBillingParameters = new CarrierBillingParameters.Builder().setId(localObject2.getId()).setName(localObject2.getName()).setMncMccs(localObject2.getMncMccList()).setGetProvisioningUrl(localObject2.getBackendUrl(0)).setGetCredentialsUrl(localObject2.getBackendUrl(1)).setCarrierIconId(localObject2.getIconId()).setShowCarrierTos(localObject2.getInstrumentTosRequired()).setCarrierApiVersion(localObject2.getApiVersion()).setPerTransactionCredentialsRequired(localObject2.getPerTransactionCredentialsRequired()).setSendSubscriberInfoWithCarrierRequests(localObject2.getSendSubscriberIdWithCarrierBillingRequests()).setAssociationMethod(localObject2.getDeviceAssociationMethod()).setRequestUserTokenText(localObject2.getUserTokenRequestMessage()).setRequestUserTokenTo(localObject2.getUserTokenRequestAddress()).setPasswordRequired(localObject2.getPassphraseRequired()).build();
          localObject1 = localCarrierBillingParameters;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          FinskyLog.e("Missing fields for creating carrier billing parameters", new Object[0]);
          localObject1 = null;
        }
    }
  }

  public void run(Runnable paramRunnable)
  {
    CarrierBillingParameters localCarrierBillingParameters = createCarrierBillingParameters(this.mResponse);
    if (localCarrierBillingParameters != null)
      BillingLocator.getCarrierBillingStorage().setParams(localCarrierBillingParameters);
    while (true)
    {
      paramRunnable.run();
      return;
      FinskyLog.w("Saving carrier billing params failed.", new Object[0]);
      BillingLocator.getCarrierBillingStorage().clearParams();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.action.CarrierParamsAction
 * JD-Core Version:    0.6.2
 */