/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lists;

import ProblemDomain.DriverOrders;
import ProblemDomain.Menus;
import ProblemDomain.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.faces.context.FacesContext;
import java.util.*;
import java.util.Date.*;

/**
 *
 * @author
 * espen
 */
public class Orders_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private boolean isChef = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("chef");
    private boolean isDriver = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("driver");
    private boolean isAdmin = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin");
    private boolean isUser = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("user");
    private PreparedStatement line = null;
    private ResultSet res = null;
    private String sqlConstructorDriver = "select orders.order_id,users.name,users.address,menus.name as MenuName,orders.status from orders,users,menus where orders.username=users.username and orders.menu_id=menus.menu_id and orders.status=0 order by order_id asc";
    private String sqlConstructorChef = "select orders.order_id,users.name,users.address,menus.name as MenuName,orders.status from orders,users,menus where orders.username=users.username and orders.menu_id=menus.menu_id and orders.status<0 order by order_id asc";
    private String sqlConstructor = "SELECT distinct status,order_nr,orderDate FROM orders where username=?"; //Order date? Pass på distinct, disse skal jo være lik når du legger til uansett
    private String sqlConstructor2 = "SELECT menu_id FROM orders where username=? and order_nr=?";
    private String sqlConstructor3 = "select menu_id,name,total_price  from menus where menu_id=?";
    private String sqlConstructor4 = "select price from orders,menus where orders.menu_id=? and username=?";
    private String sqlGetSum2 = "select price from orders where order_nr=? and username=?";
    private String sqlConstructor5 = "SELECT distinct status,order_nr,orderDate FROM orders where username=?";
    private String sqlGetUsername = "select username from users where name=?";
    private int order_nr = 0;

    public Orders_List() {
    }

    public Orders_List(int order_nr) {
        this.order_nr = order_nr;
    }

    public List buildOrdersList() {
        List<Orders> list = new ArrayList<Orders>();
        List<Menus> list2 = new ArrayList<>();

        try {
            db.openConnection();
            if (order_nr == 0) {
                line = db.getConnection().prepareStatement(sqlConstructor);
                line.setString(1, user);
                res = line.executeQuery();
                while (res.next()) {
                    Orders orders = new Orders();
                    orders.status = res.getInt("status");
                    orders.order_nr = res.getInt("order_nr");
                    orders.orderDate = res.getDate("orderDate");
                    list.add(orders);
                }

            } else {
                line = db.getConnection().prepareStatement(sqlConstructor2);
                line.setString(1, user);
                line.setInt(2, order_nr);
                res = line.executeQuery();
                while (res.next()) {
                    line = db.getConnection().prepareStatement(sqlConstructor3);
                    line.setInt(1, res.getInt("menu_id"));
                    ResultSet res2 = line.executeQuery();
                    while (res2.next()) {
                        Menus menu = new Menus();
                        menu.menu_id = res2.getInt("menu_id");
                        menu.name = res2.getString("name");
                        line = db.getConnection().prepareStatement(sqlConstructor4);
                        line.setInt(1, menu.menu_id);
                        line.setString(2, user);
                        ResultSet res3 = line.executeQuery();
                        while (res3.next()) {
                            menu.sum = (double) res3.getInt("price");
                        }
                        list2.add(menu);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Feil i buildOrdersList() " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        /* 
         *If order_nr = 0, you havent choosen a order yet
         * if order_nr != 0, you have choosen a order
         */
        if (order_nr == 0) {
            return list;
        } else {
            return list2;
        }
    }

    public List<Orders> getOrdersByName(String name) {
        List<Orders> list = new ArrayList();
        try {
            if (name != null) {
                db.openConnection();
                line = db.getConnection().prepareStatement(sqlGetUsername);
                line.setString(1, name);
                res = line.executeQuery();
                res.next();
                String username = res.getString("username");
                db.closeStatement(line);
                db.closeResSet(res);
                line = db.getConnection().prepareStatement(sqlConstructor5);
                line.setString(1, username);
                res = line.executeQuery();
                while (res.next()) {
                    Orders temp = new Orders();
                    temp.setStatus(res.getInt("status"));
                    temp.setOrder_nr(res.getInt("order_nr"));
                    temp.setOrderDate(res.getDate("orderDate"));
                    list.add(temp);
                }
            }
        } catch (SQLException e) {
            System.out.println("getOrdersByName() failed:  " + e);
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;
    }

    public List getOrders() {
        List list = buildOrdersList();
        return list; //.size()>0 ? list : null;
    }

    public double getSum(int order_nr) {
        double sum = 0;
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlGetSum2);
            line.setInt(1, order_nr);
            line.setString(2, user);
            ResultSet res2 = line.executeQuery();
            while (res2.next()) {
                sum += res2.getInt("price");
            }
            //}
        } catch (SQLException e) {
            System.out.println("Feil i getSum " + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.getConnection();
        }
        return sum;
    }

    public List<DriverOrders> getDriverOrders() {
        List<DriverOrders> list = new ArrayList();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlConstructorDriver);
            res = line.executeQuery();
            while (res.next()) {
                int order_id = res.getInt("order_id");
                String name = res.getString("name");
                String address = res.getString("address");
                String menuName = res.getString("MenuName");
                int status = res.getInt("status");
                DriverOrders order = new DriverOrders(order_id, name, address, menuName,status);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Failure in getDriverOrders()" + e.getMessage());
        } finally {
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
        return list;
    }
    
    /*
     *DriverOrders, could have renamed it to a better name, because both driver and chef uses it
     */
    public List<DriverOrders> getChefOrders() {
        List<DriverOrders> list = new ArrayList();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlConstructorChef);
            res = line.executeQuery();
            while (res.next()) {
                int order_id = res.getInt("order_id");
                String name = res.getString("name");
                String address = res.getString("address");
                String menuName = res.getString("MenuName");
                int status = res.getInt("status");
                DriverOrders order = new DriverOrders(order_id, name, address, menuName,status);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Failure in getDriverOrders()" + e.getMessage());
        } finally {
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
        return list;
    }
    
}
