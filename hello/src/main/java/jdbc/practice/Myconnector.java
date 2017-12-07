package jdbc.practice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public interface Myconnector {
	public void addConn(String DB_URL, String user, String pass,
			String dataBaseName) throws SQLException, ClassNotFoundException;

	public Connection getConn(String dataBaseName);

	public void closeConn();

}
