package me.MinecraftSkills.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SkillManager {

	protected static final main plugin = main.p;
    private static File skillConfigFile;
    private static FileConfiguration skillConfig;

    public static FileConfiguration getXpConfig() {
        return skillConfig;
    }

    
    public static void loadConfig() {
		main.ConsoleMsg(ChatColor.BLUE , "loadConfig(skillConfig)");
		skillConfigFile = new File(plugin.getDataFolder(), "skillconfig.yml");
        if (!skillConfigFile.exists()) {
        	skillConfigFile.getParentFile().mkdirs();
    		main.ConsoleMsg(ChatColor.BLUE , "SaveConfig(skillConfig)");
            plugin.saveResource("skillconfig.yml", false);
         }

        skillConfig = new YamlConfiguration();
        try {
    		main.ConsoleMsg(ChatColor.BLUE , "try to load configfile");

    		skillConfig.load(skillConfigFile);
            //FillPlayerListFromConfig();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
    	try {
    		main.ConsoleMsg(ChatColor.BLUE , "Try to save skillconfig");
    		skillConfig.save(skillConfigFile);
    		main.ConsoleMsg(ChatColor.BLUE , "skillconfig saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public static double getSkillConfigEntry(String ConfigClass, String SkillClass, String Skill, String configEntry) { 
		return skillConfig.getDouble(ConfigClass + "." + SkillClass + "." + Skill + "." + configEntry); 
	}
}