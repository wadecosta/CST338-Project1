package project1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	
	String name;
	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	
	public Bank() {
		this.name = null;
	}
	
	public Bank(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void bankInfo() {
		System.out.println("Bank Name: " + this.name);
		System.out.println("Account(s) :" + accounts);
		System.out.println("Customer(s) :" + customers);
		System.out.println("Transaction(s) :" + transactions);
	}

	public void readData(String fileName) {
		Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File not found or could not be opened.");
            System.exit(0);
        }

        // number of Customers
        int numCustomers = inputStream.nextInt();
        // swallow newline character
        inputStream.nextLine();
        
        // first customer... (you will most likely want to use a loop here using the number above...)
        for(int i=0; i < numCustomers; i++) {
        	String tempCustomer = inputStream.nextLine();
        	System.out.println(tempCustomer);
        	String[] customerString = tempCustomer.split(",", 5);
        	
        	//String name = customerString[0];
        	//String address = customerString[1];
        	//String zip = customerString[2];
        	//String ss = customerString[3];
        	
        	Customer newCustomer = new Customer();
        	
        	//Customer customers[i] = (Customer) new Customer(tempCustomer);
        	inputStream.nextLine();
        	//customers.add(tempCustomer);
        }
        
        String customer1 = inputStream.nextLine();

        System.out.printf("%nThe number of accounts is %d.%n", numCustomers);
        System.out.printf("First customer: %s%n", customer1);
		
	}

	public void accountInfo(int i) {
		// TODO Auto-generated method stub
		
	}

	public void deposit(int i, double d) {
		// TODO Auto-generated method stub
		
	}

	public boolean closeAccount(int i) {
		// TODO Auto-generated method stub
		return false;
	}

	public void withdraw(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public void transaction(int i) {
		// TODO Auto-generated method stub
		
	}

}
