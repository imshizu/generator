package eu.imshizu.generator.events;

import eu.imshizu.generator.configs.LangConfig;
import eu.imshizu.generator.database.StoreManager;
import eu.imshizu.generator.objects.User;
import eu.imshizu.generator.utils.Utilities;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.i18n.BI18n;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

@Component
public class GeneratorPlaceEvent implements Listener {
    private @Inject StoreManager stores;
    private @Inject("lang") BI18n i18n;
    private @Inject LangConfig langConfig;

    @EventHandler
    public void onGeneratorPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        Player player = event.getPlayer();
        User user = stores.getUserStore().getUser(player);
        int tier = Utilities.getTier(item);

        if (!event.canBuild()) return;
        if (tier == -1) return;

        if (user.getUsedSlots() >= user.getSlots()) {
            i18n.get(langConfig.getMaxGenerators())
                    .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                    .sendTo(player);
            event.setCancelled(true);
        } else {
            boolean hasGeneratorOfTier = stores.getUserStore().hasGeneratorOfTier(user, tier);

            if (!hasGeneratorOfTier) {
                stores.getUserStore().addGenerator(user, tier, event.getBlock().getLocation());
                i18n.get(langConfig.getPlacedGenerator())
                        .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                        .with("tier", tier).sendTo(player);
            } else {
                stores.getUserStore().addGenerator(user, tier, event.getBlock().getLocation());
                event.setCancelled(true);
                Utilities.removeItems(player, item.getType(), 1);
                i18n.get(langConfig.getAddedToStack())
                        .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                        .with("amount", stores.getUserStore().getAmount(user, tier))
                        .sendTo(player);
            }
        }
    }
}
