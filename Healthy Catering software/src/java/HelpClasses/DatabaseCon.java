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

    public void openConnection()  {
   try {
            ds = (DataSource) new InitialContext().lookup("jdbc/mysql");
            if (ds == null) {
                throw new SQLException("No datasource found");
            }
            con = ds.getConnection();
            System.out.println("Connected to datasource!");


        } catch (Exception e) {
            System.out.println("Error with databaseconnection " + e);
        }
    }
    
    public void closeConnection() {
        try {
            if (this.con != null) {
                this.con.close();
            }
        } catch (SQLException e) {
            System.out.println("Can't clse connection");
        }
    }
     public void closeStatement(Statement stm) {
        try {
            if (stm != null) {
                stm.close();
            }
        } catch (SQLException e) {
            System.out.println("Can't close statement " + e.getMessage());
        }
    }
      public void closeResSet(ResultSet res) {
        try {
            if (res != null) {
                res.close();
            }
        } catch (SQLException e) {
            System.out.println("Cant close result " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.con;
    }
}
