package me.MinecraftSkills.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{

    private File playerConfigFile;
    private FileConfiguration playerConfig;
    
    private List<Player> playerList;

	
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Plugin MinecraftSkills gestartet");
    	System.out.println("Plugin MinecraftSkills gestartet");
    	loadConfig();
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	try {
			playerConfig.save(playerConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.println("Plugin MinecraftSkills gestoppt");
    	Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Plugin MinecraftSkills gestoppt");
    }
    
    private void loadConfig() {
        playerConfigFile = new File(getDataFolder(), "playerconfig.yml");
        if (!playerConfigFile.exists()) {
            playerConfigFile.getParentFile().mkdirs();
            saveResource("playerconfig.yml", false);
         }

        playerConfig= new YamlConfiguration();
        try {
            playerConfig.load(playerConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    public FileConfiguration getPlayerConfig() {
        return this.playerConfig;
    }
//    @Override
//    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
//    {
//    	return false;
//    }
}
