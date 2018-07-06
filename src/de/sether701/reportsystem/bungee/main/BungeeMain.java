package de.sether701.reportsystem.bungee.main;

import de.sether701.reportsystem.bungee.filemanagement.FileManager;
import de.sether701.reportsystem.bungee.filemanagement.FilePath;
import de.sether701.reportsystem.bungee.pmc.BungeePMC;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeMain extends Plugin {

	private static BungeeMain plugin;
	private static FileManager fileManager;
	
	public static String PREFIX;
	public static String MODE;
	
	private static String CONSOLE_PREFIX_BAD,
							CONSOLE_PREFIX_GOOD,
							CONSOLE_SUFFIX_BAD = "§c===============================",
							CONSOLE_SUFFIX_GOOD = "§a==============================";
	
	@Override
	public void onEnable() {
		plugin = this;
		
		String version = "§cVersion: "+BungeeMain.getPlugin().getDescription().getVersion();
		String author = "§bAuthor: "+BungeeMain.getPlugin().getDescription().getAuthor();
		
		fileManager = new FileManager();
		fileManager.createFiles();
		
		/* start messages */
		
		CONSOLE_PREFIX_BAD = "§c========== §r"+PREFIX+" §c==========";
		CONSOLE_PREFIX_GOOD = "§a========== §r"+PREFIX+" §a==========";
		
		switch (MODE) {
			case "none":
				sendConsoleMessage(CONSOLE_PREFIX_BAD);
				sendConsoleMessage((String) fileManager.getLanguage().read(FilePath.LANG_UNSUCCESSFUL_PLUGINSTART));
				sendConsoleMessage((String) fileManager.getLanguage().read(FilePath.LANG_CONFIG_MODE_NONE_1));
				sendConsoleMessage((String) fileManager.getLanguage().read(FilePath.LANG_CONFIG_MODE_NONE_2));
				sendConsoleMessage(version);
				sendConsoleMessage(author);
				sendConsoleMessage(CONSOLE_SUFFIX_BAD);
				return;
			case "invalid":
				sendConsoleMessage(CONSOLE_PREFIX_BAD);
				sendConsoleMessage((String) fileManager.getLanguage().read(FilePath.LANG_UNSUCCESSFUL_PLUGINSTART));
				sendConsoleMessage((String) fileManager.getLanguage().read(FilePath.LANG_CONFIG_MODE_INVALID));
				sendConsoleMessage(version);
				sendConsoleMessage(author);
				sendConsoleMessage(CONSOLE_SUFFIX_BAD);
				return;
			case "mysql":
				sendConsoleMessage(CONSOLE_PREFIX_BAD);
				sendConsoleMessage((String) fileManager.getLanguage().read(FilePath.LANG_UNSUCCESSFUL_PLUGINSTART));
				sendConsoleMessage((String) fileManager.getLanguage().read(FilePath.LANG_MYSQL_INVALID));
				sendConsoleMessage(version);
				sendConsoleMessage(author);
				sendConsoleMessage(CONSOLE_SUFFIX_BAD);
				return;
			default:
				sendConsoleMessage(CONSOLE_PREFIX_GOOD);
				sendConsoleMessage((String) fileManager.getLanguage().read(FilePath.LANG_SUCCESSFUL_PLUGINSTART));
				sendConsoleMessage(version);
				sendConsoleMessage(author);
				sendConsoleMessage(CONSOLE_SUFFIX_GOOD);
				break;
		}
		
		PluginManager pm = this.getProxy().getPluginManager();
		pm.registerListener(this, new BungeePMC());
		
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
