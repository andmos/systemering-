
package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 *
 * @author
 * espen
 */
public class Menus_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    private String sqlConstructor = "SELECT * FROM menus order by type_id";


    /** 
     *Menu_id = 0 ,If you have not choosen a menu from your order history.
     *Menu_id != 0 ,If you have choosen a menu from your order history. 
     */
    public List buildMenuList() {
        List<Menus> list = new ArrayList<Menus>();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlConstructor);
            res = line.executeQuery();
            while (res.next()) {
                Menus menu = new Menus();
                menu.menu_id = res.getInt("menu_id");
                line = db.getConnection().prepareStatement("select sum(price) as sum from course where menu_id=?");
                line.setInt(1, menu.menu_id);
                ResultSet res2 = line.executeQuery();
                double sum = 0;
                while (res2.next()){
                    sum += res2.getInt("sum");
                }
                menu.total_price = res.getInt("total_price");
                menu.name = res.getString("name");
                menu.type = res.getString("type");
                menu.sum = sum;
                list.add(menu);
            }
        } catch (SQLException e) {
            System.out.println("Feil i buildMenuList() " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;
    }
    /**
     * 
     * @return returns the meny list. 
     */
    public List getMenu() {
        List<Menus> list = buildMenuList();
        return list; //.size()>0 ? list : null;
    }
}
