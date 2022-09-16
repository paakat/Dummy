package app.menus;

import app.tools.LMapIntegerString;
import app.tools.Generic;

import app.menus.Controller;


public class MenuManager extends Controller
{

  public MenuManager()
  {
    SetName(Names.HOME); 
  }


  public LMapIntegerString CreateOptions()
  {
    options = new LMapIntegerString(); 
    options.SetName(Names.HOME); 
    options.Put( 1, Names.LOGIN);
    options.Put( 2, Names.SIGN);

    Generic.PrintDebug("[CreateOptions] '"+name+"' ...");
    return options; 
  } 

}
