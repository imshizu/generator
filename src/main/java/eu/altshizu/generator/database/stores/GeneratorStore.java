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

public class GeneratorStore extends BaseStore<Integer, Generator> {
    private GensConfig gensConfig;

    public GeneratorStore(Dao<Generator, Integer> dao, StoreManager stores) {
        super(dao, stores);
    }

    public Optional<Generator> getGeneratorAtLocation(Location location) {
        return get("location", Locations.serialize(location));
    }

    public Global getGeneratorByTier(int tier) {
        return gensConfig.getGenerators().get(tier);
    }

    public void upgrade(Generator generator) {
        Global global = this.getGeneratorByTier(generator.getTier() + 1);
        Location location = Locations.deserialize(generator.getLocation());

        location.getBlock().setType(global.getMaterial());

        generator.setTier(generator.getTier() + 1);
        persist(generator);
    }

    public void delete(Generator generator) {
        delete(generator.getId());
    }
}
