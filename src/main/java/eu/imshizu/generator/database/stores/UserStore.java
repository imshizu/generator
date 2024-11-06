package eu.imshizu.generator.database.stores;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import eu.imshizu.generator.database.BaseStore;
import eu.imshizu.generator.database.StoreManager;
import eu.imshizu.generator.objects.Generator;
import eu.imshizu.generator.objects.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * This is a user store class that manages various operations related to Users.
 */
public class UserStore extends BaseStore<Integer, User> {
    private final StoreManager stores;

    /**
     * Creates a new UserStore instance.
     *
     * @param dao     DAO for User
     * @param stores  store manager instance
     */
    public UserStore(Dao<User, Integer> dao, StoreManager stores) {
        super(dao, stores);
        this.stores = stores;
    }

    /**
     * Gets a User for a player, or creates and persists a new one if none already exists.
     *
     * @param player  instance of the player for which we want to retrieve or create the user
     * @return        the retrieved or newly created user
     */
    public User getUser(Player player) {
        User user = new User(player.getName(), 5, 1, 1, 0, player.getUniqueId(), 0);
        User created = getOrPersist("uuid", user.getUuid(), user);
        created.setName(player.getName());
        persist(created);
        return created;
    }

    /**
     * Update the user's data in the database.
     */
    public void updateData(User user, String data, double value) {
        switch (data) {
            case "slots":
                user.setSlots(user.getSlots() + (int) value);
                break;
            case "multiplier":
                user.setMultiplier(user.getMultiplier() + value);
                break;
            case "level":
                user.setLevel(user.getLevel() + (int) value);
                break;
            case "usedSlots":
                user.setUsedSlots(user.getUsedSlots() + (int) value);
                break;
            case "xp":
                user.setXp(user.getXp() + (int) value);
                break;
        }
        persist(user);
    }

    /**
     * Adds a generator to a user.
     *
     * @param user      the user to whom the generator should be added
     * @param tier      the tier of the generator
     * @param location  the location of the generator
     */
    public void addGenerator(User user, int tier, Location location) {
        try {
            Dao<Generator, Integer> generatorDao = stores.getGeneratorStore().getDao();
            if (hasGeneratorOfTier(user, tier)) {
                Generator generator = generatorDao.queryBuilder().where()
                        .eq("user", user).and().eq("tier", tier).queryForFirst();
                if (generator != null) {
                    generator.setAmount(generator.getAmount() + 1);
                    generatorDao.update(generator);
                }
            } else {
                Generator generator = new Generator(user, tier, location);
                generator.setAmount(1);
                stores.getGeneratorStore().persist(generator);
            }

            user.setUsedSlots(user.getUsedSlots() + 1);
            persist(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Check if a user has a generator of a specific tier.
     *
     * @param user      the user to check
     * @param tier      the tier of the generator to check for
     * @return          true if the user has a generator of the given tier, false otherwise
     */
    public boolean hasGeneratorOfTier(User user, int tier) {
        try {
            Dao<Generator, Integer> generatorDao = stores.getGeneratorStore().getDao();
            QueryBuilder<Generator, Integer> queryBuilder = generatorDao.queryBuilder();
            Where<Generator, Integer> where = queryBuilder.where();
            where.eq("user", user).and().eq("tier", tier);

            Generator generator = generatorDao.queryForFirst(queryBuilder.prepare());
            return generator != null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Get the amount of generators a user has of a specific tier.
     *
     * @param user      the user to check
     * @param tier      the tier of the generator to check for
     */
    public int getAmount(User user, int tier) {
        try {
            Dao<Generator, Integer> generatorDao = stores.getGeneratorStore().getDao();
            QueryBuilder<Generator, Integer> queryBuilder = generatorDao.queryBuilder();
            Where<Generator, Integer> where = queryBuilder.where();
            where.eq("user", user).and().eq("tier", tier);

            Generator generator = generatorDao.queryForFirst(queryBuilder.prepare());
            return generator != null ? generator.getAmount() : 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}