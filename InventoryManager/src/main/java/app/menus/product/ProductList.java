package app.menus.product;

//import app.tools.LMapStringString;
import app.tools.LMapIntegerString;
import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Names;
import app.menus.Controller;

import app.menus.product.item.ItemFind;

import app.menus.inventory.InventoryModel;  


public class ProductList extends Controller {


  public ProductList() {
    SetName(ProductRunners.PRODUCT_LIST); 
  }


  public LMapIntegerString Model() {
    options = new LMapIntegerString(); 
    options.SetName(ProductRunners.PRODUCT_LIST); 

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
      default -> view(optionChosen); 
    }

    BottomMessage(rScanner);
    return optionChosen;
  }


  LMapIntegerString find() {
    return new ItemFind().getKeys();
  }


  void view(String key) {
    new InventoryModel(key+".reg").view();
    new ItemFind().get(key).Print();
  }

}
