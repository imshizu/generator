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
    private String noPermission = "&6[Generator] &7Du har ikke adgang til denne kommando.";

    /**
     * Default & default admin message for the plugin is stored here.
     */
    private String defaultMessage =
            "&6[Generator]\n" +
            "&7/gen list &fviser alle generators\n" +
            "&7/gen shop &fåbner shop med generators\n";

    private String adminMessage =
            "&7/gen tilføj &ftilføjer en generator\n" +
            "&7/gen fjern <tier> &ffjerner en generator\n" +
            "&7/gen giv <tier> &fgiver en bestemt generator\n" +
            "&7/gen reload &fgenindlæser pluginnet\n";

    /**
     * Messages for the generator command is stored here.
     */
    private String addGenerator = "&6[Generator] &7Du tilføjede en ny generator. (Tier {tier})";
    private String holdItem = "&6[Generator] &7Du skal holde et item i hånden.";
    private String removeGenerator = "&6[Generator] &7Du fjernede &e{tier} &7generator.";
    private String generatorNotFound = "&6[Generator] &7Denne generator findes ikke.";
    private String receiveGenerator = "&6[Generator] Du modtog &e{tier} &7generator.";

    /**
     * Messages for the generator shop is stored here.
     */
    private String buyGenerator = "&6[Generator] &7Du købte en &etier {tier} generator&7.";
    private String notEnoughMoney = "&6[Generator] &7Du mangler &c${money}&7.";

    /**
     * Messages for the generator events is stored here.
     */
    private String placedSellchest = "&6[Generator] &7Du placerede en &esælgekiste&7.";
    private String addedToStack = "&6[Generator] &7Du tilføjede en generator til stacken. ({amount} i alt)";
    private String placedGenerator = "&6[Generator] &7Du placerede en &etier {tier} generator&7.";
    private String maxGenerators = "&6[Generator] &7Du har ikke plads til flere generators.";
    private String sellSuccess = "&6[Generator] &7Du indsamlet &a${money} &7& &b{xp} XP&7.";
    private String sellFail = "&6[Generator] &7Der er ikke noget i denne sælgekiste.";
    private String upgradeSuccess = "&6[Generator] &7Du opgraderet &e{amount}x tier {tier} generator&7 for &a${money}&7.";
    private String upgradeFail = "&6[Generator] &7Du mangler &c${money}&7.";
    private String upgradeMax = "&6[Generator] &7Du har den bedste generator tier.";
    private String notYourGenerator = "&6[Generator] &7Du ejer ikke denne generator. ({owner})";
    private String removeFromStack = "&6[Generator] &7Du fjernede &e{amount}x generator &7fra stacken. &7({gens} i alt)";
    private String notEnoughInventorySpace = "&6[Generator] &7Du har ikke nok inventory plads.";
    private String removeStack = "&6[Generator] &7Du fjernede en generator stack.";
}