package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 *
 * @author espen
 */
public class Menus_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    private String sqlGetMenus = "SELECT * FROM menus order by type_id";
    private String sqlGetMenus2 = "SELECT * FROM menus where menu_id=? order by type_id";
    private int menu_id = -1;

    public Menus_List(int menu_id) {
        this.menu_id = menu_id;
    }

    public Menus_List() {
    }

    ;
    /** 
     * Menu_id = 0 ,If you have not chosen a menu from your order history.
     * Menu_id != 0 ,If you have chosen a menu from your order history. 
     */
    public List buildMenuList() {
        List<Menus> list = new ArrayList<Menus>();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlGetMenus);
            res = line.executeQuery();
            while (res.next()) {
                Menus menu = new Menus();
                menu.menu_id = res.getInt("menu_id");
                line = db.getConnection().prepareStatement("select sum(price) as sum from course where menu_id=?");
                line.setInt(1, menu.menu_id);
                ResultSet res2 = line.executeQuery();
                double sum = 0;
                while (res2.next()) {
                    //userNormal = normal price, userCompany = normal price  * VAT, else normal price (not logged in)
                    if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userNormal")) {
                        sum = res2.getDouble("sum") * 1.25;
                    } else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userCompany")) {
                        sum = res2.getDouble("sum");
                    } else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("management")) {
                        sum = res2.getDouble("sum");
                    } else {
                        sum = res2.getInt("sum") * 1.25;
                    }
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

    public Menus getMenus() {
        Menus temp = new Menus();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlGetMenus2);
            line.setInt(1, this.menu_id);
            res = line.executeQuery();
            while (res.next()) {
                temp.menu_id = res.getInt("menu_id");
                line = db.getConnection().prepareStatement("select sum(price) as sum from course where menu_id=?");
                line.setInt(1, temp.menu_id);
                ResultSet res2 = line.executeQuery();
                double sum = 0;
                while (res2.next()) {
                    //userNormal = normal price, userCompany = normal price  * VAT
                    if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userNormal")) {
                        sum += res2.getInt("sum") * 1.25;
                    } else {
                        sum += res2.getInt("sum");
                    }
                }
                temp.name = res.getString("name");
                temp.type = res.getString("type");
                temp.sum = sum;
            }
        } catch (SQLException e) {
            System.out.println("Feil i buildMenuList() " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return temp;
    }
}
