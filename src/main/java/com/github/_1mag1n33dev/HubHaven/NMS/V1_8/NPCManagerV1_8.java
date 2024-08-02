package com.github._1mag1n33dev.HubHaven.NMS.V1_8;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPC;
import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPCManager;
import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPCMetaData;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.UUID;
import java.util.logging.Logger;

public class NPCManagerV1_8 implements NPCManager {

    private final HubHaven plugin;
    private final Logger logger;

    public NPCManagerV1_8(HubHaven plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    @Override
    public void createNPC(String name, double x, double y, double z) {
        logger.info("Creating NPC: " + name + " at " + x + ", " + y + ", " + z);

        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();

        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
        logger.info("GameProfile created: " + profile);

        EntityPlayer npc = new EntityPlayer(server, world, profile, new PlayerInteractManager(world)) {
            @Override
            public void setPosition(double x, double y, double z) {
                super.setPosition(x, y, z);
            }

            @Override
            public boolean damageEntity(DamageSource source, float amount) {
                return false;
            }

            @Override
            public void a(NBTTagCompound nbttagcompound) {
            }
        };

        npc.playerConnection = new DummyPlayerConnection(server);
        npc.setPosition(x, y, z);
        npc.setCustomName(name);
        npc.setCustomNameVisible(true);

        NPC npcWrapper = new NPC(npc);
        npcWrapper.spawn();

        NPCMetaData npcMetaData = new NPCMetaData(npc.getUniqueID(), name, x, y, z);
        plugin.getNPCRegistry().registerNPC(npcMetaData);
        logger.info("NPC registered: " + npcMetaData.getId());

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) onlinePlayer;
            PlayerConnection connection = craftPlayer.getHandle().playerConnection;

            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));

            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));

            connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), npc.getDataWatcher(), true));

            logger.info("Packets sent to player: " + onlinePlayer.getName());
        }
        logger.info("NPC spawn packets sent to all online players.");
    }

    @Override
    public void removeNPC(NPC npc) {
        npc.remove();
    }
}
