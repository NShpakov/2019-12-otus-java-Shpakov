package ru.otus.jsonValues;

import ru.otus.utils.JsonUtils;
import ru.otus.JsonValue;

public class JsonString implements JsonValue {
	private String value;

	public JsonString(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return JsonUtils.quote(value);
	}
}