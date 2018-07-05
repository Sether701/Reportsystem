package de.sether701.reportsystem.bungee.pmc;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeePMC implements Listener {

	@EventHandler
	public void onPluginMessage(PluginMessageEvent event) {
		if(event.getTag().equalsIgnoreCase("BungeeCord")) {
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
			try {
				String subChannel = in.readUTF();
				if(subChannel.equals("Reportsystem")) {
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
