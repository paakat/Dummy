package app.tools;

import app.tools.Generic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class HashValues
{
  public static String Create(String value)
  {
    MessageDigest hash;
    try {
      hash = MessageDigest.getInstance("SHA-256"); 
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }

    hash.reset();
    byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
    hash.update(bytes);
    byte[] d = hash.digest();
    //System.out.println("digest:'"+ d +"' "); 

    StringBuffer hexString = new StringBuffer();
    for (int i = 0;i<d.length;i++) hexString.append(Integer.toHexString(0xFF & d[i]));
    //System.out.println("hexString:'"+ hexString +"' ");

    return hexString.toString();  
  }
}
