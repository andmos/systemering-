package Lists;

import ProblemDomain.Ingredient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author
 * havardb
 */
public class Ingredient_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private PreparedStatement line = null;
    private ResultSet res = null;
    public String sqlGetIngredients = "select * from ingredient where inventory_id=?";
    
    public List getIngredients(int inventory_id){
        List<Ingredient> list = new ArrayList<Ingredient>();
        try{
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlGetIngredients);
            line.setInt(1,inventory_id);
            res=line.executeQuery();
            while(res.next()){
                Ingredient ingredient = new Ingredient();
                ingredient.ingridient_id = res.getInt("ingredient_id");
                ingredient.inventory_id = res.getInt("inventory_id");
                ingredient.name = res.getString("name");
                ingredient.price = res.getDouble("price");
                ingredient.quantity = res.getInt("quantity");
                list.add(ingredient);
            }
        }catch(SQLException e){
            System.out.println("Failure in getIngredients()"+e.getMessage());
        }finally{
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
        return list;
    }
}
