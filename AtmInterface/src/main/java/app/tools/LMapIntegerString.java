package app.tools;
import app.tools.LinkedHashMapExtended;
import app.tools.Generic; 

public class LMapIntegerString extends LinkedHashMapExtended<Integer,String> 
{
  private static final long serialVersionUID = (long)3.14159265359;

  private static Integer iteration = 0; 

  public void Print()
  {
    if(name != null) 
    {
      System.out.printf("\n%s menu (%02d)\n", Generic.SetGreen(name.toUpperCase()), iteration++); 
    } 
    for(Integer key : super.keySet())
    {
      String v = Generic.SetBlue( super.get(key) );
      String k = Generic.SetGreen(String.format("%2d",key));
      System.out.printf("[%s] %s\n", k, v);
    }
  }

};
