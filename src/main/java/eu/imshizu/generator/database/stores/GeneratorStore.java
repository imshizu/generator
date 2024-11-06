package eu.imshizu.generator.database.stores;

import com.j256.ormlite.dao.Dao;
import eu.imshizu.generator.configs.Config;
import eu.imshizu.generator.configs.GensConfig;
import eu.imshizu.generator.database.BaseStore;
import eu.imshizu.generator.database.StoreManager;
import eu.imshizu.generator.objects.Generator;
import eu.imshizu.generator.objects.Global;
import eu.imshizu.generator.utils.Locations;
import lombok.Getter;
import org.bukkit.Location;

import java.util.Optional;

/**
 * This is a generator store class that manages various operations related to Generators.
 */
public class GeneratorStore extends BaseStore<Integer, Generator> {
    private final GensConfig gensConfig;
    private final @Getter Config config;

    /**
     * Creates a new GeneratorStore instance.
     *
     * @param dao     DAO for Generator
     * @param stores  store manager instance
     */
    public GeneratorStore(Dao<Generator, Integer> dao, StoreManager stores, GensConfig gensConfig, Config config) {
        super(dao, stores);
        this.gensConfig = gensConfig;
        this.config = config;
    }

    /**
     * Gets a generator at a specific location.
     *
     * @param location  the location of the generator
     * @return          an optional of generator at the given location
     */
    public Optional<Generator> getGeneratorAtLocation(Location location) {
        return get("location", Locations.serialize(location));
    }

    /**
     * Gets a generator by tier level.
     *
     * @param tier  the tier level of the generator
     * @return      the generator object of the given tier
     */
    public Global getGeneratorByTier(int tier) {
        return gensConfig.getGenerators().get(tier);
    }

    /**
     * Upgrades a generator to the next tier level.
     *
     * @param generator  the generator object to be upgraded
     */
    public void upgrade(Generator generator) {
        Global global = this.getGeneratorByTier(generator.getTier() + 1);
        Location location = Locations.deserialize(generator.getLocation());

        location.getBlock().setType(global.getMaterial());

        generator.setTier(generator.getTier() + 1);
        persist(generator);
    }

    /**
     * Deletes a generator from the store.
     *
     * @param generator  the generator object to be deleted
     */
    public void delete(Generator generator, int amount) {
        if(generator.getAmount() > amount) {
            generator.setAmount(generator.getAmount() - amount);
            persist(generator);
        } else {
            delete(generator.getId());
        }
    }

    /**
     * Retrieve the upgrade cost for this generator.
     * The cost is calculated based on the tier of the generator.
     *
     * @return the upgrade cost for this generator
     */
    public double getUpgradeCost(Generator generator) {
        return (config.getStartPrice() * Math.pow(config.getPriceIncrease(), generator.getTier()) * generator.getAmount());
    }
}