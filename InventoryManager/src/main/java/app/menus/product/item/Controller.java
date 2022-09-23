package app.menus.product.item;

import app.tools.Generic;
import app.tools.LMapStringString;
import app.tools.io.Serializing;


class Controller {

  public LMapStringString item = null;


  public Controller() {
  }


  public Controller(LMapStringString i) {
    item = i;  
  }


  public Controller(String key) {
    try {
      item = new ItemFind().get(key);
    } 
    catch(Exception e) { 
      System.out.println("\t[Controller] '" + Generic.SetGreen(key) +"' no valid. Try again...");
    }
  }


  public void view() {
    check(); 
    item.Print();
  }


  void check() {
    Generic.Exit(item == null, "[Controller] 'item == null' ");
  }


  public void replaceStrings(String key, String value) {
    check();
    item.Replace(key,value);
  }


  public Double replaceDouble(String key, String value) {
    check();

    boolean wrong = false;

    Double d = 0.0; 
    try {
      d = Double.parseDouble(value);

      if(d < 0.0) wrong = true;
      else item.Replace(key,value);
    }
    catch(Exception e) {
      wrong = true;
    }

    if(wrong) System.out.println("\t[setDouble] '" + Generic.SetGreen(value) +"' no valid. Try again...");
    return d; 
  }


  public Integer replaceInteger(String key, String value) {
    check();

    boolean wrong = false;

    Integer d = 0; 
    try {
      d = Integer.parseInt(value);

      if(d < 0) wrong = true;
      else item.Replace(key,value);
    }
    catch(Exception e) {
      wrong = true;
    }

    if(wrong) System.out.println("\t[setDouble] '" + Generic.SetGreen(value) +"' no valid. Try again...");
    return d; 
  }


  public void save() {
    save("id");
  }


  public void save(String key) {
    check();

    try {
      String fname = item.Get(key).toLowerCase();
      new Serializing<LMapStringString>(item, fname + ".ser");
    }
    catch(Exception e) {
      System.out.println("\t[Save] '" + Generic.SetGreen(key) +"' no valid. Try again...");
    }
  }

}
