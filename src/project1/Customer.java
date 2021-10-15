package project1;

public class Customer extends Bank {
	String address;
	String name;
	String ss;
	int zip;
	
	public Customer() {
		this.name = null;
		this.address = null;
		this.zip = 0;
		this.ss = null;
	}
	

	public Customer(String name, String address, int zip, String ss) {
		this.name = name;
		this.address = address;
		this.zip = zip;
		this.ss = ss;
	}
	
	public void newCustomer(String name, String address, int zip, String ss) {
		this.name = name;
		this.address = address;
		this.zip = zip;
		this.ss = ss;
	}
	
	
	@Override
	public String toString() {
		return (this.name + this.address + this.zip + this.ss);
	}

}
