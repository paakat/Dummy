package app.menus.product;

import app.tools.Generic;
import app.tools.LMapIntegerString;

import app.menus.Names;
import app.menus.Controller;


public class Product extends Controller {


  public Product() {
    SetName(Names.PRODUCT); 
  }


  public LMapIntegerString Model() {
    options = new LMapIntegerString(); 
    options.SetName(ProductRunners.PRODUCT); 

    Integer i=0; 
    options.Put(i++, Names.HOME);
    options.Put(i++, ProductRunners.PRODUCT_LIST);

    if(Names.LEVEL == Names.CASHIER) {} 
    else {
      options.Put(i++, ProductRunners.PRODUCT_ADD);
      options.Put(i++, ProductRunners.PRODUCT_REMOVE);
    }

    Generic.PrintDebug("[Model] '"+ GetName() +"' ...");
    return options; 
  } 
}
