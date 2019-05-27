import java.util.*;

public class Booking {
	private Calendar start,end ;
	private int hotelid;
	private int si,dou,qu;
	public Booking(Calendar st,Calendar e ,int id,int s,int d,int q) {
		start = st;
		end = e;
		hotelid = id;
		si = s;
		dou = d;
		qu = q;
	} 
	public void change(Calendar st,Calendar e ,int s,int d,int q) {
		if(st.before(start)||e.after(end)||s>si||d>dou||q>qu) {
			System.out.println("can't change");
		}
		else {
			start = st;
			end = e;
			si = s;
			dou = d;
			qu = q;
		}
	}
}
