package eu.altshizu.generator.configs;

import eu.okaeri.configs.annotation.Comment;
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
    private String reloadStart = "{prefix} &7Genindlæser...";
    private String reloadEnd = "{prefix} &aDu genindlæste pluginnet successfuldt! &2({ms}ms)";

    @Comment({
            "",
            "Command messages"
    })
    private String prefix = "&6[Generator]";

    @Comment({
            "",
            "Generator command messages"
    })
    private String addGenerator = "{prefix} &7Du tilføjede en ny generator. (Tier {tier})";
    private String holdItem = "{prefix} &7Du skal holde et item i hånden.";
    private String removeGenerator = "{prefix} &7Du fjernede &e{tier} &7generator.";
    private String generatorNotFound = "{prefix} &7Denne generator findes ikke.";
    private String receiveGenerator = "{prefix} &7Du modtog en &e{tier} &7generator.";
    private String gaveGenerator = "{prefix} &7Du gav en &etier {tier} &7generator til &e{target}&7.";

    @Comment({
            "",
            "Generator shop messages"
    })
    private String buyGenerator = "{prefix} &7Du købte en &etier {tier} generator&7.";
    private String notEnoughMoney = "{prefix} &7Du mangler &c${money}&7.";

    @Comment({
            "",
            "Generator event messages"
    })
    private String placedSellchest = "{prefix} &7Du placerede en &esælgekiste&7.";
    private String addedToStack = "{prefix} &7Du tilføjede en generator til stacken. ({amount} i alt)";
    private String placedGenerator = "{prefix} &7Du placerede en &etier {tier} generator&7.";
    private String maxGenerators = "{prefix} &7Du har ikke plads til flere generators.";
    private String sellSuccess = "{prefix} &7Du indsamlede &a${money} &7& &b{xp} XP&7.";
    private String sellFail = "{prefix} &7Der er ikke noget i denne sælgekiste.";
    private String upgradeSuccess = "{prefix} &7Du opgraderede &e{amount}x tier {tier} generator&7 for &a${money}&7.";
    private String upgradeFail = "{prefix} &7Du mangler &c${money}&7.";
    private String upgradeMax = "{prefix} &7Du har den bedste generator tier.";
    private String notYourGenerator = "{prefix} &7Du ejer ikke denne generator. ({owner})";
    private String removeFromStack = "{prefix} &7Du fjernede &e{amount}x generator &7fra stacken. &7({gens} i alt)";
    private String notEnoughInventorySpace = "{prefix} &7Du har ikke nok inventory plads.";
    private String removeStack = "{prefix} &7Du fjernede en generator stack.";
}