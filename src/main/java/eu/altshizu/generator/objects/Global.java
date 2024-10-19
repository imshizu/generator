/**
 * A class representing a generator.
 * Contains information about generator's tier and material.
 * Also, contains methods which enable certain operations on the Generator object.
 */
package eu.altshizu.generator.objects;

import eu.okaeri.commons.bukkit.item.ItemStackBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

@Getter @Setter
public class Global implements Serializable {

    /**
     * The tier of the generator.
     */
    private int tier;

    /**
     * The material used in the generator.
     */
    private Material material;

    /**
     * The data for the generator.
     */
    private short data;

    /**
     * Constructs a Generator with the provided tier, material and data values.
     *
     * @param tier the tier of the generator
     * @param material the material used in the generator
     * @param data the data relevant to the generator
     */
    public Global(int tier, Material material, short data) {
        this.tier = tier;
        this.material = material;
        this.data = data;
    }

    /**
     * Creates an instance of ItemStack involving material defined in this generator
     * with custom name that includes the tier of generator.
     *
     * @return the ItemStack instance for this generator
     */
    public ItemStack getItem() {
        return ItemStackBuilder.of(this.material, 1)
                .withDurability(this.data)
                .withName("§2§lGenerator §7(Tier " + this.tier + ")")
                .get();
    }
}