package HelpClasses;

import java.util.Date;

/**
 *
 * @author havardb
 * Help-class
 */
public class ChefOrders {
public int order_id;
public String name;
public String address;
public String MenuName;
public int status;
public Date deliverDate;

    public ChefOrders(int order_id, String name, String address, String MenuName,int status,Date deliverDate) {
        this.order_id = order_id;
        this.name = name;
        this.address = address;
        this.MenuName = MenuName;
        this.status = status;
        this.deliverDate = deliverDate;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public int getStatus() {
        return status;
    }
    
    public String getAddress() {
        return address;
    }

    public String getMenuName() {
        return MenuName;
    }

    public String getName() {
        return name;
    }

    public int getOrder_id() {
        return order_id;
    }
}
