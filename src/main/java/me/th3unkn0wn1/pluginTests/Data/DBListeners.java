package me.th3unkn0wn1.pluginTests.Data;

import me.th3unkn0wn1.pluginTests.PluginTests;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class DBListeners implements Listener {

    private final Database database;

    public DBListeners(Database database) {
        this.database = database;
    }

    public PlayerStats getPlayerStatsFromDatabase(Player player) throws SQLException {

        PlayerStats playerStats = database.findPlayerStatsByUUID(player.getUniqueId().toString());

        if (playerStats == null) {
            playerStats = new PlayerStats(player.getUniqueId().toString(), 0, 0);
            database.createPlayerStats(playerStats);
        }

        return playerStats;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskAsynchronously(PluginTests.pluginTests , () -> {
            try {
                getPlayerStatsFromDatabase(player);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Could not create player stats on join.");
            }
        });
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {

        Player victim = e.getEntity();
        Player killer = victim.getKiller();

        Bukkit.getScheduler().runTaskAsynchronously(PluginTests.pluginTests , () -> {
            try {
                PlayerStats victimStats = getPlayerStatsFromDatabase(victim);
                victimStats.setDeaths(victimStats.getDeaths() + 1);
                database.updatePlayerStats(victimStats);

                if (killer != null) {
                    PlayerStats killerStats = getPlayerStatsFromDatabase(killer);
                    killerStats.setKills(killerStats.getKills() + 1);
                    database.updatePlayerStats(killerStats);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Could not update player stats on death/kill");

            }
        });
    }

}