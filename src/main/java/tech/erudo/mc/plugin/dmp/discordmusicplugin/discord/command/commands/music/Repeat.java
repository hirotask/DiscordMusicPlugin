package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.commands.music;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.CommandContext;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.ICommand;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.lavaplayer.GuildMusicManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.lavaplayer.PlayerManager;

public class Repeat implements ICommand {

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

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final boolean newRepeating = musicManager.scheduler.repeating;

        musicManager.scheduler.repeating = newRepeating;

        channel.sendMessageFormat("The player has been set to **%s**", newRepeating ? "repeating" : "not repeating").queue();

    }

    @Override
    public String getName() {
        return "repeat";
    }

    @Override
    public String getHelp() {
        return "loops the current song";
    }
}
