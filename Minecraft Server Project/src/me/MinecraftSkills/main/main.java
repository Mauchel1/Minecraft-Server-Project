package me.MinecraftSkills.main;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.MinecraftSkills.main.Commands.*;
import me.MinecraftSkills.main.Listener.*;

public class main extends JavaPlugin implements Listener{

    
	public static main p;
	private static String preString = ChatColor.BLUE + "[Minecraft" + ChatColor.DARK_GREEN + "Skills] ";
	
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	p = this;
    	PlayerManager.loadConfig();
    	XpManager.loadConfig();
    	SkillManager.loadConfig();
    	registerCommands();
    	registerEvents();
    	
    	//Bukkit.getOperators().add(Bukkit.getPlayer(java.util.UUID.fromString("7bb92f59-e507-4e69-a08c-373e6dcff48a")));    
    	
    	ConsoleMsg(ChatColor.RED, "Plugin MinecraftSkills gestartet");
    	//System.out.println("Plugin MinecraftSkills gestartet");
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	PlayerManager.saveConfig();
    	XpManager.saveConfig();
    	SkillManager.saveConfig();
    	//System.out.println("Plugin MinecraftSkills gestoppt");
    	ConsoleMsg(ChatColor.RED, "Plugin MinecraftSkills gestoppt");
    	
    }
    
    private void registerCommands() 
    {
    	this.getCommand("skill").setExecutor(new SkillCommand());
    	this.getCommand("skill_mining").setExecutor(new MiningCommand());
    	this.getCommand("skill_agility").setExecutor(new AgilityCommand());
    	this.getCommand("skill_alchemy").setExecutor(new AlchemyCommand());
    	this.getCommand("skill_farming").setExecutor(new FarmingCommand());
    	this.getCommand("skill_fishing").setExecutor(new FishingCommand());
    	this.getCommand("skill_woodcutting").setExecutor(new WoodcuttingCommand());
    	this.getCommand("skill_digging").setExecutor(new DiggingCommand());
    	this.getCommand("skill_sword").setExecutor(new SwordCommand());
    	this.getCommand("skill_axe").setExecutor(new AxeCommand());
    	this.getCommand("skill_fist").setExecutor(new FistCommand());
    	this.getCommand("skill_bow").setExecutor(new BowCommand());
    	this.getCommand("skill_crossbow").setExecutor(new CrossbowCommand());
    }
    
    private void registerEvents()
    {
    	Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    	Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
    	Bukkit.getPluginManager().registerEvents(new FishingListener(), this);
    	Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
    	Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }
    
    public static String convertName(String inputstring) 
    {
    	String output = "";
    	String[] substrings = inputstring.split("_");

    	int size = 1;
    	for (String s : substrings) 
    	{
    		
    		output = output.concat(s.substring(0, 1).toUpperCase());
    	    output = output.concat(s.substring(1).toLowerCase());
            if (size < substrings.length) {
                output = output.concat("_");
            }

            size++;

    	}
    	
    	return output;
    }
    
    public static void ConsoleMsg(ChatColor color, String msg) 
    {
    	
		Bukkit.getConsoleSender().sendMessage(preString + color + msg);

    }	
	
}
