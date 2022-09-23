package app.menus.product;

import app.tools.LMapIntegerString;
import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Menus; 
import app.menus.Names;
import app.menus.Controller;

import app.menus.inventory.InventoryController;


public class ProductAdd extends Controller {


  public static final String CREATE = "create";


  public ProductAdd() {
    SetName(ProductRunners.PRODUCT_ADD);
  }


  public LMapIntegerString Model() {
    options = new LMapIntegerString();
    options.SetName(ProductRunners.PRODUCT_ADD);

    Integer i = 0;
    options.Put(i++, ProductRunners.PRODUCT);
    options.Put(i++, CREATE);

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
      case(CREATE) -> optionChosen = create(rScanner); 
    }

    BottomMessage(rScanner);
    return optionChosen;
  }


  String create(RecursiveScanner rScanner) {
    String    id = rScanner.GetNextLine("id:");
    if(!Generic.tryStringToInteger(id)) return ProductRunners.PRODUCT_ADD; 

    id = String.format("%04d",Integer.parseInt(id));
    if(InventoryController.getParent(id) != null) {  
      System.out.println("\t[product.create] '"+id+"' exist !");
      return ProductRunners.PRODUCT_ADD;
    }

    String  name = rScanner.GetNextLine("name:");
    if(name.replaceAll("\\s","").length() < 1) {
      System.out.println("\t[product.create] '"+name+"' no valid !");
      return ProductRunners.PRODUCT_ADD;  
    }

    String  cost = rScanner.GetNextLine("cost:");
    if(!Generic.tryStringToDouble(cost)) return ProductRunners.PRODUCT_ADD;

    String price = rScanner.GetNextLine("price:");
    if(!Generic.tryStringToDouble(price)) return ProductRunners.PRODUCT_ADD;

    InventoryController.create(id, name, price, cost);
    System.out.println("\t[product.create] '"+id+"' created!");   

    Menus.runners.Put(id, new ProductModify(id));

    return ProductRunners.PRODUCT;
  }

}
