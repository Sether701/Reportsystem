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
	
	private static final String SETTINGS_PATH_PREFIX = "prefix",
								SETTINGS_PATH_MODE = "mode",
								SETTINGS_PATH_LANGUAGE = "language",
								SETTINGS_PATH_MYSQL = "mysql",
								
								MYSQL_PATH_USER = "user",
								MYSQL_PATH_PASSWORD = "password",
								MYSQL_PATH_DATABASE = "database",
								MYSQL_PATH_HOST = "host",
								MYSQL_PATH_PORT = "port";
	
	public static final String LANG_UNSUCCESSFUL_PLUGINSTART = "console.unsuccessful_pluginstart",
						  		LANG_SUCCESSFUL_PLUGINSTART = "console.successful_pluginstart",        
						  		LANG_CONFIG_MODE_none_1 = "console.config_mode_none_1",
						  		LANG_CONFIG_MODE_none_2 = "console.config_mode_none_2",
						  		LANG_CONFIG_MODE_INVALID = "console.config_mode_invalid",
						  		LANG_MYSQL_INVALID = "console.mysql_invalid";
	
	@SuppressWarnings("serial")
	private final ArrayList<String> modes = new ArrayList<String>() {{
		add("none"); 
		add("custom"); 
		add("list"); 
		add("gui");
	}};
	
	public void createFiles() {
		
		String path = BungeeMain.getPlugin().getDataFolder().getPath();
		File folder = new File(path);
		
		if(!(folder.exists())) {
			folder.mkdirs();
		}
		
		/* settings */
		
		settingsYaml = new Yaml(path, "config.yml");
		
		BungeeMain.PREFIX = ChatColor.translateAlternateColorCodes('&', settingsYaml.read(SETTINGS_PATH_PREFIX) + " &r");
		String mode = (String) settingsYaml.read(SETTINGS_PATH_MODE);
		if(modes.contains(mode)) {
			BungeeMain.MODE = mode;
		} else {
			BungeeMain.MODE = "invalid";
		}
		
		/* language */
		
		String lang = (String) settingsYaml.read(SETTINGS_PATH_LANGUAGE);
		if(!(lang.equalsIgnoreCase("de") || lang.equalsIgnoreCase("en"))) lang = "en";
		languageYaml = new Yaml(path, "lang_"+lang+".yml");
		
		/* permissions */
		
		permissionsYaml = new Yaml(path, "permissions.yml");
		
		/* mysql */
		mysqlEnabled = Boolean.valueOf((String) settingsYaml.read(SETTINGS_PATH_MYSQL));
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
			
			mysql = new MySQL((String) mysqlYaml.read(MYSQL_PATH_USER),
								(String) mysqlYaml.read(MYSQL_PATH_PASSWORD), 
								(String) mysqlYaml.read(MYSQL_PATH_DATABASE), 
								(String) mysqlYaml.read(MYSQL_PATH_HOST), 
								(String) mysqlYaml.read(MYSQL_PATH_PORT), 
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
