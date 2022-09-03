package app.tools;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Generic  
{

  public static boolean TryStringToInteger(String text) 
  {
    boolean result = true;
 
    try
    {
      Integer.parseInt(text);
    }
    catch(Exception e)
    {
      result = false; 
    }
    
    return result; 
  }


  public static String RemoveExtraSpaces(String text)
  {
    return text.trim().replaceAll("\\s+"," ");
  }


  public static void PrintDebug(String text)
  {
    String toprint = SetRed("  [DEBUG] ");
    toprint += SetBlue(text); 

    if(System.getProperty("DEBUG")!=null) System.out.println(toprint);
  }


  public static
  boolean CheckPattern(String str, String pattern)  
  {
    Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE); 
    Matcher m = p.matcher(str);
    return m.find(); 
  }


  public static
  void ClearConsoleUnix()
  {
    if(System.getProperty("DEBUG")==null) 
    {
      System.out.print("\033[H\033[2J");
      System.out.flush();
    } 
  }


  public static
  void Exit(boolean trigger, String text)
  {
    if(trigger)
    {
      System.out.println( SetGreen(text) );
      System.out.println( SetGreen("Exit!\n") );
      System.exit(1);
    } 
  }


  public static
  String SetGreen(String text)
  {
    return ANSI_GREEN + text + ANSI_RESET;
  }


  public static
  String SetBlue(String text)
  {
    return ANSI_BLUE + text + ANSI_RESET;
  }


  public static
  String SetRed(String text) 
  {
    return ANSI_RED + text + ANSI_RESET;
  }


  public static
  String SetYellow(String text)
  {
    return ANSI_YELLOW + text + ANSI_RESET;
  }


  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
}
