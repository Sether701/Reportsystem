package de.sether701.reportsystem.bungee.main;

import org.bukkit.ChatColor;

import de.sether701.reportsystem.bungee.filemanagement.FileManager;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {

	private static BungeeMain plugin;
	private static FileManager fileManager;
	
	public static String PREFIX;
	public static String MODE;
	
	private static final String CONSOLE_PREFIX_BAD = "§c========== §r"+PREFIX+" §c==========",
								CONSOLE_PREFIX_GOOD = "§a========== §r"+PREFIX+" §a==========",
								CONSOLE_SUFFIX_BAD = "§c===============================",
								CONSOLE_SUFFIX_GOOD = "§a==============================";
	
	@Override
	public void onEnable() {
		plugin = this;
		
		String version = "§cVersion: "+BungeeMain.getPlugin().getDescription().getVersion();
		String author = "§bAuthor: "+BungeeMain.getPlugin().getDescription().getAuthor();
		
		fileManager = new FileManager();
		fileManager.createFiles();
		
		switch (MODE) {
			case "none":
				sendConsoleMessage(CONSOLE_PREFIX_BAD);
				sendConsoleMessage(translateColorCode((String) fileManager.getLanguage().read(FileManager.LANG_UNSUCCESSFUL_PLUGINSTART)));
				sendConsoleMessage(translateColorCode((String) fileManager.getLanguage().read(FileManager.LANG_CONFIG_MODE_none_1)));
				sendConsoleMessage(translateColorCode((String) fileManager.getLanguage().read(FileManager.LANG_CONFIG_MODE_none_2)));
				sendConsoleMessage(version);
				sendConsoleMessage(author);
				sendConsoleMessage(CONSOLE_SUFFIX_BAD);
				return;
			case "invalid":
				sendConsoleMessage(CONSOLE_PREFIX_BAD);
				sendConsoleMessage(translateColorCode((String) fileManager.getLanguage().read(FileManager.LANG_UNSUCCESSFUL_PLUGINSTART)));
				sendConsoleMessage(translateColorCode((String) fileManager.getLanguage().read(FileManager.LANG_CONFIG_MODE_INVALID)));
				sendConsoleMessage(version);
				sendConsoleMessage(author);
				sendConsoleMessage(CONSOLE_SUFFIX_BAD);
				return;
			case "mysql":
				sendConsoleMessage(CONSOLE_PREFIX_BAD);
				sendConsoleMessage(translateColorCode((String) fileManager.getLanguage().read(FileManager.LANG_UNSUCCESSFUL_PLUGINSTART)));
				sendConsoleMessage(translateColorCode((String) fileManager.getLanguage().read(FileManager.LANG_MYSQL_INVALID)));
				sendConsoleMessage(version);
				sendConsoleMessage(author);
				sendConsoleMessage(CONSOLE_SUFFIX_BAD);
				return;
			default:
				sendConsoleMessage(CONSOLE_PREFIX_GOOD);
				sendConsoleMessage(translateColorCode((String) fileManager.getLanguage().read(FileManager.LANG_SUCCESSFUL_PLUGINSTART)));
				sendConsoleMessage(version);
				sendConsoleMessage(author);
				sendConsoleMessage(CONSOLE_SUFFIX_GOOD);
				break;
		}
		
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
	
	public static String translateColorCode(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
}
