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
    private String sqlPlaceOrder = "insert into orders(status,username,menu_id,order_nr,orderDate,price,deliverDate) values(-2,?,?,?,now(),?,?)";
    private String sqlDeliverOrder = "update orders set status=1 where order_id=?";
    private String sqlStartCooking = "update orders set status=-1 where order_id=?";
    private String sqlDoneCooking = "update orders set status=0 where order_id=?";
    private String sqlDeleteOrder = "delete from orders where order_id=?";
    public int order_id;
    public int status;
    public int menu_id;
    public int order_nr;
    public Date orderDate;
    public int menuCount;
    public double price;
    public Date deliverDate;
    public String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

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
    
    /**
     * Increment a new order number, we had to do this manually because we did not add auto_increment in the database.
     */
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
            db.WriteMessage(e, "setNewOrderNr()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
    }
    /**
     * Place a new order on a user that are registered.
     * @param menu New menu to the order.
     * @return Error variable.
     */
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
            java.sql.Date sqlDate = new java.sql.Date(deliverDate.getTime());
            line.setDate(5,sqlDate);
            line.executeUpdate();
            check = true;

        } catch (SQLException e) {
          db.WriteMessage(e, "placeOrder()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return check;
    }
    /**
     * Place a order on a user that are not registered.
     * @param menu new Menu
     * @param name new Name
     * @param deliverDate new Deliver date
     * @return Error variable.
     */
     public boolean placeOrder(Menus menu,String name,Date deliverDate) {
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
            java.sql.Date sqlDate = new java.sql.Date(deliverDate.getTime());
            line.setDate(5,sqlDate);
            line.executeUpdate();
            check = true;

        } catch (SQLException e) {
            db.WriteMessage(e, "placeOrder()");
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return check;
    }
     
     /**
      * Deliver a specific order
      * @param order_id Specific order
      */
     public void DeliverOrder(int order_id){
         try{
             db.openConnection();
             line = db.getConnection().prepareStatement(sqlDeliverOrder);
             line.setInt(1,order_id);
             line.executeUpdate();
         }catch(SQLException e){
             db.WriteMessage(e, "DeliverOrder()");
         }finally{
             db.closeConnection();
             db.closeResSet(res);
             db.closeStatement(line);
         }
     }
     
     /**
      * Start cooking a specific order
      * @param order_id Specific order.
      */
     public void StartCooking(int order_id){
         try{
             db.openConnection();
             line = db.getConnection().prepareStatement(sqlStartCooking);
             line.setInt(1,order_id);
             line.executeUpdate();
         }catch(SQLException e){
             db.WriteMessage(e, "StartCooking");
         }finally{
             db.closeConnection();
             db.closeResSet(res);
             db.closeStatement(line);
         }
     }
     /**
      * Set a order to done.
      * @param order_id Specific order.
      */
     public void DoneCooking(int order_id){
         try{
             db.openConnection();
             line = db.getConnection().prepareStatement(sqlDoneCooking);
             line.setInt(1,order_id);
             line.executeUpdate();
         }catch(SQLException e){
             db.WriteMessage(e, "DoneCooking()");
         }finally{
             db.closeConnection();
             db.closeResSet(res);
             db.closeStatement(line);
         }
     }
     /**
      * Delete a specific order
      * @param order_id Specific order
      */
     public void deleteOrder(int order_id){
         try{
             db.openConnection();
             line = db.getConnection().prepareStatement(sqlDeleteOrder);
             line.setInt(1,order_id);
             line.executeUpdate();
         }catch(SQLException e){
             db.WriteMessage(e, "deleteOrder()");
         }finally{
             db.closeConnection();
             db.closeResSet(res);
             db.closeStatement(line);
         }
     }
}
