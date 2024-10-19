package eu.altshizu.generator.events;

import eu.altshizu.generator.configs.LangConfig;
import eu.altshizu.generator.database.StoreManager;
import eu.altshizu.generator.objects.User;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.i18n.BI18n;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

@Component
public class GeneratorPlaceEvent implements Listener {
    private @Inject StoreManager stores;
    private @Inject("lang") BI18n i18n;
    private @Inject LangConfig langConfig;

    @EventHandler
    public void onGeneratorPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        User user = stores.getUserStore().getUser(player);

        if (user.getUsedSlots() >= user.getSlots()) {
            i18n.get(langConfig.getMaxGenerators())
                    .sendTo(player);
            event.setCancelled(true);
            return;
        }

        i18n.get(langConfig.getPlacedGenerator())
                .with("tier", 1)
                .sendTo(player);
        Location location = event.getBlock().getLocation();

        if (stores.getUserStore().hasGeneratorOfTier(user, 1)) {
            stores.getUserStore().addGenerator(user, 1, location);
            event.setCancelled(true);
            return;
        }

        stores.getUserStore().addGenerator(user, 1, location);
    }
}
