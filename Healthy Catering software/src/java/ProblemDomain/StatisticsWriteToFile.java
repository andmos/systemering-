/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemDomain;

import java.io.*;
import java.util.*;

/**
 *
 * @author marius
 */
public class StatisticsWriteToFile {

    private Statistics stat = new Statistics();
    private String path = "path/hello/ok";
    
    public String getPopularMenu(){
        String content = "";
        List<Statistics_id_count> list = stat.getMostPopularMenu();
        for (Statistics_id_count s: list) {
            content += s.toString();
        }
        return content;
    }
    public String getProfitableCustomers(){
        String content = "";
        List<Statistics_username_priceSum> list = stat.getMostProfitableCustomers();
        for (Statistics_username_priceSum s: list) {
            content += s.toString();
        }
        return content;
    }
    
    public static void main(String[] args) {
        StatisticsWriteToFile s = new StatisticsWriteToFile();
        
        System.out.println(s.getPopularMenu());
    }
}
