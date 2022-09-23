package app.tools.find; 

import app.tools.Generic;
import app.tools.LMapIntegerString;
import app.tools.LMapStringString;
import app.tools.LMapStringLMapStringString; 

import app.tools.io.DeSerializing;
import app.tools.io.FilesTools;

//import app.tools.HashValues;

import java.util.Set;


public class FindLMapStringLMapStringString {

  LMapStringLMapStringString founds = null; 


  public FindLMapStringLMapStringString(String name)
  {
    founds = new LMapStringLMapStringString(); 
    founds.SetName(name);
  }


  public LMapStringLMapStringString getList(String path, String keyId, String type)
  {
    Generic.Exit(founds.GetName() == null, "[FindLMapStringLMapStringString] call 'setName' ..."); 

    LMapStringString account = null; 

    LMapIntegerString filesName = FilesTools.GetFilesName(path,type);

    for(Integer id : filesName.keySet())
    {
      String fname = filesName.Get(id);
      if( FilesTools.CouldBeOpen(fname) )
      {
        account = new DeSerializing<LMapStringString>(fname).GetObj();  
        founds.Put(account.Get(keyId), account); 
      }
    }

    Generic.PrintDebug("[FindLMapStringLMapStringString] nFounds:'"+ founds.size() +"' ...");
    return founds; 
  }


  public boolean exist(String key)
  {
    return founds.Exits(key);
  }


  public LMapStringString get(String key)
  {
    return founds.Get(key);
  }


  public LMapIntegerString getKeys()    
  {
    LMapIntegerString keys = new LMapIntegerString();
    keys.SetName("Keys"); 

    Integer i = 0; 
    for(String key : founds.keySet()) keys.Put(i++,key);  
    return keys;  
  }

/* 
  public boolean CheckPass(String user, String pass)
  {
    boolean ok = false; 
    String passOrig = GetAccount(user).Get(AccountNames.PASS);
    ok = passOrig.equals( HashValues.Create(pass) );  
    return ok;
  } 
*/

}
