/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lists;

import HelpClasses.ChefOrders;
import HelpClasses.DriverOrders;
import HelpClasses.OrderOnName;
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
 * Help-class to help with printing orders from the database.
 */
public class Orders_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private boolean isChef = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("chef");
    private boolean isDriver = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("driver");
    private boolean isAdmin = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin");
    private boolean isUser = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("user");
    private PreparedStatement line = null;
    private PreparedStatement line2 = null;
    private ResultSet res = null;
    private ResultSet res2 = null;
    private String sqlConstructorDriver = "select orders.order_id,users.name,users.address,menus.name as MenuName,orders.status from orders,users,menus where orders.username=users.username and orders.menu_id=menus.menu_id and orders.status=0 order by order_id asc";
    private String sqlConstructorChef = "select orders.order_id,users.name,users.address,menus.name as MenuName,orders.status,orders.deliverDate from orders,users,menus where orders.username=users.username and orders.menu_id=menus.menu_id and orders.status<0 order by deliverDate asc";
    private String sqlConstructor = "SELECT distinct status,order_nr,orderDate FROM orders where username=?"; //Order date? Pass på distinct, disse skal jo være lik når du legger til uansett
    private String sqlConstructor2 = "SELECT menu_id FROM orders where username=? and order_nr=?";
    private String sqlConstructor3 = "select menu_id,name,total_price  from menus where menu_id=?";
    private String sqlConstructor4 = "select price from orders,menus where orders.menu_id=? and username=?";
    private String sqlGetSum2 = "select price from orders where order_nr=? and username=?";
    private String sqlConstructor5 = "select orders.order_nr,orders.order_id,orders.orderDate,orders.status,menus.name,orders.price,orders.deliverDate from orders,menus where menus.menu_id=orders.menu_id and username=? order by order_id";
    private String sqlGetUsername = "select username from users where name=?";
    private String sqlNameAtUsername = "select name from users where username=?";
    private int order_nr = 0;
    public List<Integer> orderIDs = new ArrayList<Integer>();

    public Orders_List() {
    }

    public Orders_List(int order_nr) {
        this.order_nr = order_nr;
    }

    public List<Integer> getorderIDs() {
        return orderIDs;
    }
/**
 * The queries here are a little weak, we could have made better queries. :(
 * @return A complete list of orders.
 */
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
            db.WriteMessage(e, "buildOrdersList()");
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
   /**
    * @param name username from a logged in user or a user that are chosen.
    * @param var (true)logged in as normal user, (false) salesmen
    * @param var2 (true)registeredUser , (false)unRegisteredUser.
    * @return A complete list of a order on a specific name.
    */
    public List<OrderOnName> getOrdersByName(String name, boolean var, boolean var2) {
        List<OrderOnName> list = new ArrayList();
        try {
            if (name != null) {
                orderIDs.removeAll(orderIDs);
                db.openConnection();
                String username = name;
                if (!var && var2) {
                    line = db.getConnection().prepareStatement(sqlGetUsername);
                    line.setString(1, name);
                    res = line.executeQuery();
                    res.next();
                    username = res.getString("username");
                    db.closeStatement(line);
                    db.closeResSet(res);
                }
                line2 = db.getConnection().prepareStatement(sqlConstructor5);
                line2.setString(1, username);
                res2 = line2.executeQuery();
                while (res2.next()) {
                    //public OrderOnName(int order_id, Date orderDate, int status, String name, double price,int order_nr,Date deliverDate) {
                    int order_id = res2.getInt("order_id");
                    Date orderDate = res2.getDate("orderDate");
                    int status = res2.getInt("status");
                    String nameFromDB = res2.getString("name");
                    double price = res2.getDouble("price");
                    int order_nr = res2.getInt("order_nr");
                    Date deliverDate = res2.getDate("deliverDate");
                    OrderOnName temp = new OrderOnName(order_id, orderDate, status, nameFromDB, price, order_nr, deliverDate);
                    list.add(temp);
                    if (status == -2) {
                        orderIDs.add(order_id);
                    }
                }
            }
        } catch (SQLException e) {
            db.WriteMessage(e, "getOrdersByName");
        } finally {
            db.closeResSet(res2);
            db.closeStatement(line2);
            db.closeConnection();
        }
        return list;
    }
/**
 * 
 * @return A complete list of all orders.
 */
    public List getOrders() {
        List list = buildOrdersList();
        return list; //.size()>0 ? list : null;
    }
    /**
     * 
     * @param order_nr Specific order.
     * @return Sum of a orders prices.
     */
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
            db.WriteMessage(e, "getSum()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.getConnection();
        }
        return sum;
    }
    
    /**
     * Help method to split a string on temp!, witch is a unregistered user.
     * @param s raw name
     * @return  actual name
     */
    private String splitString(String s) {
        String username = "";
        String[] parts = username.split("!");
        return parts[0].equals("temp") ? parts[1] : s;

    }
    /**
     * 
     * @return A complete list of orders that a driver wants.
     */
    public List<DriverOrders> getDriverOrders() {
        List<DriverOrders> list = new ArrayList();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlConstructorDriver);
            res = line.executeQuery();
            while (res.next()) {
                int order_id = res.getInt("order_id");
                String username = splitString(res.getString("name"));
                String address = res.getString("address");
                String menuName = res.getString("MenuName");
                int status = res.getInt("status");
                DriverOrders order = new DriverOrders(order_id, username, address, menuName, status);
                list.add(order);
            }
        } catch (SQLException e) {
            db.WriteMessage(e, "GetDriversOrders()");
        } finally {
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
        return list;
    }
    
    /**
     * 
     * @return A complete list of orders that a chef wants.
     */
    public List getChefOrders() {
        List<ChefOrders> list = new ArrayList();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlConstructorChef);
            res = line.executeQuery();
            while (res.next()) {
                int order_id = res.getInt("order_id");
                String username = splitString(res.getString("name"));
                String address = res.getString("address");
                String menuName = res.getString("MenuName");
                int status = res.getInt("status");
                Date deliverDate = res.getDate("deliverDate");
                ChefOrders order = new ChefOrders(order_id, username, address, menuName, status, deliverDate);
                list.add(order);
            }
        } catch (SQLException e) {
            db.WriteMessage(e, "getChefOrders()");
        } finally {
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
        return list;
    }
}
