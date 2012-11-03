package com.google.android.finsky.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ContainerEncryptionParams;
import android.content.pm.IPackageDataObserver.Stub;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageInstallObserver.Stub;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

public class PackageManagerUtils
{
  private static int ENCRYPTED_V0_SIZE_FOOTER = 20;
  private static int ENCRYPTED_V0_SIZE_HEADER;
  private static String TAG = "Finsky";

  static
  {
    ENCRYPTED_V0_SIZE_HEADER = 21;
  }

  public static void callInstallEncrypted(ContentResolver paramContentResolver, PackageManager paramPackageManager, Uri paramUri, IPackageInstallObserver paramIPackageInstallObserver, int paramInt, long paramLong, String paramString1, String paramString2)
  {
    paramPackageManager.installPackageWithVerification(paramUri, paramIPackageInstallObserver, paramInt, "com.android.vending", null, null, (ContainerEncryptionParams)generateEncryptionParams(paramContentResolver, paramUri, paramLong, paramString1, paramString2));
  }

  public static void freeStorageAndNotify(Context paramContext, long paramLong, FreeSpaceListener paramFreeSpaceListener)
  {
    paramContext.getPackageManager().freeStorageAndNotify(paramLong, new IPackageDataObserver.Stub()
    {
      public void onRemoveCompleted(String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        PackageManagerUtils.this.onComplete(paramAnonymousBoolean);
      }
    });
  }

  // ERROR //
  private static Object generateEncryptionParams(ContentResolver paramContentResolver, Uri paramUri, long paramLong, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: bipush 16
    //   2: newarray byte
    //   4: astore 6
    //   6: iconst_4
    //   7: newarray byte
    //   9: astore 7
    //   11: bipush 20
    //   13: newarray byte
    //   15: astore 8
    //   17: lload_2
    //   18: iconst_1
    //   19: getstatic 17	com/google/android/finsky/utils/PackageManagerUtils:ENCRYPTED_V0_SIZE_HEADER	I
    //   22: iadd
    //   23: getstatic 19	com/google/android/finsky/utils/PackageManagerUtils:ENCRYPTED_V0_SIZE_FOOTER	I
    //   26: iadd
    //   27: i2l
    //   28: lcmp
    //   29: ifge +41 -> 70
    //   32: getstatic 15	com/google/android/finsky/utils/PackageManagerUtils:TAG	Ljava/lang/String;
    //   35: new 64	java/lang/StringBuilder
    //   38: dup
    //   39: ldc 66
    //   41: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   44: lload_2
    //   45: invokevirtual 73	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   48: ldc 75
    //   50: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_1
    //   54: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   63: pop
    //   64: aconst_null
    //   65: astore 11
    //   67: goto +569 -> 636
    //   70: aload_0
    //   71: aload_1
    //   72: invokevirtual 97	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   75: astore 14
    //   77: new 99	java/io/DataInputStream
    //   80: dup
    //   81: new 101	java/io/BufferedInputStream
    //   84: dup
    //   85: aload 14
    //   87: invokespecial 104	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   90: invokespecial 105	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   93: astore 15
    //   95: aload 15
    //   97: invokevirtual 109	java/io/DataInputStream:read	()I
    //   100: istore 16
    //   102: iload 16
    //   104: ifeq +42 -> 146
    //   107: getstatic 15	com/google/android/finsky/utils/PackageManagerUtils:TAG	Ljava/lang/String;
    //   110: new 64	java/lang/StringBuilder
    //   113: dup
    //   114: ldc 111
    //   116: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   119: iload 16
    //   121: invokevirtual 114	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   124: ldc 116
    //   126: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_1
    //   130: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   133: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   139: pop
    //   140: aconst_null
    //   141: astore 11
    //   143: goto +493 -> 636
    //   146: aload 15
    //   148: aload 7
    //   150: invokevirtual 120	java/io/DataInputStream:readFully	([B)V
    //   153: aload 15
    //   155: aload 6
    //   157: invokevirtual 120	java/io/DataInputStream:readFully	([B)V
    //   160: lload_2
    //   161: getstatic 17	com/google/android/finsky/utils/PackageManagerUtils:ENCRYPTED_V0_SIZE_HEADER	I
    //   164: getstatic 19	com/google/android/finsky/utils/PackageManagerUtils:ENCRYPTED_V0_SIZE_FOOTER	I
    //   167: iadd
    //   168: i2l
    //   169: lsub
    //   170: lstore 17
    //   172: lload 17
    //   174: lconst_0
    //   175: lcmp
    //   176: ifgt +169 -> 345
    //   179: aload 15
    //   181: aload 8
    //   183: invokevirtual 120	java/io/DataInputStream:readFully	([B)V
    //   186: aload 15
    //   188: invokevirtual 123	java/io/DataInputStream:close	()V
    //   191: aload 4
    //   193: iconst_0
    //   194: invokestatic 129	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   197: astore 21
    //   199: aload 5
    //   201: iconst_0
    //   202: invokestatic 129	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   205: astore 22
    //   207: ldc 131
    //   209: invokestatic 137	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   212: astore 25
    //   214: aload 21
    //   216: arraylength
    //   217: istore 26
    //   219: aload 25
    //   221: sipush 255
    //   224: iload 26
    //   226: bipush 24
    //   228: ishr
    //   229: iand
    //   230: i2b
    //   231: invokevirtual 141	java/security/MessageDigest:update	(B)V
    //   234: aload 25
    //   236: sipush 255
    //   239: iload 26
    //   241: bipush 16
    //   243: ishr
    //   244: iand
    //   245: i2b
    //   246: invokevirtual 141	java/security/MessageDigest:update	(B)V
    //   249: aload 25
    //   251: sipush 255
    //   254: iload 26
    //   256: bipush 8
    //   258: ishr
    //   259: iand
    //   260: i2b
    //   261: invokevirtual 141	java/security/MessageDigest:update	(B)V
    //   264: aload 25
    //   266: sipush 255
    //   269: iload 26
    //   271: iconst_0
    //   272: ishr
    //   273: iand
    //   274: i2b
    //   275: invokevirtual 141	java/security/MessageDigest:update	(B)V
    //   278: aload 25
    //   280: aload 21
    //   282: invokevirtual 143	java/security/MessageDigest:update	([B)V
    //   285: aload 25
    //   287: aload 22
    //   289: invokevirtual 143	java/security/MessageDigest:update	([B)V
    //   292: aload 25
    //   294: invokevirtual 147	java/security/MessageDigest:digest	()[B
    //   297: astore 27
    //   299: iconst_1
    //   300: istore 28
    //   302: iconst_0
    //   303: istore 29
    //   305: iload 29
    //   307: iconst_3
    //   308: if_icmple +138 -> 446
    //   311: iload 28
    //   313: ifne +208 -> 521
    //   316: getstatic 15	com/google/android/finsky/utils/PackageManagerUtils:TAG	Ljava/lang/String;
    //   319: new 64	java/lang/StringBuilder
    //   322: dup
    //   323: ldc 149
    //   325: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   328: aload_1
    //   329: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   332: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   335: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   338: pop
    //   339: aconst_null
    //   340: astore 11
    //   342: goto +294 -> 636
    //   345: aload 15
    //   347: lload 17
    //   349: invokevirtual 153	java/io/DataInputStream:skip	(J)J
    //   352: lstore 19
    //   354: lload 17
    //   356: lload 19
    //   358: lsub
    //   359: lstore 17
    //   361: goto -189 -> 172
    //   364: astore 12
    //   366: getstatic 15	com/google/android/finsky/utils/PackageManagerUtils:TAG	Ljava/lang/String;
    //   369: new 64	java/lang/StringBuilder
    //   372: dup
    //   373: ldc 155
    //   375: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   378: aload 12
    //   380: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   383: ldc 157
    //   385: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   388: aload_1
    //   389: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   392: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   395: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   398: pop
    //   399: aconst_null
    //   400: astore 11
    //   402: goto +234 -> 636
    //   405: astore 9
    //   407: getstatic 15	com/google/android/finsky/utils/PackageManagerUtils:TAG	Ljava/lang/String;
    //   410: new 64	java/lang/StringBuilder
    //   413: dup
    //   414: ldc 155
    //   416: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   419: aload 9
    //   421: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   424: ldc 159
    //   426: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   429: aload_1
    //   430: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   433: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   436: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   439: pop
    //   440: aconst_null
    //   441: astore 11
    //   443: goto +193 -> 636
    //   446: aload 27
    //   448: iload 29
    //   450: baload
    //   451: istore 30
    //   453: aload 7
    //   455: iload 29
    //   457: baload
    //   458: istore 31
    //   460: iload 30
    //   462: iload 31
    //   464: if_icmpne +19 -> 483
    //   467: iconst_1
    //   468: istore 32
    //   470: iload 28
    //   472: iload 32
    //   474: iand
    //   475: istore 28
    //   477: iinc 29 1
    //   480: goto -175 -> 305
    //   483: iconst_0
    //   484: istore 32
    //   486: goto -16 -> 470
    //   489: astore 23
    //   491: getstatic 15	com/google/android/finsky/utils/PackageManagerUtils:TAG	Ljava/lang/String;
    //   494: new 64	java/lang/StringBuilder
    //   497: dup
    //   498: ldc 161
    //   500: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   503: aload 23
    //   505: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   508: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   511: invokestatic 164	android/util/Log:wtf	(Ljava/lang/String;Ljava/lang/String;)I
    //   514: pop
    //   515: aconst_null
    //   516: astore 11
    //   518: goto +118 -> 636
    //   521: new 166	javax/crypto/spec/IvParameterSpec
    //   524: dup
    //   525: aload 6
    //   527: invokespecial 168	javax/crypto/spec/IvParameterSpec:<init>	([B)V
    //   530: astore 33
    //   532: new 170	javax/crypto/spec/SecretKeySpec
    //   535: dup
    //   536: aload 21
    //   538: ldc 172
    //   540: invokespecial 175	javax/crypto/spec/SecretKeySpec:<init>	([BLjava/lang/String;)V
    //   543: astore 34
    //   545: new 170	javax/crypto/spec/SecretKeySpec
    //   548: dup
    //   549: aload 22
    //   551: ldc 177
    //   553: invokespecial 175	javax/crypto/spec/SecretKeySpec:<init>	([BLjava/lang/String;)V
    //   556: astore 35
    //   558: getstatic 17	com/google/android/finsky/utils/PackageManagerUtils:ENCRYPTED_V0_SIZE_HEADER	I
    //   561: i2l
    //   562: lstore 36
    //   564: lload_2
    //   565: getstatic 19	com/google/android/finsky/utils/PackageManagerUtils:ENCRYPTED_V0_SIZE_FOOTER	I
    //   568: i2l
    //   569: lsub
    //   570: lstore 38
    //   572: new 32	android/content/pm/ContainerEncryptionParams
    //   575: dup
    //   576: ldc 179
    //   578: aload 33
    //   580: aload 34
    //   582: ldc 177
    //   584: aconst_null
    //   585: aload 35
    //   587: aload 8
    //   589: lconst_0
    //   590: lload 36
    //   592: lload 38
    //   594: invokespecial 182	android/content/pm/ContainerEncryptionParams:<init>	(Ljava/lang/String;Ljava/security/spec/AlgorithmParameterSpec;Ljavax/crypto/SecretKey;Ljava/lang/String;Ljava/security/spec/AlgorithmParameterSpec;Ljavax/crypto/SecretKey;[BJJJ)V
    //   597: astore 11
    //   599: goto +37 -> 636
    //   602: astore 40
    //   604: getstatic 15	com/google/android/finsky/utils/PackageManagerUtils:TAG	Ljava/lang/String;
    //   607: new 64	java/lang/StringBuilder
    //   610: dup
    //   611: ldc 155
    //   613: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   616: aload 40
    //   618: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   621: ldc 184
    //   623: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   626: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   629: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   632: pop
    //   633: aconst_null
    //   634: astore 11
    //   636: aload 11
    //   638: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   17	191	364	java/io/FileNotFoundException
    //   345	354	364	java/io/FileNotFoundException
    //   17	191	405	java/io/IOException
    //   345	354	405	java/io/IOException
    //   207	339	489	java/security/NoSuchAlgorithmException
    //   446	460	489	java/security/NoSuchAlgorithmException
    //   572	599	602	java/security/InvalidAlgorithmParameterException
  }

  public static int installExistingPackage(Context paramContext, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return paramContext.getPackageManager().installExistingPackage(paramString);
  }

  public static void installPackage(Context paramContext, Uri paramUri, PackageInstallObserver paramPackageInstallObserver, int paramInt, long paramLong, String paramString1, String paramString2)
  {
    IPackageInstallObserver.Stub local1 = new IPackageInstallObserver.Stub()
    {
      public void packageInstalled(String paramAnonymousString, int paramAnonymousInt)
      {
        if (PackageManagerUtils.this != null)
          PackageManagerUtils.this.packageInstalled(paramAnonymousString, paramAnonymousInt);
      }
    };
    PackageManager localPackageManager = paramContext.getPackageManager();
    if ((paramString1 != null) && (paramString2 != null))
      callInstallEncrypted(paramContext.getContentResolver(), localPackageManager, paramUri, local1, paramInt, paramLong, paramString1, paramString2);
    while (true)
    {
      return;
      localPackageManager.installPackage(paramUri, local1, paramInt, "com.android.vending");
    }
  }

  public static boolean isEncryptionSupported()
  {
    boolean bool = true;
    ClassLoader localClassLoader = PackageManager.class.getClassLoader();
    if (localClassLoader == null)
      localClassLoader = ClassLoader.getSystemClassLoader();
    try
    {
      Class localClass1 = localClassLoader.loadClass("android.content.pm.ManifestDigest");
      Class localClass2 = localClassLoader.loadClass("android.content.pm.ContainerEncryptionParams");
      Class[] arrayOfClass = new Class[7];
      arrayOfClass[0] = Uri.class;
      arrayOfClass[1] = IPackageInstallObserver.class;
      arrayOfClass[2] = Integer.TYPE;
      arrayOfClass[3] = String.class;
      arrayOfClass[4] = Uri.class;
      arrayOfClass[5] = localClass1;
      arrayOfClass[6] = localClass2;
      PackageManager.class.getMethod("installPackageWithVerification", arrayOfClass);
      return bool;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (true)
        bool = false;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
        bool = false;
    }
  }

  public static void uninstallPackage(Context paramContext, String paramString)
  {
    paramContext.getPackageManager().deletePackage(paramString, null, 0);
  }

  public static abstract interface FreeSpaceListener
  {
    public abstract void onComplete(boolean paramBoolean);
  }

  public static abstract interface PackageInstallObserver
  {
    public abstract void packageInstalled(String paramString, int paramInt);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.PackageManagerUtils
 * JD-Core Version:    0.6.2
 */