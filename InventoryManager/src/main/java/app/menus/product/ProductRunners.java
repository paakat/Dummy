package app.menus.product;

import app.tools.LMapStringRunnable;

import app.tools.LMapIntegerString;
import app.menus.Controller;
import app.menus.Names;


import app.menus.product.item.ItemFind;


public class ProductRunners {

  public static final String PRODUCT = "product";
  public static final String PRODUCT_ADD = "productAdd";
  public static final String PRODUCT_LIST = "productList";
  public static final String PRODUCT_REMOVE = "productRemove";


  static LMapStringRunnable CreateRunners() {
    LMapStringRunnable r = new LMapStringRunnable();
    r.SetName("ProductRunners");
    r.Put(PRODUCT, new Product());
    r.Put(PRODUCT_ADD, new ProductAdd());
    r.Put(PRODUCT_LIST, new ProductList());
    r.Put(PRODUCT_REMOVE, new ProductRemove());

    for(String v : find().values()) r.Put(v, new ProductModify(v)); 

    return r; 
  }


  static LMapIntegerString find() {
    return new ItemFind().getKeys();
  }


  static public LMapStringRunnable Append(LMapStringRunnable rs) {

    LMapStringRunnable result = new LMapStringRunnable();
    result.SetName(rs.GetName());

    for(String key : rs.keySet()) result.Put(key, rs.Get(key));

    LMapStringRunnable runners = CreateRunners();
    for(String key : runners.keySet()) result.Put(key, runners.Get(key));

    return result;
  }

}
