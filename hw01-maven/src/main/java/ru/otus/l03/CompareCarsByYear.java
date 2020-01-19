package ru.otus.l03;

import java.util.Comparator;

public class CompareCarsByYear implements Comparator {


	@Override
	public int compare(Object o1, Object o2) {
		return ((Cars) o1).years - ((Cars) o2).years;
	}
}
