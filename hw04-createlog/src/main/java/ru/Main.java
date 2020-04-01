package main.java.ru;

public class Main {
	public static void main(String[] args) throws NoSuchMethodException {

		IoC i = new IoC();
		TestLoggingInterface ti = i.createMyClass();
		ti.calculation(17);

	}
}