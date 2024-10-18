package eu.altshizu.generator.configs;

import eu.altshizu.generator.objects.Generator;
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
    public Map<Integer, Generator> generators = new HashMap<Integer, Generator>() {{
        put(1, new Generator(1, Material.DIRT, (short) 0));
    }};
}
