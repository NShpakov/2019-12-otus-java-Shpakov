package ru.otus.hw01;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;

public class HelloOtus {
	public static void main(String[] args) {


		List<String> list = ImmutableList.of("Hello OTUS 1", "Hello OTUS 2", "Hello OTUS 3");
		print(list);

	}

	public static void print(Object... args) {
		Arrays.stream(args).forEach(System.out::println);
	}
}
