package ru.otus.framework.tests;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;

public class TestClassWithoutTests {
	public void test1() {
		System.out.println("Запущен тест 1 " + "@Test");
	}
	@After
	public void test3() {
		System.out.println("Запущен тест 3 " + "@After");
	}

	@Before
	public void test5() {
		System.out.println("Запущен тест 5 " + "@Before N2");
	}
}
