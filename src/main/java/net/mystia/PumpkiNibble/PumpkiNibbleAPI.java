package net.mystia.PumpkiNibble;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** PumpkiNibble
 * PumpkiNibbleConfig gets and sets the configuration options for the plugin
 * As close of an API as you're ever gonna get.
 * @author ron975
 * 
 */
public class PumpkiNibbleAPI {

	/**
	 * Checks if items are to be removed for that type if insufficient
	 * @param type Type as defined in config
	 * @return Whether to remove items or not if insufficient
	 */
	public static Boolean removeItems(String type){
		return PumpkiNibbleMain.p.getConfig().getBoolean("items."+type+".takeInsufficientItems");
	}
	

	/**
	 * Gets the blocks that will be ignored when clicked for a type
	 * @param type Type as defined in config
	 * @return List of blocks that will not do anything when clicked for a type
	 */
	public static List<Integer> getBlacklistedBlocks(String type){

		return PumpkiNibbleMain.p.getConfig().getIntegerList("items."+type+".blacklist");
	}
	
	/**
	 * Checks if a type exists in the config
	 * @param type Type name
	 * @return Whether a type exists, and if so, if it is enabled.
	 */
	public static Boolean checkType(String type){
		if(!PumpkiNibbleMain.p.getConfig().getConfigurationSection("items").getKeys(false).contains(type)){
			return false;
		}else{
			return isEnabled(type);

		}
		
	}


	/**
	 * Gets the type with a certain item ID and damage value
	 * @param id Item ID to look up
	 * @param damage Damage value to look up
	 * @return Type name if exists in config, id:damage combo if not
	 */
	public static String getType(int id, int damage){
	
		for (String s : PumpkiNibbleMain.p.getConfig().getConfigurationSection("items").getKeys(false)){
			if (PumpkiNibbleMain.p.getConfig().getInt("items."+s+".data.id") == id && PumpkiNibbleMain.p.getConfig().getInt("items."+s+".data.dmg") == damage){
				return s;
			}
		}
	
		return String.valueOf(id+":"+damage);
	}

	/**
	 * Gets the potion duration of an effect as defined in config
	 * @param type Type in which effect is defined
	 * @param effect Effect name
	 * @return Duration of potion effect in ticks
	 */
	public static Integer getPotionDuration(String type, String effect) {
		Integer potionDuration = PumpkiNibbleMain.p.getConfig().getInt(
				"items."+ type + ".potionEffects." + effect+".duration")*20;
		
		return potionDuration;
	}

	/**
	 * Gets the potion strength of an effect as defined in config
	 * @param type Type in which effect is defined
	 * @param effect Effect name
	 * @return Strength of potion effect
	 */
	public static Integer getPotionStrength(String type, String effect) {
		Integer potionStrength = PumpkiNibbleMain.p.getConfig().getInt("items."+type+".potionEffects."+effect+"strength");
		if (potionStrength == null) {
			potionStrength = 0;
		}
		return potionStrength;
	}


	/**
	 * Gets potion effects as defined in config for a certain type
	 * @param type Type name
	 * @return Potion effects
	 */
	public static Set<String> getPotionEffects(String type) {
		Set<String> potionEffects = PumpkiNibbleMain.p.getConfig().getConfigurationSection("items."+type+".potionEffects").getKeys(false);
		return potionEffects;
	}


	/**
	 * Gets the message that will be displayed to the user when an item is eaten
	 * @param type Type name
	 * @return Message that will be displayed when a user eats an item
	 */
	public static String getEatMessage(String type) {
		String eatMessage = PumpkiNibbleMain.p.getConfig()
				.getString("items."+ type + ".messageOnEat");
		return eatMessage;
	}

	/**
	 * Gets the message that will be displayed to the user when there is not enough of an item to be eaten
	 * @param type Type name
	 * @return Message that will be displayed if an user attempts to eat an item, but does not have enough
	 */
	public static String getInsufficientMessage(String type) {
		String insufficientMessage = PumpkiNibbleMain.p.getConfig().getString(
				"items."+type + ".messageInsufficient");
		return insufficientMessage;
	}

	/**
	 * Gets the amount of the food bar to be restored when item is consumed
	 * @param type Type name
	 * @return Amount of the food bar to be restored when item is consumed
	 */
	public static Integer getHealFoodAmount(String type) {
		Integer healFood = PumpkiNibbleMain.p.getConfig().getInt("items."+type + ".healFoodAmount");
		if (healFood == null) {
			healFood = 0;
		}
		return healFood;
	}
	
	/**
	 * Gets the amount of the health bar to be restored when item is consumed
	 * @param type Type name
	 * @return Amount of the health to be restored when item is consumed
	 */
	public static Integer getHealHealthAmount(String type) {
		Integer healHealth = PumpkiNibbleMain.p.getConfig().getInt(
				"items."+type + ".healHealthAmount");
		if (healHealth == null) {
			healHealth = 0;
		}
		return healHealth;
	}

	/**
	 * Gets the amount of items required before an effect is triggered
	 * @param type Type name
	 * @return Amount of items required before an effect is triggered
	 */
	public static Integer getItemAmount(String type) {
		Integer itemAmount = PumpkiNibbleMain.p.getConfig().getInt("items."+type + ".itemAmount");
		if (itemAmount == null) {
			itemAmount = 1;
		}
		return itemAmount;
	}

	/**
	 * Puts toghether a string for use in hasPermission()
	 * @param permissionSection Middle part of permission, eg "nibble"
	 * @param type End part of permission
	 * @return "pumpkinibble.[permissionSection].[type]", subsituting respectively
	 */
	public static String getPermission(String permissionSection, String type) {
		String permission = "pumpkinibble." + permissionSection + "." + type;
		permission = permission.toLowerCase();
		return permission;
	}

	/**
	 * Checks if a type is enabled
	 * @param type Type name
	 * @return Whether a type is enabled or not
	 */
	public static boolean isEnabled(String type) {
		boolean ifEnabled = PumpkiNibbleMain.p.getConfig().getBoolean("items."+type+".enabled");
		return ifEnabled;
	}


	/**
	 * Gets the message that will be displayed to the user when the user lacks the permission to eat a type
	 * @param type Type name
	 * @return Message that will be displayed if an user lacks permission to consume a type
	 */
	public static String getUnableMessage(String type){
		String unableMessage = PumpkiNibbleMain.p.getConfig().getString("items."+type+".messageUnable");
		return unableMessage;
	}

	/**
	 * Gets list of types that are enabled
	 * @return List of types in config
	 * @deprecated Is not required for plugin usage
	 */
	public static Set<String> getValidTypes(){
		
		Set<String> allItems = PumpkiNibbleMain.p.getConfig().getConfigurationSection("items").getKeys(false);
		Set<String> validItems = new HashSet<String>();
		for (String item : allItems){
			if(!PumpkiNibbleMain.p.getConfig().getBoolean("items."+item+".enabled")){
				validItems.add(item);
				}
				
			}
		return validItems;
		}
		
	/**
	 * Puts the list of types into a string, separated with separator
	 * @param separator String that separates each item
	 * @return A String with all valid items
	 * @deprecated Does not use StringBuffer nor is required
	 */
	public static String getValidItemsAsString(String separator){
		String validItems = null;
		for (String item : getValidTypes()){
			if (validItems == null){
				validItems = item;
			}else{
				validItems = validItems + separator + item;
			}
		}
		return validItems;
	}
}
