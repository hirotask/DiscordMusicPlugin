package tech.erudo.mc.plugin.dmp.discordmusicplugin.command.subcommand.subcommands;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;
import org.bukkit.entity.Player;
import org.w3c.dom.Text;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.DiscordMusicAPI;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication.AuthManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication.AuthPlayer;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.command.subcommand.SubCommand;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.lavaplayer.PlayerManager;

import java.net.URI;
import java.net.URISyntaxException;

public class Play implements SubCommand {
    @SuppressWarnings("ConstantConditions")
    @Override
    public void onCommand(Player player, String[] args) {
        if(args.length > 0) {
            AuthPlayer authPlayer = AuthManager.getAuthPlayer(player);

            if(authPlayer == null) {
                player.sendMessage("認証を完了してください");
                return;
            }

            final String channelStr = DiscordMusicAPI.getInstance().getDiscordConfig().getChannel();

            final Member member = authPlayer.getMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();
            final Guild guild = member.getGuild();

            final TextChannel channel = (TextChannel) member.getGuild().getGuildChannelById(channelStr);

            final Member self = guild.getSelfMember();
            final GuildVoiceState selfVoiceState = self.getVoiceState();

            if(channel == null) {
                player.sendMessage("設定したチャンネルは存在しません");
                return;
            }

            String link = args[0];

            if(!isUrl(link)) {
                link = "ytsearch:" + link;
            }

            if(!selfVoiceState.inVoiceChannel()) {
                if(memberVoiceState.inVoiceChannel()) {
                    final AudioManager audioManager = guild.getAudioManager();
                    final VoiceChannel memberChannel = memberVoiceState.getChannel();

                    audioManager.openAudioConnection(memberChannel);
                    player.sendMessage("Connecting to " + memberChannel.getName());
                    channel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();

                } else {
                    player.sendMessage("You need to be in a voice channel for this command to work");
                    channel.sendMessage("You need to be in a voice channel for this command to work").queue();

                    return;
                }

            } else {
                if(!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
                    player.sendMessage("You need to be in the same voice channel as me for this to work");
                    channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();

                    return;
                }
            }

            PlayerManager.getInstance().loadAndPlay(channel, link);

        } else {
            player.sendMessage("引数が足りません");
        }
    }

    @Override
    public String name() {
        return "play";
    }

    @Override
    public String help() {
        return "play the songs";
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
