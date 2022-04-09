package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.HashMap;
import java.util.Map;

public enum PlayerManager {

    INSTANCE;

    private Map<Long, GuildMusicManager> musicManagers;
    private AudioPlayerManager audioPlayerManager;

    public static PlayerManager getInstance() {
        if(INSTANCE.musicManagers == null) {
            INSTANCE.musicManagers = new HashMap<>();
        }
        if(INSTANCE.audioPlayerManager == null) {
            INSTANCE.audioPlayerManager = new DefaultAudioPlayerManager();

            AudioSourceManagers.registerRemoteSources(INSTANCE.audioPlayerManager);
            AudioSourceManagers.registerLocalSource(INSTANCE.audioPlayerManager);
        }

        return INSTANCE;
    }

    public GuildMusicManager getMusicManager(Guild guild) {
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), guildId -> {
           final GuildMusicManager guildMusicManager = new GuildMusicManager((this.audioPlayerManager));

           guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

           return guildMusicManager;
        });
    }

    public void loadAndPlay(TextChannel channel, String trackUrl) {
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.scheduler.queue(track);

                channel.sendMessage("Adding to queue: `")
                        .append(track.getInfo().title)
                        .append("` by `")
                        .append(track.getInfo().author)
                        .queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

            }

            @Override
            public void noMatches() {

            }

            @Override
            public void loadFailed(FriendlyException exception) {

            }
        });
    }
}
