package app.tools;

import app.tools.LinkedHashMapExtended;
import app.tools.LMapIntegerString;
import app.tools.Generic; 


public class LMapStringLMapStringString extends LinkedHashMapExtended<String,LMapStringString> 
{
  private static final long serialVersionUID = (long)3.14159265359;

 
  public void Print()
  {
    System.out.print(Generic.SetGreen("\n================| "));
    if(name != null)
    {
      System.out.printf("%s recipe", Generic.SetGreen(name.toUpperCase()) );
      System.out.print(Generic.SetGreen(" |======================="));
    }
    for(String key : super.keySet())
    {
      super.get(key).Print(); 
    }
    System.out.println(Generic.SetGreen("=================================================================\n"));
  }

};
