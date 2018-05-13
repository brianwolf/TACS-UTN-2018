package ar.utn.tacs.telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Telegram {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		try {
			new TelegramBotsApi().registerBot(new TACSCryptoBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
