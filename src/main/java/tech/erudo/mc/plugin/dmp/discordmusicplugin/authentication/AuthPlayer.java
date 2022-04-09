package tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication;

import lombok.Data;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.entity.Player;

/* マイクラのプレイヤーと、認証情報を紐づけするためのクラス */
@Data
public class AuthPlayer {

    private final Player player;
    private final Member member;

    public AuthPlayer(Player player, Member member) {
        this.player = player;
        this.member = member;
    }

    public void mute(boolean muted) {
        this.member.mute(muted).queue();
    }


}