package me.MinecraftVillage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class VillageCommands implements CommandExecutor{

	static BukkitTask particleVillagerTask;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
        if (sender instanceof Player) {
        	
        	if (args.length == 0) //leerer Aufruf
        	{
            	sender.sendMessage("HI");
            	//((Player) sender).getWorld().spawnParticle(Particle.VILLAGER_HAPPY, ((Player) sender).getLocation(), 1, 2, 20, 2, 1, Particle.VILLAGER_HAPPY.getDataType(), true);
            	//((Player) sender).getWorld().spawnParticle(Particle.VILLAGER_HAPPY, ((Player) sender).getLocation(), 100, 2, 20, 2);
            	((Player) sender).getWorld().spawnParticle(Particle.HEART, ((Player) sender).getLocation(), 100, 2, 20, 2, 1, null, true   );
            	//((Player) sender).spawnParticle(Particle.HEART, ((Player) sender).getLocation(), 100, 2, 20, 2, 1);
            	//https://www.spigotmc.org/threads/comprehensive-particle-spawning-guide-1-13.343001/

        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("showvillager"))
        	{
        		List<Villager> villagerList = new ArrayList<Villager>();
        		Collection<Entity> entitys = ((Player) sender).getWorld().getNearbyEntities(((Player) sender).getLocation(), 200, 100, 200);
        		for (Entity entity : entitys) {
        			if (entity instanceof Villager)
        			{
        				villagerList.add((Villager) entity);
        			}
				}
        		if (villagerList.isEmpty())
        		{
    				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Keine Villager gefunden");
        		} 
        		else
        		{
    				if (particleVillagerTask == null || particleVillagerTask.isCancelled()) {
        				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, villagerList.size() + " Villager gefunden");
        				particleVillagerTask = new TaskSpawnVillagerParticlePeriodically(villagerList, ((Player) sender).getWorld()).runTaskTimer(VillagePlugin.p, 15, 15);
    				}
    				else
    				{
    					particleVillagerTask.cancel();
    				}
        		}
        		
        	}

        }

		
		return true;
	}
	
//	private void SpawnParticleTreeAtLocation() 
//	{
//		
//	}
	
}

class TaskSpawnVillagerParticlePeriodically extends BukkitRunnable
{
	private List<Villager> villagerList;
	private World w;
	
	TaskSpawnVillagerParticlePeriodically(List<Villager> villagerList , World w)
	{
		this.villagerList = villagerList;
		this.w = w;
	}
	
	@Override
	public void run() {
		
		for (Villager villager : villagerList) {
        	w.spawnParticle(Particle.HEART, villager.getLocation(), 100, 0, 20, 0, 1, null, true);
		}
	}
}

