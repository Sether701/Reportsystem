package de.sether701.reportsystem.bukkit.pmc;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class BukkitPMC implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {

		if(!(channel.equals("BungeeCord"))) {
			return;
		}
		
		ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
	    String subChannel = in.readUTF();
	    
	    if(subChannel.equals("Reportsystem")) {
	    	// TODO
	    }
		
	}

}
