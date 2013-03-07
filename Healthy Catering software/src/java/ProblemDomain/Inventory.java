package ProblemDomain;
/**
 *
 * @author havardb
 */
public class Inventory {
public int inventory_id;
public String type;

    public String getType() {
        return type;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public void setType(String type) {
        this.type = type;
    }


}
