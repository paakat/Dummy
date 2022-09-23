package app.menus.inventory;

import app.tools.LMapStringString;
import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Names;
import app.menus.Controller;


public class InventoryOperations {


  public static void selling(RecursiveScanner rScanner) { 
    String input = SetData("product name:",rScanner); 
  
    LMapStringString obj = null;  
    if(InventoryController.exist(input)) obj = InventoryController.get(input);  
    else System.out.println("[selling] '"+ input +"'  no found!"); 

    Integer sum = 0;
    if(obj != null) {
      obj.Print();
      for(String key : obj.keySet()) sum += Integer.parseInt( obj.Get(key) ); 
    }

    if(sum < 2) System.out.println("[selling] '"+ input +"' cannot be sold!");  
    else { 
      input = SetData("product id:",rScanner);
      if(obj.Exits(input)) {
        if(obj.Get(input).equals("1")) InventoryController.remove(input);  
        else System.out.println("[selling] '"+ input +"' does not exist!");
      } 
      else System.out.println("[selling] '"+ input +"'  no found!"); 
    }
  }


  public static void returning(RecursiveScanner rScanner) { 
    String id = SetData("product id:",rScanner);

    String name = InventoryController.getParent(id); 
    if(name == null) System.out.println("[selling] '"+ id +"' does not exist!");
    else {
      if(InventoryController.get(name).Get(id).equals("1")) {
        System.out.println("[selling] '"+ id +"' cannot be returned becuase it exist!"); 
      }
      else InventoryController.back(id);
    } 
  }


  public static String SetData(String text, RecursiveScanner rScanner) {
    String inputa = null;
    inputa = rScanner.GetNextLine(text);
    return inputa;
  }

}
