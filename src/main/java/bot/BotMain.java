package bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BotMain {
    private static final Logger logger = LogManager.getLogger(BotMain.class);
    public static void main(String[] args) {
        try {
            logger.info("Log file found!");
            JDA myBot = new JDABuilder(BotConfig.BOT_TOKEN).addEventListeners(new TextListener()).build();
            myBot.awaitReady();
            myBot.setAutoReconnect(true);

            logger.info("Bot has started!");
            myBot.getPresence().setActivity(Activity.watching("Genie push to 30k"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
