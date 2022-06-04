package com.ubiniti.noplace.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CConfig {

    private static File file;
    private static FileConfiguration config;

    public static void setup() {

        file = new File(Bukkit.getServer().getPluginManager().getPlugin("NoPlace").getDataFolder(), "data.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Something went wrong setting up the configuration file!");
            }
        }

        config = YamlConfiguration.loadConfiguration(file);

    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            System.err.println("Something went wrong on saving the config file!");
        }
    }

    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }


}
