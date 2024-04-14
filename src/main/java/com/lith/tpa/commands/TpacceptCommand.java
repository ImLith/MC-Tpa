package com.lith.tpa.commands;

import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.lith.lithcore.abstractClasses.AbstractCommand;
import com.lith.lithcore.utils.PlayerUtil;
import com.lith.tpa.Plugin;
import com.lith.tpa.Static;
import com.lith.tpa.classes.TpaStore;

final public class TpacceptCommand extends AbstractCommand<Plugin> {
    public TpacceptCommand(Plugin plugin) {
        super((Plugin) plugin, Static.Commands.Name.TPACCEPT, 1, Static.Commands.Permission.TPA);
        plugin.getCommand(Static.Commands.Name.TPACCEPT).setExecutor(this);
    }

    @Override
    public boolean onExecute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(plugin.configs.messages.errors.notfound.replace(Static.MessageKey.player, args[0]));
            return true;
        }

        String targetName = target.getName();

        if (sender.getName().equalsIgnoreCase(targetName)) {
            sender.sendMessage(plugin.configs.messages.errors.self);
            return true;
        }

        if (!target.isOnline()) {
            sender.sendMessage(plugin.configs.messages.errors.offline.replace(Static.MessageKey.player, args[0]));
            return true;
        }

        UUID playerUUID = player.getUniqueId();
        UUID targetUUID = target.getUniqueId();
        UUID result = TpaStore.fetchRequest(targetUUID, playerUUID);

        if (result == null) {
            sender.sendMessage(plugin.configs.messages.errors.expired);
            return true;
        }

        TpaStore.deleteRequest(targetUUID, playerUUID);
        String playerName = player.getName();

        target.sendMessage(plugin.configs.messages.tpaccept.accepted.replace(Static.MessageKey.player, playerName));
        Boolean teleported = target.teleport(player.getLocation());

        if (teleported) {
            sender.sendMessage(
                    plugin.configs.messages.tpaccept.teleported.replace(Static.MessageKey.player, targetName));
        } else {
            sender.sendMessage(plugin.configs.messages.errors.tpto.replace(Static.MessageKey.player, targetName));
            target.sendMessage(plugin.configs.messages.errors.tpfrom.replace(Static.MessageKey.player, playerName));
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
        return plugin.configs.messages.tpaccept.usage;
    }
}
