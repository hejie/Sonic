package com.google.android.finsky.previews;

import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocDetails.SongDetails;
import java.util.Collection;

public class PreviewController
{
  private static final PreviewPlayer mPlayer = new PreviewPlayer();
  private final StatusListener mStatusListener;

  public PreviewController(StatusListener paramStatusListener)
  {
    mPlayer.initialize();
    this.mStatusListener = paramStatusListener;
    mPlayer.addStatusListener(paramStatusListener);
  }

  public static void reset()
  {
    mPlayer.reset();
  }

  public static void setupOnBackStackChangedListener(NavigationManager paramNavigationManager)
  {
    FragmentManager.OnBackStackChangedListener local1 = new FragmentManager.OnBackStackChangedListener()
    {
      public void onBackStackChanged()
      {
        PreviewController.mPlayer.clear();
        PreviewController.mPlayer.stop();
        PreviewController.mPlayer.reset();
        this.val$navigationManager.removeOnBackStackChangedListener(this);
      }
    };
    paramNavigationManager.removeOnBackStackChangedListener(local1);
    paramNavigationManager.addOnBackStackChangedListener(local1);
  }

  public int getCurrentQueueSize()
  {
    return mPlayer.getCurrentQueueSize();
  }

  public DocDetails.SongDetails getCurrentTrack()
  {
    return mPlayer.getCurrentTrack();
  }

  public void getStatusUpdate(StatusListener paramStatusListener)
  {
    mPlayer.notifyListener(paramStatusListener);
  }

  public void play(Collection<Document> paramCollection)
  {
    mPlayer.play(paramCollection);
  }

  public void skip()
  {
    mPlayer.skip();
  }

  public void togglePlayback(DocDetails.SongDetails paramSongDetails)
  {
    mPlayer.togglePlayback(paramSongDetails);
  }

  public void unbind()
  {
    mPlayer.removeStatusListener(this.mStatusListener);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.previews.PreviewController
 * JD-Core Version:    0.6.2
 */