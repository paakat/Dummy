package app.tools;

// Basics 
import java.util.stream.Collectors;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

// Tools 
import app.tools.Generic; 


public class LinkedHashMapExtended<KEY,VALUE> extends LinkedHashMap<KEY,VALUE> implements java.io.Serializable 
{
  private static final long serialVersionUID = (long)3.14159265359;

  public String name = null;


  public LinkedHashMapExtended()
  {
    super();
  }


  public LinkedHashMapExtended(String name)
  {
    super();
    SetName(name);
  }


  public void SetName(String name)
  {
    this.name = name;
  }


  public String GetName()
  { 
    return name;
  }


  public boolean Put(KEY key, VALUE value) 
  {
    Generic.PrintDebug("[LinkedHashMapExtended] name:'" + name +"' k:'" + key + "' v:'" + value +"' ");

    boolean done = false;

    if( Exits(key) )
    {
      Warning(key," EXIST IN DIC ");
    }
    else
    { 
      super.put(key,value); 
      done = true; 
    } 

    return done; 
  }


  public VALUE Get(KEY key) 
  {
    if( Exits(key) )
    {
      return super.get(key);
    }
    else
    {
      Warning(key, " NO FOUND IN DIC ");
    }

    return null;  
  }


  public boolean Remove(KEY key)
  {
    boolean done = false; 

    if( Exits(key) )
    {
      super.remove(key);
      done = true; 
    }
    else
    {
      Warning(key, " NO FOUND IN DIC ");
    }

    return done;
  }


  public void Replace(KEY key, VALUE valueNew)
  {
    if( Exits(key) )
    {
      super.replace(key,valueNew);
    }
    else
    {
      Warning(key, " NO FOUND IN DIC IN DIC");
    }
  }


  public void Modify(KEY key, VALUE value)
  {
    if( Exits(key) )
    {
      super.remove(key);
      super.put(key,value);
    }
    else
    {
      Warning(key, " NO FOUND IN DIC ");
    }
  }


  public boolean Exits(KEY key)
  {
    return super.containsKey(key);
  }


  public boolean ExistValue(VALUE value)
  {
    return super.containsValue(value); 
  }


  public void Warning(KEY key, String message)
  {
    System.out.println( Generic.SetRed("\t[LinkedHashMapExtended] '") + key + Generic.SetRed("'"+message+"'") + name + Generic.SetRed("' "));
  }


  public void Print()
  {
    Generic.PrintDebug("[LinkedHashMapExtended] '"+ name +"' ...");

    for(KEY key : super.keySet())
    {
      VALUE v = super.get(key);
      String k = Generic.SetGreen( key.toString() );
      System.out.printf("\t[%s] %s", Generic.SetBlue(name) , "key:'"+ k +"'; value:'"+ v +"' \n");  
    }
  }
 
}
