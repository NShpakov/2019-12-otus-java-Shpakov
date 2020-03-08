package ru.otus.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class ReflectionHelper {

	public static Iterable<Field> getAllFields(Object object) {
		ArrayList<Field> fields = new ArrayList<>();

		Class clazz = object.getClass();

		while (clazz != null) {
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}

		return fields;
	}

	public static Object getFieldValue(Object object, Field field) {
		boolean isAccessible = true;
		try {
			isAccessible = field.isAccessible();
			field.setAccessible(true);
			return field.get(object);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			if (field != null && !isAccessible) {
				field.setAccessible(false);
			}
		}
		return null;
	}
}
