package ru.otus.framework.tests;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;

public class TestAfterException {
	@Before
	public void test1() {
		System.out.println("Запущен тест 1 " + "@Before N1");
	}

	@After
	public void test3() {
		throw new UnsupportedOperationException("After FAILED");
	}

	@Test
	public void test4() {
		System.out.println("Запущен тест 4 " + "@Test");
	}
}
