package eu.altshizu.generator.database.stores;

import com.j256.ormlite.dao.Dao;
import eu.altshizu.generator.database.BaseStore;
import eu.altshizu.generator.database.StoreManager;
import eu.altshizu.generator.objects.User;
import org.bukkit.entity.Player;

public class UserStore extends BaseStore<Integer, User> {
    private StoreManager stores;

    public UserStore(Dao<User, Integer> dao, StoreManager stores) {
        super(dao, stores);
    }

    public User getUser(Player player) {
        // ...
        return null;
    }
}
