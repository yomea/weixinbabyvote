package com.test.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class YaoHan2 {

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        String line = sc.nextLine();

	        String[] arr = line.split(" +");
	        int n = Integer.parseInt(arr[0]);

	        List<String> list = new ArrayList<String>();
	        for(int i = 0; i < n; i++){
	            String temp = arr[i + 1];
	            while(temp.length() > 8){
	                String temp2 = temp.substring(0, 8);
	                temp = temp.substring(8, temp.length());
	                list.add(temp2 + " ");
	            }
	            for(int j = temp.length(); j < 8 ;j++){
	                temp = temp + "0";
	            }
	            list.add(temp + " ");
	        }
	        Collections.sort(list);

	        list.stream().forEach(System.out::print);
	    }
		
}
