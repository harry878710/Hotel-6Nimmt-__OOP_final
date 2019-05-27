
public class Visitor {
	//TODO 訂房紀錄?->sql?    身分? 驗證? class 查詢方法?  booking 退訂?修改? exceptions
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
