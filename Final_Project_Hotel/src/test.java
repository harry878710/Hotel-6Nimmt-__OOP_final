import java.io.*;
import org.json.*;
import java.util.*;
//import java.sql.*;

public class test {

	public static void main(String[] args) {
//json start
		Hotel[] h = new Hotel[1500];
 		try (FileReader reader = new FileReader("HotelList.json");) {
			JSONTokener jsrc = new JSONTokener(reader);
			JSONArray jobj = new JSONArray(jsrc);
			JSONObject j[] = new JSONObject[1500];
			for (int i = 0; i < 1500; i++) {
				h[i] = new Hotel();
				j[i] = jobj.getJSONObject(i);
				h[i].setID(j[i].getInt("HotelID"));
				h[i].setLocality(j[i].getString("Locality"));
				h[i].setHotelStar(j[i].getInt("HotelStar"));
				h[i].setStreet_Address(j[i].getString("Street-Address"));
				JSONTokener js = new JSONTokener (j[i].get("Rooms").toString());
				JSONArray tmp = new JSONArray(js);
				JSONObject room = tmp.getJSONObject(0);
				h[i].setsingle(room.getString("RoomType"),room.getInt("RoomPrice"),room.getInt("Number"));
				room = tmp.getJSONObject(1);
				h[i].setdoub(room.getString("RoomType"),room.getInt("RoomPrice"),room.getInt("Number"));
				room = tmp.getJSONObject(2);
				h[i].setquad(room.getString("RoomType"),room.getInt("RoomPrice"),room.getInt("Number"));
//end
				//System.out.println(h[i].getHotelStar()+" "+h[i].getID()+" "+h[i].getLocality());
				//System.out.println(h[i].getSingle().toString()+h[i].getDoub().toString()+h[i].getQuad().toString());
			}
			//System.out.println(Calendar.getInstance(Locale.TAIWAN).getTime());
			//Room[] r ;
			//r = h[10].getSingle();
			//System.out.println(r[3].getDate().getTime());
			Calendar tmp = Calendar.getInstance(Locale.TAIWAN);
			Calendar tmp2 = Calendar.getInstance(Locale.TAIWAN);
			tmp2.add(Calendar.DAY_OF_MONTH, 3);
			Search se  = new Search(10,5,tmp,tmp2);
			se.roomsearch(h[11]);
			//System.out.println();
		} catch (IOException e) {
			System.out.println("error");
		}

	}

}
