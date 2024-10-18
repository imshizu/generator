package eu.altshizu.generator;

import eu.okaeri.platform.bukkit.OkaeriBukkitPlugin;
import eu.okaeri.platform.core.annotation.Scan;

@Scan(exclusions = "eu.altshizu.generator.libs.*", deep = true)
public final class Generator extends OkaeriBukkitPlugin {
}