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
		// ���ݿ� IP ��ַ
		String host = "127.0.0.1";
		// ���ݿ�˿�
		int port = 3306;
		// Ҫ���ʵ����ݿ�
		String db = "android";

		// MySQL ���ݿ��¼�˺�
		String user = "root";
		// MySQL ���ݿ��¼����
		String pass = "root";

		// �������ݿ�� URL����ʽΪ��"jdbc:mysql://���ݿ�IP:���ݿ�˿ں�/���ݿ�����?user=MySQL��¼�˺�&password=MySQL��¼����"
		String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user="
				+ user + "&password=" + pass;
		// ���ݲ�ѯ���
		String sql = "SELECT * FROM apress";

		try {
			// ��ȡ MySQL ����
			Class.forName("com.mysql.cj.jdbc.Driver");
			// �������ݿ�
			conn = DriverManager.getConnection(url);

			// ������ѯ������
			PreparedStatement stmt = conn.prepareStatement(sql);
			// ִ�в�ѯ���
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
			// �ر����ݿ�����
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found!");
		} catch (SQLException e) {
			System.out.println("SQL Exception " + e.getMessage());
		}
	}

}
