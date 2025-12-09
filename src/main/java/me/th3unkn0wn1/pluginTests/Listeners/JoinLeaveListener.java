package me.th3unkn0wn1.pluginTests.Listeners;

import me.th3unkn0wn1.pluginTests.PluginTests;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        if (player.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.ITALIC + "" + ChatColor.BLACK + "Welcome Back to the Server " + ChatColor.BOLD + ChatColor.DARK_RED + player.getDisplayName());
        } else {
            e.setJoinMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "Welcome to the Server " + ChatColor.BOLD + ChatColor.GREEN + "! If you need help, ask some other Players");
        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + player.getName() + ChatColor.ITALIC + ChatColor.GRAY + " has left the server!");

    }

}