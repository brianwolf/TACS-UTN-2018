package ar.utn.tacs.model.transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.utn.tacs.model.operation.Operation;
import ar.utn.tacs.model.user.User;

@JsonIgnoreProperties(value = {"id", "user"})
public class Transaction {

	private Long id;
	
	private User user;
	
	private List<Operation> operations = new ArrayList<Operation>();
	
	Date dateStart;
	
	Date dateFinal;
		
	public List<Operation> getOperations() {
		return operations;
	}

	public void addOperation(Operation operation) {
		this.operations.add(operation);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
}
