package tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication;

import net.dv8tion.jda.api.entities.Member;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class AuthManager {

    public static HashMap<String, Member> authCode = new HashMap<>();

    public static List<AuthPlayer> authPlayers = new ArrayList<>();

    public static void init() {
        
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
