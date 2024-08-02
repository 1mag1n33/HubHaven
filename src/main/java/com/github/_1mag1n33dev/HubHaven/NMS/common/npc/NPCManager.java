package com.github._1mag1n33dev.HubHaven.NMS.common.npc;

import org.bukkit.Location;

public interface NPCManager {

    void createNPC(String name, double x, double y, double z);

    void removeNPC(NPC npc);

}
