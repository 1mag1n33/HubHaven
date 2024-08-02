package com.github._1mag1n33dev.HubHaven.NMS.common.npc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NPCRegistry {

    private final Map<UUID, NPCMetaData> npcs = new HashMap<>();

    public void registerNPC(NPCMetaData npc) {
        npcs.put(npc.getId(), npc);
    }

    public NPCMetaData getNPC(UUID id) {
        return npcs.get(id);
    }

    public NPCMetaData removeNPC(UUID id) {
        return npcs.remove(id);
    }

    public void clear() {
        npcs.clear();
    }
}
