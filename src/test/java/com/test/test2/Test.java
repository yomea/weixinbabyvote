package com.test.test2;

public class Test {
	
	private Integer age = 10;
	
	public void print() {
		
		Test test = new Test() {
			
			public void print() {
				System.out.println(age);
			}
			
		};
		
		test.print();
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(Integer.class.isAssignableFrom(Number.class));
		
	}

}
