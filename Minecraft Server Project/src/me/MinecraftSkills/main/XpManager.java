package me.MinecraftSkills.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class XpManager {

	protected static final main plugin = main.p;
    private static File xpConfigFile;
    private static FileConfiguration xpConfig;

    public static FileConfiguration getXpConfig() {
        return xpConfig;
    }

    
    public static void loadConfig() {
		main.ConsoleMsg(ChatColor.BLUE , "loadConfig(xpConfig)");
		xpConfigFile = new File(plugin.getDataFolder(), "xpconfig.yml");
        if (!xpConfigFile.exists()) {
        	xpConfigFile.getParentFile().mkdirs();
    		main.ConsoleMsg(ChatColor.BLUE , "SaveConfig(xpConfig)");
            plugin.saveResource("xpconfig.yml", false);
         }

        xpConfig = new YamlConfiguration();
        try {
    		main.ConsoleMsg(ChatColor.BLUE , "try to load configfile");

    		xpConfig.load(xpConfigFile);
            //FillPlayerListFromConfig();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
    	try {
    		main.ConsoleMsg(ChatColor.BLUE , "Try to save xpconfig");
    		xpConfig.save(xpConfigFile);
    		main.ConsoleMsg(ChatColor.BLUE , "xpconfig saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public static int getSkillXP(String Skill, String Block) { 
		//main.ConsoleMsg(ChatColor.BLUE , "Try to get Xp for: " + Skill + "." + main.convertName(Block));
		
		return xpConfig.getInt(Skill + "." + main.convertName(Block)); 
	}

    
}
