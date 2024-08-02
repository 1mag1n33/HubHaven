package com.github._1mag1n33dev.HubHaven.NMS.common;

import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPCManager;

public abstract class AbstractPacketManager implements PacketManager{
    private NPCManager npcManager;

    public void setNPCManager(NPCManager npcManager) {
        this.npcManager = npcManager;
    }

    public NPCManager getNPCManager() {
        return npcManager;
    }
}
