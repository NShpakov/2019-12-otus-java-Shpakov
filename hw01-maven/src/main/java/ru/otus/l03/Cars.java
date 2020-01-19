package ru.otus.l03;

public class Cars {
	public String model;
	public int years;

	public Cars(String model, int years) {
		this.model = model;
		this.years = years;
	}

	@Override
	public String toString() {
		return this.model + " " + this.years;
	}
}
