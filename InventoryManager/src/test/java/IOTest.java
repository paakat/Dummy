package app.tools;

// pow.xml : org.junit.jupiter, org.junit
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import app.tools.io.Serializing;
import app.tools.io.DeSerializing;


public class IOTest 
{
  String fname = "ioTestDeSerializing.ser";

  @DisplayName("IOTest_DeSerializing")
  @Test
  void Case01()                 
  {
    LMapStringString s = new LMapStringString();
    s.SetName("dicName"); 
    s.Put("1","a");
    s.Put("2","b"); 
    s.Put("3","c");

    new Serializing<LMapStringString>(s,fname);

    LMapStringString d = new DeSerializing<LMapStringString>(fname).GetObj();
    assertEquals(s.Get("2"), d.Get("2"));
  }
} 
