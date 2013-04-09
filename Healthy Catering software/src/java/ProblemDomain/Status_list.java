package ProblemDomain;

import HelpClasses.DatabaseCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import java.util.*;


public class Status_list {
    
    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private PreparedStatement line = null;
    private ResultSet res = null;
    private String sqlConstructor = "SELECT * FROM orders where status < 0";
    
    
    
    
    
    
    
}
