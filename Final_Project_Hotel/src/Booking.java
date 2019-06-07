import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Booking {
	private Visitor user;
	private Calendar start, end;
	private int hotelid;
	private int si, dou, qu;

	public Booking() {
		;
	}

	public Booking(Visitor us, Calendar st, Calendar e, int id, int s, int d, int q) {
		user = us;
		start = (Calendar) st.clone();
		end = (Calendar) e.clone();
		hotelid = id;
		si = s;
		dou = d;
		qu = q;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name = 'BOOK';");
			int bookid = rs.getInt("seq") + 1;
			System.out.println(bookid);
			String sql = "INSERT INTO BOOK (USER,START,END,HOTELID,SINGLE,DOUBLE,QUAD) " + "VALUES ('" + user.getName()
					+ "', '" + start.get(Calendar.YEAR) + "-" + (start.get(Calendar.MONTH) + 1) + "-"
					+ start.get(Calendar.DAY_OF_MONTH) + "', '" + end.get(Calendar.YEAR) + "-"
					+ (end.get(Calendar.MONTH) + 1) + "-" + end.get(Calendar.DAY_OF_MONTH) + "', " + hotelid + ", " + si
					+ ", " + dou + ", " + qu + " );";
			stmt.executeUpdate(sql);
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception er) {
			System.err.println(er.getClass().getName() + ": " + er.getMessage());
			// System.exit(0);
		}
		// System.out.println("Records created successfully");

	}

	public static boolean cancel(Visitor v, int id) {
		Connection c = null;
		Statement stmt = null;
		String user = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK WHERE ID = " + id + ";");
			user = rs.getString("USER");
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception er) {
			// System.err.println(er.getClass().getName() + ": " + er.getMessage());
			System.out.println("Can't find this id");
			return false;
		}
		if (user.equals(v.getName())) {
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
				c.setAutoCommit(false);
				stmt = c.createStatement();
				String sql = "DELETE FROM BOOK WHERE ID = " + id + ";";
				stmt.executeUpdate(sql);
				stmt.close();
				c.commit();
				c.close();
			} catch (Exception er) {
				// System.err.println(er.getClass().getName() + ": " + er.getMessage());
				System.out.println("Can't find this id");
				return false;
			}
			return true;
		}
		return false;
	}

	public static boolean change(Visitor v, int id, Calendar st, Calendar e, int s, int d, int q) {
		// can't change to extend the date or change to more room\
		int si, dou, qu;
		Calendar start, end;
		start = Calendar.getInstance(Locale.TAIWAN);
		end = Calendar.getInstance(Locale.TAIWAN);
		Connection c = null;
		Statement stmt = null;
		String user = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK WHERE ID = " + id + ";");
			si = rs.getInt("SINGLE");
			dou = rs.getInt("DOUBLE");
			qu = rs.getInt("QUAD");
			user = rs.getString("USER");
			DateFormat df = new SimpleDateFormat("yyyy-M-dd");
			Date stdate = new Date();
			Date endate = new Date();
			stdate = df.parse(rs.getString("START"));
			endate = df.parse(rs.getString("END"));
			// System.out.println(rs.getString("START"));
			// System.out.println(stdate);
			start.setTime(stdate);
			end.setTime(endate);
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception er) {
			System.out.println("Can't find this id");
			return false;
		}
		if (user.equals(v.getName())) {
			if (start.after(st) || end.before(e) || s > si || d > dou || q > qu) {
				if (start.after(st) || end.before(e)) {
					System.out.println("can't change for longer booking");
					return false;
				} else {
					System.out.println("can't change for booking more rooms");
					return false;
				}
			} else {
				start = st;
				end = e;
				si = s;
				dou = d;
				qu = q;
				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
					c.setAutoCommit(false);
					stmt = c.createStatement();
					String sql = "UPDATE BOOK set START = '" + start.get(Calendar.YEAR) + "-"
							+ (start.get(Calendar.MONTH) + 1) + "-" + start.get(Calendar.DAY_OF_MONTH) + "',END = '"
							+ end.get(Calendar.YEAR) + "-" + (end.get(Calendar.MONTH) + 1) + "-"
							+ end.get(Calendar.DAY_OF_MONTH) + "', SINGLE = " + si + ", DOUBLE = " + dou + ", QUAD = "
							+ qu + " where ID =" + id + ";";
					stmt.executeUpdate(sql);
					stmt.close();
					c.commit();
					c.close();
					return true;
				} catch (Exception er) {
					System.err.println(er.getClass().getName() + ": " + er.getMessage());
					return false;
					// System.exit(0);
				}
			}
		}
		System.out.println("wrong user");
		return false;
	}

//maybe put in the interface layer like test.java
	public static boolean tobook(Visitor us, Calendar start, Calendar end, Hotel h, int s, int d, int q) {
		DateFormat df = new SimpleDateFormat("yyyy-M-dd");
		long st = start.getTimeInMillis() - Calendar.getInstance(Locale.TAIWAN).getTimeInMillis();
		long en = end.getTimeInMillis() - Calendar.getInstance(Locale.TAIWAN).getTimeInMillis();
		st = st / (24 * 60 * 60 * 1000);
		en = en / (24 * 60 * 60 * 1000);
		if (st == 0
				&& start.get(Calendar.DAY_OF_YEAR) != Calendar.getInstance(Locale.TAIWAN).get(Calendar.DAY_OF_YEAR)) {
			st += 1;
		}
		if (en == 0 && end.get(Calendar.DAY_OF_YEAR) != Calendar.getInstance(Locale.TAIWAN).get(Calendar.DAY_OF_YEAR)) {
			en += 1;
		}
		if (h.isempty(s, d, q, start, end)) {
			Booking tmp = new Booking(us, start, end, h.getID(), s, d, q);
			for (int i = (int) st; i < en; i++) {
				h.getSingle()[i].setNumber(h.getSingle()[i].getNumber() - s);
				h.getDoub()[i].setNumber(h.getDoub()[i].getNumber() - d);
				h.getQuad()[i].setNumber(h.getQuad()[i].getNumber() - q);
			}
			return true;
		} else {
			for (int i = (int) st; i < en; i++) {
				if (h.getSingle()[i].getNumber() < s) {
					System.out
							.println("Single on " + df.format(h.getSingle()[i].getDate().getTime()) + " is not enough");
				}
				if (h.getDoub()[i].getNumber() < s) {
					System.out
							.println("Double on " + df.format(h.getSingle()[i].getDate().getTime()) + " is not enough");
				}
				if (h.getQuad()[i].getNumber() < s) {
					System.out.println("Quad on " + df.format(h.getSingle()[i].getDate().getTime()) + " is not enough");
				}
			}
			return false;
		}
	}
	public static boolean search(Visitor v,int id) {
		int si, dou, qu, hotelid;
		Calendar start, end;
		DateFormat df = new SimpleDateFormat("yyyy-M-dd");
		Date stdate = new Date();
		Date endate = new Date();
		start = Calendar.getInstance(Locale.TAIWAN);
		end = Calendar.getInstance(Locale.TAIWAN);
		Connection c = null;
		Statement stmt = null;
		String user = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK WHERE ID = " + id + ";");
			si = rs.getInt("SINGLE");
			dou = rs.getInt("DOUBLE");
			qu = rs.getInt("QUAD");
			hotelid = rs.getInt("HOTELID");
			user = rs.getString("USER");
			stdate = df.parse(rs.getString("START"));
			endate = df.parse(rs.getString("END"));
			// System.out.println(rs.getString("START"));
			// System.out.println(stdate);
			start.setTime(stdate);
			end.setTime(endate);
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception er) {
			System.out.println("Can't find this id");
			return false;
		}
		if (user.equals(v.getName())) {
			System.out.println("HotelID "+hotelid);
			System.out.println("Single "+si);
			System.out.println("Double "+dou);
			System.out.println("Quad "+qu);
			System.out.println("StartDate "+df.format(stdate)+" EndDate "+df.format(endate));
			return true;
		}
		return false;
	}
}
