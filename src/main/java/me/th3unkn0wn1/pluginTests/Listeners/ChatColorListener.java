package me.th3unkn0wn1.pluginTests.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatColorListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        String msg = event.getMessage();

        // Formats
        msg = msg.replace("&B", ChatColor.BOLD.toString());
        msg = msg.replace("&I", ChatColor.ITALIC.toString());
        msg = msg.replace("&U", ChatColor.UNDERLINE.toString());

        // Colors
        msg = msg.replace("&b", ChatColor.BLUE.toString());
        msg = msg.replace("&r", ChatColor.RED.toString());
        msg = msg.replace("&w", ChatColor.WHITE.toString());
        msg = msg.replace("&y", ChatColor.YELLOW.toString());
        msg = msg.replace("&g", ChatColor.GREEN.toString());
        msg = msg.replace("&p", ChatColor.DARK_PURPLE.toString());
        msg = msg.replace("&a", ChatColor.AQUA.toString());

    }

}