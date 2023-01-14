package org.systemdev.mc.rdsacks.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.systemdev.mc.rdsacks.Config.Configuration;
import org.systemdev.mc.rdsacks.Utilities;

import java.util.EventListener;

public class PickupEvent implements Listener {

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = ((Player) e.getEntity()).getPlayer();
            ItemStack offHandItem = p.getInventory().getItemInOffHand();
            p.sendMessage("Работает!");
            if (offHandItem.getItemMeta().getPersistentDataContainer().has(Utilities.sackTypeKey)) {
                p.sendMessage("1");
                PersistentDataContainer offHandItemContainer = offHandItem.getItemMeta().getPersistentDataContainer();
                String sackType = offHandItem.getItemMeta().getPersistentDataContainer().get(Utilities.sackTypeKey,PersistentDataType.STRING);
                p.sendMessage(String.valueOf(e.getItem().getItemStack().getType()));
                if(Utilities.findStringInList(Configuration.GetSackPickupMaterials(sackType), String.valueOf(e.getItem().getItemStack().getType()))) {
                    p.sendMessage("2");
                    e.getItem().getItemStack().setAmount(e.getItem().getItemStack().getAmount() - 1);
                }
            }
        }
    }
}
