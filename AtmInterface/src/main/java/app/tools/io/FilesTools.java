package app.tools.io;

import app.tools.LMapIntegerString;
import app.tools.Generic;

import java.io.File;
import java.nio.file.Files;

public class FilesTools
{

  public static LMapIntegerString GetFilesName(String path, String type)
  {
    File dir = new File(path);
    File[] files = dir.listFiles((d,name) -> name.endsWith(type));

    LMapIntegerString dic = new LMapIntegerString();

    if(files != null)
    {
      int i = 1;
      for (File file : files) dic.put(i++, file.getAbsolutePath());
    }

    return dic; 
  }


  public static boolean OpenFile(String fname, String path) 
  {
    File root = new File(path);
    File  fin = new File(root,fname);

    Generic.Exit(!root.exists(), "[player.Runner] '"+ root.getAbsolutePath() +"' no found!");
    Generic.Exit(!fin.exists(), "[player.Runner] '"+ fin.getAbsolutePath() +"' no found!");
     
    String absolute = fin.getAbsolutePath();
    
    boolean couldBeOpen = fin.isFile(); 
    return couldBeOpen; 
  }


  public static boolean CouldBeOpen(String fname)
  {
    File  fin = new File(fname);
    Generic.Exit(!fin.exists(), "[player.Runner] '"+ fin.getAbsolutePath() +"' no found!");
    return fin.isFile(); 
  }


  public static void RemoveFile(String fname)
  {
    try
    {
      Files.deleteIfExists( new File(fname).toPath() ); 
    }
    catch(Exception e)
    {
      System.out.println(" '"+ fname+ "' no found!"); 
    }
  }

}
