package app.account;

import app.tools.Generic;
import app.tools.RecursiveScanner;
import app.tools.LMapIntegerString;
import app.tools.LMapStringString;
import app.tools.LMapStringLMapStringString; 

import app.tools.io.DeSerializing;
import app.tools.io.FilesTools;

import app.tools.HashValues;


public class AccountFind
{
  LMapStringLMapStringString accounts = null; 


  public AccountFind()
  {
    accounts = new LMapStringLMapStringString(); 
  }


  public void LoadList(String path)
  {
    LMapStringString account = null; 

    LMapIntegerString filesName = FilesTools.GetFilesName(path,".ser");

    for(Integer id : filesName.keySet())
    {
      String fname = filesName.Get(id);
      if( FilesTools.CouldBeOpen(fname) )
      {
        account = new DeSerializing<LMapStringString>(fname).GetObj();  
        accounts.Put(account.Get(AccountNames.USER), account); 
      }
    }

    Generic.PrintDebug("[AccountFind] nAccounts:'"+ accounts.size() +"' ...");
  }


  public boolean Exist(String user)
  {
    return accounts.Exits(user);
  }


  public LMapStringString GetAccount(String user)
  {
    return accounts.Get(user);
  }


  public boolean CheckPass(String user, String pass)
  {
    boolean ok = false; 
    String passOrig = GetAccount(user).Get(AccountNames.PASS);
//    ok = passOrig.equals(pass); 
    ok = passOrig.equals( HashValues.Create(pass) );  
    return ok;
  } 


}
