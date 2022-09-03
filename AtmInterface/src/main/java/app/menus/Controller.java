package app.menus;

import app.tools.Generic;
import app.tools.RecursiveScanner;
import app.tools.LMapIntegerString;

import java.util.Scanner;


public class Controller 
{
  static public LMapIntegerString options = null;

  String name = null;


  public void SetName(String name)
  {
    this.name = name;
  } 


  public String GetName()
  {
    return name;
  }


  public LMapIntegerString CreateOptions()
  {
    return new LMapIntegerString(); 
  } 


  public String Recursive(RecursiveScanner rScanner) 
  {
    rScanner.SetOptions(options);
    rScanner.Execute();
    String optionChosen = rScanner.GetChosen();

    Generic.PrintDebug("[Recursive] chosen:'"+ optionChosen+"'... ");
    if(optionChosen == null){} 
    else
    {
      optionChosen = ChosenOptionFound(optionChosen, rScanner);
    }

    return optionChosen;
  }


  public String ChosenOptionFound(String optionChosen, RecursiveScanner rScanner)
  {
    Generic.PrintDebug("[ChosenOptionFound] '"+ optionChosen+"' found in '"+name+"' .");
    Generic.PrintDebug("                     Nothing to do...");
    return optionChosen;
  }


  public String SetData(String text, RecursiveScanner rScanner)
  {
    String inputa = null;
    inputa = rScanner.GetNextLine(text);
    Generic.PrintDebug("[SetData] Changing directory: '"+GetName()+"' -> '"+inputa+"' ");
    return inputa;
  }


  public void BottomMessage(RecursiveScanner rScanner) 
  {
    rScanner.GetNextLine("\nContinue ... ");
    Generic.ClearConsoleUnix();
  }

}
