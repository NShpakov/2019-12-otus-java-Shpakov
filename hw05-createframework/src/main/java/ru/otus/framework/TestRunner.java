package ru.otus.framework;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;


import java.lang.reflect.Method;
import java.util.List;


public class TestRunner {
	private static Class<?> clazz;
	private int failed;
	private int total;

	TestRunner(Class<?> clazz) {
		this.clazz = clazz;
	}

	private void testOrder() {
		Object testCase = null;
		List<Method> listOfTests = ReflectionHelper.getMethods(clazz, Test.class);
		if (!listOfTests.isEmpty()) {
			total = listOfTests.size();
			System.out.println("Всего тестов: " + total);
			for (Method m : listOfTests) {
				try {
					testCase = ReflectionHelper.newInstance(clazz);
					ReflectionHelper.invoke(testCase, ReflectionHelper.getMethods(testCase, Before.class));
				} catch (Exception e) {
					e.printStackTrace();
					ReflectionHelper.invoke(testCase, ReflectionHelper.getMethods(testCase, After.class));
					failed=total;
					break;
				}
				try {
					ReflectionHelper.invoke(testCase, m);
				} catch (Exception e) {
					failed++;
					e.printStackTrace();
				}
			}
			System.out.println("Количество упавших тестов :" + failed + " . Количество пройденных: " + (total - failed));
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
