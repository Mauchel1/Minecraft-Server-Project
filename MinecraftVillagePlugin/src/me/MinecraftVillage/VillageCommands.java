package me.MinecraftVillage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bed;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class VillageCommands implements CommandExecutor{

	static BukkitTask particleVillagerTask;
	static BukkitTask particleBedTask;
	static BukkitTask particleMeetingTask;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
        	Player player = (Player) sender;
        	
        	if (args.length == 0) //leerer Aufruf
        	{
            	sender.sendMessage("Parameter: showvillager, showbeds, showmeeting, showowner, showmeetingvillager, showbrain, hidevillager, hidebeds, hidemeeting, hideowner, hidemeetingvillager, hidebrain");
            	//https://www.spigotmc.org/threads/comprehensive-particle-spawning-guide-1-13.343001/

        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("time"))
        	{
        		long time = player.getWorld().getTime();
        		
        		if (time < 2000)
        		{
                	sender.sendMessage("Dorf erkunden, Leute treffen. Zeit: " + time);
        		}
        		else if (time < 9000)
        		{
                	sender.sendMessage("Auf zur Arbeit! Zeit: " + time);
        		}
        		else if (time < 10000)
        		{
                	sender.sendMessage("Dorfversammlung! Zeit: " + time);
        		}
        		else if (time < 12000)
        		{
                	sender.sendMessage("Dorf erkunden, Leute treffen. Zeit: " + time);
        		}
        		else if (time < 13000)
        		{
                	sender.sendMessage("Alles schläft. Nur der Trottel läuft irgendwo umher. Zeit: " + time);
        		}
        		else if (time < 14000)
        		{
                	sender.sendMessage("Alles schläft. Nur der Trottel ist zu Hause wach. Zeit: " + time);
        		}
        		else
        		{
                	sender.sendMessage("Alles schläft. Zeit: " + time);
        		}            	
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("showvillager"))
        	{
        		List<Villager> villagerList = getNearbyVillagers(player.getWorld(), player.getLocation());
        		if (villagerList.isEmpty())
        		{
    				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Keine Villager gefunden");
    				sender.sendMessage("Keine Villager gefunden");
        		} 
        		else
        		{
					StartVillagerTask(player.getWorld(), villagerList);
        		}
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("hidevillager"))
        	{
        		StopVillagerTask();
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("showbeds"))
        	{
        		List<Villager> villagerList = getNearbyVillagers(player.getWorld(), player.getLocation());
        		if (villagerList.isEmpty())
        		{
    				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Keine Villager gefunden");
    				sender.sendMessage("Keine Villager gefunden");
        		} 
        		else
        		{
        			StartBedTask(player.getWorld(), villagerList);
        		}
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("hidebeds"))
        	{
        		StopBedTask();
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("showmeeting"))
        	{
        		List<Villager> villagerList = getNearbyVillagers(player.getWorld(), player.getLocation());
        		if (villagerList.isEmpty())
        		{
    				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Keine Villager gefunden");
    				sender.sendMessage("Keine Villager gefunden");
        		} 
        		else
        		{
        			StartMeetingPointTask(player.getWorld(), villagerList);
        		}
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("hidemeeting"))
        	{
        		StopMeetingPointTask();
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("showowner"))
        	{
        		if (player.getTargetBlock(null, 15).getType().toString().toLowerCase().contains("_bed")) 
        		{
        			Bed b = (Bed) player.getTargetBlock(null, 15).getBlockData();
        			Location bedLoc;
        			if(b.getPart() == Bed.Part.HEAD)
        			{
        				//VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Kopfpart " + b.getFacing().getOppositeFace());
        				bedLoc = player.getTargetBlock(null, 15).getLocation();
        	        }
        			else
        			{
        				//VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Fusspart " + b.getFacing());
        				bedLoc = (((Block) player.getTargetBlock(null, 15)).getRelative(b.getFacing())).getLocation();
        	        }
            		List<Villager> villagerList = getNearbyVillagers(player.getWorld(), player.getLocation());
            		if (villagerList.isEmpty())
            		{
        				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Keine Villager gefunden");
        				sender.sendMessage("Keine Villager gefunden");
            		} 
            		else
            		{
            			for (Villager villager : villagerList) {
            				if (villager.getMemory(MemoryKey.HOME) != null && villager.getMemory(MemoryKey.HOME).equals(bedLoc)) 
							{
		        				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "HAB IHN");
		        				sender.sendMessage("HAB IHN");

								List<Villager> vList = new ArrayList<Villager>();
								vList.add(villager);
								StartVillagerTask(player.getWorld(), vList);
								break;
							}
						}
            		}
        		}
        		else 
        		{
    				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Du musst ein Bett angucken!");
    				sender.sendMessage("Du musst ein Bett angucken!");
        		}
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("hideowner"))
        	{
        		StopVillagerTask();
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("showmeetingvillager"))
        	{
        		if (player.getTargetBlock(null, 15).getType().equals(Material.BELL)) 
        		{
	        		List<Villager> villagerList = getNearbyVillagers(player.getWorld(), player.getLocation());
            		if (villagerList.isEmpty())
            		{
        				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Keine Villager gefunden");
        				sender.sendMessage("Keine Villager gefunden");
            		} 
            		else
            		{
						List<Villager> vList = new ArrayList<Villager>();
            			for (Villager villager : villagerList) {
            				if (villager.getMemory(MemoryKey.MEETING_POINT) != null && villager.getMemory(MemoryKey.MEETING_POINT).equals(player.getTargetBlock(null, 15).getLocation())) 
							{
								vList.add(villager);
							}
						}
            			if (!vList.isEmpty()) 
            			{
	            			StartVillagerTask(player.getWorld(), vList);
            			}
            			else
            			{
            				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Keine Villager haben diesen MeetingPoint gefunden");
            				sender.sendMessage("Keine Villager haben diesen MeetingPoint gefunden");
            			}
            		}
        		}
        		else 
        		{
    				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Du musst eine Glocke angucken!");
    				sender.sendMessage("Du musst eine Glocke angucken!");
    			}
			}
        	else if (args.length == 1 && args[0].toLowerCase().equals("hidemeetingvillager"))
        	{
        		StopVillagerTask();
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("showbrain"))
        	{
        		Villager villager = getNearestVillager(player.getWorld(), player.getLocation());
        		if(villager != null)
        		{
					List<Villager> villagerList = new ArrayList<Villager>();
					villagerList.add(villager);
					StartVillagerTask(player.getWorld(), villagerList);
    				if (villager.getMemory(MemoryKey.MEETING_POINT) != null) 
					{
    					StartMeetingPointTask(player.getWorld(), villagerList);
	    			}
	    			else
	    			{
	    				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Der Villager hat keinen MeetingPoint gefunden");
	    				sender.sendMessage("Der Villager hat keinen MeetingPoint gefunden");
	    			}
    				if (villager.getMemory(MemoryKey.HOME) != null) 
					{
    					StartBedTask(player.getWorld(), villagerList);
	    			}
	    			else
	    			{
	    				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Der Villager hat kein Zuhause");
	    				sender.sendMessage("Der Villager hat kein Zuhause");
	    			}
        		}
        		else
        		{
    				VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "Kein Villager in unmittelbarer Nähe");
    				sender.sendMessage("Kein Villager in unmittelbarer Nähe");
        		}
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("hidebrain"))
        	{
        		StopVillagerTask();
        		StopBedTask();
        		StopMeetingPointTask();
        	}
        }		
		return true;
	}
	
	List<Villager> getNearbyVillagers(World w, Location loc)
	{
		List<Villager> villagerList = new ArrayList<Villager>();
		Collection<Entity> entitys = (w.getNearbyEntities(loc, 200, 100, 200));
		for (Entity entity : entitys) {
			if (entity instanceof Villager)
			{
				villagerList.add((Villager) entity);
			}
		}
		return villagerList;
	}
	
	Villager getNearestVillager(World w, Location loc)
	{
		Villager nearestVillager = null;
		double distance = 99999;
		Collection<Entity> entitys = (w.getNearbyEntities(loc, 10, 5, 10));
		for (Entity entity : entitys) {
			if (entity instanceof Villager)
			{
				if (entity.getLocation().distance(loc) < distance)
				{
					distance = entity.getLocation().distance(loc);
					nearestVillager = (Villager) entity;
				}
			}
		}
		return nearestVillager;
	}
	
	private void StopMeetingPointTask()
	{
		if (particleMeetingTask != null && !particleMeetingTask.isCancelled())
		{
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "MeetingPoint Marker gestoppt");
			Bukkit.broadcastMessage( "MeetingPoint Marker gestoppt");
			particleMeetingTask.cancel();
		}
	}
	
	private void StartMeetingPointTask(World w, List<Villager> villagerList)
	{
		if (particleMeetingTask == null || particleMeetingTask.isCancelled()) {
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, villagerList.size() + " Villager gefunden");
			Bukkit.broadcastMessage( villagerList.size() + " Villager gefunden");
			particleMeetingTask = new TaskSpawnMeetingPointParticlePeriodically(villagerList, w).runTaskTimer(VillagePlugin.p, 6, 6);
		}
		else
		{
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, "MeetingPoint Marker restarted");
			Bukkit.broadcastMessage("MeetingPoint Marker restarted");
			particleMeetingTask.cancel();
			particleMeetingTask = new TaskSpawnMeetingPointParticlePeriodically(villagerList, w).runTaskTimer(VillagePlugin.p, 6, 6);
		}
	}

	private void StopBedTask()
	{
		if (particleBedTask != null && !particleBedTask.isCancelled())
		{
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, " Bedmarker gestoppt");
			Bukkit.broadcastMessage( " Bedmarker gestoppt");
			particleBedTask.cancel();
		}
	}
	
	private void StartBedTask(World w, List<Villager> villagerList)
	{
		if (particleBedTask == null || particleBedTask.isCancelled()) {
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, villagerList.size() + " Villager gefunden");
			Bukkit.broadcastMessage( villagerList.size() + " Villager gefunden");
			particleBedTask = new TaskSpawnBedParticlePeriodically(villagerList, w).runTaskTimer(VillagePlugin.p, 15, 15);
		}
		else
		{
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, " Bedmarker restarted");
			Bukkit.broadcastMessage(" Bedmarker restarted");
			particleBedTask.cancel();
			particleBedTask = new TaskSpawnBedParticlePeriodically(villagerList, w).runTaskTimer(VillagePlugin.p, 15, 15);
		}
	}
	
	private void StopVillagerTask()
	{
		if (particleVillagerTask != null && !particleVillagerTask.isCancelled())
		{
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, " Villagermarker gestoppt");
			Bukkit.broadcastMessage(" Villagermarker gestoppt");
			particleVillagerTask.cancel();
		}
	}
	
	private void StartVillagerTask(World w, List<Villager> villagerList)
	{
		if (particleVillagerTask == null || particleVillagerTask.isCancelled()) {
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, villagerList.size() + " Villager gemarkert");
			Bukkit.broadcastMessage(villagerList.size() + " Villager gemarkert");
			particleVillagerTask = new TaskSpawnVillagerParticlePeriodically(villagerList, w).runTaskTimer(VillagePlugin.p, 15, 15);
		}
		else
		{
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, " Villagermarker restarted");
			Bukkit.broadcastMessage(" Villagermarker restarted");
			particleVillagerTask.cancel();
			particleVillagerTask = new TaskSpawnVillagerParticlePeriodically(villagerList, w).runTaskTimer(VillagePlugin.p, 15, 15);
		}
	}
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
        	w.spawnParticle(Particle.VILLAGER_HAPPY, villager.getLocation(), 40, 0, 10, 0, 1, null, true);
		}
	}
}

class TaskSpawnBedParticlePeriodically extends BukkitRunnable
{
	private List<Villager> villagerList;
	private World w;
	
	TaskSpawnBedParticlePeriodically(List<Villager> villagerList , World w)
	{
		this.villagerList = villagerList;
		this.w = w;
	}
	
	@Override
	public void run() {
		
		for (Villager villager : villagerList) {
			Location bedLoc = villager.getMemory(MemoryKey.HOME);
			if (bedLoc != null) 
			{
				w.spawnParticle(Particle.HEART, villager.getMemory(MemoryKey.HOME), 30, 0, 10, 0, 1, null, true);
			}
		}
	}
}

class TaskSpawnMeetingPointParticlePeriodically extends BukkitRunnable
{
	private List<Villager> villagerList;
	private World w;
	
	TaskSpawnMeetingPointParticlePeriodically(List<Villager> villagerList , World w)
	{
		this.villagerList = villagerList;
		this.w = w;
	}
	
	@Override
	public void run() {
		List<Location> meetingPoints = new ArrayList<Location>();		
		for (Villager villager : villagerList) {
			Location meetLoc = villager.getMemory(MemoryKey.MEETING_POINT);
			if (meetLoc != null && !meetingPoints.contains(meetLoc)) 
			{
				meetingPoints.add(meetLoc);
				w.spawnParticle(Particle.NOTE, villager.getMemory(MemoryKey.MEETING_POINT), 40, 0, 10, 0, 1, null, true);
			}
		}
		if (meetingPoints.isEmpty()) 
		{
			VillagePlugin.ConsoleMsg(ChatColor.YELLOW, villagerList.size() + " kein Meeting Point gefunden");
			Bukkit.broadcastMessage(villagerList.size() + " kein Meeting Point gefunden");
		}
	}
}
