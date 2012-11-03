package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.previews.StatusListener;
import com.google.android.finsky.utils.Lists;
import java.util.Collection;

public class PlaylistControlButtons extends TextView
  implements View.OnClickListener
{
  private final PreviewController mConnection = new PreviewController(this.mStatusListener);
  private Collection<Document> mDocs = Lists.newArrayList();
  private boolean mIsPlaying = false;
  private final StatusListener mStatusListener = new StatusListener()
  {
    public void queueChanged(int paramAnonymousInt)
    {
      PlaylistControlButtons localPlaylistControlButtons = PlaylistControlButtons.this;
      if (paramAnonymousInt > 0);
      for (boolean bool = true; ; bool = false)
      {
        localPlaylistControlButtons.setIsPlaying(bool);
        return;
      }
    }
  };

  public PlaylistControlButtons(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public PlaylistControlButtons(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public PlaylistControlButtons(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void setIsPlaying(boolean paramBoolean)
  {
    this.mIsPlaying = paramBoolean;
    updateState();
  }

  private void updateState()
  {
    if (!this.mIsPlaying)
      setText(2131165458);
    for (int i = 2130837549; ; i = 2130837550)
    {
      setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
      return;
      setText(2131165459);
    }
  }

  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (this.mConnection.getCurrentQueueSize() > 0);
    for (boolean bool = true; ; bool = false)
    {
      setIsPlaying(bool);
      return;
    }
  }

  public void onClick(View paramView)
  {
    if (!this.mIsPlaying)
    {
      this.mConnection.play(this.mDocs);
      setIsPlaying(true);
    }
    while (true)
    {
      return;
      this.mConnection.skip();
    }
  }

  protected void onDetachedFromWindow()
  {
    this.mConnection.unbind();
    super.onDetachedFromWindow();
  }

  public void setDocuments(Collection<Document> paramCollection)
  {
    this.mDocs = paramCollection;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.PlaylistControlButtons
 * JD-Core Version:    0.6.2
 */