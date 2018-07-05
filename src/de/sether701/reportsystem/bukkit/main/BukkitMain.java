package de.sether701.reportsystem.bukkit.main;

import org.bukkit.plugin.java.JavaPlugin;

import de.sether701.reportsystem.bukkit.pmc.BukkitPMC;

public class BukkitMain extends JavaPlugin {

	private static BukkitMain plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BukkitPMC());
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static BukkitMain getPlugin() {
		return plugin;
	}
}
