package app.menus.inventory;

import app.tools.LMapStringRunnable;

//import app.tools.LMapIntegerString;
import app.menus.Names;
import app.menus.Controller;


public class InventoryRunners {

  public static final String INVENTO = Names.INVENTO;


  static LMapStringRunnable CreateRunners() {
    LMapStringRunnable r = new LMapStringRunnable();
    r.SetName("InventorysRunners");
    r.Put(INVENTO, new Inventory());

    return r; 
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
