package model;

public class DBCredentials {
	private static String DBUsername = "root";
	private static String DBPassword = "";
	private static String DBUrl = "jdbc:mysql://localhost/test";
	
	DBCredentials(){
		
	}
	
	
	public static String getUsername() {
		return DBCredentials.DBUsername;
	}
	public static String getPassword() {
		return DBCredentials.DBPassword;
	}
	public static String getURL() {
		return DBCredentials.DBUrl;
	}
	
}
