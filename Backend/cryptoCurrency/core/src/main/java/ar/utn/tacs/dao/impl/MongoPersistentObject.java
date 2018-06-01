package ar.utn.tacs.dao.impl;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class MongoPersistentObject {

	@Id
	private ObjectId id;

	public MongoPersistentObject() {
		this.id = new ObjectId();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

}
