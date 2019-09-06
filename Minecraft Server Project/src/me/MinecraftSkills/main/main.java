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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.MinecraftSkills.main.Commands.MiningCommands;
//import me.MinecraftSkills.main.Listener.*;

public class main extends JavaPlugin implements Listener{

    private File playerConfigFile;
    private FileConfiguration playerConfig;
    
    public List<Player> playerList = new ArrayList<Player>();

	
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	loadConfig();
    	registerCommands();
    	registerEvents();
    	
    	Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Plugin MinecraftSkills gestartet");
    	System.out.println("Plugin MinecraftSkills gestartet");
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	try {
    		playerConfig.set("playerlist", playerList);
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

        playerConfig = new YamlConfiguration();
        try {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"try to load configfile");

            playerConfig.load(playerConfigFile);
            FillPlayerListFromConfig();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    public FileConfiguration getPlayerConfig() {
        return playerConfig;
    }
    
    private void registerCommands() 
    {
    	this.getCommand("skill_mining").setExecutor(new MiningCommands());
    }
    
    private void registerEvents()
    {   
//    	Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    	Bukkit.getPluginManager().registerEvents(this, this);
    	
    }
    
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) 
	{
		Bukkit.broadcastMessage("Moin du Hobelschlonze" );
		boolean found = false;
		for (Player player : playerList) {
			if (player.uuid.equals(event.getPlayer().getUniqueId().toString())) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"Beigetretener Spieler bereits geladen");
				found = true;
				break;
			}
		}
		if (!found) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"Beigetretener Spieler Config geschrieben");
			getPlayerConfig().set("player", event.getPlayer().getUniqueId().toString());
			Player p = new Player(event.getPlayer().getUniqueId().toString());
			playerList.add(p);
			
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	void FillPlayerListFromConfig() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"try to parse playerlist in List");

			playerList = (List<Player>) playerConfig.getList("playerlist");
		}
	
	
}
