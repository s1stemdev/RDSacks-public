package org.systemdev.mc.rdsacks.Config;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.systemdev.mc.rdsacks.RDSacks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Configuration {

    public static void reloadConfig() {
        RDSacks.getInstance().reloadConfig();
    }

    public static String getString(String path) {
        String s = RDSacks.getInstance().getConfig().getString(path);
        String formatedString = s.replaceAll("&", "§");
        return formatedString;
    }


    public static Integer getInt(String path) { return RDSacks.getInstance().getConfig().getInt(path); }
    public static List<String> GetSacks() { return GetStrList("init_sacks"); }
    public static List<String> GetSackLore(String sackName) { return GetStrList("items." + sackName + ".lore");}
    public static List<String> GetDecoItems() { return GetStrList("sack_menu.inventory");}
    public static List<String> GetStrList(String path) {
        List<String> list = RDSacks.getInstance().getConfig().getStringList(path);
        List<String> result = replace(list, "&", "§");
        return result;
    }
    public static List<String> GetSackPickupMaterials(String sackName) { return GetStrList("items." + sackName + ".pickupMaterials"); }

    private static ItemConfig GetDecoItem(char item) {
        return new ItemConfig(getString("sack_menu.decoration." + item + ".name"),
                Material.getMaterial(getString("sack_menu.decoration." + item + ".material")
                ), GetStrList("sack_menu.decoration." + item + ".lore"), getInt("sack_menu.decoration." + item + ".custommodeldata"));
    }

    public static ItemConfig GetDecoration(char n) {
        return GetDecoItem(n);
    }

    public static List<String> replace(Iterable<String> itrb, String from, String to) {
        return StreamSupport.stream(itrb.spliterator(), false)
                .map(line->line.replace(from, to))
                .collect(Collectors.toList());
    }

}
