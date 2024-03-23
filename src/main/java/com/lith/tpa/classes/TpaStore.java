package com.lith.tpa.classes;

import java.util.UUID;
import com.lith.redis.classes.RedisDb;

public class TpaStore {
    public static final String PREFIX = "tpa.";
    public static final String REQUEST_PREFIX = PREFIX + "request.";

    public static void storeRequest(UUID sender, UUID target, long expiration) {
        store(REQUEST_PREFIX, sender, target, expiration);
    }

    public static UUID fetchRequest(UUID sender) {
        return fetch(REQUEST_PREFIX, sender);
    }

    private static void store(String prefix, UUID sender, UUID target, long expiration) {
        RedisDb.init().set(TpaStore.REQUEST_PREFIX + sender, target.toString(), expiration);
    }

    private static UUID fetch(String prefix, UUID sender) {
        String result = RedisDb.init().get(TpaStore.REQUEST_PREFIX + sender);

        return result == null ? null : UUID.fromString(result);
    }

}
