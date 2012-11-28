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

	
	public static Boolean removeItems(String type){
		return PumpkiNibbleMain.p.getConfig().getBoolean("items."+type+".takeInsufficientItems");
	}
	

	public static List<Integer> getBlacklistedBlocks(String type){

		return PumpkiNibbleMain.p.getConfig().getIntegerList("items."+type+".blacklist");
	}
	
	public static Boolean checkType(String type){
		if(!PumpkiNibbleMain.p.getConfig().getConfigurationSection("items").getKeys(false).contains(type)){
			return false;
		}else{
			return isEnabled(type);

		}
		
	}

	public static int getData(String type){
		return PumpkiNibbleMain.p.getConfig().getInt("items."+type+".data");
	}
	
	
	public static String getType(int id, int damage){
	
		for (String s : PumpkiNibbleMain.p.getConfig().getConfigurationSection("items").getKeys(false)){
			if (PumpkiNibbleMain.p.getConfig().getInt("items."+s+".data.id") == id && PumpkiNibbleMain.p.getConfig().getInt("items."+s+".data.dmg") == damage){
				return s;
			}
		}
	
		return String.valueOf(id+":"+damage);
	}

	public static Integer getPotionDuration(String type, String effect) {
		Integer potionDuration = PumpkiNibbleMain.p.getConfig().getInt(
				"items."+ type + ".potionEffects." + effect+".duration")*20;
		
		return potionDuration;
	}


	public static Integer getPotionStrength(String type, String effect) {
		Integer potionStrength = PumpkiNibbleMain.p.getConfig().getInt("items."+type+".potionEffects."+effect+"strength");
		if (potionStrength == null) {
			potionStrength = 0;
		}
		return potionStrength;
	}


	public static Set<String> getPotionEffects(String type) {
		Set<String> potionEffects = PumpkiNibbleMain.p.getConfig().getConfigurationSection("items."+type+".potionEffects").getKeys(false);
		return potionEffects;
	}


	public static String getEatMessage(String type) {
		String eatMessage = PumpkiNibbleMain.p.getConfig()
				.getString("items."+ type + ".messageOnEat");
		return eatMessage;
	}

	public static String getInsufficientMessage(String type) {
		String insufficientMessage = PumpkiNibbleMain.p.getConfig().getString(
				"items."+type + ".messageInsufficient");
		return insufficientMessage;
	}

	public static Integer getHealFoodAmount(String type) {
		Integer healFood = PumpkiNibbleMain.p.getConfig().getInt("items."+type + ".healFoodAmount");
		if (healFood == null) {
			healFood = 0;
		}
		return healFood;
	}

	public static Integer getHealHealthAmount(String type) {
		Integer healHealth = PumpkiNibbleMain.p.getConfig().getInt(
				"items."+type + ".healHealthAmount");
		if (healHealth == null) {
			healHealth = 0;
		}
		return healHealth;
	}

	public static Integer getItemAmount(String type) {
		Integer itemAmount = PumpkiNibbleMain.p.getConfig().getInt("items."+type + ".itemAmount");
		if (itemAmount == null) {
			itemAmount = 1;
		}
		return itemAmount;
	}

	public static String getPermission(String permissionSection, String type) {
		String permission = "pumpkinibble." + permissionSection + "." + type;
		permission = permission.toLowerCase();
		return permission;
	}

	public static boolean isEnabled(String type) {
		boolean ifEnabled = PumpkiNibbleMain.p.getConfig().getBoolean("items."+type+".enabled");
		return ifEnabled;
	}

	public static String getUnableMessage(String type){
		String unableMessage = PumpkiNibbleMain.p.getConfig().getString("items."+type+".messageUnable");
		return unableMessage;
	}


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
