package me.th3unkn0wn1.pluginTests.Data;

public class PlayerStats {

    private String playerUUID;

    private int kills;
    private int deaths;

    public PlayerStats(String playerUUID, int kills, int deaths) {

        this.playerUUID = playerUUID;
        this.kills = kills;
        this.deaths = deaths;


    }

    public String getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(String playerUUID) {
        this.playerUUID = playerUUID;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

}