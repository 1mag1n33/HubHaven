package com.github._1mag1n33dev.HubHavenApi.npc;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.UUID;

public interface NPC {

    void spawn(Location location, String name);
    void despawn();
    void setName(String name);
    void setLocation(Location location);
    Entity getEntity();
    void setNPCId(UUID id);
    UUID getNPCId();
}
