package app.tools.io;

import java.io.FileOutputStream ;
import java.io.ObjectOutputStream ;
import java.io.IOException ;


// Tools 
import app.tools.Generic;
import app.tools.LinkedHashMapExtended;


public class Serializing<T>
{
  public Serializing(T obj, String path) 
  {
    try 
    {
       FileOutputStream fileOut =
       new FileOutputStream(path);
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(obj);
       out.close();
       fileOut.close();
    }
    catch (IOException i) 
    {
       i.printStackTrace();
    }
  } 
}
