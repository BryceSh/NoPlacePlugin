package com.ubiniti.noplace;

import com.ubiniti.noplace.commands.MainCommand;
import com.ubiniti.noplace.files.CConfig;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Noplace extends JavaPlugin {

    public static final String pre = ChatColor.RED + "[" + ChatColor.BLUE + "NoPlace" + ChatColor.RED + "] " + ChatColor.WHITE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[NoPlace]:  Loading Plugin");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CConfig.setup();
        List<String> list = new ArrayList<>();
        list.add("BEDROCK");
        list.add("BARRIER");
        CConfig.getConfig().addDefault("DisabledBlocks", list);
        CConfig.getConfig().options().copyDefaults(true);
        CConfig.saveConfig();

        getCommand("noplace").setExecutor(new MainCommand());

        getServer().getPluginManager().registerEvents(new BlockPlaced(), this);

        System.out.println("[NoPlace]:  Plugin loaded successfully!");
    }


}
