package me.LesepultPlugin.Listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import me.LesepultPlugin.LesepultPlugin;


public class PlayerChangedWorldListener implements Listener
{
	@EventHandler
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) 
	{
		LesepultPlugin.ConsoleMsg(ChatColor.GOLD , "Found Pults: " + event.getPlayer().getWorld().getName());

		if (event.getPlayer().getWorld().getName().equals("world")) 
		{
			List<Block> pultlist = searchForLesepults(event.getPlayer());
			
			//LesepultPlugin.ConsoleMsg(ChatColor.GOLD , "Found Pults: " + pultlist.size());
			int counter = 0;
			
			for (Block pult : pultlist) 
			{
				if (ObsidianUnder(pult)) 
				{
					if(pult.getState() instanceof Lectern) {
						counter++;
						Lectern lectern = (Lectern) pult.getState();
						//LesepultPlugin.ConsoleMsg(ChatColor.GOLD , "Found lectern + " + pult.getState() + pult.getState().getClass() + pult.getState().getData());
						
						if(lectern.getInventory().getItem(0) != null)
						{
							lectern.getInventory().setItem(0, lectern.getInventory().getItem(0));
						}
					}
				}
			}
			
			if(counter > 0)
			{
				LesepultPlugin.ConsoleMsg(ChatColor.GOLD, counter + " Lesepulte resettet.");
			}
		}
	}



	List<Block> searchForLesepults(Player player)
	{
		List<Block> pultlist = new ArrayList<Block>();
		World world = player.getWorld();
		int xstop = player.getLocation().getBlockX() + 50;
		int ystop = player.getLocation().getBlockY() + 50;
		int zstop = player.getLocation().getBlockZ() + 50;
		
		for (int x = player.getLocation().getBlockX() - 50; x < xstop; x++) 
		{
			for (int y = player.getLocation().getBlockY() - 50; y < ystop; y++) 
			{
				for (int z = player.getLocation().getBlockZ() - 50; z < zstop; z++) 
				{
					if(world.getBlockAt(x, y, z).getType().equals(Material.LECTERN))
					{
						pultlist.add(world.getBlockAt(x, y, z));
					}
				}
			}
		}
		return pultlist;
	}
	
	boolean ObsidianUnder(Block b)
	{
		for(int i = -1; i > -6; i--)
		{
			if(b.getRelative(0, i, 0).getType().equals(Material.OBSIDIAN))
			{
				return true;
			}
		}
		return false;
	}
	
}