package me.MinecraftSkills.main.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MiningCommands implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // Here we need to give items to our player
            // Create a new ItemStack (type: brick)
            ItemStack bricks = new ItemStack(Material.BRICK, 5);

            // Give the player our items (comma-seperated list of all ItemStack)
            player.getInventory().addItem(bricks);
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }

}
