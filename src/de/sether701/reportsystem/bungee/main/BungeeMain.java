package de.sether701.reportsystem.bungee.main;

import de.sether701.reportsystem.bungee.filemanagement.FileManager;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
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
	
	public static void sendConsoleMessage(String message) {
		plugin.getProxy().getConsole().sendMessage(new TextComponent(message));
	}
	
	public static void sendPlayerMessage(ProxiedPlayer player, String message) {
		player.sendMessage(new TextComponent(message));
	}
}
