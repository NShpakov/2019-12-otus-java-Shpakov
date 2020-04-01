package main.java.ru;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IoC {

	public TestLoggingInterface createMyClass() {
		InvocationHandler handler = new DemoInvocationHandler(new TestLogging());

		return (TestLoggingInterface) Proxy.newProxyInstance(Main.class.getClassLoader(),
				new Class<?>[]{TestLoggingInterface.class}, handler);

	}

	static class DemoInvocationHandler implements InvocationHandler {
		TestLogging testlog;

		DemoInvocationHandler(TestLogging testlog) {
			this.testlog = testlog;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			if (testlog.getClass().getDeclaredMethod("calculation", int.class).isAnnotationPresent(Log.class)) {
				System.out.println("executed method: calculation, param:" + args[0]);
			}

			return method.invoke(testlog, args);
		}

		@Override
		public String toString() {
			return "DemoInvocationHandler{" +
					"myClass=" + testlog +
					'}';
		}
	}
}