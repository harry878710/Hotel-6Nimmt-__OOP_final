import java.util.Calendar;

public class Room {
	public String RoomType;
	public int Price;
	public int Number;
	public Calendar date;
	
	public Room(String Type, int p, int n,Calendar d) {
		RoomType = Type;
		Price = p;
		Number = n;
		date = d;
	}

	// Copy constructor
	public Room(Room room) {
		RoomType = room.RoomType;
		Price = room.Price;
		Number = room.Number;
		date = room.date; 
	}
	

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar d) {
		this.date = d;
	}

	public String getRoomType() {
		return RoomType;
	}

	public void setRoomType(String roomType) {
		RoomType = roomType;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public String toString() {
		return "Room [RoomType=" + RoomType + ", Price=" + Price + ", Number=" + Number + ", date=" + date.getTime() + "]";
	}
}
