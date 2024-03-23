package com.lith.tpa;

import java.util.logging.Logger;

public class Static {
    public static final String pluginName = "TPA";
    public static final Logger log = Logger.getLogger(Static.pluginName);

    final public static class ConfigKeys {
        public static final class Messages {
            public static final String SECTION = "messages";
            public static final String TPA_USAGE = "tpa_usage";
            public static final String NO_TPA_SELF = "no_tpa_self";
            public static final String PLAYER_NOT_FOUND = "player_not_found";
            public static final String PLAYER_NOT_ONLINE = "player_not_online";
        }
    }

    final public static class Command {
        final public static class Names {
            public static final String TPA = "tpa";
        }

        final public static class PermissionKeys {
            public static final String PREFIX = "tpa";
            public static final String TPA = PermissionKeys.PREFIX + "tpa";
        }
    }

    final public static class Default {
        final public static class Command {
            public static final String TPA_USAGE = "Usage: /tpa <player>";
            public static final String NO_TPA_SELF = "Can't teleport to yourself!";
            public static final String PLAYER_NOT_FOUND = "Player %player% not found!";
            public static final String PLAYER_NOT_ONLINE = "Player %player% is not online!";
        }
    }
}
