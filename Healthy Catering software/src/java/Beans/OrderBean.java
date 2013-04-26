package Beans;
/* *
 * @author
 * havardb
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

    public String getCheckName() {
        return checkName;
    }

    public String getUnRegName() {
        return UnRegName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public Date getDeliverDate() {
        return order.getDeliverDate();
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
        order.setDeliverDate(deliverDate);
    }

    public int getOrder_id() {
        return order.getOrder_id();
    }

    public boolean renderShoppingCart() {
        if (menulists.size() > 0) {
            return true;
        } else {
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
        setText(FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_name"));
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        menu_id = Integer.parseInt(value);
        return "chooseMenuUser";
    }

    public String choosenOrder() {
        System.out.println("inne");
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_nr");
        order_nr = Integer.parseInt(value);
        System.out.println(order_nr);
        return "OrderHistory";
    }
    /*
     *Get your orders
     */

    public List<Orders> getOrders() {
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
        setText(FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_name"));
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_id");
        menu_id = Integer.parseInt(value);
        Orders orders = new Orders();
        orders.setMenu_id(menu_id);
        shoppingcart.add(orders);

    }

    public List<Menus> getMenus() {
        for (int i = 0; i < shoppingcart.size(); i++) {
            menu_id = shoppingcart.get(i).menu_id;
            menulists.add(new Menus_List(menu_id).getMenus());
        }
        shoppingcart.removeAll(shoppingcart);
        return menulists;
    }

    public void deleteShoppingCartItem() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_id");
        int menu_id = Integer.parseInt(value);
        System.out.println("1");
        for (int i = 0; i < menulists.size(); i++) {
            System.out.println("2");
            if (menulists.get(i).menu_id == menu_id) {
                System.out.println("3");
                menulists.remove(i);
                System.out.println("4");
                break;
            }
        }
    }

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

    public ArrayList<Orders> getShoppingcart() {
        return shoppingcart;
    }

    public void DeliverOrder() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_id");
        int order_id = Integer.parseInt(value);
        order.DeliverOrder(order_id);
    }

    public void StartCooking() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_id");
        int order_id = Integer.parseInt(value);
        order.StartCooking(order_id);
    }

    public void DoneCooking() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_id");
        int order_id = Integer.parseInt(value);
        order.DoneCooking(order_id);
    }

    public List<DriverOrders> getDriverOrders() {
        return orderlist.getDriverOrders();
    }

    public List getChefOrders() {
        return orderlist.getChefOrders();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void deleteOrder() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_id");
        int order_id = Integer.parseInt(value);
        order.deleteOrder(order_id);
    }

    public void setOrderID(int Order_id) {
        order.setOrder_id(Order_id);
    }

    public int getOrderID() {
        return order.getOrder_id();
    }

    public List<Integer> getOrderIDs() {
        return orderlist.orderIDs;
    }

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

    public String showOrderTable() {
        String retur = "";
        if (checkName != null) {
            retur = "ManageUserOrder";
        }
        return retur;
    }

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

    public List getNamesUnregistered() {
        return userlist.getNamesUnregistered();
    }
}
