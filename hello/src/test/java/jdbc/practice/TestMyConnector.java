/*package jdbc.practice;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import spring.practice.Chinese;

*//**
 * 测试连接
 * 
 * @author 50448
 * 
 *//*
public class TestMyConnector {
	//String JDBC_DRIVER;
	@Value(value="hzdatabase.DB_URL")
	String DB_URL;
	@Value(value = "hzdatabase.user")
	String USER;
	@Value(value = "hzdatabase.pass")
	String PASS;
	@Value(value = "hzdatabase.hztest")
	String database;
	Myconnector mc;

	@Before
	public void setUp() {
		//this.JDBC_DRIVER = "com.mysql.jdbc.Driver";
		//this.DB_URL = "jdbc:mysql://localhost:3306";
		//this.database = "hz_test";

		// Database credentials -- 数据库名和密码自己修改
		//this.USER = "root";
		//this.PASS = "haozhoa";
		
		//this.mc = new Myconnector(this.JDBC_DRIVER);
	}

	@Ignore
	@Test
	public void testConnectorPool() throws Exception {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resource/spring.xml");
		this.mc=(Myconnector) ctx.getBean("Myconnector");
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
}
*/