package me.MinecraftSkills.main.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.MinecraftSkills.main.ScoreboardManager;
import me.MinecraftSkills.main.main;

public class SkillCommand implements CommandExecutor
{

protected static final main plugin = main.p;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
        	
        	if (args.length == 0) 
        	{
                sender.sendMessage("Scoreboard aufrufen args 1");
                ScoreboardManager.displayScoreboardLvl((Player) sender);
                sender.sendMessage("Scoreboard aufrufen fertig");
        	}
        	else if (args.length == 1 && args[0].toLowerCase().equals("xp"))
        	{
        		
	            sender.sendMessage("Scoreboard aufrufen args 2");
	            ScoreboardManager.displayScoreboardXp((Player) sender);
	            sender.sendMessage("Scoreboard aufrufen fertig");
        		
        	}

        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}