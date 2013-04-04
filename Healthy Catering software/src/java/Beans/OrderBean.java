package Beans;
/* *
 * @author
 * havardb
 */

import ProblemDomain.*;
import java.io.Serializable;
import java.util.*; //List and other important java.util classes
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named("order")
public class OrderBean implements Serializable {

    private int order_id;
    private int order_nr;
    private int delivered;
    private int menu_id;
    private double sum;
    private Orders order = new Orders();
    private Orders_List orderlist;
    private Menus_List menulist;

    public int getOrder_id() {
        return order.getOrder_id();
    }

    public int getMenu_id() {
        return order.getMenu_id();
    }

    public int getDelivered() {
        return order.getDelivered();
    }

    public String choosenOrder() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_nr");
        order_nr = Integer.parseInt(value);
        System.out.println(order_nr);
        return "OrderHistory";
    }
    /*
     *Get your orders
     */

    public List getOrders() {
        orderlist = new Orders_List();
        return orderlist.getOrders();
    }
    /*
     *Get a order
     */
    public List getOrder() {
       orderlist = new Orders_List(order_nr);
       return orderlist.getOrders();
    }
    
    /*
     *Get total bought
     */
    
    public double getSum(){
        return orderlist.getSum(order_nr);
    }

 
}
