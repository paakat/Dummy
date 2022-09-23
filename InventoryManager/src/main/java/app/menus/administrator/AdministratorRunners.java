package app.menus.administrator;

import app.tools.LMapStringRunnable;

//import app.tools.LMapIntegerString;
import app.menus.Names;
import app.menus.Controller;


public class AdministratorRunners {

  static final String MYSELF = Names.ADMINIS;


  static LMapStringRunnable CreateRunners() {
    LMapStringRunnable r = new LMapStringRunnable();
    r.SetName("AdministratorRunners");
    r.Put(MYSELF, new Administrator());

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
