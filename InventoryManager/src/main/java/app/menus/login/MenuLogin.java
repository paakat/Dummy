package app.menus.login;

import app.tools.Generic;
import app.tools.RecursiveScanner;
import app.tools.LMapIntegerString;

import app.menus.Names;
import app.menus.Controller;

import app.account.AccountFind;
import app.account.AccountCurrentUser;


public class MenuLogin extends Controller
{
  public static final String ENTER = "enter";

  static AccountFind accounts = null;  


  public MenuLogin()
  {
    SetName(Names.LOGIN);
    accounts = new AccountFind(); 
  }


  public LMapIntegerString CreateOptions()
  {
    options = new LMapIntegerString(); 
    options.SetName(Names.LOGIN); 
    options.Put( 0, Names.HOME);
    options.Put( 1, ENTER);

    accounts.LoadList(Names.PATH);

    Generic.PrintDebug("[CreateOptions] '"+GetName()+"' ...");
    return options; 
  } 


  @Override 
  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner)
  {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");

    switch(optionChosen)
    {
      case(ENTER) -> optionChosen = CheckIn(optionChosen, rScanner);  
    }

    BottomMessage(rScanner);
    return optionChosen;
  }


  String CheckIn(String optionChosen, RecursiveScanner rScanner)
  {
    AccountCurrentUser.account = null;

    String next = optionChosen; 

    String user = SetData("user: ",rScanner); 
    if( accounts.Exist(user) )
    {
      if( accounts.CheckPass(user, SetData("pass: ",rScanner)) ) 
      {
        AccountCurrentUser.account = accounts.GetAccount(user);
 
        next = Names.ATM;
        System.out.println("\tWellcome '" + Generic.SetBlue(user) +"'... ");
      }
      else
      { 
        System.out.println("\t['"+GetName()+"'] Wrong Password! Try again ... ");
      } 
    } 
    else
    {
      System.out.println("\t['"+GetName()+"'] User:'" + Generic.SetBlue(user) +"' no found! Try again ... ");
    }

    Generic.PrintDebug("[CheckIn] Changing directory: '"+GetName()+"' -> '"+next+"' ");
    return next; 
  }

}
