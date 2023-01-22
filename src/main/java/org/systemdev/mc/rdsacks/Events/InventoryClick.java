package org.systemdev.mc.rdsacks.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.systemdev.mc.rdsacks.InventoryUtils;
import org.systemdev.mc.rdsacks.RDSacks;
import org.systemdev.mc.rdsacks.Utilities;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        String pname = e.getWhoClicked().getName();
        Player p = Bukkit.getPlayer(pname);
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getItemMeta() == null) return;
        if (item.getItemMeta().getPersistentDataContainer().has(InventoryUtils.materialType)) {
            PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
            NamespacedKey sackMaterialKey = new NamespacedKey(RDSacks.getInstance(), String.valueOf(item.getType()));
            Integer amount = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(sackMaterialKey, PersistentDataType.INTEGER);
            e.setCancelled(true);
            if (amount != 0) {
                Inventory inv = e.getClickedInventory();
                ItemStack give = new ItemStack(item.getType(), amount);
                ItemStack menuItem = e.getCurrentItem();
                ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                ItemMeta menuMeta = menuItem.getItemMeta();
                meta.getPersistentDataContainer().set(sackMaterialKey, PersistentDataType.INTEGER, 0);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                menuMeta.setLore(InventoryUtils.setLoreAmount(e.getCurrentItem(), p.getInventory().getItemInMainHand()));
                menuItem.setItemMeta(menuMeta);
                inv.removeItem(e.getCurrentItem());
                inv.setItem(e.getSlot(), menuItem);
                p.getInventory().addItem(give);
            }
        }
        if (item.getItemMeta().getPersistentDataContainer().has(InventoryUtils.decoKey)) e.setCancelled(true);
    }
}
