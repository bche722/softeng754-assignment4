package nz.ac.auckland.softeng754_assignment4.exception;

public class NoReviewerException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -465174252434741513L;

	public NoReviewerException() {
		super();
	}
	
	public NoReviewerException(String message) {
		super(message);
	}

}
