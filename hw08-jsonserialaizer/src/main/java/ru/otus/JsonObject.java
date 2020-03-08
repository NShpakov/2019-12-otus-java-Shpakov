package ru.otus;

import java.util.*;

public class JsonObject {
	int intNumber = 85;
	long longNumber = 999999999L;
	float floatNumber = 9.8f;
	double doubleNumber = 676.0299;
	String stringName = "Nikolay Shpakov";

	transient String stringSecretField = "Keep it in secret";

	int[] intArr = new int[]{1, 2, 3, 4, 5, 6, 7};
	String[] stringArr = new String[]{"StringArr1", "StringArr2", "StringArr3", "StringArr4"};

	List<Object> someList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, "North", 5L, 42, new InnerTestObject()));

	Map<String, Object> map = new HashMap<>();

	InnerTestObject innerObject = new InnerTestObject();

	{
		map.put("keyOne", "valueOne");
		map.put("keyTwo", "valueTwo");
		map.put("keyThree", "valueThree");
		map.put("keyFour", "valueFour");
	}

	class InnerTestObject {
		double innerTesrValue = 42.99;
	}
}
