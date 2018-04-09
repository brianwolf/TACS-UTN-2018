package ar.utn.tacs.model.coin;

public class Coin {

	private long id;
	private String name;
	private String ticker;

	public Coin() {
	}

	public Coin(long id, String name, String ticker) {
		this.id = id;
		this.name = name;
		this.ticker = ticker;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

}
