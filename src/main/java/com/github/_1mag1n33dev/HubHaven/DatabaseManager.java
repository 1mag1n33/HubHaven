package com.github._1mag1n33dev.HubHaven;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.*;
import java.util.logging.Level;

public class DatabaseManager {
    private Connection connection;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:plugins/HubHaven/hubhaven.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to SQLite database.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from SQLite database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS hub_locations (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "world TEXT NOT NULL," +
                "x DOUBLE NOT NULL," +
                "y DOUBLE NOT NULL," +
                "z DOUBLE NOT NULL," +
                "yaw FLOAT NOT NULL," +
                "pitch FLOAT NOT NULL" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setHubLocation(String world, double x, double y, double z, float yaw, float pitch) {
        String deleteSql = "DELETE FROM hub_locations;";
        String insertSql = "INSERT INTO hub_locations (world, x, y, z, yaw, pitch) VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql);
             PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            deleteStmt.executeUpdate();

            insertStmt.setString(1, world);
            insertStmt.setDouble(2, x);
            insertStmt.setDouble(3, y);
            insertStmt.setDouble(4, z);
            insertStmt.setFloat(5, yaw);
            insertStmt.setFloat(6, pitch);
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Location getHubLocation() {
        String sql = "SELECT * FROM hub_locations LIMIT 1;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String worldName = rs.getString("world");
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double z = rs.getDouble("z");
                float yaw = rs.getFloat("yaw");
                float pitch = rs.getFloat("pitch");

                World world = Bukkit.getWorld(worldName);
                if (world == null) {
                    return null;
                }

                return new Location(world, x, y, z, yaw, pitch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clearHubLocation() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "DELETE FROM hub_location";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
