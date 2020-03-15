package ru.otus.jsonValues;

import ru.otus.exception.JsonSerializerException;
import ru.otus.factory.JsonValueFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JsonArray extends JsonCollection {
	public JsonArray(Object array) throws JsonSerializerException {
		int length = Array.getLength(array);
		this.value = new ArrayList<>();

		for (int i = 0; i < length; i++) {
			this.value.add(JsonValueFactory.createFromObject(Array.get(array, i)));
		}
	}

}
