package tech.erudo.mc.plugin.dmp.discordmusicplugin;

import org.bukkit.plugin.java.JavaPlugin;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.DiscordConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.DiscordClient;

public final class DiscordMusicPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        DiscordMusicAPI.setInstance(this);
    }

    @Override
    public void onDisable() {
        DiscordMusicAPI.getInstance().getClient().shutdown();

    }
}
