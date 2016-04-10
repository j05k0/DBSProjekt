package Model;

public class Strings {
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/DBS_Projekt";
	static final String USER = "postgres";
	static final String PASS = "dbs123456";
	
	public static String getJdbcDriver() {
		return JDBC_DRIVER;
	}
	public static String getDbUrl() {
		return DB_URL;
	}
	public static String getUser() {
		return USER;
	}
	public static String getPass() {
		return PASS;
	}
}
