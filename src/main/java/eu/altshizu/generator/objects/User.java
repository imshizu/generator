package eu.altshizu.generator.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * A class representing a user.
 * Contains information about user's multiplier, experience and level.
 */
@Getter @Setter
@DatabaseTable(tableName = "users")
@NoArgsConstructor
public class User extends BaseDaoEnabled<User, Integer> {

    /**
     * The id of the user.
     */
    @DatabaseField(generatedId = true, columnName = "id")
    private int id;

    /**
     * The name of the user.
     */
    @DatabaseField(columnName = "name")
    private String name;

    /**
     * The UUID of the user.
     */
    @DatabaseField(columnName = "uuid")
    private UUID uuid;

    /**
     * The amount of slots the user has unlocked.
     */
    @DatabaseField(columnName = "slots")
    private int slots;

    /**
     * The amount of multiplier the user has.
     */
    @DatabaseField(columnName = "multiplier")
    private double multiplier;

    /**
     * The level of the user.
     */
    @DatabaseField(columnName = "level")
    private int level;

    /**
     * The amount of used slots (aka placed generators) the user has.
     */
    @DatabaseField(columnName = "used_slots")
    private int usedSlots;

    /**
     * The experience of the user.
     */
    @DatabaseField(columnName = "xp")
    private double xp;

    /**
     * The location of the user's sellchest, if they have one.
     */
    @DatabaseField(columnName = "sellchest")
    private String sellchest;

    public User(String name, int slots, double multiplier, int level, int usedSlots, UUID uuid, double xp) {
        this.name = name;
        this.slots = slots;
        this.multiplier = multiplier;
        this.level = level;
        this.usedSlots = usedSlots;
        this.uuid = uuid;
        this.xp = xp;
    }

    /**
     * The formula to calculate the experience needed for the next level.
     *
     */
    private double xpNeeded;

    public void addXP(double xp) {
    }
}