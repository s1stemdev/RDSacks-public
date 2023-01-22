package org.systemdev.mc.rdsacks.Events;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.systemdev.mc.rdsacks.Config.Configuration;
import org.systemdev.mc.rdsacks.RDSacks;
import org.systemdev.mc.rdsacks.Utilities;

public class PickupEvent implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        ItemStack sack = player.getInventory().getItemInOffHand();
        if(sack.getType().isEmpty() || sack.getItemMeta() == null) return;
        PersistentDataContainer container = sack.getItemMeta().getPersistentDataContainer();
        if(!(container.has(Utilities.sackTypeKey))) return;
        String sackType = container.get(Utilities.sackTypeKey, PersistentDataType.STRING);
        Integer maxAmount = container.get(Utilities.maxItems, PersistentDataType.INTEGER);
        NamespacedKey currentKey = new NamespacedKey(RDSacks.getInstance(), String.valueOf(e.getItem().getItemStack().getType()));
        Integer currentAmount = container.get(currentKey, PersistentDataType.INTEGER);
        if(Utilities.findStringInList(Configuration.GetSackPickupMaterials(sackType), String.valueOf(e.getItem().getItemStack().getType()))) {
            if((currentAmount + e.getItem().getItemStack().getAmount()) <= maxAmount) {
                Utilities.addItemByMaterial(sack, String.valueOf(e.getItem().getItemStack().getType()), e.getItem().getItemStack().getAmount());
                player.sendMessage("1" + e.getItem().getItemStack().getType() + e.getItem().getItemStack().getAmount());
                e.setCancelled(true);
                e.getItem().remove();
            }
            else {
                e.setCancelled(true);
                Integer toSack = maxAmount - currentAmount;
                Integer toInv = e.getItem().getItemStack().getAmount() - toSack;
                Utilities.addItemByMaterial(sack, String.valueOf(e.getItem().getItemStack().getType()), toSack);
                ItemStack onGround = e.getItem().getItemStack();
                onGround.setAmount(toInv);
                e.getItem().setItemStack(onGround);
                e.setCancelled(false);
            }
        }
    }
}
