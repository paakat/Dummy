package app.menus;

import app.menus.Names;
import app.menus.Controller;

import app.tools.LMapIntegerString;
import app.tools.Generic;


public class Home extends Controller
{
  final String BACK = Names.HOME;
  final String MYSELF = Names.HOME; 


  public Home() {
    SetName(MYSELF); 
//    Names.LEVEL = "ZERO";
  }


  public LMapIntegerString Model() {
    options = new LMapIntegerString(); 
    options.SetName(MYSELF); 

    Integer i = 0;
    options.Put(i++, Names.ADMINIS);
    options.Put(i++, Names.MANAGER);
    options.Put(i++, Names.CASHIER);

    Names.LEVEL = "ZERO";

/*
    switch(Names.Level.valueOf(Names.LEVEL)) {
      case MANAGER -> {
        options.Put(i++, Names.INVENTO);
        options.Put(i++, Names.PRODUCT);
        options.Put(i++, Names.STATIST);
      }
      default -> {
        options.Put(i++, Names.ADMINIS);
        options.Put(i++, Names.MANAGER);
        options.Put(i++, Names.CASHIER);
      }
    }
*/
    Generic.PrintDebug("[Model] '"+ GetName() +"' ...");
    return options; 
  } 

}
