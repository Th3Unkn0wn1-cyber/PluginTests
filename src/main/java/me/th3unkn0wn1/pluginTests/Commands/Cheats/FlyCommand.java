package me.th3unkn0wn1.pluginTests.Commands.Cheats;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    public  boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("fly")) {

            if (sender instanceof Player p) {

                if(!(p.hasPermission("player.fly"))) {

                    p.sendMessage(ChatColor.RED + "You have no permission for this command!");

                }

                if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {

                    if (p.getAllowFlight()) {

                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.sendMessage(ChatColor.RED + "Flying disabled.");

                    } else {

                        p.setAllowFlight(true);
                        p.setFlying(true);
                        p.sendMessage(ChatColor.GREEN + "Flying enabled.");

                    }

                }

            } else {

                sender.sendMessage(ChatColor.RED + "Only players can use this command.");

            }

        }

        return true;

    }

}