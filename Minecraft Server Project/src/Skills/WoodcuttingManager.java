package Skills;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;

import me.MinecraftSkills.main.PlayerManager;
import me.MinecraftSkills.main.SkillManager;
import me.MinecraftSkills.main.XpManager;
import me.MinecraftSkills.main.main;

public class WoodcuttingManager {
	
	static List<Woodplayer> WoodplayerList = new ArrayList<Woodplayer>();

	public static void useSuperAxe(String UUID)
	{

		Woodplayer wp = getWoodplayerByUUID(UUID);
		
		if (wp.isSuperAxeReady() && wp.canUseSuperAxe())
		{
			wp.superAxeInUse = true;
			wp.setLastTimeUsed();
    		Bukkit.getPlayer(java.util.UUID.fromString(UUID)).sendMessage(ChatColor.DARK_GREEN + "SuperAxt gestartet! Hau weg den Baum!");
			main.ConsoleMsg(ChatColor.DARK_GREEN , "WoodcuttingManager: " + "Player SuperAxe Ability startet");
			wp.StartAxeInUseTimer();
		}
	}
	
	public static boolean isSuperAxeInUse(String UUID) 
	{
		return getWoodplayerByUUID(UUID).superAxeInUse;
	}

	private static Woodplayer getWoodplayerByUUID(String UUID)
	{
		Woodplayer wp = null;
		
		for (Woodplayer woodplayer : WoodplayerList) {
			if (woodplayer.UUID.equals(UUID)) 
			{
				return woodplayer;
			}
		}
		wp = new Woodplayer(UUID);
		WoodplayerList.add(wp);

		return wp;
	}

	public static void cutDownTree(String UUID, Block initblock) {
		
		List<Block> ToCheckBlocks = new ArrayList<Block>();
		List<Block> CheckedBlocks = new ArrayList<Block>();
		List<Block> FoundBlocks = new ArrayList<Block>();
		
		ToCheckBlocks.add(initblock);
		
		main.ConsoleMsg(ChatColor.DARK_GREEN , "WoodcuttingManager: " + "Cutting down tree");

		int overflow = 0;
		int maxBlocks = (int) (PlayerManager.getSkillLvl(UUID, "Woodcutting") * SkillManager.getSkillConfigEntry("Ability", "Woodcutting", "SuperAxe", "maxBlockNumberLvlMultiplyer") );
		
		while (ToCheckBlocks.size() > 0) 
		{

			// Catch maximum Treesize
			if (overflow > maxBlocks )
			{
				break;
			}
			
			Block block = ToCheckBlocks.get(0);
			if (block.getType()  == initblock.getType()) 
			{

				FoundBlocks.add(block);
			
				//Block darüber (Y nach oben)
				
				List<Block> adjacentBlocks = new ArrayList<Block>();
				
				adjacentBlocks.add(block.getLocation().add(0,1,0).getBlock());
				adjacentBlocks.add(block.getLocation().add(1,0,0).getBlock());
				adjacentBlocks.add(block.getLocation().add(-1,0,0).getBlock());
				adjacentBlocks.add(block.getLocation().add(0,0,1).getBlock());
				adjacentBlocks.add(block.getLocation().add(0,0,-1).getBlock());

				adjacentBlocks.add(block.getLocation().add(1,0,1).getBlock());
				adjacentBlocks.add(block.getLocation().add(-1,0,1).getBlock());
				adjacentBlocks.add(block.getLocation().add(1,0,-1).getBlock());
				adjacentBlocks.add(block.getLocation().add(-1,0,-1).getBlock());

				adjacentBlocks.add(block.getLocation().add(1,1,0).getBlock());
				adjacentBlocks.add(block.getLocation().add(0,1,1).getBlock());
				adjacentBlocks.add(block.getLocation().add(-1,1,0).getBlock());
				adjacentBlocks.add(block.getLocation().add(0,1,-1).getBlock());

				adjacentBlocks.add(block.getLocation().add(1,1,1).getBlock());
				adjacentBlocks.add(block.getLocation().add(-1,1,1).getBlock());
				adjacentBlocks.add(block.getLocation().add(1,1,-1).getBlock());
				adjacentBlocks.add(block.getLocation().add(-1,1,-1).getBlock());

				for (Block adjacentBlock : adjacentBlocks) 
				{
					if (!CheckedBlocks.contains(adjacentBlock) && !ToCheckBlocks.contains(adjacentBlock)) 
					{
						ToCheckBlocks.add(adjacentBlock);
					}
				}
				adjacentBlocks.clear();
				overflow++;

			}
			
			CheckedBlocks.add(block);
			ToCheckBlocks.remove(block);
		}
		
		main.ConsoleMsg(ChatColor.DARK_GREEN , "WoodcuttingManager: " + FoundBlocks.size() + " Blocks found. Cutting them down now");

		
		PlayerManager.addSkillXP(UUID, "Woodcutting", (FoundBlocks.size() - 1) * (XpManager.getSkillXP("Woodcutting", initblock.getType().name())/2 ));

		for (Block block : FoundBlocks) 
		{
			block.breakNaturally();

			//block.setType(Material.AIR);
		}
		
		getWoodplayerByUUID(UUID).stopSuperAxe();
		
	}
}

class Woodplayer
{
	String UUID;
	private long lastTimeUsed = 0;
	boolean superAxeInUse = false;
	private Timer timer = new Timer();

	Woodplayer(String UUID) 
	{
		this.UUID = UUID;
	}
	
	public boolean canUseSuperAxe() {
		if (PlayerManager.getSkillLvl(UUID, "Woodcutting") >= SkillManager.getSkillConfigEntry("Ability", "Woodcutting", "SuperAxe", "activationLevel")) 
		{
			return true;
		} else 
		{
			Bukkit.getPlayer(java.util.UUID.fromString(UUID)).sendMessage(ChatColor.DARK_GREEN + "SuperAxt kann erst ab Level " + (int) SkillManager.getSkillConfigEntry("Ability", "Woodcutting", "SuperAxe", "activationLevel") + " verwendet werden.");
			return false;
		}
	}

	public void StartAxeInUseTimer() {
		timer.schedule(new TaskSwitchSuperAxeOff(this, UUID), (int) SkillManager.getSkillConfigEntry("Ability", "Woodcutting", "SuperAxe", "timeStaysReady") * 1000);
		
	}

	public void setLastTimeUsed(){lastTimeUsed = System.currentTimeMillis();}
	
	public boolean isSuperAxeReady()
	{
		int cooldownTime = (int) (SkillManager.getSkillConfigEntry("Ability", "Woodcutting", "SuperAxe", "maxTimeToNextUse")) - (int) (PlayerManager.getSkillLvl(UUID, "Woodcutting") * SkillManager.getSkillConfigEntry("Ability", "Woodcutting", "SuperAxe", "timeReducedPerLevel"));
		cooldownTime = (cooldownTime < (int) SkillManager.getSkillConfigEntry("Ability", "Woodcutting", "SuperAxe", "minTimeToNextUse")) ? (int) SkillManager.getSkillConfigEntry("Ability", "Woodcutting", "SuperAxe", "minTimeToNextUse"): cooldownTime;
		if (System.currentTimeMillis() <= lastTimeUsed + (cooldownTime * 1000) ) 
		{
			//main.ConsoleMsg(ChatColor.DARK_GREEN , "WoodcuttingManager: " + "lastTimeUsed " + lastTimeUsed + " CurrentTime " + System.currentTimeMillis());
			Bukkit.getPlayer(java.util.UUID.fromString(UUID)).sendMessage(ChatColor.DARK_GREEN + "SuperAxt bereit in " + (int) ((cooldownTime * 1000) - (System.currentTimeMillis() - lastTimeUsed))/1000 + " Sekunden");
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	public void stopSuperAxe()
	{
		superAxeInUse = false;
		//timer.cancel();
		main.ConsoleMsg(ChatColor.DARK_GREEN , "WoodcuttingManager: " + "Player SuperAxe Ability stopped");
	}
}


class TaskSwitchSuperAxeOff extends TimerTask
{
	private Woodplayer wp;
	private String UUID;
	
	public TaskSwitchSuperAxeOff(Woodplayer wp, String UUID) {
		this.wp = wp;
		this.UUID = UUID;
	}
	
	@Override
	public void run() {
		if (wp.superAxeInUse)
		{
			wp.superAxeInUse = false;
			main.ConsoleMsg(ChatColor.DARK_GREEN , "WoodcuttingManager: " + "Player SuperAxe Ability timed out");
    		Bukkit.getPlayer(java.util.UUID.fromString(UUID)).sendMessage(ChatColor.DARK_GREEN + "SuperAxt gestoppt!");

		}
	}
}
