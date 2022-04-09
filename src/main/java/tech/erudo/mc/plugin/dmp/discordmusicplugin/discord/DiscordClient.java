package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.DiscordMusicAPI;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class DiscordClient {

    public static final String BOT_NAME = "DiscordMusicBot";

    @Getter
    private JDA jda;

    public DiscordClient(String token) {
        try {
            jda = JDABuilder
                    .createDefault(
                            token,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_VOICE_STATES
                    )
                    .disableCache(EnumSet.of(
                            CacheFlag.CLIENT_STATUS,
                            CacheFlag.ACTIVITY,
                            CacheFlag.EMOTE
                    ))
                    .enableCache(EnumSet.of(
                            CacheFlag.VOICE_STATE
                    ))
                    .addEventListeners(new DiscordListener())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

    public boolean isLogin() {
        return jda != null;
    }

    public void shutdown() {
        jda.shutdownNow();
    }

}
