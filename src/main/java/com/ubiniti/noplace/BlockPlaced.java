package com.ubiniti.noplace;

import com.ubiniti.noplace.files.CConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class BlockPlaced implements Listener {

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e) {

        Block placedBlock = e.getBlock();
        Player player = e.getPlayer();

        List<String> disabledBlocks = CConfig.getConfig().getStringList("DisabledBlocks");

        if (disabledBlocks.contains(placedBlock.getType().name())) {
            if (!player.isOp()) {
                if (!player.hasPermission("noplace.*")) {
                    if (!player.hasPermission("noplace.place")) {
                        e.setCancelled(true);
                        player.sendMessage(Noplace.pre + ChatColor.RED + "This block has been disabled!");
                    }
                }
            }
        }



    }

}
