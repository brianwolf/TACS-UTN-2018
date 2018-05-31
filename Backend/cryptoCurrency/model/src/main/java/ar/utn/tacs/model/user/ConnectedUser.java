package ar.utn.tacs.model.user;

import java.math.BigInteger;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "connectedUsers")
@JsonIgnoreProperties(value = { "id" })
public class ConnectedUser {

	@Id
	private BigInteger id;

	private String token;

	private BigInteger idUser;

	private Date timeConnecting;

	public ConnectedUser(String token, BigInteger idUser) {
		super();
		this.token = token;
		this.idUser = idUser;
	}

	public ConnectedUser() {
		super();
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTimeConnecting() {
		return timeConnecting;
	}

	public void setTimeConnecting(Date timeConnecting) {
		this.timeConnecting = timeConnecting;
	}

	public BigInteger getIdUser() {
		return idUser;
	}

	public void setIdUser(BigInteger idUser) {
		this.idUser = idUser;
	}

}
