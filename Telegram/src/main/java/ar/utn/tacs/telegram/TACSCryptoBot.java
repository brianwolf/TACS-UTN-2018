package ar.utn.tacs.telegram;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;

import ar.utn.tacs.telegram.model.Quote;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

import org.springframework.web.client.RestTemplate;

public class TACSCryptoBot extends AbilityBot  {

	public static final String HOST = "http://localhost:8080/";
	
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
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send("Ingreso correcto al sistema", ctx.chatId()))
				.build();
	}
	
	/* Como usuario quiero poder registrar una transacción (moneda, cantidad comprada / vendida) usando Telegram */
	private String buyAction(String coin, String q) {		
		return "Se registro la transacción de compra de " + q + coin;
	}
	
	public Ability buy() {
		return Ability
				.builder()
				.name("buy")
				.info("Compra <coin-ticker> <quantity>")
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(buyAction(ctx.firstArg(), ctx.secondArg()), ctx.chatId()))
				.build();
	}
	
	private String sellAction(String coin, String q) {		
		return "Se registro la transacción de venta de " + q + coin;
	}
	
	public Ability sell() {
		return Ability
				.builder()
				.name("sell")
				.info("Vende <coin-ticker> <quantity>")
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(sellAction(ctx.firstArg(), ctx.secondArg()), ctx.chatId()))
				.build();
	}
	
	/* Como usuario quiero ver la cantidad de una criptomoneda que poseo a través de Telegram. */
	private String coinAction(String coin) {		
		return "Usted posee XXX de la cryptomoneda " + coin + ".";
	}
	
	public Ability coin() {
		return Ability
				.builder()
				.name("coin")
				.info("Cantidad de <coin-ticker>")
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(coinAction(ctx.firstArg()), ctx.chatId()))
				.build();
	}
	
	/* Como usuario quiero saber la cotización actual de una critpomoneda desde Telegram. */
	private String quoteAction(String coin) {		
		return "La cotización actual de " + coin + "XXX.";
	}
	
	public Ability quote() {
		return Ability
				.builder()
				.name("quote")
				.info("Cotización de <coin-ticker>")
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(quoteAction(ctx.firstArg()), ctx.chatId()))
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
