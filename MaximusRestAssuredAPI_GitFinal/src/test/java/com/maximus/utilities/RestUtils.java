package com.maximus.utilities;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

public class RestUtils {
	
	public static String empName() {
		String randomName = RandomStringUtils.randomAlphanumeric(1);
		return ("Smith"+randomName);
	}
	
	public static String empSalary() {
		String randomSalary = RandomStringUtils.randomNumeric(5);
		return randomSalary;
	}
	
	public static String empAge() {
		String age = RandomStringUtils.randomNumeric(2);
		return age;
	}

}
