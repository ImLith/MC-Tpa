package com.lith.tpa;

import com.lith.lithcore.abstractClasses.AbstractConfigKey;

public class Static {
    final public static class ConfigKeys {
        public static final class Messages extends AbstractConfigKey {
            public static final class Tpa extends AbstractConfigKey {
                public static final String USAGE = setKey("usage");
                public static final String SENT = setKey("sent");
                public static final String RECIEVED = setKey("recieved");
                public static final String PENDING = setKey("pending");

                public static final class Buttons extends AbstractConfigKey {
                    public static final class Accept extends AbstractConfigKey {
                        public static final String TEXT = setKey("text");
                        public static final String HOVER = setKey("hover");
                    }

                    public static final class Deny extends AbstractConfigKey {
                        public static final String TEXT = setKey("text");
                        public static final String HOVER = setKey("hover");
                    }
                }
            }

            public static final class Tpaccept extends AbstractConfigKey {
                public static final String USAGE = setKey("usage");
                public static final String ACCEPTED = setKey("accepted");
                public static final String TELEPORTED = setKey("teleported");
            }

            public static final class Tpdeny extends AbstractConfigKey {
                public static final String USAGE = setKey("usage");
                public static final String DENIED = setKey("denied");
                public static final String NOT_TELEPORTED = setKey("notteleported");
            }

            public static final class Errors extends AbstractConfigKey {
                public static final String SELF = setKey("self");
                public static final String OFFLINE = setKey("offline");
                public static final String NOTFOUND = setKey("notfound");
                public static final String EXPIRED = setKey("expired");
                public static final String TPTO = setKey("tpto");
                public static final String TPFROM = setKey("tpfrom");
            }
        }
    }

    final public static class Command {
        final public static class Names {
            public static final String TPA = "tpa";
            public static final String TPACCEPT = "tpaccept";
            public static final String TPDENY = "tpdeny";
        }

        final public static class PermissionKeys {
            public static final String PREFIX = "tpa";
            public static final String TPA = PermissionKeys.PREFIX + "tpa";
        }
    }

    final public static class MessageKey {
        public static final String player = "%player%";
        public static final String accept_btn = "%accept%";
        public static final String deny_btn = "%deny%";
    }

    final public static class StorageKeys {
        final public static class Tpa {
            public static final String PREFIX = "tpa.";
            public static final String REQUEST_PREFIX = PREFIX + "request.";
        }
    }
}
