package de.sether701.reportsystem.bungee.filemanagement;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import de.sether701.reportsystem.bungee.main.BungeeMain;
import de.sether701.reportsystem.bungee.mysql.MySQL;
import de.sether701.reportsystem.bungee.mysql.MySQLTable;
import net.md_5.bungee.api.ChatColor;

public class FileManager {

	private Yaml settingsYaml;
	private Yaml permissionsYaml;
	private Yaml languageYaml;
	private MySQL mysql;
	private boolean mysqlEnabled;
	
	public void createFiles() {
		
		String path = BungeeMain.getPlugin().getDataFolder().getPath();
		File folder = new File(path);
		
		if(!(folder.exists())) {
			folder.mkdirs();
		}
		
		/* settings */
		
		this.settingsYaml = new Yaml(path, "config.yml");
		
		BungeeMain.PREFIX = ChatColor.translateAlternateColorCodes('&', (settingsYaml.read(FilePath.SETTINGS_PATH_PREFIX) + " &r"));
		String mode = (String) settingsYaml.read(FilePath.SETTINGS_PATH_MODE);
		BungeeMain.MODE = mode;
		
		/* language */
		
		String lang = (String) settingsYaml.read(FilePath.SETTINGS_PATH_LANGUAGE);
		if(!(lang.equalsIgnoreCase("de") || lang.equalsIgnoreCase("en"))) lang = "en";
		this.languageYaml = new Yaml(path, "lang_"+lang+".yml");
		
		/* permissions */
		
		this.permissionsYaml = new Yaml(path, "permissions.yml");
		
		/* settings */
		
		switch (mode) {
			case "none": return;
			case "gui": break;
			case "list": break;
			case "custom": break;
			default: BungeeMain.MODE = "invalid"; return;
		}
		
		/* mysql */
		this.mysqlEnabled = Boolean.valueOf((String) settingsYaml.read(FilePath.SETTINGS_PATH_MYSQL));
		Yaml mysqlYaml = new Yaml(path, "mysql.yml");
		if(mysqlEnabled) {
			ArrayList<MySQLTable> tables = new ArrayList<>();
			
			/* archive table */
			
			Map<String, String> archive_columns = ImmutableMap.of(
					"reporter", "VARCHAR(36)", 
					"reported", "VARCHAR(36)", 
					"reason", "VARCHAR(255)",
					"timemillis", "BIGINT"
			);
											
			MySQLTable archive = new MySQLTable("archive", archive_columns);
			
			/* reason table */
			
			Map<String, String> reasons_columns = ImmutableMap.of(								
					"reason", "VARCHAR(255)",
					"displayName", "VARCHAR(255)",
					"material", "VARCHAR(255)",
					"subId", "TINYINT "
					
			);
											
			MySQLTable reasons = new MySQLTable("reasons", reasons_columns);
			
			tables.add(archive);
			tables.add(reasons);
			
			this.mysql = new MySQL((String) mysqlYaml.read(FilePath.MYSQL_PATH_USER),
								(String) mysqlYaml.read(FilePath.MYSQL_PATH_PASSWORD), 
								(String) mysqlYaml.read(FilePath.MYSQL_PATH_DATABASE), 
								(String) mysqlYaml.read(FilePath.MYSQL_PATH_HOST), 
								(String) mysqlYaml.read(FilePath.MYSQL_PATH_PORT), 
								tables);
			
			if(!mysql.connect()) {
				BungeeMain.MODE = "mysql";
			}
		}
		
	}
	
	public Yaml getConfig() {
		return settingsYaml;
	}
	
	public Yaml getLanguage() {
		return languageYaml;
	}
	
	public Yaml getPermissions() {
		return permissionsYaml;
	}
	
}
