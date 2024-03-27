package com.lith.tpa.classes;

import java.util.UUID;
import com.lith.redis.classes.RedisDb;

public class TpaStore {
    public static final String PREFIX = "tpa.";
    public static final String REQUEST_PREFIX = PREFIX + "request.";

    public static void storeRequest(UUID sender, UUID target) {
        RedisDb.init().set(genKey(sender, target), target.toString(), 120L);
    }

    public static UUID fetchRequest(UUID sender, UUID target) {
        String result = RedisDb.init().get(genKey(sender, target));

        return result == null ? null : UUID.fromString(result);
    }

    public static void deleteRequest(UUID sender, UUID target) {
        RedisDb.init().del(genKey(sender, target));
    }

    private static String genKey(UUID sender, UUID target) {
        return REQUEST_PREFIX + sender + "." + target;
    }
}
