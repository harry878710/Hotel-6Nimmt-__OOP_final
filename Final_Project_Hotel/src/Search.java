import java.util.Calendar;

public class Search {
	private int rooms;
	private int people;
	private Calendar start, end;

	public Search(int p, int r, Calendar s, Calendar e) {
		rooms = r;
		people = p;
		start = (Calendar) s.clone();
		end = (Calendar) e.clone();
	}

	// to search the input Hotel h has the option for the people and rooms
	public void roomsearch(Hotel h) {
		int s = 0, d = 0, q = 0;
		// s+d+q = rooms s+2d+4q = people -> d+3q = p-r
		// for q = 0~p/4 for d = 0~(p-4*q)/2
		for (q = 0; q <= people / 4; q++) {
			for (d = 0; d <= (people - 4 * q) / 2; d++) {
				if (rooms - q - d == people - 2 * d - 4 * q) {
					s = rooms - q - d;
					if (h.isempty(s, d, q, start, end)) {
						int price = h.getSingle()[0].getPrice() * s + h.getDoub()[0].getPrice() * d
								+ h.getQuad()[0].getPrice() * q;
						System.out.println(s + " " + d + " " + q + " " + people + " " + rooms + " " + price);
					}
				}
			}
		}
	}

	public Hotel[] starsearch(Hotel[] h, int star) {
		Hotel [] list = new Hotel[0];;
		for (int i = 0; i < h.length; i++) {
			if(h[i].getHotelStar()==star) {
				roomsearch(h[i]);
				Hotel[] tmp = new Hotel[list.length+2];
				for(int j = 0; j<list.length;j++) {
					tmp[j] = list[j];
				}
				tmp[tmp.length-1] = h[i];
			}
		}
		return list;
	}
	public void listbyprice(Hotel[] h) {
		
	}
}
