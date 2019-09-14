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

		//Bukkit.getPlayer(java.util.UUID.fromString(event.getPlayer().getUniqueId().toString())).sendMessage("TESTMESSAGE: Glückwunsch! Level Up!");
		//Mining
		
		if (XpManager.getSkillXP("Mining", event.getBlock().getType().name()) != 0) 
		{
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Mining", XpManager.getSkillXP("Mining", event.getBlock().getType().name()) );
			//Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: Block found, XP: " + XpManager.getSkillXP("Mining", event.getBlock().getType().name()  ));
		}

		//Farming
		
		if (XpManager.getSkillXP("Farming", event.getBlock().getType().name()) != 0) 
		{
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Farming", XpManager.getSkillXP("Farming", event.getBlock().getType().name()) );
			//Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: Block found, XP: " + XpManager.getSkillXP("Mining", event.getBlock().getType().name()  ));
		}

		//Digging
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: " + event.getBlock().getType() + " from Player " + event.getPlayer().getUniqueId());
		
		if (XpManager.getSkillXP("Digging", event.getBlock().getType().name()) != 0) 
		{
			//Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: Block not found");
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Digging", XpManager.getSkillXP("Digging", event.getBlock().getType().name()) );
			//Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: Block found, XP: " + XpManager.getSkillXP("Digging", event.getBlock().getType().name()  ));
		}
		
		//Woodcutting
		
		if (XpManager.getSkillXP("Woodcutting", event.getBlock().getType().name()) != 0) 
		{
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Woodcutting", XpManager.getSkillXP("Woodcutting", event.getBlock().getType().name()) );
			//Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: Block found, XP: " + XpManager.getSkillXP("Woodcutting", event.getBlock().getType().name()  ));
		}

	}
	
	@EventHandler
    public void onBlockPlace(BlockPlaceEvent event) 
	{
		//Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Block Event: " + event.getBlock() + event.getBlockAgainst() + event.getBlockPlaced() + event.getBlockReplacedState() + event.getItemInHand() + event.getPlayer() + event.getHand());

		
		//Farming
	}
	
}