package ar.utn.tacs.telegram;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;

import ar.utn.tacs.telegram.command.*;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

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
				.action(ctx -> silent.send(ctx.user().fullName(), ctx.chatId()))
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
				.info("Compra <coin-ticker> <quantity>")
				.input(2)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(Transaction.exec(ctx, "/wallet/buy"), ctx.chatId()))
				.build();
	}

	public Ability sell() {
		return Ability
				.builder()
				.name("sell")
				.info("Vende <coin-ticker> <quantity>")
				.input(2)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(Transaction.exec(ctx, "/wallet/sale"), ctx.chatId()))
				.build();
	}
	
	/* Como usuario quiero ver la cantidad de una criptomoneda que poseo a través de Telegram. */
	public Ability coin() {
		return Ability
				.builder()
				.name("amount")
				.info("Cantidad de <coin-ticker>")
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
				.info("Cotización de <coin-ticker>")
				.input(1)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> silent.send(Quote.exec(ctx), ctx.chatId()))
				.build();
	}

}
