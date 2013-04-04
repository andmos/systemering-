package ProblemDomain;

import java.util.Date;

/**
 *
 * @author
 * havardb
 */
public class Orders {

    public int order_id;
    public int delivered;
    public int menu_id;
    public String order_nr;
    public Date orderDate;
    public int menuCount;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getDelivered() {
        return delivered;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
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

    public void setOrder_nr(String order_nr) {
        this.order_nr = order_nr;
    }

    public String getOrder_nr() {
        return order_nr;
    }

    public int getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(int menuCount) {
        this.menuCount = menuCount;
    }
}
