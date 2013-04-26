/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpClasses;

/**
 *
 * @author espen
  * Help-class to handle statistic object for arraylist.
 */
public class Statistics_username_priceSum {
    private String username;
    private double priceSum;
    
    public Statistics_username_priceSum(String username, double priceSum){
        this.username = username;
        this.priceSum = priceSum;
    }
    public String getUsername(){
        return username;
    }
    public double getPriceSum(){
        return priceSum;
    }
    public String toString(){
        return "Username: "+username+" Total income: "+priceSum + "\n";
    }
    
}
