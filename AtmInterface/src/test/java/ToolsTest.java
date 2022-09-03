package app.tools;

// pow.xml : org.junit.jupiter, org.junit
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class ToolsTest 
{
  @DisplayName("ToolsTest_LMapIntegerString")
  @Test
  void Case01()                 
  {
    LMapIntegerString d = new LMapIntegerString();
    d.SetName("dicName"); 
    d.Put(1,"a");
    d.Put(2,"b"); 
    d.Put(2,"c"); // [LinkedHashMapExtended] '2' EXIST IN DIC 'dicName'  
    assertEquals("b", d.Get(2));

    d.Replace(1,"x"); 
    assertEquals("x", d.Get(1));

    d.Remove(1); 
    assertEquals(1, d.size());
  }

} 
