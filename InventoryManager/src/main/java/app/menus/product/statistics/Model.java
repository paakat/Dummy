package app.menus.product.statistics;

import app.tools.Generic;
import app.tools.LMapStringDouble;

import app.tools.io.Serializing;
import app.tools.io.DeSerializing;
import app.tools.io.FilesTools;

import app.menus.Names;


class Model { 

  LMapStringDouble stats = null; 


  public Model(String name) {
    if(FilesTools.CouldBeOpen(name)) {
      stats = new DeSerializing<LMapStringDouble>(name).GetObj();
      Generic.PrintDebug("[inventory.Model] '"+name+"' open...");
    }
    else {
      stats = new LMapStringDouble();
      stats.SetName(name);
      Generic.PrintDebug("[inventory.Model] '"+name+"' created...");
    }
  }


  public void view() {
    stats.Print();
  }


  public LMapStringDouble get() {
    return stats;
  }


  public void save(String fname) {
    try {
      new Serializing<LMapStringDouble>(stats, fname);
    }
    catch(Exception e) {
      System.out.println("\t[Save] '" + Generic.SetGreen(fname) +"' no valid. Try again...");
    }

    Generic.PrintDebug("[inventory.save] '"+ fname +"' saved...");
  }

}
