package me.th3unkn0wn1.pluginTests;

import me.th3unkn0wn1.pluginTests.Commands.Cheats.*;
import me.th3unkn0wn1.pluginTests.Commands.FaWe.TogglePvPCommand;
import me.th3unkn0wn1.pluginTests.Commands.Normal.*;
import me.th3unkn0wn1.pluginTests.Data.DBListeners;
import me.th3unkn0wn1.pluginTests.Listeners.*;
import me.th3unkn0wn1.pluginTests.Data.Database;
import me.th3unkn0wn1.pluginTests.Commands.Gamemodes.GameModeCommands;
import me.th3unkn0wn1.pluginTests.Commands.Kill.DieCommand;
import org.bukkit.GameMode;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

public final class PluginTests extends JavaPlugin {

    public static PluginTests pluginTests;
    private final HashMap<UUID, GameMode> lastMode = new HashMap<>();

    private Database database;

    @Override
    public void onEnable() {

        pluginTests = this;
        System.out.println("It's me, the first plugin!");

        saveDefaultConfig();

        this.database = new Database(
                getConfig().getString("database.host"),
                getConfig().getString("database.port"),
                getConfig().getString("database.user"),
                getConfig().getString("database.password"),
                getConfig().getString("database.database_name"));
        try {
            this.database.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not initialize database.");
        }

        //Listener Toggle
        boolean XPBottleBreakListener = this.pluginTests.getConfig().getBoolean("listener_toggle.xpbottlebreaklistener");
        boolean ShearSheepListener = this.pluginTests.getConfig().getBoolean("listener_toggle.shearsheeplistener");
        boolean JoinLeaveListener = this.pluginTests.getConfig().getBoolean("listener_toggle.joinleavelistener");
        boolean CheatsInterface =  this.pluginTests.getConfig().getBoolean("listener_toggle.cheatsinterface");
        boolean ChatColorListener = this.pluginTests.getConfig().getBoolean("listener_toggle.chatcolorlistener");
        boolean DBListeners = this.pluginTests.getConfig().getBoolean("listener_toggle.dblisteners");

        //Command Toggle
        boolean gmc = this.pluginTests.getConfig().getBoolean("command_toggle.gmc");
        boolean gms = this.pluginTests.getConfig().getBoolean("command_toggle.gms");
        boolean gma = this.pluginTests.getConfig().getBoolean("command_toggle.gma");
        boolean gsp = this.pluginTests.getConfig().getBoolean("command_toggle.gsp");
        boolean die = this.pluginTests.getConfig().getBoolean("command_toggle.gdi");
        boolean heal = this.pluginTests.getConfig().getBoolean("command_toggle.heal");
        boolean fly =  this.pluginTests.getConfig().getBoolean("command_toggle.fly");
        boolean invulnerable = this.pluginTests.getConfig().getBoolean("command_toggle.invulnerable");
        boolean message =  this.pluginTests.getConfig().getBoolean("command_toggle.message");
        boolean smsg = this.pluginTests.getConfig().getBoolean("command_toggle.smsg");
        boolean enderchest = this.pluginTests.getConfig().getBoolean("command_toggle.enderchest");
        boolean togglepvp = this.pluginTests.getConfig().getBoolean("command_toggle.togglepvp");


        // register Events
        if (XPBottleBreakListener == true) {
            getServer().getPluginManager().registerEvents(new XPBottleBreakListener(), this);
        }
        if (ShearSheepListener == true) {
            getServer().getPluginManager().registerEvents(new ShearSheepListener(), this);
        }
        if (JoinLeaveListener == true) {
            getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        }
        if (CheatsInterface == true) {
            getServer().getPluginManager().registerEvents(new CheatsInterface(), this);
            this.getCommand("cheatinterface").setExecutor(new CheatsInterface());
        }
        if (ChatColorListener == true) {
            getServer().getPluginManager().registerEvents(new ChatColorListener(), this);
        }
        if (DBListeners == true) {
            getServer().getPluginManager().registerEvents(new DBListeners(database), this);
        }


        // Commands
        GameModeCommands gm = new GameModeCommands(lastMode);

        if (gmc == true) {
            this.getCommand("gmc").setExecutor(gm);
        }
        if (gms == true) {
            this.getCommand("gms").setExecutor(gm);
        }
        if (gma == true) {
            this.getCommand("gma").setExecutor(gm);
        }
        if (gsp == true) {
            this.getCommand("gsp").setExecutor(gm);
        }
        if (die == true) {
            this.getCommand("die").setExecutor(new DieCommand());
        }
        if (heal == true) {
            this.getCommand("heal").setExecutor(new HealCommand());
        }
        if (fly == true) {
            this.getCommand("fly").setExecutor(new FlyCommand());
        }
        if (invulnerable == true) {
            this.getCommand("invulnerable").setExecutor(new InvulnerableCommand());
        }
        if (message == true) {
            this.getCommand("message").setExecutor(new MessageCommand());
        }
        if (smsg == true) {
            this.getCommand("smsg").setExecutor(new MessageCommand());
        }
        if (enderchest == true) {
            this.getCommand("enderchest").setExecutor(new EnderChestCommand());
        }

        if (togglepvp == true) {
            this.getCommand("togglepvp").setExecutor(new TogglePvPCommand());
        }

    }

}