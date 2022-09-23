package app.menus.administrator;

import app.tools.LMapIntegerString;
import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Names;
import app.menus.Controller;


public class Administrator extends Controller
{
  final String   VIEW = "list employees";
  final String CREATE = "add employee";
  final String   BACK = Names.HOME;
  final String MYSELF = AdministratorOperations.MYSELF; 


  public Administrator() { 
    SetName(MYSELF); 
  }


  public LMapIntegerString Model() { 
    options = new LMapIntegerString(); 
    options.SetName(MYSELF); 

    Integer i=0; 
    options.Put(i++,BACK);
    options.Put(i++,VIEW);
    options.Put(i++,CREATE);
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
      case(VIEW) -> AdministratorController.view(); 
      case(CREATE) -> optionChosen = AdministratorOperations.creating(rScanner);
      case(Names.PRODUCT) -> optionChosen = Names.PRODUCT;  
      case(Names.STATIST) -> optionChosen = Names.STATIST;
      case(Names.INVENTO) -> optionChosen = Names.INVENTO;
      default -> {}
    }

    BottomMessage(rScanner);
    return optionChosen;
  }

}
