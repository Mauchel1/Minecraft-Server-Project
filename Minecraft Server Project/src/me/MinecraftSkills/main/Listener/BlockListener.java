package me.MinecraftSkills.main.Listener;

import java.util.Collection;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.block.*;
import org.bukkit.inventory.ItemStack;

import Skills.WoodcuttingManager;
import me.MinecraftSkills.main.PlayerManager;
import me.MinecraftSkills.main.SkillManager;
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
		
		//TODO Block "natürlich" gebrochen? korrektes tool benutzt? gibt er überhaupt drops? (stein mit hand abbauen...) -event.getBlock().getDrops(ItemStack) should do, but dosnt work correctly ...
		// https://www.spigotmc.org/threads/block-getdrops-itemstack-incorrect-results.239989/
		if (XpManager.getSkillXP("Mining", event.getBlock().getType().name()) != 0) 
		{
			//main.ConsoleMsg(ChatColor.AQUA , "Block Event: Block found, XP: " + XpManager.getSkillXP("Mining", event.getBlock().getType().name()  ));
			//main.ConsoleMsg(ChatColor.AQUA , "Block Event: " + event.getBlock().getType() + " isDropItems: " + event.isDropItems() + " getDropItems: " + event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand()).stream().findFirst() + " MainHand: " + event.getPlayer().getInventory().getItemInMainHand());
			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Mining", XpManager.getSkillXP("Mining", event.getBlock().getType().name()) );
			
			//DoubleDrop
			if (!event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand()).isEmpty())
			{
				if ( PlayerManager.getSkillLvl(event.getPlayer().getUniqueId().toString(), "Mining") * SkillManager.getSkillConfigEntry("Ability", "Mining", "DoubleDrop", "chanceIncreasePerLevelInPercent") * 10 >= new Random().nextInt(1001) )
				{
					event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), (ItemStack) event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand()).stream().findFirst().get());
				}
			}
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
        			
        			//DoubleDrop
        			
        			if (!event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand()).isEmpty())
        			{
        				if ( PlayerManager.getSkillLvl(event.getPlayer().getUniqueId().toString(), "Farming") * SkillManager.getSkillConfigEntry("Ability", "Farming", "DoubleDrop", "chanceIncreasePerLevelInPercent") * 10 >= new Random().nextInt(1001) )
        				{
	        				Collection<ItemStack> dropCollection = event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand());
	        				for (ItemStack item : dropCollection) {
	        					event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);	        					
	        				}
        				}
        			}
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