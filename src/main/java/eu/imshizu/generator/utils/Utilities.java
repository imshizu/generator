package eu.imshizu.generator.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    /**
     * Get the tier of a generator item.
     *
     * @param item The item we want to get the tier of.
     * @return The tier of the item.
     */
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

    /**
     * Sort a number into a more readable format.
     *
     * @param number The number we want to sort.
     * @return The sorted number.
     */
    public static String sort(double number) {
        String[] abbreviations = new String[] { "", "K", "M", "B", "T", "Q", "QT", "SX" };
        int i = Math.min((int) Math.floor(Math.log(number) / 3), abbreviations.length);
        return String.format("%.2f%s", number / Math.pow(1000, i), abbreviations[i]);
    }

    /**
     * Remove a specific amount of items from the player's inventory.
     *
     * @param player The player whose inventory we are modifying.
     * @param material The material of the item we want to remove.
     * @param amount The amount of the item we want to remove.
     */
    public static void removeItems(Player player, Material material, int amount) {
        int remaining = amount;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == material) {
                int itemAmount = item.getAmount();
                if (itemAmount <= remaining) {
                    player.getInventory().remove(item);
                    remaining -= itemAmount;
                } else {
                    item.setAmount(itemAmount - remaining);
                    remaining = 0;
                }
                if (remaining <= 0) break;
            }
        }
    }

    /**
     * Check if the player can hold a specific amount of the specified item.
     *
     * @param player The player whose inventory we are checking.
     * @param item The item we want to check.
     * @param amount The amount of the item the player needs to hold.
     * @return True if the player can hold the specified amount of the item, false otherwise.
     */
    public static boolean canPlayerHold(Player player, ItemStack item, int amount) {
        int remainingAmount = amount;

        for (ItemStack invItem : player.getInventory().getContents()) {
            if (invItem == null) {
                remainingAmount -= item.getMaxStackSize();
            }
            else if (invItem.isSimilar(item)) {
                remainingAmount -= (item.getMaxStackSize() - invItem.getAmount());
            }

            if (remainingAmount <= 0) {
                return true;
            }
        }

        return remainingAmount <= 0;
    }
}