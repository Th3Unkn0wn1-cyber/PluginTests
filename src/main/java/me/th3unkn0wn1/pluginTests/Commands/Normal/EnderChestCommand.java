package me.th3unkn0wn1.pluginTests.Commands.Normal;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {

        if (sender instanceof Player p) {

            p.openInventory(p.getEnderChest());

        } else {

            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
            return true;

        }

        return true;

    }

}