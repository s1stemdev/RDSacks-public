package org.systemdev.mc.rdsacks.Events;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.systemdev.mc.rdsacks.InventoryUtils;
import org.systemdev.mc.rdsacks.RDSacks;
import org.systemdev.mc.rdsacks.Utilities;

public class OpenInventory implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(p.getInventory().getItemInMainHand().getType().isEmpty()) return;
        if(e.getAction().isRightClick()) {
            if (p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(Utilities.sackTypeKey))
                InventoryUtils.openSackMenu(p, p.getInventory().getItemInMainHand(), p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(Utilities.sackTypeKey, PersistentDataType.STRING));
        }
    }
}
