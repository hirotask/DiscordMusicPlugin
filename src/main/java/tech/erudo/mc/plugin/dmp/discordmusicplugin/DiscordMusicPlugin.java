package tech.erudo.mc.plugin.dmp.discordmusicplugin;

import org.bukkit.plugin.java.JavaPlugin;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.TokenConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.DiscordClient;

public final class DiscordMusicPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        TokenConfig token = new TokenConfig(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
