package me.th3unkn0wn1.pluginTests.Listeners.Scoreboards.Zone;

import me.th3unkn0wn1.pluginTests.Listeners.Scoreboards.SBManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ZoneListener implements Listener {

    // needed for eventhandler in contains
    private final Map<UUID, Boolean> playerInZone = new HashMap<>();
    private final SBManager sbManager;

    private final World world;

    private final int minX, minY, minZ;
    private final int maxX, maxY, maxZ;

    public ZoneListener(FileConfiguration config, SBManager sbManager) {

        this.sbManager = sbManager;
        this.world = Bukkit.getWorld(Objects.requireNonNull(config.getString("zone.world")));

        Location corner1 = new Location(world,
                config.getInt("zone.corner1.x"),
                config.getInt("zone.corner1.y"),
                config.getInt("zone.corner1.z")
        );

        Location corner2 = new Location(world,
                config.getInt("zone.corner2.x"),
                config.getInt("zone.corner2.y"),
                config.getInt("zone.corner2.z")
        );

        this.minX = Math.min(corner1.getBlockX(), corner2.getBlockX());
        this.minY = Math.min(corner1.getBlockY(), corner2.getBlockY());
        this.minZ = Math.min(corner1.getBlockZ(), corner2.getBlockZ());
        this.maxX = Math.max(corner1.getBlockX(), corner2.getBlockX());
        this.maxY = Math.max(corner1.getBlockY(), corner2.getBlockY());
        this.maxZ = Math.max(corner1.getBlockZ(), corner2.getBlockZ());

    }

    public boolean contains(Location loc) {

        if (!loc.getWorld().equals(world)) return false;

        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();

        return x >= minX && x <= maxX &&
                z >= minZ && z <= maxZ &&
                y >= minY && y <= maxY;

    }

        public boolean isPlayerInZone(Player player) {
            return contains(player.getLocation());
        }

        @EventHandler
        public void onPlayerMove(PlayerMoveEvent event) {

            Player player = event.getPlayer();
            UUID uuid = event.getPlayer().getUniqueId();

            boolean currentlyInZone = isPlayerInZone(player);

            Boolean previouslyInZone = playerInZone.get(uuid);
            if (previouslyInZone != null && previouslyInZone == currentlyInZone) {
                return;
            }

            playerInZone.put(uuid, currentlyInZone);

            sbManager.updateScoreboard(player);

        }

}
