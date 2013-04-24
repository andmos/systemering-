package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;

/**
 *
 * @author
 * havardb
 */
public class Menus {

    public int menu_id;
    public int total_price;
    public String name;
    public String type;
    public int type_id;
    public Double sum;
    private PreparedStatement line = null;
    private ResultSet res = null;
    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    public String sqlChangeMenu = "update menus set name=?,type=? where menu_id=?";
    public String sqlCreateMenu = "insert into menus(name,type,type_id) values(?,?,?)";
    public String sqlGetMenuId = "select max(menu_id)+1 as menu_id from menus";
    public String sqlCheckName = "select name from menus where name=?";
    public String sqlGetName = "select name from menu where menu_id=?";
    public String sqlDeleteMenu = "delete from menus where menu_id=?";
    public String sqlDeleteCoursesOnMenu = "delete from course where menu_id=?";
    public String sqlUpdateMenu = "update menus set name=?,type=?,type_id=? where menu_id=?";

    public int getMenu_id() {
        return menu_id;
    }

    public String getName() {
        return name;
    }

    public int getTotal_price() {
        return total_price;
    }

    public String getType() {
        return type;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public void changeMenu(String name, String type, int menu_id) {
        this.name = name;
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlChangeMenu);
            line.setString(1, name);
            line.setString(2, type);
            line.setInt(3, menu_id);
            line.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not get name from DB " + e.getMessage());

        } finally {
            db.closeStatement(line);
            db.closeConnection();
        }
    }

    private int chooseTypeID(String type) {
        if (type.equals("Breakfast")) {
            return 0;
        } else if (type.equals("Lunch")) {
            return 1;
        } else if (type.equals("Dinner")) {
            return 2;
        } else if (type.equals("Supper")) {
            return 3;
        }
        return -1;
    }

    /*
     *CreateMenu:
     *Increments the menu_id and checks if the name is avaible
     *
     */
    public boolean createMenu() {
        try {
            db.openConnection();
            PreparedStatement line2 = null;
            //Increment menu_id
            line2 = db.getConnection().prepareStatement(sqlGetMenuId);
            ResultSet res2 = line2.executeQuery();
            while (res2.next()) {
                this.menu_id = res2.getInt("menu_id");
            }
            //Checks if name is avaible
            PreparedStatement line3 = null;
            line3 = db.getConnection().prepareStatement(sqlCheckName);
            line3.setString(1, name);
            res = line3.executeQuery();
            if (res.next()) {
                return false;
            } else {
                line = db.getConnection().prepareStatement(sqlCreateMenu);
                line.setString(1, name);
                line.setString(2, type);
                type_id = chooseTypeID(type);
                line.setInt(3, type_id);
                line.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Could not get name from DB " + e.getMessage());
            return false;
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
    }

    public boolean deleteMenu() {
        try {
            db.openConnection();
            PreparedStatement line2 = null;
            line2 = db.getConnection().prepareStatement(sqlDeleteCoursesOnMenu);
            line2.setInt(1, menu_id);
            line2.executeUpdate();
            line = db.getConnection().prepareStatement(sqlDeleteMenu);
            line.setInt(1, menu_id);
            line.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Could not get name from DB " + e.getMessage());
            return false;
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
    }

    public boolean updateMenu() {
        try {
            db.openConnection();
            //Checks if name is avaible
            PreparedStatement line3 = null;
            line3 = db.getConnection().prepareStatement(sqlCheckName);
            line3.setString(1, name);
            res = line3.executeQuery();
            if (res.next()) {
                return false;   
            } else {
                line = db.getConnection().prepareStatement(sqlUpdateMenu);
                type_id = chooseTypeID(type);
                line.setString(1, this.name);
                line.setString(2, this.type);
                line.setInt(3, type_id);
                line.setInt(4, menu_id);
                line.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Could not get name from DB " + e.getMessage());
            return false;
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
    }
}
