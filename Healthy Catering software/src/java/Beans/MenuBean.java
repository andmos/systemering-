package Beans;

import ProblemDomain.Course_List;
import ProblemDomain.Menus;
import ProblemDomain.Menus_List;
/**
 *
 * @author
 * havardb
 */
import java.io.Serializable;
import java.util.*; //List and other important java.util classes
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named("menu")
public class MenuBean implements Serializable {

    private int menu_id;
    private int total_price;
    private String name;
    private String type;
    private int choosenMenu;
    private Menus menu = new Menus();
    private Menus_List menulist;
    private Course_List courselist;

    public int getMenu_id() {
        return menu.getMenu_id();
    }

    public String getName() {
        return menu.getName();
    }

    public int getTotal_price() {
        return menu.getTotal_price();
    }

    public String getType() {
        return type;
    }

    public String choosenMenu() {
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        System.out.println(value);
        menu_id = Integer.parseInt(value);
        String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if(user == null){
        return "choosenMenu";
        }else{
            return "chooseMenuUser";
        }
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
        menu.setMenu_id(menu_id);
    }

    public void setName(String name) {
        this.name = name;
        menu.setName(name);
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
        menu.setTotal_price(total_price);
    }

    public void setType(String type) {
        this.type = type;
        menu.setType(type);
    }

    public List getMenu() {
        menulist = new Menus_List();
        return menulist.getMenu();
    }

    public List getCourses() {
        courselist = new Course_List(menu_id);
        return courselist.getCourses();
    }
}
