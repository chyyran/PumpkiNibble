package net.mystia.PumpkiNibble;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PumpkiNibbleMain extends JavaPlugin implements Listener
{
	public void onEnable()
	{

		File config = new File(this.getDataFolder(), "config.yml");
		if (!config.exists())
		{
			this.saveDefaultConfig();
		}

		getLogger().info("PumpkiNibble(tm) 0.5 is nibbling on pumpkin seeds..");
		getLogger().warning(
				"This plugin may lead to excessive and or compulsive consumption of pumpkin seeds");
		getLogger().warning("in Minecraft and Real Life enviroments. ron975 is not liable");
		getLogger().warning("for any addictions, injuries and pumpkin seed shortages relating to");
		getLogger().warning("this plugin and pumpkin seeds.");
		Bukkit.getServer().getPluginManager().registerEvents(new PumpkiNibbleListener(), this);

	}


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("pumpkinibble"))
		{ 
			sender.sendMessage("PumpkiNibble has reloaded it's configuration");
			this.reloadConfig();
			return true;
		} 
			
		return false;
	}

	public void onDisable()
	{
		getLogger().info("PumpkiNibble has been disabled.");
		getLogger().info("No more nibbling on seeds ");
	}
}