package app.menus.product.item;


public class ItemModel extends Model {

  public ItemModel() {
    super("ItemModel"); 
  }

  public void setId(String value) {
    setInteger("id",value);
  }

  public void setName(String value) {
    item.Put("name",value);
  }

  public void setType(String value) {
    item.Put("type",value);
  }


  public void setAmount(String value) {
    setInteger("amount",value); 
  }

  public void setPrice(String value) {
    setDouble("price",value);
  }

  public void setCost(String value) {
    setDouble("cost",value);
  }

  public void save() {
    save("id"); 
  }

} 
