package com.ubiniti.noplace.listeners;

import com.ubiniti.noplace.Noplace;
import com.ubiniti.noplace.files.CConfig;
import com.ubiniti.noplace.utilities.listGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class listGUIListener implements Listener {

    public static Material clickedBlock;

    @EventHandler
    public void onListClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Blacklisted Blocks")) {

            if (e.getCurrentItem() != null) {
                List<String> disabledBlocks = CConfig.getConfig().getStringList("DisabledBlocks");
                if (disabledBlocks.contains(e.getCurrentItem().getType().name())) {
                    clickedBlock = e.getCurrentItem().getType();
                    listGUI.openConfirm(p);
                }

            }

            e.setCancelled(true);

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Confirm Removal")) {

            if (e.getCurrentItem() != null) {

                if (e.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {

                    List<String> disabledBlocks = CConfig.getConfig().getStringList("DisabledBlocks");
                    disabledBlocks.remove(clickedBlock.name());

                    CConfig.getConfig().set("DisabledBlocks", disabledBlocks);
                    CConfig.saveConfig();

                    p.sendMessage(Noplace.pre + "Removed " + clickedBlock.name() + " from the blacklist!");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.7F, 0.7F);

                    clickedBlock = null;
                    listGUI.openList(p);

                } else if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                    listGUI.openList(p);
                }

            }

            e.setCancelled(true);

        }

    }

}
