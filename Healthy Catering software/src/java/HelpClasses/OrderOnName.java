package HelpClasses;

import java.util.Date;

/**
 *
 * @author havardb
 */
public class OrderOnName {
public int order_id;
public Date orderDate;
public int status;
public String name;
public double price;
public int order_nr;
public Date deliverDate;

    public OrderOnName(int order_id, Date orderDate, int status, String name, double price,int order_nr,Date deliverDate) {
        this.order_id = order_id;
        this.orderDate = orderDate;
        this.status = status;
        this.name = name;
        this.price = price;
        this.order_nr = order_nr;
        this.deliverDate = deliverDate;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public int getOrder_nr() {
        return order_nr;
    }

    public String getName() {
        return name;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getOrder_id() {
        return order_id;
    }

    public double getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }
}
