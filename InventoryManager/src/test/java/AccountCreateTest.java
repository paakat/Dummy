package app.account;

// pow.xml : org.junit.jupiter, org.junit
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class AccountCreateTest 
{
  @DisplayName("AccountCreateTest_Case01")
  @Test
  void Case01()                 
  {
    AccountCreate a = new AccountCreate(); 
    a.SetName("my name is");
    a.SetUser("user");
    a.SetPass("pass");
    a.SetCash("cash"); // [SetCash] 'cash' no valid. Try again...
    a.SetCash("0.0"); 
    a.Replace("1.0");
    a.Print();

    assertEquals("1.0", a.GetAccount().Get(AccountNames.CASH) );

    a.Save();
  }


  @DisplayName("AccountCreateTest_Case02")
  @Test
  void Case02()
  {
    String hash = "d74ff0ee8da3b9806b18c877dbf29bbde5b5bd8e4dad7a3a7250feb82e8f1"; 

    AccountFind a = new AccountFind(); 
    a.LoadList("./"); 

    assertTrue( a.Exist("user") ); 

    assertEquals("my name is", a.GetAccount("user").Get(AccountNames.NAME) ); 

    boolean equals = hash.equals( a.GetAccount("user").Get(AccountNames.PASS) );
    assertTrue(equals);
  }


} 
