package me.MinecraftSkills.main.Listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import Skills.WoodcuttingManager;
import me.MinecraftSkills.main.main;


public class PlayerInteractListener implements Listener

{

	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) 
	{
		// Woodcutting Superaxe Ability
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			if (event.getPlayer().getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("axe")) 
			{
				WoodcuttingManager.useSuperAxe(event.getPlayer().getUniqueId().toString());
			}
			else 
			{
				main.ConsoleMsg(ChatColor.DARK_GREEN , "Player interact: " + "SuperAxe could not be activated" );
			}
		}
	}
}