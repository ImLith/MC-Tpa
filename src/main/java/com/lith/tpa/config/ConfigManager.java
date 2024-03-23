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
                public final String requestExpired = getCommand(ConfigKeys.Messages.REQUEST_EXPIRED);
                public final String tpacceptUsage = getCommand(ConfigKeys.Messages.TPACCEPT_USAGE);
                public final String acceptedRequest = getCommand(ConfigKeys.Messages.ACCEPTED_REQUEST);
                public final String onTeleport = getCommand(ConfigKeys.Messages.ON_TELEPORT);
                public final String failedTeleport = getCommand(ConfigKeys.Messages.FAILED_TELEPORT);
                public final String failedTeleportation = getCommand(ConfigKeys.Messages.FAILED_TELEPORATION);

                private String getCommand(String key) {
                        return get(ConfigKeys.Messages.SECTION, key);
                }

                private String getAcceptBtn(String key) {
                        return get(ConfigKeys.AcceptBtn.SECTION, key);
                }

                private String get(String prefix, String key) {
                        return ChatColor.translateAlternateColorCodes('&',
                                        unescapeJava(config.getString(prefix + "." + key)));
                }

                private String unescapeJava(String input) {
                        StringBuilder builder = new StringBuilder();
                        int i = 0;

                        while (i < input.length()) {
                                char currentChar = input.charAt(i);
                                if (currentChar == '\\' && i + 1 < input.length() && input.charAt(i + 1) == 'u') {
                                        try {
                                                builder.append((char) Integer
                                                                .parseInt(input.substring(i + 2, i + 6), 16));
                                                i += 6;
                                        } catch (NumberFormatException e) {
                                                builder.append(currentChar);
                                                i++;
                                        }
                                } else {
                                        builder.append(currentChar);
                                        i++;
                                }
                        }

                        return builder.toString();
                }
        }
}
