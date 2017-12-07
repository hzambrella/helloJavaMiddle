package jdbc.practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**失败的连接池
 * 
 * @author 50448
 *
 */
@Component("MyconnectorImpl")
public class MyconnectorImpl implements Myconnector {
	@Value("${mysql.JDBC_DRIVER:'com.mysql.jdbc.Driver'}")
	private String JDBC_DRIVER;
	private HashMap<String, Connection> connPool;

	// static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/*public MyconnectorImpl(String JDBC_DRIVER) {
		this.JDBC_DRIVER = JDBC_DRIVER;
		this.connPool = new HashMap<String, Connection>();
	}*/

	/**
	 * 
	 * @param DB_URL
	 *            url 协议:子协议:// ip地址 : 端口号，例子:jdbc:mysql:// ip地址 : 端口号
	 * @param user
	 *            数据库用户名
	 * @param pass
	 *            数据库密码
	 * @param dataBaseName
	 *            子数据库名,作为取得连接的键名
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public void addConn(String DB_URL, String user, String pass,
			String dataBaseName) throws SQLException, ClassNotFoundException {
		DB_URL = DB_URL + "/" + dataBaseName;
		Class.forName(JDBC_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, user, pass);
		this.connPool.put(dataBaseName, conn);
	}

	public Connection getConn(String dataBaseName) {
		return this.connPool.get(dataBaseName);
	}

	public void closeConn() {
		Iterator<Entry<String, Connection>> it = this.connPool.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, Connection> entry = it.next();
			try {
				Connection conn = entry.getValue();
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out
						.println("error to  close database " + entry.getKey());
				e.printStackTrace();
			}
		}
	}
	
}
