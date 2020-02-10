package ru.otus.framework;

public class Main {
	public static void main(String[] args) {
		TestRunner.run(TestClass.class);
		System.out.println("_____________________________");
		TestRunner.run(TestClassWithoutTests.class);
	}
}