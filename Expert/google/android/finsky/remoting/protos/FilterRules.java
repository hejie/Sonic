package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class FilterRules
{
  public static final class Availability extends MessageMicro
  {
    private boolean availableIfOwned_ = false;
    private int cachedSize = -1;
    private FilterRules.FilterEvaluationInfo filterInfo_ = null;
    private boolean hasAvailableIfOwned;
    private boolean hasFilterInfo;
    private boolean hasOfferType;
    private boolean hasOwnershipInfo;
    private boolean hasRestriction;
    private boolean hasRule;
    private List<Common.Install> install_ = Collections.emptyList();
    private int offerType_ = 1;
    private Common.OwnershipInfo ownershipInfo_ = null;
    private List<PerDeviceAvailabilityRestriction> perDeviceAvailabilityRestriction_ = Collections.emptyList();
    private int restriction_ = 1;
    private FilterRules.Rule rule_ = null;

    public Availability addInstall(Common.Install paramInstall)
    {
      if (paramInstall == null)
        throw new NullPointerException();
      if (this.install_.isEmpty())
        this.install_ = new ArrayList();
      this.install_.add(paramInstall);
      return this;
    }

    public Availability addPerDeviceAvailabilityRestriction(PerDeviceAvailabilityRestriction paramPerDeviceAvailabilityRestriction)
    {
      if (paramPerDeviceAvailabilityRestriction == null)
        throw new NullPointerException();
      if (this.perDeviceAvailabilityRestriction_.isEmpty())
        this.perDeviceAvailabilityRestriction_ = new ArrayList();
      this.perDeviceAvailabilityRestriction_.add(paramPerDeviceAvailabilityRestriction);
      return this;
    }

    public boolean getAvailableIfOwned()
    {
      return this.availableIfOwned_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public FilterRules.FilterEvaluationInfo getFilterInfo()
    {
      return this.filterInfo_;
    }

    public List<Common.Install> getInstallList()
    {
      return this.install_;
    }

    public int getOfferType()
    {
      return this.offerType_;
    }

    public Common.OwnershipInfo getOwnershipInfo()
    {
      return this.ownershipInfo_;
    }

    public List<PerDeviceAvailabilityRestriction> getPerDeviceAvailabilityRestrictionList()
    {
      return this.perDeviceAvailabilityRestriction_;
    }

    public int getRestriction()
    {
      return this.restriction_;
    }

    public FilterRules.Rule getRule()
    {
      return this.rule_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasRestriction())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(5, getRestriction());
      if (hasOfferType())
        i += CodedOutputStreamMicro.computeInt32Size(6, getOfferType());
      if (hasRule())
        i += CodedOutputStreamMicro.computeMessageSize(7, getRule());
      Iterator localIterator1 = getPerDeviceAvailabilityRestrictionList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeGroupSize(9, (PerDeviceAvailabilityRestriction)localIterator1.next());
      if (hasAvailableIfOwned())
        i += CodedOutputStreamMicro.computeBoolSize(13, getAvailableIfOwned());
      Iterator localIterator2 = getInstallList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(14, (Common.Install)localIterator2.next());
      if (hasFilterInfo())
        i += CodedOutputStreamMicro.computeMessageSize(16, getFilterInfo());
      if (hasOwnershipInfo())
        i += CodedOutputStreamMicro.computeMessageSize(17, getOwnershipInfo());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAvailableIfOwned()
    {
      return this.hasAvailableIfOwned;
    }

    public boolean hasFilterInfo()
    {
      return this.hasFilterInfo;
    }

    public boolean hasOfferType()
    {
      return this.hasOfferType;
    }

    public boolean hasOwnershipInfo()
    {
      return this.hasOwnershipInfo;
    }

    public boolean hasRestriction()
    {
      return this.hasRestriction;
    }

    public boolean hasRule()
    {
      return this.hasRule;
    }

    public Availability mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 40:
          setRestriction(paramCodedInputStreamMicro.readInt32());
          break;
        case 48:
          setOfferType(paramCodedInputStreamMicro.readInt32());
          break;
        case 58:
          FilterRules.Rule localRule = new FilterRules.Rule();
          paramCodedInputStreamMicro.readMessage(localRule);
          setRule(localRule);
          break;
        case 75:
          PerDeviceAvailabilityRestriction localPerDeviceAvailabilityRestriction = new PerDeviceAvailabilityRestriction();
          paramCodedInputStreamMicro.readGroup(localPerDeviceAvailabilityRestriction, 9);
          addPerDeviceAvailabilityRestriction(localPerDeviceAvailabilityRestriction);
          break;
        case 104:
          setAvailableIfOwned(paramCodedInputStreamMicro.readBool());
          break;
        case 114:
          Common.Install localInstall = new Common.Install();
          paramCodedInputStreamMicro.readMessage(localInstall);
          addInstall(localInstall);
          break;
        case 130:
          FilterRules.FilterEvaluationInfo localFilterEvaluationInfo = new FilterRules.FilterEvaluationInfo();
          paramCodedInputStreamMicro.readMessage(localFilterEvaluationInfo);
          setFilterInfo(localFilterEvaluationInfo);
          break;
        case 138:
        }
        Common.OwnershipInfo localOwnershipInfo = new Common.OwnershipInfo();
        paramCodedInputStreamMicro.readMessage(localOwnershipInfo);
        setOwnershipInfo(localOwnershipInfo);
      }
    }

    public Availability setAvailableIfOwned(boolean paramBoolean)
    {
      this.hasAvailableIfOwned = true;
      this.availableIfOwned_ = paramBoolean;
      return this;
    }

    public Availability setFilterInfo(FilterRules.FilterEvaluationInfo paramFilterEvaluationInfo)
    {
      if (paramFilterEvaluationInfo == null)
        throw new NullPointerException();
      this.hasFilterInfo = true;
      this.filterInfo_ = paramFilterEvaluationInfo;
      return this;
    }

    public Availability setOfferType(int paramInt)
    {
      this.hasOfferType = true;
      this.offerType_ = paramInt;
      return this;
    }

    public Availability setOwnershipInfo(Common.OwnershipInfo paramOwnershipInfo)
    {
      if (paramOwnershipInfo == null)
        throw new NullPointerException();
      this.hasOwnershipInfo = true;
      this.ownershipInfo_ = paramOwnershipInfo;
      return this;
    }

    public Availability setRestriction(int paramInt)
    {
      this.hasRestriction = true;
      this.restriction_ = paramInt;
      return this;
    }

    public Availability setRule(FilterRules.Rule paramRule)
    {
      if (paramRule == null)
        throw new NullPointerException();
      this.hasRule = true;
      this.rule_ = paramRule;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasRestriction())
        paramCodedOutputStreamMicro.writeInt32(5, getRestriction());
      if (hasOfferType())
        paramCodedOutputStreamMicro.writeInt32(6, getOfferType());
      if (hasRule())
        paramCodedOutputStreamMicro.writeMessage(7, getRule());
      Iterator localIterator1 = getPerDeviceAvailabilityRestrictionList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeGroup(9, (PerDeviceAvailabilityRestriction)localIterator1.next());
      if (hasAvailableIfOwned())
        paramCodedOutputStreamMicro.writeBool(13, getAvailableIfOwned());
      Iterator localIterator2 = getInstallList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(14, (Common.Install)localIterator2.next());
      if (hasFilterInfo())
        paramCodedOutputStreamMicro.writeMessage(16, getFilterInfo());
      if (hasOwnershipInfo())
        paramCodedOutputStreamMicro.writeMessage(17, getOwnershipInfo());
    }

    public static final class PerDeviceAvailabilityRestriction extends MessageMicro
    {
      private long androidId_ = 0L;
      private int cachedSize = -1;
      private long channelId_ = 0L;
      private int deviceRestriction_ = 1;
      private FilterRules.FilterEvaluationInfo filterInfo_ = null;
      private boolean hasAndroidId;
      private boolean hasChannelId;
      private boolean hasDeviceRestriction;
      private boolean hasFilterInfo;

      public long getAndroidId()
      {
        return this.androidId_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public long getChannelId()
      {
        return this.channelId_;
      }

      public int getDeviceRestriction()
      {
        return this.deviceRestriction_;
      }

      public FilterRules.FilterEvaluationInfo getFilterInfo()
      {
        return this.filterInfo_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasAndroidId())
          i = 0 + CodedOutputStreamMicro.computeFixed64Size(10, getAndroidId());
        if (hasDeviceRestriction())
          i += CodedOutputStreamMicro.computeInt32Size(11, getDeviceRestriction());
        if (hasChannelId())
          i += CodedOutputStreamMicro.computeInt64Size(12, getChannelId());
        if (hasFilterInfo())
          i += CodedOutputStreamMicro.computeMessageSize(15, getFilterInfo());
        this.cachedSize = i;
        return i;
      }

      public boolean hasAndroidId()
      {
        return this.hasAndroidId;
      }

      public boolean hasChannelId()
      {
        return this.hasChannelId;
      }

      public boolean hasDeviceRestriction()
      {
        return this.hasDeviceRestriction;
      }

      public boolean hasFilterInfo()
      {
        return this.hasFilterInfo;
      }

      public PerDeviceAvailabilityRestriction mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 81:
            setAndroidId(paramCodedInputStreamMicro.readFixed64());
            break;
          case 88:
            setDeviceRestriction(paramCodedInputStreamMicro.readInt32());
            break;
          case 96:
            setChannelId(paramCodedInputStreamMicro.readInt64());
            break;
          case 122:
          }
          FilterRules.FilterEvaluationInfo localFilterEvaluationInfo = new FilterRules.FilterEvaluationInfo();
          paramCodedInputStreamMicro.readMessage(localFilterEvaluationInfo);
          setFilterInfo(localFilterEvaluationInfo);
        }
      }

      public PerDeviceAvailabilityRestriction setAndroidId(long paramLong)
      {
        this.hasAndroidId = true;
        this.androidId_ = paramLong;
        return this;
      }

      public PerDeviceAvailabilityRestriction setChannelId(long paramLong)
      {
        this.hasChannelId = true;
        this.channelId_ = paramLong;
        return this;
      }

      public PerDeviceAvailabilityRestriction setDeviceRestriction(int paramInt)
      {
        this.hasDeviceRestriction = true;
        this.deviceRestriction_ = paramInt;
        return this;
      }

      public PerDeviceAvailabilityRestriction setFilterInfo(FilterRules.FilterEvaluationInfo paramFilterEvaluationInfo)
      {
        if (paramFilterEvaluationInfo == null)
          throw new NullPointerException();
        this.hasFilterInfo = true;
        this.filterInfo_ = paramFilterEvaluationInfo;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasAndroidId())
          paramCodedOutputStreamMicro.writeFixed64(10, getAndroidId());
        if (hasDeviceRestriction())
          paramCodedOutputStreamMicro.writeInt32(11, getDeviceRestriction());
        if (hasChannelId())
          paramCodedOutputStreamMicro.writeInt64(12, getChannelId());
        if (hasFilterInfo())
          paramCodedOutputStreamMicro.writeMessage(15, getFilterInfo());
      }
    }
  }

  public static final class FilterEvaluationInfo extends MessageMicro
  {
    private int cachedSize = -1;
    private List<FilterRules.RuleEvaluation> ruleEvaluation_ = Collections.emptyList();

    public FilterEvaluationInfo addRuleEvaluation(FilterRules.RuleEvaluation paramRuleEvaluation)
    {
      if (paramRuleEvaluation == null)
        throw new NullPointerException();
      if (this.ruleEvaluation_.isEmpty())
        this.ruleEvaluation_ = new ArrayList();
      this.ruleEvaluation_.add(paramRuleEvaluation);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<FilterRules.RuleEvaluation> getRuleEvaluationList()
    {
      return this.ruleEvaluation_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getRuleEvaluationList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (FilterRules.RuleEvaluation)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public FilterEvaluationInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        FilterRules.RuleEvaluation localRuleEvaluation = new FilterRules.RuleEvaluation();
        paramCodedInputStreamMicro.readMessage(localRuleEvaluation);
        addRuleEvaluation(localRuleEvaluation);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getRuleEvaluationList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (FilterRules.RuleEvaluation)localIterator.next());
    }
  }

  public static final class Rule extends MessageMicro
  {
    private int cachedSize = -1;
    private String comment_ = "";
    private List<Integer> constArg_ = Collections.emptyList();
    private List<Double> doubleArg_ = Collections.emptyList();
    private boolean hasComment;
    private boolean hasKey;
    private boolean hasNegate;
    private boolean hasOperator;
    private boolean hasResponseCode;
    private int key_ = 1;
    private List<Long> longArg_ = Collections.emptyList();
    private boolean negate_ = false;
    private int operator_ = 1;
    private int responseCode_ = 1;
    private List<Long> stringArgHash_ = Collections.emptyList();
    private List<String> stringArg_ = Collections.emptyList();
    private List<Rule> subrule_ = Collections.emptyList();

    public Rule addConstArg(int paramInt)
    {
      if (this.constArg_.isEmpty())
        this.constArg_ = new ArrayList();
      this.constArg_.add(Integer.valueOf(paramInt));
      return this;
    }

    public Rule addDoubleArg(double paramDouble)
    {
      if (this.doubleArg_.isEmpty())
        this.doubleArg_ = new ArrayList();
      this.doubleArg_.add(Double.valueOf(paramDouble));
      return this;
    }

    public Rule addLongArg(long paramLong)
    {
      if (this.longArg_.isEmpty())
        this.longArg_ = new ArrayList();
      this.longArg_.add(Long.valueOf(paramLong));
      return this;
    }

    public Rule addStringArg(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.stringArg_.isEmpty())
        this.stringArg_ = new ArrayList();
      this.stringArg_.add(paramString);
      return this;
    }

    public Rule addStringArgHash(long paramLong)
    {
      if (this.stringArgHash_.isEmpty())
        this.stringArgHash_ = new ArrayList();
      this.stringArgHash_.add(Long.valueOf(paramLong));
      return this;
    }

    public Rule addSubrule(Rule paramRule)
    {
      if (paramRule == null)
        throw new NullPointerException();
      if (this.subrule_.isEmpty())
        this.subrule_ = new ArrayList();
      this.subrule_.add(paramRule);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getComment()
    {
      return this.comment_;
    }

    public List<Integer> getConstArgList()
    {
      return this.constArg_;
    }

    public List<Double> getDoubleArgList()
    {
      return this.doubleArg_;
    }

    public int getKey()
    {
      return this.key_;
    }

    public List<Long> getLongArgList()
    {
      return this.longArg_;
    }

    public boolean getNegate()
    {
      return this.negate_;
    }

    public int getOperator()
    {
      return this.operator_;
    }

    public int getResponseCode()
    {
      return this.responseCode_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasNegate())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getNegate());
      if (hasOperator())
        i += CodedOutputStreamMicro.computeInt32Size(2, getOperator());
      if (hasKey())
        i += CodedOutputStreamMicro.computeInt32Size(3, getKey());
      int j = 0;
      Iterator localIterator1 = getStringArgList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int k = i + j + 1 * getStringArgList().size();
      int m = 0;
      Iterator localIterator2 = getLongArgList().iterator();
      while (localIterator2.hasNext())
        m += CodedOutputStreamMicro.computeInt64SizeNoTag(((Long)localIterator2.next()).longValue());
      int n = k + m + 1 * getLongArgList().size() + 8 * getDoubleArgList().size() + 1 * getDoubleArgList().size();
      Iterator localIterator3 = getSubruleList().iterator();
      while (localIterator3.hasNext())
        n += CodedOutputStreamMicro.computeMessageSize(7, (Rule)localIterator3.next());
      if (hasResponseCode())
        n += CodedOutputStreamMicro.computeInt32Size(8, getResponseCode());
      if (hasComment())
        n += CodedOutputStreamMicro.computeStringSize(9, getComment());
      int i1 = n + 8 * getStringArgHashList().size() + 1 * getStringArgHashList().size();
      int i2 = 0;
      Iterator localIterator4 = getConstArgList().iterator();
      while (localIterator4.hasNext())
        i2 += CodedOutputStreamMicro.computeInt32SizeNoTag(((Integer)localIterator4.next()).intValue());
      int i3 = i1 + i2 + 1 * getConstArgList().size();
      this.cachedSize = i3;
      return i3;
    }

    public List<Long> getStringArgHashList()
    {
      return this.stringArgHash_;
    }

    public List<String> getStringArgList()
    {
      return this.stringArg_;
    }

    public List<Rule> getSubruleList()
    {
      return this.subrule_;
    }

    public boolean hasComment()
    {
      return this.hasComment;
    }

    public boolean hasKey()
    {
      return this.hasKey;
    }

    public boolean hasNegate()
    {
      return this.hasNegate;
    }

    public boolean hasOperator()
    {
      return this.hasOperator;
    }

    public boolean hasResponseCode()
    {
      return this.hasResponseCode;
    }

    public Rule mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 8:
          setNegate(paramCodedInputStreamMicro.readBool());
          break;
        case 16:
          setOperator(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
          setKey(paramCodedInputStreamMicro.readInt32());
          break;
        case 34:
          addStringArg(paramCodedInputStreamMicro.readString());
          break;
        case 40:
          addLongArg(paramCodedInputStreamMicro.readInt64());
          break;
        case 49:
          addDoubleArg(paramCodedInputStreamMicro.readDouble());
          break;
        case 58:
          Rule localRule = new Rule();
          paramCodedInputStreamMicro.readMessage(localRule);
          addSubrule(localRule);
          break;
        case 64:
          setResponseCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 74:
          setComment(paramCodedInputStreamMicro.readString());
          break;
        case 81:
          addStringArgHash(paramCodedInputStreamMicro.readFixed64());
          break;
        case 88:
        }
        addConstArg(paramCodedInputStreamMicro.readInt32());
      }
    }

    public Rule setComment(String paramString)
    {
      this.hasComment = true;
      this.comment_ = paramString;
      return this;
    }

    public Rule setKey(int paramInt)
    {
      this.hasKey = true;
      this.key_ = paramInt;
      return this;
    }

    public Rule setNegate(boolean paramBoolean)
    {
      this.hasNegate = true;
      this.negate_ = paramBoolean;
      return this;
    }

    public Rule setOperator(int paramInt)
    {
      this.hasOperator = true;
      this.operator_ = paramInt;
      return this;
    }

    public Rule setResponseCode(int paramInt)
    {
      this.hasResponseCode = true;
      this.responseCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasNegate())
        paramCodedOutputStreamMicro.writeBool(1, getNegate());
      if (hasOperator())
        paramCodedOutputStreamMicro.writeInt32(2, getOperator());
      if (hasKey())
        paramCodedOutputStreamMicro.writeInt32(3, getKey());
      Iterator localIterator1 = getStringArgList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(4, (String)localIterator1.next());
      Iterator localIterator2 = getLongArgList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeInt64(5, ((Long)localIterator2.next()).longValue());
      Iterator localIterator3 = getDoubleArgList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeDouble(6, ((Double)localIterator3.next()).doubleValue());
      Iterator localIterator4 = getSubruleList().iterator();
      while (localIterator4.hasNext())
        paramCodedOutputStreamMicro.writeMessage(7, (Rule)localIterator4.next());
      if (hasResponseCode())
        paramCodedOutputStreamMicro.writeInt32(8, getResponseCode());
      if (hasComment())
        paramCodedOutputStreamMicro.writeString(9, getComment());
      Iterator localIterator5 = getStringArgHashList().iterator();
      while (localIterator5.hasNext())
        paramCodedOutputStreamMicro.writeFixed64(10, ((Long)localIterator5.next()).longValue());
      Iterator localIterator6 = getConstArgList().iterator();
      while (localIterator6.hasNext())
        paramCodedOutputStreamMicro.writeInt32(11, ((Integer)localIterator6.next()).intValue());
    }
  }

  public static final class RuleEvaluation extends MessageMicro
  {
    private List<Boolean> actualBoolValue_ = Collections.emptyList();
    private List<Double> actualDoubleValue_ = Collections.emptyList();
    private List<Long> actualLongValue_ = Collections.emptyList();
    private List<String> actualStringValue_ = Collections.emptyList();
    private int cachedSize = -1;
    private boolean hasRule;
    private FilterRules.Rule rule_ = null;

    public RuleEvaluation addActualBoolValue(boolean paramBoolean)
    {
      if (this.actualBoolValue_.isEmpty())
        this.actualBoolValue_ = new ArrayList();
      this.actualBoolValue_.add(Boolean.valueOf(paramBoolean));
      return this;
    }

    public RuleEvaluation addActualDoubleValue(double paramDouble)
    {
      if (this.actualDoubleValue_.isEmpty())
        this.actualDoubleValue_ = new ArrayList();
      this.actualDoubleValue_.add(Double.valueOf(paramDouble));
      return this;
    }

    public RuleEvaluation addActualLongValue(long paramLong)
    {
      if (this.actualLongValue_.isEmpty())
        this.actualLongValue_ = new ArrayList();
      this.actualLongValue_.add(Long.valueOf(paramLong));
      return this;
    }

    public RuleEvaluation addActualStringValue(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.actualStringValue_.isEmpty())
        this.actualStringValue_ = new ArrayList();
      this.actualStringValue_.add(paramString);
      return this;
    }

    public List<Boolean> getActualBoolValueList()
    {
      return this.actualBoolValue_;
    }

    public List<Double> getActualDoubleValueList()
    {
      return this.actualDoubleValue_;
    }

    public List<Long> getActualLongValueList()
    {
      return this.actualLongValue_;
    }

    public List<String> getActualStringValueList()
    {
      return this.actualStringValue_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public FilterRules.Rule getRule()
    {
      return this.rule_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasRule())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getRule());
      int j = 0;
      Iterator localIterator1 = getActualStringValueList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int k = i + j + 1 * getActualStringValueList().size();
      int m = 0;
      Iterator localIterator2 = getActualLongValueList().iterator();
      while (localIterator2.hasNext())
        m += CodedOutputStreamMicro.computeInt64SizeNoTag(((Long)localIterator2.next()).longValue());
      int n = k + m + 1 * getActualLongValueList().size() + 1 * getActualBoolValueList().size() + 1 * getActualBoolValueList().size() + 8 * getActualDoubleValueList().size() + 1 * getActualDoubleValueList().size();
      this.cachedSize = n;
      return n;
    }

    public boolean hasRule()
    {
      return this.hasRule;
    }

    public RuleEvaluation mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          FilterRules.Rule localRule = new FilterRules.Rule();
          paramCodedInputStreamMicro.readMessage(localRule);
          setRule(localRule);
          break;
        case 18:
          addActualStringValue(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          addActualLongValue(paramCodedInputStreamMicro.readInt64());
          break;
        case 32:
          addActualBoolValue(paramCodedInputStreamMicro.readBool());
          break;
        case 41:
        }
        addActualDoubleValue(paramCodedInputStreamMicro.readDouble());
      }
    }

    public RuleEvaluation setRule(FilterRules.Rule paramRule)
    {
      if (paramRule == null)
        throw new NullPointerException();
      this.hasRule = true;
      this.rule_ = paramRule;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasRule())
        paramCodedOutputStreamMicro.writeMessage(1, getRule());
      Iterator localIterator1 = getActualStringValueList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(2, (String)localIterator1.next());
      Iterator localIterator2 = getActualLongValueList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeInt64(3, ((Long)localIterator2.next()).longValue());
      Iterator localIterator3 = getActualBoolValueList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeBool(4, ((Boolean)localIterator3.next()).booleanValue());
      Iterator localIterator4 = getActualDoubleValueList().iterator();
      while (localIterator4.hasNext())
        paramCodedOutputStreamMicro.writeDouble(5, ((Double)localIterator4.next()).doubleValue());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.FilterRules
 * JD-Core Version:    0.6.2
 */