package de.sether701.reportsystem.bukkit.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Main plugin;
	public static String PREFIX;
	
	@Override
	public void onEnable() {
		plugin = this;
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Main getPlugin() {
		return plugin;
	}
}
