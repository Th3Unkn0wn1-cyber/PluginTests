package me.th3unkn0wn1.pluginTests.Commands.Kill;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DieCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /die - kills the player
        if (command.getName().equalsIgnoreCase("die")) {

            if (sender instanceof Player p) {

                p.setHealth(0);
                p.sendMessage(ChatColor.BOLD + "" + ChatColor.BLUE + "You've decided to die!");

            } else {

                sender.sendMessage(ChatColor.RED + "You've no permission to use this command!");

            }

            return true;

        }

        return true;

    }

}