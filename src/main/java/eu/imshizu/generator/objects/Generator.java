package eu.imshizu.generator.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;
import eu.imshizu.generator.utils.Locations;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;

/**
 * This is a Generator class that represents a generator entity in the database.
 */
@Getter @Setter
@NoArgsConstructor
@DatabaseTable(tableName = "user_generators")
public class Generator extends BaseDaoEnabled<Generator, Integer> {

    /**
     * The id of the generator - auto-generated.
     */
    @DatabaseField(generatedId = true, columnName = "id")
    private int id;

    /**
     * The user to which the generator belongs.
     */
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "user")
    private User user;

    /**
     * The tier level of the generator.
     */
    @DatabaseField(columnName = "tier")
    private int tier;

    /**
     * The amount of the generator.
     */
    @DatabaseField(columnName = "amount")
    private int amount;

    /**
     * The location of the generator.
     */
    @DatabaseField(columnName = "location")
    private String location;

    /**
     * Construct a new Generator with a user, tier level and location.
     *
     * @param user      the user that the generator belongs to
     * @param tier      the tier level of the generator
     * @param location  the location of the generator
     */
    public Generator(User user, int tier, Location location) {
        this.user = user;
        this.tier = tier;
        this.location = Locations.serialize(location);
    }
}