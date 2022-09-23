package app.tools.io;

import java.io.FileInputStream; 
import java.io.ObjectInputStream; 
import java.io.IOException ;

// Tools 
//import app.tools.Generic;
//import app.tools.Definitions;


public class DeSerializing<T>
{
  private T obj = null;

  @SuppressWarnings("unchecked") // warning: [unchecked] unchecked cast 
  public DeSerializing(String fname) 
  {
    try 
    {
     FileInputStream fileIn = new FileInputStream(fname);
     ObjectInputStream in = new ObjectInputStream(fileIn);

     obj = (T) in.readObject(); 
     in.close();
     fileIn.close();
    } 
    catch (IOException i) 
    {
      i.printStackTrace();
      return;
    } 
    catch (ClassNotFoundException c) 
    {
      System.out.println("Class not found");
      c.printStackTrace();
      return;
    }
  }


  public void ShowObj()
  {
    System.out.println("[DeSerializing] obj:'" + obj +"' "); 
  }


  public T GetObj() 
  {
    return obj; 
  }

}
