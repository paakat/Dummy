package app.menus.product.item;

import app.tools.Generic;
import app.tools.LMapStringString;
import app.tools.io.Serializing;

import app.menus.product.statistics.StatisticsModel;


public class Item  
{

  static public void unitTest(String id)
  {
    ItemModel itemModel = new ItemModel();
    itemModel.setId(id);
    itemModel.setName("name" + id);
    itemModel.setType("type" + id);
    itemModel.setAmount("1.0");
    itemModel.setAmount("1");
    itemModel.setPrice("-1.0");
    itemModel.setPrice("1.0");
    itemModel.setCost("a");
    itemModel.setCost("1.0");
    itemModel.view();
    itemModel.save();

    new ItemController();
  }


} 
