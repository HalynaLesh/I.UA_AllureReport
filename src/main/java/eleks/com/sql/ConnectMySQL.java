package eleks.com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMySQL {
	

	public Connection connectMySQL() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/selenium", "root", "password");
		return connection;
	}
	
	public ResultSet executionQuery() throws ClassNotFoundException, SQLException {
		Statement statement = connectMySQL().createStatement();
		ResultSet query = statement.executeQuery("select * from loginiua");
		return query;
	}

}
