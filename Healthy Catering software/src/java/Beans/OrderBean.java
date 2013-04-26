package Beans;
/* *
 * @author
 * havardb
 * Bean to handle requests from a order.
 */

import HelpClasses.ChefOrders;
import HelpClasses.DriverOrders;
import HelpClasses.OrderOnName;
import Lists.Menus_List;
import Lists.Course_List;
import Lists.Orders_List;
import Lists.Users_List;
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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@SessionScoped
@Named("order")
public class OrderBean implements Serializable {

    private int order_id;
    private int order_nr;
    private int status;
    private int menu_id = 0;
    private double sum;
    private Orders order = new Orders();
    private Orders_List orderlist = new Orders_List();
    private Menus_List menulist = new Menus_List();
    private Course_List courselist;
    private ArrayList<Orders> shoppingcart = new ArrayList();
    private Users_List userlist = new Users_List();
    private List<Menus> menulists = new ArrayList();
    private String text;
    private Date deliverDate;
    private String checkName;
    //Variable if you are registered or not for management(salesmen)
    private boolean var2;
    private String UnRegName;

    /**
     * 
     * @return The string that you check against from the database (auto-complete method)
     */
    public String getCheckName() {
        return checkName;
    }
/**
 * 
 * @return The raw string that you check against from the database (auto-complete method)
 */
    public String getUnRegName() {
        return UnRegName;
    }
/**
 * 
 * @param checkName Sets the name that you check against from the view (auto-complete method).
 */
    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }
/**
 * 
 * @return The orders deliver date.
 */
    public Date getDeliverDate() {
        return order.getDeliverDate();
    }
/**
 * 
 * @param deliverDate Sets the orders deliver date from the view.
 */
    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
        order.setDeliverDate(deliverDate);
    }
/**
 * 
 * @return The order id of a order.
 */
    public int getOrder_id() {
        return order.getOrder_id();
    }

    /**
     * 
     * @return of a shopping cart should be visible in the view.
     */
    public boolean renderShoppingCart() {
        if (menulists.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @return The menu ID you choose on a order.
     */
    public int getMenu_id() {
        return menu_id;
    }

    /**
     * 
     * @return The status of a order.
     */
    public int getDelivered() {
        return order.getStatus();
    }

    /**
     * Keeps the menu ID of a chosen menu from the database.
     * @return Navigation case.
     */
    public String choosenMenu() {
        setText(FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_name"));
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        menu_id = Integer.parseInt(value);
        return "chooseMenuUser";
    }

    /**
     * Keeps the order number of a chosen order from the database.
     * @return Navigation case.
     */
    public String choosenOrder() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_nr");
        order_nr = Integer.parseInt(value);
        return "OrderHistory";
    }
    /**
     *
     * @return Order that belong to a user.
     */
    public List<Orders> getOrders() {
        return orderlist.getOrders();
    }
    /**
    * 
    * @return A specific order
    */
    public List getOrder() {
        orderlist = new Orders_List(order_nr);
        return orderlist.getOrders();

    }
    
    /**
    * 
    * @return The sum of a order
    */
    public double getSum() {
        return orderlist.getSum(order_nr);
    }
    /**
     * 
     * @return The courses on a selected order
     */
    public List getCourses() {
        courselist = new Course_List(menu_id);
        return courselist.getCourses();
    }
    /**
     * Add a chosen menu to your shopping cart.
     */
    public void placeOrder() {
        setText(FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_name"));
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_id");
        menu_id = Integer.parseInt(value);
        Orders orders = new Orders();
        orders.setMenu_id(menu_id);
        shoppingcart.add(orders);

    }
    /**
     * 
     * @return A list of menus on a menu ID chosen from a order.
     */
    public List<Menus> getMenus() {
        for (int i = 0; i < shoppingcart.size(); i++) {
            menu_id = shoppingcart.get(i).menu_id;
            menulists.add(new Menus_List(menu_id).getMenus());
        }
        shoppingcart.removeAll(shoppingcart);
        return menulists;
    }
    
    /**
     * Delete a specific shopping cart item that you choose from the view.
     */
    public void deleteShoppingCartItem() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_id");
        int menu_id = Integer.parseInt(value);
        for (int i = 0; i < menulists.size(); i++) {
            if (menulists.get(i).menu_id == menu_id) {
                menulists.remove(i);
                break;
            }
        }
    }
/**
 * Place a order.
 */
    public void placeOrders() {
        order.setNewOrderNr();
        if (menulists.size() > 0) {
            for (int i = 0; i < menulists.size(); i++) {
                Menus menu = (Menus) menulists.get(i);
                order.placeOrder(menu);
            }
            menulists = null;
        }
    }
    /**
     * 
     * @return The shopping cart.
     */
    public ArrayList<Orders> getShoppingcart() {
        return shoppingcart;
    }

    /**
     * Used by a driver to deliver orders.
     */
    public void DeliverOrder() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_id");
        int order_id = Integer.parseInt(value);
        order.DeliverOrder(order_id);
    }
    /**
     * Used by a chef to start cooking.
     */
    public void StartCooking() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_id");
        int order_id = Integer.parseInt(value);
        order.StartCooking(order_id);
    }
/**
 * Used by a chef when he is done cooking.
 */
    public void DoneCooking() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_id");
        int order_id = Integer.parseInt(value);
        order.DoneCooking(order_id);
    }
/**
 * 
 * @return A list for the drivers.
 */
    public List<DriverOrders> getDriverOrders() {
        return orderlist.getDriverOrders();
    }
/**
 * 
 * @return A list for the chefs.
 */
    public List getChefOrders() {
        return orderlist.getChefOrders();
    }

    /**
     * 
     * @return The text variable.
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text Sets the text variable.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Delete a specific order.
     */
    public void deleteOrder() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_id");
        int order_id = Integer.parseInt(value);
        order.deleteOrder(order_id);
    }

    /**
     * 
     * @param Order_id Set a order ID from the view.
     */
    public void setOrderID(int Order_id) {
        order.setOrder_id(Order_id);
    }
    /**
     * 
     * @return The order ID from the database.
     */
    public int getOrderID() {
        return order.getOrder_id();
    }
    /**
     * 
     * @return The order IDs that from the database.
     */
    public List<Integer> getOrderIDs() {
        return orderlist.orderIDs;
    }
    /**
    * Var2 = if a user is registered or not.
    * @return Orders on a name.
    */
    public List<OrderOnName> getOrdersByName() {
        if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userNormal")
                || FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userCompany")) {
            String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
            return orderlist.getOrdersByName(username, true,true);
        } else {
            if(var2){
            return orderlist.getOrdersByName(checkName, false,var2);
            }else{
                return orderlist.getOrdersByName(UnRegName, false, var2);
            }
        }
    }
    
    /**
     * 
     * @return Navigation case.
     */
    public String showOrderTable() {
        String retur = "";
        if (checkName != null) {
            retur = "ManageUserOrder";
        }
        return retur;
    }
/**
 * 
 * @param query String from the auto-complete method.
 * @return A list of names from the database that you can auto-complete from.
 */
    public List<String> completeReg(String query) {
        List<String> results = new ArrayList<String>();
        for (String var : userlist.getNamesRegistered()) {
            if (var.toUpperCase().startsWith(query.toUpperCase())) {
                checkName = var;
                results.add(var);
                var2=true;
            }
        }

        return results;
    }
    /**
    * 
    * @param query String from the auto-complete method.
    * @return A list of names from the database that you can auto-complete from.
    */
    public List<String> completeUnreg(String query) {
        List<String> results = new ArrayList<String>();
        for (String var : userlist.getNamesUnregistered()) {
            String[] parts = var.split("!");
            String var0 = parts[0].equals("temp") ? parts[1] : var;
            if (var0.toUpperCase().startsWith(query.toUpperCase())) {
                checkName = var0;
                results.add(var0);
                UnRegName=var;
                var2=false;
            }
        }

        return results;
    }
/**
 * 
 * @return Names from the database that are not registered.
 */
    public List getNamesUnregistered() {
        return userlist.getNamesUnregistered();
    }
}
