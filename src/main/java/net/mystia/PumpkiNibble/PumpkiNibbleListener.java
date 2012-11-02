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
import net.mystia.PumpkiNibble.PumpkiNibbleAPI;

public class PumpkiNibbleListener implements Listener
{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		String type = null;
		if (event.getAction() == Action.RIGHT_CLICK_AIR
			|| (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() != Material.SOIL
				&& event.getClickedBlock().getType() != Material.CHEST && event.getClickedBlock().getType() != Material.WOODEN_DOOR
				&& event.getClickedBlock().getType() != Material.STONE_BUTTON && event.getClickedBlock().getType() != Material.LEVER && event
				.getClickedBlock().getType() != Material.TRAP_DOOR))
		{
			/* Check for items */
			if (player.getItemInHand() != null)
			{
				if (player.getItemInHand().getType() == Material.PUMPKIN_SEEDS)
				{
					type = PumpkiNibbleType.PUMPKIN;
				}
				else
				{
					if (player.getItemInHand().getType() == Material.INK_SACK && player.getItemInHand().getDurability() == 3)
					{
						type = PumpkiNibbleType.COCOA;
					}
					else
					{
						if (player.getItemInHand().getType() == Material.NETHER_STALK)
						{
							if (event.getClickedBlock().getType() != Material.SOUL_SAND)
							{
								type = PumpkiNibbleType.NETHERWART;
							}
						}
						else
						{
							return;
						}
					}
				}
			}
			else
			{
				if (player.getItemInHand().getType() == Material.MILK_BUCKET)
				{
					type = PumpkiNibbleType.MILK;
				}
				else
				{
					if (player.getItemInHand().getType() == Material.WHEAT)
					{
						type = PumpkiNibbleType.WHEAT;
					}
					else
					{
						if (player.getItemInHand().getType() == Material.FERMENTED_SPIDER_EYE)
						{
							type = PumpkiNibbleType.FERMENTED_EYE;
						}
						else
						{
							if (player.getItemInHand().getType() == Material.SLIME_BALL)
							{
								type = PumpkiNibbleType.SLIMEBALL;
							}
							else
							{
								if (player.getItemInHand().getType() == Material.SUGAR)
								{
									type = PumpkiNibbleType.SUGAR;
								}
								else
								{
									if (player.getItemInHand().getType() == Material.SPECKLED_MELON)
									{
										type = PumpkiNibbleType.GOLD_MELON;
									}
									else
									{

										if (event.getClickedBlock().getType() != Material.DIRT 
											|| event.getClickedBlock().getType() != Material.MYCEL
											|| event.getClickedBlock().getType() != Material.GRASS)
										{

										

										if (player.getItemInHand().getType() == Material.SUGAR_CANE)
										{

											type = PumpkiNibbleType.SUGARCANE;

										}
										else
										{
											if (player.getItemInHand().getType() == Material.BROWN_MUSHROOM)
											{
												type = PumpkiNibbleType.BROWN_MUSHROOM;
											}
											else
											{
												if (player.getItemInHand().getType() == Material.RED_MUSHROOM)
												{
													type = PumpkiNibbleType.RED_MUSHROOM;
												}
											}
										}
										}

									}
								}
							}
						}
					}
				}
			}

		}

		/* Check if the type is a valid item or null */
		/*
		 * if (type == null || !PumpkiNibbleAPI.getValidItems().contains(type))
		 * { System.out.println("[DEBUG] "+type+" was not in database"); return;
		 * }
		 */

		String messageUnable = PumpkiNibbleAPI.getUnableMessage(type);

		String messageOnEat = PumpkiNibbleAPI.getEatMessage(type);

		String messageInsufficient = PumpkiNibbleAPI.getInsufficientMessage(type);
		List<String> potionEffects = PumpkiNibbleAPI.getPotionEffects(type);
		boolean isEnabled = PumpkiNibbleAPI.isEnabled(type);
		int healFoodAmount = PumpkiNibbleAPI.getHealFoodAmount(type);
		int healHealthAmount = PumpkiNibbleAPI.getHealHealthAmount(type);
		int seedAmount = PumpkiNibbleAPI.getItemAmount(type);
		String permission = PumpkiNibbleAPI.getPermission("nibble", type);

		if (!isEnabled)
		{
			return;
		}
		else
		{

			if (player.hasPermission(permission))
			{
				// if
				// (!PumpkiNibbleAPI.getPersonalSettings(event.getPlayer().getName()).contains(type))
				// {
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
					player.sendMessage(ChatColor.GOLD + messageOnEat);

					int FoodLevel = player.getFoodLevel();
					int HealthLevel = player.getHealth();
					int NewFoodLevel = FoodLevel + healFoodAmount;
					int NewHealthLevel = HealthLevel + healHealthAmount;
					player.setFoodLevel(NewFoodLevel);
					player.setHealth(NewHealthLevel);

					if (potionEffects.contains("regen"))
					{

						player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, PumpkiNibbleAPI.getPotionDuration(type, "regen"),
							PumpkiNibbleAPI.getPotionStrength(type, "regen")));
					}
					if (potionEffects.contains("heal"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, PumpkiNibbleAPI.getPotionDuration(type, "heal"),
							PumpkiNibbleAPI.getPotionStrength(type, "heal")));
					}

					if (potionEffects.contains("harm"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, PumpkiNibbleAPI.getPotionDuration(type, "harm"),
							PumpkiNibbleAPI.getPotionStrength(type, "harm")));
					}

					if (potionEffects.contains("poison"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, PumpkiNibbleAPI.getPotionDuration(type, "poison"),
							PumpkiNibbleAPI.getPotionStrength(type, "poison")));
					}
					if (potionEffects.contains("hunger"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, PumpkiNibbleAPI.getPotionDuration(type, "hunger"),
							PumpkiNibbleAPI.getPotionStrength(type, "hunger")));
					}
					if (potionEffects.contains("nightvision"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,
							PumpkiNibbleAPI.getPotionDuration(type, "nightvision"), PumpkiNibbleAPI.getPotionStrength(type, "nightvision")));
					}

					if (potionEffects.contains("weakness"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, PumpkiNibbleAPI.getPotionDuration(type, "weakness"),
							PumpkiNibbleAPI.getPotionStrength(type, "weakness")));
					}
					if (potionEffects.contains("jump"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, PumpkiNibbleAPI.getPotionDuration(type, "jump"),
							PumpkiNibbleAPI.getPotionStrength(type, "jump")));
					}
					if (potionEffects.contains("resistance"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PumpkiNibbleAPI.getPotionDuration(type,
							"resistance"), PumpkiNibbleAPI.getPotionStrength(type, "resistance")));
					}
					if (potionEffects.contains("fireresist"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PumpkiNibbleAPI.getPotionDuration(type,
							"fireresist"), PumpkiNibbleAPI.getPotionStrength(type, "fireresist")));
					}
					if (potionEffects.contains("invisibility"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, PumpkiNibbleAPI
							.getPotionDuration(type, "invisibility"), PumpkiNibbleAPI.getPotionStrength(type, "invisibility")));
					}
					if (potionEffects.contains("slowmine"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, PumpkiNibbleAPI.getPotionDuration(type, "slowmine"),
							PumpkiNibbleAPI.getPotionStrength(type, "slowmine")));
					}
					if (potionEffects.contains("slow"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, PumpkiNibbleAPI.getPotionDuration(type, "slow"),
							PumpkiNibbleAPI.getPotionStrength(type, "slow")));
					}
					if (potionEffects.contains("fastmine"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, PumpkiNibbleAPI.getPotionDuration(type, "fastmine"),
							PumpkiNibbleAPI.getPotionStrength(type, "fastmine")));
					}
					if (potionEffects.contains("waterbreathing"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, PumpkiNibbleAPI.getPotionDuration(type,
							"waterbreathing"), PumpkiNibbleAPI.getPotionStrength(type, "waterbreathing")));
					}
					if (potionEffects.contains("speed"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, PumpkiNibbleAPI.getPotionDuration(type, "speed"),
							PumpkiNibbleAPI.getPotionStrength(type, "speed")));
					}
					if (potionEffects.contains("nausea"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, PumpkiNibbleAPI.getPotionDuration(type, "nausea"),
							PumpkiNibbleAPI.getPotionStrength(type, "nausea")));
					}
					if (potionEffects.contains("blindness"))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PumpkiNibbleAPI.getPotionDuration(type, "blindness"),
							PumpkiNibbleAPI.getPotionStrength(type, "blindness")));
					}
				}
				else
				{

					player.sendMessage(ChatColor.GOLD + messageInsufficient);
					player.getInventory().remove(player.getItemInHand());

				}
			}
			else
			{
				player.sendMessage(ChatColor.GOLD + messageUnable);
			}
		}
	}
}
// }
