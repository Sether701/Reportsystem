package de.sether701.reportsystem.bungee.main;

import de.sether701.reportsystem.bungee.filemanagement.FileManager;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {

	private static BungeeMain plugin;
	private static FileManager fileManager;
	
	public static String PREFIX;
	public static String MODE;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		fileManager = new FileManager();
		fileManager.createFiles();
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static BungeeMain getPlugin() {
		return plugin;
	}
}
