package eu.imshizu.generator.database;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.LogBackendType;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import eu.imshizu.generator.Generator;
import eu.imshizu.generator.configs.Config;
import eu.imshizu.generator.configs.GensConfig;
import eu.imshizu.generator.database.stores.GeneratorStore;
import eu.imshizu.generator.database.stores.UserStore;
import eu.imshizu.generator.objects.User;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.injector.annotation.PostConstruct;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;

/**
 * Manages the database connection and data access objects (DAOs) for the application.
 */
@Component @Getter
public class StoreManager {

    private final @Inject Generator plugin;
    private final @Inject GensConfig gensConfig;
    private final @Inject Config config;

    private UserStore userStore;
    private GeneratorStore generatorStore;

    private @Getter ConnectionSource connectionSource;

    public StoreManager(@Inject Generator plugin, @Inject GensConfig gensConfig, @Inject Config config) {
        this.plugin = plugin;
        this.gensConfig = gensConfig;
        this.config = config;
    }

    /**
     * Initializes the database connection and creates the necessary tables if they do not exist.
     */
    @SneakyThrows
    @PostConstruct
    public void init() {
        com.j256.ormlite.logger.Logger.setGlobalLogLevel(Level.ERROR);
        LoggerFactory.setLogBackendFactory(LogBackendType.JAVA_UTIL);

        try {
            connectionSource = new JdbcConnectionSource(getConnectionUrl());

            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, eu.imshizu.generator.objects.Generator.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        this.userStore = new UserStore(DaoManager.createDao(connectionSource, User.class), this, config);
        this.generatorStore = new GeneratorStore(DaoManager.createDao(connectionSource, eu.imshizu.generator.objects.Generator.class), this, gensConfig, config);
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