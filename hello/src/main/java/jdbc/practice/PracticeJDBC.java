package jdbc.practice;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 练习jdbc
 * 
 * @author 50448
 * 
 */
public class PracticeJDBC {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hz_test";

	// Database credentials -- 数据库名和密码自己修改
	static final String USER = "root";
	static final String PASS = "haozhoa";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// createStatement
			createStatement(conn, stmt);
			// 更新
			prepareStatementUpdate(conn);
			// 查询
			prepareStatementQuery(conn);
			// 批处理
			prepareStatementBatch(conn);
			// 流
			StreamingData(conn, stmt);

		} catch (SQLException se) {
			// Handle errors for JDBC
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}// end main

	/**
	 * createStatement
	 * 
	 * @param conn
	 * @param stmt
	 * @throws SQLException
	 */

	public static void createStatement(Connection conn, Statement stmt)
			throws SQLException {
		// createStatement
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT id, first, last, age FROM employees";
		ResultSet rs = stmt.executeQuery(sql);

		System.out.println("createStatement用法");
		while (rs.next()) {
			// Retrieve by column name
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
	}

	/**
	 * 更新
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public static void prepareStatementUpdate(Connection conn)
			throws SQLException {
		System.out.println("prepareStatement用法1，更新");
		String SQL = "Update Employees SET age = ? WHERE id = ?";
		PreparedStatement ptst = conn.prepareStatement(SQL);
		// ptst.setString(1,"aa");
		ptst.setInt(1, 23);
		ptst.setInt(2, 1);
		System.out.println("影响:" + ptst.executeUpdate());
		ptst.close();
	}

	/**
	 * 查询
	 * 
	 * @param conn
	 * @throws SQLException
	 */

	private static void prepareStatementQuery(Connection conn)
			throws SQLException {
		System.out.println("prepareStatement用法2，查询");
		String query1 = "select tb1.id,tb2.employee_money as money,tb1.first as first,tb1.last as last from employees as tb1 "
				+ "left join employees_money as tb2"
				+ " on tb1.id=tb2.employee_id where tb1.id=?";

		PreparedStatement ptst2 = conn.prepareStatement(query1);
		ptst2.setInt(1, 1);
		ResultSet r1s = ptst2.executeQuery();
		while (r1s.next()) {
			System.out.println(r1s.getString("first") + " "
					+ r1s.getString("last") + "的工资 " + r1s.getFloat("money"));
		}
		ptst2.close();

		PreparedStatement ptst3 = conn
				.prepareStatement("select update_time from employees where id=?");
		ptst3.setInt(1, 1);
		ResultSet r2s = ptst3.executeQuery();
		while (r2s.next()) {
			System.out.println("时间为" + r2s.getDate(1));
		}

		ptst3.close();
	}

	/**
	 * 批处理
	 * 
	 * @param conn
	 * @throws SQLException
	 */

	private static void prepareStatementBatch(Connection conn)
			throws SQLException {
		// 批处理例子
		System.out.println("批处理");
		String SQLBatch = "INSERT INTO Employees (first, last, age) "
				+ "VALUES( ?, ?, ?)";

		// Create PrepareStatement object
		PreparedStatement pstmt = conn.prepareStatement(SQLBatch);

		// Set auto-commit to false
		conn.setAutoCommit(false);

		// Set the variables
		// pstmt.setInt(1, 400);
		pstmt.setString(1, "JDBC");
		pstmt.setString(2, "Li");
		pstmt.setInt(3, 33);
		// Add it to the batch
		pstmt.addBatch();

		// Set the variables
		// pstmt.setInt(1, 1);
		pstmt.setString(1, "CSharp");
		pstmt.setString(2, "Liang");
		pstmt.setInt(3, 31);

		// Add it to the batch
		pstmt.addBatch();

		// add more batches
		// Create an int[] to hold returned values
		int[] count = pstmt.executeBatch();

		// Explicitly commit statements to apply changes
		conn.commit();

	}

	private static void StreamingData(Connection conn, Statement stmt)
			throws SQLException, IOException {
		// Register JDBC driver
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// Create a Statement object and build table
		stmt = conn.createStatement();
		createXMLTable(stmt);

		// Open a FileInputStream
		File f = new File("e://xml_data.xml");
		if (!f.exists()) {
			f.createNewFile();
		
			FileOutputStream fout = new FileOutputStream(f);
			//DataOutputStream da=new DataOutputStream(fout);
			//da.write(xmlData().getBytes());
			fout.write(xmlData().getBytes());
			//da.close();
			fout.close();
		}

		long fileLength = f.length();
		FileInputStream fis = new FileInputStream(f);

		// Create PreparedStatement and stream data
		String SQL = "INSERT INTO XML_Data VALUES (?,?)";
		pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, 126);
		pstmt.setAsciiStream(2, fis, (int) fileLength);
		pstmt.execute();

		// Close input stream
		fis.close();

		// Do a query to get the row
		SQL = "SELECT Data FROM XML_Data WHERE id=126";
		rs = stmt.executeQuery(SQL);
		// Get the first row
		if (rs.next()) {
			// Retrieve data from input stream
			InputStream xmlInputStream = rs.getAsciiStream(1);
			int c;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((c = xmlInputStream.read()) != -1)
				bos.write(c);
			// Print results
			System.out.println(bos.toString());
		}
		// Clean-up environment
		rs.close();
		stmt.close();
		pstmt.close();
		conn.close();
	}

	/**
	 * 建立表
	 * 
	 * @param stmt
	 * @throws SQLException
	 */
	public static void createXMLTable(Statement stmt) throws SQLException {
		System.out.println("Creating XML_Data table...");
		// Create SQL Statement
		String streamingDataSql = "CREATE TABLE XML_Data "
				+ "(id INTEGER, Data LONG)";
		// Drop table first if it exists.
		try {
			stmt.executeUpdate("DROP TABLE XML_Data");
		} catch (SQLException se) {
			se.printStackTrace();
		}// do nothing
			// Build table.
		stmt.executeUpdate(streamingDataSql);
	}// end createXMLTable

	public static String xmlData() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<Employee>\r\n    <id>125</id>\r\n    <first>Max</first>\r\n    <last>Su</last>\r\n    <Salary>18000</Salary>\r\n    <Dob>18-08-1978</Dob>\r\n<Employee>";

	}
}
