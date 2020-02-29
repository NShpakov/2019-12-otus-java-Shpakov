package ru.otus.framework.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionHelper {

	public static Object newInstance(Class<?> clazz) {
		for (Constructor constructor : clazz.getDeclaredConstructors()) {
		//Выбор конструктора по умолчанию
			if (constructor.getParameterCount() == 0) {
				try {
					constructor.setAccessible(true);
					return constructor.newInstance();
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				} finally {
					constructor.setAccessible(false);
				}
			}
		}
		return null;
	}

	public static List<Method> getMethods(Object object, Class<? extends Annotation> annotation) {
		List<Method> methods = new ArrayList<>();
		for (Method method : object.getClass().getDeclaredMethods()) {
			for (Annotation a : method.getDeclaredAnnotationsByType(annotation)) {
				methods.add(method);
			}
		}

		return methods;
	}

	public static List<Method> getMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
		List<Method> methods = new ArrayList<>();
		for (Method method : clazz.getDeclaredMethods()) {
			for (Annotation a: method.getDeclaredAnnotationsByType(annotation)) {
				methods.add(method);
			}
		}
		return methods;
	}

	public static void invoke(Object object, Method method) {
		try {
			method.invoke(object, new Object[]{});
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	public static void invoke(Object object, List<Method> methods) {
		for (Method m : methods) {
			invoke(object, m);
		}
	}


}
