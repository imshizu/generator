package eu.altshizu.generator.utils;

import org.bukkit.inventory.ItemStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item {

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
}