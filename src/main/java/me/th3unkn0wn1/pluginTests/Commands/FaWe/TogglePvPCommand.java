package me.th3unkn0wn1.pluginTests.Commands.FaWe;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TogglePvPCommand implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command,String s, String[] args) {

            // Check if sender is Player
            if(sender instanceof Player) {

                if (sender.hasPermission("fawe.togglepvp")) {

                    Player player = (Player) sender;

                    // Check if sender selected a full zone (2 points)
                    LocalSession session = WorldEdit.getInstance().getSessionManager().get(BukkitAdapter.adapt(player));
                    Region region = session.getSelection(BukkitAdapter.adapt(player.getWorld()));
                    try {

                        // get player location
                        BlockVector3 vec = BlockVector3.at(
                                player.getLocation().getBlockX(),
                                player.getLocation().getBlockY(),
                                player.getLocation().getBlockZ()
                        );

                        // check if player is in region & set effect
                        if(region.contains(vec)) {
                            player.setInvulnerable(true);
                        }

                    } catch (IncompleteRegionException e) {
                        player.sendMessage(ChatColor.RED + "No zone is been set");
                    }

                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
                }

            } else {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
                return true;
            }

        return true;
    }
}
