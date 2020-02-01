package ru.otus.hw02;

public class Car {
	public String model;
	public int years;

	public Car(String model, int years) {
		this.model = model;
		this.years = years;
	}

	@Override
	public String toString() {
		return this.model + " " + this.years;
	}
}
