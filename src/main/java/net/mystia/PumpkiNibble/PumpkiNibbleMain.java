package net.mystia.PumpkiNibble;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import net.mystia.PumpkiNibble.PumpkiNibbleConfig;
public class PumpkiNibbleMain extends JavaPlugin implements Listener {
	private PumpkiNibbleConfig config;
	
	public void onEnable() {

		File configfile = new File(this.getDataFolder(), "config.yml");
		if (!configfile.exists()) {
			this.saveDefaultConfig();
		}
		File data = new File(this.getDataFolder(), "usersettings.dat");
		if (data.exists()){
			try {
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
		Bukkit.getServer().getPluginManager()
				.registerEvents(new PumpkiNibbleListener(), this);

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		boolean isPlayer = false;
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
			isPlayer = true;

		}

		if (cmd.getName().equalsIgnoreCase("pumpkinibble")) {
			if (args[0] == "reload") {

				if (sender.hasPermission(config.getPermission("general",
						"command"))) {

				}

				sender.sendMessage("PumpkiNibble has reloaded it's configuration");
				this.reloadConfig();

				return true;
			}
			if (args[0] == "toggle") {
				if (!isPlayer) {
					sender.sendMessage("This command can only be run by a player");
					return true;
				} else {
					if (args[1] == null) {
						sender.sendMessage("You can toggle whether to eat");
						sender.sendMessage("pumpkin, cocoa, netherwart, milk, spidereye, fermentedeye, slimeball, wheat");

					} else {
						if (args[2] == "pumpkin")

							if (!config.setPersonalSettings(player.getName(),
									args[2])) {
								sender.sendMessage("Nibbling of Pumpkin Seeds enabled");
							} else {
								sender.sendMessage("Nibbling of Pumpkin Seeds disabled");
							}
						else {
							sender.sendMessage("You can toggle whether to eat");
							sender.sendMessage("pumpkin, cocoa, netherwart, milk, spidereye, fermentedeye, slimeball, wheat");
						}
					}

				}
			}

		}
		return false;
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