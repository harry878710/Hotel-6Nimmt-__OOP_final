import java.util.Calendar;
import java.util.Locale;

public class Hotel {
	private int ID = -1;
	private int HotelStar = -1;
	private String Locality = "";
	private String Street_Address = "";
	private Room[] single = new Room[100];
	private Room[] doub = new Room[100];
	private Room[] quad = new Room[100];
	
	/**
	 * @param s numbers of single rooms
	 * @param d numbers of double rooms
	 * @param q numbers of quad rooms
	 * @param start the date start
	 * @param end the date end
	 * @return boolean if the rooms is empty
	 */
	public boolean isempty(int s, int d, int q, Calendar start, Calendar end) {
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
		if (st >= en) {
			return false;
		} else {
			for (int i = (int) st; i < en; i++) {
				if (single[i].getNumber() >= s && doub[i].getNumber() >= d && quad[i].getNumber() >= q) {
					//System.out.println(st+" "+en+" "+i);
					if (i == (int) en - 1) {
						return true;
					}
				} else {
					break;
				}
			}
			return false;
		}
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getHotelStar() {
		return HotelStar;
	}

	public void setHotelStar(int hotelStar) {
		HotelStar = hotelStar;
	}

	public String getLocality() {
		return Locality;
	}

	public void setLocality(String locality) {
		Locality = locality;
	}

	public String getStreet_Address() {
		return Street_Address;
	}

	public void setStreet_Address(String street_Address) {
		Street_Address = street_Address;
	}

	public void setsingle(String type, int p, int n) {
		for (int i = 0; i < 100; i++) {

			Calendar tmp = Calendar.getInstance(Locale.TAIWAN);
			tmp.add(Calendar.DAY_OF_MONTH, i);
			single[i] = new Room(type, p, n, tmp);
		}
	}

	public void setdoub(String type, int p, int n) {
		for (int i = 0; i < 100; i++) {
			Calendar tmp = Calendar.getInstance(Locale.TAIWAN);
			tmp.add(Calendar.DAY_OF_MONTH, i);
			doub[i] = new Room(type, p, n, tmp);
		}

	}

	public void setquad(String type, int p, int n) {
		for (int i = 0; i < 100; i++) {

			Calendar tmp = Calendar.getInstance(Locale.TAIWAN);
			tmp.add(Calendar.DAY_OF_MONTH, i);
			quad[i] = new Room(type, p, n, tmp);
		}
	}

	public Room[] getSingle() {
		Room[] tmp = new Room[100];
		for (int i = 0; i < 100; i++) {
			tmp[i] = new Room(single[i]);
		}
		return tmp;
	}

	public Room[] getDoub() {
		Room[] tmp = new Room[100];
		for (int i = 0; i < 100; i++) {
			tmp[i] = new Room(doub[i]);
		}
		return tmp;
	}

	public Room[] getQuad() {
		Room[] tmp = new Room[100];
		for (int i = 0; i < 100; i++) {
			tmp[i] = new Room(quad[i]);
		}
		return tmp;
	}

}
