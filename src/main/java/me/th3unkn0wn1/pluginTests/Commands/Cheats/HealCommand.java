package me.th3unkn0wn1.pluginTests.Commands.Cheats;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {

        if (command.getName().equalsIgnoreCase("heal")) {

            if (sender instanceof Player p) {

                if(!(p.hasPermission("player.heal"))) {

                    p.sendMessage(ChatColor.RED + "You have no permission for this command!");

                }

                double health = p.getHealth();
                double maxHealth = p.getMaxHealth();
                int foodLevel = p.getFoodLevel();

                if (health == maxHealth && foodLevel == 20) {

                    sender.sendMessage(ChatColor.RED + "You can't use this command right now!");

                } else {

                    p.setHealth(20.0);
                    p.setFoodLevel(20);
                    sender.sendMessage(ChatColor.GREEN + "You have been healed!");

                }

            } else {

                sender.sendMessage(ChatColor.RED + "You have no permission to use this command!");

            }

        }

        return true;

    }

}