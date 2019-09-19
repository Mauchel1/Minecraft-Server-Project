package me.MinecraftSkills.main.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.MinecraftSkills.main.PlayerManager;
import me.MinecraftSkills.main.XpManager;

public class DamageListener implements Listener

{
	
	@EventHandler
    public void onEntityDamageEntityEvent(EntityDamageByEntityEvent event) 
	{
		//main.ConsoleMsg(ChatColor.AQUA , "Damage Event damager: " + event.getDamager().getName() + " " + event.getDamager().toString() );

		// Fernkampf
		
		if (event.getDamager() instanceof Projectile && event.getCause() == DamageCause.PROJECTILE) 
		{
			Projectile pj = (Projectile) event.getDamager();
			if (pj.getShooter() instanceof Player) 
			{
				Player player = (Player) pj.getShooter();
				//main.ConsoleMsg(ChatColor.AQUA , "Damage Event item: " + player.getInventory().getItemInMainHand().getType()  + " from " + player.getName() + " Damage: " + event.getFinalDamage());
				
				//Bow
				//main.ConsoleMsg(ChatColor.AQUA , "DistanceProjectile: " + player.getLocation().distance(event.getEntity().getLocation()));
				
				if(player.getInventory().getItemInMainHand().getType() == Material.BOW) 
				{
					if (XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) != 0) 
					{
						PlayerManager.addSkillXP(player.getUniqueId().toString(), "Bow", (1 + player.getLocation().distance(event.getEntity().getLocation()) * 0.03) * event.getFinalDamage() * XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) );
						//main.ConsoleMsg(ChatColor.LIGHT_PURPLE , "Damage Event, XP: " + XpManager.getSkillXP("Combat", event.getEntity().getType().toString()  ));
					}
				}

				//Crossbow
				
				if(player.getInventory().getItemInMainHand().getType() == Material.CROSSBOW) 
				{
					if (XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) != 0) 
					{
						PlayerManager.addSkillXP(player.getUniqueId().toString(), "Crossbow", (1 + player.getLocation().distance(event.getEntity().getLocation()) * 0.03) * event.getFinalDamage() * XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) );
						//main.ConsoleMsg(ChatColor.LIGHT_PURPLE , "Damage Event, XP: " + XpManager.getSkillXP("Combat", event.getEntity().getType().toString()  ));
					}
				}
			}
		} 
		
		// Nahkampf
		
		else if (event.getDamager() instanceof Player && event.getCause() == DamageCause.ENTITY_ATTACK) 		
		{
			Player player = (Player) event.getDamager();
			//main.ConsoleMsg(ChatColor.AQUA , "Damage Event item: " + player.getInventory().getItemInMainHand().getType() + " from " + player.getName() + " Damage: " + event.getFinalDamage());
			
			//Sword
			
			if(player.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("sword")) 
			{
				if (XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) != 0) 
				{
					PlayerManager.addSkillXP(player.getUniqueId().toString(), "Sword", event.getFinalDamage() * XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) );
					//main.ConsoleMsg(ChatColor.LIGHT_PURPLE , "Damage Event, XP: " + XpManager.getSkillXP("Combat", event.getEntity().getType().toString()  ));
				}
			}
			
			//Axe
			
			else if(player.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("axe")) 
			{
				if (XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) != 0) 
				{
					PlayerManager.addSkillXP(player.getUniqueId().toString(), "Axe", event.getFinalDamage() * XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) );
					//main.ConsoleMsg(ChatColor.LIGHT_PURPLE , "Damage Event, XP: " + XpManager.getSkillXP("Combat", event.getEntity().getType().toString()  ));
				}
			}

			//Fist
			
			else 
			{
				if (XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) != 0) 
				{
					PlayerManager.addSkillXP(player.getUniqueId().toString(), "Fist", event.getFinalDamage() * XpManager.getSkillXP("Combat", event.getEntity().getType().toString()) );
					//main.ConsoleMsg(ChatColor.LIGHT_PURPLE , "Damage Event, XP: " + XpManager.getSkillXP("Combat", event.getEntity().getType().toString()  ));
				}
			}
		}
	}

	@EventHandler
    public void onEntityDamageBlockEvent(EntityDamageByBlockEvent event) 
	{
		
//		if (XpManager.getSkillXP("Fishing", item.getItemStack().getType().name()) != 0) 
//		{
//			PlayerManager.addSkillXP(event.getPlayer().getUniqueId().toString(), "Fishing", XpManager.getSkillXP("Fishing", item.getItemStack().getType().name()) );
//			main.ConsoleMsg(ChatColor.AQUA , "Fishing Event: Cought, XP: " + XpManager.getSkillXP("Fishing", item.getItemStack().getType().name()  ));
//		}
	}

}
