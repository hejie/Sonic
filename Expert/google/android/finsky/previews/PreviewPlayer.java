package com.google.android.finsky.previews;

import android.app.ActivityManager;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.net.Uri.Builder;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.SkyjamJsonObjectRequest;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.DocDetails.SongDetails;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class PreviewPlayer
{
  private final AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener()
  {
    public void onAudioFocusChange(int paramAnonymousInt)
    {
      PreviewPlayer.access$602(PreviewPlayer.this, paramAnonymousInt);
    }
  };
  private int mAudioFocusState = -1;
  private AudioManager mAudioManager = null;
  private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
    {
      PreviewPlayer.this.mListenerProxy.completed();
      PreviewPlayer.this.mStatusListeners.remove(PreviewPlayer.this.mCurrentTrack);
      if (PreviewPlayer.this.playNextTrack())
        PreviewPlayer.this.mPlayer.setBetweenTrackState();
    }
  };
  private DocDetails.SongDetails mCurrentTrack = null;
  private JsonObjectRequest mCurrentUnrollRequest = null;
  private final Response.Listener<JSONObject> mJsonListener = new Response.Listener()
  {
    public void onResponse(JSONObject paramAnonymousJSONObject)
    {
      try
      {
        PreviewPlayer.access$102(PreviewPlayer.this, null);
        try
        {
          PreviewPlayer.this.mPlayer.setDataSource(PreviewPlayer.this.setModeToStreaming(paramAnonymousJSONObject.getString("url")));
          PreviewPlayer.this.mPlayer.prepareAsync();
          PreviewPlayer.this.mListenerProxy.preparing();
          return;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          while (true)
          {
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Boolean.valueOf(ActivityManager.isUserAMonkey());
            FinskyLog.e("Illegal state while preparing audio. Is Monkey=%b.", arrayOfObject2);
          }
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = localIOException.getMessage();
          FinskyLog.e("IOException: %s", arrayOfObject1);
          PreviewPlayer.this.notifyError();
        }
      }
      catch (JSONException localJSONException)
      {
        while (true)
        {
          FinskyLog.e("Unable to decode JSON response", new Object[0]);
          PreviewPlayer.this.notifyError();
        }
      }
    }
  };
  private final StatusListener mListenerProxy = new StatusListener()
  {
    public void completed()
    {
      Iterator localIterator = PreviewPlayer.this.mStatusListeners.iterator();
      while (localIterator.hasNext())
        ((StatusListener)localIterator.next()).completed();
    }

    public void error()
    {
      Iterator localIterator = PreviewPlayer.this.mStatusListeners.iterator();
      while (localIterator.hasNext())
        ((StatusListener)localIterator.next()).error();
    }

    public void paused()
    {
      Iterator localIterator = PreviewPlayer.this.mStatusListeners.iterator();
      while (localIterator.hasNext())
        ((StatusListener)localIterator.next()).paused();
    }

    public void playing()
    {
      Iterator localIterator = PreviewPlayer.this.mStatusListeners.iterator();
      while (localIterator.hasNext())
        ((StatusListener)localIterator.next()).playing();
    }

    public void prepared()
    {
      Iterator localIterator = PreviewPlayer.this.mStatusListeners.iterator();
      while (localIterator.hasNext())
        ((StatusListener)localIterator.next()).prepared();
    }

    public void preparing()
    {
      Iterator localIterator = PreviewPlayer.this.mStatusListeners.iterator();
      while (localIterator.hasNext())
        ((StatusListener)localIterator.next()).preparing();
    }

    public void queueChanged(int paramAnonymousInt)
    {
      Iterator localIterator = PreviewPlayer.this.mStatusListeners.iterator();
      while (localIterator.hasNext())
        ((StatusListener)localIterator.next()).queueChanged(paramAnonymousInt);
    }

    public void reset()
    {
      Iterator localIterator = PreviewPlayer.this.mStatusListeners.iterator();
      while (localIterator.hasNext())
        ((StatusListener)localIterator.next()).reset();
    }

    public void unrolling()
    {
      Iterator localIterator = PreviewPlayer.this.mStatusListeners.iterator();
      while (localIterator.hasNext())
        ((StatusListener)localIterator.next()).unrolling();
    }
  };
  private final MediaPlayerWrapper mPlayer = new MediaPlayerWrapper(this.mListenerProxy);
  private final MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener()
  {
    public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
    {
      PreviewPlayer.this.mPlayer.start();
    }
  };
  private final Queue<DocDetails.SongDetails> mQueue = new LinkedList();
  private RequestQueue mRequestQueue = null;
  private final List<StatusListener> mStatusListeners = Lists.newArrayList();
  private final Response.ErrorListener mUnrollErrorListener = new Response.ErrorListener()
  {
    public void onErrorResponse(VolleyError paramAnonymousVolleyError)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramAnonymousVolleyError.getCause();
      FinskyLog.e("Unable to load JSON: %s", arrayOfObject);
      PreviewPlayer.access$102(PreviewPlayer.this, null);
      PreviewPlayer.this.notifyError();
    }
  };

  private boolean isCurrentTrack(DocDetails.SongDetails paramSongDetails)
  {
    boolean bool;
    if ((this.mCurrentTrack == null) && (paramSongDetails == null))
      bool = true;
    while (true)
    {
      return bool;
      if ((this.mCurrentTrack == null) || (paramSongDetails == null))
        bool = false;
      else
        bool = paramSongDetails.getPreviewUrl().equals(this.mCurrentTrack.getPreviewUrl());
    }
  }

  private void notifyError()
  {
    this.mListenerProxy.error();
    this.mCurrentTrack = null;
    playNextTrack();
  }

  private void notifyQueueChanged()
  {
    this.mListenerProxy.queueChanged(this.mQueue.size());
  }

  private boolean playNextTrack()
  {
    Utils.ensureOnMainThread();
    updateAudioFocus();
    this.mPlayer.resetWhileStayingAwake();
    if (this.mQueue.isEmpty())
      this.mCurrentTrack = null;
    for (boolean bool = false; ; bool = true)
    {
      return bool;
      this.mListenerProxy.completed();
      if (this.mCurrentUnrollRequest != null)
        this.mCurrentUnrollRequest.cancel();
      this.mCurrentTrack = ((DocDetails.SongDetails)this.mQueue.remove());
      notifyQueueChanged();
      this.mCurrentUnrollRequest = new SkyjamJsonObjectRequest(setModeToStreaming(this.mCurrentTrack.getPreviewUrl()), null, this.mJsonListener, this.mUnrollErrorListener);
      FinskyApp.drain(this.mRequestQueue);
      this.mRequestQueue.add(this.mCurrentUnrollRequest);
      this.mListenerProxy.unrolling();
    }
  }

  private String setModeToStreaming(String paramString)
  {
    ArrayList localArrayList = Lists.newArrayList(URLEncodedUtils.parse(URI.create(paramString), "UTF-8"));
    NameValuePair localNameValuePair = null;
    for (int i = 0; ; i++)
    {
      if (i < localArrayList.size())
      {
        localNameValuePair = (NameValuePair)localArrayList.get(i);
        if (!"mode".equals(localNameValuePair.getName()));
      }
      else
      {
        if (localNameValuePair != null)
          localArrayList.remove(localNameValuePair);
        localArrayList.add(new BasicNameValuePair("mode", "streaming"));
        String str = URLEncodedUtils.format(localArrayList, "UTF-8");
        return Uri.parse(paramString).buildUpon().encodedQuery(str).build().toString();
      }
      localNameValuePair = null;
    }
  }

  private void updateAudioFocus()
  {
    boolean bool = this.mQueue.isEmpty();
    if ((!bool) && (this.mAudioFocusState != 1))
    {
      this.mAudioManager.requestAudioFocus(this.mAudioFocusChangeListener, 3, 1);
      this.mAudioFocusState = 1;
    }
    while (true)
    {
      return;
      if ((bool) && (this.mAudioFocusState != -1))
      {
        this.mAudioManager.abandonAudioFocus(this.mAudioFocusChangeListener);
        this.mAudioFocusState = -1;
      }
    }
  }

  public void addStatusListener(StatusListener paramStatusListener)
  {
    this.mStatusListeners.add(paramStatusListener);
  }

  public void clear()
  {
    Utils.ensureOnMainThread();
    this.mQueue.clear();
  }

  public int getCurrentQueueSize()
  {
    return this.mQueue.size();
  }

  public DocDetails.SongDetails getCurrentTrack()
  {
    return this.mCurrentTrack;
  }

  public void initialize()
  {
    if (this.mRequestQueue == null)
      this.mRequestQueue = FinskyApp.get().getRequestQueue();
    if (this.mAudioManager == null)
      this.mAudioManager = ((AudioManager)FinskyApp.get().getSystemService("audio"));
    this.mPlayer.setOnPreparedListener(this.mPreparedListener);
    this.mPlayer.setOnCompletionListener(this.mCompletionListener);
  }

  public void notifyListener(StatusListener paramStatusListener)
  {
    switch (this.mPlayer.getCurrentState())
    {
    case 6:
    default:
    case 2:
    case 3:
    case 4:
    case 5:
    case 7:
    case 8:
    }
    while (true)
    {
      return;
      paramStatusListener.preparing();
      continue;
      paramStatusListener.prepared();
      continue;
      paramStatusListener.playing();
      continue;
      paramStatusListener.paused();
      continue;
      paramStatusListener.completed();
      continue;
      paramStatusListener.error();
    }
  }

  public void play(DocDetails.SongDetails paramSongDetails)
  {
    Utils.ensureOnMainThread();
    this.mQueue.clear();
    this.mQueue.add(paramSongDetails);
    notifyQueueChanged();
    playNextTrack();
  }

  public void play(Collection<Document> paramCollection)
  {
    Utils.ensureOnMainThread();
    this.mQueue.clear();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      DocDetails.SongDetails localSongDetails = ((Document)localIterator.next()).getSongDetails();
      if (localSongDetails != null)
        this.mQueue.add(localSongDetails);
    }
    notifyQueueChanged();
    playNextTrack();
  }

  public void removeStatusListener(StatusListener paramStatusListener)
  {
    this.mStatusListeners.remove(paramStatusListener);
  }

  public void reset()
  {
    this.mListenerProxy.reset();
    this.mPlayer.reset();
    this.mCurrentTrack = null;
  }

  public void skip()
  {
    playNextTrack();
  }

  public void stop()
  {
    switch (this.mPlayer.getCurrentState())
    {
    default:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    }
    while (true)
    {
      return;
      this.mPlayer.stop();
    }
  }

  public void togglePlayback(DocDetails.SongDetails paramSongDetails)
  {
    if (this.mCurrentUnrollRequest != null)
      this.mCurrentUnrollRequest.cancel();
    int i = this.mPlayer.getCurrentState();
    if (isCurrentTrack(paramSongDetails))
      if (i == 4)
        this.mPlayer.pause();
    while (true)
    {
      return;
      if (i == 5)
      {
        this.mPlayer.start();
      }
      else if (i == 2)
      {
        this.mListenerProxy.completed();
        reset();
        continue;
        play(paramSongDetails);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.previews.PreviewPlayer
 * JD-Core Version:    0.6.2
 */