package app.menus.product;

//import app.tools.LMapStringString;
import app.tools.LMapIntegerString;
import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Names;
import app.menus.Controller;
import app.menus.product.item.ItemController; 

import app.menus.inventory.InventoryController;


public class ProductModify extends Controller {

  public static final String DISPLAY = "display";
  public static final String AMOUNT = "amount";
  public static final String PRICE = "price";
  public static final String COST = "cost";
  public static final String NAME = "name";
  public static final String TYPE = "type";


  String itemId = null;

  public ProductModify(String itemId) {
    super();

    this.itemId = itemId; 
    SetName(itemId); 
  }


  public LMapIntegerString Model() {
    options = new LMapIntegerString(); 
    options.SetName(itemId); 

    Integer i=0; 
    options.Put(i++, ProductRunners.PRODUCT_LIST);
    options.Put(i++, DISPLAY);
    options.Put(i++, PRICE);
    options.Put(i++, COST);

    Generic.PrintDebug("[Model] '"+ GetName() +"' ...");
    return options; 
  }


  @Override
  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner)
  {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");

    ItemController item = new ItemController(itemId);
    switch(optionChosen)
    {
      case(DISPLAY) -> item.view();  
      case(PRICE) -> modify(PRICE,item,rScanner); 
      case(COST) -> modify(COST,item,rScanner); //item.replaceDouble(COST,rScanner);  
    }
    item.save(); 

    BottomMessage(rScanner);
    return optionChosen;
  }


  void modify(String type, ItemController item, RecursiveScanner rScanner) {
    item.replaceDouble(type,rScanner);
    InventoryController.setRegistration(itemId,Generic.getNow(),type,itemId+".reg"); 
  }

}
