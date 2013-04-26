package HelpClasses;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.servlet.jsp.jstl.sql.Result;
import javax.sql.DataSource;
import javax.naming.InitialContext;

/**
 *
 * @author
 * havardb
 */
public class DatabaseCon {

    @Resource(name = "jdbc/mysql")
    
    String mainDB = "jdbc/mysql"; 
    String backubDB = "jdbc/mysqlBackup"; 
    
    private DataSource ds;
    public Connection con;
   
    /**
     * Method opens connection to our database. If the main database fails, 
     * the system will try a secondary backup DB. throws exception and console message if failed. 
     */
    public void openConnection() {
        try {
          try{ 
            ds = (DataSource) new InitialContext().lookup(mainDB);
          }catch (Exception e) {
              ds = (DataSource) new InitialContext().lookup(backubDB);
          }
            if (ds == null) {
                
                throw new SQLException("No datasource found");  
             } 
            
            con = ds.getConnection();
        } catch (Exception e) {
            WriteMessage(e, "openConnection()");
        }
    }
    /**
     * Method closes database connection, throws exception and console message if failed. 
     */
    public void closeConnection() {
        try {
            if (this.con != null) {
                this.con.close();
            }
        } catch (SQLException e) {
            WriteMessage(e, "closeConnection()");
        }
    }
    /**
     * 
     * @param stm takes inn and closes a SQL - statement. 
     */
    public void closeStatement(Statement stm) {
        try {
            if (stm != null) {
                stm.close();
            }
        } catch (SQLException e) {
            WriteMessage(e, "closeStatement()");
        }
    }
    
    /**
     * 
     * @param res Takes inn and closes a resultset - object. 
     */
    public void closeResSet(ResultSet res) {
        try {
            if (res != null) {
                res.close();
            }
        } catch (SQLException e) {
            WriteMessage(e, "closeResSet()");
        }
    }
    /**
     * 
     * @return returns the connection itself. 
     */
    public Connection getConnection() {
        return this.con;
    }
    /**
     * method sets autocommit to true.  
     */
    public void setAutoCommit() {
        try {
            if (this.con != null && !this.con.getAutoCommit()) {
                this.con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            WriteMessage(e, "setAutoCOmmit()");
        }
    }
    
    /**
     * Method to print message to the error log.
     * @param e Exception
     * @param message Message to print to the error log
     */
     public void WriteMessage(Exception e, String message) {
        System.err.println("*** Feil oppstått: " + message + ". ***");
        e.printStackTrace(System.err);
    }
}