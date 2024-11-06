package eu.imshizu.generator.utils;

import eu.okaeri.injector.annotation.PostConstruct;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

@Component
public class Vault {
    @Getter
    private static Economy econ;

    @PostConstruct
    private boolean init() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public double get(OfflinePlayer player) {
        return econ.getBalance(player);
    }

    public void add(OfflinePlayer player, double amount) {
        econ.depositPlayer(player, amount);
    }

    public void widthraw(OfflinePlayer player, double amount) {
        econ.withdrawPlayer(player, amount);
    }

    public boolean has(OfflinePlayer player, double amount) {
        return econ.getBalance(player) >= amount;
    }
}
