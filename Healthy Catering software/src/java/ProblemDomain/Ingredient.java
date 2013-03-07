package ProblemDomain;
/**
 *
 * @author havardb
 */
public class Ingredient {
public int ingridient_id;
public double price;
public int inventory_id;

    public double getPrice() {
        return price;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public int getIngridient_id() {
        return ingridient_id;
    }

    public void setIngridient_id(int ingridient_id) {
        this.ingridient_id = ingridient_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
}
