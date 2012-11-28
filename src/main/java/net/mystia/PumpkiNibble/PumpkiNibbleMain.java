package net.mystia.PumpkiNibble;


import java.io.File;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
/** PumpkiNibble
 * Plugin for Bukkit
 * Allows users to eat PumpkinSeeds and more!
 * 
 * Main class
 * @author ron975
 */
public class PumpkiNibbleMain extends JavaPlugin implements Listener {
	public static PumpkiNibbleMain p;

	public void onEnable() {
		p = this;
		/*Checks for configuration and personal settings files*/
		File PumpkiNibbleConfigFile = new File(this.getDataFolder(), "config.yml");
		if (!PumpkiNibbleConfigFile.exists()) {
			this.saveDefaultConfig();
		}
		this.reloadConfig();
		
		getLogger().info("PumpkiNibble is ready to munch");
		/*Register events*/
		getServer().getPluginManager()
				.registerEvents(new PumpkiNibbleListener(), this);
		
		getCommand("pumpkinibble").setExecutor(new PumpkiNibbleCommand(this));
		

	}
		
		

		

	

	public void onDisable() {
		getLogger().info("PumpkiNibble has been disabled.");
		getLogger().info("No more nibbling on seeds ");
		
	}
}