package me.th3unkn0wn1.pluginTests;

import me.th3unkn0wn1.pluginTests.Commands.Cheats.*;
import me.th3unkn0wn1.pluginTests.Commands.FaWe.TogglePvPCommand;
import me.th3unkn0wn1.pluginTests.Commands.Normal.*;
import me.th3unkn0wn1.pluginTests.Data.DBListeners;
import me.th3unkn0wn1.pluginTests.Listeners.*;
import me.th3unkn0wn1.pluginTests.Data.Database;
import me.th3unkn0wn1.pluginTests.Commands.Gamemodes.GameModeCommands;
import me.th3unkn0wn1.pluginTests.Commands.Kill.DieCommand;
import me.th3unkn0wn1.pluginTests.Listeners.Scoreboards.SBManager;
import me.th3unkn0wn1.pluginTests.Listeners.Scoreboards.Zone.ZoneListener;
import me.th3unkn0wn1.pluginTests.PAPIExpensions.ZoneExpension;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class PluginTests extends JavaPlugin {

    public static PluginTests pluginTests;
    private final HashMap<UUID, GameMode> lastMode = new HashMap<>();

    @Override
    public void onEnable() {

        pluginTests = this;
        System.out.println("It's me, the first plugin!");

        saveDefaultConfig();

        Database database = new Database(
                getConfig().getString("database.host"),
                getConfig().getString("database.port"),
                getConfig().getString("database.user"),
                getConfig().getString("database.password"),
                getConfig().getString("database.database_name"));
        try {
            database.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not initialize database.");
        }

        SBManager sbManager = new SBManager();
        ZoneListener zone = new ZoneListener(getConfig(), sbManager);

        //Listener Toggle
        boolean XPBottleBreakListener = pluginTests.getConfig().getBoolean("listener_toggle.xpbottlebreaklistener");
        boolean ShearSheepListener = pluginTests.getConfig().getBoolean("listener_toggle.shearsheeplistener");
        boolean JoinLeaveListener = pluginTests.getConfig().getBoolean("listener_toggle.joinleavelistener");
        boolean CheatsInterface =  pluginTests.getConfig().getBoolean("listener_toggle.cheatsinterface");
        boolean ChatColorListener = pluginTests.getConfig().getBoolean("listener_toggle.chatcolorlistener");
        boolean DBListeners = pluginTests.getConfig().getBoolean("listener_toggle.dblisteners");
        boolean ZoneListener = pluginTests.getConfig().getBoolean("listener_toggle.zonelistener");
        boolean ZoneExpension = pluginTests.getConfig().getBoolean("listener_toggle.zoneexpension");

        //Command Toggle
        boolean gmc = pluginTests.getConfig().getBoolean("command_toggle.gmc");
        boolean gms = pluginTests.getConfig().getBoolean("command_toggle.gms");
        boolean gma = pluginTests.getConfig().getBoolean("command_toggle.gma");
        boolean gsp = pluginTests.getConfig().getBoolean("command_toggle.gsp");
        boolean die = pluginTests.getConfig().getBoolean("command_toggle.gdi");
        boolean heal = pluginTests.getConfig().getBoolean("command_toggle.heal");
        boolean fly =  pluginTests.getConfig().getBoolean("command_toggle.fly");
        boolean invulnerable = pluginTests.getConfig().getBoolean("command_toggle.invulnerable");
        boolean message =  pluginTests.getConfig().getBoolean("command_toggle.message");
        boolean smsg = pluginTests.getConfig().getBoolean("command_toggle.smsg");
        boolean enderchest = pluginTests.getConfig().getBoolean("command_toggle.enderchest");
        boolean togglepvp = pluginTests.getConfig().getBoolean("command_toggle.togglepvp");


        // register Events
        if (XPBottleBreakListener) {
            getServer().getPluginManager().registerEvents(new XPBottleBreakListener(), this);
        }
        if (ShearSheepListener) {
            getServer().getPluginManager().registerEvents(new ShearSheepListener(), this);
        }
        if (JoinLeaveListener) {
            getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        }
        if (CheatsInterface) {
            getServer().getPluginManager().registerEvents(new CheatsInterface(), this);
            Objects.requireNonNull(this.getCommand("cheatinterface")).setExecutor(new CheatsInterface());
        }
        if (ChatColorListener) {
            getServer().getPluginManager().registerEvents(new ChatColorListener(), this);
        }
        if (DBListeners) {
            getServer().getPluginManager().registerEvents(new DBListeners(database), this);
        }
        if (ZoneListener) {
            getServer().getPluginManager().registerEvents(zone, this);
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            if (ZoneExpension) {
                new ZoneExpension(this, zone).register();
            }
        } else {
            System.out.println("Could not find PlaceholderAPI!");
        }


        // Commands
        GameModeCommands gm = new GameModeCommands(lastMode);

        if (gmc) {
            Objects.requireNonNull(this.getCommand("gmc")).setExecutor(gm);
        }
        if (gms) {
            Objects.requireNonNull(this.getCommand("gms")).setExecutor(gm);
        }
        if (gma) {
            Objects.requireNonNull(Objects.requireNonNull(this.getCommand("gma"))).setExecutor(gm);
        }
        if (gsp) {
            Objects.requireNonNull(this.getCommand("gsp")).setExecutor(gm);
        }
        if (die) {
            Objects.requireNonNull(this.getCommand("die")).setExecutor(new DieCommand());
        }
        if (heal) {
            Objects.requireNonNull(this.getCommand("heal")).setExecutor(new HealCommand());
        }
        if (fly) {
            Objects.requireNonNull(this.getCommand("fly")).setExecutor(new FlyCommand());
        }
        if (invulnerable) {
            Objects.requireNonNull(this.getCommand("invulnerable")).setExecutor(new InvulnerableCommand());
        }
        if (message) {
            Objects.requireNonNull(this.getCommand("message")).setExecutor(new MessageCommand());
        }
        if (smsg) {
            Objects.requireNonNull(this.getCommand("smsg")).setExecutor(new MessageCommand());
        }
        if (enderchest) {
            Objects.requireNonNull(Objects.requireNonNull(this.getCommand("enderchest"))).setExecutor(new EnderChestCommand());
        }
        if (togglepvp) {
            Objects.requireNonNull(this.getCommand("togglepvp")).setExecutor(new TogglePvPCommand());
        }

    }

}