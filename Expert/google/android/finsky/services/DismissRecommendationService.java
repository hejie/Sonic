package com.google.android.finsky.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import com.google.android.finsky.api.model.Document;

public class DismissRecommendationService extends IntentService
{
  public DismissRecommendationService()
  {
    super(DismissRecommendationService.class.getSimpleName());
  }

  public static PendingIntent getDismissPendingIntent(Context paramContext, int paramInt1, Document paramDocument, int paramInt2, int paramInt3)
  {
    String str = paramDocument.getReasonUniqueId();
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("content");
    localBuilder.authority("dismiss");
    localBuilder.appendPath(String.valueOf(paramInt1));
    localBuilder.appendPath(String.valueOf(paramInt3));
    localBuilder.appendPath(str);
    Intent localIntent = new Intent(paramContext, DismissRecommendationService.class);
    localIntent.setData(localBuilder.build());
    localIntent.putExtra("appWidgetId", paramInt1);
    localIntent.putExtra("documentId", paramDocument.getDocId());
    localIntent.putExtra("contentUniqueId", str);
    localIntent.putExtra("backendId", paramInt2);
    return PendingIntent.getService(paramContext, 0, localIntent, 0);
  }

  // ERROR //
  protected void onHandleIntent(Intent paramIntent)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc 61
    //   3: iconst_0
    //   4: invokevirtual 95	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   7: istore_2
    //   8: aload_1
    //   9: ldc 67
    //   11: invokevirtual 99	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   14: astore_3
    //   15: aload_1
    //   16: ldc 75
    //   18: invokevirtual 99	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   21: astore 4
    //   23: aload_1
    //   24: ldc 77
    //   26: iconst_3
    //   27: invokevirtual 95	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   30: istore 5
    //   32: invokestatic 105	com/google/android/finsky/FinskyApp:get	()Lcom/google/android/finsky/FinskyApp;
    //   35: invokevirtual 109	com/google/android/finsky/FinskyApp:getLibraries	()Lcom/google/android/finsky/library/Libraries;
    //   38: astore 6
    //   40: invokestatic 105	com/google/android/finsky/FinskyApp:get	()Lcom/google/android/finsky/FinskyApp;
    //   43: invokevirtual 113	com/google/android/finsky/FinskyApp:getDfeApi	()Lcom/google/android/finsky/api/DfeApi;
    //   46: astore 7
    //   48: aload 7
    //   50: ifnonnull +17 -> 67
    //   53: aload_0
    //   54: ldc 115
    //   56: iconst_1
    //   57: newarray int
    //   59: dup
    //   60: iconst_0
    //   61: iload_2
    //   62: iastore
    //   63: invokestatic 121	com/google/android/finsky/widget/BaseWidgetProvider:update	(Landroid/content/Context;Ljava/lang/Class;[I)V
    //   66: return
    //   67: aload_0
    //   68: aload 7
    //   70: iload 5
    //   72: aload 6
    //   74: invokestatic 127	com/google/android/finsky/widget/recommendation/RecommendationsStore:getRecommendations	(Landroid/content/Context;Lcom/google/android/finsky/api/DfeApi;ILcom/google/android/finsky/library/Library;)Lcom/google/android/finsky/widget/recommendation/RecommendationList;
    //   77: astore 30
    //   79: aload 30
    //   81: astore 12
    //   83: aload 12
    //   85: aload_3
    //   86: invokevirtual 133	com/google/android/finsky/widget/recommendation/RecommendationList:remove	(Ljava/lang/String;)Z
    //   89: pop
    //   90: aload 12
    //   92: aload_0
    //   93: invokevirtual 137	com/google/android/finsky/widget/recommendation/RecommendationList:writeToDisk	(Landroid/content/Context;)V
    //   96: aload_0
    //   97: iconst_1
    //   98: newarray int
    //   100: dup
    //   101: iconst_0
    //   102: iload_2
    //   103: iastore
    //   104: invokestatic 143	com/google/android/finsky/widget/recommendation/RecommendationsViewFactory:notifyDataSetChanged	(Landroid/content/Context;[I)V
    //   107: aload 12
    //   109: astore 16
    //   111: ldc 145
    //   113: iconst_1
    //   114: anewarray 147	java/lang/Object
    //   117: dup
    //   118: iconst_0
    //   119: aload 4
    //   121: aastore
    //   122: invokestatic 153	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   125: invokestatic 159	com/android/volley/toolbox/RequestFuture:newFuture	()Lcom/android/volley/toolbox/RequestFuture;
    //   128: astore 17
    //   130: aload 17
    //   132: aload 7
    //   134: aload 4
    //   136: aload 17
    //   138: aload 17
    //   140: invokeinterface 165 4 0
    //   145: invokevirtual 169	com/android/volley/toolbox/RequestFuture:setRequest	(Lcom/android/volley/Request;)V
    //   148: aload 17
    //   150: getstatic 175	com/google/android/finsky/config/G:recommendationsFetchTimeoutMs	Lcom/google/android/finsky/config/GservicesValue;
    //   153: invokevirtual 180	com/google/android/finsky/config/GservicesValue:get	()Ljava/lang/Object;
    //   156: checkcast 182	java/lang/Long
    //   159: invokevirtual 186	java/lang/Long:longValue	()J
    //   162: getstatic 192	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   165: invokevirtual 195	com/android/volley/toolbox/RequestFuture:get	(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
    //   168: pop
    //   169: aload 16
    //   171: ifnull +31 -> 202
    //   174: aload 16
    //   176: invokevirtual 199	com/google/android/finsky/widget/recommendation/RecommendationList:needsBackfill	()Z
    //   179: ifeq +23 -> 202
    //   182: aload 7
    //   184: iload 5
    //   186: invokeinterface 203 2 0
    //   191: aload 7
    //   193: aload_0
    //   194: aload 16
    //   196: aload 6
    //   198: iload_2
    //   199: invokestatic 207	com/google/android/finsky/widget/recommendation/RecommendationsStore:performBackFill	(Lcom/google/android/finsky/api/DfeApi;Landroid/content/Context;Lcom/google/android/finsky/widget/recommendation/RecommendationList;Lcom/google/android/finsky/library/Library;I)V
    //   202: ldc 209
    //   204: iconst_1
    //   205: anewarray 147	java/lang/Object
    //   208: dup
    //   209: iconst_0
    //   210: aload 4
    //   212: aastore
    //   213: invokestatic 153	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   216: goto -150 -> 66
    //   219: astore 20
    //   221: ldc 211
    //   223: iconst_0
    //   224: anewarray 147	java/lang/Object
    //   227: invokestatic 214	com/google/android/finsky/utils/FinskyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   230: goto -164 -> 66
    //   233: astore 26
    //   235: aconst_null
    //   236: astore 12
    //   238: aload 26
    //   240: astore 27
    //   242: ldc 216
    //   244: iconst_1
    //   245: anewarray 147	java/lang/Object
    //   248: dup
    //   249: iconst_0
    //   250: aload 27
    //   252: aastore
    //   253: invokestatic 153	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   256: iconst_1
    //   257: anewarray 147	java/lang/Object
    //   260: astore 28
    //   262: aload 28
    //   264: iconst_0
    //   265: iload 5
    //   267: invokestatic 221	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   270: aastore
    //   271: ldc 223
    //   273: aload 28
    //   275: invokestatic 153	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   278: aload 7
    //   280: iload 5
    //   282: invokeinterface 203 2 0
    //   287: aload_0
    //   288: iload 5
    //   290: invokestatic 227	com/google/android/finsky/widget/recommendation/RecommendationsStore:getCacheFile	(Landroid/content/Context;I)Ljava/io/File;
    //   293: invokevirtual 232	java/io/File:delete	()Z
    //   296: pop
    //   297: aload 12
    //   299: astore 16
    //   301: goto -190 -> 111
    //   304: astore 22
    //   306: aconst_null
    //   307: astore 12
    //   309: aload 22
    //   311: astore 23
    //   313: ldc 234
    //   315: iconst_1
    //   316: anewarray 147	java/lang/Object
    //   319: dup
    //   320: iconst_0
    //   321: aload 23
    //   323: aastore
    //   324: invokestatic 153	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   327: iconst_1
    //   328: anewarray 147	java/lang/Object
    //   331: astore 24
    //   333: aload 24
    //   335: iconst_0
    //   336: iload 5
    //   338: invokestatic 221	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   341: aastore
    //   342: ldc 223
    //   344: aload 24
    //   346: invokestatic 153	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   349: aload 7
    //   351: iload 5
    //   353: invokeinterface 203 2 0
    //   358: aload_0
    //   359: iload 5
    //   361: invokestatic 227	com/google/android/finsky/widget/recommendation/RecommendationsStore:getCacheFile	(Landroid/content/Context;I)Ljava/io/File;
    //   364: invokevirtual 232	java/io/File:delete	()Z
    //   367: pop
    //   368: aload 12
    //   370: astore 16
    //   372: goto -261 -> 111
    //   375: astore 11
    //   377: aconst_null
    //   378: astore 12
    //   380: aload 11
    //   382: astore 13
    //   384: ldc 236
    //   386: iconst_1
    //   387: anewarray 147	java/lang/Object
    //   390: dup
    //   391: iconst_0
    //   392: aload 13
    //   394: aastore
    //   395: invokestatic 153	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   398: iconst_1
    //   399: anewarray 147	java/lang/Object
    //   402: astore 14
    //   404: aload 14
    //   406: iconst_0
    //   407: iload 5
    //   409: invokestatic 221	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   412: aastore
    //   413: ldc 223
    //   415: aload 14
    //   417: invokestatic 153	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   420: aload 7
    //   422: iload 5
    //   424: invokeinterface 203 2 0
    //   429: aload_0
    //   430: iload 5
    //   432: invokestatic 227	com/google/android/finsky/widget/recommendation/RecommendationsStore:getCacheFile	(Landroid/content/Context;I)Ljava/io/File;
    //   435: invokevirtual 232	java/io/File:delete	()Z
    //   438: pop
    //   439: aload 12
    //   441: astore 16
    //   443: goto -332 -> 111
    //   446: astore 8
    //   448: iconst_1
    //   449: anewarray 147	java/lang/Object
    //   452: astore 9
    //   454: aload 9
    //   456: iconst_0
    //   457: iload 5
    //   459: invokestatic 221	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   462: aastore
    //   463: ldc 223
    //   465: aload 9
    //   467: invokestatic 153	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   470: aload 7
    //   472: iload 5
    //   474: invokeinterface 203 2 0
    //   479: aload_0
    //   480: iload 5
    //   482: invokestatic 227	com/google/android/finsky/widget/recommendation/RecommendationsStore:getCacheFile	(Landroid/content/Context;I)Ljava/io/File;
    //   485: invokevirtual 232	java/io/File:delete	()Z
    //   488: pop
    //   489: aload 8
    //   491: athrow
    //   492: astore 19
    //   494: ldc 238
    //   496: iconst_1
    //   497: anewarray 147	java/lang/Object
    //   500: dup
    //   501: iconst_0
    //   502: aload 19
    //   504: aastore
    //   505: invokestatic 214	com/google/android/finsky/utils/FinskyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   508: goto -442 -> 66
    //   511: astore 18
    //   513: ldc 240
    //   515: iconst_0
    //   516: anewarray 147	java/lang/Object
    //   519: invokestatic 214	com/google/android/finsky/utils/FinskyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   522: goto -456 -> 66
    //   525: astore 13
    //   527: goto -143 -> 384
    //   530: astore 23
    //   532: goto -219 -> 313
    //   535: astore 27
    //   537: goto -295 -> 242
    //
    // Exception table:
    //   from	to	target	type
    //   148	216	219	java/lang/InterruptedException
    //   67	79	233	java/util/concurrent/ExecutionException
    //   67	79	304	java/lang/InterruptedException
    //   67	79	375	java/util/concurrent/TimeoutException
    //   67	79	446	finally
    //   83	107	446	finally
    //   242	256	446	finally
    //   313	327	446	finally
    //   384	398	446	finally
    //   148	216	492	java/util/concurrent/ExecutionException
    //   148	216	511	java/util/concurrent/TimeoutException
    //   83	107	525	java/util/concurrent/TimeoutException
    //   83	107	530	java/lang/InterruptedException
    //   83	107	535	java/util/concurrent/ExecutionException
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.DismissRecommendationService
 * JD-Core Version:    0.6.2
 */