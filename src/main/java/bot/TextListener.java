package bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class TextListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //Don't respond to bots.
        if (event.getAuthor().isBot()) {
            return;
        }

        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();

        //If it isn't a command, return.
        if (content.isEmpty() || !content.substring(0, 1).equals(BotConfig.PREFIX)) {
            return;
        }

        content = content.substring(1).trim();
        ArrayList<String> args = new ArrayList<>(Arrays.asList(content.split(" ")));

        switch (args.get(0)) {
            case "clubs" -> {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setAuthor("NA Peak Bot", null, "https://cdn.discordapp.com/icons/700098739326418964/2b7ab40fe5227f9545222d720d36de3c.png?size=128");
                embed.setTitle("Clubs");
                embed.addField("NA Peak", "#VUYRGGUR\n" + "Required Trophies: " + APIWrangler.getRequiredTrophies("#VUYRGGUR") + "+\n" + "Total Trophies: " + APIWrangler.getClubTrophies("#VUYRGGUR"), false);
                embed.addField("NA Elevate", "Coming Soon", false);
                embed.setTimestamp(Instant.now());
                embed.setFooter("best club NA");
                channel.sendMessage(embed.build()).queue();
            }
            case "b2b" -> {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("NA Peak > B2B");
                channel.sendMessage(embed.build()).queue();
            }
        }
    }
}
