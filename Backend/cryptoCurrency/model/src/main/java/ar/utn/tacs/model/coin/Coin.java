package ar.utn.tacs.model.coin;

public class Coin {

	private Long id;
	private String name;
	private String ticker;

	public Coin() {
	}

	public Coin(Long id, String name, String ticker) {
		this.id = id;
		this.name = name;
		this.ticker = ticker;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
