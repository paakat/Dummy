package app.menus.inventory;

import app.tools.Generic;
import app.tools.LMapStringString;
//import app.tools.LMapStringLMapStringString;

import app.menus.Names;
import app.menus.product.item.ItemModel;

import app.menus.product.statistics.StatisticsModel;


public class InventoryController {


  static public boolean create(String id, String name, String price, String cost) {

    ItemModel i = new ItemModel();
    i.setId(id);
    i.setName(name);
    i.setPrice(price);
    i.setCost(cost);
    i.save();

    setAvailability(id,"1",name);
    setAmounts(name,1.0); 
    setRegistration(id,Generic.getNow(),"aggregated",id+".reg");  
    System.out.println("\tproduct:'"+id+"' added!");

    return false; 
  }


  static public void view() {
    InventoryModel inv = new InventoryModel(Names.FILE_INVENTORY);
    inv.view();
  }


  static public void remove(String id) {
    setAvailability(id,"0");
    setAmounts(getParent(id),-1.0);
    setRegistration(id,Generic.getNow(),"removed",id+".reg");
    System.out.println("\tproduct:'"+id+"' removed!"); 
  }


  static public void back(String id) {
    setAvailability(id,"1");
    setAmounts(getParent(id),1.0);
    setRegistration(id,Generic.getNow(),"returned",id+".reg");
    System.out.println("\tproduct:'"+id+"' returned!"); 
  }


  static public boolean exist(String id) {
    InventoryModel inv = new InventoryModel(Names.FILE_INVENTORY);
    return inv.exist(id);   
  }


  static public String getParent(String child) {
    InventoryModel inv = new InventoryModel(Names.FILE_INVENTORY);
    return inv.WhereKey2(child);
  }


  static public LMapStringString get(String id) {
    InventoryModel inv = new InventoryModel(Names.FILE_INVENTORY);
    return inv.get(id);
  }


  static void setAvailability(String id, String availability, String name) { 
    InventoryModel inv = new InventoryModel(Names.FILE_INVENTORY);
    inv.Add(name,id,availability);
    inv.save();
    inv.view();
  }


  static void setAvailability(String id, String availability) {
    InventoryModel inv = new InventoryModel(Names.FILE_INVENTORY);

    String name = inv.WhereKey2(id);
    Generic.PrintDebug("[setAvailability] '"+id+"' --> '"+ name +"' ");

    inv.Add(name,id,availability);
    inv.save();
    inv.view();
  }


  static void setAmounts(String id, Double value) {
    StatisticsModel amounts = new StatisticsModel(Names.FILE_AMOUNTS);
    amounts.sum(id,value);  
    amounts.save();
    amounts.view();
  }


  public static void setRegistration(String key1, String key2, String val2, String fname) {
    InventoryModel inv = new InventoryModel(fname);  
    inv.Add(key1, key2, val2);
    inv.save();
    inv.view();
  }
}
