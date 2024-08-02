package com.github._1mag1n33dev.HubHaven.NMS.common;

import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPCManager;
import net.minecraft.server.v1_8_R3.Packet;

public interface PacketManager {

    void sendPacket(Object packet);
    void sendPacketToPlayer(org.bukkit.entity.Player player, Packet<?> packet);
    NPCManager getNPCManager();
}
