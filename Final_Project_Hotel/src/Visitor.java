import java.sql.*;

public class Visitor {
	// TODO 訂房紀錄?->sql? 身分? 驗證? class 查詢方法? booking 退訂?修改? exceptions
	private String name = null;
	private String password = null;

//加入同帳號不能建立
	public boolean newVisitor(String n, String p) {
		name = n;
		password = p;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Login;");
			while (rs.next()) {
				if (n.equals(rs.getString("NAME"))) {
					System.out.println("used name");
					rs.close();
					stmt.close();
					c.close();
					return false;
				}
			}
			if (!rs.next()) {
				String sql = "INSERT INTO Login (NAME,PASSWORD) " + "VALUES ('" + n + "' ,'" + p + "' " + ");";
				stmt.executeUpdate(sql);
				rs.close();
				stmt.close();
				c.commit();
				c.close();
				return true;
			}
			return false;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
		return false;
	}

	public boolean login(String n, String p) {
//		if (name.equals(n) && password.equals(p)) {
//			return true;
//		} else {
//			System.out.println("Can't login");
//			return false;
//		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Login;");
			while (rs.next()) {
				if (n.equals(rs.getString("NAME"))) {
					if (p.equals(rs.getString("PASSWORD"))) {
						rs.close();
						stmt.close();
						c.close();
						return true;
					} else {
						rs.close();
						stmt.close();
						c.close();
						System.out.println("wrong password");
						return false;
					}
				}
			}
			System.out.println("Can't login");
			rs.close();
			stmt.close();
			c.close();
			return false;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return true;
	}

}
