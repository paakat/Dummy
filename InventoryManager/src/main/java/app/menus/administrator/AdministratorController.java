package app.menus.administrator;


import app.tools.Generic;
import app.tools.LMapStringString;

import app.menus.Names;
import app.menus.inventory.InventoryModel;


public class AdministratorController {


  static public boolean create(String id, String name, String type) {
    setRegistration(type,id,name,Names.FILE_AUTORIZATIONS);
    System.out.println("\t[AdministratorController] '"+name+"' added as '"+ type +"'  !");
    return false;
  }


  static public void view() {
    InventoryModel inv = new InventoryModel(Names.FILE_AUTORIZATIONS);
    inv.view();
  }


  static public boolean exist(String id) {
    InventoryModel inv = new InventoryModel(Names.FILE_AUTORIZATIONS);
    return inv.exist(id);
  }


  static public String getParent(String child) {
    InventoryModel inv = new InventoryModel(Names.FILE_AUTORIZATIONS);
    return inv.WhereKey2(child);
  }


  static public LMapStringString get(String id) {
    InventoryModel inv = new InventoryModel(Names.FILE_AUTORIZATIONS);
    return inv.get(id);
  }


  public static void setRegistration(String key1, String key2, String val2, String fname) {
    InventoryModel inv = new InventoryModel(fname);
    inv.Add(key1, key2, val2);
    inv.save();
    inv.view();
  }
}
