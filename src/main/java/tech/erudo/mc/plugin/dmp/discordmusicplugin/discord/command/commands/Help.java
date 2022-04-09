package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.commands;

import net.dv8tion.jda.api.entities.TextChannel;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.DiscordMusicAPI;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.config.DiscordConfig;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.CommandContext;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.CommandManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.ICommand;

import java.util.List;

public class Help implements ICommand {

    private final CommandManager manager;

    public Help(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        if(args.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            DiscordConfig config = DiscordMusicAPI.getInstance().getDiscordConfig();

            builder.append("List of commands \n");
            manager.getCommands().stream().map(ICommand::getName).forEach(
                    it -> builder.append('`').append(config.getPrefix()).append(it).append("`\n")
            );

            channel.sendMessage(builder.toString()).queue();
            return;
        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if(command == null) {
            channel.sendMessage("Nothing Found for " + search).queue();
            return;
        }

        channel.sendMessage(command.getName()+ " : " + command.getHelp()).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Show the list with commands in the bot \n" +
                "Usage: `!help [command]`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("commands", "cmds", "commandList");
    }
}
