package eu.altshizu.generator.utils;

import org.bukkit.inventory.ItemStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    public static int getTier(ItemStack item) {
        if(!item.hasItemMeta() && !item.getItemMeta().hasDisplayName()) return -1;
        String name = item.getItemMeta().getDisplayName();

        Pattern pattern = Pattern.compile("Tier (\\d+)");
        Matcher matcher = pattern.matcher(name);

        if(matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }

    public static String sort(double number) {
        String[] abbreviations = new String[] { "", "K", "M", "B", "T", "Q", "QT", "SX" };
        int i = Math.min((int) Math.floor(Math.log(number) / 3), abbreviations.length);
        return String.format("%.2f%s", number / Math.pow(1000, i), abbreviations[i]);
    }
}