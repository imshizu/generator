package eu.imshizu.generator.events;

import eu.imshizu.generator.configs.LangConfig;
import eu.imshizu.generator.database.StoreManager;
import eu.imshizu.generator.objects.Generator;
import eu.imshizu.generator.objects.Global;
import eu.imshizu.generator.objects.User;
import eu.imshizu.generator.utils.Utilities;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.i18n.BI18n;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@Component
public class GeneratorRemoveEvent implements Listener {
    private @Inject StoreManager stores;
    private @Inject("lang") BI18n i18n;
    private @Inject LangConfig langConfig;

    @EventHandler
    public void onGeneratorRemove(PlayerInteractEvent event) {
        if(!(event.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        if(event.getClickedBlock() == null) return;

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

        int amount = player.isSneaking() ? 64 : 1;
        amount = Math.min(amount, generator.getAmount());

        Global global = stores.getGeneratorStore().getGeneratorByTier(generator.getTier());

        if(!Utilities.canPlayerHold(player, global.getItem(), amount)) {
            i18n.get(langConfig.getNotEnoughInventorySpace())
                    .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                    .sendTo(player);
            return;
        }

        if (amount < generator.getAmount()) {
            event.setCancelled(true);
            i18n.get(langConfig.getRemoveFromStack())
                    .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                    .with("amount", amount)
                    .with("gens", generator.getAmount() - amount)
                    .sendTo(player);
        } else {
            i18n.get(langConfig.getRemoveStack())
                    .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                    .with("gens", generator.getAmount() - amount)
                    .sendTo(player);

            event.getClickedBlock().setType(Material.AIR);
        }

        stores.getUserStore().updateData(user, "usedSlots", -amount);
        stores.getGeneratorStore().delete(generator, amount);

        ItemStack item = global.getItem().clone();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }
}
