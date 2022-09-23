package app.menus.product.item;

import app.tools.Generic;
import app.tools.LMapStringString;
import app.tools.io.Serializing;

import app.tools.io.Serializing;
import app.tools.io.DeSerializing;
import app.tools.io.FilesTools;

import app.menus.Names;


public class Model {

  public LMapStringString item = null;


  public Model(String name) {
    item = openModel(name);   
  }


  LMapStringString openModel(String name) {
    LMapStringString obj = null; 
    if(FilesTools.CouldBeOpen(name)) {
      obj = new DeSerializing<LMapStringString>(name).GetObj();
      Generic.PrintDebug("[item.Model] '"+name+"' open...");
    }
    else {
      obj = new LMapStringString();
      obj.SetName(name);
      Generic.PrintDebug("[item.Model] '"+name+"' created...");
    }
    return obj; 
  }


  public String getName() {
    return item.GetName(); 
  }


  public void view() {
    item.Print();
  }


  public LMapStringString get() {
    return item;
  }


  public void save(String key) {
    try {
      String fname = item.Get(key).toLowerCase();
      new Serializing<LMapStringString>(item, fname + ".ser");
    }
    catch(Exception e) {
      System.out.println("\t[Save] '" + Generic.SetGreen(key) +"' no valid. Try again...");
    }
  }


  public void setDouble(String key, String value) {
    boolean wrong = false; 

    try {
      Double d = Double.parseDouble(value); 

      if(d < 0.0) wrong = true; 
      else item.Put(key,value); 
    }
    catch(Exception e) {
      wrong = true; 
    }

    if(wrong) System.out.println("\t[setDouble] '" + Generic.SetGreen(value) +"' no valid. Try again...");
  }


  public void setInteger(String key, String value) {
    boolean wrong = false;

    try {
      Integer d = Integer.parseInt(value);

      if(d < 0) wrong = true;
      else item.Put(key,value);
    }
    catch(Exception e) {
      wrong = true;
    }

    if(wrong) System.out.println("\t[setDouble] '" + Generic.SetGreen(value) +"' no valid. Try again...");
  }

} 
