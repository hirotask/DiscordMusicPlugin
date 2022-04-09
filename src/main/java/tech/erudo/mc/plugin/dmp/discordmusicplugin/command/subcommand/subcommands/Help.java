package tech.erudo.mc.plugin.dmp.discordmusicplugin.command.subcommand.subcommands;

import org.bukkit.entity.Player;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.command.CommandManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.command.subcommand.SubCommand;

import java.util.ArrayList;
import java.util.List;

public class Help implements SubCommand {

    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if(args.length == 0) {
            List<String> msgs = new ArrayList<>();

            msgs.add("===== Commands =====");
            commandManager.getCommands().forEach(
                    it -> msgs.add("/" + it.name() + "➡" + it.help())
            );

            msgs.forEach(player::sendMessage);
            return;
        }

        String search = args[0];
        SubCommand subCommand = commandManager.getCommand(search);

        if(subCommand == null) {
            player.sendMessage("そのようなコマンドは存在しません");
            return;
        }

        player.sendMessage("/" + subCommand.name() + "➡" + subCommand.help());
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String help() {
        return "Show command help";
    }

    @Override
    public List<String> aliases() {
        return List.of("commands", "cmds", "commandList");
    }
}
