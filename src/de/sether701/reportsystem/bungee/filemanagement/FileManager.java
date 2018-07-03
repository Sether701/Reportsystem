package de.sether701.reportsystem.bungee.filemanagement;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.ChatColor;

import de.sether701.reportsystem.bungee.main.Main;

public class FileManager {

	private static final String PATH_PREFIX = "prefix",
			PATH_MODE = "mode";
	
	@SuppressWarnings("serial")
	private static final ArrayList<String> modes = new ArrayList<String>() {{
		add("none"); 
		add("custom"); 
		add("list"); 
		add("gui");
	}};
	
	public static void createFiles() {
		
		String path = Main.getPlugin().getDataFolder().getPath();
		File folder = new File(path);
		
		if(!(folder.exists())) {
			folder.mkdirs();
		}
		
		Yaml settingsYaml = new Yaml(path, "config.yml");
		
		Main.PREFIX = ChatColor.translateAlternateColorCodes('&', settingsYaml.read(PATH_PREFIX) + " &r");
		String mode = (String) settingsYaml.read(PATH_MODE);
		if(modes.contains(mode)) {
			Main.MODE = mode;
		} else {
			Main.MODE = "invalid";
		}
		
	}
	
}
