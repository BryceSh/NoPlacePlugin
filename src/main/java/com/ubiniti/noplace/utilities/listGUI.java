package com.ubiniti.noplace.utilities;

import com.ubiniti.noplace.files.CConfig;
import com.ubiniti.noplace.listeners.listGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class listGUI {

    public static void openList(Player p) {

        Inventory menu = Bukkit.createInventory(p, 45, ChatColor.BLUE + "Blacklisted Blocks");
        List<String> disabledBlocks = CConfig.getConfig().getStringList("DisabledBlocks");

        for (int i = 0; i<disabledBlocks.size(); ++i) {

            ItemStack item = new ItemStack(Material.getMaterial(disabledBlocks.get(i)), 1);
            menu.setItem(i, item);

        }

        p.openInventory(menu);

    }

    public static void openConfirm(Player p) {

        Inventory menu = Bukkit.createInventory(p, 27, ChatColor.RED + "Confirm Removal");

        ItemStack block = new ItemStack(listGUIListener.clickedBlock, 1);
        ItemMeta blockM = block.getItemMeta();
        blockM.setDisplayName(ChatColor.GOLD + listGUIListener.clickedBlock.name());
        block.setItemMeta(blockM);

        ItemStack confirm = new ItemStack(Material.GREEN_WOOL, 1);
        ItemMeta confirmM = confirm.getItemMeta();
        confirmM.setDisplayName(ChatColor.GREEN + "Confirm Removal");
        confirm.setItemMeta(confirmM);

        ItemStack cancel = new ItemStack(Material.RED_WOOL, 1);
        ItemMeta cancelM = cancel.getItemMeta();
        cancelM.setDisplayName(ChatColor.RED + "Cancel");
        cancel.setItemMeta(cancelM);

        menu.setItem(4, block);
        menu.setItem(12, confirm);
        menu.setItem(14, cancel);

        p.openInventory(menu);

    }

}
