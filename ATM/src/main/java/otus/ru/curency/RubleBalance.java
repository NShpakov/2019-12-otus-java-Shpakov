package otus.ru.curency;

import otus.ru.inerfaces.Bill;
import otus.ru.inerfaces.Money;

public class RubleBalance implements Bill {

	private Integer balance = 0;

	@Override
	public void added(Money amount) {
		balance += amount.getNominal().getAmount().intValue();
	}

	@Override
	public void spent(Money amount) throws UnsupportedOperationException {
		if (balance >= amount.getNominal().getAmount().intValue()) {
			balance -= amount.getNominal().getAmount().intValue();
		} else {
			throw new UnsupportedOperationException("Недостаточно средств!");
		}
	}

	@Override
	public Integer getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Баланс счета RUR = " + balance +"руб";
	}
}
