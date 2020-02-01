package ru.otus.hw02;

import java.util.*;

public class Main {
	public static void main(String[] args) {

		Collection<String> listEmpty = new DIYArrayLIst<>();

		String[] str = new String[22];
		for (int i = 0; i <= 21; i++) {
			str[i] = "Строка" + i;
		}
		Collections.addAll(listEmpty, str);
		System.out.println("Проверка метода: " + "Collections.addAll(Collection<? super T> c, T... elements)");
		System.out.println(listEmpty);


		List<String> listSrc = new ArrayList<>();
		for (int i = 0; i <= 21; i++) {
			listSrc.add("стр" + i);
		}
		List<String> listCopy = new DIYArrayLIst<>(listSrc);

		Collections.copy(listCopy, listSrc);
		System.out.println("Проверка метода: " + "Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)");
		System.out.println("Результат копирования списка: " + listCopy);

		System.out.println("Проверка метода: " + "Collections.static <T> void sort(List<T> list, Comparator<? super T> c)");
		List<Car> cars = new DIYArrayLIst<>(25);
		for (int i = 0; i <= 21; i++) {
			cars.add(new Car("Audi", 1987 + i));
		}
		cars.add(new Car("Audi", 1957));
		cars.add(new Car("Audi", 2020));
		cars.add(new Car("Audi", 1963));
		System.out.println("Несортированный список автомобилей : " + cars);


		Collections.sort(cars, new CompareCarsByYear());
		System.out.println("Список автомобилей отсортирован по году : " + cars);
	}
}
