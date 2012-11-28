package net.mystia.PumpkiNibble;

import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.mystia.PumpkiNibble.PumpkiNibbleAPI;

public class PumpkiNibbleListener implements Listener
{

	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event)
	{

		Player player = event.getPlayer();
		String type = "";
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			/* Check for items */
			if (player.getItemInHand() != null)
			{

				type = PumpkiNibbleAPI.getType(player.getItemInHand().getTypeId(), (int) player.getItemInHand().getDurability());
				System.out.println(type);
				System.out.println(player.getItemInHand().getType());
			}
			/*Check if type exists or is null*/
			if (!PumpkiNibbleAPI.checkType(type))
			{
				System.out.println(player.getItemInHand().getType());
				System.out.println("Checktype Failed");

				return;
			}

			/* Check if the clicked block is blacklisted for the item */
			if (event.getClickedBlock() != null && PumpkiNibbleAPI.getBlacklistedBlocks(type).contains(event.getClickedBlock().getTypeId()))
			{

				System.out.println("Clicked Block was found in blacklist");
				return;
			}
			else
			{

				/* Start Bulk of Code */

				// Assemble messages
				String messageUnable = PumpkiNibbleAPI.getUnableMessage(type);
				String messageOnEat = PumpkiNibbleAPI.getEatMessage(type);
				String messageInsufficient = PumpkiNibbleAPI.getInsufficientMessage(type);

				// Set<String> potionEffects =
				// PumpkiNibbleAPI.getPotionEffects(type);

				// Assemble amounts
				int healFoodAmount = PumpkiNibbleAPI.getHealFoodAmount(type);
				int healHealthAmount = PumpkiNibbleAPI.getHealHealthAmount(type);
				int seedAmount = PumpkiNibbleAPI.getItemAmount(type);

				// State permission
				String permission = PumpkiNibbleAPI.getPermission("nibble", type);

				if (!player.hasPermission(permission) || !player.isOp() || !player.hasPermission("pumpkinibble.nibble.all"))
				{
					player.sendMessage(ChatColor.GOLD + ChatColor.translateAlternateColorCodes('&', messageUnable));
					return;
				}
				else
				{
					/* If the player has enough seeds to trigger */
					if (player.getItemInHand().getAmount() >= seedAmount)
					{
						/* We start to do stuff here, such as assigning effects */

						// This is required to prevent negative items.
						int ItemAmount = player.getItemInHand().getAmount();
						if (ItemAmount == seedAmount)
						{
							player.getInventory().remove(player.getItemInHand());
						}
						else
						{
							player.getItemInHand().setAmount(ItemAmount - seedAmount);
						}
						player.sendMessage(ChatColor.GOLD + ChatColor.translateAlternateColorCodes('&', messageOnEat));

						int FoodLevel = player.getFoodLevel();
						int HealthLevel = player.getHealth();
						int NewFoodLevel = FoodLevel + healFoodAmount;
						int NewHealthLevel = HealthLevel + healHealthAmount;
						player.setFoodLevel(NewFoodLevel);
						player.setHealth(NewHealthLevel);

						/* We now apply potion effects */

						for (String effectname : PumpkiNibbleAPI.getPotionEffects(type))
						{
							try
							{
								player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effectname.toUpperCase(Locale.ENGLISH)),
									PumpkiNibbleAPI.getPotionDuration(type, effectname), PumpkiNibbleAPI.getPotionStrength(type, effectname)));
							}
							catch (NullPointerException e)
							{
								PumpkiNibbleMain.p.getLogger().severe("Potions configuration is invalid in item " + type);
								PumpkiNibbleMain.p.getLogger().severe("Invalid potionEffect is "+effectname);
								continue;
							}

						}

						/* And we're done! */
					}
					else
					{

						/*
						 * If the player does not have enough seeds, we send him
						 * a message telling him/her so
						 */
						player.sendMessage(ChatColor.GOLD + ChatColor.translateAlternateColorCodes('&', messageInsufficient));
						/* If we need to take his items, we do so as well */
						if (PumpkiNibbleAPI.removeItems(type))
						{
							player.setItemInHand(null);
						}
					}

				}

			}
		}
	}
}
