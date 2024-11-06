package eu.imshizu.generator.commands;

import eu.imshizu.generator.configs.GensConfig;
import eu.imshizu.generator.configs.LangConfig;
import eu.okaeri.commands.annotation.*;
import eu.okaeri.commands.bukkit.annotation.Async;
import eu.okaeri.commands.bukkit.annotation.Permission;
import eu.okaeri.commands.bukkit.annotation.Sync;
import eu.okaeri.commands.service.CommandService;
import eu.okaeri.commands.service.Option;
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

    @Permission("generator.admin")
    @Executor(pattern = "give * ?") @Sync
    @Completion(arg = "target", value = "@bukkit:player:name")
    public void give(@Context Player player, @Arg("tier") int tier, @Arg("target") Option<Player> target) {
        Player targetPlayer = target.orElse(player);
        if(gensConfig.getGenerators().get(tier) == null) {
            i18n.get(langConfig.getGeneratorNotFound())
                    .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                    .sendTo(player);
            return;
        }

        targetPlayer.getInventory().addItem(gensConfig.getGenerators().get(tier).getItem());
        i18n.get(langConfig.getReceiveGenerator())
                .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                .with("tier", tier)
                .sendTo(targetPlayer);
        /*
            Detect if targetPlayer is the same as
            player, if not send a message to the player
         */
        if(targetPlayer == player) return;
        i18n.get(langConfig.getGaveGenerator())
                .with("prefix", i18n.get(player, langConfig.getPrefix()).apply())
                .with("tier", tier)
                .with("target", targetPlayer.getName())
                .sendTo(player);
    }
}