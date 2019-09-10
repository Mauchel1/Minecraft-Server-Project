package me.MinecraftSkills.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Player {

	protected static final main plugin = main.p;
    private static File playerConfigFile;
    private static FileConfiguration playerConfig;

    
    public static List<Player> playerList = new ArrayList<Player>();

	public String uuid;
	
	public int lvlWoodcutting;
	int lvlAgility;
	int lvlMining;
	int lvlFarming;
	int lvlAlchemy;
	int lvlFishing;
	
	int xpWoodcutting;
	int xpAgility;
	int xplMining;
	int xpFarming;
	int xpAlchemy;
	int xpFishing;
	
	
	public Player(String UUID) {
		uuid = UUID;
	}
	
    public static FileConfiguration getPlayerConfig() {
        return playerConfig;
    }
	
    public static void loadConfig() {
        playerConfigFile = new File(plugin.getDataFolder(), "playerconfig.yml");
        if (!playerConfigFile.exists()) {
            playerConfigFile.getParentFile().mkdirs();
            plugin.saveResource("playerconfig.yml", false);
         }

        playerConfig = new YamlConfiguration();
        try {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"try to load configfile");

            playerConfig.load(playerConfigFile);
            FillPlayerListFromConfig();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
    	try {
    		playerConfig.set("playerlist", playerList);
			playerConfig.save(playerConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
    
	@SuppressWarnings("unchecked")
	static void FillPlayerListFromConfig() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"try to parse playerlist in List");

			playerList = (List<Player>) Player.getPlayerConfig().getList("playerlist");
		}


	public double getAgilityXP(String UUID) { return playerConfig.getDouble("Player." + UUID + "XP.Agility", 1.0); }
	public double getMiningXP(String UUID) { return playerConfig.getDouble("Player." + UUID + "XP.Mining", 1.0); }
	public double getFarmingXP(String UUID) { return playerConfig.getDouble("Player." + UUID + "XP.Farming", 1.0); }
	public double getWoodcuttingXP(String UUID) { return playerConfig.getDouble("Player." + UUID + "XP.Woodcutting", 1.0); }
	public double getAlchemyXP(String UUID) { return playerConfig.getDouble("Player." + UUID + "XP.Alchemy", 1.0); }
	public double getFishingXP(String UUID) { return playerConfig.getDouble("Player." + UUID + "XP.Fishing", 1.0); }
	
	public int getAgilityLvl(String UUID) { return playerConfig.getInt("Player." + UUID + "Lvl.Agility", 1); }
	public int getMiningLvl(String UUID) { return playerConfig.getInt("Player." + UUID + "Lvl.Mining", 1); }
	public int getFarmingLvl(String UUID) { return playerConfig.getInt("Player." + UUID + "Lvl.Farming", 1); }
	public int getWoodcuttingLvl(String UUID) { return playerConfig.getInt("Player." + UUID + "Lvl.Woodcutting", 1); }
	public int getAlchemyLvl(String UUID) { return playerConfig.getInt("Player." + UUID + "Lvl.Alchemy", 1); }
	public int getFishingLvl(String UUID) { return playerConfig.getInt("Player." + UUID + "Lvl.Fishing", 1); }

	public static void setAgilityXP(String UUID ) {
		playerConfig.set("Player." + UUID + "XP.Agility", 1.0);
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
