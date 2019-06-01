import java.util.*;

public class Booking {
	private Visitor user;
	private Calendar start, end;
	private int hotelid;
	private int si, dou, qu;

	public Booking(Visitor us, Calendar st, Calendar e, int id, int s, int d, int q) {
		user = us;
		start = st;
		end = e;
		hotelid = id;
		si = s;
		dou = d;
		qu = q;
	}

	public void change(Calendar st, Calendar e, int s, int d, int q) {
		// can't change to extend the date or change to more room
		if (st.before(start) || e.after(end) || s > si || d > dou || q > qu) {
			System.out.println("can't change");
		} else {
			start = st;
			end = e;
			si = s;
			dou = d;
			qu = q;
		}
	}
//maybe put in the interface layer like test.java
//	public boolean tobook(Calendar st, Calendar e, Hotel h, int s, int d, int q) {
//		if (h.isempty(s, d, q, st, e)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
}
