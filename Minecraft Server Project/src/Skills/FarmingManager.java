package Skills;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import me.MinecraftSkills.main.main;

public class FarmingManager {

	protected static final main plugin = main.p;

	public static void GreenThumb(Block block, Material mat)
	{
		new TaskPlaceNewBlock(block, mat).runTaskLater(plugin, 10);
	}
	
}


class TaskPlaceNewBlock extends BukkitRunnable
{

	private Block block;
	private Material mat;
	
	TaskPlaceNewBlock(Block block, Material mat)
	{
		this.block = block;
		this.mat = mat;
	}
	
	@Override
	public void run() {
		
		block.setType(mat);
		// TODO Auto-generated method stub
		
	}
	
}