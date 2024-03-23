package com.lith.tpa.classes;

import java.util.UUID;
import com.lith.redis.classes.RedisDb;

public class TpaStore {
    public static final String PREFIX = "tpa.";
    public static final String REQUEST_PREFIX = PREFIX + "request.";

    public static void storeRequest(UUID sender, UUID target) {
        RedisDb.init().set(REQUEST_PREFIX + sender, target.toString(), 120L);
    }

    public static UUID fetchRequest(UUID sender) {
        String result = RedisDb.init().get(REQUEST_PREFIX + sender);

        return result == null ? null : UUID.fromString(result);
    }

    public static void deleteRequest(UUID tagret) {
        RedisDb.init().del(REQUEST_PREFIX + tagret);
    }
}
