package eu.altshizu.generator.events;

import eu.altshizu.generator.configs.LangConfig;
import eu.altshizu.generator.database.StoreManager;
import eu.altshizu.generator.objects.User;
import eu.altshizu.generator.utils.Item;
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
        int tier = Item.getTier(item);
        if(!event.canBuild()) return;

        if (user.getUsedSlots() >= user.getSlots()) {
            i18n.get(langConfig.getMaxGenerators()).sendTo(player);
            event.setCancelled(true);
        } else {
            if (stores.getUserStore().hasGeneratorOfTier(user, tier)) {
                event.setCancelled(true);
            }
            stores.getUserStore().addGenerator(user, tier, event.getBlock().getLocation());
            i18n.get(langConfig.getPlacedGenerator()).with("tier", tier).sendTo(player);
        }
    }
}