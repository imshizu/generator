package eu.altshizu.generator.database;

import com.j256.ormlite.dao.Dao;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * BaseStore is a generic data access layer class that manages the interaction with the database.
 * It provides methods to query, persist, and delete objects using ORMLite.
 *
 * @param <K> The type of the key used to identify objects in the database.
 * @param <V> The type of the value or object being stored and managed in the database.
 */
@Getter
public class BaseStore<K, V> {

    private final Dao<V, K> dao;
    private final StoreManager stores;

    /**
     * Constructor for BaseStore.
     *
     * @param dao    The ORMLite Dao used to interact with the database.
     * @param stores A reference to the StoreManager for managing multiple stores.
     */
    public BaseStore(Dao<V, K> dao, StoreManager stores) {
        this.dao = dao;
        this.stores = stores;
    }

    /**
     * Retrieves an object by a specific key-value pair or persists a new object if absent.
     *
     * @param key            The field name to query.
     * @param keyValue       The value of the key to look for.
     * @param createIfAbsent The object to persist if the key-value pair is not found.
     * @return The existing object or the newly persisted object if absent.
     */
    public V getOrPersist(String key, Object keyValue, V createIfAbsent) {
        Optional<V> existing = this.get(key, keyValue);
        if (existing.isPresent()) {
            return existing.get();
        }
        this.persist(createIfAbsent);
        return createIfAbsent;
    }

    /**
     * Persists or updates the given object in the database.
     *
     * @param value The object to be persisted or updated.
     */
    public void persist(V value) {
        try {
            this.dao.createOrUpdate(value);
        } catch (Exception ignored) {
        }
    }

    /**
     * Retrieves an object based on a specific field and value.
     *
     * @param key      The field name to query.
     * @param keyValue The value of the field to match.
     * @return An Optional containing the object if found, or empty if not found.
     */
    public Optional<V> get(String key, Object keyValue) {
        return getAll(key, keyValue).stream().findAny();
    }

    /**
     * Retrieves a list of objects matching a specific field and value.
     *
     * @param key      The field name to query.
     * @param keyValue The value of the field to match.
     * @return A list of matching objects or an empty list if none found.
     */
    public List<V> getAll(String key, Object keyValue) {
        try {
            return this.dao.queryForEq(key, keyValue);
        } catch (Exception ignored) {
        }
        return Collections.emptyList();
    }

    /**
     * Retrieves all objects from the database.
     *
     * @return A list of all objects in the store or an empty list if none found.
     */
    public List<V> getAll() {
        try {
            return this.dao.queryForAll();
        } catch (Exception ignored) {
        }
        return Collections.emptyList();
    }

    /**
     * Retrieves an object by its unique key (ID).
     *
     * @param key The unique identifier of the object.
     * @return An Optional containing the object if found, or empty if not found.
     */
    public Optional<V> get(K key) {
        try {
            return Optional.ofNullable(this.dao.queryForId(key));
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }

    /**
     * Deletes an object by its unique key (ID) from the database.
     *
     * @param key The unique identifier of the object to delete.
     * @return true if the object was successfully deleted, false otherwise.
     */
    public boolean delete(K key) {
        try {
            this.dao.deleteById(key);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}