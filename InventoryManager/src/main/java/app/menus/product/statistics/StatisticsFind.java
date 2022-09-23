package app.menus.product.statistics;

import app.tools.Generic;


//import app.menus.Names;
import app.tools.LMapIntegerString;
import app.tools.LMapStringDouble;

import app.tools.io.DeSerializing;
import app.tools.io.FilesTools;


public class StatisticsFind {

  LMapStringDouble founds = null;


  public StatisticsFind(String name) {
    founds = new LMapStringDouble(); 
    founds.SetName(name);
  }


  void getList(String path, String type) {
    LMapIntegerString filesName = FilesTools.GetFilesName(path,type);

    for(Integer id : filesName.keySet()) {
      String fname = filesName.Get(id);
      if( FilesTools.CouldBeOpen(fname) ) {
        String k = fname.substring(fname.lastIndexOf('/')+1);
        k = k.replace(type,""); 
        founds.Put(k,1.0);
      }
    }
  }


  public boolean exist(String fname)
  {
    return founds.Exits(fname);
  }


  public LMapStringDouble get(String fname, String type)
  {
    return new DeSerializing<LMapStringDouble>(fname+type).GetObj();
  }


}
