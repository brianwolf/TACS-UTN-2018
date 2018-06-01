package ar.utn.tacs.model.transaction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.operation.Operation;
import ar.utn.tacs.model.user.User;

@JsonIgnoreProperties(value = {"id", "user"})
public class Transaction {

	private BigInteger id;
	
	private User user;
	
	private List<Operation> operations;
	
	private Date dateStart;
	
	private Date dateFinal;
	
	public Transaction() {
		this.operations = new ArrayList<Operation>();
	}

	public void addOperation(Operation operation) {
		this.operations.add(operation);
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinal() {
		return dateFinal;
	}

	public void setDateFinal(Date dateFinal) {
		this.dateFinal = dateFinal;
	}

	public List<Operation> getOperations() {
		return operations;
	}
	
	public void doOperations() throws UtnTacsException {
		for (Operation operation : this.operations) {
			operation.doOperation();
		}
	}

}
