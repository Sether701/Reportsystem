package de.sether701.reportsystem.bungee.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySQL {
	
	private String user;
	private String password;
	private String database;
	private String host;
	private String port;
	private List<MySQLTable> tables;
	
	public static Connection con;
	
	public MySQL(String user, String password, String database, String host, String port, List<MySQLTable> tables) {
		this.user = user;
		this.password = password;
		this.database = database;
		this.host = host;
		this.port = port;
		this.tables = tables;
	}
	
	public boolean connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void disconnect() {
		if(isConnected()) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static boolean isConnected() {
		try {
			Statement statement = con.createStatement();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void createTables() {
		
		for(MySQLTable table : tables) {
			if(isConnected()) {
				try {
					String columns = "";
					for(String columnName : table.getColumns().keySet()) {
						columns = columnName + table.getColumns().get(columnName) + ", ";
					}
					columns = columns.substring(0, columns.length()-2);
					con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS "+table+" ("+columns+")");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void update(String qry) {
		if(isConnected()) {
			try {
				con.createStatement().executeUpdate(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet getResultList(String qry) {
		if(isConnected()) {
			try {
				return con.createStatement().executeQuery(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Object get(String whereresult, String where, String select, String table) {

		ResultSet rs = getResultList("SELECT " + select + " FROM " + table + " WHERE " + where + "='" + whereresult + "'");
		try {
			if(rs.next()) {
				Object v = rs.getObject(select);
				return v;
			}
		} catch (SQLException e) {
			return "ERROR";
		}

		return "ERROR";
	}
}
