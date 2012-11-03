package com.google.android.play.analytics;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Message;
import android.util.Log;
import com.google.android.gsf.Gservices;
import com.google.android.play.utils.LoggableHandler;
import com.google.android.play.utils.PlayUtils;
import com.google.android.volley.GoogleHttpClient;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;

public class EventLogger extends LoggableHandler
{
  protected static final String LOG_FILE_DIRECTORY = "logs";
  private final String mAndroidId;
  private final Context mContext;
  private final long mDelayBetweenUploadsMs;
  private final GoogleHttpClient mHttpClient;
  private volatile boolean mIsLoggingEnabled = true;
  private final int mLogSource;
  private final String mLoggingId;
  private volatile long mNextAllowedUploadTime = 0L;
  private volatile OutputStream mOutputStream = null;
  private volatile CodedOutputStreamMicro mProtoWriter = null;
  private final RollingFileStream mRollingFileStream;
  private volatile Account mUploadAccount;

  public EventLogger(Context paramContext, String paramString1, Account paramAccount, LogSource paramLogSource, String paramString2)
  {
    super("PlayEventLogger");
    this.mContext = paramContext;
    this.mLogSource = paramLogSource.getProtoValue();
    this.mLoggingId = paramString1;
    setUploadAccount(paramAccount);
    if (paramString2 == null)
      paramString2 = PlayUtils.getDefaultUserAgentString(this.mContext);
    this.mHttpClient = new GoogleHttpClient(this.mContext, paramString2, true);
    ContentResolver localContentResolver = paramContext.getContentResolver();
    this.mAndroidId = String.valueOf(Gservices.getLong(localContentResolver, "android_id", 0L));
    if (this.mAndroidId == null)
      this.mIsLoggingEnabled = false;
    String str = Gservices.getString(localContentResolver, "playlog_enabled_sources");
    if ((str == null) || (!str.toLowerCase().contains(paramLogSource.name().toLowerCase())))
      this.mIsLoggingEnabled = false;
    this.mDelayBetweenUploadsMs = getDelayBetweenUploads();
    long l1 = getRecommendedLogFileSize();
    long l2 = getMaxStoreSize();
    int i = (int)getNumberOfFiles();
    this.mRollingFileStream = new RollingFileStream(new File(this.mContext.getCacheDir(), "logs"), getStoreFilenamePrefix(), i, l1, l2 / i);
    sendEmptyMessage(1);
  }

  private void addEventImpl(Message paramMessage)
  {
    if (this.mProtoWriter == null)
      Log.d("PlayEventLogger", "addEventImpl: mProtoWriter was null");
    while (true)
    {
      return;
      ClientsAnalytics.LogEvent localLogEvent = (ClientsAnalytics.LogEvent)paramMessage.obj;
      Log.d("PlayEventLogger", "addEventImpl: " + localLogEvent.getTag() + " size: " + localLogEvent.getSerializedSize());
      try
      {
        this.mProtoWriter.writeMessageNoTag(localLogEvent);
        this.mProtoWriter.flush();
        checkIfShouldUpload();
        ProtoCache.recycle(localLogEvent);
      }
      catch (IOException localIOException)
      {
        Log.e("PlayEventLogger", "Could not write string (" + localLogEvent + ") to file: " + localIOException.getMessage(), localIOException);
        ProtoCache.recycle(localLogEvent);
      }
      finally
      {
        ProtoCache.recycle(localLogEvent);
      }
    }
  }

  private void checkIfShouldUpload()
  {
    if (this.mRollingFileStream.hasFileReadyToSend())
      queueUpload(0L);
  }

  private long getGservicesValue(String paramString, long paramLong1, long paramLong2)
  {
    long l = Gservices.getLong(this.mContext.getContentResolver(), paramString, paramLong1);
    if (l <= paramLong2)
      l = paramLong1;
    return l;
  }

  private void loadFileImpl(Message paramMessage)
  {
    if (!this.mIsLoggingEnabled)
      Log.d("PlayEventLogger", "loadFileImpl: not continuing since logging disabled");
    while (true)
    {
      return;
      try
      {
        this.mOutputStream = this.mRollingFileStream.getOutputStream();
        this.mProtoWriter = CodedOutputStreamMicro.newInstance(this.mOutputStream);
        Log.d("PlayEventLogger", "loadFileImpl succeeded");
      }
      catch (IOException localIOException)
      {
        Log.e("PlayEventLogger", "Could not open log file " + localIOException.getMessage(), localIOException);
      }
    }
  }

  private void queueUpload(long paramLong)
  {
    long l1 = System.currentTimeMillis();
    long l2;
    if (paramLong > 0L)
    {
      l2 = paramLong;
      if (l1 + l2 < this.mNextAllowedUploadTime)
        paramLong = this.mNextAllowedUploadTime - System.currentTimeMillis();
      if (paramLong <= 0L)
        break label117;
      sendEmptyMessageDelayed(3, paramLong);
    }
    while (true)
    {
      Log.d("PlayEventLogger", "Upload queued for " + paramLong + " ms");
      this.mNextAllowedUploadTime = Math.max(this.mNextAllowedUploadTime, System.currentTimeMillis() + Gservices.getLong(this.mContext.getContentResolver(), "play_event_logging_min_delay", 60000L));
      return;
      l2 = 0L;
      break;
      label117: sendEmptyMessage(3);
    }
  }

  private void uploadEventsImpl()
  {
    if (this.mProtoWriter == null)
      Log.d("PlayEventLogger", "uploadEventsImpl: not continuing since proto writer was null");
    InputStream localInputStream;
    boolean bool1;
    while (true)
    {
      return;
      Account localAccount = this.mUploadAccount;
      localInputStream = null;
      bool1 = false;
      if (localAccount == null);
      try
      {
        Log.w("PlayEventLogger", "No account available for uploading logs.  Skipping upload");
        if (0 != 0)
        {
          try
          {
            this.mRollingFileStream.closeInputStream(null, false);
          }
          catch (IOException localIOException6)
          {
            Log.e("PlayEventLogger", "Error closing input stream: " + localIOException6.getMessage(), localIOException6);
          }
          continue;
          if (!this.mRollingFileStream.hasFileReadyToSend())
          {
            Log.d("PlayEventLogger", "uploadEventsImpl: no file ready to send");
            if (0 != 0)
              try
              {
                this.mRollingFileStream.closeInputStream(null, false);
              }
              catch (IOException localIOException5)
              {
                Log.e("PlayEventLogger", "Error closing input stream: " + localIOException5.getMessage(), localIOException5);
              }
          }
          else
          {
            Log.d("PlayEventLogger", "loadFileImpl: uploading Events");
            ClientsAnalytics.LogRequest localLogRequest = new ClientsAnalytics.LogRequest();
            localLogRequest.setSdkVersion(Build.VERSION.SDK_INT);
            localLogRequest.setModel(Build.MODEL);
            localLogRequest.setBuildId(Build.ID);
            if (this.mLoggingId != null)
              localLogRequest.setLoggingId(this.mLoggingId);
            localLogRequest.setAndroidId(this.mAndroidId);
            localLogRequest.setLogSource(this.mLogSource);
            Log.d("PlayEventLogger", "uploadLog: logrequest created with: " + localLogRequest.getClickEventList().size() + " events");
            try
            {
              localInputStream = this.mRollingFileStream.getInputStream();
              CodedInputStreamMicro localCodedInputStreamMicro = CodedInputStreamMicro.newInstance(localInputStream);
              while (!localCodedInputStreamMicro.isAtEnd())
              {
                ClientsAnalytics.LogEvent localLogEvent = ProtoCache.obtainEvent();
                localCodedInputStreamMicro.readMessage(localLogEvent);
                localLogRequest.addClickEvent(localLogEvent);
              }
            }
            catch (IOException localIOException2)
            {
              Log.e("PlayEventLogger", "Error reading file: " + localIOException2.getMessage(), localIOException2);
              bool1 = true;
              ProtoCache.recycleLogRequest(localLogRequest);
              if (localInputStream != null)
              {
                try
                {
                  this.mRollingFileStream.closeInputStream(localInputStream, bool1);
                }
                catch (IOException localIOException3)
                {
                  Log.e("PlayEventLogger", "Error closing input stream: " + localIOException3.getMessage(), localIOException3);
                }
                continue;
                boolean bool2 = uploadLog(localAccount, localLogRequest);
                bool1 = bool2;
                ProtoCache.recycleLogRequest(localLogRequest);
                if (localInputStream != null)
                  try
                  {
                    this.mRollingFileStream.closeInputStream(localInputStream, bool1);
                  }
                  catch (IOException localIOException4)
                  {
                    Log.e("PlayEventLogger", "Error closing input stream: " + localIOException4.getMessage(), localIOException4);
                  }
              }
            }
            finally
            {
              ProtoCache.recycleLogRequest(localLogRequest);
            }
          }
        }
      }
      finally
      {
        if (localInputStream == null);
      }
    }
    try
    {
      this.mRollingFileStream.closeInputStream(localInputStream, bool1);
      throw localObject1;
    }
    catch (IOException localIOException1)
    {
      while (true)
        Log.e("PlayEventLogger", "Error closing input stream: " + localIOException1.getMessage(), localIOException1);
    }
  }

  public void dispatchMessage(Message paramMessage)
  {
    if (!this.mIsLoggingEnabled);
    while (true)
    {
      return;
      switch (paramMessage.what)
      {
      default:
        Log.w("PlayEventLogger", "Unknown msg: " + paramMessage.what);
        break;
      case 1:
        loadFileImpl(paramMessage);
        queueUpload(this.mDelayBetweenUploadsMs);
        break;
      case 2:
        addEventImpl(paramMessage);
        break;
      case 3:
        removeMessages(3);
        uploadEventsImpl();
        queueUpload(this.mDelayBetweenUploadsMs);
        checkIfShouldUpload();
      }
    }
  }

  protected String getAuthToken(Account paramAccount)
  {
    Object localObject = null;
    if (paramAccount == null)
      Log.w("PlayEventLogger", "No account for auth token provided");
    while (true)
    {
      return localObject;
      try
      {
        String str = AccountManager.get(this.mContext).blockingGetAuthToken(paramAccount, "androidmarket", true);
        localObject = str;
      }
      catch (OperationCanceledException localOperationCanceledException)
      {
        Log.e("PlayEventLogger", localOperationCanceledException.getMessage());
      }
      catch (AuthenticatorException localAuthenticatorException)
      {
        Log.e("PlayEventLogger", localAuthenticatorException.getMessage());
      }
      catch (IOException localIOException)
      {
        Log.e("PlayEventLogger", localIOException.getMessage());
      }
    }
  }

  protected long getDelayBetweenUploads()
  {
    return getGservicesValue("play_event_logging_message_size", 300000L, 0L);
  }

  protected long getMaxStoreSize()
  {
    return getGservicesValue("play_event_logging_max_buffer_size", 2097152L, 1024L);
  }

  protected long getNumberOfFiles()
  {
    return getGservicesValue("play_event_logging_numfiles", 2L, 2L);
  }

  protected long getRecommendedLogFileSize()
  {
    return getGservicesValue("play_event_logging_message_size", 51200L, 0L);
  }

  protected String getStoreFilenamePrefix()
  {
    String str = PlayUtils.getProcessName();
    StringBuilder localStringBuilder = new StringBuilder("eventlog.store");
    if (str != null)
    {
      int i = str.indexOf(":");
      if ((i > 0) && (i < -2 + str.length()))
      {
        localStringBuilder.append(".");
        localStringBuilder.append(str.substring(i + 1));
      }
    }
    return localStringBuilder.toString();
  }

  public void logEvent(String paramString, Object[] paramArrayOfObject)
  {
    if (!this.mIsLoggingEnabled);
    while (true)
    {
      return;
      Log.d("PlayEventLogger", "logEvent: " + paramString + ": " + Arrays.toString(paramArrayOfObject));
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length % 2 != 0))
        throw new IllegalArgumentException("Extras must be in the format <key>, <value>, <key>, <value>...  incorrect: " + Arrays.toString(paramArrayOfObject));
      ClientsAnalytics.LogEvent localLogEvent = ProtoCache.obtainEvent();
      localLogEvent.setEventTime(System.currentTimeMillis());
      localLogEvent.setTag(paramString);
      if (paramArrayOfObject != null)
      {
        int i = 0;
        if (i < paramArrayOfObject.length)
        {
          ClientsAnalytics.LogEventKeyValues localLogEventKeyValues = ProtoCache.obtainKeyValue();
          localLogEventKeyValues.setKey(paramArrayOfObject[i].toString());
          if (paramArrayOfObject[(i + 1)] != null);
          for (String str = paramArrayOfObject[(i + 1)].toString(); ; str = "null")
          {
            localLogEventKeyValues.setValue(str);
            localLogEvent.addValues(localLogEventKeyValues);
            i += 2;
            break;
          }
        }
      }
      sendMessage(obtainMessage(2, localLogEvent));
    }
  }

  public void setUploadAccount(Account paramAccount)
  {
    this.mUploadAccount = paramAccount;
  }

  protected boolean uploadLog(Account paramAccount, ClientsAnalytics.LogRequest paramLogRequest)
    throws IOException
  {
    boolean bool = false;
    String str = getAuthToken(paramAccount);
    HttpPost localHttpPost = new HttpPost("https://android.clients.google.com/play/log");
    localHttpPost.addHeader("Authorization", "GoogleLogin auth=" + str);
    localHttpPost.setEntity(new ByteArrayEntity(paramLogRequest.toByteArray()));
    HttpResponse localHttpResponse = this.mHttpClient.execute(localHttpPost);
    StatusLine localStatusLine = localHttpResponse.getStatusLine();
    int i = localStatusLine.getStatusCode();
    if (i == 200)
    {
      Log.i("PlayEventLogger", "Successfully uploaded logs.");
      bool = true;
    }
    while (true)
    {
      return bool;
      if ((i == 503) && (localHttpResponse.getFirstHeader("Retry-After") != null))
      {
        try
        {
          long l = Long.valueOf(localHttpResponse.getFirstHeader("Retry-After").getValue()).longValue();
          Log.w("PlayEventLogger", "Server said to retry after " + l + " seconds");
          this.mNextAllowedUploadTime = (l + System.currentTimeMillis());
        }
        catch (NumberFormatException localNumberFormatException)
        {
          Log.e("PlayEventLogger", "Unknown retry value: " + localHttpResponse.getFirstHeader("Retry-After").getValue());
        }
      }
      else if (i == 400)
      {
        Log.w("PlayEventLogger", "Server returned 400... deleting local malformed logs");
        bool = true;
      }
      else if (i == 401)
      {
        Log.w("PlayEventLogger", "Server returned 401... invalidating auth token");
        AccountManager.get(this.mContext).invalidateAuthToken(paramAccount.type, str);
        bool = false;
      }
      else
      {
        Log.e("PlayEventLogger", "Error received from server: " + i + " " + localStatusLine.getReasonPhrase());
      }
    }
  }

  public static enum LogSource
  {
    private final int mProtoValue;

    static
    {
      BOOKS = new LogSource("BOOKS", 2, 2);
      VIDEO = new LogSource("VIDEO", 3, 3);
      MAGAZINES = new LogSource("MAGAZINES", 4, 4);
      LogSource[] arrayOfLogSource = new LogSource[5];
      arrayOfLogSource[0] = MARKET;
      arrayOfLogSource[1] = MUSIC;
      arrayOfLogSource[2] = BOOKS;
      arrayOfLogSource[3] = VIDEO;
      arrayOfLogSource[4] = MAGAZINES;
    }

    private LogSource(int paramInt)
    {
      this.mProtoValue = paramInt;
    }

    public int getProtoValue()
    {
      return this.mProtoValue;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.play.analytics.EventLogger
 * JD-Core Version:    0.6.2
 */