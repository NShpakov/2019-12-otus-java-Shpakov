package otus.ru.curency;

import otus.ru.inerfaces.Money;

public class Ruble extends Curency implements Money {
	private RublesNominal nominal;

	public Ruble(Number amount) {
		this.nominal = value(amount);
	}

	@Override
	public RublesNominal value(Number amount) {
		for (RublesNominal nominal : RublesNominal.values()) {
			if (nominal.getAmount().equals(amount.intValue())) {
				this.nominal = nominal;
				break;
			}
		}
		return this.nominal;
	}

	public RublesNominal getNominal() {
		if (nominal == null) {
			throw new UnsupportedOperationException("Amount not supported");
		}
		return nominal;
	}
}