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
import static com.lith.tpa.config.ConfigManager.messages;

final public class TpacceptCommand extends AbstractCommand<Plugin> {
    protected final String permission = Static.Command.PermissionKeys.TPA;

    public TpacceptCommand() {
        super((Plugin) Plugin.plugin, Static.Command.Names.TPACCEPT, 1);
        Plugin.plugin.getCommand(Static.Command.Names.TPACCEPT).setExecutor(this);
    }

    @Override
    public boolean onExecute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (sender.getName().equalsIgnoreCase(args[0])) {
            sender.sendMessage(messages.errors.self);
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(messages.errors.notfound.replace(Static.MessageKey.player, args[0]));
            return true;
        }

        if (!target.isOnline()) {
            sender.sendMessage(messages.errors.offline.replace(Static.MessageKey.player, args[0]));
            return true;
        }

        UUID targetUUID = target.getUniqueId();
        UUID result = TpaStore.fetchRequest(targetUUID);

        if (result == null) {
            sender.sendMessage(messages.errors.expired);
            return true;
        }

        TpaStore.deleteRequest(targetUUID);
        String playerName = player.getName();
        String targetName = target.getName();

        target.sendMessage(messages.tpaccept.accepted.replace(Static.MessageKey.player, playerName));
        Boolean teleported = target.teleport(player.getLocation());

        if (teleported) {
            sender.sendMessage(messages.tpaccept.teleported.replace(Static.MessageKey.player, targetName));
        } else {
            sender.sendMessage(messages.errors.tpto.replace(Static.MessageKey.player, targetName));
            target.sendMessage(messages.errors.tpfrom.replace(Static.MessageKey.player, playerName));
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
        return messages.tpaccept.usage;
    }

}
