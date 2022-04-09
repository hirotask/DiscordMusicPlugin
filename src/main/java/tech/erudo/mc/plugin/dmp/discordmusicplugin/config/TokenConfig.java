package tech.erudo.mc.plugin.dmp.discordmusicplugin.config;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class TokenConfig extends CustomConfig {

    public final String token;

    public TokenConfig(JavaPlugin plugin) {
        super(plugin, "token.yml");

        this.token = this.getConfig().getString("TOKEN");
    }
}