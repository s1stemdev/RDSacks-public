package org.systemdev.mc.rdsacks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.systemdev.mc.rdsacks.Config.Configuration;
import org.systemdev.mc.rdsacks.Config.ItemConfig;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {

    public static NamespacedKey materialType = new NamespacedKey(RDSacks.getInstance(), "materialType");
    public static NamespacedKey decoKey = new NamespacedKey(RDSacks.getInstance(), "decoKey");

    public static void openSackMenu(Player player, ItemStack sackItem, String sack) {
        StringBuilder builder = new StringBuilder();
        for (String all : Configuration.GetStrList("sack_menu.inventory")) {
            builder.append(all);
        }
        String rawInv = builder.toString();
        Holder holder = new Holder();
        Inventory inv = Bukkit.createInventory((InventoryHolder) player, rawInv.length(), Configuration.getString("items." + sack + ".name"));
        List<ItemStack> materialItems = new ArrayList<>();
        for (String material : Configuration.GetSackPickupMaterials(sack)) {
            ItemStack itemStack = new ItemStack(Material.valueOf(material), 1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("1");
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            container.set(materialType, PersistentDataType.STRING, material);
            itemMeta.setLore(setLoreAmount(itemStack, sackItem));
            itemStack.setItemMeta(itemMeta);
            materialItems.add(itemStack);
        }
        int CurrentItem = 0;
        for (int I = 0; I < inv.getSize(); I++) {
            if (rawInv.charAt(I) == ' ') {
                if (CurrentItem < materialItems.size()) {
                    inv.setItem(I, materialItems.get(CurrentItem));
                    CurrentItem++;
                }
            } else {
                ItemConfig Decor = Configuration.GetDecoration(rawInv.charAt(I));
                ItemStack decoItem = new ItemStack(Decor.Material);
                ItemMeta decoItemMeta = decoItem.getItemMeta();
                decoItemMeta.setDisplayName(Decor.Name);
                decoItemMeta.setLore(Decor.Lore);
                decoItemMeta.setCustomModelData(Decor.CustomModelData);
                decoItemMeta.getPersistentDataContainer().set(decoKey, PersistentDataType.INTEGER, 1);
                decoItem.setItemMeta(decoItemMeta);

                inv.setItem(I, decoItem);
            }
        } player.openInventory(inv); }

    public static List<String> setLoreAmount(ItemStack item, ItemStack sackItem) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = sackItem.getItemMeta().getPersistentDataContainer();
        List<String> lore = Configuration.GetStrList("sack_menu.material_item.lore");
        for (int I = 0; I < lore.size(); I++) {
            NamespacedKey key = new NamespacedKey(RDSacks.getInstance(), String.valueOf(item.getType()));
            lore.set(I, lore.get(I).replace("{count}", String.valueOf(container.get(key, PersistentDataType.INTEGER))));
            lore.set(I, lore.get(I).replace("{max}", String.valueOf(container.get(Utilities.maxItems, PersistentDataType.INTEGER))));
        }
        return lore;
    }
}
