package app; 

import app.tools.RecursiveScanner;
import app.tools.LMapStringRunnable;

import app.menus.Menus;
import app.menus.Controller;


public class Scheduler {

  static RecursiveScanner rScanner = null; 
  static LMapStringRunnable runners = null; 


  public Scheduler() {
 
    rScanner = new RecursiveScanner();

    Menus m = new Menus();
    runners = m.Get(); 
    executingRecursively(m.GetMainMenuName()); 
  }


  static void executingRecursively(String currentMenu) {
    Controller runner = runners.Get(currentMenu);
    runner.Model();
    String nextAction = runner.Recursive(rScanner); 
      
    if(runners.Exits(nextAction)) currentMenu = nextAction;
    executingRecursively(currentMenu);
  }

} 
