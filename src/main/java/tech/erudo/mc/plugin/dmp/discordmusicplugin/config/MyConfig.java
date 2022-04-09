package tech.erudo.mc.plugin.dmp.discordmusicplugin.config;

import lombok.Getter;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class MyConfig extends CustomConfig {

    private final String channel;

    public MyConfig(JavaPlugin plugin) {
        super(plugin);

        this.channel = this.getConfig().getString("BOT_CHANNEL_ID");
    }


}
