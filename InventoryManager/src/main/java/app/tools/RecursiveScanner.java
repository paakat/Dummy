package app.tools;

import app.tools.Generic;
import app.tools.LMapIntegerString;

import java.util.Scanner;


public class RecursiveScanner
{
  RecursiveScannerSpecialCases specialCases = null;

  static Integer iscanner = 0;

  static String chosen = null;
  static Scanner scanner = null;
  LMapIntegerString options = null;


  public RecursiveScanner()
  {
    scanner = new Scanner(System.in);
    specialCases = new RecursiveScannerSpecialCases(); 
  }

  
  public void SetOptions(LMapIntegerString options)
  {
    this.options = options; 
  }


  public String GetChosen()
  {
    return chosen; 
  }


  public String Execute() 
  {
    TopMessage();

    options.Print();
    System.out.print(Generic.SetGreen("Option: "));

    String nextLine = GetNextLine(); 
    Generic.PrintDebug("[GetNextLine] -> nextLine("+ iscanner +"):'"+ nextLine +"' ");

    chosen = null;
    if(IsInteger(nextLine))
    {
      chosen = options.Get( Integer.parseInt(nextLine) );
      Generic.PrintDebug("[RecursiveScanner]  '"+ nextLine +"' -> '"+ chosen +"'   ");
      if(chosen != null) return chosen;
    }

    iscanner++;
    boolean hasNext = scanner.hasNext();
    if(hasNext)
    {
      chosen = Execute(); 
    }

    return chosen;
  }


  boolean IsInteger(String input)
  {
    boolean exec = true;

    try
    {
      Integer.parseInt(input);
    }
    catch(Exception e)
    {
      Generic.PrintDebug("[IsInteger] 'Integer.parseInt' fails with input:'" + input +"' !");
      exec = false;
    }

    return exec;
  }


  String GetNextLine()
  {
    String nextLine = null;

    try
    {
      nextLine = scanner.nextLine();
      Generic.PrintDebug("[GetNextLine] nextLine:'"+ nextLine +"' ");
    }
    catch(Exception e)
    {
      Generic.PrintDebug("[GetNextLine] 'scanner.nextLine' fail!!");
      nextLine = "exit";
    }

    specialCases.Execute(nextLine);

    return nextLine;
  }


  public String GetNextLine(String text)
  {
    System.out.print(Generic.SetGreen(text));  
    return GetNextLine();
  }


  void TopMessage()  
  {
    Generic.ClearConsoleUnix();
    System.out.println(Generic.SetYellow("\nINVENTORY MANAGER"));
  }
}
