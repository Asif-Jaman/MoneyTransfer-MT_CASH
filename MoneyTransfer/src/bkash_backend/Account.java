package bkash_backend;

public class Account {
	public final double WITHDRAW_ERROR = -1;

	private double balance = 0;
	private String userName;

	public Account(String name) {
		this.setUserName(name);
	}

	public double withdraw(double amount) {
		amount = Math.abs(amount);
		if (this.balance >= amount) {
			this.balance -= amount;
			return amount;
		} else
			return this.WITHDRAW_ERROR;
	}

	public double deposit(double amount) {
		amount = Math.abs(amount);
		this.balance += amount;
		this.balance = (double) Math.round(this.balance * 100) / 100;
		return amount;
	}

	public double checkBalance() {
		return this.balance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
