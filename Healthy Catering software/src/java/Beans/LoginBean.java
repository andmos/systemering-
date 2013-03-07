package Beans;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import ProblemDomain.Users;
import ProblemDomain.Roles;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.sql.DataSource;

import ProblemDomain.Users;
import ProblemDomain.Users;
/**
 *
 * @author Group 7
 */

@Named("login");
@SessionScoped;        
public class LoginBean implements Serializable {
    //resource injection
	@Resource(name="jdbc/mysql")
	private DataSource ds;

}
