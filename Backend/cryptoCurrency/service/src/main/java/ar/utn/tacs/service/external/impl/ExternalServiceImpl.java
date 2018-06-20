package ar.utn.tacs.service.external.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import ar.utn.tacs.dao.external.ExternalDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.coin.UpdatedCoinsPrice;
import ar.utn.tacs.model.email.Mail;
import ar.utn.tacs.service.external.ExternalService;
import ar.utn.tacs.util.RequestMaker;

public class ExternalServiceImpl implements ExternalService {

	@SuppressWarnings("unused")
	@Autowired
	private ExternalDao externalDao;

	private Gson gson = new Gson();

	@Override
	public List<Coin> coinMarketCap() {
		return UpdatedCoinsPrice.getCoinsList();
	}

	@Override
	public void updateCoinMarketCap() {
		String response = RequestMaker.makeRequest(RequestMaker.GET_METHOD, null, UpdatedCoinsPrice.COIN_MARKET_CAP_URL);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> mapResult = gson.fromJson(response, List.class);

		CoinBuilder coinBuilder = new CoinBuilder();
		UpdatedCoinsPrice.setCoinsList(coinBuilder.createCoinList(mapResult));
	}

	@Override
	public Coin getCoinByName(String name) {
		return UpdatedCoinsPrice.getCoinsList().stream().filter(coin -> coin.getName().equals(name)).findFirst().get();
	}

	@Override
	public Coin getCoinByTicker(String ticker) {
		return UpdatedCoinsPrice.getCoinsList().stream().filter(coin -> coin.getTicker().equals(ticker)).findFirst().get();
	}

	@Override
	public Boolean sendMail(Mail mail) {

		final String username = mail.getSenderUsername();
		final String password = mail.getSenderPassword();

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.setProperty("mail.smtp.host", "smpt.host");
		props.put("mail.smtp.starttls.enable", "true");
//		props.setProperty("mail.smtp.starttls.enable", "false");
		props.setProperty("mail.smtp.port", "25");
//		props.put("mail.smtp.port", "587");
		props.setProperty("mail.smtp.user", "alerts");
//		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);

//
//		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
			message.setSubject(mail.getSubject());
			message.setText(mail.getBody());

//			Transport.send(message);
			Transport t = session.getTransport("smtp");
			t.connect(username, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
