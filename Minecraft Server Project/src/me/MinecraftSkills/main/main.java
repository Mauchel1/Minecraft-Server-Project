package me.MinecraftSkills.main;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.MinecraftSkills.main.Commands.MiningCommands;
//import me.MinecraftSkills.main.Listener.*;

public class main extends JavaPlugin implements Listener{

    
	public static main p;
	
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	Player.loadConfig();
    	registerCommands();
    	registerEvents();
    	
    	Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Plugin MinecraftSkills gestartet");
    	System.out.println("Plugin MinecraftSkills gestartet");
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	Player.saveConfig();
    	System.out.println("Plugin MinecraftSkills gestoppt");
    	Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Plugin MinecraftSkills gestoppt");
    	
    }
    
    private void registerCommands() 
    {
    	this.getCommand("skill_mining").setExecutor(new MiningCommands());
    	//this.getCommand("skill_agility").setExecutor(new MiningCommands());
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
		for (Player player : Player.playerList) {
			if (player.uuid.equals(event.getPlayer().getUniqueId().toString())) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"Beigetretener Spieler bereits geladen");
				found = true;
				break;
			}
		}
		if (!found) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"Beigetretener Spieler Config geschrieben");
			Player.getPlayerConfig().set("player", event.getPlayer().getUniqueId().toString());
			Player p = new Player(event.getPlayer().getUniqueId().toString());
			Player.playerList.add(p);
			
		}
		
		
	}
	
	
}
