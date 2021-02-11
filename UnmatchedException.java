package assignment1;

//@SuppressWarnings("serial")
public class UnmatchedException extends Exception {

	public UnmatchedException(String message) {
		super(message);
	}

	public UnmatchedException() {
		super("Passwords do not match");
	}

	
}
