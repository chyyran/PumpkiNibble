package net.mystia.PumpkiNibble;


import java.io.File;
import org.bukkit.Bukkit;
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
	private PumpkiNibbleAPIDebug config;
	

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
		//Bukkit.getServer().getPluginManager()
		//		.registerEvents(new PumpkiNibbleListener(), this);
		
		//getCommand("pumpkinibble").setExecutor(new PumpkiNibbleCommand(this));
		
		
		try{
		getLogger().info(config.getEatMessage("pumpkin"));
		}catch (Exception ex){
			ex.printStackTrace();
			getConfig().set("items.pumpkin.messageOnEat", "[DEBUB] EAT MESSAGE");
			saveConfig();
		}
		try
		{
			getLogger().info(config.getValidItemsAsString(","));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		try
		{
			getLogger().info(config.getInsufficientMessage("pumpkin"));
			getConfig().set("items.pumpkin.messageInsufficient", "[DEBUG] NOT ENOUGH MESSAGE");
			saveConfig();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			getLogger().info(getConfig().getString("items.pumpkin.messageUnable"));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			getConfig().set("items.pumpkin.messageUnable", "[DEBUG] NOT ENOUGH MESSAGE");
			saveConfig();
			e.printStackTrace();
		}
		
	}


	public void onDisable() {
		getLogger().info("PumpkiNibble has been disabled.");
		getLogger().info("No more nibbling on seeds ");
		/*try {
			SLAPI.save(config.settings, this.getDataFolder()+File.separator+"usersettings.dat");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}