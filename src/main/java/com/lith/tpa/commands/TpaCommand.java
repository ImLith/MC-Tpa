package com.lith.tpa.commands;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.lith.lithcore.abstractClasses.AbstractCommand;
import com.lith.lithcore.utils.PlayerUtil;
import com.lith.tpa.Plugin;
import com.lith.tpa.Static;
import com.lith.tpa.classes.TpaStore;
import com.lith.tpa.config.ConfigManager;
import static net.kyori.adventure.text.Component.join;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.event.HoverEvent.showText;
import static net.kyori.adventure.text.event.ClickEvent.runCommand;

final public class TpaCommand extends AbstractCommand<Plugin> {
    protected final String permission = Static.Command.PermissionKeys.TPA;

    public TpaCommand() {
        super((Plugin) Plugin.plugin, Static.Command.Names.TPA, 1);
        Plugin.plugin.getCommand(Static.Command.Names.TPA).setExecutor(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onExecute(CommandSender sender, Command command, String label, String[] args) {
        String playerName = args[0];
        Player player = (Player) sender;

        if (sender.getName().equalsIgnoreCase(playerName)) {
            sender.sendMessage(ConfigManager.messages.noTpaSelf);
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(playerName);

        if (targetPlayer == null) {
            sender.sendMessage(ConfigManager.messages.playerNotFound.replace(Static.MessageKey.player, playerName));
            return true;
        }

        if (!targetPlayer.isOnline()) {
            sender.sendMessage(ConfigManager.messages.playerNotOnline.replace(Static.MessageKey.player, playerName));
            return true;
        }

        String targetName = targetPlayer.getName();
        String[] parts = ConfigManager.messages.acceptTpa.replace(Static.MessageKey.player, sender.getName())
                .split(Static.MessageKey.accept_btn);

        TpaStore.storeRequest(player.getUniqueId(), targetPlayer.getUniqueId(), 120L);
        player.sendMessage(ConfigManager.messages.requestSent.replace(Static.MessageKey.player, targetName));
        targetPlayer.sendMessage(join(
                text(parts[0]),
                text(ConfigManager.messages.acceptBtnText)
                        .hoverEvent(showText(text(ConfigManager.messages.acceptBtnHoverText
                                .replace(Static.MessageKey.player, targetName))))
                        .clickEvent(runCommand("/" + Static.Command.Names.TPACCEPT + " " + targetName)),
                text(parts[1])));
        targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, SoundCategory.MASTER, 1.0f,
                1.0f);

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
        return ConfigManager.messages.tpaUsage;
    }

}
