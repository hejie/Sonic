package com.google.android.finsky.exploreactivity;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.FinskyLog;
import com.jme3.app.AndroidHarness;
import com.jme3.app.Application;
import com.jme3.input.event.TouchEvent;
import com.jme3.system.JmeSystem;
import com.jme3.system.android.AndroidConfigChooser.ConfigType;
import com.jme3.system.android.JmeAndroidSystem;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExploreActivity extends AndroidHarness
{
  private MusicPreviewManager mMusicPreviewManager;
  private NodeController mNodeController;
  private RelativeLayout mParentLayout;

  public ExploreActivity()
  {
    this.eglConfigType = AndroidConfigChooser.ConfigType.BEST;
    this.eglConfigVerboseLogging = false;
    this.screenOrientation = 0;
    this.mouseEventsInvertX = true;
    this.mouseEventsInvertY = true;
  }

  public static Intent createIntent(Context paramContext, Document paramDocument)
  {
    Intent localIntent = new Intent(paramContext, ExploreActivity.class);
    localIntent.setFlags(537001984);
    localIntent.putExtra("ExploreActivity.doc", paramDocument);
    return localIntent;
  }

  public void handleError(String paramString, Throwable paramThrowable)
  {
    try
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Log.getStackTraceString(paramThrowable);
      FinskyLog.e("Exception in ExploreActivity, exiting. Exception:\n%s", arrayOfObject);
      if ((paramThrowable instanceof OutOfMemoryError))
        this.mNodeController.dumpControllerState();
      return;
    }
    finally
    {
      this.app.stop();
      finish();
    }
  }

  public void layoutDisplay()
  {
    this.mParentLayout.addView(this.view);
    setContentView(this.mParentLayout);
  }

  public void onCreate(Bundle paramBundle)
  {
    FinskyApp localFinskyApp = FinskyApp.get();
    Document localDocument = null;
    if (getIntent() != null)
      localDocument = (Document)getIntent().getExtras().get("ExploreActivity.doc");
    this.mParentLayout = new RelativeLayout(this);
    DfeApi localDfeApi = localFinskyApp.getDfeApi();
    this.mMusicPreviewManager = new MusicPreviewManager(getBaseContext());
    this.mNodeController = new NodeController(this, localDfeApi, this.mMusicPreviewManager, localFinskyApp.getRequestQueue());
    this.app = new ExploreApplication(this, this.mNodeController, localDocument, this.mParentLayout);
    super.onCreate(paramBundle);
    ((ExploreApplication)this.app).createViews();
    Logger.getLogger("com.jme3").setLevel(Level.WARNING);
  }

  public void onDestroy()
  {
    FinskyLog.d("Destroying explorer", new Object[0]);
    super.onDestroy();
    this.mMusicPreviewManager.destroy();
    JmeSystem.setSoftTextDialogInput(null);
    JmeAndroidSystem.setActivity(null);
  }

  public void onNewIntent(Intent paramIntent)
  {
    Document localDocument = (Document)paramIntent.getExtras().get("ExploreActivity.doc");
    if (localDocument != null)
    {
      setIntent(paramIntent);
      ((ExploreApplication)this.app).setSeedDocument(localDocument);
    }
  }

  public void onPause()
  {
    FinskyLog.d("Pausing explorer", new Object[0]);
    ((ExploreApplication)this.app).onPause();
    super.onPause();
  }

  public void onStop()
  {
    FinskyLog.d("Stopping explorer", new Object[0]);
    ((ExploreApplication)this.app).onStop();
    super.onStop();
    this.mMusicPreviewManager.clear();
  }

  public void onTouch(String paramString, TouchEvent paramTouchEvent, float paramFloat)
  {
    if (paramString.equals("TouchEscape"))
      switch (2.$SwitchMap$com$jme3$input$event$TouchEvent$Type[paramTouchEvent.getType().ordinal()])
      {
      default:
        super.onTouch(paramString, paramTouchEvent, paramFloat);
      case 1:
      }
    while (true)
    {
      return;
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (ExploreActivity.this.app != null)
            ExploreActivity.this.app.stop(true);
          ExploreActivity.this.finish();
        }
      });
    }
  }

  public void runOnGlThread(Runnable paramRunnable)
  {
    this.view.queueEvent(paramRunnable);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.exploreactivity.ExploreActivity
 * JD-Core Version:    0.6.2
 */