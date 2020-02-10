package ru.otus.framework;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;

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
