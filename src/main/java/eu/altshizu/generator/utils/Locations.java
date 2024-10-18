package eu.altshizu.generator.utils;

public class Locations {

    public static String serialize(org.bukkit.Location location) {
        return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ();
    }

    public static org.bukkit.Location deserialize(String location) {
        String[] parts = location.split(";");
        return new org.bukkit.Location(org.bukkit.Bukkit.getWorld(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
    }
}
