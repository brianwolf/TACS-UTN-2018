package ar.utn.tacs.service.external;

import ar.utn.tacs.model.coin.Coin;

public class MarketCoin {
	
	Coin coin;
	String rank;
	String price_usd;
	String price_btc;
	String volume_usd_24h;
	String market_cap_usd;
	String available_supply;
	
	public Coin getCoin() {
		return coin;
	}
	public void setCoin(Coin coin) {
		this.coin = coin;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getPrice_usd() {
		return price_usd;
	}
	public void setPrice_usd(String price_usd) {
		this.price_usd = price_usd;
	}
	public String getPrice_btc() {
		return price_btc;
	}
	public void setPrice_btc(String price_btc) {
		this.price_btc = price_btc;
	}
	public String getVolume_usd_24h() {
		return volume_usd_24h;
	}
	public void setVolume_usd_24h(String volume_usd_24h) {
		this.volume_usd_24h = volume_usd_24h;
	}
	public String getMarket_cap_usd() {
		return market_cap_usd;
	}
	public void setMarket_cap_usd(String market_cap_usd) {
		this.market_cap_usd = market_cap_usd;
	}
	public String getAvailable_supply() {
		return available_supply;
	}
	public void setAvailable_supply(String available_supply) {
		this.available_supply = available_supply;
	}
	public String getTotal_supply() {
		return total_supply;
	}
	public void setTotal_supply(String total_supply) {
		this.total_supply = total_supply;
	}
	public String getPercent_change_1h() {
		return percent_change_1h;
	}
	public void setPercent_change_1h(String percent_change_1h) {
		this.percent_change_1h = percent_change_1h;
	}
	public String getPercent_change_24h() {
		return percent_change_24h;
	}
	public void setPercent_change_24h(String percent_change_24h) {
		this.percent_change_24h = percent_change_24h;
	}
	public String getPercent_change_7d() {
		return percent_change_7d;
	}
	public void setPercent_change_7d(String percent_change_7d) {
		this.percent_change_7d = percent_change_7d;
	}
	public String getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	String total_supply;
	String percent_change_1h;
	String percent_change_24h;
	String percent_change_7d;
	String last_updated;
}
