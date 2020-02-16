package otus.ru;

import otus.ru.atm.RublesATM;
import otus.ru.inerfaces.ATM;

public class Main {
	public static void main(String[] args) {
		ATM atm = new RublesATM();
		atm.checkBalance();

		atm.putCash(10);
		atm.putCash(100);

		atm.putCash(5000);

		atm.checkBalance();

		atm.getCash(100);

		atm.checkBalance();

		//Попытка снять сумму больше остатка

		atm.getCash(5100);
	}

}
