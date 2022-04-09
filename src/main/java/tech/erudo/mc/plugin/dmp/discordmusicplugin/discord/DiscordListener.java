package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.printf("%s is Ready!", DiscordClient.BOT_NAME);
    }
}
