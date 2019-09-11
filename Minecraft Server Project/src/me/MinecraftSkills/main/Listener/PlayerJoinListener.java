package me.MinecraftSkills.main.Listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoinListener implements Listener
{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) 
	{
		Bukkit.broadcastMessage("Moin du Hobelschlonze" + event.getPlayer().getUniqueId().toString() );
		event.getPlayer().getUniqueId().toString();
		event.setJoinMessage("Willkommen du Schwuler Hengst!");
		
		
	}
}
