package com.lith.tpa.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.lith.lithcore.abstractClasses.AbstractCommand;
import com.lith.lithcore.utils.PlayerUtil;
import com.lith.tpa.Plugin;
import com.lith.tpa.Static;

final public class TpaCommand extends AbstractCommand<Plugin> {
    protected final String permission = Static.Command.PermissionKeys.TPA;

    public TpaCommand() {
        super((Plugin) Plugin.plugin, Static.Command.Names.TPA, 1);
        Plugin.plugin.getCommand(Static.Command.Names.TPA).setExecutor(this);
    }

    @Override
    public boolean onExecute(CommandSender sender, Command command, String label, String[] args) {
        String playerName = args[0];

        if (sender.getName().equalsIgnoreCase(playerName)) {
            sender.sendMessage(Plugin.plugin.cm.messages.noTpaSelf);
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(playerName);

        if (targetPlayer == null) {
            sender.sendMessage(Plugin.plugin.cm.messages.playerNotFound);
            return true;
        }

        if (!targetPlayer.isOnline()) {
            sender.sendMessage(Plugin.plugin.cm.messages.playerNotOnline);
            return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        int argsIndex = args.length;

        switch (argsIndex) {
            case 1:
                return PlayerUtil.getOnlinePlayerNames(List.of(sender.getName()));
            default:
                return null;
        }
    }

    @Override
    public String usage() {
        return Plugin.plugin.cm.messages.tpaUsage;
    }

}
