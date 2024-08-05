package com.github._1mag1n33dev.HubHaven.NMS.V1_8.npc;


import com.github._1mag1n33dev.HubHavenApi.npc.EntityRegistry;
import com.github._1mag1n33dev.HubHavenApi.npc.NPC;
import com.github._1mag1n33dev.HubHavenApi.npc.NPCManager;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.WorldServer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NPCManagerV1_8 implements NPCManager {

    private final Map<UUID, NPC> npcInstances = new HashMap<>();

    @Override
    public NPC createNPC(String type, MinecraftServer server, WorldServer world) {
        Class<? extends NPC> entityClass = EntityRegistry.getEntityClass(type);
        if (entityClass == null) {
            return null;
        }

        try {
            NPC npc = entityClass.getConstructor(MinecraftServer.class, WorldServer.class).newInstance(server, world);
            UUID npcId = UUID.randomUUID();
            npc.setNPCId(npcId);
            npcInstances.put(npcId, npc);
            return npc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    @Override
    public void removeNPC(NPC npc) {
        npc.despawn();
    }

    @Override
    public void addClickAction(NPC npc, Runnable action) {
    }


    public NPC getNpcById(UUID id) {
        return npcInstances.get(id);
    }

    public void removeNpc(UUID id) {
        npcInstances.remove(id);
    }
}
