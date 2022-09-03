package app.menus.atm;

import app.tools.Generic;
import app.tools.RecursiveScanner;
import app.tools.LMapIntegerString;
import app.tools.LMapStringString;

import app.menus.Names;
import app.menus.Controller;

import app.account.AccountFind;
import app.account.AccountNames;
import app.account.AccountCurrentUser;

import app.tools.io.Serializing;


public class MenuAtmOperations
{

  static boolean Transfer(String user, Double received)    
  {
    boolean done = false;

    AccountFind accounts = new AccountFind();
    accounts.LoadList(Names.PATH);
    if( accounts.Exist(user) )
    {
      LMapStringString account = accounts.GetAccount(user);

      Double cash = Double.parseDouble(account.Get(AccountNames.CASH));
      Double amount = cash + received; 
      account.Replace(AccountNames.CASH, amount.toString());
      new Serializing<LMapStringString>(account, user + ".ser");

      done = true; 
    }
    else 
    {
      System.out.println("\t[Transfer] '" + Generic.SetGreen(user) +"' no valid. Try again...");
    }

    return done; 
  }


  public static void Transfer(Double money, RecursiveScanner rScanner)
  {
    String target = SetData("user who receives: ",rScanner); 
    Double amount = String2Double("amount: ",rScanner);

    if(Transfer(target,amount))
    { 
      Double diff = money - amount;
      AccountCurrentUser.account.Replace(AccountNames.CASH, diff.toString() );
      //new Serializing<LMapStringString>(AccountCurrentUser.account, target + ".ser");

      System.out.println("\t[Transfer] '"+target+"' received '"+amount+"'  (balance:" + diff +") ");
      //Generic.Exit(true,"\nThanks ..."); 
    }
  }


  public static Double Deposit(Double money, RecursiveScanner rScanner)
  { 
    Double amount = String2Double("amount: ",rScanner);
    Double diff = money + amount;
    
    AccountCurrentUser.account.Replace(AccountNames.CASH, diff.toString() ); 
    System.out.println("\t[Deposit] cash:" + money +" + "+amount+" = " + diff);
    return diff;
  }


  public static Double WithDraw(Double money, RecursiveScanner rScanner)
  {
    Double amount = String2Double("amount: ",rScanner);
    Double diff = money - amount;

    AccountCurrentUser.account.Replace(AccountNames.CASH, diff.toString() );
    System.out.println("\t[WithDraw] cash:" + money +" - "+amount+" = " + diff);
    return diff;
  }


  public static Double String2Double(String txt, RecursiveScanner rScanner)
  {
    return String2Double( SetData(txt,rScanner) );
  }


  public static Double String2Double(String amount)
  {
    Double d = 0.0;

    try
    {
      d = Double.parseDouble(amount);
    }
    catch(Exception e)
    {
      System.out.println("\t[String2Double] '" + Generic.SetGreen(amount) +"' no valid. Try again...");
    }

    return d;
  }


  static String SetData(String text, RecursiveScanner rScanner)
  {
    String inputa = null;
    inputa = rScanner.GetNextLine(text);
    return inputa;
  }

}
