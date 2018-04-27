package ar.utn.tacs.telegram;

import ar.utn.tacs.telegram.action.*;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;

import ar.utn.tacs.telegram.model.Quote;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

import org.springframework.web.client.RestTemplate;

public class TACSCryptoBot extends AbilityBot  {
	
	public static final String BOT_TOKEN = "528360666:AAE215nVkyXqXnOOYt9MJlaMm8o8JxWnyL0";
	public static final String BOT_USERNAME = "TACSCryptoBot";
	public static final int BOT_CREATORID = 521791773;

	public TACSCryptoBot() {
		super(BOT_TOKEN, BOT_USERNAME);
	}

	@Override
	public int creatorId() {
		return BOT_CREATORID;
	}

	public Ability me() {
		return Ability
				.builder()
				.name("me")
				.info("Nombre del contacto.")
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(ctx.update().getMessage().getFrom().getFirstName()+
						" "+ctx.update().getMessage().getFrom().getLastName(), ctx.chatId()))
				.build();
	}
	
	public Ability login() {
		return Ability
				.builder()
				.name("login")
				.info("Autenticación con <user> <pass>")
				.input(2)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(Login.exec(ctx), ctx.chatId()))
				.build();
	}
	
	/* Como usuario quiero poder registrar una transacción (moneda, cantidad comprada / vendida) usando Telegram */
	public Ability buy() {
		return Ability
				.builder()
				.name("buy")
				.info("Compra <coin-id> <quantity>")
				.input(2)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(Buy.exec(ctx), ctx.chatId()))
				.build();
	}

	public Ability sell() {
		return Ability
				.builder()
				.name("sell")
				.info("Vende <coin-id> <quantity>")
				.input(2)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(Sell.exec(ctx), ctx.chatId()))
				.build();
	}
	
	/* Como usuario quiero ver la cantidad de una criptomoneda que poseo a través de Telegram. */
	public Ability coin() {
		return Ability
				.builder()
				.name("coin")
				.info("Cantidad de <coin-id>")
				.input(1)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(Amount.exec(ctx), ctx.chatId()))
				.build();
	}
	
	/* Como usuario quiero saber la cotización actual de una critpomoneda desde Telegram. */
	public Ability quote() {
		return Ability
				.builder()
				.name("quote")
				.info("Cotización de <coin-id>")
				.input(1)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(ActualQuote.exec(ctx), ctx.chatId()))
				.build();
	}

	/* COMANDO DE PRUEBA /cita */
	public Ability citaDelDia() {
        Quote quote = new RestTemplate()
        				.getForObject("http://gturnquist-quoters.cfapps.io/api/random",
        						Quote.class);
		return Ability
				.builder()
				.name("cita")
				.info("Cita Random del dia")
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(quote.getValue().getQuote(), ctx.chatId()))
				.build();
	}

}
