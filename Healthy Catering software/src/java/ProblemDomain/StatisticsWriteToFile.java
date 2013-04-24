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

    private static Statistics stat = new Statistics();
    private String path = "path/hello/ok";
    
    public static String getPopularMenu(){
        String content = "";
        System.out.println("JAIJ??????????????????????????????");
        List<Statistics_id_count> list = stat.getMostPopularMenu();
        System.out.println("JAAAAAAIJ" + list.get(0));
        for (Statistics_id_count s: list) {
            content += s.toString();
        }
        return content;
    }
    public static String getProfitableCustomers(){
        String content = "";
        List<Statistics_username_priceSum> list = stat.getMostProfitableCustomers();
        for (Statistics_username_priceSum s: list) {
            content += s.toString();
        }
        return content;
    }
    
    public static void main(String[] args) {
        //StatisticsWriteToFile s = new StatisticsWriteToFile();
        
        System.out.println(getPopularMenu());
    }
}
