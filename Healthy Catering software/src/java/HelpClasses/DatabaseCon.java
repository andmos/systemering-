package HelpClasses;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.servlet.jsp.jstl.sql.Result;
import javax.sql.DataSource;

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

    public void openConnection() throws SQLException {
        if (ds == null) {
            throw new SQLException("Can't get data source");
        }

        //get database connection
        this.con = ds.getConnection();

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
    }
    
    public void closeConnection() throws SQLException{
        try {
            if (this.con != null) {
                this.con.close();
            }
        } catch (SQLException e) {
            throw new SQLException("Can't clse connection");
        }
    }
     public void closeStatement(Statement stm) throws SQLException {
        try {
            if (stm != null) {
                stm.close();
            }
        } catch (SQLException e) {
            throw new SQLException("Can't close statement");
        }
    }
      public static void closeResSet(ResultSet res) throws SQLException {
        try {
            if (res != null) {
                res.close();
            }
        } catch (SQLException e) {
            throw new SQLException("Cant close result");
        }
    }

    public Connection getConnection() {
        return this.con;
    }
}
