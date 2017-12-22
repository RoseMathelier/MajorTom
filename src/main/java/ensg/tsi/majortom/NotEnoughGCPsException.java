package ensg.tsi.majortom;

/**
 * Exception thrown when a transformation is computed without enough GCPs to determine the parameters.
 * @author Rose Mathelier
 *
 */
public class NotEnoughGCPsException extends Exception {
	
	/**
	 * Empty constructor.
	 */
	public NotEnoughGCPsException() {
		super();
	}
	
	/**
	 * Constructor with an error message.
	 * @param message The error message.
	 */
	public NotEnoughGCPsException(String message) {
		super();
	}

}
