package com.ubiniti.noplace.commands;

import com.ubiniti.noplace.Noplace;
import com.ubiniti.noplace.files.CConfig;
import com.ubiniti.noplace.utilities.listGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.isOp() || player.hasPermission("noplace.*")) {
                    player.sendMessage(Noplace.pre);
                    player.sendMessage("Usage: ");
                    player.sendMessage("/noplace add : to add the item in your current hand");
                    player.sendMessage("/noplace remove : remove the item in your current hand");
                    player.sendMessage("/noplace list : view the list of banned blocks");
                }
            }
        } else {
            if (args[0].equals("reload")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.isOp() || player.hasPermission("noplace.*") || player.hasPermission("noplace.place")) {
                        CConfig.reloadConfig();
                        player.sendMessage(Noplace.pre + "Reloaded plugin!");
                        System.out.println("[NoPlace]:");
                        System.out.println("Config file has been reloaded");
                    } else {
                        player.sendMessage(Noplace.insufficientPerm);
                    }
                }
            } else if (args[0].equals("add")) {

                if (sender instanceof Player) {

                    Player player = (Player) sender;
                    if (player.isOp() || player.hasPermission("noplace.*") || player.hasPermission("noplace.add")) {
                        String itemInHand = player.getInventory().getItemInMainHand().getType().name();

                        if (!itemInHand.equals("AIR") && player.getInventory().getItemInMainHand().getType().isBlock()) {

                            List<String> disabledBlocks = CConfig.getConfig().getStringList("DisabledBlocks");
                            if (!disabledBlocks.contains(itemInHand)) {
                                disabledBlocks.add(itemInHand);
                                player.sendMessage(Noplace.pre + "Added \"" + itemInHand + "\" to the list!");
                                CConfig.getConfig().set("DisabledBlocks", disabledBlocks);
                                CConfig.saveConfig();
                            } else {
                                player.sendMessage(Noplace.pre + "Block already blacklisted!");
                            }


                        } else {

                            player.sendMessage(Noplace.pre + "Please have a valid block in your hand!");

                        }
                    } else {
                        player.sendMessage(Noplace.insufficientPerm);
                    }

                }
            } else if (args[0].equals("remove")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if (player.isOp() || player.hasPermission("noplace.*") || player.hasPermission("noplace.remove")) {
                        String itemInHand = player.getInventory().getItemInMainHand().getType().name();

                        if (!itemInHand.equals("AIR")) {

                            List<String> disabledBlocks = CConfig.getConfig().getStringList("DisabledBlocks");
                            disabledBlocks.remove(itemInHand);

                            player.sendMessage(Noplace.pre + "Removed \"" + itemInHand + "\" from the list!");
                            CConfig.getConfig().set("DisabledBlocks", disabledBlocks);
                            CConfig.saveConfig();

                        } else {

                            player.sendMessage(Noplace.pre + "Please have a valid block in your hand!");

                        }
                    } else {
                        player.sendMessage(Noplace.insufficientPerm);
                    }


                }
            } else if (args[0].equals("list")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if (player.isOp() || player.hasPermission("noplace.*") || player.hasPermission("noplace.list")) {

                        listGUI.openList(player);

                    } else {
                        player.sendMessage(Noplace.insufficientPerm);
                    }

                }
            } else {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(Noplace.pre + "Invalid command usage!");
                }
            }
        }
        return true;
    }

}
