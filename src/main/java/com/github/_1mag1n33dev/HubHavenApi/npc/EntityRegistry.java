package com.github._1mag1n33dev.HubHavenApi.npc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EntityRegistry {

    private static final Map<String, Class<? extends NPC>> registeredEntities = new HashMap<>();

    public static void registerEntity(String type, Class<? extends NPC> entityClass) {
        registeredEntities.put(type, entityClass);
    }

    public static Set<String> getTypes() {
        return registeredEntities.keySet();
    }

    public static Class<? extends NPC> getEntityClass(String type) {
        return registeredEntities.get(type);
    }
}
