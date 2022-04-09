package tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.authentication.AuthManager;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.CommandContext;
import tech.erudo.mc.plugin.dmp.discordmusicplugin.discord.command.ICommand;

public class Auth implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member member = ctx.getMember();
        final User user = member.getUser();
        String code = AuthManager.generateAuthCode(member);

        try {
            user.openPrivateChannel().complete()
                    .sendMessage("Your auth code is " + code).queue();
        } catch(ErrorResponseException ex) {
            channel.sendMessage("I can not open your private channel").queue();
        }
    }

    @Override
    public String getName() {
        return "auth";
    }

    @Override
    public String getHelp() {
        return "send you an authentication code";
    }
}
