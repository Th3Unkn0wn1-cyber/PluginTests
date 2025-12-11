package me.th3unkn0wn1.pluginTests.Listeners.Scoreboards;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SBManager {

    private final Map<UUID, Scoreboard> playerBoards = new HashMap<>();

    public void updateScoreboard(Player player) {

        UUID uuid = player.getUniqueId();

        Scoreboard board = playerBoards.get(uuid);
        if (board == null) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            board = manager.getNewScoreboard();
            playerBoards.put(uuid, board);
        }

        Objective obj = board.getObjective("scoreboard");
        if (obj == null) {
            obj = board.registerNewObjective("scoreboard", "dummy", "zone status");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        for (String entry : board.getEntries()) {
            board.resetScores(entry);
        }

        String status = PlaceholderAPI.setPlaceholders(player, "%zone_status%");
        Score score = obj.getScore(status);
        score.setScore(1);

        player.setScoreboard(board);
    }

}