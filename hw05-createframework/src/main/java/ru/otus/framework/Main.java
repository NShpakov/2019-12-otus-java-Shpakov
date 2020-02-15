package ru.otus.framework;

import ru.otus.framework.runner.TestRunner;
import ru.otus.framework.tests.TestClass;
import ru.otus.framework.tests.TestClassCheckingExceptionInBefore;
import ru.otus.framework.tests.TestClassWithoutTests;

public class Main {
	public static void main(String[] args) {
		TestRunner.run(TestClass.class);
		System.out.println("_____________________________");
		TestRunner.run(TestClassWithoutTests.class);
		System.out.println("_____________________________");
		TestRunner.run(TestClassCheckingExceptionInBefore.class);
	}
}