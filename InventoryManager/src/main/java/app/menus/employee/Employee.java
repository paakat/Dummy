package app.menus.employee;

import app.tools.LMapIntegerString;
import app.tools.LMapStringString;
import app.tools.RecursiveScanner;
import app.tools.Generic;

import app.menus.Names;
import app.menus.Controller;

import app.menus.administrator.AdministratorController;


public class Employee extends Controller
{

  String BACK = null;
  String MYSELF = null;  


  public Employee(String myself, String back) {
    SetName(myself);
    MYSELF = myself;  
    BACK = back; 
  }


  public LMapIntegerString Model() {
    options = new LMapIntegerString(); 
    options.SetName(MYSELF); 

    Integer i=0;
    options.Put(i++, BACK);

    for(String v : find().keySet()) options.Put(i++,v);

    Generic.PrintDebug("[Model] '"+ GetName() +"' ...");
    return options; 
  } 

  @Override
  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner) {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");

    if(optionChosen.equals(BACK)) {} 
    else {
      optionChosen = login(optionChosen,rScanner); 
    }

    BottomMessage(rScanner);
    return optionChosen;
  }


  String login(String optionChosen, RecursiveScanner rScanner) {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+GetName()+"' .");
    if(optionChosen.equals("0")) return BACK; 

    System.out.println("[Employee] name:" + find().get(optionChosen) ); 

    String correct = rScanner.GetNextLine("y/n:");
    if(correct.replaceAll("\\s","").length() < 1) {
      System.out.println("\t[Employee] '"+ correct +"' no valid (y/n)!");
      return MYSELF;
    }

    if(correct.equals("y") || correct.equals("n")) {}
    else {
      System.out.println("\t[Employee] '"+ correct +"' no valid (y/n)!");
      return MYSELF;
    }

    if(correct.equals("y")) { 
      Names.LEVEL = MYSELF; //.toUpperCase();  
      return Names.EMPLOYEE; 
    }

    return optionChosen;
  }


  LMapStringString find() {
    return AdministratorController.get(MYSELF);
  }
}
