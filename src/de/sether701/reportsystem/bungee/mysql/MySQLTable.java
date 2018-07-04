package de.sether701.reportsystem.bungee.mysql;

import java.util.Map;

public class MySQLTable {

	private String name;
	private Map<String, String> columns;
	
	public MySQLTable(String name, Map<String, String> columns) {
		this.name = name;
		this.columns = columns;
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String, String> getColumns() {
		return columns;
	}
	
}
