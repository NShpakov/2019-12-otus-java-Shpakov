package ru.otus.exception;

public class JsonSerializerException extends Exception {
	public JsonSerializerException() {
	}

	public JsonSerializerException(String message) {
		super(message);
	}

	public JsonSerializerException(String message, Throwable cause) {
		super(message, cause);
	}

	public JsonSerializerException(Throwable cause) {
		super(cause);
	}
}