package net.mystia.PumpkiNibble;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PumpkiNibbleListener implements Listener
{
	private PumpkiNibbleMain plugin;
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event)
	{
		int potionDuration = plugin.getConfig().getInt("pumpkin.potionDuration")*20;
		int potionStrength = plugin.getConfig().getInt("pumpkin.potionStrength");
		
		List<String> potionEffects = plugin.getConfig().getStringList("pumpkin.potionEffects");
		boolean ifEnabled = plugin.getConfig().getBoolean("pumpkin.enabled");
		int healFoodAmount = plugin.getConfig().getInt("pumpkin.healFoodAmount");
		int healHealthAmount = plugin.getConfig().getInt("pumpkin.healHealthAmount");
		int seedAmount = plugin.getConfig().getInt("pumpkin.itemAmount");
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
							if (potionEffects.contains("regen")){
								player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,potionDuration,potionStrength));
							}

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
		boolean ifEnabled = plugin.getConfig().getBoolean("cocoa.enabled");
		int healFoodAmount = plugin.getConfig().getInt("cocoa.healFoodAmount");
		int healHealthAmount = plugin.getConfig().getInt("cocoa.healHealthAmount");
		int seedAmount = plugin.getConfig().getInt("cocoa.itemAmount");
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
		boolean ifEnabled = plugin.getConfig().getBoolean("netherwart.enabled");
		int durationInSeconds = plugin.getConfig().getInt("netherwart.duration");
		int durationInTicks = durationInSeconds * 20;
		int seedAmount = plugin.getConfig().getInt("netherwart.itemAmount");
		int strength = plugin.getConfig().getInt("netherwart.strength");
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
}
