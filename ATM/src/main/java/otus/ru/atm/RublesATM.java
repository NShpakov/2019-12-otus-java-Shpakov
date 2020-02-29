package otus.ru.atm;

import otus.ru.curency.Ruble;
import otus.ru.curency.RubleBalance;
import otus.ru.curency.RublesNominal;
import otus.ru.inerfaces.ATM;
import otus.ru.inerfaces.Bill;
import otus.ru.inerfaces.Box;
import otus.ru.inerfaces.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RublesATM implements ATM {

	private Bill rurBill = new RubleBalance();
	private Box boxes = new RubleBox();
	private Integer tmp = 0;
	List<Money> moneyBox;


	@Override
	public boolean putCash(Number amount) {
		try {
			Money money = new Ruble(amount);
			boxes.put(money);
			rurBill.added(money);
		} catch (UnsupportedOperationException e) {
			System.out.println("Невозможно пополнить баланс на "+amount);
			return false;
		}
		System.out.println("Balance = "+boxes.total());
		return true;
	}

	@Override
	public boolean getCash(Number amount) {
		moneyBox = new ArrayList<>();

		if (!checkBox(amount)) {
			System.out.println("Запрошенная сумма "+amount +" превышает остаток!");
			return false;
		}
		moneyBox.forEach(money -> {
			boxes.get(money);
			rurBill.spent(money);
		});

		return false;
	}

	@Override
	public void checkBalance() {
		System.out.println("Информация по счету : "+rurBill);
	}


	public boolean checkBox(Number amount) {
		if (boxes.total().intValue() < amount.intValue()) {
			return false;
		}
		System.out.println("Запрос на снятие " + amount +"руб");
		Map<RublesNominal, Integer> buf = boxes.getBoxes();

		tmp = amount.intValue();
		buf.forEach((rubleNominal, integer) -> {
			int count;
			while((count = buf.getOrDefault(rubleNominal, 0)) > 0) {

				if (tmp / rubleNominal.getAmount() > 0) {
					tmp -= rubleNominal.getAmount();
					if (tmp >= 0) {
						System.out.println("Со счета RUR снято: " + rubleNominal.getAmount()+"руб");
						moneyBox.add(new Ruble(rubleNominal.getAmount()));
					}
				}
				buf.put(rubleNominal, --count);
			}

		});

		int calcSum = moneyBox.stream()
				.map(money -> money.getNominal().getAmount().intValue()).mapToInt(value -> value).sum();


		return amount.equals(calcSum);
	}
}
