package org.systemdev.mc.rdsacks.Config;
import org.bukkit.Material;

import java.util.List;

public class ItemConfig {

    public final String Name;

    public final Material Material;

    public final List<String> Lore;

    public final int CustomModelData;

    public ItemConfig(String name, Material material, List<String> lore, int customModelData) {
        this.Name = name;
        this.Material = material;
        this.Lore = lore;
        this.CustomModelData = customModelData;
    }
}
