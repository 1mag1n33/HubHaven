package com.github._1mag1n33dev.HubHaven.NMS.common;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.NMS.V1_8.PacketManagerV1_8;
import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPCRegistry;

public class PacketManagerFactory {
    private final String serverVersion;

    public PacketManagerFactory(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public PacketManager createPacketManager(HubHaven plugin) {
        switch (serverVersion) {
            case "v1_8_R3":
                return new PacketManagerV1_8(plugin);
            case "v1_16_R3":
                throw new IllegalArgumentException("Not yet implemented.");
            default:
                throw new IllegalArgumentException("Unsupported version: " + serverVersion);
        }
    }
}
