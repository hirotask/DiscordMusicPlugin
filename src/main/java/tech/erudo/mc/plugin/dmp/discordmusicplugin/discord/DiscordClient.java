package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DiscordClient {

    public static final String BOT_NAME = "DiscordMusicBot";

    private JDA jda;

    public DiscordClient(String token) {
        try {
            jda = JDABuilder
                    .createDefault(token)
                    .addEventListeners(new DiscordListener())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }


    }

}
