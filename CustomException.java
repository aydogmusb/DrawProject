package example;

public class CustomException extends Exception {

	String message;

	public CustomException(String message) {
		super(message);
	}
	
}
