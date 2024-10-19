package eu.altshizu.generator.objects;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;
import eu.altshizu.generator.utils.Locations;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;

@Getter @Setter
@DatabaseTable(tableName = "user_generators")
@NoArgsConstructor
public class Generator extends BaseDaoEnabled<Generator, Integer> {

    @DatabaseField(generatedId = true, columnName = "id")
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "user")
    private User user;

    @DatabaseField(columnName = "tier")
    private int tier;

    @DatabaseField(columnName = "amount")
    private int amount;

    @DatabaseField(columnName = "location")
    private String location;

    public Generator(User user, int tier, Location location) {
        this.user = user;
        this.tier = tier;
        this.location = Locations.serialize(location);
    }
}