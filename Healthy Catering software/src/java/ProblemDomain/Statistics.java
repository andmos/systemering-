/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemDomain;

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

    private String sqlMostPopularMenu = "select menu.menu_id, id from (select menu_id, count(menu_id) as id from orders group by menu_id order by id desc) menu;";
    private String sqlMostProfitableCustomers = "select username, priceSum from (select username, sum(price) as priceSum from orders group by username order by priceSum desc) sumTable;";
    private String sqlNumberOfCustomers = "select count(username) as count from roles where role = 'userNormal';";
    private String sqlNumberOfManagement = "select count(username)as count from roles where role = 'management';";
    private String sqlNumberOfChefs = "select count(username) as count from roles where role = 'chef';";
    private String sqlActiveCustomers = "select username from orders where orderDate >  date_sub(curdate(), interval 1 month) group by username;";
    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon();
    private PreparedStatement line = null;
    private ResultSet res = null;

    public List getMostPopularMenu() {
        ArrayList<Statistics_id_count> list = new ArrayList();

        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlMostPopularMenu);
            res = line.executeQuery();
            while (res.next()) {
                int id = res.getInt("menu_id");
                int count = res.getInt("id");
                Statistics_id_count obj = new Statistics_id_count(id, count);
                list.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("getMostPopularMenu() " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        System.out.println("test");
        return list;
    }

    public List getMostProfitableCustomers() {
        ArrayList<Statistics_username_priceSum> list = new ArrayList();

        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlMostProfitableCustomers);
            res = line.executeQuery();
            while (res.next()) {
                String username = res.getString("username");
                double priceSum = res.getDouble("priceSum");
                Statistics_username_priceSum obj = new Statistics_username_priceSum(username, priceSum);
                list.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("getMostProfitableCustomers() " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;
    }

    public int getNumberOfCustomers() {
        //  sqlNumberOfCustomers
        int customers = -1; // default error number
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlNumberOfCustomers);
            res = line.executeQuery();
            customers = res.getInt("count");

        } catch (SQLException e) {
            System.out.println("getNumberOfCustomers() " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return customers;
    }
    public int getNumberOfManagement() {
        //  sqlNumberOfCustomers
        int management = -1; // default error number
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlNumberOfManagement);
            res = line.executeQuery();
            management = res.getInt("count");

        } catch (SQLException e) {
            System.out.println("getNumberOfManagement() " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return management;
    }
    public int getNumberOfChefs() {
        //  sqlNumberOfCustomers
        int chefs = -1; // default error number
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlNumberOfChefs);
            res = line.executeQuery();
            chefs = res.getInt("count");

        } catch (SQLException e) {
            System.out.println("getNumberOfChefs() " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return chefs;
    }
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
            System.out.println("getActiveCustomers() " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;  
    }
    
}
