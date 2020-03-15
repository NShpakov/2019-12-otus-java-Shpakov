package ru.otus;

import ru.otus.exception.JsonSerializerException;
import ru.otus.factory.JsonValueFactory;

public class JsonSerializer {

	public static String objectToJson(Object object) throws JsonSerializerException {

		return JsonValueFactory.createFromObject(object).toString();
	}
}