package Beans;

import ProblemDomain.Menus;
import Lists.Menus_List;
import ProblemDomain.Orders;
import Lists.Orders_List;
import ProblemDomain.tempUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author
 * havardb
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

    public void cleanUp() {
        setAddress(null);
        setName(null);
        setOrder_id(0);
        menuslist = new Menus_List();
        for (int i = 0; i < menulists.size(); i++) {
            menulists.remove(i);

        }
    }

    public String getAddress() {
        return tempUser.getAddress();
    }

    public String getName() {
        return tempUser.getName();
    }

    public int getOrder_id() {
        return tempUser.getOrder_id();
    }

    public void setAddress(String address) {
        tempUser.setAddress(address);
    }

    public void setName(String name) {
        tempUser.setName(name);
    }

    public void setOrder_id(int order_id) {
        tempUser.setOrder_id(order_id);
    }
    public boolean getCustomerOK(){
        return CustomerOK;
    }

    //Vurder boolean metoder nedenfor for feilhåndtering
    public void addNewTempUser() {
        tempUser.addNewTempUser();
        CustomerOK = true;
    }

    public void removeTempUser() {
        tempUser.removeTempUser();
    }

    public void updateTempUser(int order_id) {
        tempUser.updateTempUser(order_id);
    }

    public List<Menus> getMenus() {
        return menuslist.buildMenuList();
    }

    public void saveMenu() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_id");
        int menu_id = Integer.parseInt(value);
        Orders order = new Orders();
        order.setMenu_id(menu_id);
        tempOrder.add(order);
    }

    public List<Menus> getSavedOrder() {
        for (int i = 0; i < tempOrder.size(); i++) {
            int menu_id = tempOrder.get(i).menu_id;
            menulists.add(new Lists.Menus_List(menu_id).getMenus());
            tempOrder.remove(i);
        }
        return menulists;
    }

    public void deleteMenu() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_id");
        int menu_id = Integer.parseInt(value);
        for (int i = 0; i <= menulists.size(); i++) {
            if (menulists.get(i).menu_id == menu_id) {
                menulists.remove(i);
            }
        }
    }

    public void placeOrders() {
        Orders order = new Orders();
        order.setNewOrderNr();
        if (menulists.size() > 0) {
            for (int i = 0; i < menulists.size(); i++) {
                Menus menu = (Menus) menulists.get(i);
                order.placeOrder(menu, tempUser.name);
            }
            System.out.println(order.menu_id);
            updateTempUser(order.menu_id);
            menulists = new ArrayList();
            setName(null);
            setAddress(null);
            CustomerOK = false;
        }
    }
}