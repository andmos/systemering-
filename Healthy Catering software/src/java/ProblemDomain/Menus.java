package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;

/**
 *
 * @author
 * havardb
 */
public class Menus {

    public int menu_id;
    public int total_price;
    public String name;
    public String type;
    public Double sum;

    public int getMenu_id() {
        return menu_id;
    }

    public String getName() {
        return name;
    }

    public int getTotal_price() {
        return total_price;
    }

    public String getType() {
        return type;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
    
    
}
