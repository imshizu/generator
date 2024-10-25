package eu.altshizu.generator.configs;

import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import eu.okaeri.i18n.configs.LocaleConfig;
import eu.okaeri.platform.core.annotation.Messages;
import lombok.Getter;

/**
 * A class that represents the language configuration.
 * Contains information about the language configuration
 */
@Getter
@SuppressWarnings("FieldMayBeFinal")
@Messages(path = "lang", defaultLocale = "dk", provider = YamlSnakeYamlConfigurer.class)
public class LangConfig extends LocaleConfig {
    private String prefix = "&6[Generator]";
    private String noPermission = "{prefix} &7Du har ikke adgang til denne kommando.";
    
    /**
     * Messages for the generator command is stored here.
     */
    private String addGenerator = "{prefix} &7Du tilføjede en ny generator. (Tier {tier})";
    private String holdItem = "{prefix} &7Du skal holde et item i hånden.";
    private String removeGenerator = "{prefix} &7Du fjernede &e{tier} &7generator.";
    private String generatorNotFound = "{prefix} &7Denne generator findes ikke.";
    private String receiveGenerator = "{prefix} Du modtog &e{tier} &7generator.";

    /**
     * Messages for the generator shop is stored here.
     */
    private String buyGenerator = "{prefix} &7Du købte en &etier {tier} generator&7.";
    private String notEnoughMoney = "{prefix} &7Du mangler &c${money}&7.";

    /**
     * Messages for the generator events is stored here.
     */
    private String placedSellchest = "{prefix} &7Du placerede en &esælgekiste&7.";
    private String addedToStack = "{prefix} &7Du tilføjede en generator til stacken. ({amount} i alt)";
    private String placedGenerator = "{prefix} &7Du placerede en &etier {tier} generator&7.";
    private String maxGenerators = "{prefix} &7Du har ikke plads til flere generators.";
    private String sellSuccess = "{prefix} &7Du indsamlet &a${money} &7& &b{xp} XP&7.";
    private String sellFail = "{prefix} &7Der er ikke noget i denne sælgekiste.";
    private String upgradeSuccess = "{prefix} &7Du opgraderet &e{amount}x tier {tier} generator&7 for &a${money}&7.";
    private String upgradeFail = "{prefix} &7Du mangler &c${money}&7.";
    private String upgradeMax = "{prefix} &7Du har den bedste generator tier.";
    private String notYourGenerator = "{prefix} &7Du ejer ikke denne generator. ({owner})";
    private String removeFromStack = "{prefix} &7Du fjernede &e{amount}x generator &7fra stacken. &7({gens} i alt)";
    private String notEnoughInventorySpace = "{prefix} &7Du har ikke nok inventory plads.";
    private String removeStack = "{prefix} &7Du fjernede en generator stack.";
}