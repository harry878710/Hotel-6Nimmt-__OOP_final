//import java.io.*;
//import java.sql.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import org.json.*;
//import java.util.*;
//import java.util.Date;
//
//public class test {
//
//	public static void main(String[] args) {
//		Visitor v = new Visitor();
//		v.login("eshan", "hehe");
////json to object start
//		Hotel[] h = new Hotel[1500];
//		try  {
//			FileReader reader = new FileReader("HotelList.txt");
//			JSONTokener jsrc = new JSONTokener(reader);
//			JSONArray jobj = new JSONArray(jsrc);
//			JSONObject j[] = new JSONObject[1500];
//			for (int i = 0; i < 1500; i++) {
//				h[i] = new Hotel();
//				j[i] = jobj.getJSONObject(i);
//				h[i].setID(j[i].getInt("HotelID"));
//				h[i].setLocality(j[i].getString("Locality"));
//				h[i].setHotelStar(j[i].getInt("HotelStar"));
//				h[i].setStreet_Address(j[i].getString("Street-Address"));
//				JSONTokener js = new JSONTokener(j[i].get("Rooms").toString());
//				JSONArray tmp = new JSONArray(js);
//				JSONObject room = tmp.getJSONObject(0);
//				h[i].setsingle(room.getString("RoomType"), room.getInt("RoomPrice"), room.getInt("Number"));
//				room = tmp.getJSONObject(1);
//				h[i].setdoub(room.getString("RoomType"), room.getInt("RoomPrice"), room.getInt("Number"));
//				room = tmp.getJSONObject(2);
//				h[i].setquad(room.getString("RoomType"), room.getInt("RoomPrice"), room.getInt("Number"));
//				// System.out.println(h[i].getHotelStar()+" "+h[i].getID()+"
//				// "+h[i].getLocality());
//				// System.out.println(h[i].getSingle().toString()+h[i].getDoub().toString()+h[i].getQuad().toString());
//			}
////json end
////initial the booking from sql
//			Connection c = null;
//			Statement stmt = null;
//			try {
//				Class.forName("org.sqlite.JDBC");
//				c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
//				c.setAutoCommit(false);
//				stmt = c.createStatement();
//				ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
//				while (rs.next()) {
//					DateFormat df = new SimpleDateFormat("yyyy-M-dd");
//					Date stdate = new Date();
//					Date endate = new Date();
//					stdate = df.parse(rs.getString("START"));
//					endate = df.parse(rs.getString("END"));
//					Calendar start = Calendar.getInstance(Locale.TAIWAN);
//					Calendar end = Calendar.getInstance(Locale.TAIWAN);
//					start.setTime(stdate);
//					end.setTime(endate);
//					long st = start.getTimeInMillis() - Calendar.getInstance(Locale.TAIWAN).getTimeInMillis();
//					long en = end.getTimeInMillis() - Calendar.getInstance(Locale.TAIWAN).getTimeInMillis();
//					st = st / (24 * 60 * 60 * 1000);
//					en = en / (24 * 60 * 60 * 1000);
//					if (st == 0 && start.get(Calendar.DAY_OF_YEAR) != Calendar.getInstance(Locale.TAIWAN)
//							.get(Calendar.DAY_OF_YEAR)) {
//						st += 1;
//					}
//					if (en == 0 && end.get(Calendar.DAY_OF_YEAR) != Calendar.getInstance(Locale.TAIWAN)
//							.get(Calendar.DAY_OF_YEAR)) {
//						en += 1;
//					}
//					for (int i =(int) st; i < en; i++) {
//						if(i>=0) {
//							h[rs.getInt("HOTELID")].getSingle()[i].setNumber(h[rs.getInt("HOTELID")].getSingle()[i].getNumber()-rs.getInt("SINGLE"));
//							h[rs.getInt("HOTELID")].getDoub()[i].setNumber(h[rs.getInt("HOTELID")].getDoub()[i].getNumber()-rs.getInt("DOUBLE"));
//							h[rs.getInt("HOTELID")].getQuad()[i].setNumber(h[rs.getInt("HOTELID")].getQuad()[i].getNumber()-rs.getInt("QUAD"));
//						}
//					}
//				}
//				stmt.close();
//				c.close();
//			} catch (Exception e) {
//				System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			}
////end
//			// System.out.println(Calendar.getInstance(Locale.TAIWAN).getTime());
//			// Room[] r ;
//			// r = h[10].getSingle();
//			// System.out.println(r[3].getDate().getTime());
//			Calendar tmp = Calendar.getInstance(Locale.TAIWAN);
//			Calendar tmp2 = Calendar.getInstance(Locale.TAIWAN);
//			// System.out.println(tmp.get(Calendar.YEAR));
//			tmp.add(Calendar.DAY_OF_MONTH, 15);
//			tmp2.add(Calendar.DAY_OF_MONTH, 18);
//			// System.out.println(tmp.getTime());
//			//Booking b = new Booking(v, tmp, tmp2, 12, 45, 3, 14);
//			//b.change(v,61, tmp, tmp2, 4, 1, 3);
//			//Search se = new Search(10,5,tmp,tmp2);
//			//se.roomsearch(h[1]);
//			// System.out.println();
//			Booking.cancel(v, 65);
//			System.out.println(Booking.tobook(v, tmp, tmp2, h[100], 7, 8, 12));
//			System.out.println(Booking.tobook(v, tmp, tmp2, h[100], 7, 8, 12));
//			System.out.println(Booking.tobook(v, tmp, tmp2, h[100], 7, 8, 12));
//		} catch (IOException e) {
//			System.out.println("error");
//		}
//
//	}
//
//}
