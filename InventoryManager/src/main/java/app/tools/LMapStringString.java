package app.tools;
import app.tools.LinkedHashMapExtended;
import app.tools.Generic; 


public class LMapStringString extends LinkedHashMapExtended<String,String> 
{
  private static final long serialVersionUID = (long)3.14159265359;

/* 
  public void Print() {

    Integer ii = 1; 
    for(String key : super.keySet())
    {
      String value = super.Get(key);
      String type = ( value.equals("1") )?("available"):("not available");  
      System.out.println("|_"+ ii +"/"+super.size() +" " + Generic.SetBlue(key) +" " + type ); 
      ii += 1; 
    }
  }
*/

  public LMapStringString() {
    super();
  }


  public LMapStringString(String name) {
    super(name); 
  }

};
