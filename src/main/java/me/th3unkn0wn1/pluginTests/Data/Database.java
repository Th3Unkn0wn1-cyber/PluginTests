package me.th3unkn0wn1.pluginTests.Data;

import java.sql.*;

public class Database {

    private final String HOST;
    private final String PORT;
    private final String USER;
    private final String PASSWORD;

    public Database(String HOST, String PORT, String USER, String PASSWORD, String DATABASE_NAME) {

        this.HOST = HOST;
        this.PORT = PORT;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.DATABASE_NAME = DATABASE_NAME;

    }

    private final String DATABASE_NAME;

    private Connection connection;

    public Connection getConnection() throws SQLException {

        if (connection != null) {
            return connection;
        }

        String url = "jdbc:mysql://" + this.HOST + ":" + this.PORT + "/" + this.DATABASE_NAME;
        Connection connection = DriverManager.getConnection(url, this.USER, this.PASSWORD);

        this.connection = connection;

        System.out.println("Connected to database.");

        return connection;
    }

    public void initializeDatabase() throws SQLException {

        Statement statement = getConnection().createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS player_stats (uuid varchar(36) primary key, kills int, deaths int)";

        statement.execute(sql);

        statement.close();

    }

    public PlayerStats findPlayerStatsByUUID(String uuid) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_stats WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet resultSet = statement.executeQuery();

        PlayerStats playerStats;

        if(resultSet.next()){

            playerStats = new PlayerStats(
                    resultSet.getString("uuid"),
                    resultSet.getInt("kills"),
                    resultSet.getInt("deaths"));

            statement.close();

            return playerStats;
        }

        statement.close();

        return null;
    }

    public void createPlayerStats(PlayerStats playerStats) throws SQLException {

        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT INTO player_stats(uuid, coins, deaths, kills) VALUES (?, ?, ?)");
        statement.setString(1, playerStats.getPlayerUUID());
        statement.setInt(2, playerStats.getDeaths());
        statement.setInt(3, playerStats.getKills());

        statement.executeUpdate();

        statement.close();

    }

    public void updatePlayerStats(PlayerStats playerStats) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("UPDATE player_stats SET deaths = ?, kills = ? WHERE uuid = ?");
        statement.setInt(1, playerStats.getDeaths());
        statement.setInt(2, playerStats.getKills());
        statement.setString(3, playerStats.getPlayerUUID());

        statement.executeUpdate();

        statement.close();

    }

}