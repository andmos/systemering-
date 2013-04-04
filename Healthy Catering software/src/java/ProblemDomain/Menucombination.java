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
public class Menucombination {

    public int menu_id;
    public int total_price;
    public String name_menu;
    public String type;
    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    private String sqlConstructor = "SELECT * FROM menucombination";

    public Menucombination() {
        /*
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlConstructor);
            res = line.executeQuery();
            while (res.next()) {
                this.menu_id = res.getInt("menu_id");
                this.total_price = res.getInt("total_price");
                this.name_menu = res.getString("name_menu");
                this.type = res.getString("type");

            }
        } catch (SQLException e) {
            System.out.println("Could not get name from DB " + e.getMessage());

        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }*/
    }

    public int getMenu_id() {
        return menu_id;
    }

    public String getName_menu() {
        return name_menu;
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

    public void setName_menu(String name_menu) {
        this.name_menu = name_menu;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setType(String type) {
        this.type = type;
    }
}
