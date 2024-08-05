package com.github._1mag1n33dev.HubHaven.NMS.common;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.NMS.V1_8.network.PacketManagerV1_8;
import com.github._1mag1n33dev.HubHaven.NMS.V1_8.npc.NPCManagerV1_8;
import com.github._1mag1n33dev.HubHavenApi.network.PacketManager;
import com.github._1mag1n33dev.HubHavenApi.npc.NPCManager;

public class VersionFactory {
    private final String serverVersion;

    public VersionFactory(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public PacketManager createPacketManager(HubHaven plugin) {
        switch (serverVersion) {
            case "v1_8_R3":
                return new PacketManagerV1_8(plugin);
            case "v1_16_R3":
                // return new PacketManagerV1_16(plugin);
            default:
                throw new IllegalArgumentException("Unsupported version: " + serverVersion);
        }
    }

    public NPCManager createNPCManager(HubHaven plugin) {
        switch (serverVersion) {
            case "v1_8_R3":
                return new NPCManagerV1_8();
            case "v1_16_R3":
                // return new NPCManagerV1_16(plugin);
            default:
                throw new IllegalArgumentException("Unsupported version: " + serverVersion);
        }
    }
}
