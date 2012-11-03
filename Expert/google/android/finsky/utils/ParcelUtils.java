package com.google.android.finsky.utils;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParcelUtils
{
  // ERROR //
  public static <T extends Parcelable> T readFromDisk(java.io.File paramFile)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aconst_null
    //   4: astore_1
    //   5: new 16	java/io/DataInputStream
    //   8: dup
    //   9: new 18	java/io/FileInputStream
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 21	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   17: invokespecial 24	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   20: invokestatic 28	com/google/android/finsky/utils/ParcelUtils:readObject	(Ljava/io/DataInputStream;)Landroid/os/Parcelable;
    //   23: astore 9
    //   25: aload 9
    //   27: ifnull +6 -> 33
    //   30: aload 9
    //   32: astore_1
    //   33: ldc 2
    //   35: monitorexit
    //   36: aload_1
    //   37: areturn
    //   38: astore 6
    //   40: aload_0
    //   41: invokevirtual 34	java/io/File:delete	()Z
    //   44: pop
    //   45: iconst_2
    //   46: anewarray 4	java/lang/Object
    //   49: astore 8
    //   51: aload 8
    //   53: iconst_0
    //   54: aload_0
    //   55: invokevirtual 38	java/io/File:getName	()Ljava/lang/String;
    //   58: aastore
    //   59: aload 8
    //   61: iconst_1
    //   62: aload 6
    //   64: invokevirtual 41	com/google/android/finsky/utils/ParcelUtils$CacheVersionException:getMessage	()Ljava/lang/String;
    //   67: aastore
    //   68: ldc 43
    //   70: aload 8
    //   72: invokestatic 49	com/google/android/finsky/utils/FinskyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   75: goto -42 -> 33
    //   78: astore 5
    //   80: ldc 2
    //   82: monitorexit
    //   83: aload 5
    //   85: athrow
    //   86: astore_2
    //   87: aload_0
    //   88: invokevirtual 34	java/io/File:delete	()Z
    //   91: pop
    //   92: iconst_2
    //   93: anewarray 4	java/lang/Object
    //   96: astore 4
    //   98: aload 4
    //   100: iconst_0
    //   101: aload_0
    //   102: invokevirtual 38	java/io/File:getName	()Ljava/lang/String;
    //   105: aastore
    //   106: aload 4
    //   108: iconst_1
    //   109: aload_2
    //   110: invokevirtual 50	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   113: aastore
    //   114: ldc 52
    //   116: aload 4
    //   118: invokestatic 55	com/google/android/finsky/utils/FinskyLog:wtf	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   121: aload_2
    //   122: invokevirtual 58	java/lang/Throwable:printStackTrace	()V
    //   125: goto -92 -> 33
    //
    // Exception table:
    //   from	to	target	type
    //   5	25	38	com/google/android/finsky/utils/ParcelUtils$CacheVersionException
    //   5	25	78	finally
    //   40	75	78	finally
    //   87	125	78	finally
    //   5	25	86	java/lang/Throwable
  }

  private static <T extends Parcelable> T readObject(DataInputStream paramDataInputStream)
    throws IOException
  {
    Utils.ensureNotOnMainThread();
    ClassLoader localClassLoader = ParcelUtils.class.getClassLoader();
    byte[] arrayOfByte = new byte[paramDataInputStream.readInt()];
    paramDataInputStream.read(arrayOfByte);
    Parcel localParcel = Parcel.obtain();
    localParcel.setDataPosition(0);
    localParcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
    localParcel.setDataPosition(0);
    try
    {
      Parcelable localParcelable = localParcel.readParcelable(localClassLoader);
      return localParcelable;
    }
    finally
    {
      localParcel.recycle();
    }
  }

  private static void writeObject(DataOutputStream paramDataOutputStream, Parcelable paramParcelable)
    throws IOException
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.setDataPosition(0);
      localParcel.writeParcelable(paramParcelable, 0);
      byte[] arrayOfByte = localParcel.marshall();
      paramDataOutputStream.writeInt(arrayOfByte.length);
      paramDataOutputStream.write(arrayOfByte);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
  }

  // ERROR //
  public static void writeToDisk(java.io.File paramFile, Parcelable paramParcelable)
    throws IOException
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 65	com/google/android/finsky/utils/Utils:ensureNotOnMainThread	()V
    //   6: aload_0
    //   7: invokevirtual 125	java/io/File:getParentFile	()Ljava/io/File;
    //   10: invokevirtual 128	java/io/File:mkdirs	()Z
    //   13: pop
    //   14: aconst_null
    //   15: astore 4
    //   17: new 112	java/io/DataOutputStream
    //   20: dup
    //   21: new 130	java/io/FileOutputStream
    //   24: dup
    //   25: aload_0
    //   26: invokespecial 131	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   29: invokespecial 134	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   32: astore 5
    //   34: aload 5
    //   36: aload_1
    //   37: invokestatic 136	com/google/android/finsky/utils/ParcelUtils:writeObject	(Ljava/io/DataOutputStream;Landroid/os/Parcelable;)V
    //   40: aload 5
    //   42: ifnull +8 -> 50
    //   45: aload 5
    //   47: invokevirtual 139	java/io/DataOutputStream:close	()V
    //   50: ldc 2
    //   52: monitorexit
    //   53: return
    //   54: astore 6
    //   56: aload 4
    //   58: ifnull +8 -> 66
    //   61: aload 4
    //   63: invokevirtual 139	java/io/DataOutputStream:close	()V
    //   66: aload 6
    //   68: athrow
    //   69: astore_2
    //   70: ldc 2
    //   72: monitorexit
    //   73: aload_2
    //   74: athrow
    //   75: astore 8
    //   77: goto -27 -> 50
    //   80: astore 7
    //   82: goto -16 -> 66
    //   85: astore 6
    //   87: aload 5
    //   89: astore 4
    //   91: goto -35 -> 56
    //
    // Exception table:
    //   from	to	target	type
    //   17	34	54	finally
    //   3	14	69	finally
    //   45	50	69	finally
    //   61	66	69	finally
    //   66	69	69	finally
    //   45	50	75	java/io/IOException
    //   61	66	80	java/io/IOException
    //   34	40	85	finally
  }

  public static final class CacheVersionException extends RuntimeException
  {
    private final long mActualVersion;
    private final Class<? extends Parcelable> mClass;
    private final long mExpectedVersion;

    public CacheVersionException(Class<? extends Parcelable> paramClass, long paramLong1, long paramLong2)
    {
      this.mClass = paramClass;
      this.mExpectedVersion = paramLong1;
      this.mActualVersion = paramLong2;
    }

    public String getMessage()
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = this.mClass.getSimpleName();
      arrayOfObject[1] = Long.valueOf(this.mExpectedVersion);
      arrayOfObject[2] = Long.valueOf(this.mActualVersion);
      return String.format("Failed parsing %s (wanted %d, got %d)", arrayOfObject);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.ParcelUtils
 * JD-Core Version:    0.6.2
 */