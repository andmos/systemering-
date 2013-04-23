/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemDomain;

/**
 *
 * @author espen
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
    
}
