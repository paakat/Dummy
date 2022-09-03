package app.account;

import app.tools.Generic;
import app.tools.RecursiveScanner;
import app.tools.LMapIntegerString;
import app.tools.LMapStringString;
import app.tools.HashValues;

import app.tools.io.Serializing;


public class AccountCreate
{
  LMapStringString account = null;  


  public AccountCreate()
  {
    account = new LMapStringString(); 
    account.SetName("Bank Account"); 
  }


  public void SetName(String value)
  {
    account.Put(AccountNames.NAME,value);
  }


  public void SetUser(String value)
  {
    account.Put(AccountNames.USER,value);
  }


  public void SetPass(String value)
  {
    account.Put(AccountNames.PASS, HashValues.Create(value) );
  }


  public void SetCash(String value)
  {
    try 
    {
      Double.parseDouble(value); 
      account.Put(AccountNames.CASH,value);
    }
    catch(Exception e)
    {
      System.out.println("\t[SetCash] '" + Generic.SetGreen(value) +"' no valid. Try again...");
    }
  }


  public void Replace(String value)
  {
    try
    {
      Double.parseDouble(value);
      account.Replace(AccountNames.CASH,value);
    }
    catch(Exception e)
    {
      System.out.println("\t[SetCash] '" + Generic.SetGreen(value) +"' no valid. Try again...");
    }
  }


  public LMapStringString GetAccount() 
  {
    return account; 
  }


  public void Save()
  {
    String fname = account.Get("user").toLowerCase();
    new Serializing<LMapStringString>(account, fname + ".ser");
  }


  public void Print()
  {
    account.Print();  
  }

}
