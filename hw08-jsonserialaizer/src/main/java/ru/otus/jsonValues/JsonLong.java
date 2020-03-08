package ru.otus.jsonValues;

import ru.otus.JsonValue;

public class JsonLong implements JsonValue {
	private long value;

	public JsonLong(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Long.toString(value);
	}
}