package ar.utn.tacs.model.wallet;

import java.math.BigDecimal;
import java.util.HashMap;

import org.codehaus.jackson.annotate.JsonIgnoreType;

import ar.utn.tacs.model.coin.Coin;

//ESTO SE PONE POR AHORA PORQUE JACKSON ROMPE AL SERIALIZAR CLASES VACIAS
@JsonIgnoreType
public class Wallet {

//	private HashMap<Coin, BigDecimal> coinsMap;
//
//	public HashMap<Coin, BigDecimal> getCoinsMap() {
//		return coinsMap;
//	}
//
//	public void setCoinsMap(HashMap<Coin, BigDecimal> coinsMap) {
//		this.coinsMap = coinsMap;
//	}
//
//	public Wallet() {
//	}
//
//	public Wallet(HashMap<Coin, BigDecimal> coinsMap) {
//		this.coinsMap = coinsMap;
//	}
}
