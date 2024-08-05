package com.github._1mag1n33dev.HubHavenApi.npc;

import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.WorldServer;

public interface NPCManager {
    NPC createNPC(String type, MinecraftServer server, WorldServer world);
    void removeNPC(NPC npc);
    void addClickAction(NPC npc, Runnable action);
}
