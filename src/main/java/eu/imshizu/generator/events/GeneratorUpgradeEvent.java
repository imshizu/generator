package eu.imshizu.generator.events;

import eu.imshizu.generator.configs.LangConfig;
import eu.imshizu.generator.database.StoreManager;
import eu.imshizu.generator.objects.Generator;
import eu.imshizu.generator.objects.Global;
import eu.imshizu.generator.objects.User;
import eu.imshizu.generator.utils.Vault;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.i18n.BI18n;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@Component
public class GeneratorUpgradeEvent implements Listener {
    private @Inject StoreManager stores;
    private @Inject("lang") BI18n i18n;
    private @Inject LangConfig langConfig;
    private @Inject Vault vault;

    @EventHandler
    public void onGeneratorUpgrade(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getClickedBlock() == null) return;

        Player player = event.getPlayer();
        User user = stores.getUserStore().getUser(player);

        Generator generator = stores.getGeneratorStore()
                .getGeneratorAtLocation(event.getClickedBlock().getLocation())
                .orElse(null);

        if(generator == null) return;
        if(!generator.getUser().getUuid().equals(user.getUuid())) {
            i18n.get(langConfig.getNotYourGenerator())
                    .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                    .with("owner", generator.getUser().getName())
                    .sendTo(player);
            return;
        }

        Global global = stores.getGeneratorStore().getGeneratorByTier(generator.getTier() + 1);

        if(global == null) {
            i18n.get(langConfig.getUpgradeMax())
                    .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                    .sendTo(player);
            return;
        }

        double price = stores.getGeneratorStore().getUpgradeCost(generator);

        if(!vault.has(player, price)) {
            i18n.get(langConfig.getUpgradeFail())
                    .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                    .with("money", price - vault.get(player))
                    .sendTo(player);
            return;
        }

        stores.getGeneratorStore().upgrade(generator);
        i18n.get(langConfig.getUpgradeSuccess())
                .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                .with("amount", generator.getAmount())
                .with("tier", generator.getTier())
                .with("money", price)
                .sendTo(player);

        vault.widthraw(player, price);
    }
}
