package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.commands;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.CommandContext;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.ICommand;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.lavaplayer.PlayerManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Play implements ICommand {

    @SuppressWarnings("ConstantConditions")
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if(ctx.getArgs().size() < 1 || ctx.getArgs().isEmpty()) {
            channel.sendMessage("Missing Arguments").queue();
            return;
        }

        String link = String.join(" ", ctx.getArgs());

        if(!isUrl(link)) {
            link = "ytsearch:" + link;
        }

        if(!selfVoiceState.inVoiceChannel()) {
            if(memberVoiceState.inVoiceChannel()) {
                final AudioManager audioManager = ctx.getGuild().getAudioManager();
                final VoiceChannel memberChannel = memberVoiceState.getChannel();

                audioManager.openAudioConnection(memberChannel);
                channel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();

            } else {
                channel.sendMessage("You need to be in a voice channel for this command to work").queue();
                return;
            }

        } else {
            if(!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
                channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
                return;
            }
        }

        PlayerManager.getInstance().loadAndPlay(channel, link);
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "Plays a song \n" +
                "Usage: `!play <youtube link>`";
    }

    private boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        }catch (URISyntaxException e) {
            return false;
        }
    }
}
