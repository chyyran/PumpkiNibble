package net.mystia.PumpkiNibble;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
		Bukkit.getServer().getPluginManager().registerEvents(this, this);

	}

	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event)
	{
		boolean ifEnabled = this.getConfig().getBoolean("pumpkin.enabled");
		int healFoodAmount = this.getConfig().getInt("pumpkin.healFoodAmount");
		int healHealthAmount = this.getConfig().getInt("pumpkin.healHealthAmount");
		int seedAmount = this.getConfig().getInt("pumpkin.itemAmount");
		String permission = "pumpkinibble.nibble.pumpkin";
		Player player = event.getPlayer();
		if (ifEnabled == true)
		{
			if (event.getAction() == Action.RIGHT_CLICK_AIR
					|| (event.getAction() == Action.RIGHT_CLICK_BLOCK
							&& event.getClickedBlock().getType() != Material.SOIL
							&& event.getClickedBlock().getType() != Material.CHEST
							&& event.getClickedBlock().getType() != Material.WOODEN_DOOR
							&& event.getClickedBlock().getType() != Material.STONE_BUTTON
							&& event.getClickedBlock().getType() != Material.LEVER && event
							.getClickedBlock().getType() != Material.TRAP_DOOR))
			{
				if (player.getItemInHand() != null
						&& player.getItemInHand().getType() == Material.PUMPKIN_SEEDS)
				{
					if (player.hasPermission(permission))
					{
						if (player.getItemInHand().getAmount() >= seedAmount)
						{
							int ItemAmount = player.getItemInHand().getAmount();
							if (ItemAmount == seedAmount)
							{
								player.getInventory().remove(player.getItemInHand());
							}
							else
							{
								player.getItemInHand().setAmount(ItemAmount - seedAmount);
							}
							player.sendMessage(ChatColor.GOLD + "You nibbled on some seeds.");

							int FoodLevel = player.getFoodLevel();
							int HealthLevel = player.getHealth();
							int NewFoodLevel = FoodLevel + healFoodAmount;
							int NewHealthLevel = HealthLevel + healHealthAmount;
							player.setFoodLevel(NewFoodLevel);
							player.setHealth(NewHealthLevel);

						}
						else
						{

							player.sendMessage(ChatColor.GOLD
									+ "Too..little..seeds..must..satisfy..cravings!");
							player.getInventory().remove(player.getItemInHand());

						}
					}
					else
					{
						player.sendMessage(ChatColor.GOLD
								+ "Doesn't seem like you can nibble any seeds ");
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerClickCocoa(PlayerInteractEvent event)
	{
		boolean ifEnabled = this.getConfig().getBoolean("cocoa.enabled");
		int healFoodAmount = this.getConfig().getInt("cocoa.healFoodAmount");
		int healHealthAmount = this.getConfig().getInt("cocoa.healHealthAmount");
		int seedAmount = this.getConfig().getInt("cocoa.itemAmount");
		String permission = "pumpkinibble.nibble.cocoa";
		Player player = event.getPlayer();
		if (ifEnabled == true)
		{
			if (event.getAction() == Action.RIGHT_CLICK_AIR
					|| (event.getAction() == Action.RIGHT_CLICK_BLOCK
							&& event.getClickedBlock().getType() != Material.LOG
							&& event.getClickedBlock().getType() != Material.CHEST
							&& event.getClickedBlock().getType() != Material.WOODEN_DOOR
							&& event.getClickedBlock().getType() != Material.STONE_BUTTON
							&& event.getClickedBlock().getType() != Material.LEVER && event
							.getClickedBlock().getType() != Material.TRAP_DOOR))
			{
				if (player.getItemInHand() != null
						&& player.getItemInHand().getType() == Material.INK_SACK
						&& player.getItemInHand().getDurability() == 3)
				{
					if (player.hasPermission(permission))
					{
						if (player.getItemInHand().getAmount() >= seedAmount)
						{
							int ItemAmount = player.getItemInHand().getAmount();
							if (ItemAmount == seedAmount)
							{
								player.getInventory().remove(player.getItemInHand());
							}
							else
							{
								player.getItemInHand().setAmount(ItemAmount - seedAmount);
							}
							player.sendMessage(ChatColor.GOLD + "Mhm..Chocolatey.");

							int FoodLevel = player.getFoodLevel();
							int HealthLevel = player.getHealth();
							int NewFoodLevel = FoodLevel + healFoodAmount;
							int NewHealthLevel = HealthLevel + healHealthAmount;
							player.setFoodLevel(NewFoodLevel);
							player.setHealth(NewHealthLevel);

						}
						else
						{

							player.sendMessage(ChatColor.GOLD + "MOAR CHOCOLATE NAO!!");
							player.getInventory().remove(player.getItemInHand());

						}
					}
					else
					{
						player.sendMessage(ChatColor.GOLD
								+ "Doesn't seem like you can nibble any cocoa ");
					}

				}
			}
		}
	}

	@EventHandler
	public void onPlayerClickWarts(PlayerInteractEvent event)
	{
		boolean ifEnabled = this.getConfig().getBoolean("netherwart.enabled");
		int durationInSeconds = this.getConfig().getInt("netherwart.duration");
		int durationInTicks = durationInSeconds * 20;
		int seedAmount = this.getConfig().getInt("netherwart.itemAmount");
		int strength = this.getConfig().getInt("netherwart.strength");
		String permission = "pumpkinibble.nibble.wart";
		Player player = event.getPlayer();
		if (ifEnabled == true)
		{
			if (event.getAction() == Action.RIGHT_CLICK_AIR
					|| (event.getAction() == Action.RIGHT_CLICK_BLOCK
							&& event.getClickedBlock().getType() != Material.SOUL_SAND
							&& event.getClickedBlock().getType() != Material.CHEST
							&& event.getClickedBlock().getType() != Material.WOODEN_DOOR
							&& event.getClickedBlock().getType() != Material.STONE_BUTTON
							&& event.getClickedBlock().getType() != Material.LEVER && event
							.getClickedBlock().getType() != Material.TRAP_DOOR))
			{
				if (player.getItemInHand() != null
						&& player.getItemInHand().getType() == Material.NETHER_STALK)
				{
					if (player.hasPermission(permission))
					{
						if (player.getItemInHand().getAmount() >= seedAmount)
						{
							int ItemAmount = player.getItemInHand().getAmount();
							if (ItemAmount == seedAmount)
							{
								player.getInventory().remove(player.getItemInHand());
							}
							else
							{
								player.getItemInHand().setAmount(ItemAmount - seedAmount);
							}
							player.sendMessage(ChatColor.GOLD + "Eh he he he");

							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,
									durationInTicks, strength));

						}
						else
						{

							player.sendMessage(ChatColor.GOLD + "Need.. more.. ");
							player.getInventory().remove(player.getItemInHand());

						}
					}
					else
					{
						player.sendMessage(ChatColor.GOLD + "Doesn't seem like you can use"
								+ ChatColor.STRIKETHROUGH + "LSD" + ChatColor.RESET
								+ ChatColor.GOLD + "erh.. nothing");

					}
				}
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("pumpkinibble"))
		{ // If the player typed /basic then do the following...
			sender.sendMessage("PumpkiNibble has reloaded it's configuration");
			this.reloadConfig();
			return true;
		} // If this has happened the function will break and return true. if
			// this hasn't happened the a value of false will be returned.
		return false;
	}

	public void onDisable()
	{
		getLogger().info("PumpkiNibble has been disabled.");
		getLogger().info("No more nibbling on seeds ");
	}
}