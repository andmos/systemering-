
 
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 
import ProblemDomain.Users;
import javax.inject.Named;
 
@Named("customer")
@SessionScoped
public class eksempel implements Serializable{
 
	//resource injection
	@Resource(name="jdbc/mysql")
	private DataSource ds;
 
	//connect to DB and get customer list
	public List<Users> getCustomerList() throws SQLException{
 
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		Connection con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select id, name, address from users"); 
 
		//get customer data from database
		ResultSet result =  ps.executeQuery();
 
		List<Users> list = new ArrayList<Users>();
 
		while(result.next()){
			Users users = new Users(); 
         
			users.setName(result.getString("name"));
                        users.setAddress(result.getString("address"));
 
			//store all data into a List
			list.add(users);
		}
 
		return list;
	}
}