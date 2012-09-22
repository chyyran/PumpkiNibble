package net.mystia.PumpkiNibble;

import java.util.HashMap;
import java.util.List;

import net.mystia.PumpkiNibble.PumpkiNibbleMain;

public class PumpkiNibbleConfig {

	public HashMap<String, List<String>> settings;
	private PumpkiNibbleMain plugin;

	public Integer getPotionDuration(String type, String effect) {
		Integer potionDuration = plugin.getConfig().getInt(
				type + ".potionDuration." + effect);
		if (potionDuration == null) {
			potionDuration = 0;
		}

		return potionDuration;
	}

	public Integer getPotionStrength(String type, String effect) {
		Integer potionStrength = plugin.getConfig().getInt(
				type + ".potionStrength." + effect);
		if (potionStrength == null) {
			potionStrength = 0;
		}
		return potionStrength;
	}

	public List<String> getPotionEffects(String type) {
		List<String> potionEffects = plugin.getConfig().getStringList(
				type + ".potionEffects");
		return potionEffects;
	}

	public String getEatMessage(String type) {
		String eatMessage = plugin.getConfig()
				.getString(type + ".messageOnEat");
		return eatMessage;
	}

	public String getInsufficientMessage(String type) {
		String insufficientMessage = plugin.getConfig().getString(
				type + ".messageInsufficient");
		return insufficientMessage;
	}

	public Integer getHealFoodAmount(String type) {
		Integer healFood = plugin.getConfig().getInt(type + ".healFoodAmount");
		if (healFood == null) {
			healFood = 0;
		}
		return healFood;
	}

	public Integer getHealHealthAmount(String type) {
		Integer healHealth = plugin.getConfig().getInt(
				type + ".healHealthAmount");
		if (healHealth == null) {
			healHealth = 0;
		}
		return healHealth;
	}

	public Integer getItemAmount(String type) {
		Integer itemAmount = plugin.getConfig().getInt(type + ".itemAmount");
		if (itemAmount == null) {
			itemAmount = 1;
		}
		return itemAmount;
	}

	public String getPermission(String permissionSection, String type) {
		String permission = "pumpkinibble." + permissionSection + "." + type;
		permission = permission.toLowerCase();
		return permission;
	}

	public boolean isEnabled(String type) {
		boolean ifEnabled = plugin.getConfig().getBoolean(type + ".enabled");
		return ifEnabled;
	}
	public String getUnableMessage(String type){
		String unableMessage = plugin.getConfig().getString(type+".messageUnable");
		return unableMessage;
	}
	public List<String> getPersonalSettings(String playername){
		List<String> personalSettings = settings.get(playername);
		return personalSettings;
		
	}
	
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
}
