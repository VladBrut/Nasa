package org.example;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        new NasaTelegramBot("Nasa_picture", "7097710463:AAGh9uKSsJAZGfhtZr_Xg7if27ai5tgh9Rs");
    }
}