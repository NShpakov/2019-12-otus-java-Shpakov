package ru.otus.jsonValues;

import ru.otus.JsonValue;

public class JsonInt implements JsonValue {
	private int value;

	public JsonInt(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}