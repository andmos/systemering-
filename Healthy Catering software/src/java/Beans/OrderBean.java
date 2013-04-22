package Beans;
/* *
 * @author
 * havardb
 */

import ProblemDomain.*;
import java.io.Serializable;
import java.util.*; //List and other important java.util classes
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
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
    private int status;
    private int menu_id = 0;
    private double sum;
    private Orders order = new Orders();
    private Orders_List orderlist;
    private Menus_List menulist = new Menus_List();
    private Course_List courselist;
    private ArrayList<Orders> shoppingcart = new ArrayList();
    private List menulists = new ArrayList();

    public int getOrder_id() {
        return order.getOrder_id();
    }

   public boolean renderShoppingCart(){
       if(menulists.size() >0){
           return true;
       }else{
           return false;
       }
   }
    
    
    public int getMenu_id() {
        return menu_id;
    }

    public int getDelivered() {
        return order.getStatus();
    }

    public String choosenMenu() {
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        menu_id = Integer.parseInt(value);
        return "chooseMenuUser";
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
    public double getSum() {
        return orderlist.getSum(order_nr);
    }
    /* 
     * Get courses on a selected order
     */

    public List getCourses() {
        courselist = new Course_List(menu_id);
        return courselist.getCourses();
    }

    public void placeOrder() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_id");
        menu_id = Integer.parseInt(value);
        Orders orders = new Orders();
        orders.setMenu_id(menu_id);
        shoppingcart.add(orders);
    }

    public List getMenus() {
        for (int i = 0; i < shoppingcart.size(); i++) {
            System.out.println(i);
            menu_id = shoppingcart.get(i).menu_id;
            menulists.add(new Menus_List(menu_id).getMenus());
            shoppingcart.remove(i);
        }
        return menulists;
    }

    public void placeOrders() {
        Orders order = new Orders();
        order.setNewOrderNr();
        if (menulists.size() > 0) {
            for (int i = 0; i < menulists.size(); i++) {
                Menus menu = (Menus) menulists.get(i);
                order.placeOrder(menu);
            }
            menulists = null;
        }
    }

    public ArrayList<Orders> getShoppingcart() {
        return shoppingcart;
    }
}
