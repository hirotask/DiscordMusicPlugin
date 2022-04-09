package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord;

import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.JDAImpl;
import org.jetbrains.annotations.NotNull;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.DiscordMusicAPI;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.DiscordConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.CommandManager;

public class DiscordListener extends ListenerAdapter {

    private final CommandManager manager = new CommandManager();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.printf("%s is Ready!", DiscordClient.BOT_NAME);
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        DiscordConfig config = DiscordMusicAPI.getInstance().getDiscordConfig();

        User user = event.getAuthor();

        if(user.isBot() || event.isWebhookMessage()) {
            return;
        }

        String prefix = config.getPrefix();
        String raw = event.getMessage().getContentRaw();

        if(raw.equalsIgnoreCase(prefix + "admin")
                && event.getAuthor().getId().equals(config.getOwner_id())) {
            TextChannel channel = event.getChannel();
            channel.sendMessage("Hello" + event.getAuthor().getName()).queue();

            return;
        }

        if(raw.startsWith(prefix)) {
            manager.handle(event);
        }
    }
}
