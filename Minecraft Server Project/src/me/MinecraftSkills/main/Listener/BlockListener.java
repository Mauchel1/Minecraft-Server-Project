package me.MinecraftSkills.main.Listener;

import org.bukkit.ChatColor;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.block.*;

import Skills.WoodcuttingManager;
import me.MinecraftSkills.main.PlayerManager;
import me.MinecraftSkills.main.XpManager;
import me.MinecraftSkills.main.main;

public class BlockListener implements Listener
{

	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) 
	{
		//main.ConsoleMsg(ChatColor.AQUA , "Block Event: " + event.getBlock() + " from Player " + event.getPlayer());

		//Bukkit.getPlayer(java.util.UUID.fromString(event.getPlayer().getUniqueId().toString())).sendMessage("TESTMESSAGE: Glückwunsch! Level Up!");
		
		//Mining
		
		if (XpManager.getSkillXP("Mining", event.getBlock().getType().name()) != 0) 
		{
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Mining", XpManager.getSkillXP("Mining", event.getBlock().getType().name()) );
			//main.ConsoleMsg(ChatColor.AQUA , "Block Event: Block found, XP: " + XpManager.getSkillXP("Mining", event.getBlock().getType().name()  ));
		}

		//Farming
		
		if (XpManager.getSkillXP("Farming", event.getBlock().getType().name()) != 0) 
		{
			//main.ConsoleMsg(ChatColor.BLUE , event.getBlock().getType().name());
            if (event.getBlock().getBlockData() instanceof Ageable) 
            {
    			//main.ConsoleMsg(ChatColor.BLUE , "IsAgeable");

                Ageable ageData = (Ageable) event.getBlock().getBlockData();
                
                //Cactus + SugarCane dont work with "Age", you may have multiple drops
                if (ageData.getMaterial().name() == "CACTUS" || ageData.getMaterial().name() == "SUGAR_CANE") 
                {
                	//TODO doppelte drops abfangen
        			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Farming", XpManager.getSkillXP("Farming", event.getBlock().getType().name()) );
        			main.ConsoleMsg(ChatColor.AQUA , " Drecks SugarCane oder Caktus -.- ");
                }
                //Only full grown plants give XP
                else if (ageData.getAge() == ageData.getMaximumAge()) 
                {
        			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Farming", XpManager.getSkillXP("Farming", event.getBlock().getType().name()) );
                }
            }
            else
            {
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Farming", XpManager.getSkillXP("Farming", event.getBlock().getType().name()) );
			//main.ConsoleMsg(ChatColor.AQUA , "Block Event: Block found, XP: " + XpManager.getSkillXP("Mining", event.getBlock().getType().name()  ));
            }
		}

		//Digging
		
		//main.ConsoleMsg(ChatColor.AQUA , "Block Event: " + event.getBlock().getType() + " from Player " + event.getPlayer().getUniqueId());
		
		if (XpManager.getSkillXP("Digging", event.getBlock().getType().name()) != 0) 
		{
			//main.ConsoleMsg(ChatColor.AQUA , "Block Event: Block not found");
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Digging", XpManager.getSkillXP("Digging", event.getBlock().getType().name()) );
			//main.ConsoleMsg(ChatColor.AQUA , "Block Event: Block found, XP: " + XpManager.getSkillXP("Digging", event.getBlock().getType().name()  ));
		}
		
		//Woodcutting
		
		if (XpManager.getSkillXP("Woodcutting", event.getBlock().getType().name()) != 0) 
		{
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Woodcutting", XpManager.getSkillXP("Woodcutting", event.getBlock().getType().name()) );
			//main.ConsoleMsg(ChatColor.AQUA , "Block Event: Block found, XP: " + XpManager.getSkillXP("Woodcutting", event.getBlock().getType().name()  ));
			if (WoodcuttingManager.isSuperAxeInUse(event.getPlayer().getUniqueId().toString())) 
			{
				WoodcuttingManager.cutDownTree(event.getPlayer().getUniqueId().toString(), event.getBlock());
			}
		}

	}
	
	@EventHandler
    public void onBlockPlace(BlockPlaceEvent event) 
	{
		//main.ConsoleMsg(ChatColor.AQUA , "Block Event: " + event.getBlock() + event.getBlockAgainst() + event.getBlockPlaced() + event.getBlockReplacedState() + event.getItemInHand() + event.getPlayer() + event.getHand());

		
		//Farming
	}
	
}