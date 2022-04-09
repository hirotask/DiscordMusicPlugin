package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.CommandContext;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.ICommand;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.lavaplayer.GuildMusicManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.lavaplayer.PlayerManager;

public class Leave implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!selfVoiceState.inVoiceChannel()) {
            if(!memberVoiceState.inVoiceChannel()) {
                channel.sendMessage("You need to be in a voice channel for this command to work").queue();
                return;
            }
        } else {
            if(!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
                channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
                return;
            }
        }

        final Guild guild = ctx.getGuild();

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);

        musicManager.scheduler.repeating = false;
        musicManager.scheduler.queue.clear();
        musicManager.audioPlayer.stopTrack();

        final AudioManager audioManager = ctx.getGuild().getAudioManager();

        audioManager.closeAudioConnection();
        channel.sendMessage("I have left the voice channel").queue();
    }

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getHelp() {
        return "leave the voice channel that the bot is in";
    }
}
