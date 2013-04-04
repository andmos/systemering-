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
 * @author
 * espen
 */
public class Orders_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    private String sqlConstructor = "SELECT distinct delivered,order_nr FROM orders where username=?"; //Order date? Pass på distinct, disse skal jo være lik når du legger til uansett
    private String sqlConstructor2 = "SELECT menu_id FROM orders where username=? and order_nr=?";
    private String sqlConstructor3 = "select menu_id,name,total_price  from menus where menu_id=?";
    private String sqlGetSum = "select menu_id from orders  where order_nr=?";
    private String sqlGetSum2 = "select total_price from menus where menu_id=?";
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
                    orders.delivered = res.getInt("delivered");
                    orders.order_nr = res.getString("order_nr");
                    //orders.orderDate = res.getDate("orderDate");
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
                        menu.total_price = res2.getInt("total_price");
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

    public List getOrders() {
        List<Menus> list = buildOrdersList();
        return list; //.size()>0 ? list : null;
    }
    
    public double getSum(int order_nr){
        System.out.println("her");
        double sum = 0;
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlGetSum);
            line.setInt(1, order_nr);
            res = line.executeQuery();
            while(res.next()){
                int menu_id = res.getInt("menu_id");
                line = db.getConnection().prepareStatement(sqlGetSum2);
                line.setInt(1,menu_id);
                ResultSet res2 = line.executeQuery();
                while(res2.next()){
                    sum += res2.getInt("total_price");
                }
            }
        }catch(SQLException e){
            System.out.println("Feil i getSum "+e.getMessage());
        }finally{
            db.closeResSet(res);
            db.closeStatement(line);
            db.getConnection();
        }
        return sum;
    }
}
