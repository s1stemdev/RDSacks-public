package org.systemdev.mc.rdsacks.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.systemdev.mc.rdsacks.Config.Configuration;
import org.systemdev.mc.rdsacks.RDSacks;
import org.systemdev.mc.rdsacks.Utilities;

import java.util.EventListener;

public class PickupEvent implements Listener {

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player)) return;
        Player player = ((Player) e.getEntity());
        ItemStack sack = player.getInventory().getItemInOffHand();
        if(sack.getType().isEmpty() || sack.getItemMeta() == null) return;
        PersistentDataContainer container = sack.getItemMeta().getPersistentDataContainer();
        if(!(container.has(Utilities.sackTypeKey))) return;
        String sackType = container.get(Utilities.sackTypeKey, PersistentDataType.STRING);
        RDSacks.getInstance().log.info(sackType);
        if(Utilities.findStringInList(Configuration.GetSackPickupMaterials(sackType), String.valueOf(e.getItem().getItemStack().getType()))) {
            player.sendMessage("1");
            e.getItem().getItemStack().setAmount(0);
        }
    }
}
