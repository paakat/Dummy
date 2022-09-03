package app.menus.atm;

import app.tools.Generic;
import app.tools.RecursiveScanner;
import app.tools.LMapIntegerString;
import app.tools.LMapStringString;

import app.menus.Names;
import app.menus.Controller;

import app.account.AccountCurrentUser;
import app.account.AccountNames; 

import app.tools.io.Serializing;


public class MenuAtm extends Controller
{

  public MenuAtm()
  {
    SetName(Names.ATM); 
  }


  public LMapIntegerString CreateOptions()
  {
    options = new LMapIntegerString(); 
    options.SetName(Names.ATM); 

    Integer i = 0;
    options.Put(i++, Names.HOME);
    options.Put(i++, Names.BALANCE);
    options.Put(i++, Names.WITHDRAW);
    options.Put(i++, Names.DEPOSIT);
    options.Put(i++, Names.TRANSFER);
    options.Put(i++, Names.LOGOUT);

    Generic.PrintDebug("[CreateOptions] '"+GetName()+"' ...");
    return options; 
  } 


  @Override
  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner)
  {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");

    LMapStringString account = AccountCurrentUser.account; 
    Generic.Exit(account == null, "[ChosenOptionFound] 'account == null' "); 

    String title = Generic.SetGreen(account.GetName());
    Double cash = Double.parseDouble(account.Get(AccountNames.CASH));

    switch(optionChosen)
    { 
      case(Names.LOGOUT)   -> { optionChosen = Names.HOME; }
      case(Names.TRANSFER) -> MenuAtmOperations.Transfer(cash,rScanner);
      case(Names.WITHDRAW) -> MenuAtmOperations.WithDraw(cash,rScanner);  
      case(Names.DEPOSIT)  -> MenuAtmOperations.Deposit(cash,rScanner);
      case(Names.BALANCE)  -> { System.out.println("["+title+"] cash:" + cash); }
    }

    String user = account.Get(AccountNames.USER);
    new Serializing<LMapStringString>(AccountCurrentUser.account, user + ".ser");

    Generic.Exit(optionChosen == Names.TRANSFER,"\nThanks ..."); 
    BottomMessage(rScanner); 
    return optionChosen;
  }

}
