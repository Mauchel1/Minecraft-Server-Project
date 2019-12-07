package me.LesepultPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.LesepultPlugin.Listener.PlayerChangedWorldListener;


public class LesepultPlugin extends JavaPlugin{
	
	public static LesepultPlugin p; 
	private static String preString = ChatColor.BLUE + "[Lesepult" + ChatColor.DARK_GREEN + "Plugin] ";

	
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	p = this;
    	
    	registerEvents();

    	ConsoleMsg(ChatColor.RED, "Plugin LesepultPlugin gestartet");
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	ConsoleMsg(ChatColor.RED, "Plugin LesepultPlugin gestoppt");
    	
    }

    private void registerEvents()
    {
    	Bukkit.getPluginManager().registerEvents(new PlayerChangedWorldListener(), this);
    }

    
    public static void ConsoleMsg(ChatColor color, String msg) 
    {
    	
		Bukkit.getConsoleSender().sendMessage(preString + color + msg);

    }	


}
