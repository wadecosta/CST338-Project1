package project1;

public class Transaction extends Bank{
	int accountNumber = 0;
	String typeOf = null;
	String time = null;
	double amount = 0;
	boolean isClosed = false;
	
	
	public Transaction() {
		accountNumber = 0;
		typeOf = null;
		time = null;
		amount = 0;
		isClosed = false;
	}
	
	public Transaction(int accountNumber, String typeOf, double amount, boolean isClosed){
		this.accountNumber = accountNumber;
		this.typeOf = typeOf;
		this.amount = amount;
		this.isClosed = isClosed;
		this.time = time();
	}
	
	public Transaction(int accountNumber, String typeOf, boolean isClosed){
		this.accountNumber = accountNumber;
		this.typeOf = typeOf;
		this.isClosed = isClosed;
		this.time = time();
	}
	
	
}
