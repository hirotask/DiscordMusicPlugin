package tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication;

import lombok.Data;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/* マイクラのプレイヤーと、認証情報を紐づけするためのクラス */
@Data
public class AuthPlayer {
    private final Member member;
    private final UUID uuid;
    private long userId;

    public AuthPlayer(UUID uuid, Member member) {
        this.member = member;
        this.uuid = uuid;
        this.userId = member.getIdLong();
    }

    public Player getPlayer() {
        return Bukkit.getServer().getPlayer(uuid);
    }

    public void mute(boolean muted) {
        this.member.mute(muted).queue();
    }


}