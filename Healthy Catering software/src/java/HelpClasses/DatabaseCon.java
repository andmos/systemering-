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
//resource injection

    @Resource(name = "jdbc/mysql")
    private DataSource ds;
    public Connection con;
    /**
     * Method opens connection to our database, throws exception and console message if failed. 
     */
    public void openConnection() {
        try {
            ds = (DataSource) new InitialContext().lookup("jdbc/mysql");
            if (ds == null) {
                throw new SQLException("No datasource found");
            }
            con = ds.getConnection();
        } catch (Exception e) {
            System.out.println("Error with databaseconnection " + e);
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
            System.out.println("Can't clse connection");
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
            System.out.println("Can't close statement " + e.getMessage());
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
            System.out.println("Cant close result " + e.getMessage());
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
            System.out.println("Cat set AutoCommit "+ e.getMessage());
        }
    }
}