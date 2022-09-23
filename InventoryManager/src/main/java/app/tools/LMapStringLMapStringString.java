package app.tools;

import app.tools.LinkedHashMapExtended;
import app.tools.LMapStringString;
import app.tools.Generic; 


public class LMapStringLMapStringString extends LinkedHashMapExtended<String,LMapStringString> 
{
  private static final long serialVersionUID = (long)3.14159265359;

 
  public void Print()
  {
    System.out.print(Generic.SetGreen("\n================| "));
    if(name != null)
    {
      System.out.printf("%s", Generic.SetGreen(name.toUpperCase()) );
      System.out.print(Generic.SetGreen(" |=======================\n"));
    }

    Integer ii = 1;
    for(String key : super.keySet())
    {
//      System.out.print("["+ Generic.SetGreen(key) +"] "+ ii +"/"+super.size() +"  \n"); 
      System.out.print("+"+ ii +"/"+super.size() +". "+Generic.SetGreen(key) + " \n");
      super.get(key).Print(); 
      ii++;
    }
    System.out.println(Generic.SetGreen("============================================================\n"));
  }

/* 
  public void Add(String key1, String key2, String val2) { 
    if(super.Exits(key1)) {
    }
    else {
      super.Put(key1, new LMapStringString(key1)); 
    }

    if(super.Get(key1).Exits(key2)) {
      super.Get(key1).Replace(key2,val2);
    }
    else {  
      super.Get(key1).Put(key2,val2);
    } 
  }


  public boolean Remove(String key1, String key2) { 
    boolean done = false;

    if(super.Exits(key1)) {
      super.Get(key1).Remove(key2); 
      done = true;
    }
    else { 
      Warning(key1, " NO FOUND IN DIC ");
    }

    return done;
  }


  public String WhereKey2(String key2) { 

    String found = null; 
 
    for(String key1 : super.keySet()) {
      if(super.Get(key1).Exits(key2)) { 
        found = key1; 
      } 
    }

    return found; 
  }
*/


};
