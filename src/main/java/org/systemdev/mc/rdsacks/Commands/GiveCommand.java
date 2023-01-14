package org.systemdev.mc.rdsacks.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.systemdev.mc.rdsacks.Config.Configuration;
import org.systemdev.mc.rdsacks.RDSacks;
import org.systemdev.mc.rdsacks.Utilities;

import java.util.ArrayList;
import java.util.List;

public class GiveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Utilities.giveSack(args[0], RDSacks.getInstance().getServer().getPlayer(args[1]));
        return true;
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> ToReturn = new ArrayList<>();
        if (args.length == 1)
            if(command.getName().equals("sacks-give")) {
                ToReturn.addAll(Configuration.GetSacks());
            }
        return ToReturn;
    }

}
