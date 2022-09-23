package app.menus.product.statistics;

//import app.menus.Names;

import app.tools.LMapStringDouble;
import app.tools.Generic;
import app.tools.io.Serializing;


public class StatisticsModel extends Model { 


  public StatisticsModel(String name) {
    super(name); 
  }


  public void sum(String key, String value) {
    Double amount = Generic.String2Double(value);
    sum(key,amount);
  }


  public void sum(String key, Double amount) {
    if(stats.Exits(key)) {
      stats.Replace(key, stats.Get(key) + amount); 
    } 
    else {
      stats.Put(key, amount); 
    }
  }


  public void save() 
  {
    save( stats.GetName() ); 
  }

}
