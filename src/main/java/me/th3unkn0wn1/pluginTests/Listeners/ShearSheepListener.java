package me.th3unkn0wn1.pluginTests.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class ShearSheepListener implements Listener {

    @EventHandler
    public void onShearSheep(PlayerShearEntityEvent e) {

        Player player = e.getPlayer();
        Entity entity = e.getEntity();

        if (entity.getType() == EntityType.SHEEP) {
            player.sendMessage(ChatColor.RED + "You can't shear a sheep!");
            e.setCancelled(true);
        } else {
            player.sendMessage(ChatColor.GREEN + "This is not a sheep!");
        }

    }

}