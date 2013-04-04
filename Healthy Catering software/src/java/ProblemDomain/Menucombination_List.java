/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Menucombination_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    private String sqlConstructor = "SELECT * FROM menucombination";
    

    public List buildMenuList() {
        List<Menucombination> list = new ArrayList<Menucombination>();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlConstructor);
            res = line.executeQuery();
            while (res.next()) {
                Menucombination menu = new Menucombination();
                
                menu.menu_id = res.getInt("menu_id");
                menu.total_price = res.getInt("total_price");
                menu.name_menu = res.getString("name_menu");
                menu.type = res.getString("type");
                
                list.add(menu);
            }
        } catch (SQLException e) {
            System.out.println("Could not get name from DB " + e.getMessage());    
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;
    }

    public List getMenu() {
        List<Menucombination> list = buildMenuList();
        return list; //.size()>0 ? list : null;
    }

}
