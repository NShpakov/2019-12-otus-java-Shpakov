package ru.otus.jsonValues;

import ru.otus.JsonValue;

public class JsonDouble implements JsonValue {
	private double value;

	public JsonDouble(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Double.toString(value);
	}
}
