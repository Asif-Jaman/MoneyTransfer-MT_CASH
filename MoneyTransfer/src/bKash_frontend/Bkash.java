package bKash_frontend;

import java.util.Scanner;

import bkash_backend.AccountInfo;
import bkash_backend.BkashAccount;

public class Bkash {

	private int id = 1;
	private Scanner in = new Scanner(System.in);

	public boolean showMenu(BkashAccount account, BkashAccount[] accounts, int maximumAccounts) {
		boolean changeAccountRequested = false;
		do {
			System.out.println("bKash System");
			System.out.println("--------------");
			showMenuOptions();
			int input = readInt(">> ");
			switch (input) {
			case 1:
				showAccountMenu(account);
				break;
			case 2:
				System.out.print("Receiver Name: ");
				String receiver = in.next();
				sendMoney(accounts, account, receiver);
				break;
			case 3:
				withdrawFromAccount(account);
				break;
			case 4:
				mobileRecharge(account);
				break;
			case 5:
				changeAccountRequested = true;
				break;
			case 6:
				System.out.println("System terminated");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option");
			}
		} while (!changeAccountRequested);
		return changeAccountRequested;
	}

	private void mobileRecharge(BkashAccount account) {
		System.out.print("Sender mobile number: ");
		int length;
		do {
			length = in.next().length();
		} while (length != 11);
		withdrawFromAccount(account);
	}

	private void sendMoney(BkashAccount[] accounts, BkashAccount account, String receiverName) {
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] != null) {
				if (accounts[i].getUserName().equals(receiverName)) {
					System.out.println("Amount to send: ");
					double amount = in.nextDouble();
					double withDrawError = account.withdraw(amount);
					if (withDrawError != account.WITHDRAW_ERROR) {
						accounts[i].deposit(amount);
						System.out.println(amount + " sent to " + receiverName);
					}
				} else {
					System.out.println(receiverName + " account not found.");
				}
			}
		}
	}

	private void showMenuOptions() {
		System.out.println("\t1: My Account");
		System.out.println("\t2: Send Money");
		System.out.println("\t3: Cash Out");
		System.out.println("\t4: Mobile Recharge");
		System.out.println("\t5: Sign In/Create new account");
		System.out.println("\t6: Exit");
	}

	public void showAccountMenu(BkashAccount account) {
		boolean exit = false;
		do {
			if (exit)
				break;
			System.out.println("My Account");
			System.out.println("---------------");
			System.out.println("\t1: Withdraw");
			System.out.println("\t2: Deposit");
			System.out.println("\t3: Check Balance");
			System.out.println("\t4: Back");
			int input = readInt(">> ");
			switch (input) {
			case 1:
				withdrawFromAccount(account);
				break;
			case 2:
				depositToAccount(account);
				break;
			case 3:
				System.out.println("Current balance: " + account.checkBalance());
				break;
			case 4:
				exit = true;
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
		} while (true);
	}

	private void withdrawFromAccount(BkashAccount account) {
		double amount = readDouble("Amount: ");
		double withdrawn = account.withdraw(amount);
		if (withdrawn != account.WITHDRAW_ERROR) {
			System.out.println(withdrawn + "tk withdrawn");
		} else {
			System.out.println("Not enoungh balance to withdraw");
		}
	}

	private void depositToAccount(BkashAccount account) {
		double amount = readDouble("Amount: ");
		double deposit = account.deposit(amount);
		System.out.println(deposit + "tk deposited");
	}

	private int readInt(String prompt) {
		System.out.print(prompt);
		return in.nextInt();
	}

	private double readDouble(String prompt) {
		System.out.print(prompt);
		return in.nextDouble();
	}

	public int authenticate(BkashAccount[] accounts, int maximumAccounts) {
		int accountIndex = -1;

		System.out.println("\t1: Sign in");
		System.out.println("\t2: Create New Account");
		int input = readInt(">> ");
		switch (input) {
		case 1:
			accountIndex = signIn(accounts, maximumAccounts);
			break;
		case 2:
			accountIndex = createNewAccount(accounts, maximumAccounts);
			break;
		default:
			System.out.println("Invalid option");
			break;
		}

		return accountIndex;
	}

	private int createNewAccount(BkashAccount[] accounts, int maximumAccounts) {
		int accountIndex = -1;

		System.out.print("Phone number : ");
		String name = in.next();
		System.out.print("Password: ");
		String password = in.next();
		AccountInfo acInfo = new AccountInfo(this.id++, password);
		BkashAccount bAccount = new BkashAccount(name, acInfo);

		for (int accountPointer = 0; accountPointer < maximumAccounts; accountPointer++) {
			if (accounts[accountPointer] == null) {
				accounts[accountPointer] = bAccount;
				accountIndex = accountPointer;
				break;
			}
		}
		return accountIndex;
	}

	private int signIn(BkashAccount[] accounts, int maxAccounts) {
		int accountIndex = -1;

		System.out.print("User name: ");
		String name = in.next();
		System.out.print("Password: ");
		String password = in.next();
		AccountInfo acInfo = new AccountInfo(this.id++, password);
		BkashAccount bAccount = new BkashAccount(name, acInfo);

		boolean accountFound = false;
		// Checks if account exists in accounts[]
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] != null) {
				if (accounts[i].equals(bAccount)) {
					accountIndex = i;
					accountFound = true;
				}
			}
		}
		if (!accountFound) {
			System.out.println("Wrong sign in info");
		}
		return accountIndex;
	}

}