package me.th3unkn0wn1.pluginTests.Commands.Cheats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public class CheatsInterface implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {

        if (sender instanceof Player p) {

            if ((!p.hasPermission("gui.cheat"))) {
                p.sendMessage(ChatColor.RED + "You have no permission for this command!");
                return true;
            }

            Inventory inv = Bukkit.createInventory(p, 9, ChatColor.AQUA + "Cheat Interface");
            ItemStack blocker = new ItemStack(Material.CHAIN);
            ItemStack gmc = new ItemStack(Material.GRASS_BLOCK);
            ItemStack gms = new ItemStack(Material.IRON_SWORD);
            ItemStack gma = new ItemStack(Material.MAP);
            ItemStack gsp = new ItemStack(Material.ENDER_EYE);
            ItemStack fly = new ItemStack(Material.ELYTRA);
            ItemStack god = new ItemStack(Material.SHIELD);
            ItemStack heal = new ItemStack(Material.POTION);
            PotionMeta meta = (PotionMeta) heal.getItemMeta();
            meta.setBasePotionType(PotionType.HEALING);
            heal.setItemMeta(meta);


            ItemMeta meta0 = blocker.getItemMeta();
            meta0.setDisplayName("");
            god.setItemMeta(meta0);

            ItemMeta meta1 = gmc.getItemMeta();
            meta1.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Creative");
            gmc.setItemMeta(meta1);

            ItemMeta meta2 = gms.getItemMeta();
            meta2.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Survival");
            gms.setItemMeta(meta2);

            ItemMeta meta3 = gma.getItemMeta();
            meta3.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Adventure");
            gma.setItemMeta(meta3);

            ItemMeta meta4 = gsp.getItemMeta();
            meta4.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Spectator");
            gsp.setItemMeta(meta4);

            ItemMeta meta5 = fly.getItemMeta();
            meta5.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Fly");
            fly.setItemMeta(meta5);

            ItemMeta meta6 = god.getItemMeta();
            meta6.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Invulnerable");
            god.setItemMeta(meta6);

            ItemMeta meta7 = heal.getItemMeta();
            meta7.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Heal");
            heal.setItemMeta(meta7);



            inv.setItem(0, blocker);
            inv.setItem(1, gmc);
            inv.setItem(2, gms);
            inv.setItem(3, gma);
            inv.setItem(4, gsp);
            inv.setItem(5, fly);
            inv.setItem(6, god);
            inv.setItem(7, heal);
            inv.setItem(8, blocker);

            p.openInventory(inv);

        } else {

            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;

        }

        return true;

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player)) return;
        Player p = (Player) e.getWhoClicked();

        if (!e.getView().getTitle().equals(ChatColor.AQUA + "Cheat Interface")) return;

        e.setCancelled(true);

        ItemStack clicked = e.getCurrentItem();
        if (clicked == null) return;

        switch (clicked.getType()) {

            case CHAIN:
                break;

            case GRASS_BLOCK:
                p.closeInventory();
                Bukkit.dispatchCommand(p, "gmc");
                break;

            case IRON_SWORD:
                p.closeInventory();
                Bukkit.dispatchCommand(p, "gms");
                break;

            case MAP:
                p.closeInventory();
                Bukkit.dispatchCommand(p, "gma");
                break;

            case ENDER_EYE:
                p.closeInventory();
                Bukkit.dispatchCommand(p, "gsp");
                break;

            case ELYTRA:
                p.closeInventory();
                Bukkit.dispatchCommand(p, "fly");
                break;

            case POTION:
                p.closeInventory();
                Bukkit.dispatchCommand(p, "heal");
                break;

            case SHIELD:
                p.closeInventory();
                Bukkit.dispatchCommand(p, "god");
                break;

        }

    }

}