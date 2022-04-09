package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.commands;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.CommandContext;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.ICommand;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.lavaplayer.PlayerManager;

import java.util.List;

public class Play implements ICommand {

    @SuppressWarnings("ConstantConditions")
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        final List<String> args = ctx.getArgs();

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if(args.size() < 1) {
            channel.sendMessage("Missing Arguments").queue();
            return;
        }

        if(!selfVoiceState.inVoiceChannel()) {
            if(memberVoiceState.inVoiceChannel()) {
                final AudioManager audioManager = ctx.getGuild().getAudioManager();
                final VoiceChannel memberChannel = memberVoiceState.getChannel();

                audioManager.openAudioConnection(memberChannel);
                channel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();

                PlayerManager.getInstance().loadAndPlay(channel, args.get(0));
            } else {
                channel.sendMessage("You need to be in a voice channel for this command to work").queue();
            }

        } else {
            if(!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
                channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
            }
        }
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
}
