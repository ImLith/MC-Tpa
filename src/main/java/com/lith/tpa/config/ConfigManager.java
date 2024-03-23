package com.lith.tpa.config;

import com.lith.lithcore.abstractClasses.MainPlugin;
import com.lith.lithcore.abstractClasses.PluginConfigManager;
import com.lith.tpa.Static;
import net.md_5.bungee.api.ChatColor;

public class ConfigManager extends PluginConfigManager {
        public static ConfigMessages messages;

        public ConfigManager(final MainPlugin<ConfigManager> plugin) {
                super(plugin);

                messages = new ConfigMessages();
        }

        public class ConfigMessages {
                public final String tpaUsage = getCommand(
                                Static.ConfigKeys.Messages.TPA_USAGE,
                                Static.Default.Command.TPA_USAGE);
                public final String noTpaSelf = getCommand(
                                Static.ConfigKeys.Messages.NO_TPA_SELF,
                                Static.Default.Command.NO_TPA_SELF);
                public final String playerNotFound = getCommand(
                                Static.ConfigKeys.Messages.PLAYER_NOT_FOUND,
                                Static.Default.Command.PLAYER_NOT_FOUND);
                public final String playerNotOnline = getCommand(
                                Static.ConfigKeys.Messages.PLAYER_NOT_ONLINE,
                                Static.Default.Command.PLAYER_NOT_ONLINE);
                public final String requestSent = getCommand(
                                Static.ConfigKeys.Messages.REQUEST_SENT,
                                Static.Default.Command.REQUEST_SENT);
                public final String acceptTpa = getCommand(
                                Static.ConfigKeys.Messages.ACCEPT_TPA,
                                Static.Default.Command.ACCEPT_TPA);
                public final String acceptBtnHoverText = getAcceptBtn(
                                Static.ConfigKeys.AcceptBtn.HOVER_TEXT,
                                Static.Default.AcceptBtn.HOVER_TEXT);
                public final String acceptBtnText = getAcceptBtn(
                                Static.ConfigKeys.AcceptBtn.TEXT,
                                Static.Default.AcceptBtn.TEXT);

                private String getCommand(String key, String defaultValue) {
                        return get(Static.ConfigKeys.Messages.SECTION, key, defaultValue);
                }

                private String getAcceptBtn(String key, String defaultValue) {
                        return get(Static.ConfigKeys.AcceptBtn.SECTION, key, defaultValue);
                }

                private String get(String prefix, String key, String defaultValue) {
                        return ChatColor.translateAlternateColorCodes('&',
                                        config.getString(prefix + "." + key, defaultValue));
                }
        }
}
