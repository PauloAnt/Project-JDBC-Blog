package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null) {	
			try {
				Properties prop = loadProperties();
				conn = DriverManager.getConnection(prop.getProperty("dburl"), prop.getProperty("user"), prop.getProperty("password"));
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		} 
		System.out.println("Conexão com o banco estabelecidade com sucesso!");
		return conn;
	}
	
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties prop = new Properties();
			prop.load(fs);
			return prop;
			
		} catch (IOException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				System.out.println("Conexão com o banco fechada com sucesso!");
				conn.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
}
