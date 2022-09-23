package app.menus.inventory;

import app.tools.Generic;
import app.tools.LMapStringLMapStringString;
import app.tools.LMapStringString;

import app.tools.io.Serializing;
import app.tools.io.DeSerializing;
import app.tools.io.FilesTools;
 
import app.menus.Names;


public class Model {

  static public LMapStringLMapStringString  obj = null; 


  public Model(String name) {
    if(FilesTools.CouldBeOpen(name)) {
      obj = new DeSerializing<LMapStringLMapStringString>(name).GetObj(); 
      Generic.PrintDebug("[inventory.Model] '"+name+"' open...");
    }
    else { 
      obj = new LMapStringLMapStringString();
      obj.SetName(name);
      Generic.PrintDebug("[inventory.Model] '"+name+"' created..."); 
    }
  }


  public void view() {
    obj.Print();
  }


  public LMapStringLMapStringString get() {
    return obj;
  }


  public LMapStringString get(String key) {
    return obj.Get(key);
  }


  public boolean exist(String key) {
    return obj.Exits(key);
  }


  public void save(String fname) {
    try {
      new Serializing<LMapStringLMapStringString>(obj, fname);
    }
    catch(Exception e) {
      System.out.println("\t[Save] '" + Generic.SetGreen(fname) +"' no valid. Try again...");
    }

    Generic.PrintDebug("[inventory.save] '"+ fname +"' saved...");
  }


} 
