package eu.imshizu.generator;

import eu.imshizu.generator.database.StoreManager;
import eu.okaeri.platform.bukkit.OkaeriBukkitPlugin;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.SneakyThrows;

@Scan(exclusions = "eu.imshizu.generator.libs.*", deep = true)
public final class Generator extends OkaeriBukkitPlugin {

    @SneakyThrows
    @Planned(ExecutionPhase.SHUTDOWN)
    public void shutdownStores(StoreManager stores) {
        stores.disconnect();
    }
}