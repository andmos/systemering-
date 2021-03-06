package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author
 * havardb
 */
public class Ingredient {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private PreparedStatement line = null;
    private ResultSet res = null;
    public int ingridient_id;
    public int inventory_id;
    public String name;
    public int quantity;
    public String sqlAddQuantity = "update ingredient set quantity=quantity+? where name=? and inventory_id=?";
    public String sqlRemoveQuantity = "update ingredient set quantity=quantity-? where name=? and inventory_id=?";
    public String sqlAddIngredient = "insert into ingredient(name,quantity,inventory_id) values(?,?,?)";

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



    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Add a given quantity to a specific ingredient in a inventory.
     * @param inventory_id Specific inventory.
     * @return Error variable.
     */
    public boolean addQuantity(int inventory_id) {
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlAddQuantity);
            line.setInt(1, quantity);
            line.setString(2,name);
            line.setInt(3,inventory_id);
            line.executeUpdate();
            return true;
        } catch (SQLException e) {
            db.WriteMessage(e, "addQuantity()");
            return false;
        } finally {
            db.getConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }

    }
    /**
     * Remvoe a given quantity to a specific ingredient in a inventory.
     * @param inventory_id Specific inventory.
     * @return Error variable.
     */
    public boolean removeQuantity(int inventory_id) {
       try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlRemoveQuantity);
            line.setInt(1, quantity);
            line.setString(2,name);
            line.setInt(3,inventory_id);
            line.executeUpdate();
            return true;
        } catch (SQLException e) {
            db.WriteMessage(e, "removeQuantity()");
            return false;
        } finally {
            db.getConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
    }
    /**
     * Add a new ingredient to a specific inventory.
     * @param inventory_id Specific inventory.
     * @return Error variable.
     */
    public boolean addNewIngredient(int inventory_id){
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlAddIngredient);
            line.setString(1,name);
            line.setInt(2, quantity);
            line.setInt(3,inventory_id);
            line.executeUpdate();
            return true;
        } catch (SQLException e) {
            db.WriteMessage(e, "addNewIngredient()");
            return false;
        } finally {
            db.getConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
    }
}
