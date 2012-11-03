package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class CheckPromoOffer
{
  public static final class AddCreditCardPromoOffer extends MessageMicro
  {
    private int cachedSize = -1;
    private String descriptionHtml_ = "";
    private boolean hasDescriptionHtml;
    private boolean hasHeaderText;
    private boolean hasImage;
    private boolean hasIntroductoryTextHtml;
    private boolean hasNoActionDescription;
    private boolean hasOfferTitle;
    private boolean hasTermsAndConditionsHtml;
    private String headerText_ = "";
    private Doc.Image image_ = null;
    private String introductoryTextHtml_ = "";
    private String noActionDescription_ = "";
    private String offerTitle_ = "";
    private String termsAndConditionsHtml_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDescriptionHtml()
    {
      return this.descriptionHtml_;
    }

    public String getHeaderText()
    {
      return this.headerText_;
    }

    public Doc.Image getImage()
    {
      return this.image_;
    }

    public String getIntroductoryTextHtml()
    {
      return this.introductoryTextHtml_;
    }

    public String getNoActionDescription()
    {
      return this.noActionDescription_;
    }

    public String getOfferTitle()
    {
      return this.offerTitle_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasHeaderText())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getHeaderText());
      if (hasDescriptionHtml())
        i += CodedOutputStreamMicro.computeStringSize(2, getDescriptionHtml());
      if (hasImage())
        i += CodedOutputStreamMicro.computeMessageSize(3, getImage());
      if (hasIntroductoryTextHtml())
        i += CodedOutputStreamMicro.computeStringSize(4, getIntroductoryTextHtml());
      if (hasOfferTitle())
        i += CodedOutputStreamMicro.computeStringSize(5, getOfferTitle());
      if (hasNoActionDescription())
        i += CodedOutputStreamMicro.computeStringSize(6, getNoActionDescription());
      if (hasTermsAndConditionsHtml())
        i += CodedOutputStreamMicro.computeStringSize(7, getTermsAndConditionsHtml());
      this.cachedSize = i;
      return i;
    }

    public String getTermsAndConditionsHtml()
    {
      return this.termsAndConditionsHtml_;
    }

    public boolean hasDescriptionHtml()
    {
      return this.hasDescriptionHtml;
    }

    public boolean hasHeaderText()
    {
      return this.hasHeaderText;
    }

    public boolean hasImage()
    {
      return this.hasImage;
    }

    public boolean hasIntroductoryTextHtml()
    {
      return this.hasIntroductoryTextHtml;
    }

    public boolean hasNoActionDescription()
    {
      return this.hasNoActionDescription;
    }

    public boolean hasOfferTitle()
    {
      return this.hasOfferTitle;
    }

    public boolean hasTermsAndConditionsHtml()
    {
      return this.hasTermsAndConditionsHtml;
    }

    public AddCreditCardPromoOffer mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      while (true)
      {
        int i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
          if (parseUnknownField(paramCodedInputStreamMicro, i))
            continue;
        case 0:
          return this;
        case 10:
          setHeaderText(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setDescriptionHtml(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          Doc.Image localImage = new Doc.Image();
          paramCodedInputStreamMicro.readMessage(localImage);
          setImage(localImage);
          break;
        case 34:
          setIntroductoryTextHtml(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setOfferTitle(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setNoActionDescription(paramCodedInputStreamMicro.readString());
          break;
        case 58:
        }
        setTermsAndConditionsHtml(paramCodedInputStreamMicro.readString());
      }
    }

    public AddCreditCardPromoOffer setDescriptionHtml(String paramString)
    {
      this.hasDescriptionHtml = true;
      this.descriptionHtml_ = paramString;
      return this;
    }

    public AddCreditCardPromoOffer setHeaderText(String paramString)
    {
      this.hasHeaderText = true;
      this.headerText_ = paramString;
      return this;
    }

    public AddCreditCardPromoOffer setImage(Doc.Image paramImage)
    {
      if (paramImage == null)
        throw new NullPointerException();
      this.hasImage = true;
      this.image_ = paramImage;
      return this;
    }

    public AddCreditCardPromoOffer setIntroductoryTextHtml(String paramString)
    {
      this.hasIntroductoryTextHtml = true;
      this.introductoryTextHtml_ = paramString;
      return this;
    }

    public AddCreditCardPromoOffer setNoActionDescription(String paramString)
    {
      this.hasNoActionDescription = true;
      this.noActionDescription_ = paramString;
      return this;
    }

    public AddCreditCardPromoOffer setOfferTitle(String paramString)
    {
      this.hasOfferTitle = true;
      this.offerTitle_ = paramString;
      return this;
    }

    public AddCreditCardPromoOffer setTermsAndConditionsHtml(String paramString)
    {
      this.hasTermsAndConditionsHtml = true;
      this.termsAndConditionsHtml_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasHeaderText())
        paramCodedOutputStreamMicro.writeString(1, getHeaderText());
      if (hasDescriptionHtml())
        paramCodedOutputStreamMicro.writeString(2, getDescriptionHtml());
      if (hasImage())
        paramCodedOutputStreamMicro.writeMessage(3, getImage());
      if (hasIntroductoryTextHtml())
        paramCodedOutputStreamMicro.writeString(4, getIntroductoryTextHtml());
      if (hasOfferTitle())
        paramCodedOutputStreamMicro.writeString(5, getOfferTitle());
      if (hasNoActionDescription())
        paramCodedOutputStreamMicro.writeString(6, getNoActionDescription());
      if (hasTermsAndConditionsHtml())
        paramCodedOutputStreamMicro.writeString(7, getTermsAndConditionsHtml());
    }
  }

  public static final class AvailablePromoOffer extends MessageMicro
  {
    private CheckPromoOffer.AddCreditCardPromoOffer addCreditCardOffer_ = null;
    private int cachedSize = -1;
    private boolean hasAddCreditCardOffer;

    public CheckPromoOffer.AddCreditCardPromoOffer getAddCreditCardOffer()
    {
      return this.addCreditCardOffer_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAddCreditCardOffer())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getAddCreditCardOffer());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAddCreditCardOffer()
    {
      return this.hasAddCreditCardOffer;
    }

    public AvailablePromoOffer mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      while (true)
      {
        int i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
          if (parseUnknownField(paramCodedInputStreamMicro, i))
            continue;
        case 0:
          return this;
        case 10:
        }
        CheckPromoOffer.AddCreditCardPromoOffer localAddCreditCardPromoOffer = new CheckPromoOffer.AddCreditCardPromoOffer();
        paramCodedInputStreamMicro.readMessage(localAddCreditCardPromoOffer);
        setAddCreditCardOffer(localAddCreditCardPromoOffer);
      }
    }

    public AvailablePromoOffer setAddCreditCardOffer(CheckPromoOffer.AddCreditCardPromoOffer paramAddCreditCardPromoOffer)
    {
      if (paramAddCreditCardPromoOffer == null)
        throw new NullPointerException();
      this.hasAddCreditCardOffer = true;
      this.addCreditCardOffer_ = paramAddCreditCardPromoOffer;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAddCreditCardOffer())
        paramCodedOutputStreamMicro.writeMessage(1, getAddCreditCardOffer());
    }
  }

  public static final class CheckPromoOfferResponse extends MessageMicro
  {
    private List<CheckPromoOffer.AvailablePromoOffer> availableOffer_ = Collections.emptyList();
    private int cachedSize = -1;
    private boolean checkoutTokenRequired_ = false;
    private boolean hasCheckoutTokenRequired;
    private boolean hasRedeemedOffer;
    private CheckPromoOffer.RedeemedPromoOffer redeemedOffer_ = null;

    public CheckPromoOfferResponse addAvailableOffer(CheckPromoOffer.AvailablePromoOffer paramAvailablePromoOffer)
    {
      if (paramAvailablePromoOffer == null)
        throw new NullPointerException();
      if (this.availableOffer_.isEmpty())
        this.availableOffer_ = new ArrayList();
      this.availableOffer_.add(paramAvailablePromoOffer);
      return this;
    }

    public CheckPromoOffer.AvailablePromoOffer getAvailableOffer(int paramInt)
    {
      return (CheckPromoOffer.AvailablePromoOffer)this.availableOffer_.get(paramInt);
    }

    public int getAvailableOfferCount()
    {
      return this.availableOffer_.size();
    }

    public List<CheckPromoOffer.AvailablePromoOffer> getAvailableOfferList()
    {
      return this.availableOffer_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getCheckoutTokenRequired()
    {
      return this.checkoutTokenRequired_;
    }

    public CheckPromoOffer.RedeemedPromoOffer getRedeemedOffer()
    {
      return this.redeemedOffer_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getAvailableOfferList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (CheckPromoOffer.AvailablePromoOffer)localIterator.next());
      if (hasRedeemedOffer())
        i += CodedOutputStreamMicro.computeMessageSize(2, getRedeemedOffer());
      if (hasCheckoutTokenRequired())
        i += CodedOutputStreamMicro.computeBoolSize(3, getCheckoutTokenRequired());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCheckoutTokenRequired()
    {
      return this.hasCheckoutTokenRequired;
    }

    public boolean hasRedeemedOffer()
    {
      return this.hasRedeemedOffer;
    }

    public CheckPromoOfferResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      while (true)
      {
        int i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
          if (parseUnknownField(paramCodedInputStreamMicro, i))
            continue;
        case 0:
          return this;
        case 10:
          CheckPromoOffer.AvailablePromoOffer localAvailablePromoOffer = new CheckPromoOffer.AvailablePromoOffer();
          paramCodedInputStreamMicro.readMessage(localAvailablePromoOffer);
          addAvailableOffer(localAvailablePromoOffer);
          break;
        case 18:
          CheckPromoOffer.RedeemedPromoOffer localRedeemedPromoOffer = new CheckPromoOffer.RedeemedPromoOffer();
          paramCodedInputStreamMicro.readMessage(localRedeemedPromoOffer);
          setRedeemedOffer(localRedeemedPromoOffer);
          break;
        case 24:
        }
        setCheckoutTokenRequired(paramCodedInputStreamMicro.readBool());
      }
    }

    public CheckPromoOfferResponse setCheckoutTokenRequired(boolean paramBoolean)
    {
      this.hasCheckoutTokenRequired = true;
      this.checkoutTokenRequired_ = paramBoolean;
      return this;
    }

    public CheckPromoOfferResponse setRedeemedOffer(CheckPromoOffer.RedeemedPromoOffer paramRedeemedPromoOffer)
    {
      if (paramRedeemedPromoOffer == null)
        throw new NullPointerException();
      this.hasRedeemedOffer = true;
      this.redeemedOffer_ = paramRedeemedPromoOffer;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getAvailableOfferList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (CheckPromoOffer.AvailablePromoOffer)localIterator.next());
      if (hasRedeemedOffer())
        paramCodedOutputStreamMicro.writeMessage(2, getRedeemedOffer());
      if (hasCheckoutTokenRequired())
        paramCodedOutputStreamMicro.writeBool(3, getCheckoutTokenRequired());
    }
  }

  public static final class RedeemedPromoOffer extends MessageMicro
  {
    private int cachedSize = -1;
    private String descriptionHtml_ = "";
    private boolean hasDescriptionHtml;
    private boolean hasHeaderText;
    private boolean hasImage;
    private String headerText_ = "";
    private Doc.Image image_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDescriptionHtml()
    {
      return this.descriptionHtml_;
    }

    public String getHeaderText()
    {
      return this.headerText_;
    }

    public Doc.Image getImage()
    {
      return this.image_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasHeaderText())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getHeaderText());
      if (hasDescriptionHtml())
        i += CodedOutputStreamMicro.computeStringSize(2, getDescriptionHtml());
      if (hasImage())
        i += CodedOutputStreamMicro.computeMessageSize(3, getImage());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDescriptionHtml()
    {
      return this.hasDescriptionHtml;
    }

    public boolean hasHeaderText()
    {
      return this.hasHeaderText;
    }

    public boolean hasImage()
    {
      return this.hasImage;
    }

    public RedeemedPromoOffer mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      while (true)
      {
        int i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
          if (parseUnknownField(paramCodedInputStreamMicro, i))
            continue;
        case 0:
          return this;
        case 10:
          setHeaderText(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setDescriptionHtml(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        Doc.Image localImage = new Doc.Image();
        paramCodedInputStreamMicro.readMessage(localImage);
        setImage(localImage);
      }
    }

    public RedeemedPromoOffer setDescriptionHtml(String paramString)
    {
      this.hasDescriptionHtml = true;
      this.descriptionHtml_ = paramString;
      return this;
    }

    public RedeemedPromoOffer setHeaderText(String paramString)
    {
      this.hasHeaderText = true;
      this.headerText_ = paramString;
      return this;
    }

    public RedeemedPromoOffer setImage(Doc.Image paramImage)
    {
      if (paramImage == null)
        throw new NullPointerException();
      this.hasImage = true;
      this.image_ = paramImage;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasHeaderText())
        paramCodedOutputStreamMicro.writeString(1, getHeaderText());
      if (hasDescriptionHtml())
        paramCodedOutputStreamMicro.writeString(2, getDescriptionHtml());
      if (hasImage())
        paramCodedOutputStreamMicro.writeMessage(3, getImage());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.CheckPromoOffer
 * JD-Core Version:    0.6.2
 */