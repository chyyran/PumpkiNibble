package net.mystia.PumpkiNibble;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PumpkiNibbleCommand implements CommandExecutor
{

	@SuppressWarnings("unused") private PumpkiNibbleMain plugin;

	public PumpkiNibbleCommand(PumpkiNibbleMain plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		sender.sendMessage("This command has been disabled for now");
		return true;
	}
}
