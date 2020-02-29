package otus.ru.curency;

import otus.ru.inerfaces.Nominal;

public enum RublesNominal implements Nominal {
	RUR_100(100),
	RUR_200(200),
	RUR_500(500),
	RUR_1000(1000),
	RUR_5000(5000);

	private Integer amount;

	RublesNominal(Integer amount) {
		this.amount = amount;
	}

	public Integer getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "RublesNominal{" +
				"amount=" + amount +
				'}';
	}
}