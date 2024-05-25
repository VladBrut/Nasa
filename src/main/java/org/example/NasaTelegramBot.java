package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class NasaTelegramBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final String URL_NASA = "https://api.nasa.gov/planetary/apod?api_key=417ddI0GA9YqnpUyKqEUFCb3SR8tcDBwariQo6ng";

    public NasaTelegramBot(String botName, String botToken) throws TelegramApiException {
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            String answer = update.getMessage().getText();
            String[] separatedAnswer = answer.split(" ");
            String action = separatedAnswer[0];

            switch (action) {
                case "/start":
                    sendMessage("I'm NASA bot. I send a picture of the day.", chat_id);
                    break;
                case "/help":
                    sendMessage("Write /image to receive today's image.", chat_id);
                    break;
                case "/image":
                    String image = Utils.getUrl(URL_NASA);
                    sendMessage(image, chat_id);
                    break;
                case "/date":
                    String date = separatedAnswer[1];
                    image = Utils.getUrl(URL_NASA + "&date=" + date);
                    sendMessage(image, chat_id);
                    break;
                default:
                    sendMessage("I don't know what you mean.", chat_id);
                    break;
            }
        }
    }

    void sendMessage(String text, Long chat_id) {
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Message not sent");
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
