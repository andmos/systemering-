/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemDomain;

import HelpClasses.Statistics_username_priceSum;
import HelpClasses.Statistics_id_count;
import HelpClasses.DatabaseCon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.faces.context.FacesContext;

/**
 *
 * @author espen
 */
public class Statistics {
    
    private String dropViewIfExists = "DROP VIEW IF EXISTS subquerry";
    private String viewMostPopularMenuSub = "create view subquerry as select menu_id, count(menu_id) as counter from orders group by menu_id order by counter desc";
    //private String viewMostPopularMenuMeta = "create view metaquerry as select menus.menu_id, id from menus,subquerry where menus.menu_id=subquerry.menu_id;";
    private String sqlMostPopularMenu = "select menus.name,counter from menus,subquerry where menus.menu_id=subquerry.menu_id";   
    
    //private String sqlMostPopularMenu = "select menu.name, id from (select menu.name, menu_id, count(menu_id) as id from orders group by menu_id order by id desc) menu;";
    private String sqlMostProfitableCustomers = "select username, priceSum from (select username, sum(price) as priceSum from orders group by username order by priceSum desc) sumTable;";
    private String sqlNumberOfCustomers = "select count(username) as count from roles where role = 'userNormal';";
    private String sqlNumberOfManagement = "select count(username)as count from roles where role = 'management';";
    private String sqlNumberOfChefs = "select count(username) as count from roles where role = 'chef';";
    private String sqlActiveCustomers = "select username from orders where orderDate >  date_sub(curdate(), interval 1 month) group by username;";
    private String sqlNameAtUsername = "select name from users where username=?";
    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon();
    private PreparedStatement line = null;
    private PreparedStatement line2 = null;
    private ResultSet res = null;
    private ResultSet res2 = null;

    /**
     * 
     * @return A complete list of the most popular menus.
     */
    public List getMostPopularMenu() {
        List<Statistics_id_count> list = new ArrayList();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(dropViewIfExists);
            line.executeUpdate();
            db.closeStatement(line);
            line = db.getConnection().prepareStatement(viewMostPopularMenuSub);
            line.executeUpdate();
            db.closeStatement(line);
            line = db.getConnection().prepareStatement(sqlMostPopularMenu);
            res = line.executeQuery();
            while (res.next()) {
                String menu = res.getString("name");
                int count = res.getInt("counter");
                Statistics_id_count obj = new Statistics_id_count(count,menu);
                list.add(obj);
            }
        } catch (SQLException e) {
            db.WriteMessage(e, "getMostPopularMenu()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;
    }
    
    /**
     * 
     * @return A complete list of the most profitable customers.
     */
    public List getMostProfitableCustomers() {
        ArrayList<Statistics_username_priceSum> list = new ArrayList();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlMostProfitableCustomers);
            res = line.executeQuery();
            while (res.next()) {
                String username = res.getString("username");
                String[] parts = username.split("!");
                if(parts[0].equals("temp")){
                    username="";
                    for(int i =1;i<parts.length;i++){
                        username=parts[i]+" ";
                    }
                }else{
                    line2=db.getConnection().prepareStatement(sqlNameAtUsername);
                    line2.setString(1, username);
                    res2=line2.executeQuery();
                    res2.next();
                    username = res2.getString("name");
                }
                double priceSum = res.getDouble("priceSum");
                Statistics_username_priceSum obj = new Statistics_username_priceSum(username, priceSum);
                list.add(obj);
            }
        } catch (SQLException e) {
            db.WriteMessage(e, "getMostProfitableCustomers()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeResSet(res2);
            db.closeStatement(line2);
            db.closeConnection();
        }
        return list;
    }

    /**
     * 
     * @return A number of customers
     */
    public int getNumberOfCustomers() {
        int customers = -1; // default error number
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlNumberOfCustomers);
            res = line.executeQuery();
            res.next();
            customers = res.getInt("count");

        } catch (SQLException e) {
            db.WriteMessage(e, "getNumberofCustomers()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return customers;
    }
    /**
     * 
     * @return A number of management users.
     */
    public int getNumberOfManagement() {
        //  sqlNumberOfCustomers
        int management = -1; // default error number
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlNumberOfManagement);
            res = line.executeQuery();
            res.next();
            management = res.getInt("count");

        } catch (SQLException e) {
            db.WriteMessage(e, "getNumberOfManagement()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return management;
    }
    /**
     * 
     * @return A number of chef users.
     */
    public int getNumberOfChefs() {
        //  sqlNumberOfCustomers
        int chefs = -1; // default error number
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlNumberOfChefs);
            res = line.executeQuery();
            res.next();
            chefs = res.getInt("count");

        } catch (SQLException e) {
            db.WriteMessage(e, "getNumberOfChefs()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return chefs;
    }
    /**
     * 
     * @return A complete list of active customers(registered)
     */
    public List getActiveCustomers(){
      ArrayList<String> list = new ArrayList();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlActiveCustomers);
            res = line.executeQuery();
            while (res.next()) {
                String username = res.getString("username");
                list.add(username);
            }
        } catch (SQLException e) {
            db.WriteMessage(e, "getActiveCustomers()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;  
    }
    
}
