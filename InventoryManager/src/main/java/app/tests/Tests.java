package app.tests; 

import app.tools.Generic;
import app.tools.io.FilesTools;

import app.menus.Names;
import app.menus.product.item.Item;
import app.menus.product.item.ItemModelEmpty; 

import app.menus.inventory.InventoryController; 
import app.menus.administrator.AdministratorController;


public class Tests {


  public Tests() {
    TestsInventory(); 
    TestsAuthorizations(); 
  }


  static void TestsInventory() {
    createItems();
    removeItems(); 
    backItems();  
  }



  static void createItems() {
    Generic.PrintDebug("[CreateItems] ...");

    FilesTools.RemoveFile(Names.FILE_INVENTORY);   
    FilesTools.RemoveFile(Names.FILE_AMOUNTS);
    FilesTools.RemoveFile(Names.FILE_PRICES);


    InventoryController.create("0001","name0001","1.0","1.0");
    InventoryController.create("0010","name0010","1.0","1.0");
    InventoryController.create("1001","name1000","1.0","1.0");
    InventoryController.create("1002","name1000","1.0","1.0");
    InventoryController.create("0100","name0100","1.0","1.0");
    InventoryController.create("0101","name0100","1.0","1.0");
    InventoryController.create("0102","name0100","1.0","1.0");

    Generic.PrintDebug("[CreateItems] OK!");
  }


  static void removeItems() {
    Generic.PrintDebug("[RemoveItems] ...");
    InventoryController.remove("0010"); 
    InventoryController.remove("0101");

    Generic.PrintDebug("[RemoveItems] OK!");
  }


  static void backItems() {
    Generic.PrintDebug("[BackItems] ...");
    InventoryController.back("0010");

    Generic.PrintDebug("[BackItems] OK!");  
  }


  static void TestsAuthorizations() {
    createAuthorizations(); 
  }


  static void createAuthorizations() {

    //FilesTools.RemoveFile(Names.FILE_AUTORIZATIONS);

    AdministratorController.create("0001","name1","administrator");
    AdministratorController.create("0004","name1","cashier"); 
    AdministratorController.create("0003","name2","cashier");
    AdministratorController.create("0002","name3","manager");
    AdministratorController.view(); 
  }


}
