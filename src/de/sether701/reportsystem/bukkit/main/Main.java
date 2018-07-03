package de.sether701.reportsystem.bukkit.main;

import org.bukkit.plugin.java.JavaPlugin;

import de.sether701.reportsystem.bungee.filemanagement.FileManager;

public class Main extends JavaPlugin {

	private static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		FileManager.createFiles();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Main getPlugin() {
		return plugin;
	}
}
