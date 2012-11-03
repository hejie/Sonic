package com.google.android.vending.verifier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;

public class PackageWarningDialog extends Activity
  implements CompoundButton.OnCheckedChangeListener, ButtonBar.ClickListener
{
  private static String KEY_APP_NAME = "app_name";
  private static String KEY_BLOCKED;
  private static String KEY_MESSAGE = "message";
  private static String KEY_VERIFICATION_ID = "verification_id";
  private ButtonBar mButtonBar;
  private int mId;

  static
  {
    KEY_BLOCKED = "blocked";
  }

  private void clearFinishOnTouchOutside()
  {
    setFinishOnTouchOutside(false);
  }

  public static void show(Context paramContext, int paramInt, boolean paramBoolean, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(paramContext, PackageWarningDialog.class);
    localIntent.setFlags(1342177280);
    localIntent.putExtra(KEY_VERIFICATION_ID, paramInt);
    localIntent.putExtra(KEY_BLOCKED, paramBoolean);
    localIntent.putExtra(KEY_APP_NAME, paramString1);
    localIntent.putExtra(KEY_MESSAGE, paramString2);
    paramContext.startActivity(localIntent);
  }

  public void onBackPressed()
  {
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    this.mButtonBar.setPositiveButtonEnabled(paramBoolean);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968774);
    clearFinishOnTouchOutside();
    Intent localIntent = getIntent();
    this.mId = localIntent.getIntExtra(KEY_VERIFICATION_ID, -1);
    boolean bool = localIntent.getBooleanExtra(KEY_BLOCKED, false);
    String str1 = localIntent.getStringExtra(KEY_APP_NAME);
    String str2 = localIntent.getStringExtra(KEY_MESSAGE);
    if (!PackageVerificationService.registerDialog(this.mId, this))
      finish();
    while (true)
    {
      return;
      ImageView localImageView = (ImageView)findViewById(2131231133);
      TextView localTextView1 = (TextView)findViewById(2131231134);
      TextView localTextView2 = (TextView)findViewById(2131231135);
      TextView localTextView3 = (TextView)findViewById(2131231136);
      TextView localTextView4 = (TextView)findViewById(2131231137);
      CheckBox localCheckBox = (CheckBox)findViewById(2131231138);
      int i;
      label149: int j;
      if (bool)
      {
        i = 2131165835;
        localTextView1.setText(i);
        if (!TextUtils.isEmpty(str2))
          localTextView2.setText(str2);
        if (!bool)
          break label284;
        j = 2131165837;
        label179: localTextView3.setText(j);
        if (!TextUtils.isEmpty(str1))
          localTextView4.setText(getString(2131165838, new Object[] { str1 }));
        if (!bool)
          break label291;
        localImageView.setImageResource(2130837685);
        localCheckBox.setVisibility(8);
      }
      while (true)
      {
        this.mButtonBar = ((ButtonBar)findViewById(2131230825));
        this.mButtonBar.setClickListener(this);
        if (!bool)
          break label307;
        this.mButtonBar.setPositiveButtonVisible(false);
        this.mButtonBar.setNegativeButtonTitle(2131165599);
        break;
        i = 2131165834;
        break label149;
        label284: j = 2131165836;
        break label179;
        label291: localImageView.setImageResource(2130837692);
        localCheckBox.setOnCheckedChangeListener(this);
      }
      label307: this.mButtonBar.setPositiveButtonTitle(2131165446);
      this.mButtonBar.setNegativeButtonTitle(2131165273);
      this.mButtonBar.setPositiveButtonEnabled(false);
    }
  }

  protected void onDestroy()
  {
    PackageVerificationService.registerDialog(this.mId, null);
    super.onDestroy();
  }

  public void onNegativeButtonClick()
  {
    PackageVerificationService.reportUserChoice(this.mId, -1);
    PackageVerificationService.registerDialog(this.mId, null);
    finish();
  }

  public void onPositiveButtonClick()
  {
    PackageVerificationService.reportUserChoice(this.mId, 1);
    PackageVerificationService.registerDialog(this.mId, null);
    finish();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.verifier.PackageWarningDialog
 * JD-Core Version:    0.6.2
 */