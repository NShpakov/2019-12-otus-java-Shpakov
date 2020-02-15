package ru.otus.framework.tests;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;

public class TestClassCheckingExceptionInBefore {

	@Test
	public void test3() {
		System.out.println("Запущен тест 3 " + "@Test");
	}
	@Test
	public void test4() {
		System.out.println("Запущен тест 4 " + "@Test");
	}

	@Before
	public void test1() {
		throw new UnsupportedOperationException("Запущен тест 1 Before TEST FAILED");
	}

	@After
	public void test2() {
		System.out.println("Запущен тест 2 " + "@After");
	}

}
