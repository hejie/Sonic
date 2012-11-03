package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyPreferences;

public class FirstRunActivity extends Activity
  implements Html.ImageGetter
{
  public static Intent getIntent(Context paramContext, Intent paramIntent)
  {
    Intent localIntent = new Intent(paramContext, FirstRunActivity.class);
    localIntent.putExtra("continue_intent", paramIntent);
    return localIntent;
  }

  public static boolean requiresFirstRun()
  {
    int i;
    boolean bool;
    if ((Build.VERSION.SDK_INT > 14) || (((Boolean)FinskyPreferences.viewedFirstRunDialog.get()).booleanValue()))
    {
      i = 1;
      if (i == 0)
        break label38;
      bool = false;
    }
    while (true)
    {
      return bool;
      i = 0;
      break;
      label38: if (!FinskyApp.get().getPackageInfoRepository().get(FinskyApp.get().getPackageName()).isUpdatedSystemApp)
      {
        FinskyPreferences.viewedFirstRunDialog.put(Boolean.valueOf(true));
        bool = false;
      }
      else
      {
        bool = true;
      }
    }
  }

  public Drawable getDrawable(String paramString)
  {
    Drawable localDrawable = getResources().getDrawable(2130837596);
    localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
    return localDrawable;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968694);
    setResult(0);
    View localView = findViewById(2131231037);
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)getResources().getDrawable(2130837523);
    localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    localView.setBackgroundDrawable(localBitmapDrawable);
    ((TextView)findViewById(2131231038)).setText(Html.fromHtml(getResources().getString(2131165407), this, null));
    findViewById(2131231039).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse((String)G.playLearnMoreUrl.get()));
        FirstRunActivity.this.startActivity(localIntent);
      }
    });
    findViewById(2131230798).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FinskyPreferences.viewedFirstRunDialog.put(Boolean.valueOf(true));
        FirstRunActivity.this.setResult(-1);
        Intent localIntent = (Intent)FirstRunActivity.this.getIntent().getParcelableExtra("continue_intent");
        if (localIntent == null)
          localIntent = FirstRunActivity.this.getPackageManager().getLaunchIntentForPackage(FirstRunActivity.this.getPackageName());
        localIntent.addFlags(67108864);
        localIntent.addFlags(33554432);
        FirstRunActivity.this.startActivity(localIntent);
        FirstRunActivity.this.finish();
      }
    });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.FirstRunActivity
 * JD-Core Version:    0.6.2
 */