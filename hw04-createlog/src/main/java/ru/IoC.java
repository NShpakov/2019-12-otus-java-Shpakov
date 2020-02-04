package main.java.ru;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;

public class IoC {

	static TestLoggingInterface createTestLogging() {
		InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
		return (TestLoggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(),
				new Class<?>[]{TestLoggingInterface.class}, handler);
	}

	static class DemoInvocationHandler implements InvocationHandler {
		private final TestLoggingInterface testlog;

		DemoInvocationHandler(TestLoggingInterface testlog) {
			this.testlog = testlog;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//	Parameter[] params =method.getParameters();
			if (!method.isAnnotationPresent(Log.class)) {
				System.out.println("executed method: calculation, param:"  + method.invoke(testlog, args));
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