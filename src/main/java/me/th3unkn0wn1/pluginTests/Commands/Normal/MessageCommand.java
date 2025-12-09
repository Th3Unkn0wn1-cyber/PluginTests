package me.th3unkn0wn1.pluginTests.Commands.Normal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "You're not allowed to use this command!");
            return true;

        }

        Player p = (Player) sender;

        if (args.length < 2) {

            p.sendMessage(ChatColor.RED + "Usage: /message <player> <message>");
            return true;

        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {

            p.sendMessage(ChatColor.RED + "Player " + args[0] + " is not online!");
            return true;

        }

        StringBuilder msg = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            msg.append(args[i]).append(" ");
        }

        p.sendMessage(ChatColor.GREEN + "MSG" + ChatColor.DARK_GRAY + " ⋙ 【" + ChatColor.YELLOW + "You" + ChatColor.DARK_GRAY + " ➡ " + ChatColor.YELLOW + target.getName() + ChatColor.DARK_GRAY + "】" + ChatColor.WHITE + msg.toString().trim());
        target.sendMessage(ChatColor.RED + "MSG" + ChatColor.DARK_GRAY + "⋙ 【" + ChatColor.YELLOW + sender.getName() + ChatColor.DARK_GRAY + " ➡ " + ChatColor.YELLOW + "You" + ChatColor.DARK_GRAY + "】" + ChatColor.WHITE + msg.toString().trim());

        return true;

    }

}