package jdbc.practice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import spring.practice.Chinese;
/**失败的连接池
 * 
 * @author 50448
 *
 */
public class MyconnectMain {
	@Value(value="hzdatabase.DB_URL")
	String DB_URL;
	@Value(value = "hzdatabase.user")
	String USER;
	@Value(value = "hzdatabase.pass")
	String PASS;
	@Value(value = "hzdatabase.hztest")
	String database;
	Myconnector mc;
	
	public void haha() throws ClassNotFoundException, SQLException{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resource/spring.xml");
	
		this.mc=(Myconnector) ctx.getBean("MyconnectorImpl");
		mc.addConn(this.DB_URL, this.USER, this.PASS, this.database);

		Connection conn = mc.getConn(this.database);

		Statement stmt = conn.createStatement();
		String sql;
		sql = "SELECT id, first, last, age FROM employees";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			int age = rs.getInt("age");
			String first = rs.getString("first");
			String last = rs.getString("last");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Age: " + age);
			System.out.print(", First: " + first);
			System.out.println(", Last: " + last);
		}
		rs.close();
		stmt.close();

		mc.closeConn();
	}
	
	public static void main(String[] args) {
		MyconnectMain m= new MyconnectMain();
		try {
			m.haha();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
