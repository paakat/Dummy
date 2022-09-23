package app.menus.administrator;

import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Names;



public class AdministratorOperations 
{
  static final String MYSELF = Names.ADMINIS; 


  static String creating(RecursiveScanner rScanner) {
    String  id = rScanner.GetNextLine("id:");
    if(!Generic.tryStringToInteger(id)) return MYSELF; 

    id = String.format("%04d",Integer.parseInt(id));
    String type = AdministratorController.getParent(id); 
    if(type != null) {
      System.out.println("\t[administrator.create] '"+id+"' exist !");
      return MYSELF;
    }

    String  name = rScanner.GetNextLine("name:");
    if(name.replaceAll("\\s","").length() < 1) {
      System.out.println("\t[administrator.create] '"+name+"' no valid !");
      return MYSELF;
    }

    String  typeNew = rScanner.GetNextLine("type:");
    if(typeNew.equals("cashier") || typeNew.equals("manager")) {}
    else {
      System.out.println("\t[administrator.create] '"+ typeNew +"' no valid (use 'cashier' or 'manager') !");
      return MYSELF;
    }

    AdministratorController.create(id, name, typeNew); 
    System.out.println("\t[administrator.create] '"+ id +"' created!");

    return MYSELF;
  }

}
