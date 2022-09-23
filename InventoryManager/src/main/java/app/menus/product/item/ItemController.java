package app.menus.product.item;

import app.tools.Generic;
import app.tools.RecursiveScanner;
import app.tools.LMapStringString;
import app.tools.io.Serializing;


public class ItemController extends Controller {


  public ItemController() {
    super(); 
    item = ItemModelEmpty.create("0000","name0000","0.0","0.0").get();
  }


  public ItemController(String key) {
    super(key);
  }


  public ItemController(LMapStringString item) {
    super(item); 
  }


  public void replaceStrings(String key, RecursiveScanner rScanner)
  {
    view();
    replaceStrings(key, SetData(key+":",rScanner)); 
    view();
  }


  public Double replaceDouble(String key, RecursiveScanner rScanner) {
    view();    
    Double output = replaceDouble(key, SetData(key+":",rScanner)); 
    view();    
    return output; 
  }


  public Integer replaceInteger(String key, RecursiveScanner rScanner) {
    view();   
    String input = SetData(key+":",rScanner);  
    Integer output = replaceInteger(key,input);
    view();   
    return output;  
  }


  static String SetData(String text, RecursiveScanner rScanner)
  {
    String inputa = null;
    inputa = rScanner.GetNextLine(text);
    return inputa;
  }

} 
