package ru.otus.l03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Cars> cars2 = new DIYArrayList<>();
		List<Cars> cars = new ArrayList<>();

		int itemsCount = 22;

		cars.add(new Cars("Audi", 1987));
		cars.add(new Cars("Opel", 1983));
		cars.add(new Cars("Opel", 1956));
		cars.add(new Cars("Opel", 1984));

		// Вывод коллекции автомобилей в стандартном списке
		for (int i = 4; i < itemsCount; i++) {
			cars.add(new Cars("Opel" + i, 1989 + i));
		}

		System.out.println(cars);

		cars2.addAll(cars);
		cars2.sort(new CompareCarsByYear());

		//вывод коллекций автомобилей в новом списке
		for (int i =0;i<itemsCount;i++ ){
			System.out.println(cars2.get(i));
		}
	}
}