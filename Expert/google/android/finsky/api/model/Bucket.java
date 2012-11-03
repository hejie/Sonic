package com.google.android.finsky.api.model;

import android.text.TextUtils;
import com.google.android.finsky.remoting.protos.Containers.ContainerMetadata;
import com.google.android.finsky.remoting.protos.DocAnnotations.ContainerWithBanner;
import com.google.android.finsky.remoting.protos.DocAnnotations.EditorialSeriesContainer;
import com.google.android.finsky.remoting.protos.DocAnnotations.Template;
import com.google.android.finsky.remoting.protos.DocumentV2.Annotations;
import com.google.android.finsky.remoting.protos.DocumentV2.DocV2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bucket
{
  private List<Document> mChildren = null;
  private DocumentV2.DocV2 mDocument;

  public Bucket(DocumentV2.DocV2 paramDocV2)
  {
    this.mDocument = paramDocV2;
  }

  public String getAnalyticsCookie()
  {
    if (!this.mDocument.hasContainerMetadata());
    for (String str = null; ; str = this.mDocument.getContainerMetadata().getAnalyticsCookie())
      return str;
  }

  public int getBackend()
  {
    return this.mDocument.getBackendId();
  }

  public String getBrowseUrl()
  {
    return this.mDocument.getContainerMetadata().getBrowseUrl();
  }

  public int getChildCount()
  {
    return this.mDocument.getChildCount();
  }

  public List<Document> getChildren()
  {
    if (this.mChildren == null)
    {
      this.mChildren = new ArrayList();
      Iterator localIterator = this.mDocument.getChildList().iterator();
      while (localIterator.hasNext())
      {
        DocumentV2.DocV2 localDocV2 = (DocumentV2.DocV2)localIterator.next();
        this.mChildren.add(new Document(localDocV2, getAnalyticsCookie()));
      }
    }
    return this.mChildren;
  }

  public DocAnnotations.ContainerWithBanner getContainerWithBannerTemplate()
  {
    return this.mDocument.getAnnotations().getTemplate().getContainerWithBanner();
  }

  public Document getDocument()
  {
    return new Document(this.mDocument, null);
  }

  public DocAnnotations.EditorialSeriesContainer getEditorialSeriesContainer()
  {
    return this.mDocument.getAnnotations().getTemplate().getEditorialSeriesContainer();
  }

  public int getEstimatedResults()
  {
    return (int)this.mDocument.getContainerMetadata().getEstimatedResults();
  }

  public String getTitle()
  {
    return this.mDocument.getTitle();
  }

  public boolean hasContainerWithBannerTemplate()
  {
    if ((this.mDocument.getAnnotations() != null) && (this.mDocument.getAnnotations().hasTemplate()) && (this.mDocument.getAnnotations().getTemplate().hasContainerWithBanner()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasEditorialSeriesContainer()
  {
    if ((this.mDocument.getAnnotations() != null) && (this.mDocument.getAnnotations().hasTemplate()) && (this.mDocument.getAnnotations().getTemplate().hasEditorialSeriesContainer()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasMoreItems()
  {
    if (!TextUtils.isEmpty(this.mDocument.getContainerMetadata().getBrowseUrl()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasRecommendationsTemplate()
  {
    if ((this.mDocument.getAnnotations() != null) && (this.mDocument.getAnnotations().hasTemplate()) && (this.mDocument.getAnnotations().getTemplate().hasRecommendationsContainer()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isOrdered()
  {
    if (!this.mDocument.hasContainerMetadata());
    for (boolean bool = false; ; bool = this.mDocument.getContainerMetadata().getOrdered())
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.Bucket
 * JD-Core Version:    0.6.2
 */