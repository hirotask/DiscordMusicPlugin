package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.commands;

import net.dv8tion.jda.api.JDA;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.CommandContext;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.ICommand;

public class Ping implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        jda.getRestPing().queue(
                ping -> ctx.getChannel()
                .sendMessageFormat("Reset ping: %s ms\nWS ping: %s ms", ping, jda.getGatewayPing()).queue()
        );
    }

    @Override
    public String getName() {
        return "ping";
    }
}
