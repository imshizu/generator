package eu.altshizu.generator.commands;

import eu.altshizu.generator.configs.GensConfig;
import eu.altshizu.generator.configs.LangConfig;
import eu.okaeri.commands.annotation.Command;
import eu.okaeri.commands.annotation.Context;
import eu.okaeri.commands.annotation.Executor;
import eu.okaeri.commands.bukkit.annotation.Async;
import eu.okaeri.commands.bukkit.annotation.Permission;
import eu.okaeri.commands.bukkit.annotation.Sync;
import eu.okaeri.commands.service.CommandService;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.i18n.BI18n;
import org.bukkit.entity.Player;

@Async
@Command(label = "gen", aliases = {"generator", "gens"})
public class GenCommand implements CommandService {
    private @Inject("lang") BI18n i18n;
    private @Inject LangConfig langConfig;
    private @Inject GensConfig gensConfig;

    @Executor
    public void __(@Context Player player) {
    }

    @Permission("generator.admin")
    @Executor(pattern = "reload") @Sync
    public void reload(@Context Player player) {
        long ms = System.currentTimeMillis();
        i18n.get(langConfig.getReloadStart())
                .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                .sendTo(player);
        i18n.load();
        gensConfig.load();
        i18n.get(langConfig.getReloadEnd())
                .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                .with("ms", System.currentTimeMillis() - ms)
                .sendTo(player);
    }
}