package de.sether701.reportsystem.bukkit.main;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMain extends JavaPlugin {

	private static BukkitMain plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static BukkitMain getPlugin() {
		return plugin;
	}
}
