package jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * JDBC事务 关键conn.setAutoCommit(false);
 * 
 * @author 50448
 * 
 */
public class PracticeJDBCTransaction {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hz_test";

	// Database credentials -- 数据库名和密码自己修改
	static final String USER = "root";
	static final String PASS = "haozhoa";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		Savepoint sp1=null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Set auto commit as false.
			conn.setAutoCommit(false);

			// STEP 5: Execute a query to create statment with
			// required arguments for RS example.
			System.out.println("Creating statement...");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			//保存点
			sp1=conn.setSavepoint("haha");
			// STEP 6: INSERT a row into Employees table
			System.out.println("Inserting one row....");
			String SQL = "INSERT INTO Employees (age,first,last) "
					+ "VALUES (106,  'Curry', 'Stephen')";
			stmt.executeUpdate(SQL);
			//保存点使用，必须在commit之前
			//conn.rollback(sp1);
			
			
			// STEP 7: INSERT one more row into Employees table
			SQL = "INSERT INTO Employees  (age,first,last)  "
					+ "VALUES (100, 'Kobe', 'Bryant')";
			stmt.executeUpdate(SQL);

			// STEP 8: Commit data here.
			System.out.println("Commiting data here....");

			// STEP 9: Now list all the available records.
			String sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);
			conn.commit();
			System.out.println("List result set for reference....");
			printRs(rs);

			// STEP 10: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			// If there is an error then rollback the changes.
			System.out.println("Rolling back data here....");
			try {
				if (conn != null)
					//所有操作都被回滚，即使有保存点
					conn.rollback(sp1);
			} catch (SQLException se2) {
				se2.printStackTrace();
			}// end try

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

	public static void printRs(ResultSet rs) throws SQLException {
		// Ensure we start with first row
		rs.beforeFirst();
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
		System.out.println();
	}// end printRs()
}
