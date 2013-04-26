package HelpClasses;

import java.util.Date;

/**
 *
 * @author havardb
 */
public class DriverOrders {
public int order_id;
public String name;
public String address;
public String MenuName;
public int status;

    public DriverOrders(int order_id, String name, String address, String MenuName,int status) {
        this.order_id = order_id;
        this.name = name;
        this.address = address;
        this.MenuName = MenuName;
        this.status = status;
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
