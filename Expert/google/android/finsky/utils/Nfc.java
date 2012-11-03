package com.google.android.finsky.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import com.google.android.finsky.activities.DetailsDataBasedFragment;
import com.google.android.finsky.api.model.Document;
import java.io.UnsupportedEncodingException;

public class Nfc
{
  public static NfcHandler getHandler(DetailsDataBasedFragment paramDetailsDataBasedFragment)
  {
    boolean bool = paramDetailsDataBasedFragment.getActivity().getPackageManager().hasSystemFeature("android.hardware.nfc");
    Object localObject;
    if ((bool) && (Build.VERSION.SDK_INT >= 14))
      localObject = new IcsNfcHandler(paramDetailsDataBasedFragment, null);
    while (true)
    {
      return localObject;
      if ((bool) && (Build.VERSION.SDK_INT >= 10))
        localObject = new GingerbreadMr1NfcHandler(paramDetailsDataBasedFragment, null);
      else
        localObject = new NoopNfcHandler(null);
    }
  }

  private static abstract class BaseNfcHandler
    implements Nfc.NfcHandler
  {
    protected final DetailsDataBasedFragment mFragment;
    protected final NfcAdapter mNfcAdapter;

    private BaseNfcHandler(DetailsDataBasedFragment paramDetailsDataBasedFragment)
    {
      this.mFragment = paramDetailsDataBasedFragment;
      this.mNfcAdapter = NfcAdapter.getDefaultAdapter(paramDetailsDataBasedFragment.getActivity());
    }

    protected final NdefMessage createMessage()
    {
      Document localDocument = this.mFragment.getDocument();
      String str;
      if (localDocument != null)
        str = localDocument.getShareUrl();
      while (true)
      {
        try
        {
          byte[] arrayOfByte3 = str.getBytes("UTF-8");
          arrayOfByte1 = arrayOfByte3;
          byte[] arrayOfByte2 = new byte[1 + arrayOfByte1.length];
          System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 1, arrayOfByte1.length);
          localNdefMessage = new NdefMessage(new NdefRecord[] { new NdefRecord(1, new byte[] { 85 }, new byte[0], arrayOfByte2) });
          return localNdefMessage;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          byte[] arrayOfByte1 = str.getBytes();
          continue;
        }
        NdefMessage localNdefMessage = null;
      }
    }
  }

  private static class GingerbreadMr1NfcHandler extends Nfc.BaseNfcHandler
  {
    private boolean mEnabled = false;

    private GingerbreadMr1NfcHandler(DetailsDataBasedFragment paramDetailsDataBasedFragment)
    {
      super(null);
    }

    private void setPushMessage()
    {
      if ((!this.mEnabled) && (this.mFragment.isResumed()))
      {
        NdefMessage localNdefMessage = createMessage();
        if (localNdefMessage != null)
        {
          this.mNfcAdapter.enableForegroundNdefPush(this.mFragment.getActivity(), localNdefMessage);
          this.mEnabled = true;
        }
      }
    }

    public void onDataChanged()
    {
      setPushMessage();
    }

    public void onPause()
    {
      if (this.mEnabled)
      {
        this.mNfcAdapter.disableForegroundNdefPush(this.mFragment.getActivity());
        this.mEnabled = false;
      }
    }

    public void onResume()
    {
      setPushMessage();
    }
  }

  private static class IcsNfcHandler extends Nfc.BaseNfcHandler
    implements NfcAdapter.CreateNdefMessageCallback
  {
    private IcsNfcHandler(DetailsDataBasedFragment paramDetailsDataBasedFragment)
    {
      super(null);
    }

    private void setCallback(NfcAdapter.CreateNdefMessageCallback paramCreateNdefMessageCallback)
    {
      if (!this.mFragment.canChangeFragmentManagerState());
      while (true)
      {
        return;
        if (this.mNfcAdapter != null)
          this.mNfcAdapter.setNdefPushMessageCallback(paramCreateNdefMessageCallback, this.mFragment.getActivity(), new Activity[0]);
      }
    }

    public NdefMessage createNdefMessage(NfcEvent paramNfcEvent)
    {
      return createMessage();
    }

    public void onDataChanged()
    {
      setCallback(this);
    }

    public void onPause()
    {
      setCallback(null);
    }

    public void onResume()
    {
      setCallback(this);
    }
  }

  public static abstract interface NfcHandler
  {
    public abstract void onDataChanged();

    public abstract void onPause();

    public abstract void onResume();
  }

  private static class NoopNfcHandler
    implements Nfc.NfcHandler
  {
    public void onDataChanged()
    {
    }

    public void onPause()
    {
    }

    public void onResume()
    {
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.Nfc
 * JD-Core Version:    0.6.2
 */