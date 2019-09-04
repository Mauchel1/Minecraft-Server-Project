package me.MinecraftSkills.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{

	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"TestText");
    	System.out.println("Plugin MinecraftSkills gestartet");
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	System.out.println("Plugin MinecraftSkills gestoppt");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	return false;
    }
}
