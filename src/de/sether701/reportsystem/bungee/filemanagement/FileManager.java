package de.sether701.reportsystem.bungee.filemanagement;

import java.io.File;
import java.util.ArrayList;

import de.sether701.reportsystem.bungee.main.BungeeMain;
import net.md_5.bungee.api.ChatColor;

public class FileManager {

	private Yaml settingsYaml;
	
	private static final String PATH_PREFIX = "prefix",
								PATH_MODE = "mode";
	
	@SuppressWarnings("serial")
	private static final ArrayList<String> modes = new ArrayList<String>() {{
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
		
		settingsYaml = new Yaml(path, "config.yml");
		
		BungeeMain.PREFIX = ChatColor.translateAlternateColorCodes('&', settingsYaml.read(PATH_PREFIX) + " &r");
		String mode = (String) settingsYaml.read(PATH_MODE);
		if(modes.contains(mode)) {
			BungeeMain.MODE = mode;
		} else {
			BungeeMain.MODE = "invalid";
		}
		
	}
	
	public Yaml getConfig() {
		return settingsYaml;
	}
	
}
