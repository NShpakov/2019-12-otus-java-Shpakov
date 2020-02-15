package ru.otus.framework.runner;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;
import ru.otus.framework.helper.ReflectionHelper;


import java.lang.reflect.Method;
import java.util.List;


public class TestRunner {
	private static Class<?> clazz;
	private int failed;
	private int total;
	private Object testCase = null;

	TestRunner(Class<?> clazz) {
		this.clazz = clazz;
	}

	private void callBefore() {
		List<Method> listOfBefore = ReflectionHelper.getMethods(testCase, Before.class);
		ReflectionHelper.invoke(testCase, listOfBefore);

	}

	private void callAfter() {
		List<Method> listOfAfter = ReflectionHelper.getMethods(testCase, After.class);
		ReflectionHelper.invoke(testCase, listOfAfter);

	}

	private void testOrder() {
		List<Method> listOfTests = ReflectionHelper.getMethods(clazz, Test.class);
		total = listOfTests.size();
		if (listOfTests.isEmpty()) {
			throw new IllegalCallerException("Не найдено методов c аннотацией @Test");
		}
		System.out.println("Всего тестов: " + total);
		for (Method m : listOfTests) {
			try {
				testCase = ReflectionHelper.newInstance(clazz);
				callBefore();
			} catch (Exception e) {
				callAfter();
				e.printStackTrace();
				failed = total;
				break;
			}
			try {
				ReflectionHelper.invoke(testCase, m);
			} catch (Exception e) {
				failed++;
				e.printStackTrace();
			} finally {
				callAfter();
			}
		}
		System.out.println("Количество упавших тестов :" + failed + " . Количество пройденных: " + (total - failed));
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
