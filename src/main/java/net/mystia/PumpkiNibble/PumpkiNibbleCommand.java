package net.mystia.PumpkiNibble;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PumpkiNibbleCommand implements CommandExecutor {
	
	private PumpkiNibbleMain plugin;
	private PumpkiNibbleConfig config;
	public PumpkiNibbleCommand(PumpkiNibbleMain plugin){
		this.plugin = plugin;
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
				plugin.reloadConfig();

				return true;
			}
			if (args[0] == "toggle") {
				if (!isPlayer) {
					sender.sendMessage("This command can only be run by a player");
					return true;
				} else {
					String validItems = config.getValidItemsAsString(" ,");
					if (args[1] == null) {
						sender.sendMessage("You can toggle whether to eat");
						sender.sendMessage(validItems);
						

					} else {
						if (config.getValidItems().contains(args[2])){
							
							if (!config.setPersonalSettings(player.getName(),
									args[2])) {
								sender.sendMessage("Nibbling of" +args[2]+" enabled");
							} else {
								sender.sendMessage("Nibbling of" +args[2]+" disabled");
							}
						}
						else {
							sender.sendMessage("You can toggle whether to eat");
							sender.sendMessage(validItems);
							
						}
					}

				}
			}

		}
		return false;
	}

}
