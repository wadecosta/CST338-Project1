package project1;

public class Account extends Customer{
	double accountAmount = 0;
	int accountNumber = 0;
	int type;
	String typeName = null;
	String ss = null;
	
	public Account() {
		accountNumber = 0;
		accountAmount = 0;
		type = 1;
		ss = null;
	}
	
	public Account(String ss, int accountNumber, int type, double accountAmount) {
		this.ss = ss;
		this.accountNumber = accountNumber;
		this.accountAmount = accountAmount;
		this.type = type;
	}

	public Account accountInfo() {
		return null;
	}
	
	public void despoit(int amount) {
		//todo
	}
	
	public void newAccount(String ss, int accountNumber, int type, double accountAmount) {
		super.ss = ss;
		this.accountNumber = accountNumber;
		this.type = type;
		this.accountAmount = accountAmount;
	}
}
