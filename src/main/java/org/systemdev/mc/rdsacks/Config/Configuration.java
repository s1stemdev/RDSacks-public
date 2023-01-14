package org.systemdev.mc.rdsacks.Config;

import org.systemdev.mc.rdsacks.RDSacks;

import java.util.Collection;
import java.util.List;

public class Configuration {


    public static void reloadConfig() {
        RDSacks.getInstance().reloadConfig();
    }

    public static String getString(String path) {
        String s = RDSacks.getInstance().getConfig().getString(path);
        String formatedString = s.replaceAll("&", "§");
        return formatedString;
    }

    public static List<String> GetSacks() {
        return RDSacks.getInstance().getConfig().getStringList("init_sacks");
    }
    public static List<String> GetSackLore(String sackName) {
        return RDSacks.getInstance().getConfig().getStringList(sackName + ".lore");
    }
    public static List<String> GetSackPickupMaterials(String sackName) {
        return RDSacks.getInstance().getConfig().getStringList(sackName + ".pickupMaterials");
    }
}
