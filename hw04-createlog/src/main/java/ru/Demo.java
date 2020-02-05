package main.java.ru;

import java.lang.reflect.Method;

public class Demo {
	public static void main(String[] args) throws NoSuchMethodException {

	//	TestLogging t = new TestLogging();
	//	Method calculation = t.getClass().getMethod("calculation", int.class);
	//	calculation.getDeclaredAnnotations();

			TestLoggingInterface ti = IoC.createTestLogging();
		ti.calculation(8);
	}


}