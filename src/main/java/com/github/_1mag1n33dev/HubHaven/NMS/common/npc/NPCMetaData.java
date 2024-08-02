package com.github._1mag1n33dev.HubHaven.NMS.common.npc;

import java.util.UUID;

public class NPCMetaData {

    private final UUID id;
    private final String name;
    private double x, y, z;

    public NPCMetaData(UUID id, String name, double x, double y, double z) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

}
