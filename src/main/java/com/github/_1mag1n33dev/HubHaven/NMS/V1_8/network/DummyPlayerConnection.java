package com.github._1mag1n33dev.HubHaven.NMS.V1_8.network;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;

public class DummyPlayerConnection extends PlayerConnection {
    public DummyPlayerConnection(MinecraftServer minecraftServer) {
        super(minecraftServer, new NetworkManager(EnumProtocolDirection.CLIENTBOUND), new EntityPlayer(minecraftServer, null, new GameProfile(null, ""), new PlayerInteractManager(null)));
    }

    @Override
    public void sendPacket(Packet packet) {
        // Do nothing - this is a dummy connection
    }

    @Override
    public void a(PacketPlayInChat packet) {
        // Do nothing
    }

    @Override
    public void a(PacketPlayInClientCommand packet) {
        // Do nothing
    }

    // Implement other methods as needed
}
