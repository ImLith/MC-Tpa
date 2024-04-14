package com.lith.tpa.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.lith.lithcore.abstractClasses.AbstractCommand;
import com.lith.lithcore.utils.PlayerUtil;
import com.lith.tpa.Plugin;
import com.lith.tpa.Static;
import com.lith.tpa.classes.TpaStore;
import net.kyori.adventure.text.TextComponent;
import static net.kyori.adventure.text.Component.join;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.event.HoverEvent.showText;
import static net.kyori.adventure.text.event.ClickEvent.runCommand;
import static org.bukkit.SoundCategory.MASTER;
import static org.bukkit.Sound.ENTITY_ARROW_HIT_PLAYER;
import static net.kyori.adventure.text.JoinConfiguration.noSeparators;

final public class TpaCommand extends AbstractCommand<Plugin> {
    public TpaCommand(Plugin plugin) {
        super((Plugin) plugin, Static.Command.Names.TPA, 1, Static.Command.PermissionKeys.TPA);
        plugin.getCommand(Static.Command.Names.TPA).setExecutor(this);
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
        UUID result = TpaStore.fetchRequest(playerUUID, targetUUID);

        if (result != null) {
            player.sendMessage(plugin.configs.messages.tpa.pending.replace(Static.MessageKey.player, targetName));
            return true;
        }

        TpaStore.storeRequest(playerUUID, targetUUID);

        String playerName = player.getName();
        String targetResponseMessage = plugin.configs.messages.tpa.recieved.replace(Static.MessageKey.player,
                playerName);
        Matcher matcher = Pattern.compile("(" + Static.MessageKey.accept_btn + "|" + Static.MessageKey.deny_btn + ")")
                .matcher(targetResponseMessage);
        ArrayList<TextComponent> targetResponse = new ArrayList<>();
        int previousEnd = 0;

        while (matcher.find()) {
            targetResponse.add(text(targetResponseMessage.substring(previousEnd, matcher.start())));

            String match = matcher.group();

            if (match.equals(Static.MessageKey.accept_btn))
                targetResponse.add(text(plugin.configs.messages.tpa.buttons.accept.text)
                        .hoverEvent(showText(
                                text(plugin.configs.messages.tpa.buttons.accept.hover
                                        .replace(Static.MessageKey.player, playerName))))
                        .clickEvent(runCommand("/" + Static.Command.Names.TPACCEPT + " " + playerName)));
            else if (match.equals(Static.MessageKey.deny_btn))
                targetResponse.add(text(plugin.configs.messages.tpa.buttons.deny.text)
                        .hoverEvent(showText(
                                text(plugin.configs.messages.tpa.buttons.deny.hover
                                        .replace(Static.MessageKey.player, playerName))))
                        .clickEvent(runCommand("/" + Static.Command.Names.TPDENY + " " + playerName)));

            previousEnd = matcher.end();
        }

        targetResponse.add(text(targetResponseMessage.substring(previousEnd)));

        player.sendMessage(plugin.configs.messages.tpa.sent.replace(Static.MessageKey.player, targetName));
        target.sendMessage(join(noSeparators(), targetResponse));
        target.playSound(target.getLocation(), ENTITY_ARROW_HIT_PLAYER, MASTER, 1.0f, 1.0f);

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
        return plugin.configs.messages.tpa.usage;
    }
}
