package net.mystia.PumpkiNibble;

import java.util.HashMap;
import java.util.List;

import net.mystia.PumpkiNibble.PumpkiNibbleMain;
/** PumpkiNibble
 * PumpkiNibbleConfig gets and sets the configuration options for the plugin
 * As close of an API as you're ever gonna get.
 * @author ron975
 * 
 */
public class PumpkiNibbleConfig {

	public HashMap<String, List<String>> settings;
	private PumpkiNibbleMain plugin;

	/**
	 * Gets potion durations from config.yml
	 * @param type String
	 * @param effect String
	 * @return potionDuration As Integer
	 */
	public Integer getPotionDuration(String type, String effect) {
		Integer potionDuration = plugin.getConfig().getInt(
				"items."+ type + ".potionDuration." + effect);
		if (potionDuration == null) {
			potionDuration = 0;
		}

		return potionDuration;
	}

	/**
	 * Gets potion strength from config.yml
	 * @param type String
	 * @param effect String
	 * @return potionStrength As Integer
	 */
	public Integer getPotionStrength(String type, String effect) {
		Integer potionStrength = plugin.getConfig().getInt(
				"items."+type + ".potionStrength." + effect);
		if (potionStrength == null) {
			potionStrength = 0;
		}
		return potionStrength;
	}

	/**
	 * Gets potion effects as List from config.yml
	 * @param type String
	 * @return potionEffects As List<String>
	 */
	public List<String> getPotionEffects(String type) {
		List<String> potionEffects = plugin.getConfig().getStringList(
				"items."+type + ".potionEffects");
		return potionEffects;
	}

	/**
	 * Gets message shown when an item is eaten from config.yml
	 * @param type String
	 * @return eatMessage As String
	 */
	public String getEatMessage(String type) {
		String eatMessage = plugin.getConfig()
				.getString("items."+ type + ".messageOnEat");
		return eatMessage;
	}

	/**
	 * Gets message shown when there is not enough items to be eaten
	 * @param type String
	 * @return insufficientMessage As String
	 */
	public String getInsufficientMessage(String type) {
		String insufficientMessage = plugin.getConfig().getString(
				"items."+type + ".messageInsufficient");
		return insufficientMessage;
	}

	/**
	 * Gets amount of food to be healed
	 * @param type String
	 * @return healFood As Integer
	 */
	public Integer getHealFoodAmount(String type) {
		Integer healFood = plugin.getConfig().getInt("items."+type + ".healFoodAmount");
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
	public Integer getHealHealthAmount(String type) {
		Integer healHealth = plugin.getConfig().getInt(
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
	public Integer getItemAmount(String type) {
		Integer itemAmount = plugin.getConfig().getInt("items."+type + ".itemAmount");
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
	public String getPermission(String permissionSection, String type) {
		String permission = "pumpkinibble." + permissionSection + "." + type;
		permission = permission.toLowerCase();
		return permission;
	}

	/**
	 * Checks if a certain item is enabled in config
	 * @param type String
	 * @return ifEnabled As boolean
	 */
	public boolean isEnabled(String type) {
		boolean ifEnabled = plugin.getConfig().getBoolean("items."+type + ".enabled");
		return ifEnabled;
	}
	/**
	 * Gets message shown when someone is unable to eat something
	 * @param type String
	 * @return unableMessage As String
	 */
	public String getUnableMessage(String type){
		String unableMessage = plugin.getConfig().getString("items."+type+".messageUnable");
		return unableMessage;
	}
	/**
	 * Gets personal toggle settings from HashMap
	 * @param playername
	 * @return personalSettings As List<String>
	 */
	public List<String> getPersonalSettings(String playername){
		List<String> personalSettings = settings.get(playername);
		return personalSettings;
		
	}
	
	/**
	 * Sets personal settings to HashMap and returns whether item is enabled or disabled
	 * @param playername
	 * @param setting
	 * @return boolean
	 */
	public boolean setPersonalSettings(String playername, String setting){
		List<String> personalSettings = getPersonalSettings(playername);
		if (!personalSettings.contains(setting)){
		personalSettings.add(setting);
		settings.put(playername, personalSettings);
		return true;
		}else{
		personalSettings.remove(setting);
		settings.put(playername, personalSettings);
		return false;
		}
		
	}
	/**
	 * Gets valid items from config as List<String>
	 * @return validItems As List<String>
	 */
	public List<String> getValidItems(){
		List<String> validItems = plugin.getConfig().getStringList("items");
		return validItems;
	}

	/**
	 * Parses List of valid items and returns string of items separated by separator parameter
	 * @param separator String
	 * @return validItems As String
	 */
	public String getValidItemsAsString(String separator){
		String validItems = null;
		for (String item : this.getValidItems()){
			if (validItems == null){
				validItems = item;
			}else{
				validItems = validItems + separator + item;
			}
		}
		return validItems;
	}
}
