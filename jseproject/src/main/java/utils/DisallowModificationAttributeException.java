package utils;

public class DisallowModificationAttributeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DisallowModificationAttributeException() {
		super("Disallow Modification Attribute");
	}

	public DisallowModificationAttributeException(String message) {
		super(message);
	}
}
