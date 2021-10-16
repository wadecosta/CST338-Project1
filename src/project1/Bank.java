package project1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Double;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * 
 * This is a Bank Program that can be use in production.
 * Creating a new virtual Bank is easy and intuitive.
 * Once you have this new Bank open it's easy to now Manage Multi-million funds.
 * 
 * The code is the rest of the comments.
 * Have Fun! 
 * 
 * I'm not liable if you use this in production and have your Bank robbed.
 * 
 * @author CostaWade
 * @version 1.1
 * @release 2021-10-15
 * @license GPL-v2
 *
 */

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
		
		System.out.println("Number of Customers: " + customers.size());
		for(int i=0; i < customers.size(); i++) {
			System.out.println("\t" + customers.get(i).name + ": " + customers.get(i).ss);
		}
		
		System.out.println("Number of Accounts: " + accounts.size());
		for(int i=0; i < accounts.size(); i++) {
			System.out.print("\t" + accounts.get(i).accountNumber);
			System.out.printf(": $%.2f", accounts.get(i).accountAmount);
			System.out.println("");
		}
		double total = 0;
        for(int i=0; i < accounts.size(); i++) {
        	total += Double.valueOf(accounts.get(i).accountAmount);
        }
		System.out.printf("Total Balance: $%.2f", total);
		System.out.println();
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
        	
        	if(tempCustomer.contains(",")) {
        		String[] customerString = tempCustomer.split(",", 4);
            	String name = customerString[0];
            	String address = customerString[1];
            	String zip = customerString[2];
            	String ss = customerString[3];
            	Customer newCustomer = new Customer(name, address, Integer.parseInt(zip), ss); 
            	customers.add(newCustomer);
        	}
        }
        int numAccounts = 5;
        inputStream.nextLine();
        for(int i=0; i < numAccounts; i++) {
        	String tempAccount = inputStream.nextLine();
        	if(tempAccount.contains(",")) {
        		String[] accountString = tempAccount.split(",",4);
        		String ss = accountString[0];
        		int accountNumber = Integer.parseInt(accountString[1]);
        		int type = Integer.parseInt(accountString[2]);
        		
        		Double accountAmount = Double.valueOf(accountString[3]);
        		Account newAccount = new Account(ss, accountNumber, type, accountAmount);
        		accounts.add(newAccount);
        	}
        }
        
	}
	
	public void newCustomer(String name, String address, int zip, String ss) {
		boolean dup = false;
		for(int i=0; i<customers.size(); i++) {
			if(customers.get(i).ss.equals(ss)) {
				dup = true;
			}
		}
		if(dup == false) {
			Customer newCustomer = new Customer(name, address, zip, ss);
			customers.add(newCustomer);
			System.out.println(name + " is added.");
		}
		else {
			System.out.println(name + " is NOT added - Duplicate SSN.");
		}
		
	}
	
	public void newAccount(String ss, int accountNumber, int type, double amount) {
		boolean dup = false;
		for(int i=0; i<accounts.size(); i++) {
			if(accounts.get(i).ss.equals(ss)) {
				dup = true;
				System.out.println("Account creation failed - " + getNameFromSS(ss) + " (" + ss + ") already has a checking account");
				break;
			}
			else if(accounts.get(i).accountNumber == accountNumber) {
				dup = true;
				System.out.println("Account creation failed - Account " + accountNumber + " already exists");
			}
				
		}
		if(dup == false) {
			Account newAccount = new Account(ss, accountNumber, type, amount);
			accounts.add(newAccount);
			System.out.println("Account creation - Number: " + accountNumber + ", Customer: " + getNameFromSS(ss));
		}
		
	}
	
	public void customerInfoWithSSN(int ss) {
		int numOfSSN = 0;
		
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).ss.contains(String.valueOf(ss))) {
				numOfSSN++;
			}
		}
		if(numOfSSN == 0)
			System.out.println("No customer with " + ss);
		else {
			for(int i = 0; i < customers.size(); i++) {
				if(customers.get(i).ss.contains(String.valueOf(ss))) {
					System.out.println("Name:\t" + customers.get(i).name);
					System.out.println(customers.get(i).address + ", " + customers.get(i).zip);
					System.out.println("SSN: " + customers.get(i).ss);
					
					String tempSS = customers.get(i).ss;
					
					int matches = 0;
					
					
					
					
					for(int j = 0; j < accounts.size(); j++) {
							
						if(accounts.get(j).ss.contains(String.valueOf(ss))) {
							
							if((accounts.get(j).type == 1) && (accounts.get(j).ss.equals(tempSS))) {
								System.out.print("Checking (" + accounts.get(j).accountNumber);
								System.out.printf("), $%.2f", accounts.get(j).accountAmount);
								System.out.println();
								matches++;
								
							}
							if((accounts.get(j).type == 2) && (accounts.get(j).ss.equals(tempSS))){
								System.out.print("Savings (" + accounts.get(j).accountNumber);
								System.out.printf("), $%.2f", accounts.get(j).accountAmount);
								System.out.println();
								matches++;
								
							}
						}
								
					}
					if(matches == 0) {
						System.out.println("No account");
						break;
					}
						
				}
			}
		}
	}
	
	public void removeCustomer(String ss) {
		int total = 0;
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).ss.equals(ss)) {
				System.out.println("Customer removed - SSN: " + customers.get(i).ss + ", Customer: " + customers.get(i).name);
				customers.remove(i);
				
				for(int j = 0; j < accounts.size(); j++) {
					if(accounts.get(j).ss.contains(ss)){
						System.out.print("  Account closed - Number: " + accounts.get(j).accountNumber);
						System.out.printf(", Balance $%.2f", accounts.get(j).accountAmount);
						System.out.println();
						total++;
							}
						}
					}
				else {
					if(total == 0) {
					System.out.println("Customer remove failed. SSN does not exist.");
					break;
				}
			}
			
		}
	}
	
	public void accountInfo(int accountNumber) {
		int accountTotal = 0;
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).accountNumber == accountNumber) {
				System.out.println("  - Number: " + accounts.get(i).accountNumber);
				int type = accounts.get(i).type;
				String typeName = "Checking";
				if(type == 2)
					typeName = "Savings";
					
				System.out.println("  - " + typeName);
				System.out.printf("  - Balance: $%.2f", accounts.get(i).accountAmount);
				
				for(int j=0; j<customers.size(); j++) {
					if(customers.get(j).ss.equals(accounts.get(i).ss)){
						System.out.println("\n" + "  - Customer: " + customers.get(j).name);
					}
				}
				accountTotal++;
			}
			else if (accountTotal == 0){
				System.out.println("Account (" + accountNumber + ") does not exist.");
				break;
			}
			
		}
	}
	
	public int getAccountNumFromSS(String ss) {
		for(int i = 0; i<accounts.size(); i++) {
			if(accounts.get(i).ss.equals(ss))
				return accounts.get(i).accountNumber;
		}
		return -1;
	}
	
	public int whereIsAccount(String ss) {
		int location = 0;
		for(int i = 0; i<accounts.size(); i++) {
			if(accounts.get(i).ss.contains("ss"))
				location = i;
		}
		return location;
	}
	
	public String getNameFromSS(String ss) {
		for(int i = 0; i<customers.size(); i++) {
			if(customers.get(i).ss.equals(ss))
				return customers.get(i).name;
		}
		return ("No Name with that SS");
	}

	public void transaction(int accountNumber) {
		for(int i = 0; i < transactions.size(); i++) {
			if(transactions.get(i).accountNumber == accountNumber) {
				System.out.print("  - Account Number: " + transactions.get(i).accountNumber + ", " + transactions.get(i).typeOf);
				if(transactions.get(i).isClosed == false) {
					System.out.printf(" ($%.2f", transactions.get(i).amount);
					System.out.println("), " + transactions.get(i).time);
				}
				else
					System.out.println(", " + transactions.get(i).time);
			}
			else
			{
				System.out.println("  - No transaction for account " + accountNumber);
				break;
			}
				
			
		}
		
	}
	
	public void deposit(int accountNumber, double amount) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).accountNumber == accountNumber) {
				accounts.get(i).accountAmount += amount;
				break;
			}
		}
		Transaction newDeposit = new Transaction(accountNumber, "Deposit", amount, false);
		transactions.add(newDeposit);
	}

	public boolean closeAccount(int accountNumber) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).accountNumber == accountNumber) {
				accounts.remove(i);
				Transaction closeAccount = new Transaction(accountNumber, "Account closed", true);
				transactions.add(closeAccount);
				return true;
			}
		}
		return false;
	}

	public void withdraw(int accountNumber, double amount) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).accountNumber == accountNumber) {
				accounts.get(i).accountAmount -= amount;
			}
		}
		Transaction newWithdraw = new Transaction(accountNumber, "Withdraw", amount, false);
		transactions.add(newWithdraw);

	}
	
	public String time() {
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
        String formatDateTime = now.format(formatter);
        return formatDateTime;
	}

}
