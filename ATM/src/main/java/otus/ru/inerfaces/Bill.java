package otus.ru.inerfaces;

public interface Bill {
	void added(Money amount);
	void spent (Money amount);
	Number getBalance();
}
