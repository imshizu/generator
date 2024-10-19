package eu.altshizu.generator.database.stores;

import com.j256.ormlite.dao.Dao;
import eu.altshizu.generator.configs.GensConfig;
import eu.altshizu.generator.database.BaseStore;
import eu.altshizu.generator.database.StoreManager;
import eu.altshizu.generator.objects.Generator;
import eu.altshizu.generator.objects.Global;
import eu.altshizu.generator.utils.Locations;
import org.bukkit.Location;

import java.util.Optional;

/**
 * This is a generator store class that manages various operations related to Generators.
 */
public class GeneratorStore extends BaseStore<Integer, Generator> {
    private GensConfig gensConfig;

    /**
     * Creates a new GeneratorStore instance.
     *
     * @param dao     DAO for Generator
     * @param stores  store manager instance
     */
    public GeneratorStore(Dao<Generator, Integer> dao, StoreManager stores) {
        super(dao, stores);
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
    public void delete(Generator generator) {
        delete(generator.getId());
    }
}