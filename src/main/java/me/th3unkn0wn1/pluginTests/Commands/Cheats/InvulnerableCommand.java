package me.th3unkn0wn1.pluginTests.Commands.Cheats;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvulnerableCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("invulnerable")) {

            if (sender instanceof Player p) {

                if(!(p.hasPermission("player.invulnerable"))) {

                    p.sendMessage(ChatColor.RED + "You have no permission for this command!");

                }

                if (p.getGameMode() ==  GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {

                    boolean Invulnerable = p.isInvulnerable();
                    p.setInvulnerable(!p.isInvulnerable());
                    sender.sendMessage(Invulnerable ? ChatColor.RED + "Invulnerability turned off." : ChatColor.GREEN + "Invulnerability turned on.");

                } else {

                    sender.sendMessage(ChatColor.RED + "You have to be in survival or Adventure to use this command!");

                }

            }

        }

        return true;

    }

}