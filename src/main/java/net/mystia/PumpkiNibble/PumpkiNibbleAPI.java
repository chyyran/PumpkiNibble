package net.mystia.PumpkiNibble;

import java.util.HashMap;
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



	public static HashMap<String, List<String>> settings = new HashMap<String, List<String>>();

	/**
	 * Gets potion durations from PumpkiNibbleMain.p.getConfig().yml
	 * @param type String
	 * @param effect String
	 * @return potionDuration As Integer
	 */
	public static Integer getPotionDuration(String type, String effect) {
		Integer potionDuration = PumpkiNibbleMain.p.getConfig().getInt(
				"items."+ type + ".potionDuration." + effect);
		if (potionDuration == null) {
			potionDuration = 0;
		}

		return potionDuration;
	}

	/**
	 * Gets potion strength from PumpkiNibbleMain.p.getConfig().yml
	 * @param type String
	 * @param effect String
	 * @return potionStrength As Integer
	 */
	public static Integer getPotionStrength(String type, String effect) {
		Integer potionStrength = PumpkiNibbleMain.p.getConfig().getInt(
				"items."+type + ".potionStrength." + effect);
		if (potionStrength == null) {
			potionStrength = 0;
		}
		return potionStrength;
	}

	/**
	 * Gets potion effects as List from PumpkiNibbleMain.p.getConfig().yml
	 * @param type String
	 * @return potionEffects As List<String>
	 */
	public static List<String> getPotionEffects(String type) {
		List<String> potionEffects = PumpkiNibbleMain.p.getConfig().getStringList(
				"items."+type + ".potionEffects");
		return potionEffects;
	}

	/**
	 * Gets message shown when an item is eaten from PumpkiNibbleMain.p.getConfig().yml
	 * @param type String
	 * @return eatMessage As String
	 */
	public static String getEatMessage(String type) {
		String eatMessage = PumpkiNibbleMain.p.getConfig()
				.getString("items."+ type + ".messageOnEat");
		return eatMessage;
	}

	/**
	 * Gets message shown when there is not enough items to be eaten
	 * @param type String
	 * @return insufficientMessage As String
	 */
	public static String getInsufficientMessage(String type) {
		String insufficientMessage = PumpkiNibbleMain.p.getConfig().getString(
				"items."+type + ".messageInsufficient");
		return insufficientMessage;
	}

	/**
	 * Gets amount of food to be healed
	 * @param type String
	 * @return healFood As Integer
	 */
	public static Integer getHealFoodAmount(String type) {
		Integer healFood = PumpkiNibbleMain.p.getConfig().getInt("items."+type + ".healFoodAmount");
		if (healFood == null) {
			healFood = 0;
		}
		return healFood;
	}

	/**
	 * Gets amount of health to be healed
	 * @param type String
	 * @return healHeath As Integer
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
	 * Gets amount of item to be eaten
	 * @param type String
	 * @return itemAmount AS Integer
	 */
	public static Integer getItemAmount(String type) {
		Integer itemAmount = PumpkiNibbleMain.p.getConfig().getInt("items."+type + ".itemAmount");
		if (itemAmount == null) {
			itemAmount = 1;
		}
		return itemAmount;
	}
	/**
	 * Builds permission string cleanly for use
	 * @param permissionSection
	 * @param type String
	 * @return permission As String
	 */
	public static String getPermission(String permissionSection, String type) {
		String permission = "pumpkinibble." + permissionSection + "." + type;
		permission = permission.toLowerCase();
		return permission;
	}

	/**
	 * Checks if a certain item is enabled in config
	 * @param type String
	 * @return ifEnabled As boolean
	 */
	public static boolean isEnabled(String type) {
		boolean ifEnabled = PumpkiNibbleMain.p.getConfig().getBoolean("items."+type + ".enabled");
		return ifEnabled;
	}
	/**
	 * Gets message shown when someone is unable to eat something
	 * @param type String
	 * @return unableMessage As String
	 */
	public static String getUnableMessage(String type){
		String unableMessage = PumpkiNibbleMain.p.getConfig().getString("items."+type+".messageUnable");
		return unableMessage;
	}
	/**
	 * Gets personal toggle settings from HashMap
	 * @param playername
	 * @return personalSettings As List<String>
	 */
	public static List<String> getPersonalSettings(String playername){
		List<String> personalSettings = PumpkiNibbleMain.personalSettings.getConfig().getStringList("personalSettings."+playername);
		return personalSettings;
		
	}
	
	/**
	 * Sets personal settings to HashMap and returns whether item is enabled or disabled
	 * @param playername
	 * @param setting
	 * @return boolean
	 */
	public static boolean setPersonalSettings(String playername, String setting){
		List<String> personalSettings = PumpkiNibbleMain.personalSettings.getConfig().getStringList("personalSettings."+playername);
		if (!personalSettings.contains(setting)){
		personalSettings.add(setting);
		PumpkiNibbleMain.personalSettings.getConfig().set("personalSettings."+playername, personalSettings);
		return true;
		}else{
		personalSettings.remove(setting);
		PumpkiNibbleMain.personalSettings.getConfig().set("personalSettings."+playername, personalSettings);
		return false;
		}
		
	}
	/**
	 * Gets valid items from config as List<String>
	 * @return validItems As List<String>
	 */
	public static Set<String> getValidItems(){
		
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
	 * Parses List of valid items and returns string of items separated by separator parameter
	 * @param separator String
	 * @return validItems As String
	 */
	public static String getValidItemsAsString(String separator){
		String validItems = null;
		for (String item : getValidItems()){
			if (validItems == null){
				validItems = item;
			}else{
				validItems = validItems + separator + item;
			}
		}
		return validItems;
	}
}
