package com.lith.tpa.config;

import com.lith.lithcore.abstractClasses.AbstractConfigManager;
import com.lith.tpa.Plugin;
import com.lith.tpa.Static.ConfigKeys;

public class ConfigManager extends AbstractConfigManager<Plugin, ConfigManager> {
    public ConfigMessages messages;

    public ConfigManager(final Plugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {
        super.load();
        messages = new ConfigMessages();
    }

    public final class ConfigMessages {
        public Tpa tpa = new Tpa();
        public Tpaccept tpaccept = new Tpaccept();
        public Tpdeny tpdeny = new Tpdeny();
        public Errors errors = new Errors();

        public class Tpa {
            public Buttons buttons = new Buttons();

            public final String usage = getMessage(ConfigKeys.Messages.Tpa.USAGE);
            public final String sent = getMessage(ConfigKeys.Messages.Tpa.SENT);
            public final String recieved = getMessage(ConfigKeys.Messages.Tpa.RECIEVED);
            public final String pending = getMessage(ConfigKeys.Messages.Tpa.PENDING);

            public class Buttons {
                public Accept accept = new Accept();
                public Deny deny = new Deny();

                public class Accept {
                    public final String text = getMessage(
                            ConfigKeys.Messages.Tpa.Buttons.Accept.TEXT);
                    public final String hover = getMessage(
                            ConfigKeys.Messages.Tpa.Buttons.Accept.HOVER);
                }

                public class Deny {
                    public final String text = getMessage(
                            ConfigKeys.Messages.Tpa.Buttons.Deny.TEXT);
                    public final String hover = getMessage(
                            ConfigKeys.Messages.Tpa.Buttons.Deny.HOVER);
                }
            }
        }

        public class Tpaccept {
            public final String usage = getMessage(ConfigKeys.Messages.Tpaccept.USAGE);
            public final String accepted = getMessage(ConfigKeys.Messages.Tpaccept.ACCEPTED);
            public final String teleported = getMessage(ConfigKeys.Messages.Tpaccept.TELEPORTED);
        }

        public class Tpdeny {
            public final String usage = getMessage(ConfigKeys.Messages.Tpdeny.USAGE);
            public final String denied = getMessage(ConfigKeys.Messages.Tpdeny.DENIED);
            public final String notTeleported = getMessage(ConfigKeys.Messages.Tpdeny.NOT_TELEPORTED);
        }

        public class Errors {
            public final String self = getMessage(ConfigKeys.Messages.Errors.SELF);
            public final String offline = getMessage(ConfigKeys.Messages.Errors.OFFLINE);
            public final String notfound = getMessage(ConfigKeys.Messages.Errors.NOTFOUND);
            public final String expired = getMessage(ConfigKeys.Messages.Errors.EXPIRED);
            public final String tpto = getMessage(ConfigKeys.Messages.Errors.TPTO);
            public final String tpfrom = getMessage(ConfigKeys.Messages.Errors.TPFROM);
        }
    }
}
