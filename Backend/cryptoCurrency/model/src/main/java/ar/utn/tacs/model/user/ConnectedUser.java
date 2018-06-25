package ar.utn.tacs.model.user;

import java.util.Date;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

import ar.utn.tacs.dao.persistent.impl.MongoPersistentObject;

@Document(collection = "connectedUsers")
@JsonIgnoreProperties(value = { "id" })
public class ConnectedUser extends MongoPersistentObject {

	private String token;

	private ObjectId idUser;

	private Date timeConnecting;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ConnectedUser)) {
			return false;
		}

		ConnectedUser other = (ConnectedUser) obj;
		return this.token.equals(other.getToken()) && this.idUser.equals(other.getIdUser());
	}

	public ConnectedUser(String token, ObjectId objectId) {
		super();
		this.token = token;
		this.idUser = objectId;
		this.timeConnecting = new Date();
	}

	public ConnectedUser() {
		super();
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

	public ObjectId getIdUser() {
		return idUser;
	}

	public void setIdUser(ObjectId idUser) {
		this.idUser = idUser;
	}

}
