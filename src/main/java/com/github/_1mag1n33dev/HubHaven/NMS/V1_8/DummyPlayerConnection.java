package com.github._1mag1n33dev.HubHaven.NMS.V1_8;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import java.util.UUID;

public class DummyPlayerConnection extends PlayerConnection {

    public DummyPlayerConnection(MinecraftServer server) {
        super(server, new NetworkManager(EnumProtocolDirection.CLIENTBOUND), new EntityPlayer(server, ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle(), new GameProfile(UUID.randomUUID(), "dummy"), new PlayerInteractManager(((CraftWorld) Bukkit.getWorlds().get(0)).getHandle())));
    }

    @Override
    public void sendPacket(Packet packet) {
    }

    @Override
    public void a(PacketPlayInChat packet) {
    }

    @Override
    public void a(PacketPlayInClientCommand packet) {
    }

    @Override
    public void a(PacketPlayInUseEntity packet) {
    }

    @Override
    public void a(PacketPlayInBlockDig packet) {
    }

    @Override
    public void a(PacketPlayInBlockPlace packet) {
    }

}

