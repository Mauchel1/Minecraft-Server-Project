package me.MinecraftSkills.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerManager {

	protected static final main plugin = main.p;
    private static File playerConfigFile;
    private static FileConfiguration playerConfig;

    

	public PlayerManager() {	}
	
    public static FileConfiguration getPlayerConfig() {
        return playerConfig;
    }
	
    public static void loadConfig() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "loadConfig(playerconfig)");
        playerConfigFile = new File(plugin.getDataFolder(), "playerconfig.yml");
        if (!playerConfigFile.exists()) {
            playerConfigFile.getParentFile().mkdirs();
    		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "SaveConfig(playerconfig)");
            plugin.saveResource("playerconfig.yml", false);
         }

        playerConfig = new YamlConfiguration();
        try {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "try to load playerconfigfile");

            playerConfig.load(playerConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
    	try {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "Try to save playerconfig");
			playerConfig.save(playerConfigFile);
    		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "playerconfig saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	

	public static String getPlayer(String UUID) { return playerConfig.getString("Player." + UUID); }
	
	public static double getSkillXP(String UUID, String Skill) { return playerConfig.getDouble("Player." + UUID + ".XP." + Skill, 1.0); }
	
	public static int getSkillLvl(String UUID, String Skill) { return playerConfig.getInt("Player." + UUID + ".Lvl." + Skill, 1); }
	
	public static void setSkillXP(String UUID, String Skill, double xp) { playerConfig.set("Player." + UUID + ".XP." + Skill, xp); }
	
	public static void setSkillLvl(String UUID, String Skill, int lvl) { playerConfig.set("Player." + UUID + ".Lvl." + Skill, lvl); }

	public static void addSkillXP(String UUID, String Skill, double xp) 
	{
		int lvl = getSkillLvl(UUID, Skill);
		int expNeededLvlUp = 10*(lvl+lvl+1+101);
		//int expNeededTotal = 10*(lvl+1)*(101+(lvl+1));
		
		setSkillXP(UUID, Skill, xp + getSkillXP(UUID, Skill));	
		
		//Level Up!
		if (getSkillXP(UUID, Skill) >= expNeededLvlUp) 
		{
			setSkillXP(UUID, Skill, getSkillXP(UUID, Skill) - expNeededLvlUp);
			setSkillLvl(UUID, Skill, getSkillLvl(UUID, Skill) +1);
			Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+ Bukkit.getPlayer(java.util.UUID.fromString(UUID)).getName() + " Leveled Up Skill " + Skill);
			
    		Bukkit.getPlayer(java.util.UUID.fromString(UUID)).sendMessage("Glückwunsch! " + Skill + " Level Up!");

			//10*L*(101+L);
		}
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + Bukkit.getPlayer(java.util.UUID.fromString(UUID)).getName() + " now have " + getSkillXP(UUID, Skill) +" XP in " + Skill) ;
	}
	
	public static void initNewPlayer(String UUID) 
	{
		
		setSkillXP(UUID, "Agility", 1.0);
		setSkillXP(UUID, "Mining", 1.0);
		setSkillXP(UUID, "Farming", 1.0);
		setSkillXP(UUID, "Woodcutting", 1.0);
		setSkillXP(UUID, "Alchemy", 1.0);
		setSkillXP(UUID, "Fishing", 1.0);
		setSkillXP(UUID, "Digging", 1.0);
		setSkillXP(UUID, "Sword", 1.0);
		setSkillXP(UUID, "Axe", 1.0);
		setSkillXP(UUID, "Fist", 1.0);
		setSkillXP(UUID, "Bow", 1.0);
		setSkillXP(UUID, "Crossbow", 1.0);
		
		setSkillLvl(UUID, "Agility", 1);
		setSkillLvl(UUID, "Mining", 1);
		setSkillLvl(UUID, "Farming", 1);
		setSkillLvl(UUID, "Woodcutting", 1);
		setSkillLvl(UUID, "Alchemy", 1);
		setSkillLvl(UUID, "Fishing", 1);
		setSkillLvl(UUID, "Digging", 1);
		setSkillLvl(UUID, "Sword", 1);
		setSkillLvl(UUID, "Axe", 1);
		setSkillLvl(UUID, "Fist", 1);
		setSkillLvl(UUID, "Bow", 1);
		setSkillLvl(UUID, "Crossbow", 1);

		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"Playerconfig Entitys Created for Player " + UUID);

	}
	
//	XP-Berechnung
//	x = 10(n^2-m^2) + 1010(n-m)
//	or
//	10(n-m)(n+m+101)
//	where n is the target level and m is the current level.
//
//	To find how much exp is needed to get to level "L" from 0 exp, this equation can be used:
//	exp = 1010L + 10L^2
//	or
//	10L(101+L)


}
