package com.lith.tpa.config;

import com.lith.lithcore.abstractClasses.MainPlugin;
import com.lith.lithcore.abstractClasses.PluginConfigManager;
import com.lith.tpa.Static;

public class ConfigManager extends PluginConfigManager {
        public static ConfigMessages messages;

        public ConfigManager(final MainPlugin<ConfigManager> plugin) {
                super(plugin);

                messages = new ConfigMessages();
        }

        public class ConfigMessages {
                public final String tpaUsage = config.getString(
                                Static.ConfigKeys.Messages.SECTION + "." + Static.ConfigKeys.Messages.TPA_USAGE,
                                Static.Default.Command.TPA_USAGE);
                public final String noTpaSelf = config.getString(
                                Static.ConfigKeys.Messages.SECTION + "." + Static.ConfigKeys.Messages.NO_TPA_SELF,
                                Static.Default.Command.NO_TPA_SELF);
                public final String playerNotFound = config.getString(
                                Static.ConfigKeys.Messages.SECTION + "." + Static.ConfigKeys.Messages.PLAYER_NOT_FOUND,
                                Static.Default.Command.PLAYER_NOT_FOUND);
                public final String playerNotOnline = config.getString(
                                Static.ConfigKeys.Messages.SECTION + "." + Static.ConfigKeys.Messages.PLAYER_NOT_ONLINE,
                                Static.Default.Command.PLAYER_NOT_ONLINE);
                public final String requestSent = config.getString(
                                Static.ConfigKeys.Messages.SECTION + "." + Static.ConfigKeys.Messages.REQUEST_SENT,
                                Static.Default.Command.REQUEST_SENT);
                public final String acceptTpa = config.getString(
                                Static.ConfigKeys.Messages.SECTION + "." + Static.ConfigKeys.Messages.ACCEPT_TPA,
                                Static.Default.Command.ACCEPT_TPA);
                public final String acceptBtnHoverText = config.getString(
                                Static.ConfigKeys.AcceptBtn.SECTION + "." + Static.ConfigKeys.AcceptBtn.HOVER_TEXT,
                                Static.Default.AcceptBtn.HOVER_TEXT);
                public final String acceptBtnText = config.getString(
                                Static.ConfigKeys.AcceptBtn.SECTION + "." + Static.ConfigKeys.AcceptBtn.TEXT,
                                Static.Default.AcceptBtn.TEXT);
        }
}
