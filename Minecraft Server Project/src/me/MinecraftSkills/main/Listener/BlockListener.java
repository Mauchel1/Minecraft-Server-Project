package me.MinecraftSkills.main.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.block.*;

import me.MinecraftSkills.main.PlayerManager;
import me.MinecraftSkills.main.XpManager;

public class BlockListener implements Listener
{

	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) 
	{
		//Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: " + event.getBlock() + " from Player " + event.getPlayer());

		
		//Mining
		
		//Farming
		
		//Digging
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: " + event.getBlock().getType() + " from Player " + event.getPlayer().getUniqueId());
		
		if (XpManager.getSkillXP("Digging", event.getBlock().getType().name()) == 0) 
		{
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: Block not found");
		}
		else
		{
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Digging", XpManager.getSkillXP("Digging", event.getBlock().getType().name()) );
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: Block found, XP: " + XpManager.getSkillXP("Digging", event.getBlock().getType().name()  ));
		}
		
		//Woodcutting
		
		
	}
	
	@EventHandler
    public void onBlockPlace(BlockPlaceEvent event) 
	{
		//Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: " + event.getBlock() + event.getBlockAgainst() + event.getBlockPlaced() + event.getBlockReplacedState() + event.getItemInHand() + event.getPlayer() + event.getHand());

		
		//Farming
	}
	
}