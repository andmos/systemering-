package Beans;

import ProblemDomain.Menucombination;
import ProblemDomain.Menucombination_List;
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
    private Menucombination menu = new Menucombination();
    Menucombination_List list = new Menucombination_List();
    

    public int getMenu_id() {
        return menu.getMenu_id();
    }

    public String getName() {
        return menu.getName_menu();
    }

    public int getTotal_price() {
        return menu.getTotal_price();
    }

    public String getType() {
        return type;
    }
    
    public int getChoosenMenu(){
        return choosenMenu;
    }
    public void action(){
	   String value = FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("hidden1");
           System.out.println(value);
	}
    
    public String choosenMenu(){
        String value = FacesContext.getCurrentInstance().
               getExternalContext().getRequestParameterMap().get("menu_id"); 
        System.out.println(value);
        //choosenMenu = Integer.parseInt(value);
        return "choosenMenu";
    }
    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
        menu.setMenu_id(menu_id);
    }

    public void setName(String name) {
        this.name = name;
        menu.setName_menu(name);
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
        menu.setTotal_price(total_price);
    }
    
    public void setType(String type) {
        this.type = type;
        menu.setType(type);
    }
     
    public List getMenu(){    
        return list.getMenu();
    }
      //public List getMenu(){//
    //return menu.getMenu();
    //}
}
