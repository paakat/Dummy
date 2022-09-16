package app; 

import app.tools.RecursiveScanner;
import app.tools.LMapStringRunnable; 
//import app.tools.LMapIntegerString;
import app.tools.Generic; 

import app.menus.Names;
import app.menus.Controller;
import app.menus.MenuManager;
import app.menus.login.MenuLogin;
import app.menus.sign.MenuSign;
import app.menus.atm.MenuAtm;


public class Scheduler 
{
  static RecursiveScanner rScanner = null; 
  static LMapStringRunnable runners = null; 


  public Scheduler()
  {
    runners = new LMapStringRunnable();  
    rScanner = new RecursiveScanner();

    CreateMenus();
    CreateOptions();

//    System.out.println(Generic.SetYellow("\n\n'NICE BANK' VALKOMMEN ...\n"));
    ExecutingRecursively(Names.HOME);
  }


  static void CreateMenus() 
  {
    runners.SetName("Runners");
    runners.Put(Names.HOME, new MenuManager());
    runners.Put(Names.LOGIN, new MenuLogin());
    runners.Put(Names.SIGN, new MenuSign());
    runners.Put(Names.ATM, new MenuAtm());
  }


  static void CreateOptions() 
  {
    for(String key : runners.keySet()) 
    {
      Controller runner = runners.Get(key);
      runner.CreateOptions().Print();
      //LMapIntegerString options = runner.CreateOptions();
      //options.Print(); 
    }  
 
    Generic.ClearConsoleUnix();
  }


  static void ExecutingRecursively(String currentMenu) 
  {
    Controller runner = runners.Get(currentMenu);
    runner.CreateOptions();
    String nextAction = runner.Recursive(rScanner); 
      
    if(runners.Exits(nextAction)) currentMenu = nextAction;
    ExecutingRecursively(currentMenu);
  }

} 
