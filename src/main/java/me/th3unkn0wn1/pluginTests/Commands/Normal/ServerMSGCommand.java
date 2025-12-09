package me.th3unkn0wn1.pluginTests.Commands.Normal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ServerMSGCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("send.serverMessage"))) {

            sender.sendMessage(ChatColor.RED + "You have no permission to use this command.");
            return true;

        }

        if (args.length < 1) {

            sender.sendMessage(ChatColor.RED + "Usage: /smsg <message>");
            return true;

        }

        StringBuilder smsg = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            smsg.append(args[i]).append(" ");
        }

        Bukkit.broadcastMessage(ChatColor.BLACK + "【" + ChatColor.BLUE + "SERVER" + ChatColor.BLACK + "】 ⋙ " + ChatColor.WHITE + smsg.toString().trim());

        return true;

    }
}