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

	public void roomsearch(Hotel h) {
		int s = 0, d = 0, q = 0;
		// s+d+q = rooms s+2d+4q = people -> d+3q = p-r
		// for q = 0~p/4 for d = 0~(p-4*q)/2
		for (q = 0; q <= people / 4; q++) {
			for (d = 0; d <= (people - 4 * q) / 2; d++) {
				if (rooms - q - d == people - 2 * d - 4 * q) {
					s = rooms - q - d;
					if (h.isempty(s, d, q, start, end)) {
						System.out.println(s + " " + d + " " + q + " " + people + " " + rooms);
					}
				}
			}
		}
	}

}
