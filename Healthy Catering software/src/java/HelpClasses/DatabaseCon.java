package HelpClasses;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author havardb
 */
public class DatabaseCon {
//resource injection
	@Resource(name="jdbc/mysql")
	private DataSource ds;
        public Connection con;
       
        public DatabaseCon()  throws SQLException{
            	if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		this.con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
        }
        
       public Connection getConnection(){
           return this.con;
       }
}
