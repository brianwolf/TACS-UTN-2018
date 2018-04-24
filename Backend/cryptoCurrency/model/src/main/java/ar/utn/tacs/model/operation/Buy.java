package ar.utn.tacs.model.operation;

public class Buy extends Operation{

	private static final Long ID = 1l;
	private static final String DESCRIPTION = "Compra";
	
	protected Buy() {
		super(ID, DESCRIPTION);
	}

	@Override
	public void doOperation() {
		// TODO Auto-generated method stub
	}

}
