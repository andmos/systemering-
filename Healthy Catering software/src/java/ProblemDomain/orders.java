package ProblemDomain;
/**
 *
 * @author havardb
 */
public class orders {
public int order_id;
public int id;
public boolean delivered;

    public orders() {
    }

public boolean getDelivered(){
    
//    if(1){
//        return true;
//    }else{
//        return false;
//    }
    
    return true; // HUSK Å SKIFTE DETTE!
}

    public int getId() {
        return id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setDelivered(boolean delivered) {
        //samme som getDelivered bare motsatt
        
        this.delivered = delivered;
    }
    


}
