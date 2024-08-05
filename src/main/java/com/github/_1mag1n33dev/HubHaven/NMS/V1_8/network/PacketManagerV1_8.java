package com.github._1mag1n33dev.HubHaven.NMS.V1_8.network;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHavenApi.network.PacketManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketManagerV1_8 implements PacketManager {
    private HubHaven plugin;

    public PacketManagerV1_8(HubHaven plugin) {
        this.plugin = plugin;
    }

    @Override
    public void sendPacket(Object packet) {
        if (!(packet instanceof Packet)) {
            throw new IllegalArgumentException("Object must be an instance of Packet");
        }

        Packet<?> nmsPacket = (Packet<?>) packet;

        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(nmsPacket);
        }
    }

    @Override
    public void sendPacketToPlayer(org.bukkit.entity.Player player, Packet<?> packet) {
        ((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
