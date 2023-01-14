package org.systemdev.mc.rdsacks;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.systemdev.mc.rdsacks.Config.Configuration;

import java.util.*;

public class Utilities {
    public static NamespacedKey sackTypeKey = new NamespacedKey(RDSacks.getInstance(), "sackType");

    public static void giveSack(String sackName, Player player) {
        ItemStack sack = new ItemStack(Material.valueOf(Configuration.getString("items." + sackName + ".material")));
        ItemMeta sackMeta = sack.getItemMeta();
        PersistentDataContainer container = sackMeta.getPersistentDataContainer();
        sackMeta.setDisplayName(Configuration.getString("items." + sackName + ".name"));
        List<String> sackMaterials = Configuration.GetSackPickupMaterials(sackName);
        for (String sackMaterial : sackMaterials) {
            NamespacedKey sackMaterialKey = new NamespacedKey(RDSacks.getInstance(), sackMaterial);
            container.set(sackMaterialKey, PersistentDataType.INTEGER, 0);
        }
        container.set(sackTypeKey, PersistentDataType.STRING, sackName);
        sackMeta.setLore(Configuration.GetSackLore("items." + sackName + ".lore"));
        sack.setItemMeta(sackMeta);
        player.getInventory().addItem(sack);
    }

    public static boolean findStringInList(List<String> list, String s) {
        ArrayList<String> arrayList = (ArrayList<String>) list;
        if(arrayList.contains(s)) {
            return true;
        }
        return false;
    }

    public static void addItemByMaterial(ItemStack item, String material) {
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        NamespacedKey materialKey = new NamespacedKey(RDSacks.getInstance(), material);
        Integer oldCount = container.get(materialKey, PersistentDataType.INTEGER);
        container.set(materialKey, PersistentDataType.INTEGER, oldCount + 1);
    }
}
