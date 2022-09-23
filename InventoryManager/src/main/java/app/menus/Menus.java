package app.menus;

import app.tools.Generic;
import app.tools.LMapStringRunnable;

//import app.menus.inventory.Inventory;
import app.menus.inventory.InventoryRunners;

import app.menus.product.ProductRunners; 
import app.menus.product.statistics.StatisticsRunners; 

import app.menus.administrator.AdministratorRunners;  
import app.menus.employee.EmployeeRunners; 


public class Menus {

  public static LMapStringRunnable runners = null;


  void SetRunners() {
    Names.LEVEL = "ZERO";

    runners = new LMapStringRunnable();
    runners.SetName("Runners");

    runners.Put(Names.HOME, new Home()); 
    runners = EmployeeRunners.Append(runners);
    runners = ProductRunners.Append(runners); 
    runners = InventoryRunners.Append(runners);
    runners = StatisticsRunners.Append(runners); 
    runners = AdministratorRunners.Append(runners);
  }


  public LMapStringRunnable Get() {
    SetRunners(); 
    SetOptions();  
    return runners; 
  }


  void SetOptions() {
    for(String key : runners.keySet()) { 
      Controller runner = runners.Get(key);
      runner.Model().Print();
    }
    Generic.ClearConsoleUnix();
  }


  public String GetMainMenuName() {
    return Names.HOME;   
  } 

}
