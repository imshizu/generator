package eu.altshizu.generator.database;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.LogBackendType;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import eu.altshizu.generator.Generator;
import eu.altshizu.generator.database.stores.GeneratorStore;
import eu.altshizu.generator.database.stores.UserStore;
import eu.altshizu.generator.objects.User;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.injector.annotation.PostConstruct;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;

/**
 * Manages the database connection and data access objects (DAOs) for the application.
 */
@Component
@Getter
public class StoreManager {

    private @Inject final Generator plugin;

    private UserStore userStore;
    private GeneratorStore generatorStore;

    private @Getter ConnectionSource connectionSource;


    public StoreManager(@Inject Generator plugin) {
        this.plugin = plugin;
    }

    /**
     * Initializes the database connection and creates the necessary tables if they do not exist.
     *
     */
    @SneakyThrows
    @PostConstruct
    public void init() {
        com.j256.ormlite.logger.Logger.setGlobalLogLevel(Level.ERROR);
        LoggerFactory.setLogBackendFactory(LogBackendType.JAVA_UTIL);

        try {
            connectionSource = new JdbcConnectionSource(getConnectionUrl());

            TableUtils.createTableIfNotExists(connectionSource, UserStore.class);
            TableUtils.createTableIfNotExists(connectionSource, GeneratorStore.class);
        } catch (Exception ignored) {
        }

        this.userStore = new UserStore(DaoManager.createDao(connectionSource, User.class), this);
        this.generatorStore = new GeneratorStore(DaoManager.createDao(connectionSource, eu.altshizu.generator.objects.Generator.class), this);
    }

    /**
     * Constructs the database connection URL.
     *
     * @return the database connection URL
     */
    public String getConnectionUrl() {
        String databaseType = "sqlite";
        String databasePath = plugin.getDataFolder() + File.separator + plugin.getDescription().getName();
        return "jdbc:" + databaseType + ":" + databasePath + ".db";
    }

    /**
     * Closes the database connection and sets the DAOs to null.
     */
    @SneakyThrows
    public void disconnect() {
        this.userStore = null;
        connectionSource.close();
    }
}