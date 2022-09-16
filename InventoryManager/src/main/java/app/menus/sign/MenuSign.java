package app.menus.sign;

import app.tools.Generic;
import app.tools.RecursiveScanner;
import app.tools.LMapIntegerString;

import app.menus.Names;
import app.menus.Controller;

import app.account.AccountCreate; 


public class MenuSign extends Controller
{
  public static final String CREATE = "create";


  public MenuSign()
  {
    SetName(Names.SIGN); 
  }


  public LMapIntegerString CreateOptions()
  {
    options = new LMapIntegerString(); 
    options.SetName(Names.SIGN); 
    options.Put( 0, Names.HOME);
    options.Put( 1, CREATE);

    Generic.PrintDebug("[CreateOptions] '"+GetName()+"' ...");
    return options; 
  } 


  @Override 
  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner)
  {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");

    switch(optionChosen)
    {
      case(CREATE) -> optionChosen = SetData(rScanner);  
    }

    BottomMessage(rScanner);
    return optionChosen;
  }


  String SetData(RecursiveScanner rScanner)
  {
    AccountCreate a = new AccountCreate();
    a.SetName( SetData("name: ",rScanner) ); 
    a.SetUser( SetData("user: ",rScanner) );      
    a.SetPass( SetData("pass: ",rScanner) );      
    a.SetCash("0.0"); 
    a.Save(); 

    System.out.println("[SetData] '"+GetName()+"' -> '" + Generic.SetBlue(Names.LOGIN) +"' ");
    return Names.LOGIN; 
  }

} 
