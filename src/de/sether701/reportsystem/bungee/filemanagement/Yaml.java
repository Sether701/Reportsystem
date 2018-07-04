package de.sether701.reportsystem.bungee.filemanagement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

import de.sether701.reportsystem.bungee.main.BungeeMain;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Yaml {
	
	private File file;
	private Configuration config;
	
	public Yaml(String path, String filename) {
		File file = new File(path, filename);
		if(!(file.exists())) {
			try {
				file.createNewFile();
				this.file = file;
				setStandard();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.file = file;
		try {
			this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setStandard() {
		try(InputStream in = BungeeMain.getPlugin().getResourceAsStream("assets/sether701/reportsystem/" + file.getName());
			OutputStream out = new FileOutputStream(file)) {
			ByteStreams.copy(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object read(String path) {
		return config.get(path);
	}
	
	public Configuration getConfig() {
		return config;
	}
	
	public File getFile() {
		return file;
	}
	
}
