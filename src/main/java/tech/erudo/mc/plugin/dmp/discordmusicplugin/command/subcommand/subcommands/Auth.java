package tech.erudo.mc.plugin.dmp.discordmusicplugin.command.subcommand.subcommands;

import org.bukkit.entity.Player;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.DiscordMusicAPI;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication.AuthManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication.AuthPlayer;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.command.subcommand.SubCommand;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.DiscordClient;

public class Auth implements SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        DiscordClient client = DiscordMusicAPI.getInstance().getClient();

        if(client.isLogin()) {
            for(String code : AuthManager.authCode.keySet()) {
                if(args[0].equals(code)) {
                    player.sendMessage("Auth Complete!!");

                    AuthPlayer authPlayer = new AuthPlayer(player.getUniqueId(), AuthManager.authCode.get(code));
                    AuthManager.authPlayers.add(authPlayer);
                }
            }
        }
    }

    @Override
    public String name() {
        return "auth";
    }

    @Override
    public String help() {
        return "authentication";
    }
}
