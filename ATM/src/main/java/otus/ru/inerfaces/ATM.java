package otus.ru.inerfaces;

public interface ATM {
	boolean putCash(Number amount);
	boolean getCash(Number amount);
	void checkBalance();
}
