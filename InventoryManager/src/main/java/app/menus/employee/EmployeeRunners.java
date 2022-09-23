package app.menus.employee;

import app.tools.LMapStringRunnable;

import app.menus.Names;


public class EmployeeRunners {

  public static final String INVENTO = Names.INVENTO;


  static LMapStringRunnable CreateRunners() {
    LMapStringRunnable r = new LMapStringRunnable();
    r.SetName("EmployeeRunners");
    r.Put( Names.CASHIER, new Employee(Names.CASHIER,Names.HOME));
    r.Put( Names.MANAGER, new Employee(Names.MANAGER,Names.HOME));
    r.Put(Names.EMPLOYEE, new EmployeeMenus());
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
