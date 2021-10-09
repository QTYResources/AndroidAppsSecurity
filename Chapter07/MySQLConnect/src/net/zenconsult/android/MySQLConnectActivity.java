package net.zenconsult.android;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

public class MySQLConnectActivity {
	
	public static void main(String[] args) {
		Connection conn = null;
		// 数据库 IP 地址
		String host = "127.0.0.1";
		// 数据库端口
		int port = 3306;
		// 要访问的数据库
		String db = "android";

		// MySQL 数据库登录账号
		String user = "root";
		// MySQL 数据库登录密码
		String pass = "root";

		// 连接数据库的 URL，格式为："jdbc:mysql://数据库IP:数据库端口号/数据库名称?user=MySQL登录账号&password=MySQL登录密码"
		String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user="
				+ user + "&password=" + pass;
		// 数据查询语句
		String sql = "SELECT * FROM apress";

		try {
			// 获取 MySQL 驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 连接数据库
			conn = DriverManager.getConnection(url);

			// 构建查询语句对象
			PreparedStatement stmt = conn.prepareStatement(sql);
			// 执行查询语句
			ResultSet rs = stmt.executeQuery();
			Hashtable<String, String> details = new Hashtable<String, String>();
			while (rs.next()) {
				System.out.println("name: " + rs.getString("name") + ", email: " + rs.getString("email"));
				details.put(rs.getString("name"), rs.getString("email"));
			}
			String[] names = new String[details.keySet().size()];
			int x = 0;
			for (Enumeration<String> e = details.keys(); e.hasMoreElements();) {
				names[x] = e.nextElement();
				x++;
			}
			// 关闭数据库连接
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found!");
		} catch (SQLException e) {
			System.out.println("SQL Exception " + e.getMessage());
		}
	}

}
