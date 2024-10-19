package eu.altshizu.generator.events;

import eu.altshizu.generator.database.StoreManager;
import eu.altshizu.generator.objects.User;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

@Component
public class GeneratorPlaceEvent implements Listener {
    private @Inject StoreManager stores;

    @EventHandler
    public void onGeneratorPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        User user = stores.getUserStore().getUser(player);

        if(user.getUsedSlots() >= user.getSlots()) {
            player.sendMessage("You have reached the maximum amount of generators you can place.");
            event.setCancelled(true);
            return;
        }

        stores.getUserStore().addGenerator(user, 1, event.getBlock().getLocation());
        player.sendMessage("Generator placed!");
    }
}
