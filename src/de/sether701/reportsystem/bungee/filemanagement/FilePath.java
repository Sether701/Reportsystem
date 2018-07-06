package de.sether701.reportsystem.bungee.filemanagement;

public enum FilePath {

	SETTINGS_PATH_PREFIX("prefix"),
	SETTINGS_PATH_MODE("mode"),
	SETTINGS_PATH_LANGUAGE("language"),
	SETTINGS_PATH_MYSQL("mysql"),
			
	MYSQL_PATH_USER("user"),
	MYSQL_PATH_PASSWORD("password"),
	MYSQL_PATH_DATABASE("database"),
	MYSQL_PATH_HOST("host"),
	MYSQL_PATH_PORT("port"),
	
	LANG_UNSUCCESSFUL_PLUGINSTART("console.unsuccessful_pluginstart"),
	LANG_SUCCESSFUL_PLUGINSTART("console.successful_pluginstart"),        
	LANG_CONFIG_MODE_NONE_1("console.config_mode_none_1"),
	LANG_CONFIG_MODE_NONE_2("console.config_mode_none_2"),
	LANG_CONFIG_MODE_INVALID("console.config_mode_invalid"),
	LANG_MYSQL_INVALID("console.mysql_invalid");
	
	private String path;
	
	private FilePath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
}
