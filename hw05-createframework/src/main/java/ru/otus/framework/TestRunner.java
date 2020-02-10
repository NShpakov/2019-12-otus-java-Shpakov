package ru.otus.framework;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;


import java.lang.reflect.Method;


public class TestRunner {
	private static Class<?> clazz;
	private static int FAILED = 0;
	private static int TOTAL;

	TestRunner(Class<?> clazz) {
		this.clazz = clazz;
	}

	private void testOrder() {
		Object testCase = null;
		if (!ReflectionHelper.getMethods(clazz, Test.class).isEmpty()) {
			TOTAL = ReflectionHelper.getMethods(clazz, Test.class).size();
			System.out.println("Всего тестов: " + TOTAL);
			for (Method m : ReflectionHelper.getMethods(clazz, Test.class)) {
				try {
					testCase = ReflectionHelper.newInstance(clazz);
					ReflectionHelper.invoke(testCase, ReflectionHelper.getMethods(testCase, Before.class));
					ReflectionHelper.invoke(testCase, m);
					ReflectionHelper.invoke(testCase, ReflectionHelper.getMethods(testCase, After.class));
				} catch (Exception e) {
					FAILED++;
					e.printStackTrace();
				}

			}
			System.out.println("Количество упавших тестов :" + FAILED + " . Количество пройденных: " +(TOTAL-FAILED));
		} else {
			throw new IllegalCallerException("Не найдено методов c аннотацией @Test");
		}
	}

	private void run() {
		try {
			testOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void run(Class<?> clazz) {
		System.out.println("Запущены тесты в классе  " + clazz.getSimpleName());
		new TestRunner(clazz).run();
	}
}
