package appconfig;


import bot.work_bot.MyWorkTelegramBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;

    @Bean
    public MyWorkTelegramBot MyWorkTelegramBot() {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);

        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);

        MyWorkTelegramBot myWorkTelegramBot = new MyWorkTelegramBot(options);
        myWorkTelegramBot.setBotUserName(botUserName);
        myWorkTelegramBot.setBotToken(botToken);
        myWorkTelegramBot.setWebHookPath(webHookPath);

        return myWorkTelegramBot;
    }
}
