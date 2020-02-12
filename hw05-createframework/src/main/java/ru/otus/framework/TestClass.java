package ru.otus.framework;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;

public class TestClass {
	@Test
	public void test1() {
		System.out.println("Запущен тест 1 " + "@Test");
	}

	@Test
	public void test7() {
		System.out.println("Запущен тест 7 " + "Exception 2");
		throw new UnsupportedOperationException("TEST FAILED");
	}

	@Before
	public void test2() {
		System.out.println("Запущен тест 2 " + "@Before N1");
	}

	@After
	public void test3() {
		System.out.println("Запущен тест 3 " + "@After");
	}

	@Test
	public void test4() {
		System.out.println("Запущен тест 4 " + "@Test");
	}

	@Test
	public void test6() {
		System.out.println("Запущен тест 6 " + "Exception");
		throw new UnsupportedOperationException("TEST FAILED");
	}


	@Before
	public void test5() {
		System.out.println("Запущен тест 5 " + "@Before N2");
	}

}