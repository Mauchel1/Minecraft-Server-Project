package me.MinecraftSkills.main.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.MinecraftSkills.main.PlayerManager;
import me.MinecraftSkills.main.ScoreboardManager;
import me.MinecraftSkills.main.main;


public class AgilityCommand implements CommandExecutor
{

protected static final main plugin = main.p;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("Du hast " + PlayerManager.getSkillXP(((Player) sender).getUniqueId().toString(), "Agility") + " XP in Level " + PlayerManager.getSkillLvl(((Player) sender).getUniqueId().toString(), "Agility"));
            sender.sendMessage("Scoreboard aufrufen");

            ScoreboardManager.displayScoreboard((Player) sender);
            sender.sendMessage("Scoreboard aufrufen fertig");
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}