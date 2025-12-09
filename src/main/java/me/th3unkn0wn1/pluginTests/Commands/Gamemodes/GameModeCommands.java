package me.th3unkn0wn1.pluginTests.Commands.Gamemodes;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GameModeCommands implements CommandExecutor {

    private final HashMap<UUID, GameMode> lastMode;

    public GameModeCommands(HashMap<UUID, GameMode> lastMode) {
        this.lastMode = lastMode;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return switch (command.getName().toLowerCase()) {

            case "gmc" -> {
                if (sender instanceof Player p) {
                    UUID uuid = p.getUniqueId();
                    if (p.hasPermission("gamemode.switch.creative")) {
                        if (p.getGameMode() != GameMode.CREATIVE) {
                            lastMode.put(uuid, p.getGameMode());
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(ChatColor.ITALIC + "You switched to creative!");
                        } else {
                            if (lastMode.containsKey(uuid)) {
                                GameMode old = lastMode.get(uuid);
                                p.setGameMode(old);
                                String name = old.toString().toLowerCase();
                                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                                p.sendMessage(ChatColor.ITALIC + "You switched to " + name + " !");
                                lastMode.remove(uuid);
                            } else {
                                p.setGameMode(GameMode.SURVIVAL);
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                }
                yield true;
            }

            case "gms" -> {
                if (sender instanceof Player p) {
                    UUID uuid = p.getUniqueId();
                    if (p.hasPermission("gamemode.switch.survival")) {
                        if (p.getGameMode() != GameMode.SURVIVAL) {
                            lastMode.put(uuid, p.getGameMode());
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(ChatColor.ITALIC + "You switched to survival!");
                        } else {
                            if (lastMode.containsKey(uuid)) {
                                GameMode old = lastMode.get(uuid);
                                p.setGameMode(old);
                                String name = old.toString().toLowerCase();
                                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                                p.sendMessage(ChatColor.ITALIC + "You switched to " + name + " !");
                                lastMode.remove(uuid);
                            } else {
                                p.setGameMode(GameMode.SURVIVAL);
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                }
                yield true;
            }

            case "gma" -> {
                if (sender instanceof Player p) {
                    UUID uuid = p.getUniqueId();
                    if (p.hasPermission("gamemode.switch.adventure")) {
                        if (p.getGameMode() != GameMode.ADVENTURE) {
                            lastMode.put(uuid, p.getGameMode());
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(ChatColor.ITALIC + "You switched to adventure!");
                        } else {
                            if (lastMode.containsKey(uuid)) {
                                GameMode old = lastMode.get(uuid);
                                p.setGameMode(old);
                                String name = old.toString().toLowerCase();
                                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                                p.sendMessage(ChatColor.ITALIC + "You switched to " + name + " !");
                                lastMode.remove(uuid);
                            } else {
                                p.setGameMode(GameMode.SURVIVAL);
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                }
                yield true;
            }

            case "gsp" -> {
                if (sender instanceof Player p) {
                    UUID uuid = p.getUniqueId();
                    if (p.hasPermission("gamemode.switch.spectator")) {
                        if (p.getGameMode() != GameMode.SPECTATOR) {
                            lastMode.put(uuid, p.getGameMode());
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(ChatColor.ITALIC + "You switched to spectator!");
                        } else {
                            if (lastMode.containsKey(uuid)) {
                                GameMode old = lastMode.get(uuid);
                                p.setGameMode(old);
                                String name = old.toString().toLowerCase();
                                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                                p.sendMessage(ChatColor.ITALIC + "You switched to " + name + " !");
                                lastMode.remove(uuid);
                            } else {
                                p.setGameMode(GameMode.SURVIVAL);
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                }
                yield true;
            }

            default -> false;
        };
    }
}