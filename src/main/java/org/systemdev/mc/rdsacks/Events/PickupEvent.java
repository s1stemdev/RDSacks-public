package org.systemdev.mc.rdsacks.Events;

import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.systemdev.mc.rdsacks.Utilities;

import java.util.EventListener;

public class PickupEvent implements Listener {

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = ((Player) e.getEntity()).getPlayer();
            ItemStack offHandItem = p.getInventory().getItemInOffHand();
            PersistentDataContainer offHandItemContainer = offHandItem.getItemMeta().getPersistentDataContainer();
            p.sendMessage("Работает!");
            if (offHandItemContainer.has(Utilities.sackTypeKey)) {
                p.sendMessage("Работает!");
            }
        }
    }
}
