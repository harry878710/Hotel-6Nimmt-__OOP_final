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
	static private int bookid;
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
			bookid = rs.getInt("seq") + 1;
			//System.out.println(bookid);
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

	public static String change(Visitor v, int id, Calendar st, Calendar e, int s, int d, int q) {
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
			start.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DATE), 0, 0, 0);
			end.setTime(endate);
			end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), end.get(Calendar.DATE), 23, 59, 59);
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception er) {
			return "Can't find this id";
		}
		if (user.equals(v.getName())) {
			if (start.after(st) || end.before(e) || s > si || d > dou || q > qu) {
				if (start.after(st) || end.before(e)) {
					
					return ("can't change for longer booking");
				} else {
					
					return ("can't change for booking more rooms");
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
					return "changing success";
				} catch (Exception er) {
					System.err.println(er.getClass().getName() + ": " + er.getMessage());
					return "error";
					// System.exit(0);
				}
			}
		}
		
		return "wrong user";
	}

//maybe put in the interface layer like test.java
	public static String tobook(Visitor us, Calendar start, Calendar end, Hotel h, int s, int d, int q) {
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
			return ""+bookid;
		} else {
			String tmp = "";
			for (int i = (int) st; i < en; i++) {
				if (h.getSingle()[i].getNumber() < s) {
					tmp = tmp.concat("Single on " + df.format(h.getSingle()[i].getDate().getTime()) + " is not enough\r\n");
				}
				if (h.getDoub()[i].getNumber() < s) {
					tmp = tmp.concat("Double on " + df.format(h.getSingle()[i].getDate().getTime()) + " is not enough\r\n");
				}
				if (h.getQuad()[i].getNumber() < s) {
					tmp = tmp.concat("Quad on " + df.format(h.getSingle()[i].getDate().getTime()) + " is not enough\r\n");
				}
			}
			return tmp;
		}
	}
	public static String[] search(Visitor v,int id) {
		String[] s = new String[7]; 
		for(int i = 0;i<s.length;i++) {
			s[i] = "";
		}
		//int si, dou, qu, hotelid;
		//Calendar start, end;
		//DateFormat df = new SimpleDateFormat("yyyy-M-dd");
		//Date stdate = new Date();
		//Date endate = new Date();
		//start = Calendar.getInstance(Locale.TAIWAN);
		//end = Calendar.getInstance(Locale.TAIWAN);
		Connection c = null;
		Statement stmt = null;
		//String user = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK WHERE ID = " + id + ";");
			s[0] = rs.getString("SINGLE");
			s[1] = rs.getString("DOUBLE");
			s[2] = rs.getString("QUAD");
			s[3] = rs.getString("HOTELID");
			s[4] = rs.getString("USER");
			s[5] = rs.getString("START");
			s[6] = rs.getString("END");
			
			// System.out.println(rs.getString("START"));
			// System.out.println(stdate);
			//start.setTime(stdate);
			//end.setTime(endate);
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception er) {
			s[0] = "We didn't find this reservation.";
			return s;
		
		}
		if (s[4].equals(v.getName())) {
			return s;
		}
		s[0] = "Wrong userID.";
		return s;
	}
}
