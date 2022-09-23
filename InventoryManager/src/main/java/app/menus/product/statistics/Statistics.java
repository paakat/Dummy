package app.menus.product.statistics;

import app.tools.LMapStringString;
import app.tools.LMapIntegerString;
import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Names;
import app.menus.Controller;



public class Statistics extends Controller {

  static final String BACK = Names.HOME; 
  static final String AMOUNTS = "amounts";
//  static final String PRICES = "prices";


  public Statistics() {
    SetName(StatisticsRunners.STATISTICS); 
  }


  public LMapIntegerString Model() {
    options = new LMapIntegerString(); 
    options.SetName(StatisticsRunners.STATISTICS); 

    Integer i = 0; 
    options.Put(i++,BACK);
    options.Put(i++,AMOUNTS);
//    options.Put(i++,PRICES);

    Generic.PrintDebug("[Model] '"+ GetName() +"' ...");
    return options; 
  } 


  @Override
  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner)
  {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");

    switch(optionChosen)
    {
      case(BACK) -> {}  
//      case(PRICES) -> prices();  
      case(AMOUNTS) -> amounts();  
      default -> {}  
    }

    BottomMessage(rScanner);
    return optionChosen;
  }

/*
  void prices() { 
    StatisticsModel p = new StatisticsModel(Names.FILE_PRICES);
    p.view();
  }
*/

  void amounts() {
    StatisticsModel a = new StatisticsModel(Names.FILE_AMOUNTS);
    a.view();
  }


}
