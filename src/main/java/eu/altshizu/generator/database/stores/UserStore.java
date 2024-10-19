package eu.altshizu.generator.database.stores;

import com.j256.ormlite.dao.Dao;
import eu.altshizu.generator.database.BaseStore;
import eu.altshizu.generator.database.StoreManager;
import eu.altshizu.generator.objects.Generator;
import eu.altshizu.generator.objects.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class UserStore extends BaseStore<Integer, User> {
    private StoreManager stores;

    public UserStore(Dao<User, Integer> dao, StoreManager stores) {
        super(dao, stores);
    }

    public User getUser(Player player) {
        User user = new User(player.getName(), 5, 1, 1, 0, player.getUniqueId(), 0);
        User created = getOrPersist("uuid", user.getUuid(), user);
        created.setName(player.getName());
        persist(created);
        return created;
    }

    public void addGenerator(User user, int tier, Location location) {
        Generator generator = new Generator(user, tier, location);
        stores.getGeneratorStore().persist(generator);
        persist(user);
    }
}
