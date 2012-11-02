package net.mystia.PumpkiNibble;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PumpkiNibblePersonalSettings
{

	private FileConfiguration personalSettings = null;
	private File personalSettingsFile = null;
	
	public void reloadConfig() {
	    if (personalSettingsFile == null) {
	    personalSettingsFile = new File(PumpkiNibbleMain.p.getDataFolder(), "personalsettings.yml");
	    }
	    personalSettings = YamlConfiguration.loadConfiguration(personalSettingsFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = PumpkiNibbleMain.p.getResource("personalsettings.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        personalSettings.setDefaults(defConfig);
	    }
	}
	public FileConfiguration getConfig() {
	    if (personalSettings == null) {
	        this.reloadConfig();
	    }
	    return personalSettings;
	}
	public void savepersonalSettings() {
	    if (personalSettings == null || personalSettingsFile == null) {
	    return;
	    }
	    try {
	        getConfig().save(personalSettingsFile);
	    } catch (IOException ex) {
	        PumpkiNibbleMain.p.getLogger().log(Level.SEVERE, "Could not save config to " + personalSettingsFile, ex);
	    }
	}
	public void saveDefaultConfig() {
	    if (!personalSettingsFile.exists()) {            
	         PumpkiNibbleMain.p.saveResource("personalsettings.yml", false);
	     }
	}
}
