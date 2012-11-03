package com.google.android.play.utils;

import java.io.File;
import java.util.Comparator;

public class FileModifiedDateComparator
  implements Comparator<File>
{
  public static final FileModifiedDateComparator INSTANCE = new FileModifiedDateComparator();

  public int compare(File paramFile1, File paramFile2)
  {
    int i;
    if (paramFile1.lastModified() == paramFile2.lastModified())
      i = 0;
    while (true)
    {
      return i;
      if (paramFile1.lastModified() < paramFile2.lastModified())
        i = -1;
      else
        i = 1;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.play.utils.FileModifiedDateComparator
 * JD-Core Version:    0.6.2
 */