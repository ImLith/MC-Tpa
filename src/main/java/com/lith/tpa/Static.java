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
            public static final String REQUEST_SENT = "request_sent";
            public static final String ACCEPT_TPA = "accept_tpa";
        }

        public static final class AcceptBtn {
            public static final String SECTION = "accept_btn";
            public static final String TEXT = "text";
            public static final String HOVER_TEXT = "hover_text";
        }
    }

    final public static class Command {
        final public static class Names {
            public static final String TPA = "tpa";
            public static final String TPACCEPT = "tpaccept";
        }

        final public static class PermissionKeys {
            public static final String PREFIX = "tpa";
            public static final String TPA = PermissionKeys.PREFIX + "tpa";
        }
    }

    final public static class MessageKey {
        public static final String player = "%player%";
        public static final String accept_btn = "%accept%";
    }
}
