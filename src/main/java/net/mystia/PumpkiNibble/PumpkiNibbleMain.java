package net.mystia.PumpkiNibble;


import java.io.File;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import net.mystia.PumpkiNibble.PumpkiNibbleAPI;
/** PumpkiNibble
 * Plugin for Bukkit
 * Allows users to eat PumpkinSeeds and more!
 * 
 * Main class
 * @author ron975
 */
public class PumpkiNibbleMain extends JavaPlugin implements Listener {
	public static PumpkiNibbleMain p;
	public static PumpkiNibblePersonalSettings personalSettings;
	public void onEnable() {
		p = this;
		/*Checks for configuration and personal settings files*/
		File PumpkiNibbleConfigFile = new File(this.getDataFolder(), "config.yml");
		if (!PumpkiNibbleConfigFile.exists()) {
			this.saveDefaultConfig();
		}
		
		File PumpkiNibblePersonalSettingsFile = new File(this.getDataFolder(), "personalsettings.yml");
		if (!PumpkiNibblePersonalSettingsFile.exists()) {
			personalSettings.saveDefaultConfig();
		}

		getLogger().info("PumpkiNibble(tm) 1.0 is nibbling on pumpkin seeds..");
		getLogger()
				.warning(
						"This plugin may lead to excessive and or compulsive consumption of pumpkin seeds");
		getLogger().warning(
				"in Minecraft and Real Life enviroments. ron975 is not liable");
		getLogger()
				.warning(
						"for any addictions, injuries and pumpkin seed shortages relating to");
		getLogger().warning("this plugin and pumpkin seeds.");
		/*Register events*/
		getServer().getPluginManager()
				.registerEvents(new PumpkiNibbleListener(), this);
		
		getCommand("pumpkinibble").setExecutor(new PumpkiNibbleCommand(this));
		
		getLogger().info(PumpkiNibbleAPI.getEatMessage(PumpkiNibbleType.PUMPKIN));
		
	}
		
		

		

	

	public void onDisable() {
		getLogger().info("PumpkiNibble has been disabled.");
		getLogger().info("No more nibbling on seeds ");
		try {
			SLAPI.save(PumpkiNibbleAPI.settings, this.getDataFolder()+File.separator+"usersettings.dat");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}