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
import net.mystia.PumpkiNibble.PumpkiNibbleConfig;

public class PumpkiNibbleListener implements Listener {
	private PumpkiNibbleConfig config;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerClickPumpkin(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Type type = null;
		if (event.getAction() == Action.RIGHT_CLICK_AIR
				|| (event.getAction() == Action.RIGHT_CLICK_BLOCK
						&& event.getClickedBlock().getType() != Material.SOIL
						&& event.getClickedBlock().getType() != Material.CHEST
						&& event.getClickedBlock().getType() != Material.WOODEN_DOOR
						&& event.getClickedBlock().getType() != Material.STONE_BUTTON
						&& event.getClickedBlock().getType() != Material.LEVER && event
						.getClickedBlock().getType() != Material.TRAP_DOOR)) {
			/* Check for items */
			if (player.getItemInHand() != null) {
				if (player.getItemInHand().getType() == Material.PUMPKIN_SEEDS) {
					type = Type.PUMPKIN;
				} else {
					if (player.getItemInHand().getType() == Material.INK_SACK
							&& player.getItemInHand().getDurability() == 3) {
						type = Type.COCOA;
					} else {
						if (player.getItemInHand().getType() == Material.NETHER_STALK) {
							type = Type.NETHERWART;
						} else {
							if (player.getItemInHand().getType() == Material.MILK_BUCKET) {
								type = Type.MILK;
							} else {
								if (player.getItemInHand().getType() == Material.WHEAT) {
									type = Type.WHEAT;
								} else {
										if (player.getItemInHand().getType() == Material.FERMENTED_SPIDER_EYE) {
											type = Type.FERMENTED_EYE;
										} else {
											if (player.getItemInHand()
													.getType() == Material.SLIME_BALL) {
												type = Type.SLIMEBALL;
											} else {
												if (player.getItemInHand()
														.getType() == Material.SUGAR) {
													type = Type.SUGAR;
												} else {
													if (player.getItemInHand()
															.getType() == Material.SPECKLED_MELON) {
														type = Type.GOLD_MELON;
													} else {
														/*
														 * Sugarcane and
														 * Mushrooms can be
														 * planted on Grass and
														 * Dirt, and Mycelium,
														 * so we check for those
														 */
														if (event
																.getClickedBlock()
																.getType() != Material.DIRT
																&& event.getClickedBlock()
																		.getType() != Material.GRASS
																&& event.getClickedBlock()
																		.getType() == Material.MYCEL) {
															if (event
																	.getClickedBlock()
																	.getType() == Material.SUGAR_CANE) {
																type = Type.SUGARCANE;

															} else {
																if (event
																		.getClickedBlock()
																		.getType() == Material.BROWN_MUSHROOM) {
																	type = Type.BROWN_MUSHROOM;
																} else {
																	if (event
																			.getClickedBlock()
																			.getType() == Material.RED_MUSHROOM) {
																		type = Type.RED_MUSHROOM;
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
						if (type == null
								|| !config.getValidItems().contains(type)) {
							return;
						}
					}
					String messageUnable = config.getUnableMessage(type);
					String messageOnEat = config.getEatMessage(type);
					String messageInsufficient = config
							.getInsufficientMessage(type);
					List<String> potionEffects = config.getPotionEffects(type);
					boolean isEnabled = config.isEnabled(type);
					int healFoodAmount = config.getHealFoodAmount(type);
					int healHealthAmount = config.getHealHealthAmount(type);
					int seedAmount = config.getItemAmount(type);
					String permission = config.getPermission("nibble", type.toString());

					if (!isEnabled) {
						return;
					} else {

						if (player.hasPermission(permission)) {
							if (!config.getPersonalSettings(
									event.getPlayer().getName()).contains(type)) {
								if (type.equals("milk")){
									
								}
								if (player.getItemInHand().getAmount() >= seedAmount) {
									int ItemAmount = player.getItemInHand()
											.getAmount();
									if (ItemAmount == seedAmount) {
										player.getInventory().remove(
												player.getItemInHand());
									} else {
										player.getItemInHand().setAmount(
												ItemAmount - seedAmount);
									}
									player.sendMessage(ChatColor.GOLD
											+ messageOnEat);

									int FoodLevel = player.getFoodLevel();
									int HealthLevel = player.getHealth();
									int NewFoodLevel = FoodLevel
											+ healFoodAmount;
									int NewHealthLevel = HealthLevel
											+ healHealthAmount;
									player.setFoodLevel(NewFoodLevel);
									player.setHealth(NewHealthLevel);

									if (potionEffects.contains("regen")) {

										player.addPotionEffect(new PotionEffect(
												PotionEffectType.REGENERATION,
												config.getPotionDuration(type,
														"regen"), config
														.getPotionStrength(
																type, "regen")));
									}
									if (potionEffects.contains("heal")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.HEAL, config
														.getPotionDuration(
																type, "heal"),
												config.getPotionStrength(type,
														"heal")));
									}

									if (potionEffects.contains("harm")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.HARM, config
														.getPotionDuration(
																type, "harm"),
												config.getPotionStrength(type,
														"harm")));
									}

									if (potionEffects.contains("poison")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.POISON,
												config.getPotionDuration(type,
														"poison"), config
														.getPotionStrength(
																type, "poison")));
									}
									if (potionEffects.contains("hunger")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.HUNGER,
												config.getPotionDuration(type,
														"hunger"), config
														.getPotionStrength(
																type, "hunger")));
									}
									if (potionEffects.contains("nightvision")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.NIGHT_VISION,
												config.getPotionDuration(type,
														"nightvision"), config
														.getPotionStrength(
																type,
																"nightvision")));
									}

									if (potionEffects.contains("weakness")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.WEAKNESS,
												config.getPotionDuration(type,
														"weakness"), config
														.getPotionStrength(
																type,
																"weakness")));
									}
									if (potionEffects.contains("jump")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.JUMP, config
														.getPotionDuration(
																type, "jump"),
												config.getPotionStrength(type,
														"jump")));
									}
									if (potionEffects.contains("resistance")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.DAMAGE_RESISTANCE,
												config.getPotionDuration(type,
														"resistance"), config
														.getPotionStrength(
																type,
																"resistance")));
									}
									if (potionEffects.contains("fireresist")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.FIRE_RESISTANCE,
												config.getPotionDuration(type,
														"fireresist"), config
														.getPotionStrength(
																type,
																"fireresist")));
									}
									if (potionEffects.contains("invisibility")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.INVISIBILITY,
												config.getPotionDuration(type,
														"invisibility"), config
														.getPotionStrength(
																type,
																"invisibility")));
									}
									if (potionEffects.contains("slowmine")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.SLOW_DIGGING,
												config.getPotionDuration(type,
														"slowmine"), config
														.getPotionStrength(
																type,
																"slowmine")));
									}
									if (potionEffects.contains("slow")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.SLOW, config
														.getPotionDuration(
																type, "slow"),
												config.getPotionStrength(type,
														"slow")));
									}
									if (potionEffects.contains("fastmine")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.FAST_DIGGING,
												config.getPotionDuration(type,
														"fastmine"), config
														.getPotionStrength(
																type,
																"fastmine")));
									}
									if (potionEffects
											.contains("waterbreathing")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.WATER_BREATHING,
												config.getPotionDuration(type,
														"waterbreathing"),
												config.getPotionStrength(type,
														"waterbreathing")));
									}
									if (potionEffects.contains("speed")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.SPEED, config
														.getPotionDuration(
																type, "speed"),
												config.getPotionStrength(type,
														"speed")));
									}
									if (potionEffects.contains("nausea")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.CONFUSION,
												config.getPotionDuration(type,
														"nausea"), config
														.getPotionStrength(
																type, "nausea")));
									}
									if (potionEffects.contains("blindness")) {
										player.addPotionEffect(new PotionEffect(
												PotionEffectType.BLINDNESS,
												config.getPotionDuration(type,
														"blindness"), config
														.getPotionStrength(
																type,
																"blindness")));
									}
								} else {

									player.sendMessage(ChatColor.GOLD
											+ messageInsufficient);
									player.getInventory().remove(
											player.getItemInHand());

								}
							} else {
								player.sendMessage(ChatColor.GOLD
										+ messageUnable);
							}
						}
					}
				}
			}
		}
	}

