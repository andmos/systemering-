package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author
 * havardb
 */
public class Orders {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private PreparedStatement line = null;
    private ResultSet res = null;
    String sqlPlaceOrder = "insert into orders(status,username,menu_id,order_nr,orderDate,price) values(-1,?,?,?,now(),?)";
    public int order_id;
    public int status;
    public int menu_id;
    public int order_nr;
    public Date orderDate;
    public int menuCount;
    public double price;
    public String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

    public Orders() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getStatus() {
        return status;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrder_nr(int order_nr) {
        this.order_nr = order_nr;
    }

    public int getOrder_nr() {
        return order_nr;
    }

    public int getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(int menuCount) {
        this.menuCount = menuCount;
    }

    public void setNewOrderNr() {
        try{
        PreparedStatement line2 = null;
        db.openConnection();
        line2 = db.getConnection().prepareStatement("select max(order_nr) as max from orders where username=?");
        line2.setString(1, this.username);
        ResultSet res2 = line2.executeQuery();
        while (res2.next()) {
            this.order_nr = res2.getInt("max") + 1;
        }
         } catch (SQLException e) {
            System.out.println("Feil i setNewOrderNr()" + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
    }

    public boolean placeOrder(Menus menu) {
        boolean check = false;
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlPlaceOrder);
            line.setString(1, this.username);
            line.setInt(2, menu.menu_id);
            line.setInt(3, this.order_nr);
            if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userNormal")) {
                        line.setDouble(4, menu.sum*1.25);
                    } else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userCompany")) {
                        line.setDouble(4, menu.sum);
                    }else{
                        line.setDouble(4, menu.sum*1.25);
                    }
            line.executeUpdate();
            check = true;

        } catch (SQLException e) {
            System.out.println("Feil i placeOrder()" + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return check;
    }
     public boolean placeOrder(Menus menu,String name) {
        boolean check = false;
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlPlaceOrder);
            line.setString(1, name);
            line.setInt(2, menu.menu_id);
            line.setInt(3, this.order_nr);
            if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userNormal")) {
                        line.setDouble(4, menu.sum*1.25);
                    } else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userCompany")) {
                        line.setDouble(4, menu.sum);
                    }else{
                        line.setDouble(4, menu.sum*1.25);
                    }
            line.executeUpdate();
            check = true;

        } catch (SQLException e) {
            System.out.println("Feil i placeOrder()" + e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return check;
    }
}
