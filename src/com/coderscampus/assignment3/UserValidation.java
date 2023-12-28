package com.coderscampus.assignment3;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class UserValidation {
	 
	String username;
	String password;
	String name;
	
	public UserValidation(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public static void main(String[]args) {
	try {
		Scanner input = new Scanner(new File("data.txt"));
		ArrayList<UserValidation>users = new ArrayList<>();
		while(input.hasNextLine()) {
			String data1 = input.nextLine();
			String data[] = data1.split(",");
			
			UserValidation user = new UserValidation(data[0], data[1], data[2]);
			users.add(user);
		}
		
		try (Scanner login = new Scanner(System.in)) {
			int count = 0;
			while(true) {
				System.out.println("Enter your email:");
				String userN = login.next();
				System.out.println("Enter your password");
				String passwordN = login.next();
				boolean flag = false;
				for(UserValidation user : users) {
					if(user.username.equalsIgnoreCase(userN) && user.password.equals(passwordN)) {
						System.out.println("Welcome: " + user.name);
						flag = true;
						break;
					}
				}
				if(!flag) {
					count++;
					if(count >= 5) {
						System.out.println("Too many failed login attempts, you are now locked out.");
						break;
					}else {
						System.out.println("Invalid login, please try again.");
					}
				}else {
					break;
				}
			}
		}
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}	
	}
}
