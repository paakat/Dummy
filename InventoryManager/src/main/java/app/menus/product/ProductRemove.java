package app.menus.product;

import app.tools.Generic;
import app.tools.io.FilesTools;
import app.tools.LMapStringString;
import app.tools.LMapIntegerString;
import app.tools.RecursiveScanner;

import app.menus.Names;
import app.menus.Controller;
import app.menus.product.item.ItemFind;

import app.menus.inventory.InventoryController; 


public class ProductRemove extends Controller {


  public ProductRemove() {
    SetName(ProductRunners.PRODUCT_REMOVE); 
  }


  public LMapIntegerString Model() {
    options = new LMapIntegerString(); 
    options.SetName(ProductRunners.PRODUCT_REMOVE); 

    Integer i = 0; 
    options.Put(i++, ProductRunners.PRODUCT);
    for(String v : find().values()) options.Put(i++,v);   

    Generic.PrintDebug("[Model] '"+ GetName() +"' ...");
    return options; 
  } 


  @Override
  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner)
  {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");

    switch(optionChosen)
    {
      case(ProductRunners.PRODUCT) -> {}  
      default -> optionChosen = remove(optionChosen); 
    }

    BottomMessage(rScanner);
    return optionChosen;
  }


  LMapIntegerString find() {
    return new ItemFind().getKeys();
  }


  String remove(String key) {
    InventoryController.remove(key);

    new ItemFind().get(key).Print();

    FilesTools.RemoveFile(key + ".ser");

    System.out.println("\t[ProductRemove] '" + Generic.SetGreen(key) +"' removed! ");
    return ProductRunners.PRODUCT_REMOVE; 
  }

}
