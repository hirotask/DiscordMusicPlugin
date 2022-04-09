package tech.erudo.mc.plugin.dmp.discordmusicplugin;

import org.bukkit.plugin.java.JavaPlugin;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.DiscordConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.DiscordClient;

public final class DiscordMusicPlugin extends JavaPlugin {

    private DiscordClient client;

    @Override
    public void onEnable() {
        DiscordConfig token = new DiscordConfig(this);
        client = new DiscordClient(token.getToken());

    }

    @Override
    public void onDisable() {
        client.shutdown();
    }
}
