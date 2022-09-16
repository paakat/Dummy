import app.Scheduler; 
import app.tools.Generic; 


public class Main
{
  public static void main(String[] args)
  {
    try{
      new app.Scheduler(); 
    } 
    catch(Exception e){
      Generic.Exit(true, "Something wrong..."); 
    }

  }
} 
