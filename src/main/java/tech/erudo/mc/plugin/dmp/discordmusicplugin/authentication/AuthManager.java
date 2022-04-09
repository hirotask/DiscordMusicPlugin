package tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.DiscordMusicAPI;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.DataConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.DiscordConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.MyConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.commands.Auth;

import java.util.*;

public class AuthManager {

    public static HashMap<String, Member> authCode = new HashMap<>();

    public static List<AuthPlayer> authPlayers = new ArrayList<>();

    public static void init(Guild guild) {
        MyConfig myConfig = DiscordMusicAPI.getInstance().getConfig();
        if(myConfig.isUsingDB()) {

        } else {
            DataConfig dataConfig = DiscordMusicAPI.getInstance().getDataConfig();

            if (dataConfig.getConfig().getConfigurationSection("players") == null) {
                return;
            }

            for(String key : dataConfig.getConfig().getConfigurationSection("players").getKeys(false)) {
                String id = dataConfig.getConfig().getString("players." + key);

                if(guild == null) {
                    System.out.println("guild is null");
                    return;
                }

                Member member = guild.getMemberById(id);

                System.out.printf("UUID: %s, MemberID: %s", key, id);

                if(key != null && member != null) {
                    AuthPlayer authPlayer = new AuthPlayer(UUID.fromString(key),member);
                    AuthManager.authPlayers.add(authPlayer);
                }
            }
        }
    }

    public static void save() {
        MyConfig myConfig = DiscordMusicAPI.getInstance().getConfig();
        if(myConfig.isUsingDB()) {

        } else {
            for(AuthPlayer authPlayer : authPlayers) {
                DataConfig dataConfig = DiscordMusicAPI.getInstance().getDataConfig();
                dataConfig.getConfig().set("players." + authPlayer.getUuid().toString(), authPlayer.getUserId());
                dataConfig.saveConfig();
                dataConfig.saveDefaultConfig();
            }
        }
    }

    public static String generateAuthCode(Member member) {
        Random rand = new Random();
        String code = String.format("%04d", rand.nextInt(10000));

        if(authCode.containsKey(code)) {
            return generateAuthCode(member);
        } else {
            authCode.put(code, member);
            return code;
        }
    }

    public static boolean containsAuthPlayer(Player player) {
        for(AuthPlayer authPlayer : authPlayers) {
            if(authPlayer.getPlayer().getUniqueId().equals(player.getUniqueId()) ) {
                return true;
            }
        }

        return false;
    }

    public static AuthPlayer getAuthPlayer(Player player) {
        for(AuthPlayer authPlayer : authPlayers) {
            if(authPlayer.getPlayer().getUniqueId().equals(player.getUniqueId()) ) {
                return authPlayer;
            }
        }

        return null;
    }

}
