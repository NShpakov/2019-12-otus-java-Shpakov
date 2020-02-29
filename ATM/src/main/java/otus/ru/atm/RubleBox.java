package otus.ru.atm;

import otus.ru.curency.Ruble;
import otus.ru.curency.RublesNominal;
import otus.ru.inerfaces.Box;
import otus.ru.inerfaces.Money;
import otus.ru.inerfaces.Nominal;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class RubleBox implements Box {

	private Map<RublesNominal, Integer> boxes = new TreeMap<>(Collections.reverseOrder());
	private Integer total = 0;

	@Override
	public void put(Money amount) {
		Ruble rubles = ((Ruble) amount);
		boxes.put(rubles.getNominal(), value(amount) + 1);
	}

	@Override
	public void get(Money amount) {
		Ruble rubles = ((Ruble) amount);
		if (total().intValue() > rubles.getNominal().getAmount()) {
			boxes.put(rubles.getNominal(), value(amount) - 1);
		}
	}

	@Override
	public Number total() {
		total = 0;
		boxes.forEach((rubleNominal, integer) -> {
			total += total(rubleNominal).intValue();
		});
		return total;
	}

	@Override
	public Number total(Nominal nominal) {
		Integer total = (boxes.get(nominal) * nominal.getAmount().intValue());
		return total;
	}

	@Override
	public Map getBoxes() {
		return boxes;
	}

	@Override
	public String toString() {
		return "RubleBox{" +
				"boxes=" + boxes +
				", total=" + total +
				'}';
	}

	private Integer value(Money amount) {
		return boxes.getOrDefault(amount.getNominal(), 0);
	}
}
