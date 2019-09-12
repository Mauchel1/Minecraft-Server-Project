package me.MinecraftSkills.main.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.MinecraftSkills.main.PlayerManager;
import me.MinecraftSkills.main.main;

public class FarmingCommand implements CommandExecutor
{

protected static final main plugin = main.p;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("Du hast " + PlayerManager.getSkillXP(((Player) sender).getUniqueId().toString(), "Farming") + " XP in Level " + PlayerManager.getSkillLvl(((Player) sender).getUniqueId().toString(), "Farming"));

        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}