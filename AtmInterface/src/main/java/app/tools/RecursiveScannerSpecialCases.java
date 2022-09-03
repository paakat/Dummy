package app.tools;

import app.tools.Generic;

import java.util.LinkedHashMap;


class RecursiveScannerSpecialCases
{
  private LinkedHashMap<String,Runnable> executeByString = null;


  public RecursiveScannerSpecialCases() 
  {
    executeByString = new LinkedHashMap<>();
    executeByString.put("q", ()-> System.out.println("[Controller] 'q' Selected ...") );
    executeByString.put("exit", ()-> Generic.Exit(true,"[Controller] Exit...") );
  }


  public boolean Execute(String input)
  {
    boolean exec = executeByString.containsKey(input);
    if(exec) executeByString.get(input).run();
    return exec; 
  }

}
