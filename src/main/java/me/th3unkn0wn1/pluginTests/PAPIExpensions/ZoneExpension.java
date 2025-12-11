package me.th3unkn0wn1.pluginTests.PAPIExpensions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.th3unkn0wn1.pluginTests.Listeners.Scoreboards.Zone.ZoneListener;
import me.th3unkn0wn1.pluginTests.PluginTests;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class ZoneExpension extends PlaceholderExpansion {

    private final ZoneListener zone;
    private final PluginTests plugin;

    public ZoneExpension(PluginTests plugin, ZoneListener zone) {
        this.plugin = plugin;
        this.zone = zone;
    }

    @Override public @NonNull String getIdentifier() { return "zone"; }
    @Override public @NonNull String getAuthor() { return "Th3unkn0wn1"; }
    @Override public @NonNull String getVersion() { return "1.0"; }

    @Override
    public String onPlaceholderRequest(Player player, String id) {

        if (id.equals("status")) {

            boolean inZone = zone.isPlayerInZone(player);
            return inZone
                    ? plugin.getConfig().getString("papi.messages.pvp_false")
                    : plugin.getConfig().getString("papi.messages.pvp_true");

        }

        return null;

    }

}
