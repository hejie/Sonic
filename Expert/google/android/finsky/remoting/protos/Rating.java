package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class Rating
{
  public static final class AggregateRating extends MessageMicro
  {
    private double bayesianMeanRating_ = 0.0D;
    private int cachedSize = -1;
    private long commentCount_ = 0L;
    private long fiveStarRatings_ = 0L;
    private long fourStarRatings_ = 0L;
    private boolean hasBayesianMeanRating;
    private boolean hasCommentCount;
    private boolean hasFiveStarRatings;
    private boolean hasFourStarRatings;
    private boolean hasOneStarRatings;
    private boolean hasRatingsCount;
    private boolean hasStarRating;
    private boolean hasThreeStarRatings;
    private boolean hasThumbsDownCount;
    private boolean hasThumbsUpCount;
    private boolean hasTwoStarRatings;
    private boolean hasType;
    private long oneStarRatings_ = 0L;
    private long ratingsCount_ = 0L;
    private float starRating_ = 0.0F;
    private long threeStarRatings_ = 0L;
    private long thumbsDownCount_ = 0L;
    private long thumbsUpCount_ = 0L;
    private long twoStarRatings_ = 0L;
    private int type_ = 1;

    public double getBayesianMeanRating()
    {
      return this.bayesianMeanRating_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getCommentCount()
    {
      return this.commentCount_;
    }

    public long getFiveStarRatings()
    {
      return this.fiveStarRatings_;
    }

    public long getFourStarRatings()
    {
      return this.fourStarRatings_;
    }

    public long getOneStarRatings()
    {
      return this.oneStarRatings_;
    }

    public long getRatingsCount()
    {
      return this.ratingsCount_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getType());
      if (hasStarRating())
        i += CodedOutputStreamMicro.computeFloatSize(2, getStarRating());
      if (hasRatingsCount())
        i += CodedOutputStreamMicro.computeUInt64Size(3, getRatingsCount());
      if (hasOneStarRatings())
        i += CodedOutputStreamMicro.computeUInt64Size(4, getOneStarRatings());
      if (hasTwoStarRatings())
        i += CodedOutputStreamMicro.computeUInt64Size(5, getTwoStarRatings());
      if (hasThreeStarRatings())
        i += CodedOutputStreamMicro.computeUInt64Size(6, getThreeStarRatings());
      if (hasFourStarRatings())
        i += CodedOutputStreamMicro.computeUInt64Size(7, getFourStarRatings());
      if (hasFiveStarRatings())
        i += CodedOutputStreamMicro.computeUInt64Size(8, getFiveStarRatings());
      if (hasThumbsUpCount())
        i += CodedOutputStreamMicro.computeUInt64Size(9, getThumbsUpCount());
      if (hasThumbsDownCount())
        i += CodedOutputStreamMicro.computeUInt64Size(10, getThumbsDownCount());
      if (hasCommentCount())
        i += CodedOutputStreamMicro.computeUInt64Size(11, getCommentCount());
      if (hasBayesianMeanRating())
        i += CodedOutputStreamMicro.computeDoubleSize(12, getBayesianMeanRating());
      this.cachedSize = i;
      return i;
    }

    public float getStarRating()
    {
      return this.starRating_;
    }

    public long getThreeStarRatings()
    {
      return this.threeStarRatings_;
    }

    public long getThumbsDownCount()
    {
      return this.thumbsDownCount_;
    }

    public long getThumbsUpCount()
    {
      return this.thumbsUpCount_;
    }

    public long getTwoStarRatings()
    {
      return this.twoStarRatings_;
    }

    public int getType()
    {
      return this.type_;
    }

    public boolean hasBayesianMeanRating()
    {
      return this.hasBayesianMeanRating;
    }

    public boolean hasCommentCount()
    {
      return this.hasCommentCount;
    }

    public boolean hasFiveStarRatings()
    {
      return this.hasFiveStarRatings;
    }

    public boolean hasFourStarRatings()
    {
      return this.hasFourStarRatings;
    }

    public boolean hasOneStarRatings()
    {
      return this.hasOneStarRatings;
    }

    public boolean hasRatingsCount()
    {
      return this.hasRatingsCount;
    }

    public boolean hasStarRating()
    {
      return this.hasStarRating;
    }

    public boolean hasThreeStarRatings()
    {
      return this.hasThreeStarRatings;
    }

    public boolean hasThumbsDownCount()
    {
      return this.hasThumbsDownCount;
    }

    public boolean hasThumbsUpCount()
    {
      return this.hasThumbsUpCount;
    }

    public boolean hasTwoStarRatings()
    {
      return this.hasTwoStarRatings;
    }

    public boolean hasType()
    {
      return this.hasType;
    }

    public AggregateRating mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setType(paramCodedInputStreamMicro.readInt32());
          break;
        case 21:
          setStarRating(paramCodedInputStreamMicro.readFloat());
          break;
        case 24:
          setRatingsCount(paramCodedInputStreamMicro.readUInt64());
          break;
        case 32:
          setOneStarRatings(paramCodedInputStreamMicro.readUInt64());
          break;
        case 40:
          setTwoStarRatings(paramCodedInputStreamMicro.readUInt64());
          break;
        case 48:
          setThreeStarRatings(paramCodedInputStreamMicro.readUInt64());
          break;
        case 56:
          setFourStarRatings(paramCodedInputStreamMicro.readUInt64());
          break;
        case 64:
          setFiveStarRatings(paramCodedInputStreamMicro.readUInt64());
          break;
        case 72:
          setThumbsUpCount(paramCodedInputStreamMicro.readUInt64());
          break;
        case 80:
          setThumbsDownCount(paramCodedInputStreamMicro.readUInt64());
          break;
        case 88:
          setCommentCount(paramCodedInputStreamMicro.readUInt64());
          break;
        case 97:
        }
        setBayesianMeanRating(paramCodedInputStreamMicro.readDouble());
      }
    }

    public AggregateRating setBayesianMeanRating(double paramDouble)
    {
      this.hasBayesianMeanRating = true;
      this.bayesianMeanRating_ = paramDouble;
      return this;
    }

    public AggregateRating setCommentCount(long paramLong)
    {
      this.hasCommentCount = true;
      this.commentCount_ = paramLong;
      return this;
    }

    public AggregateRating setFiveStarRatings(long paramLong)
    {
      this.hasFiveStarRatings = true;
      this.fiveStarRatings_ = paramLong;
      return this;
    }

    public AggregateRating setFourStarRatings(long paramLong)
    {
      this.hasFourStarRatings = true;
      this.fourStarRatings_ = paramLong;
      return this;
    }

    public AggregateRating setOneStarRatings(long paramLong)
    {
      this.hasOneStarRatings = true;
      this.oneStarRatings_ = paramLong;
      return this;
    }

    public AggregateRating setRatingsCount(long paramLong)
    {
      this.hasRatingsCount = true;
      this.ratingsCount_ = paramLong;
      return this;
    }

    public AggregateRating setStarRating(float paramFloat)
    {
      this.hasStarRating = true;
      this.starRating_ = paramFloat;
      return this;
    }

    public AggregateRating setThreeStarRatings(long paramLong)
    {
      this.hasThreeStarRatings = true;
      this.threeStarRatings_ = paramLong;
      return this;
    }

    public AggregateRating setThumbsDownCount(long paramLong)
    {
      this.hasThumbsDownCount = true;
      this.thumbsDownCount_ = paramLong;
      return this;
    }

    public AggregateRating setThumbsUpCount(long paramLong)
    {
      this.hasThumbsUpCount = true;
      this.thumbsUpCount_ = paramLong;
      return this;
    }

    public AggregateRating setTwoStarRatings(long paramLong)
    {
      this.hasTwoStarRatings = true;
      this.twoStarRatings_ = paramLong;
      return this;
    }

    public AggregateRating setType(int paramInt)
    {
      this.hasType = true;
      this.type_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasType())
        paramCodedOutputStreamMicro.writeInt32(1, getType());
      if (hasStarRating())
        paramCodedOutputStreamMicro.writeFloat(2, getStarRating());
      if (hasRatingsCount())
        paramCodedOutputStreamMicro.writeUInt64(3, getRatingsCount());
      if (hasOneStarRatings())
        paramCodedOutputStreamMicro.writeUInt64(4, getOneStarRatings());
      if (hasTwoStarRatings())
        paramCodedOutputStreamMicro.writeUInt64(5, getTwoStarRatings());
      if (hasThreeStarRatings())
        paramCodedOutputStreamMicro.writeUInt64(6, getThreeStarRatings());
      if (hasFourStarRatings())
        paramCodedOutputStreamMicro.writeUInt64(7, getFourStarRatings());
      if (hasFiveStarRatings())
        paramCodedOutputStreamMicro.writeUInt64(8, getFiveStarRatings());
      if (hasThumbsUpCount())
        paramCodedOutputStreamMicro.writeUInt64(9, getThumbsUpCount());
      if (hasThumbsDownCount())
        paramCodedOutputStreamMicro.writeUInt64(10, getThumbsDownCount());
      if (hasCommentCount())
        paramCodedOutputStreamMicro.writeUInt64(11, getCommentCount());
      if (hasBayesianMeanRating())
        paramCodedOutputStreamMicro.writeDouble(12, getBayesianMeanRating());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Rating
 * JD-Core Version:    0.6.2
 */