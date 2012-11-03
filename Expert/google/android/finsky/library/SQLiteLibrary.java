package com.google.android.finsky.library;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.google.android.finsky.utils.FinskyLog;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SQLiteLibrary
  implements Library
{
  private static final String[] FULL_PROJECTION = { "account", "library_id", "backend", "doc_id", "doc_type", "offer_type", "document_hash", "app_certificate_hash", "app_refund_pre_delivery_endtime_ms", "app_refund_post_delivery_window_ms", "subs_auto_renewing", "subs_initiation_time", "subs_valid_until_time", "subs_trial_until_time", "inapp_purchase_data", "inapp_signature" };
  private Context mContext;
  private SQLiteDatabase mDb;
  private SQLiteStatement mQueryContains;
  private SQLiteStatement mQueryRemove;
  private SQLiteStatement mQueryResetAccountLibraryId;
  private SQLiteStatement mQuerySize;

  public SQLiteLibrary(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private static void bindPartialStatement(SQLiteStatement paramSQLiteStatement, LibraryEntry paramLibraryEntry)
  {
    paramSQLiteStatement.bindString(1, paramLibraryEntry.accountName);
    paramSQLiteStatement.bindString(2, paramLibraryEntry.libraryId);
    paramSQLiteStatement.bindLong(3, paramLibraryEntry.backendId);
    paramSQLiteStatement.bindString(4, paramLibraryEntry.docId);
    paramSQLiteStatement.bindLong(5, paramLibraryEntry.docType);
    paramSQLiteStatement.bindLong(6, paramLibraryEntry.offerType);
  }

  private static ContentValues getFullContentValues(LibraryEntry paramLibraryEntry)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("account", paramLibraryEntry.accountName);
    localContentValues.put("library_id", paramLibraryEntry.libraryId);
    localContentValues.put("backend", Integer.valueOf(paramLibraryEntry.backendId));
    localContentValues.put("doc_id", paramLibraryEntry.docId);
    localContentValues.put("doc_type", Integer.valueOf(paramLibraryEntry.docType));
    localContentValues.put("offer_type", Integer.valueOf(paramLibraryEntry.offerType));
    localContentValues.put("document_hash", Long.valueOf(paramLibraryEntry.documentHash));
    if ((paramLibraryEntry instanceof LibraryAppEntry))
    {
      LibraryAppEntry localLibraryAppEntry = (LibraryAppEntry)paramLibraryEntry;
      localContentValues.put("app_certificate_hash", localLibraryAppEntry.certificateHash);
      localContentValues.put("app_refund_pre_delivery_endtime_ms", Long.valueOf(localLibraryAppEntry.refundPreDeliveryEndtimeMs));
      localContentValues.put("app_refund_post_delivery_window_ms", Long.valueOf(localLibraryAppEntry.refundPostDeliveryWindowMs));
    }
    while (true)
    {
      return localContentValues;
      if ((paramLibraryEntry instanceof LibrarySubscriptionEntry))
      {
        LibrarySubscriptionEntry localLibrarySubscriptionEntry = (LibrarySubscriptionEntry)paramLibraryEntry;
        localContentValues.put("subs_auto_renewing", Boolean.valueOf(localLibrarySubscriptionEntry.isAutoRenewing));
        localContentValues.put("subs_initiation_time", Long.valueOf(localLibrarySubscriptionEntry.initiationTimestampMs));
        localContentValues.put("subs_valid_until_time", Long.valueOf(localLibrarySubscriptionEntry.validUntilTimestampMs));
        localContentValues.put("subs_trial_until_time", Long.valueOf(localLibrarySubscriptionEntry.trialUntilTimestampMs));
      }
      else if ((paramLibraryEntry instanceof LibraryInAppEntry))
      {
        LibraryInAppEntry localLibraryInAppEntry = (LibraryInAppEntry)paramLibraryEntry;
        localContentValues.put("inapp_purchase_data", localLibraryInAppEntry.signedPurchaseData);
        localContentValues.put("inapp_signature", localLibraryInAppEntry.signature);
      }
    }
  }

  public static int getVersion()
  {
    return 6;
  }

  public void add(LibraryEntry paramLibraryEntry)
  {
    try
    {
      ContentValues localContentValues = getFullContentValues(paramLibraryEntry);
      this.mDb.replace("ownership", null, localContentValues);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void addAll(Collection<? extends LibraryEntry> paramCollection)
  {
    try
    {
      this.mDb.beginTransaction();
      try
      {
        Iterator localIterator = paramCollection.iterator();
        while (localIterator.hasNext())
          add((LibraryEntry)localIterator.next());
      }
      finally
      {
        this.mDb.endTransaction();
      }
    }
    finally
    {
    }
    this.mDb.setTransactionSuccessful();
    this.mDb.endTransaction();
  }

  public void close()
  {
    this.mQueryContains.close();
    this.mQueryRemove.close();
    this.mQueryResetAccountLibraryId.close();
    this.mQuerySize.close();
    this.mDb.close();
  }

  public boolean contains(LibraryEntry paramLibraryEntry)
  {
    try
    {
      bindPartialStatement(this.mQueryContains, paramLibraryEntry);
      long l = this.mQueryContains.simpleQueryForLong();
      if (l == 1L)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public Iterator<LibraryEntry> iterator()
  {
    try
    {
      Iterator local1 = new Iterator()
      {
        protected void finalize()
          throws Throwable
        {
          if (!this.val$all.isClosed())
            this.val$all.close();
          super.finalize();
        }

        public boolean hasNext()
        {
          boolean bool = false;
          if ((this.val$all.isAfterLast()) || (this.val$all.isLast()))
            this.val$all.close();
          while (true)
          {
            return bool;
            if (!this.val$all.isClosed())
              bool = true;
          }
        }

        public LibraryEntry next()
        {
          Object localObject;
          if (!this.val$all.moveToNext())
            localObject = null;
          while (true)
          {
            return localObject;
            String str1 = this.val$all.getString(0).intern();
            String str2 = this.val$all.getString(1);
            int i = this.val$all.getInt(2);
            String str3 = this.val$all.getString(3);
            int j = this.val$all.getInt(4);
            int k = this.val$all.getInt(5);
            long l = this.val$all.getLong(6);
            if (!"u-wl".equals(str2))
            {
              if (j == 1)
              {
                localObject = new LibraryAppEntry(str1, str3, k, l, this.val$all.getString(7), this.val$all.getLong(8), this.val$all.getLong(9));
              }
              else
              {
                if (j == 15)
                {
                  if (this.val$all.getInt(10) > 0);
                  for (boolean bool = true; ; bool = false)
                  {
                    localObject = new LibrarySubscriptionEntry(str1, str2, i, str3, k, this.val$all.getLong(11), this.val$all.getLong(12), this.val$all.getLong(13), bool, l);
                    break;
                  }
                }
                if (j == 11)
                {
                  String str4 = this.val$all.getString(14);
                  String str5 = this.val$all.getString(15);
                  if ((str4 != null) && (str5 != null))
                    localObject = new LibraryInAppEntry(str1, str2, str3, k, str4, str5, l);
                }
              }
            }
            else
              localObject = new LibraryEntry(str1, str2, i, str3, j, k, l);
          }
        }

        public void remove()
        {
          throw new UnsupportedOperationException();
        }
      };
      return local1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void remove(LibraryEntry paramLibraryEntry)
  {
    try
    {
      bindPartialStatement(this.mQueryRemove, paramLibraryEntry);
      this.mQueryRemove.execute();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void reopen()
  {
    this.mDb = new Helper(this.mContext).getWritableDatabase();
    this.mQueryContains = this.mDb.compileStatement("SELECT COUNT(*) FROM ownership WHERE account=? AND library_id=? AND backend=? AND doc_id=? AND doc_type=? AND offer_type=?");
    this.mQueryRemove = this.mDb.compileStatement("DELETE FROM ownership WHERE account=? AND library_id=? AND backend=? AND doc_id=? AND doc_type=? AND offer_type=?");
    this.mQueryResetAccountLibraryId = this.mDb.compileStatement("DELETE FROM ownership WHERE account=? AND library_id=?");
    this.mQuerySize = this.mDb.compileStatement("SELECT COUNT(*) FROM ownership");
  }

  public void resetAccountLibrary(Account paramAccount, String paramString)
  {
    try
    {
      this.mQueryResetAccountLibraryId.bindString(1, paramAccount.name);
      this.mQueryResetAccountLibraryId.bindString(2, paramString);
      this.mQueryResetAccountLibraryId.execute();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void resetKeepOnlyAccounts(List<Account> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString = new String[paramList.size()];
    for (int i = 0; i < paramList.size(); i++)
    {
      if (i > 0)
        localStringBuilder.append(',');
      localStringBuilder.append('?');
      arrayOfString[i] = ((Account)paramList.get(i)).name;
    }
    int j = this.mDb.delete("ownership", "account NOT IN (" + localStringBuilder.toString() + ")", arrayOfString);
    if (j > 0)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(j);
      FinskyLog.d("Removed %d obsolete library rows.", arrayOfObject);
    }
  }

  public int size()
  {
    return (int)this.mQuerySize.simpleQueryForLong();
  }

  private static class Helper extends SQLiteOpenHelper
  {
    public Helper(Context paramContext)
    {
      super("library.db", null, 6);
    }

    private void recreateDatabase(SQLiteDatabase paramSQLiteDatabase)
    {
      try
      {
        paramSQLiteDatabase.execSQL("DROP TABLE ownership");
        label6: onCreate(paramSQLiteDatabase);
        return;
      }
      catch (SQLException localSQLException)
      {
        break label6;
      }
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE ownership (account STRING, library_id STRING, backend INTEGER, doc_id STRING, doc_type INTEGER, offer_type INTEGER, document_hash INTEGER, app_certificate_hash STRING, app_refund_pre_delivery_endtime_ms INTEGER, app_refund_post_delivery_window_ms INTEGER, subs_auto_renewing INTEGER, subs_initiation_time INTEGER, subs_valid_until_time INTEGER, subs_trial_until_time INTEGER, inapp_purchase_data STRING, inapp_signature STRING, PRIMARY KEY (account, library_id, backend, doc_id, doc_type, offer_type))");
    }

    public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      FinskyLog.d("Downgrading Library from %d to %d", arrayOfObject);
      recreateDatabase(paramSQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt1 == 5)
      {
        paramSQLiteDatabase.execSQL("ALTER TABLE ownership ADD COLUMN inapp_purchase_data STRING");
        paramSQLiteDatabase.execSQL("ALTER TABLE ownership ADD COLUMN inapp_signature STRING");
      }
      while (true)
      {
        return;
        recreateDatabase(paramSQLiteDatabase);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.SQLiteLibrary
 * JD-Core Version:    0.6.2
 */