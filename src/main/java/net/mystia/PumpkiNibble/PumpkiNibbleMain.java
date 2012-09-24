package net.mystia.PumpkiNibble;


import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import net.mystia.PumpkiNibble.PumpkiNibbleConfig;
/** PumpkiNibble
 * Plugin for Bukkit
 * Allows users to eat PumpkinSeeds and more!
 * 
 * Main class
 * @author ron975
 */
public class PumpkiNibbleMain extends JavaPlugin implements Listener {
	private PumpkiNibbleConfig config;
	

	public void onEnable() {

		/*Checks for configuration and personal settings files*/
		File configfile = new File(this.getDataFolder(), "config.yml");
		if (!configfile.exists()) {
			this.saveDefaultConfig();
		}
		File data = new File(this.getDataFolder(), "usersettings.dat");
		if (data.exists()){
			try {
				/*Load data files using SLAPI*/
				config.settings = SLAPI.load(this.getDataFolder()+ File.separator+"usersettings.dat");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		Bukkit.getServer().getPluginManager()
				.registerEvents(new PumpkiNibbleListener(), this);
		
		getCommand("pumpkinibble").setExecutor(new PumpkiNibbleCommand(this));
		
	}


	public void onDisable() {
		getLogger().info("PumpkiNibble has been disabled.");
		getLogger().info("No more nibbling on seeds ");
		try {
			SLAPI.save(config.settings, this.getDataFolder()+File.separator+"usersettings.dat");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}