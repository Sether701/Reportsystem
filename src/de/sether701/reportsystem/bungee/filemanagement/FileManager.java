package de.sether701.reportsystem.bungee.filemanagement;

import java.io.File;
import java.util.ArrayList;

import de.sether701.reportsystem.bungee.main.BungeeMain;
import net.md_5.bungee.api.ChatColor;

public class FileManager {

	private Yaml settingsYaml;
	private Yaml permissionsYaml;
	private Yaml languageYaml;
	
	private static final String PATH_PREFIX = "prefix",
								PATH_MODE = "mode",
								PATH_LANGUAGE = "language";
	
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
		
		BungeeMain.PREFIX = ChatColor.translateAlternateColorCodes('&', settingsYaml.read(PATH_PREFIX) + " &r");
		String mode = (String) settingsYaml.read(PATH_MODE);
		if(modes.contains(mode)) {
			BungeeMain.MODE = mode;
		} else {
			BungeeMain.MODE = "invalid";
		}
		
		/* language */
		
		String lang = (String) settingsYaml.read(PATH_LANGUAGE);
		if(!(lang.equalsIgnoreCase("de") || lang.equalsIgnoreCase("en"))) lang = "en";
		languageYaml = new Yaml(path, "lang_"+lang+".yml");
		
		/* permissions */
		
		permissionsYaml = new Yaml(path, "permissions.yml");
		
		
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
