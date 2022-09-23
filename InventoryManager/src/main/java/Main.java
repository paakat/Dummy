import app.Scheduler; 
import app.tools.Generic; 
import app.tests.Tests; 


public class Main{
  public static void main(String[] args){

    new Tests(); 

    try{
      new app.Scheduler(); 
    } 
    catch(Exception e){
      Generic.Exit(true, "Something wrong..."); 
    }

  }
} 
