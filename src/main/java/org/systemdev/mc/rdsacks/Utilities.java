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

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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
}
