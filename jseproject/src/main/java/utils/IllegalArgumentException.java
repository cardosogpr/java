package utils;

public class IllegalArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalArgumentException() {
		super("Cannot add entity with an existing ID. Use an update method for existing entities.");
	}

	public IllegalArgumentException(String message) {
		super(message);
	}
	
	

}
