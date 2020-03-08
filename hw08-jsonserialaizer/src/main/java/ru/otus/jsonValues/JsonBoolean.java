package ru.otus.jsonValues;

import ru.otus.JsonValue;

public class JsonBoolean implements JsonValue {
	private boolean value;

	public JsonBoolean(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Boolean.toString(value);
	}
}
