package chapter16;

import java.sql.*;

public class SQLConnector {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		String dbUrl = "jdbc:mysql://localhost:3306/demo_automationtest_development?autoReconnect=true&useSSL=false";
		// Database Username
		String username = "root";
		// Database Password
		String password = "123456";
		// Load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver");
		// Create Connection to DB
		Connection con = DriverManager.getConnection(dbUrl, username, password);
		// Create Statement Object
		Statement stmt = con.createStatement();

		// Query to Execute
		String insertQuery = "INSERT INTO blogs (title, content) VALUES ('Blog dau tay', 'Toi yeu em3');";
		String selectQuery = "SELECT title, content FROM blogs;";
		// Execute the SQL Query. Store results in ResultSet
		stmt.executeUpdate(insertQuery);
		ResultSet rs = stmt.executeQuery(selectQuery);
		// While Loop to iterate through all data and print results
		while (rs.next()) {
			String title = rs.getString(1);
			String content = rs.getString(2);
			System.out.println(title + "  " + content);
		}
		// closing DB Connection
		con.close();
	}
}
