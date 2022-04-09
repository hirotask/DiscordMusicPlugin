package tech.erudo.mc.plugin.dmp.discordmusicplugin;

import lombok.Getter;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication.AuthManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.DataConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.DiscordConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.MyConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.DiscordClient;

public enum DiscordMusicAPI {

    INSTANCE;

    private DiscordMusicPlugin plugin;

    private DiscordConfig discordConfig;
    private MyConfig myConfig;
    private DataConfig dataConfig;

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

        if(INSTANCE.myConfig == null) {
            INSTANCE.myConfig = new MyConfig(INSTANCE.plugin);
        }

        if(INSTANCE.dataConfig == null) {
            INSTANCE.dataConfig = new DataConfig(INSTANCE.plugin);
        }

        if(INSTANCE.client == null) {
            INSTANCE.client = new DiscordClient(INSTANCE.discordConfig.getToken());
        }

    }

    public DiscordClient getClient() {
        return INSTANCE.client;
    }

    public MyConfig getConfig() {
        return INSTANCE.myConfig;
    }

    public DataConfig getDataConfig() {
        return INSTANCE.dataConfig;
    }

    public DiscordConfig getDiscordConfig() {
        return INSTANCE.discordConfig;
    }

}
