package Lists;

import ProblemDomain.Ingredient;
import ProblemDomain.Inventory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author havardb
 * A help-class to handle the SQL queries to print out inventory from the database.
 */
public class Inventory_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private PreparedStatement line = null;
    private ResultSet res = null;
    public String sqlGetInventory = "select * from inventory";
    
    /**
     * 
     * @return A complete list of a inventory from the database.
     */
    public List getInventory(){
        List<Inventory> list = new ArrayList<Inventory>();
        try{
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlGetInventory);
            res = line.executeQuery();
            while(res.next()){
                Inventory inventory = new Inventory();
                inventory.type = res.getString("type");
                inventory.inventory_id = res.getInt("inventory_id");
                list.add(inventory);
            }
        }catch(SQLException e){
            db.WriteMessage(e, "getInventory()");
        }finally{
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
        return list;
    }
}
