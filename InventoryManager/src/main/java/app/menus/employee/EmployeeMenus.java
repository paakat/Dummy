package app.menus.employee;

import app.tools.LMapIntegerString;
import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Names;
import app.menus.Controller;


public class EmployeeMenus extends Controller
{
  final String   BACK = Names.HOME;
  final String MYSELF = Names.EMPLOYEE; 


  public EmployeeMenus() { 
    SetName(MYSELF); 
  }


  public LMapIntegerString Model() { 
    options = new LMapIntegerString(); 
    options.SetName(MYSELF); 

    Integer i=0; 
    options.Put(i++,BACK);
    options.Put(i++,Names.PRODUCT);
    options.Put(i++,Names.STATIST);
    options.Put(i++,Names.INVENTO);

    Generic.PrintDebug("[Model] '"+ GetName() +"' ...");
    return options; 
  } 


  @Override
  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner) {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");

    switch(optionChosen) { 
      case(BACK) -> {}
      case(Names.PRODUCT) -> optionChosen = Names.PRODUCT;  
      case(Names.STATIST) -> optionChosen = Names.STATIST;
      case(Names.INVENTO) -> optionChosen = Names.INVENTO;
      default -> {}
    }

    BottomMessage(rScanner);
    return optionChosen;
  }
}
