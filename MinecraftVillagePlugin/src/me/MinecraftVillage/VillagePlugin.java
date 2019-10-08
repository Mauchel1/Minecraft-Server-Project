package me.MinecraftVillage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class VillagePlugin extends JavaPlugin
{
	
	public static VillagePlugin p;
	private static String preString = ChatColor.BLUE + "[Village" + ChatColor.DARK_GREEN + "Plugin] ";

	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	p = this;
    	
    	this.getCommand("vl").setExecutor(new VillageCommands());

    	ConsoleMsg(ChatColor.RED, "Plugin VillagePlugin gestartet");
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	ConsoleMsg(ChatColor.RED, "Plugin VillagePlugin gestoppt");
    	
    }
	
    public static void ConsoleMsg(ChatColor color, String msg) 
    {
    	
		Bukkit.getConsoleSender().sendMessage(preString + color + msg);

    }	

}
