package tech.erudo.mc.plugin.dmp.discordmusicplugin;

import lombok.Getter;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.DiscordConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.DiscordClient;

public enum DiscordMusicAPI {

    INSTANCE;

    private DiscordMusicPlugin plugin;

    private DiscordConfig discordConfig;
    private DiscordClient client;

    public static DiscordMusicAPI getInstance() {
        return INSTANCE;
    }

    public static void setInstance(DiscordMusicPlugin plugin) {
        if(INSTANCE.plugin == null) {
            INSTANCE.plugin = plugin;
        }

        if(INSTANCE.discordConfig == null) {
            INSTANCE.discordConfig = new DiscordConfig(INSTANCE.plugin);
        }

        if(INSTANCE.client == null) {
            INSTANCE.client = new DiscordClient(INSTANCE.discordConfig.getToken());
        }

    }

    public DiscordClient getClient() {
        return INSTANCE.client;
    }

    public DiscordConfig getDiscordConfig() {
        return INSTANCE.discordConfig;
    }

}
