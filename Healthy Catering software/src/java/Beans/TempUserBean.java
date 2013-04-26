package Beans;

import ProblemDomain.Menus;
import Lists.Menus_List;
import ProblemDomain.Orders;
import Lists.Orders_List;
import ProblemDomain.tempUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author
 * havardb
 * A bean to handle requests from a unregistered user.
 */
@SessionScoped
@Named("tempUser")
public class TempUserBean implements Serializable {

    private tempUser tempUser = new tempUser();
    private Menus_List menuslist = new Menus_List();
    private ArrayList<Orders> tempOrder = new ArrayList();
    private List<Menus> menulists = new ArrayList();
    //Check if we can continue
    private boolean CustomerOK;
    private int order_nr;
    private Date deliverDate;

    /**
     * Clean up method when you are done with a unregistered user.
     */
    public void cleanUp() {
        setAddress(null);
        setName(null);
        menuslist = new Menus_List();
        for (int i = 0; i < menulists.size(); i++) {
            menulists.remove(i);

        }
    }
    /**
     * 
     * @param order_nr Sets order number from the view.
     */
    public void setOrder_nr(int order_nr) {
        this.order_nr = order_nr;
    }

    /**
     * 
     * @return A order number from the view.
     */
    public int getOrder_nr() {
        return order_nr;
    }
    /**
     * 
     * @return Address from a un-registered user from the database.
     */
    public String getAddress() {
        return tempUser.getAddress();
    }

    /**
     * 
     * @return The name from a un-registered user from the database.
     */
    public String getName() {
        return tempUser.getName();
    }
    /**
     * 
     * @param address Set the address of an un-registered users address from the view.
     */
    public void setAddress(String address) {
        tempUser.setAddress(address);
    }
/**
 * 
 * @param name Sets the name of an un-registered users address from the view.
 */
    public void setName(String name) {
        tempUser.setName(name);
    }
    /**
     * 
     * @return Error variable.
     */
    public boolean getCustomerOK() {
        return CustomerOK;
    }

    /**
     * Add new un-registered user.
     */
    public void addNewTempUser() {
        tempUser.addNewTempUser();
        CustomerOK = true;
    }
    
    /**
     * Remove an un-registered user.
     */
    public void removeTempUser() {
        tempUser.removeTempUser();
    }

    /**
     * 
     * @param order_id Update a order on a un-registered user.
     */
    public void updateTempUser(int order_id) {
        tempUser.updateTempUser(order_id);
    }
    
    /**
     * 
     * @return Return the menus from the database.
     */
    public List<Menus> getMenus() {
        return menuslist.buildMenuList();
    }

    /**
     * Add a menu to the order of a un-registered user.
     */
    public void saveMenu() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_id");
        int menu_id = Integer.parseInt(value);
        Orders order = new Orders();
        order.setMenu_id(menu_id);
        tempOrder.add(order);
    }
    /**
     * 
     * @return Menus that are in the "shopping cart" of a un-registered user.
     */
    public List<Menus> getSavedOrder() {
        for (int i = 0; i < tempOrder.size(); i++) {
            int menu_id = tempOrder.get(i).menu_id;
            menulists.add(new Lists.Menus_List(menu_id).getMenus());
        }
        tempOrder.removeAll(tempOrder);
        return menulists;
    }
    /**
     * Delete menus in the "shopping cart" of a un-registered user.
     */
    public void deleteMenu() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_id");
        int menu_id = Integer.parseInt(value);
        for (int i = 0; i <= menulists.size(); i++) {
            if (menulists.get(i).menu_id == menu_id) {
                menulists.remove(i);
                break;
            }
        }
    }
    /**
     * 
     * @return The deliver date of a order.
     */
    public Date getDeliverDate() {
        return deliverDate;

    }
    /**
     * 
     * @param deliverDate Sets the deliver date from a view.
     */
    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }
    
    
/**
 * Place a order of a un-registered user.
 * Also handles error messages.
 */
    public void placeOrders() {
        Orders order = new Orders();
        order.setNewOrderNr();
        int order_id = order.getOrder_nr();
        if (menulists.size() > 0) {
            for (int i = 0; i < menulists.size(); i++) {
                Menus menu = (Menus) menulists.get(i);
                order.placeOrder(menu, tempUser.name,deliverDate);

            }
            updateTempUser(order_id);
            menulists = new ArrayList();
            setName(null);
            setAddress(null);
            CustomerOK = false;
        }
    }
}