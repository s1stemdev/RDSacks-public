package org.systemdev.mc.rdsacks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.systemdev.mc.rdsacks.Config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {

    public static void openSackMenu(Player player, String sack) {
        StringBuilder builder = new StringBuilder();
        for (String all : Configuration.GetDecoItems()) {
            builder.append(all);
        }
        String rawInv = builder.toString();
        Inventory inv = Bukkit.createInventory((InventoryHolder) player, rawInv.length(), Configuration.getString(sack));
        List<ItemStack> materialItems = new ArrayList<>();
        for(String material : Configuration.GetSackPickupMaterials(sack)) {
            ItemStack itemStack = new ItemStack(Material.valueOf(material), 1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(Configuration.GetStrList("sack_menu.material_item.lore"));
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
                for(String key : RDSacks.getInstance().getConfig().getConfigurationSection("sack_menu.decoration").getKeys(false)) {
                    String name = Configuration.getString("sack_menu.decoration." + key + ".name");
                    Material material = Material.valueOf(Configuration.getString("sack_menu.decoration." + key + ".material"));
                    List<String> lore = Configuration.GetStrList("sack_menu.decoration." + key + ".lore");
                    Integer cmd = Configuration.getInt("sack_menu.decoration." + key + ".custommodeldata");
                    ItemStack deco = new ItemStack(material);
                    ItemMeta decoMeta = deco.getItemMeta();
                    decoMeta.setDisplayName(name);
                    decoMeta.setLore(lore);
                    decoMeta.setCustomModelData(cmd);
                    deco.setItemMeta(decoMeta);
                    inv.setItem(I, deco);
                }
            }
        }
        player.openInventory(inv);
    }

}
