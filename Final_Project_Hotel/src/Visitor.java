
public class Visitor {
	//TODO �q�Ь���?->sql?    ����? ����? class �d�ߤ�k?  booking �h�q?�ק�? exceptions
	private String name;
	private String password;
	public Visitor(String n,String p) {
		name = n;
		password = p;
	}
	public boolean login(String n,String p) {
		if(name.equals(n)&&password.equals(p)) {
			return true;
		}
		else {
			System.out.println("Can't login");
			return false;
		}
	}
	
}
