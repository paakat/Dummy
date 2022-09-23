package app.menus.product.item;

import app.tools.Generic;
import app.tools.LMapStringString;
import app.tools.LMapStringLMapStringString;

import app.menus.product.statistics.StatisticsModel;


public class ItemModelEmpty {

  static public ItemModel create(String id, String name, String price, String cost) {

    ItemModel i = new ItemModel();
    i.setId(id);
    i.setName(name);
    i.setPrice(price);
    i.setCost(cost);
    i.save();
    return i;
  }


}
