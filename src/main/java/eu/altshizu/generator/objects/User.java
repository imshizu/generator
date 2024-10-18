package eu.altshizu.generator.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

/**
 * A class representing a user.
 * Contains information about user's multiplier, experience and level.
 */
@Getter @Setter
@DatabaseTable(tableName = "users")
public class User extends BaseDaoEnabled<User, Integer> {

    /**
     * The id of the user.
     */
    @DatabaseField(canBeNull = false, generatedId = true, columnName = "id")
    private int id;

    /**
     * The multiplier of the user.
     */
    @DatabaseField(columnName = "multiplier")
    private double multiplier;

    /**
     * The experience of the user.
     */
    @DatabaseField(columnName = "xp")
    private double xp;

    /**
     * The level of the user.
     */
    @DatabaseField(columnName = "level")
    private int level;

    /**
     * Default constructor for User class.
     */
    public User() {
    }
}