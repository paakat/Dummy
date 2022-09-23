package app.menus.product.item;

import app.menus.Names;

import app.tools.find.FindLMapStringLMapStringString; 



public class ItemFind extends FindLMapStringLMapStringString {

  public ItemFind() {
    super("Items"); 
    getList(Names.PATH,"id",".ser");
  }

}
