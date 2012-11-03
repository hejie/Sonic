package com.google.android.finsky.api.model;

import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.remoting.protos.Details.BulkDetailsEntry;
import com.google.android.finsky.remoting.protos.Details.BulkDetailsResponse;
import com.google.android.finsky.remoting.protos.DocumentV2.DocV2;
import com.google.android.finsky.utils.DfeLog;
import java.util.ArrayList;
import java.util.List;

public class DfeBulkDetails extends DfeModel
  implements Response.Listener<Details.BulkDetailsResponse>
{
  private final String mAnalyticsCookie;
  private Details.BulkDetailsResponse mBulkDetailsResponse;
  private final List<String> mDocids;

  public DfeBulkDetails(DfeApi paramDfeApi, List<String> paramList)
  {
    this(paramDfeApi, paramList, null);
  }

  public DfeBulkDetails(DfeApi paramDfeApi, List<String> paramList, String paramString)
  {
    this.mAnalyticsCookie = paramString;
    this.mDocids = paramList;
    paramDfeApi.getDetails(this.mDocids, this, this);
  }

  public List<Document> getDocuments()
  {
    if (this.mBulkDetailsResponse == null)
    {
      localObject = null;
      return localObject;
    }
    Object localObject = new ArrayList();
    int i = 0;
    label21: DocumentV2.DocV2 localDocV2;
    if (i < this.mBulkDetailsResponse.getEntryCount())
    {
      localDocV2 = this.mBulkDetailsResponse.getEntry(i).getDoc();
      if (localDocV2 != null)
        break label87;
      if (DfeLog.DEBUG)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = this.mDocids.get(i);
        DfeLog.v("Null document for requested docid: %s ", arrayOfObject);
      }
    }
    while (true)
    {
      i++;
      break label21;
      break;
      label87: ((List)localObject).add(new Document(localDocV2, this.mAnalyticsCookie));
    }
  }

  public boolean isReady()
  {
    if (this.mBulkDetailsResponse != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onResponse(Details.BulkDetailsResponse paramBulkDetailsResponse)
  {
    this.mBulkDetailsResponse = paramBulkDetailsResponse;
    notifyDataSetChanged();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.DfeBulkDetails
 * JD-Core Version:    0.6.2
 */