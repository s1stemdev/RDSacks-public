package org.systemdev.mc.rdsacks;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.systemdev.mc.rdsacks.Config.Configuration;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Utilities {
    public static NamespacedKey sackTypeKey = new NamespacedKey(RDSacks.getInstance(), "sackType");
    public static NamespacedKey maxItems = new NamespacedKey(RDSacks.getInstance(), "maxItems");

    public static void giveSack(String sackName, Integer max, Player player) {
        ItemStack sack = new ItemStack(Material.valueOf(Configuration.getString("items." + sackName + ".material")));
        LeatherArmorMeta sackMeta = (LeatherArmorMeta) sack.getItemMeta();
        PersistentDataContainer container = sackMeta.getPersistentDataContainer();
        sackMeta.setDisplayName(Configuration.getString("items." + sackName + ".name"));
        sackMeta.setCustomModelData(Configuration.getInt("items." + sackName + ".custommodeldata"));
        sackMeta.setLore(Configuration.GetSackLore(sackName));
        int color = Integer.parseInt(Configuration.getString("items." + sackName + ".color"));
        sackMeta.setColor(Color.fromRGB(color));
        List<String> sackMaterials = Configuration.GetSackPickupMaterials(sackName);
        for (String sackMaterial : sackMaterials) {
            NamespacedKey sackMaterialKey = new NamespacedKey(RDSacks.getInstance(), sackMaterial);
            container.set(sackMaterialKey, PersistentDataType.INTEGER, 0);
        }
        container.set(sackTypeKey, PersistentDataType.STRING, sackName);
        container.set(maxItems, PersistentDataType.INTEGER, max);
        sack.setItemMeta(sackMeta);
        player.getInventory().addItem(sack);
    }

    public static boolean findStringInList(List<String> list, String s) {
        return list.contains(s);
    }

    public static void addItemByMaterial(ItemStack item, String material, Integer amount) {
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        NamespacedKey materialKey = new NamespacedKey(RDSacks.getInstance(), material);
        Integer oldCount = container.get(materialKey, PersistentDataType.INTEGER);
        container.set(materialKey, PersistentDataType.INTEGER, oldCount + amount);
        item.setItemMeta(itemMeta);
    }

}
