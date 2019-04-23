package com.test.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YaoHan {

	public static void main(String[] args) {

//		String str = "2(a)3(2(2(abc))3(b)2(c))";

		Pattern pattern = Pattern.compile("([0-9])\\(([a-zA-Z]+)\\)");

		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNext()) {
			
			String str = scanner.nextLine();
			
			if("exit".equals(str)) {
				scanner.close();
				System.exit(0);
			}
			
			while(true) {
				
				Matcher matcher = pattern.matcher(str);

				StringBuffer sbStr = new StringBuffer();
				
				boolean flag = false;
				
				while (matcher.find()) {
					flag = true;
					int repeat = Integer.valueOf(matcher.group(1));
					String repeatStr = matcher.group(2);
					StringBuilder xixi = new StringBuilder();
					for (int i = 0; i < repeat; i++) {
						xixi.append(repeatStr);
					}
					
					matcher.appendReplacement(sbStr, xixi.reverse().toString());
				}
				
				matcher.appendTail(sbStr);
				
				
				if(!flag) {
					System.out.println(sbStr.reverse());
					break;
				}
				
				str = sbStr.toString();
				
			}
			
		}
		
		

	}

}
