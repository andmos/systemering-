package Beans;

import ProblemDomain.Course;
import Lists.Course_List;
import ProblemDomain.Menus;
import Lists.Menus_List;
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
    //Error handling variables
    private boolean createMenuGroupError;
    private boolean createMenuError;
    private boolean deleteMenuGroupError;
    private boolean deleteMenuError;
    private boolean updateMenuGroupError;
    private boolean updateMenuError;

    public int getMenu_id() {
        return menu_id;
    }

    public String getName() {
        return menu.getName();
    }

    public int getTotal_price() {
        return menu.getTotal_price();
    }

    public String getType() {
        return menu.getType();
    }

    public String choosenMenu() {
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        menu_id = Integer.parseInt(value);
        String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (user == null) {
            return "choosenMenu";
        } else {
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
        //Sets variables to default so we can add menus later.
        //Error handling
        createMenuGroupError = false;
        menulist = new Menus_List();
        return menulist.getMenu();
    }

    public List getCourses() {
        courselist = new Course_List(menu_id);
        return courselist.getCourses();
    }

    public String chooseMenu() {
        String input = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        setName(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name"));
        setType(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type"));
        setMenu_id(Integer.parseInt(input));
        return "EditMenu";

    }

    public void createMenu() {
        deleteMenuGroupError = false;
        createMenuGroupError = true;
        if (menu.createMenu()) {
            updateMenuError = false;
            deleteMenuError = false;
            createMenuError = true;
        } else {
            createMenuError = false;
        }
    }

    public void deleteMenu() {
        deleteMenuGroupError = true;
        String input = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        menu_id = Integer.parseInt(input);
        setMenu_id(menu_id);
        if (menu.deleteMenu()) {
            createMenuError =  false;
            updateMenuError = false;
            deleteMenuError = true;
        } else {
            deleteMenuError = false;
        }
    }

    public void updateMenu() {
        updateMenuGroupError = true;
        if (menu.updateMenu()) {
            deleteMenuError = false;
            createMenuError = false;
            updateMenuError = true;
        } else {
            setName(menu.name);
            updateMenuError = false;
        }
    }
    public boolean getCreateMenuGroupError(){
        return createMenuGroupError;
    }
    public boolean getUpdateMenuError(){
        return updateMenuError;
    }
    
    public boolean getDeleteMenuError() {
        return deleteMenuError;
    }

    public boolean getCreateMenuError() {
        return createMenuError;
    }
    public boolean getDeleteMenuGroupError(){
        return deleteMenuGroupError;
    }
    public boolean getUpdateMenuGroupError(){
        return updateMenuGroupError;
    }
    
    public void setErrorGroups(){
        updateMenuGroupError = false;
        deleteMenuGroupError = false;
        createMenuGroupError = false;
        setName(null);
        setType(null);
    }
}
