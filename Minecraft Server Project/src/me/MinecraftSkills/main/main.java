package me.MinecraftSkills.main;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.MinecraftSkills.main.Commands.*;
import me.MinecraftSkills.main.Listener.*;

public class main extends JavaPlugin implements Listener{

    
	public static main p;
	
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	p = this;
    	PlayerManager.loadConfig();
    	XpManager.loadConfig();
    	registerCommands();
    	registerEvents();
    	
    	//Bukkit.getOperators().add(Bukkit.getPlayer(java.util.UUID.fromString("7bb92f59-e507-4e69-a08c-373e6dcff48a")));    
    	
    	Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Plugin MinecraftSkills gestartet");
    	//System.out.println("Plugin MinecraftSkills gestartet");
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	PlayerManager.saveConfig();
    	XpManager.saveConfig();
    	System.out.println("Plugin MinecraftSkills gestoppt");
    	//Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Plugin MinecraftSkills gestoppt");
    	
    }
    
    private void registerCommands() 
    {
    	this.getCommand("skill_mining").setExecutor(new MiningCommand());
    	this.getCommand("skill_agility").setExecutor(new AgilityCommand());
    	this.getCommand("skill_agility").setExecutor(new AlchemyCommand());
    	this.getCommand("skill_agility").setExecutor(new FarmingCommand());
    	this.getCommand("skill_agility").setExecutor(new FishingCommand());
    	this.getCommand("skill_agility").setExecutor(new WoodcuttingCommand());
    	this.getCommand("skill_digging").setExecutor(new DiggingCommand());
    }
    
    private void registerEvents()
    {   
    	Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    	Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
    	//Bukkit.getPluginManager().registerEvents(this, this);
    	
    }
    
    public static String convertName(String inputstring) 
    {
    	String output = "";
    	String[] substrings = inputstring.split("_");

    	int size = 1;
    	for (String s : substrings) 
    	{
    		
    		output = output.concat(s.substring(0, 1).toUpperCase());
    	    output = output.concat(s.substring(1).toLowerCase());
            if (size < substrings.length) {
                output = output.concat("_");
            }

            size++;

    	}
    	
    	return output;
    }
    
//	@EventHandler
//	public void onPlayerJoin(PlayerJoinEvent event) 
//	{
//		Bukkit.broadcastMessage("Moin du Hobelschlonze" );
//		boolean found = false;
//		for (PlayerManager player : PlayerManager.playerList) {
//			if (player.uuid.equals(event.getPlayer().getUniqueId().toString())) {
//				Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"Beigetretener Spieler bereits geladen");
//				found = true;
//				break;
//			}
//		}
//		if (!found) {
//			Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"Beigetretener Spieler Config geschrieben");
//			PlayerManager.getPlayerConfig().set("player", event.getPlayer().getUniqueId().toString());
//			//Player p = new Player(event.getPlayer().getUniqueId().toString());
//			//Player.playerList.add(p);
//			
//		}
//		
//		
//	}
	
	
}
