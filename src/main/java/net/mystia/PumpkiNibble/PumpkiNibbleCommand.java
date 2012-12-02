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
		
		PumpkiNibbleMain.p.reloadConfig();
		sender.sendMessage("[PumpkiNibble] Configuration has been reloaded");
		return true;
	}
}
