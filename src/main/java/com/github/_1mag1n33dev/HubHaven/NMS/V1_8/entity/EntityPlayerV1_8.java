package com.github._1mag1n33dev.HubHaven.NMS.V1_8.entity;

import com.github._1mag1n33dev.HubHavenApi.npc.NPC;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.UUID;

public class EntityPlayerV1_8 extends EntityPlayer implements NPC {

    private UUID npcId;

    public EntityPlayerV1_8(MinecraftServer server, WorldServer world) {
        super(server, world, new GameProfile(UUID.randomUUID(), "NPC"), new PlayerInteractManager(world));
    }

    @Override
    public void spawn(Location location, String name) {
        setName(name); // Ensure the name is set before spawning
        setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(name);
        this.setCustomNameVisible(true);
        this.setInvisible(false);

        this.spawnPackets(location);
    }

    private void spawnPackets(Location location) {
        PacketPlayOutPlayerInfo packetPlayOutPlayerInfoAdd = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, this);
        PacketPlayOutNamedEntitySpawn packetPlayOutNamedEntitySpawn = new PacketPlayOutNamedEntitySpawn(this);
        PacketPlayOutEntityHeadRotation packetPlayOutEntityHeadRotation = new PacketPlayOutEntityHeadRotation(this, (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
        PacketPlayOutPlayerInfo packetPlayOutPlayerInfoRemove = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, this);

        for (Player player : location.getWorld().getPlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutPlayerInfoAdd);
            craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutNamedEntitySpawn);
            craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutEntityHeadRotation);
            craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutPlayerInfoRemove);
        }
    }

    @Override
    public void despawn() {
        this.getBukkitEntity().remove();
    }

    @Override
    public void setName(String name) {
        setGameProfileName(name);
        setCustomName(name);
        setCustomNameVisible(true);
    }

    private void setGameProfileName(String name) {
        try {
            Field profileField = EntityHuman.class.getDeclaredField("bH");
            profileField.setAccessible(true);
            GameProfile profile = (GameProfile) profileField.get(this);
            Field nameField = GameProfile.class.getDeclaredField("name");
            nameField.setAccessible(true);

            nameField.set(profile, name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLocation(Location location) {
        teleportTo(location, false);
    }

    @Override
    public org.bukkit.entity.Entity getEntity() {
        return getBukkitEntity();
    }

    @Override
    public void setNPCId(UUID id) {
        this.npcId = id;
    }

    @Override
    public UUID getNPCId() {
        return this.npcId;
    }
}
