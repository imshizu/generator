package eu.imshizu.generator.configs;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * This is a configuration class that manages various settings for the plugin.
 */
@Getter @Setter
@Configuration(path = "config.yml", provider = YamlSnakeYamlConfigurer.class)
@SuppressWarnings("FieldMayBeFinal")
public class Config extends OkaeriConfig {

    @Comment({
            "",
            "How many slots you start with",
            "Default: 5",
    })
    private int slots = 5;

    @Comment({
            "",
            "The starting multiplier",
            "Default: 1.0",
    })
    private double multiplier = 1.0;

    @Comment({
            "",
            "How much generator drops increase per tier",
            "Default: 1.25",
    })
    private double dropIncrease = 1.25;

    @Comment({
            "",
            "The starting price for generators",
            "Default: 20",
    })
    private int startPrice = 20;

    @Comment({
            "",
            "How much the generator price increases per tier",
            "Default: 1.35",
    })
    private double priceIncrease = 1.35;

    @Comment({
            "",
            "The first XP requirement",
            "Default: 100",
    })
    private int xpRequirement = 100;

    @Comment({
            "",
            "How much the XP requirement increases per levelup",
            "Default: 1.40",
    })
    private double xpIncrease = 1.40;

    @Comment({
            "",
            "How many slots you gain per levelup",
            "Default: 1",
    })
    private int slotsIncrease = 1;

    @Comment({
            "",
            "How much multiplier you gain per levelup",
            "Default: 1",
    })
    private int multiplierIncrease = 1;

    @Comment({
            "",
            "How long it takes for generators to generate",
            "Default: 5 (in seconds)",
    })
    private int generateTime = 5;
}
