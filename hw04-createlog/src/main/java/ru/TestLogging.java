package main.java.ru;

public class TestLogging implements TestLoggingInterface {
	@Log
	public void calculation(int param) {
		System.out.println("Запущен метод calculation");
	}

}
