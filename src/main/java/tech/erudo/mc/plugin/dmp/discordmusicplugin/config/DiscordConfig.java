package tech.erudo.mc.plugin.dmp.discordmusicplugin.config;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class DiscordConfig extends CustomConfig {

    public final String token;
    public final String owner_id;
    public final String prefix;
    private boolean usingDB;

    public DiscordConfig(JavaPlugin plugin) {
        super(plugin, "discord.yml");

        this.token = this.getConfig().getString("TOKEN");
        this.owner_id = this.getConfig().getString("OWNER_ID");
        this.prefix = this.getConfig().getString("PREFIX");
        this.usingDB = this.getConfig().getBoolean("USING_DB");

    }
}