package fr.eni.ENIEncheres.dal;

public class DALException extends Exception{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6177233138283055844L;
	

	// Constructeurs
	public DALException() {
		super();
	}

	public DALException(String message) {
		super(message);
	}

	public DALException(String message, Throwable exception) {
		super(message, exception);
	}

	public void addError(int errorSqlSelect) {

	}

}
