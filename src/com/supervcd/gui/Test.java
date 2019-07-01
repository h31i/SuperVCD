package com.supervcd.gui;
/**
* @Author: PQ  
* @Data: 2018年8月23日下午7:55:12 
*/
public class Test{

	public static void main(String[] args) {
		String[] test = {"asd","fgh","jkl"};
		String[] test2 = {"asd","fgh","jkl"};
		
		
		long start = System.currentTimeMillis();
		System.out.println(compare(test,test2));
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
	}
	
	public static Boolean compare(String[] str1, String[] str2) {
		
		if(String.join(",", str1).equals(String.join(",", str2))) {
			return true;
		}else {
			return false;
		}
		
	}

}
