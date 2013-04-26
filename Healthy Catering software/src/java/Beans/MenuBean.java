package Beans;

import ProblemDomain.Course;
import Lists.Course_List;
import ProblemDomain.Menus;
import Lists.Menus_List;
/**
 *
 * @author
 * havardb
 * A bean to handle menu requests.
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

    /**
     * 
     * @return Menu ID from a menu.
     */
    public int getMenu_id() {
        return menu_id;
    }

    /**
     * 
     * @return The name of a menu.
     */
    public String getName() {
        return menu.getName();
    }

    /**
     * 
     * @return The total price of a menu.
     */
    public int getTotal_price() {
        return menu.getTotal_price();
    }

    /**
     * 
     * @return The type of a menu.
     */
    public String getType() {
        return menu.getType();
    }

    /**
     * Sets the menu ID of a chosen menu from the database.
     * @return Navigation case.
     */
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

    /**
     * 
     * @param menu_id Sets the menu ID for a menu from the view.
     */
    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
        menu.setMenu_id(menu_id);
    }
/**
 * 
 * @param name Sets the name of a menu from the view.
 */
    public void setName(String name) {
        this.name = name;
        menu.setName(name);
    }
/**
 * 
 * @param total_price Sets the total price for a menu from the view.
 */
    public void setTotal_price(int total_price) {
        this.total_price = total_price;
        menu.setTotal_price(total_price);
    }

    /**
     * 
     * @param type Sets the menu type from the view.
     */
    public void setType(String type) {
        this.type = type;
        menu.setType(type);
    }
/**
 * Returns a list of a menu.
 * Sets variables to default so we can add menus later.
 * @return 
 */
    public List getMenu() {
        createMenuGroupError = false;
        menulist = new Menus_List();
        return menulist.getMenu();
    }
/**
 * 
 * @return Courses on a menu.
 */
    public List getCourses() {
        courselist = new Course_List(menu_id);
        return courselist.getCourses();
    }
/**
 * Choose a menu from the database.
 * @return Navigation case.
 */
    public String chooseMenu() {
        String input = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        setName(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name"));
        setType(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type"));
        setMenu_id(Integer.parseInt(input));
        return "EditMenu";

    }
/**
 * Create a new menu.
 * Also sets different error messages depending on the outcome.
 */
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
/**
 * Delete a menu.
 * Also sets different error messages depending on the outcome.
 */
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
/**
 * Update a menu.
 * Also sets different error message depending on the outcome.
 */
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
    /**
     * 
     * @return Error variable.
     */
    public boolean getCreateMenuGroupError(){
        return createMenuGroupError;
    }
    /**
     * 
     * @return Error variable.
     */
    public boolean getUpdateMenuError(){
       return updateMenuError;
    }
    /**
     * 
     * @return Error variable.
     */
    public boolean getDeleteMenuError() {
        return deleteMenuError;
    }
/**
 * 
 * @return Error variable.
 */
    public boolean getCreateMenuError() {
        return createMenuError;
    }
    /**
     * 
     * @return Error variable.
     */
    public boolean getDeleteMenuGroupError(){
        return deleteMenuGroupError;
    }
    /**
     * 
     * @return Error variable.
     */
    public boolean getUpdateMenuGroupError(){
        return updateMenuGroupError;
    }
    
    /**
     * Sets a error-group to use in the view for rendering items.
     */
    public void setErrorGroups(){
        updateMenuGroupError = false;
        deleteMenuGroupError = false;
        createMenuGroupError = false;
        setName(null);
        setType(null);
    }
}
