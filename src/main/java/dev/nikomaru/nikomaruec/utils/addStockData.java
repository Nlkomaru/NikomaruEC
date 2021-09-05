package dev.nikomaru.nikomaruec.utils;

public class addStockData {
	
	static Object object;
	static int counter;
	
	public static Object getData() {
		return object;
	}
	
	public static int getCounter() {
		
		return counter;
	}
	
	public void addData(Object o) {
		
		object = o;
	}
	
	public void addCounter(int i) {
	
	}
}
