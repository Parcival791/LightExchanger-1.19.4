package net.parcival.lightexchanger;

import net.parcival.lightexchanger.command.ExchangeLightCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class LightExchanger extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("exchangelight").setExecutor(new ExchangeLightCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
