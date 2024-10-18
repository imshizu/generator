package eu.altshizu.generator.configs;

import eu.altshizu.generator.objects.Global;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

/**
 * A class representing the configuration of generators.
 * Contains information about generators and their properties.
 */
@Getter @Setter
@Configuration(path = "gens.yml", provider = YamlSnakeYamlConfigurer.class)
@SuppressWarnings("FieldMayBeFinal")
public class GensConfig extends OkaeriConfig {

    /**
     * A map containing all the generators.
     */
    public Map<Integer, Global> generators = new HashMap<Integer, Global>() {{
        put(1, new Global(1, Material.DIRT, (short) 0));
        put(2, new Global(2, Material.GRASS, (short) 0));
    }};
}
