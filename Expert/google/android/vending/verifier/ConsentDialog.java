package com.google.android.vending.verifier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;

public class ConsentDialog extends Activity
  implements ButtonBar.ClickListener
{
  private static String KEY_VERIFICATION_ID = "verification_id";
  private ButtonBar mButtonBar;
  private int mId;
  private boolean mResponseSent = false;

  private void clearFinishOnTouchOutside()
  {
    setFinishOnTouchOutside(false);
  }

  public static void show(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, ConsentDialog.class);
    localIntent.setFlags(1342177280);
    localIntent.putExtra(KEY_VERIFICATION_ID, paramInt);
    paramContext.startActivity(localIntent);
  }

  public void onBackPressed()
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968773);
    clearFinishOnTouchOutside();
    this.mId = getIntent().getIntExtra(KEY_VERIFICATION_ID, -1);
    if (!PackageVerificationService.registerDialog(this.mId, this))
      finish();
    while (true)
    {
      return;
      this.mButtonBar = ((ButtonBar)findViewById(2131230825));
      this.mButtonBar.setClickListener(this);
      this.mButtonBar.setPositiveButtonTitle(2131165843);
      this.mButtonBar.setNegativeButtonTitle(2131165842);
    }
  }

  protected void onDestroy()
  {
    if ((!this.mResponseSent) && (isFinishing()))
      PackageVerificationService.reportConsentDialog(this.mId, false);
    PackageVerificationService.registerDialog(this.mId, null);
    super.onDestroy();
  }

  public void onNegativeButtonClick()
  {
    this.mResponseSent = true;
    PackageVerificationService.reportConsentDialog(this.mId, false);
    PackageVerificationService.registerDialog(this.mId, null);
    finish();
  }

  public void onPositiveButtonClick()
  {
    this.mResponseSent = true;
    PackageVerificationService.reportConsentDialog(this.mId, true);
    PackageVerificationService.registerDialog(this.mId, null);
    finish();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.verifier.ConsentDialog
 * JD-Core Version:    0.6.2
 */