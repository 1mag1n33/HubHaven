package com.github._1mag1n33dev.HubHaven.NMS.common.npc;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

public class NPC {
    private EntityPlayer nmsEntity;

    public NPC(EntityPlayer nmsEntity) {
        this.nmsEntity = nmsEntity;
    }

    public EntityPlayer getNMSEntity() {
        return nmsEntity;
    }

    public void setLocation(Location location) {
        nmsEntity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public void spawn() {
        ((CraftWorld) nmsEntity.getBukkitEntity().getWorld()).getHandle().addEntity(nmsEntity);
    }

    public void remove() {
        nmsEntity.die();
    }
}
