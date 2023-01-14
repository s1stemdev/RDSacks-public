package org.systemdev.mc.rdsacks.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.systemdev.mc.rdsacks.Config.Configuration;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender.hasPermission("rdsacks.reload"))) {
            sender.sendMessage(Configuration.getString("messages.noPermission"));
            return true;
        }
        else {
            Configuration.reloadConfig();
            sender.sendMessage(Configuration.getString("messages.configReloaded"));
            return true;
        }
    }
}
