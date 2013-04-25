package ProblemDomain;
/**
 *
 * @author havardb
 */
public class DriverOrders {
public int order_id;
public String name;
public String address;
public String MenuName;

    public DriverOrders(int order_id, String name, String address, String MenuName) {
        this.order_id = order_id;
        this.name = name;
        this.address = address;
        this.MenuName = MenuName;
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
