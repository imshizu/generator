package eu.altshizu.generator.database.stores;

import com.j256.ormlite.dao.Dao;
import eu.altshizu.generator.database.BaseStore;
import eu.altshizu.generator.database.StoreManager;
import eu.altshizu.generator.objects.Generator;

public class GeneratorStore extends BaseStore<Integer, Generator> {
    private StoreManager stores;

    public GeneratorStore(Dao<Generator, Integer> dao, StoreManager stores) {
        super(dao, stores);
    }
}
