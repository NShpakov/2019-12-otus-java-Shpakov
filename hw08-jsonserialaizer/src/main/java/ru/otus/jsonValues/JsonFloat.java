package ru.otus.jsonValues;

import ru.otus.JsonValue;

public class JsonFloat implements JsonValue {
	private float value;

	public JsonFloat(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Float.toString(value);
	}
}
