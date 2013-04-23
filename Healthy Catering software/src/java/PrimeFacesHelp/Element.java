/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimeFacesHelp;

/**
 *
 * @author marius
 */
public class Element {
    public String name;
    public String price;
    public String img;
   
   
    public Element(String name,String price,String img){
        this.img=img;
        this.name=name;
        this.price=price;
        
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
    
}
