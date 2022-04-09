package tech.erudo.mc.plugin.dmp.discordmusicplugin.command.subcommand;

import org.bukkit.entity.Player;

import java.util.List;

public interface SubCommand {

    void onCommand(Player player, String[] args);

    String name();

    String help();

    default List<String> aliases() {
        return List.of();
    }

}
