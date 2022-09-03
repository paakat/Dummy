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


public class HashValuesTest 
{

  @DisplayName("HashValuesTest_DeSerializing")
  @Test
  void Case01() 
  {
    String hash = "c074dd25c9de39b9c8d5ab452e8b69bccbf86f2a60ed7e527e79d0a335852"; 
    boolean equals = hash.equals( HashValues.Create("3.14159")  ); 
    assertTrue(equals); 
  }


} 
