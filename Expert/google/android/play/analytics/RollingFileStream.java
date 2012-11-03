package com.google.android.play.analytics;

import com.google.android.play.utils.FileModifiedDateComparator;
import com.google.common.collect.Lists;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedList;

public class RollingFileStream
{
  private final LinkedList<File> mAvailableFilesToRead;
  private final LinkedList<File> mAvailableFilesToWrite;
  private final String mFileNamePrefix;
  private MultiFileInputStream mInputStream = null;
  private final File mLogFileDirectory;
  private final long mMaxFileSize;
  private final int mNumberOfFiles;
  private MultiFileOutputStream mOutputStream = null;
  private final long mRecommendedFileSize;

  public RollingFileStream(File paramFile, String paramString, int paramInt, long paramLong1, long paramLong2)
  {
    if ((paramInt <= 0) || (paramLong1 <= 0L) || (paramLong2 <= 0L))
      throw new IllegalArgumentException("value <= zero: numOfFiles" + paramInt + " recommendedFileSize: " + paramLong1 + " maxFileSize:" + paramLong2);
    this.mLogFileDirectory = paramFile;
    this.mFileNamePrefix = paramString;
    this.mNumberOfFiles = paramInt;
    this.mRecommendedFileSize = paramLong1;
    this.mMaxFileSize = paramLong2;
    this.mAvailableFilesToRead = Lists.newLinkedList();
    this.mAvailableFilesToWrite = Lists.newLinkedList();
    loadFiles();
  }

  private void loadFiles()
  {
    if (!this.mLogFileDirectory.exists())
      this.mLogFileDirectory.mkdirs();
    int i = 0;
    if (i < this.mNumberOfFiles)
    {
      File localFile = new File(this.mLogFileDirectory, getFileName(i));
      if ((localFile.exists()) && (localFile.length() >= this.mMaxFileSize))
        this.mAvailableFilesToRead.add(localFile);
      while (true)
      {
        i++;
        break;
        this.mAvailableFilesToWrite.add(localFile);
      }
    }
    Collections.sort(this.mAvailableFilesToRead, FileModifiedDateComparator.INSTANCE);
  }

  public void closeInputStream(InputStream paramInputStream, boolean paramBoolean)
    throws IOException
  {
    if (!(paramInputStream instanceof MultiFileInputStream))
      throw new IllegalArgumentException("Must provide exact stream as given by getInputStream");
    ((MultiFileInputStream)paramInputStream).close(paramBoolean);
  }

  protected String getFileName(int paramInt)
  {
    if (paramInt > this.mNumberOfFiles)
      throw new IndexOutOfBoundsException("given index: " + paramInt + " greater than the number of files: " + this.mNumberOfFiles);
    return this.mFileNamePrefix + "." + (paramInt + 1);
  }

  public InputStream getInputStream()
    throws IOException
  {
    if (this.mInputStream != null)
      throw new IllegalStateException("Input stream already provided.  Close that stream before getting a new one.");
    if (this.mAvailableFilesToRead.size() != 0);
    for (int i = 1; ; i = 0)
    {
      if ((i == 0) && (this.mOutputStream != null))
        this.mOutputStream.rotateFileIfLargerThan(this.mRecommendedFileSize);
      if (this.mAvailableFilesToRead.size() != 0)
        this.mInputStream = new MultiFileInputStream((File)this.mAvailableFilesToRead.removeFirst());
      return this.mInputStream;
    }
  }

  public OutputStream getOutputStream()
    throws IOException
  {
    if (this.mOutputStream == null)
      this.mOutputStream = new MultiFileOutputStream();
    return this.mOutputStream;
  }

  public boolean hasFileReadyToSend()
  {
    if ((this.mAvailableFilesToRead.size() != 0) || ((this.mOutputStream != null) && (this.mOutputStream.mFileSize >= this.mRecommendedFileSize)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private static class FlushableFileOutputStream extends FileOutputStream
  {
    public FlushableFileOutputStream(File paramFile, boolean paramBoolean)
      throws FileNotFoundException
    {
      super(paramBoolean);
    }

    public void close()
      throws IOException
    {
      getFD().sync();
      super.close();
    }
  }

  private class MultiFileInputStream extends InputStream
  {
    private FileInputStream mFileInputStream;
    private final File mFileReadingFrom;

    public MultiFileInputStream(File arg2)
      throws IOException
    {
      File localFile;
      this.mFileReadingFrom = localFile;
      this.mFileInputStream = new FileInputStream(localFile);
    }

    public void close()
      throws IOException
    {
      throw new UnsupportedOperationException("Use MultipleFileStream.closeInputStream to close this stream");
    }

    public void close(boolean paramBoolean)
      throws IOException
    {
      if (RollingFileStream.this.mInputStream != this)
        throw new IllegalStateException();
      this.mFileInputStream.close();
      this.mFileInputStream = null;
      if (paramBoolean)
        this.mFileReadingFrom.delete();
      RollingFileStream.this.mAvailableFilesToWrite.add(this.mFileReadingFrom);
      RollingFileStream.access$502(RollingFileStream.this, null);
    }

    public int read()
      throws IOException
    {
      throw new UnsupportedOperationException("use read(byte[]).  It's more efficient");
    }

    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      return this.mFileInputStream.read(paramArrayOfByte, paramInt1, paramInt2);
    }
  }

  private class MultiFileOutputStream extends OutputStream
  {
    private boolean mClosed = false;
    private File mCurrentFileWritingTo;
    private OutputStream mFileOutputStream;
    private long mFileSize;

    public MultiFileOutputStream()
      throws IOException
    {
      rotateFiles();
    }

    public void close()
      throws IOException
    {
      if (this.mClosed)
        throw new IllegalStateException("Stream already closed");
      this.mClosed = true;
      this.mFileOutputStream.flush();
      this.mFileOutputStream.close();
      this.mFileOutputStream = null;
      if (this.mFileSize > 0L)
        RollingFileStream.this.mAvailableFilesToRead.add(this.mCurrentFileWritingTo);
      while (true)
      {
        RollingFileStream.access$402(RollingFileStream.this, null);
        return;
        RollingFileStream.this.mAvailableFilesToWrite.addFirst(this.mCurrentFileWritingTo);
      }
    }

    public void rotateFileIfLargerThan(long paramLong)
      throws IOException
    {
      if (this.mFileSize >= paramLong)
        rotateFiles();
    }

    public void rotateFiles()
      throws IOException
    {
      if (this.mFileOutputStream != null)
      {
        this.mFileOutputStream.flush();
        this.mFileOutputStream.close();
        this.mFileOutputStream = null;
      }
      if (this.mCurrentFileWritingTo != null)
      {
        if (this.mCurrentFileWritingTo.length() > 0L)
          RollingFileStream.this.mAvailableFilesToRead.add(this.mCurrentFileWritingTo);
      }
      else
      {
        if (RollingFileStream.this.mAvailableFilesToWrite.size() == 0)
          break label137;
        this.mCurrentFileWritingTo = ((File)RollingFileStream.this.mAvailableFilesToWrite.removeFirst());
      }
      while (true)
      {
        this.mFileSize = 0L;
        this.mFileOutputStream = new BufferedOutputStream(new RollingFileStream.FlushableFileOutputStream(this.mCurrentFileWritingTo, true));
        return;
        RollingFileStream.this.mAvailableFilesToWrite.add(this.mCurrentFileWritingTo);
        break;
        label137: this.mCurrentFileWritingTo = ((File)RollingFileStream.this.mAvailableFilesToRead.removeFirst());
        this.mCurrentFileWritingTo.delete();
      }
    }

    public void write(int paramInt)
      throws IOException
    {
      throw new UnsupportedOperationException("use write(byte[])... it's more efficient");
    }

    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if (this.mClosed)
        throw new IllegalStateException("Stream already closed");
      this.mFileOutputStream.write(paramArrayOfByte, paramInt1, paramInt2);
      this.mFileSize += paramInt2;
      if (this.mFileSize >= RollingFileStream.this.mMaxFileSize)
        rotateFiles();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.play.analytics.RollingFileStream
 * JD-Core Version:    0.6.2
 */