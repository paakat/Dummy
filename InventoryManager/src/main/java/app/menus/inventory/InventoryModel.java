package app.menus.inventory;

import app.tools.LMapStringString;


public class InventoryModel extends Model { 


  public InventoryModel(String name) {
    super(name);
  }


  public void Add(String key1, String key2, String val2) {
    if(obj.Exits(key1)) {
    }
    else {
      obj.Put(key1, new LMapStringString(key1));
    }

    if(obj.Get(key1).Exits(key2)) {
      obj.Get(key1).Replace(key2,val2);
    }
    else { 
      obj.Get(key1).Put(key2,val2);
    }
  }


  public String WhereKey2(String key2) {

    String found = null;

    for(String key1 : obj.keySet()) {
      if(obj.Get(key1).Exits(key2)) {
        found = key1;
      }
    }

    return found;
  }


  public void save() {
    save( obj.GetName() );  
  }

}
