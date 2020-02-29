package otus.ru.inerfaces;

import java.util.Map;

public interface Box {
	void put(Money amount);
	void get(Money amount);
	Number total();
	Number total(Nominal nominal);
	Map getBoxes();
}
