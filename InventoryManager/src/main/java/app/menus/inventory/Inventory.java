package app.menus.inventory;

//import app.tools.LMapStringString;
import app.tools.LMapIntegerString;
import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Names;
import app.menus.Controller;


public class Inventory extends Controller
{

  final String LIST = "list";  
  final String SELL = "sell";  
  final String RETURN = "return";
  final String BACK = Names.HOME;

  public Inventory() { 
    SetName(Names.INVENTO); 
  }


  public LMapIntegerString Model() { 
    options = new LMapIntegerString(); 
    options.SetName(Names.INVENTO); 

    Integer i=0; 
    options.Put(i++, BACK);
    options.Put(i++, LIST);
    options.Put(i++, SELL);
    options.Put(i++, RETURN);

    Generic.PrintDebug("[Model] '"+ GetName() +"' ...");
    return options; 
  } 


  @Override
  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner) { 
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");

    switch(optionChosen) { 
      case(BACK) -> {}
      case(LIST) -> InventoryController.view();  
      case(SELL) -> InventoryOperations.selling(rScanner);  
      case(RETURN) -> InventoryOperations.returning(rScanner);  
      default -> {}
    }

    BottomMessage(rScanner);
    return optionChosen;
  }

}
