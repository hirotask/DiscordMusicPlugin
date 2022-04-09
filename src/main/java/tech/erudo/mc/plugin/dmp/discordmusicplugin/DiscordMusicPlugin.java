package tech.erudo.mc.plugin.dmp.discordmusicplugin;

import org.bukkit.plugin.java.JavaPlugin;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication.AuthManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.command.CommandManager;

public final class DiscordMusicPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        DiscordMusicAPI.setInstance(this);

        this.getServer().getPluginCommand(CommandManager.mainCommand).setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        DiscordMusicAPI.getInstance().getClient().shutdown();
        AuthManager.save();
    }
}
