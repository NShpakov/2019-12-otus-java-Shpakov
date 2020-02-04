package main.java.ru;

public class Demo {
	public static void main(String[] args) {
		TestLoggingInterface ti = IoC.createTestLogging();
		ti.calculation(8);
	}
}