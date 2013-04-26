/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author marius
 * A bean to handle the gallery at the welcome view.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import PrimeFacesHelp.Element;

@Named("gallery")
public class GalleriaBean {

    private ArrayList<Element> element;

    /**
     * Constructor
     */
    public GalleriaBean() {
        this.element = element;
        element = new ArrayList<Element>();
        element.add(new Element("Burger", "125,-", "Burger.jpg"));
        element.add(new Element("Feast", "1350,-", "feast.jpg"));
        element.add(new Element("Rice and goodies", "350,-", "RisNStuff.jpg"));

    }
/**
 * 
 * @return A list of the chosen images.
 */
public ArrayList<Element> getElement() {
        return element;
    }
 
}
