package com.lith.tpa.config;

import com.lith.lithcore.abstractClasses.MainPlugin;
import com.lith.lithcore.abstractClasses.PluginConfigManager;
import com.lith.tpa.Static.ConfigKeys;
import net.md_5.bungee.api.ChatColor;

public class ConfigManager extends PluginConfigManager {
        public static ConfigMessages messages;

        public ConfigManager(final MainPlugin<ConfigManager> plugin) {
                super(plugin);

                messages = new ConfigMessages();
        }

        public class ConfigMessages {
                public final String tpaUsage = getCommand(ConfigKeys.Messages.TPA_USAGE);
                public final String noTpaSelf = getCommand(ConfigKeys.Messages.NO_TPA_SELF);
                public final String playerNotFound = getCommand(ConfigKeys.Messages.PLAYER_NOT_FOUND);
                public final String playerNotOnline = getCommand(ConfigKeys.Messages.PLAYER_NOT_ONLINE);
                public final String requestSent = getCommand(ConfigKeys.Messages.REQUEST_SENT);
                public final String acceptTpa = getCommand(ConfigKeys.Messages.ACCEPT_TPA);
                public final String acceptBtnHoverText = getAcceptBtn(ConfigKeys.AcceptBtn.HOVER_TEXT);
                public final String acceptBtnText = getAcceptBtn(ConfigKeys.AcceptBtn.TEXT);

                private String getCommand(String key) {
                        return get(ConfigKeys.Messages.SECTION, key);
                }

                private String getAcceptBtn(String key) {
                        return get(ConfigKeys.AcceptBtn.SECTION, key);
                }

                private String get(String prefix, String key) {
                        return ChatColor.translateAlternateColorCodes('&', config.getString(prefix + "." + key));
                }
        }
}
