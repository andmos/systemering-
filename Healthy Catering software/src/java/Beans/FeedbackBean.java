package Beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author
 * havardb
 * A bean to handle Growl messages (Primefaces).
 */
@SessionScoped
@Named("feedback")
public class FeedbackBean implements Serializable {

    /**
     * Feedback when you add a menu to your shopping cart.
     * @param actionEvent 
     */
    public void addedToCart(ActionEvent actionEvent) {
        String text = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("menu_name");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", text + " was added to your cart"));

    }
/**
 * Feedback when you remove a menu from your order.
 * @param actionEvent 
 */
    public void removedFromOrder(ActionEvent actionEvent) {
        String name = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("name");
        FacesContext context = FacesContext.getCurrentInstance();
        String order_nr = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("order_nr");
        context.addMessage(null, new FacesMessage("Deleted", name + " was removed from order nr: "+order_nr));
    }
    /**
     * Feedback when you remove a menu from your shopping cart.
     * @param actionEvent 
     */
     public void removedFromCart(ActionEvent actionEvent) {
        String name = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("name");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Removed", name + " was removed from your cart"));

    }
     /**
      * Feedback when you place a order.
      * @param actionEvent 
      */
     public void placedOrder(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "You have placed an order!\nYou can now view it in \"Order history\""));
        context.addMessage(null, new FacesMessage("Successful", "You can now view it in \"Order history\""));
    }
}
