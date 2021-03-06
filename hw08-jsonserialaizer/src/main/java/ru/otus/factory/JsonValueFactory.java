package ru.otus.factory;

import ru.otus.JsonValue;
import ru.otus.reflection.ReflectionHelper;
import ru.otus.exception.JsonSerializerException;
import ru.otus.jsonValues.*;


import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class JsonValueFactory {
	public static JsonValue createFromObjectField(Field field, Object object) throws JsonSerializerException {
		return createFromObject(ReflectionHelper.getFieldValue(object, field));
	}

	public static JsonValue createFromObject(Object value) throws JsonSerializerException {
		if (value == null) {
			return new JsonNull();
		}
		if (value.getClass() == String.class) {
			return new JsonString((String) value);
		} else if (value.getClass() == int.class || value.getClass() == Integer.class) {
			return new JsonInt((int) value);
		} else if (value.getClass() == long.class || value.getClass() == Long.class) {
			return new JsonLong((long) value);
		} else if (value.getClass() == float.class || value.getClass() == Float.class) {
			return new JsonFloat((float) value);
		} else if (value.getClass() == double.class || value.getClass() == Double.class) {
			return new JsonDouble((double) value);
		} else if (value.getClass() == boolean.class || value.getClass() == Boolean.class) {
			return new JsonBoolean((boolean) value);
		} else if (value.getClass().isArray()) {
			return new JsonArray(value);
		} else if (value instanceof Collection) {
			return new JsonCollection((Collection) value);
		} else if (value instanceof Map) {
			return new JsonMap((Map) value);
		} else {
			return new JsonObject(value);
		}
	}
}