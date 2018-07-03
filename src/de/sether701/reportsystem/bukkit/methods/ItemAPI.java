package de.sether701.reportsystem.bukkit.methods;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemAPI {

	public static ItemStack getGlass(int subId) {
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) subId);
		ItemMeta meta = glass.getItemMeta();
		meta.setDisplayName(" ");
		glass.setItemMeta(meta);
		
		return glass;
	}
	
	public static ItemStack getItem(Material mat, int subId, String displayName) {
		ItemStack item = new ItemStack(mat, 1, (short) subId);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		item.setItemMeta(meta);
		
		return item;
	}
	
}
